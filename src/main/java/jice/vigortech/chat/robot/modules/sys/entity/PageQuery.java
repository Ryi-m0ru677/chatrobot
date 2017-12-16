package jice.vigortech.chat.robot.modules.sys.entity;

public class PageQuery {

	private String name;
	private String sql;
	private Integer pageNO;
	private Integer rowNo;
	private Integer pageSize = 10;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Integer getPageNO() {
		return pageNO;
	}

	public void setPageNO(Integer pageNO) {
		this.pageNO = pageNO;
	}

	public Integer getRowNo() {
		return rowNo;
	}

	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
