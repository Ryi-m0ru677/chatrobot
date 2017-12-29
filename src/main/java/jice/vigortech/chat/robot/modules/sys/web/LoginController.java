package jice.vigortech.chat.robot.modules.sys.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.model.web.BaseController;
import jice.vigortech.chat.robot.common.util.CorsHandler;
import jice.vigortech.chat.robot.modules.user.service.UserService;

@Controller
public class LoginController extends BaseController implements LogoutSuccessHandler,AuthenticationSuccessHandler,AuthenticationFailureHandler {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/chg-passwd")
	public String chgPasswd(@RequestParam("password") String password, @RequestParam("newPassword") String newPassword) {
		if(StringUtils.isEmpty(password) || StringUtils.isEmpty(newPassword)) {
			resCode = ResultCode.USER_PASSWORD_EMPTY;
		} else if(password.equals(newPassword)) {
			resCode = ResultCode.USER_PASSWORD_NEW_SAMEWITH_OLD;
		} else {
			resCode = userService.chgPasswd(password, newPassword);
		}
		data = null;
		return Result();
	}
	
	/**
	 * 验证失败调用
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("authenticationFailure:");
		exception.printStackTrace();
		if(exception instanceof UsernameNotFoundException){
			resCode = ResultCode.USER_NOTEXIST;
		}else if(exception instanceof DisabledException){
			resCode = ResultCode.USER_DENIED;
		}else if(exception instanceof BadCredentialsException){
			resCode = ResultCode.USER_NAME_OR_PASSWORD_ERROR;
		}else{
			resCode = ResultCode.OPERATION_FAILED;
		}
		data = null;
		data = userService.getUserTokenByUserName("zhangsan");
		CorsHandler.addCorsMapping(response);
		writeResponse(response);
	}
	
	/**
	 * 验证成功调用
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String username = request.getParameter("username");
		System.out.println(username);
		data = userService.getUserTokenByUserName(username);
		resCode = ResultCode.OPERATION_SUCCESSED;
		CorsHandler.addCorsMapping(response);
		writeResponse(response);	
	}
	/**
	 * 注销时调用
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		data = null;
		resCode = ResultCode.OPERATION_SUCCESSED;
		CorsHandler.addCorsMapping(response);
		writeResponse(response);
	}
}
