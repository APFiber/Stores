package bean;

public class USER_COMMONDATA {
	private String USERNAME;
	private String LOGINDATE;
	private String USERROLE;
	private String LOGINID;
	
	private String USERLOGINID;
	private String USERROLE1;
	private String STATUS;
	private int totalNoOfRecords;
	private int totalNoOfPages;
	private int pageNo;
	private long selectedSerialNo;
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String username) {
		USERNAME = username;
	}
	public String getLOGINDATE() {
		return LOGINDATE;
	}
	public void setLOGINDATE(String logindate) {
		LOGINDATE = logindate;
	}
	public String getUSERROLE() {
		return USERROLE;
	}
	public void setUSERROLE(String userrole) {
		USERROLE = userrole;
	}
	public String getLOGINID() {
		return LOGINID;
	}
	public void setLOGINID(String loginid) {
		LOGINID = loginid;
	}
	public String getUSERLOGINID() {
		return USERLOGINID;
	}
	public void setUSERLOGINID(String userloginid) {
		USERLOGINID = userloginid;
	}
	public String getUSERROLE1() {
		return USERROLE1;
	}
	public void setUSERROLE1(String userrole1) {
		USERROLE1 = userrole1;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String status) {
		STATUS = status;
	}
	public int getTotalNoOfRecords() {
		return totalNoOfRecords;
	}
	public void setTotalNoOfRecords(int totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}
	public int getTotalNoOfPages() {
		return totalNoOfPages;
	}
	public void setTotalNoOfPages(int totalNoOfPages) {
		this.totalNoOfPages = totalNoOfPages;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public long getSelectedSerialNo() {
		return selectedSerialNo;
	}
	public void setSelectedSerialNo(long selectedSerialNo) {
		this.selectedSerialNo = selectedSerialNo;
	}
	
	
	

}

