package bean;

public class PROJECT_MASTER 
{
	  private long PROJECTSRNO;
	  private String PROJECTNAME;
	  private String LOCATION;
	  private String MANAGER;
	  private String EMAILID;
	  private String CREATEDBY;
	  private String CREATEDDATE;
	  
	public long getPROJECTSRNO() {
		return PROJECTSRNO;
	}
	public void setPROJECTSRNO(long projectsrno) {
		PROJECTSRNO = projectsrno;
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
	public String getCREATEDBY() {
		return CREATEDBY;
	}
	public void setCREATEDBY(String createdby) {
		CREATEDBY = createdby;
	}
	public String getCREATEDDATE() {
		return CREATEDDATE;
	}
	public void setCREATEDDATE(String createddate) {
		CREATEDDATE = createddate;
	}
	
}
