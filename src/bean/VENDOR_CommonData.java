package bean;

public class VENDOR_CommonData {
	private String USERNAME;
	private String LOGINDATE;
	private String USERROLE;
	private String TYPE;
	private long VSSRNO;
	private String NAME;
	private String CITY;
	private String DISTRICT;      
	private String STATE; 
	private String COUNTRY;
	private long PIN;           
	private String PROJECT;       
	private String MANAGER;       
	private long PHONENO;       
	private String STATUS; 
	private int totalNoOfRecords;
	private int totalNoOfPages;
	private int PAGENO;
	private long selectedSerialNo;
	private String LOGINID;

	public String getLOGINID() {
		return LOGINID;
	}
	public void setLOGINID(String loginid) {
		LOGINID = loginid;
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
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String type) {
		TYPE = type;
	}
	
	public long getVSSRNO() {
		return VSSRNO;
	}
	public void setVSSRNO(long vssrno) {
		VSSRNO = vssrno;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String name) {
		NAME = name;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String city) {
		CITY = city;
	}
	public String getDISTRICT() {
		return DISTRICT;
	}
	public void setDISTRICT(String district) {
		DISTRICT = district;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String state) {
		STATE = state;
	}

	public String getPROJECT() {
		return PROJECT;
	}
	public void setPROJECT(String project) {
		PROJECT = project;
	}
	public String getMANAGER() {
		return MANAGER;
	}
	public void setMANAGER(String manager) {
		MANAGER = manager;
	}
	
	public long getPIN() {
		return PIN;
	}
	public void setPIN(long pin) {
		PIN = pin;
	}
	public long getPHONENO() {
		return PHONENO;
	}
	public void setPHONENO(long phoneno) {
		PHONENO = phoneno;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String status) {
		STATUS = status;
	}
	public int getPAGENO() {
		return PAGENO;
	}
	public void setPAGENO(int pageno) {
		PAGENO = pageno;
	}
	public String getCOUNTRY() {
		return COUNTRY;
	}
	public void setCOUNTRY(String country) {
		COUNTRY = country;
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
	public long getSelectedSerialNo() {
		return selectedSerialNo;
	}
	public void setSelectedSerialNo(long selectedSerialNo) {
		this.selectedSerialNo = selectedSerialNo;
	}


}
