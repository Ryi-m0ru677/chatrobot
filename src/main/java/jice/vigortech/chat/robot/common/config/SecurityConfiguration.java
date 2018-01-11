package jice.vigortech.chat.robot.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.security.LoginUserDetailsService;
import jice.vigortech.chat.robot.common.security.SecurityInterceptor;
import jice.vigortech.chat.robot.modules.sys.web.LoginController;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity//通过 @EnableWebMvcSecurity 注解开启Spring Security的功能
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	LoginController loginHandler;
	@Autowired
	SecurityInterceptor securityInterceptor;

	/**
	 * 对权限进行拦截校验配置
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.formLogin()
			.loginProcessingUrl(SysConstants.SYS_URL+"/login")
				.passwordParameter("password")
				.usernameParameter("username")
				.successHandler(loginHandler)
				.failureHandler(loginHandler)
				.permitAll();
		
		http.logout()
			.logoutSuccessHandler(loginHandler)
			.logoutUrl(SysConstants.SYS_URL+"/logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
		//TODO
		http.authorizeRequests()
			//越细的权限放在后面	
			.antMatchers(SysConstants.SYS_URL+"/login").hasAnyRole("SYS_USER","SYS_ADMIN")
			/*.antMatchers(SysConstants.SYS_URL+"/intent/**").hasRole(SysConstants.SYS_USE_INTENT)
			.antMatchers(SysConstants.SYS_URL+"/dict/**").hasRole(SysConstants.SYS_USE_DICT)
			.antMatchers(SysConstants.SYS_URL+"/app/**").hasRole(SysConstants.SYS_USE_APP)
			.antMatchers(SysConstants.SYS_URL + "/**").authenticated()*/
			;
		
		http.addFilterAfter(securityInterceptor, ExceptionTranslationFilter.class);
	}
	
  
	@Autowired
	LoginUserDetailsService userDetailsService;
	/**
	 * 配置密码加密
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		SystemWideSaltSource saltSource = new SystemWideSaltSource();
		saltSource.setSystemWideSalt(SysConstants.PASSWORD_SALT);
		authProvider.setSaltSource(saltSource);
		
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(new Md5PasswordEncoder());
		auth.authenticationProvider(authProvider);
	}
}
