package bean;

public class PO_CommonData {
	
	private String USER_NAME="";
	private String LOGIN_DATE="";
	private String USER_ROLE="";
	private String LOGIN_ID="";
	private String PO_NO;  
	private String PO_SUPPLIER_NAME; 
	private int total_NoOf_Records;
	private int total_NoOf_Pages;
	private int PAGE_NO;
	private long selected_Serial_No;
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String user_name) {
		USER_NAME = user_name;
	}
	public String getLOGIN_DATE() {
		return LOGIN_DATE;
	}
	public void setLOGIN_DATE(String login_date) {
		LOGIN_DATE = login_date;
	}
	public String getUSER_ROLE() {
		return USER_ROLE;
	}
	public void setUSER_ROLE(String user_role) {
		USER_ROLE = user_role;
	}
	public String getLOGIN_ID() {
		return LOGIN_ID;
	}
	public void setLOGIN_ID(String login_id) {
		LOGIN_ID = login_id;
	}
	public String getPO_NO() {
		return PO_NO;
	}
	public void setPO_NO(String po_no) {
		PO_NO = po_no;
	}
	public String getPO_SUPPLIER_NAME() {
		return PO_SUPPLIER_NAME;
	}
	public void setPO_SUPPLIER_NAME(String po_supplier_name) {
		PO_SUPPLIER_NAME = po_supplier_name;
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
	public int getPAGE_NO() {
		return PAGE_NO;
	}
	public void setPAGE_NO(int page_no) {
		PAGE_NO = page_no;
	}
	public long getSelected_Serial_No() {
		return selected_Serial_No;
	}
	public void setSelected_Serial_No(long selected_Serial_No) {
		this.selected_Serial_No = selected_Serial_No;
	}

}
