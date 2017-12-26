package jice.vigortech.chat.robot.common.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.model.web.BaseController;
import jice.vigortech.chat.robot.common.util.CorsHandler;

@Component
public class SecurityInterceptor extends GenericFilterBean implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}
	
	AuthenticationTrustResolver trustResolver;
	public SecurityInterceptor(){
		trustResolver = new AuthenticationTrustResolverImpl();
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (AccessDeniedException ade) {
        	ResultCode retCode = null;
        	if(trustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())) {
        		retCode = ResultCode.SESSION_INVALID;
        	} else {
        		retCode = ResultCode.OPERATION_NOT_PERMITTED;
        	}
    		CorsHandler.addCorsMapping((HttpServletResponse) response);
    		BaseController.writeResponse((HttpServletResponse) response, retCode);
        }
		
	}
}
