package jice.vigortech.chat.robot.common.util;

public class MyStringUtil {

	 public static void main(String[] args) {
		  String str="每次和小朋友玩捉迷藏的时候，我总是等他们次先藏好，我就回家。";
		 // System.out.println(str.indexOf("和"));
		  System.out.println("start="+str.indexOf("小朋友玩捉迷藏"));
		  System.out.println("end="+(str.indexOf("小朋友玩捉迷藏")+("小朋友玩捉迷藏".length())));
		  System.out.println("end="+str.lastIndexOf("小朋友玩捉迷藏"));
		  stringFun('次',str);
		 }
	 private static void stringFun(char c, String str) {
		  boolean flag=true; 
		  int index=-1; //用于保存初次比中的下标值
		  char[] chars=str.toCharArray();
		  for(int i=0;i<chars.length;i++) {
		   if(chars[i]==c&&flag) {
		    index=i;
		    flag=false;
		   }
		   System.out.print(chars[i]);
		  }
		  if(index!=-1) System.out.println("\n'"+c+"'字符在串中初次出现的下标为:"+index);
		  else System.out.println("\n'"+c+"'字符未在串中出现过");
	}
	
}
