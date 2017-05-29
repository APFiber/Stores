package org;


public class ActionErrors
{
	
	public java.util.Vector v = new java.util.Vector () ;
	  public void  addError ( ActionError actionerror )
		{
	      if ( actionerror != null )
		  v.add ( actionerror ) ;
		}
	  public java.util.Vector getErrors ()
		{
		      return this.v ;
		}
}


