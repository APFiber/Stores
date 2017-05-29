package org;
public class ActionError
{
	private String errorDesc  ;
	private String formType  ;
	private String requestId   ;
	public ActionError ( java.lang.String formType  , java.lang.String requestId , java.lang.String errorDesc )
	{
		this.formType = formType ;
		this.requestId  = requestId  ;
		this.errorDesc = errorDesc ;
	}
	public java.lang.String getErrorDesc () 
	{
		return this.errorDesc ;
	}
	public void setErrorDesc ( java.lang.String errorDesc )
	{
       this.errorDesc = errorDesc  ;
	}
	public java.lang.String getFormType ()
	{
		return this.formType ;
	}
	public void setFormType( java.lang.String formType )
	{
		this.formType = formType ;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
