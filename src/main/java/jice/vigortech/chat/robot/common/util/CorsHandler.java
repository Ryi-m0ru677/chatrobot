package jice.vigortech.chat.robot.common.util;

import javax.servlet.http.HttpServletResponse;

import jice.vigortech.chat.robot.common.constants.SysConstants;

public class CorsHandler {

	public static void addCorsMapping(HttpServletResponse response) {
		if(SysConstants.SERVER_ALLOW_CORSDOMAIN) {
				response.setHeader("Access-Control-Allow-Origin", SysConstants.SERVER_CORSDOMAIN);
				response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
				response.setHeader("Access-Control-Max-Age", "3600");
				response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
				response.setHeader("Access-Control-Allow-Credentials", "true");
		}
	}
}
