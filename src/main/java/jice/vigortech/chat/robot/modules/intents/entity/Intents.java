package jice.vigortech.chat.robot.modules.intents.entity;

import java.io.Serializable;

public class Intents implements Serializable{

	/**
	 * 场景
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Integer rank;//等级
	//private Intents contexts_in;
	private String actionName;
	private RobotAction action;//动作
	
	
}
