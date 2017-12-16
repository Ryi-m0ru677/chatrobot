package jice.vigortech.chat.robot.modules.intents.entity;

import java.io.Serializable;

public class RobotAction implements Serializable{

	/**
	 * 动作
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer intents;
	private String name;
	private Integer flag;//是否必须
	private String typeName;
	
}
