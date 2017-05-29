package bean;

public class VSP_Commondata 
{
	private String USERNAME;
	private String LOGINDATE;
	private String USERROLE;
	private long VPSRNO;
	private long VSSRNO;	
	private long PROJECTSRNO;
	private String PROJECTNAME;
	private String LOCATION;
	private String MANAGER;
	private String EMAILID;
	private int totalNoOfRecords;
	private int totalNoOfPages;
	private int PAGENO;
	private long selectedSerialNo;
	private String LOGINID;
	
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
	public long getVPSRNO() {
		return VPSRNO;
	}
	public void setVPSRNO(long vpsrno) {
		VPSRNO = vpsrno;
	}
	public long getVSSRNO() {
		return VSSRNO;
	}
	public void setVSSRNO(long vssrno) {
		VSSRNO = vssrno;
	}
	public long getPROJECTSRNO() {
		return PROJECTSRNO;
	}
	public void setPROJECTSRNO(long projectsrno) {
		PROJECTSRNO = projectsrno;
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
	public int getPAGENO() {
		return PAGENO;
	}
	public void setPAGENO(int pageno) {
		PAGENO = pageno;
	}
	public long getSelectedSerialNo() {
		return selectedSerialNo;
	}
	public void setSelectedSerialNo(long selectedSerialNo) {
		this.selectedSerialNo = selectedSerialNo;
	}
	public String getLOGINID() {
		return LOGINID;
	}
	public void setLOGINID(String loginid) {
		LOGINID = loginid;
	}
	public String getPROJECTNAME() {
		return PROJECTNAME;
	}
	public void setPROJECTNAME(String projectname) {
		PROJECTNAME = projectname;
	}
	public String getLOCATION() {
		return LOCATION;
	}
	public void setLOCATION(String location) {
		LOCATION = location;
	}
	public String getMANAGER() {
		return MANAGER;
	}
	public void setMANAGER(String manager) {
		MANAGER = manager;
	}
	public String getEMAILID() {
		return EMAILID;
	}
	public void setEMAILID(String emailid) {
		EMAILID = emailid;
	}
	

}
