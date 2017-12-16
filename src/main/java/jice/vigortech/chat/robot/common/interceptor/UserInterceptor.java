package jice.vigortech.chat.robot.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.model.web.BaseController;

@Component
public class UserInterceptor  extends BaseController implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		Long now = System.currentTimeMillis();
		if(session == null/* || session.getAttribute("username") == null */|| (now - session.getLastAccessedTime()) > 7200000) {
			resCode = ResultCode.SESSION_INVALID;
			data = null;
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().write(Result());
			response.getWriter().flush();
			response.getWriter().close();
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
