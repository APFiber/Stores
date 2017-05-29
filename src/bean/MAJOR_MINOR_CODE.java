package bean;

public class MAJOR_MINOR_CODE {
	private long MAJORMINORSRNO;
	private long MAJORCODE;
	private String MAJORCODEDESC;
	private long MINORCODE;
	private String MINORCODEDESC;
	private String CREATEDBY;
	private String CREATEDDATE;
	private String STATUS;
	public long getMAJORMINORSRNO() {
		return MAJORMINORSRNO;
	}
	public void setMAJORMINORSRNO(long majorminorsrno) {
		MAJORMINORSRNO = majorminorsrno;
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
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String status) {
		STATUS = status;
	}
}
