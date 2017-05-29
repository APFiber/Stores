package bean;

public class MAJOR_MINOR_CommonData {
	private String USERNAME;
	private String LOGINDATE;
	private String USERROLE;
	private String LOGINID;
	private long MAJORCODE;
	private String MAJORCODEDESC;
	private long MINORCODE;
	private String MINORCODEDESC;
	private int total_NoOf_Records;
	private int total_NoOf_Pages;
	private int page_No;
	private long selected_Serial_No;
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
	public long getMAJORCODE() {
		return MAJORCODE;
	}
	public void setMAJORCODE(long majorcode) {
		MAJORCODE = majorcode;
	}
	public String getMAJORCODEDESC() {
		return MAJORCODEDESC;
	}
	public void setMAJORCODEDESC(String majorcodedesc) {
		MAJORCODEDESC = majorcodedesc;
	}
	public long getMINORCODE() {
		return MINORCODE;
	}
	public void setMINORCODE(long minorcode) {
		MINORCODE = minorcode;
	}
	public String getMINORCODEDESC() {
		return MINORCODEDESC;
	}
	public void setMINORCODEDESC(String minorcodedesc) {
		MINORCODEDESC = minorcodedesc;
	}
	public int getTotal_NoOf_Records() {
		return total_NoOf_Records;
	}
	public void setTotal_NoOf_Records(int total_NoOf_Records) {
		this.total_NoOf_Records = total_NoOf_Records;
	}
	public int getTotal_NoOf_Pages() {
		return total_NoOf_Pages;
	}
	public void setTotal_NoOf_Pages(int total_NoOf_Pages) {
		this.total_NoOf_Pages = total_NoOf_Pages;
	}
	public int getPage_No() {
		return page_No;
	}
	public void setPage_No(int page_No) {
		this.page_No = page_No;
	}
	public long getSelected_Serial_No() {
		return selected_Serial_No;
	}
	public void setSelected_Serial_No(long selected_Serial_No) {
		this.selected_Serial_No = selected_Serial_No;
	}
	
}
