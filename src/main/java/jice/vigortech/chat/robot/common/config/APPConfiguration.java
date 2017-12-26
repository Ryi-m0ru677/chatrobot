package jice.vigortech.chat.robot.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.interceptor.LogInterceptor;
import jice.vigortech.chat.robot.common.interceptor.UserInterceptor;
@Component
public class APPConfiguration extends WebMvcConfigurerAdapter{

	@Autowired
	UserInterceptor userInterceptor;
	@Autowired
	LogInterceptor loginInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userInterceptor)
		//.addPathPatterns(SysConstants.SYS_URL+"/user/**")//addPathPatterns 用于添加拦截规则
		.addPathPatterns(SysConstants.SYS_URL+"/login/**")//addPathPatterns 用于添加拦截规则
		.excludePathPatterns(SysConstants.SYS_URL+"/user/bind")// excludePathPatterns 用户排除拦截
		.excludePathPatterns("/test/**")
		;
		//TODO
		registry.addInterceptor(loginInterceptor);
		super.addInterceptors(registry);
	}
	/**
	 * 跨域请求
	 * 
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		if(SysConstants.SERVER_ALLOW_CORSDOMAIN) {
			registry.addMapping(SysConstants.SYS_URL + "/**")
				.allowedOrigins(SysConstants.SERVER_CORSDOMAIN)
				.allowedMethods("POST", "GET")
				.allowCredentials(true)
				.allowedHeaders("X-Requested-With, Origin, Content-Type")
				.maxAge(3600);
		super.addCorsMappings(registry);
		}
	}
	
}
