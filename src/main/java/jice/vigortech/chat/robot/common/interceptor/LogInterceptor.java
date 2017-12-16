package jice.vigortech.chat.robot.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
@Component
public class LogInterceptor implements HandlerInterceptor  {

	private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
			logger.info("access url={}, params={}, queryString={}", request.getRequestURL(), JSON.toJSONString(request.getParameterMap()), request.getQueryString());
			return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(ex != null) {
			logger.error("access url={}, params={}, queryString={}, error={}", request.getRequestURL(), JSON.toJSONString(request.getParameterMap()), request.getQueryString(), ex);
		} else {
			logger.info("process returned\n url={}, params={}, queryString={}", request.getRequestURL(), JSON.toJSONString(request.getParameterMap()), request.getQueryString());
		}
	}

}

