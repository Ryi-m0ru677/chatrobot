package jice.vigortech.chat.robot.common.util;

public class MySubstringUtil {

	public static void main(String[] args) {
		String str = "/Users/jonathanwei/summary/GITRepository/rasa_nlu/models/聊天/聊天_20171228-110324";
		String[] temp = str.split("_");
		
		String[] t1 = temp[2].split("-");
		String d1 = t1[0].substring(0, 4);
		String d2 = t1[0].substring(4, 6);
		String d3 = t1[0].substring(6, 8);
		
		String d4 = t1[1].substring(0, 2);
		String d5 = t1[1].substring(2, 4);
		String d6 = t1[1].substring(4, 6);
		String time = d1+"-"+d2+"-"+d3+" "+d4+":"+d5+":"+d6;
		System.out.println(time);
		String[] name = temp[1].split("/");
		System.out.println(name[3]);
		System.out.println(name[3]+"/"+time);
	}
	
	public static String getDate(String str){
		String[] temp = str.split("_");
		
		String[] t1 = temp[2].split("-");
		String d1 = t1[0].substring(0, 4);
		String d2 = t1[0].substring(4, 6);
		String d3 = t1[0].substring(6, 8);
		
		String d4 = t1[1].substring(0, 2);
		String d5 = t1[1].substring(2, 4);
		String d6 = t1[1].substring(4, 6);
		String time = d1+"-"+d2+"-"+d3+" "+d4+":"+d5+":"+d6;
		System.out.println(time);
		
		return time;
	}
}
