package bean;

public class STOCK_SUMMARY {
	private long STOCKMASTERSRNO;
	private long ITEMCODE;
	private String SUMMARYDATE;
	private String ITEMDESCRIPTION;
	private double OPENINGBALANCE;
	private long RECEIPTS;
	private long ISSUES;
	private double CLOSINGBALANCE;
	private String CREATEDBY;
	private String CREATEDDATE;
	public long getSTOCKMASTERSRNO() {
		return STOCKMASTERSRNO;
	}
	public void setSTOCKMASTERSRNO(long stockmastersrno) {
		STOCKMASTERSRNO = stockmastersrno;
	}
	public long getITEMCODE() {
		return ITEMCODE;
	}
	public void setITEMCODE(long itemcode) {
		ITEMCODE = itemcode;
	}
	public String getSUMMARYDATE() {
		return SUMMARYDATE;
	}
	public void setSUMMARYDATE(String summarydate) {
		SUMMARYDATE = summarydate;
	}
	public String getITEMDESCRIPTION() {
		return ITEMDESCRIPTION;
	}
	public void setITEMDESCRIPTION(String itemdescription) {
		ITEMDESCRIPTION = itemdescription;
	}
	public double getOPENINGBALANCE() {
		return OPENINGBALANCE;
	}
	public void setOPENINGBALANCE(double openingbalance) {
		OPENINGBALANCE = openingbalance;
	}
	public long getRECEIPTS() {
		return RECEIPTS;
	}
	public void setRECEIPTS(long receipts) {
		RECEIPTS = receipts;
	}
	public long getISSUES() {
		return ISSUES;
	}
	public void setISSUES(long issues) {
		ISSUES = issues;
	}
	public double getCLOSINGBALANCE() {
		return CLOSINGBALANCE;
	}
	public void setCLOSINGBALANCE(double closingbalance) {
		CLOSINGBALANCE = closingbalance;
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
