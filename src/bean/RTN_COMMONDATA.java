package bean;

public class RTN_COMMONDATA {
	private String USERNAME;
	private String LOGINDATE;
	private String USERROLE;
	private String LOGINID;
	private long ITEMCODE;
	private long GPNO;
	private String RETURNDATE;
	private String PURPOSEOFRETURN;
	private int totalNoOfRecords;
	private int totalNoOfPages;
	private int pageNo;
	private long selectedSerialNo;
	
	public long getITEMCODE() {
		return ITEMCODE;
	}
	public void setITEMCODE(long itemcode) {
		ITEMCODE = itemcode;
	}
	public long getGPNO() {
		return GPNO;
	}
	public void setGPNO(long gpno) {
		GPNO = gpno;
	}
	public String getRETURNDATE() {
		return RETURNDATE;
	}
	public void setRETURNDATE(String returndate) {
		RETURNDATE = returndate;
	}
	public String getPURPOSEOFRETURN() {
		return PURPOSEOFRETURN;
	}
	public void setPURPOSEOFRETURN(String purposeofreturn) {
		PURPOSEOFRETURN = purposeofreturn;
	}
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
