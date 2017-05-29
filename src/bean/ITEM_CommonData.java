package bean;

public class ITEM_CommonData {
	
	private String USERNAME;
	private String LOGINDATE;
	private String USERROLE;
	private long ITEMCODE;
	private String ITEMGROUP;
	private String ITEMMAKE;
	private String ITEMSTATUS;
	private String ITEMDESCRIPTION;
	private int total_NoOf_Records;
	private String LOGINID;
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
	public long getITEMCODE() {
		return ITEMCODE;
	}
	public void setITEMCODE(long itemcode) {
		ITEMCODE = itemcode;
	}
	public String getITEMGROUP() {
		return ITEMGROUP;
	}
	public void setITEMGROUP(String itemgroup) {
		ITEMGROUP = itemgroup;
	}
	public String getITEMMAKE() {
		return ITEMMAKE;
	}
	public void setITEMMAKE(String itemmake) {
		ITEMMAKE = itemmake;
	}
	public String getITEMSTATUS() {
		return ITEMSTATUS;
	}
	public void setITEMSTATUS(String itemstatus) {
		ITEMSTATUS = itemstatus;
	}
	public String getITEMDESCRIPTION() {
		return ITEMDESCRIPTION;
	}
	public void setITEMDESCRIPTION(String itemdescription) {
		ITEMDESCRIPTION = itemdescription;
	}
	public int getTotal_NoOf_Records() {
		return total_NoOf_Records;
	}
	public void setTotal_NoOf_Records(int total_NoOf_Records) {
		this.total_NoOf_Records = total_NoOf_Records;
	}
	public String getLOGINID() {
		return LOGINID;
	}
	public void setLOGINID(String loginid) {
		LOGINID = loginid;
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
