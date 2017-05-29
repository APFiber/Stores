package bean;

public class PO_TC {
	private long POTCSRNO;
	private String PONO;
	private String CONDITION;
	private String DESCRIPTION;
	public long getPOTCSRNO() {
		return POTCSRNO;
	}
	public void setPOTCSRNO(long potcsrno) {
		POTCSRNO = potcsrno;
	}
	public String getPONO() {
		return PONO;
	}
	public void setPONO(String pono) {
		PONO = pono;
	}
	public String getCONDITION() {
		return CONDITION;
	}
	public void setCONDITION(String condition) {
		CONDITION = condition;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String description) {
		DESCRIPTION = description;
	}
}
