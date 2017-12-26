package jice.vigortech.chat.robot.common.util;

import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import jice.vigortech.chat.robot.common.constants.SysConstants;

public class Md5PasswordEncoderWithSalt {

	public static String encodePassword(String password) {
		return encodePassword(password, SysConstants.PASSWORD_SALT);
	}
	
	public static String encodePassword(String password, String salt) {
		return new Md5PasswordEncoder().encodePassword(password, salt);
	}

	public static boolean isPasswordValid(String encPassword, String password) {
		return new Md5PasswordEncoder().isPasswordValid(encPassword, password, SysConstants.PASSWORD_SALT);
	}
	
	public static boolean isPasswordValid(String encPassword, String password, String salt) {
		return new Md5PasswordEncoder().isPasswordValid(encPassword, password, salt);
	}
	
	public static void main(String args[]) {
		System.out.println(encodePassword("123456", "POIU$#@!"));
		System.out.println(isPasswordValid("5ab5e04195fecffa9e2e4ebdf6664991", "123456", "POIU$#@!"));
		String userNameBase64="MTIzNDU2";  
		byte[] userNameByte =null;
		try {
			userNameByte = Base64.decodeBase64(userNameBase64.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		String userName = new String(userNameByte);//获得解密后的用户名  
		System.out.println(userName);
	}
}
