package bean;

public class GP_DETAILS {
	//drstfkl;
	private long GPDETAILSSRNO;
	private long  GPNO;           
	private long ITEMCODE ;         
	private String ITEMGROUP;         
	private String  ITEMMAKE;       
	private String ITEMDESCRIPTION;   
	private long ISSUEQTY;  
	private long VENDORCODE;
	private String  REMARKS; 
	private String CREATEDBY;      
	private long CREATEDDATE;
	
	
	public long getGPDETAILSSRNO() {
		return GPDETAILSSRNO;
	}
	public void setGPDETAILSSRNO(long gpdetailssrno) {
		GPDETAILSSRNO = gpdetailssrno;
	}
	public long getGPNO() {
		return GPNO;
	}
	public void setGPNO(long gpno) {
		GPNO = gpno;
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
	public String getITEMDESCRIPTION() {
		return ITEMDESCRIPTION;
	}
	public void setITEMDESCRIPTION(String itemdescription) {
		ITEMDESCRIPTION = itemdescription;
	}
	public long getISSUEQTY() {
		return ISSUEQTY;
	}
	public void setISSUEQTY(long issueqty) {
		ISSUEQTY = issueqty;
	}
	public long getVENDORCODE() {
		return VENDORCODE;
	}
	public void setVENDORCODE(long vendorcode) {
		VENDORCODE = vendorcode;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String remarks) {
		REMARKS = remarks;
	}
	public String getCREATEDBY() {
		return CREATEDBY;
	}
	public void setCREATEDBY(String createdby) {
		CREATEDBY = createdby;
	}
	public long getCREATEDDATE() {
		return CREATEDDATE;
	}
	public void setCREATEDDATE(long createddate) {
		CREATEDDATE = createddate;
	}
	

}
