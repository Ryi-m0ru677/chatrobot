package jice.vigortech.chat.robot.modules.sys.system.entity;

public class PageQuery {

	private String name;
	
	private String sql;
	private String date;
	private String dept;//部门
	private int pageNo;
	private int rowNo;
	private int pageSize = 10;
	
	public String getName() {
		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	
	
	public int getPageNo() {
		if(pageNo <= 0) {
			pageNo = 1;
		}
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		if(pageSize <= 0) {
			pageSize = 20;
		}
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowNo() {
		rowNo = (getPageNo() - 1) * getPageSize();
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
}
