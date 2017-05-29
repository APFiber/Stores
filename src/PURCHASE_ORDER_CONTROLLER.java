import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import org.Date_library;
import org.Global_Data;
import org.KrrCommon;
import org.PurchaseorderBusiness;
import bean.ITEM_DETAILS;
import bean.PO_CommonData;
import bean.PO_DETAILS;
import bean.PO_HEADER;
import bean.PO_TC;
import bean.VENDOR_SUPPLIER;

public class PURCHASE_ORDER_CONTROLLER extends HttpServlet {
	Connection con ;
	Global_Data Global_Data=new Global_Data();
	org.Initclass initclass=new org.Initclass();
	KrrCommon krrcommon=new KrrCommon();

	/**
	 * Constructor of the object.
	 */
	public PURCHASE_ORDER_CONTROLLER() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Returns information about the servlet, such as 
	 * author, version, and copyright. 
	 *
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		initclass.initurl(getServletContext().getRealPath("/properties/database.properties"), Global_Data);
	}

	public void service (javax.servlet.http.HttpServletRequest req ,
			javax.servlet.http.HttpServletResponse resp )
	{
		org.ActionErrors errors=new org.ActionErrors();

		con = initclass.getConnection(Global_Data, errors);
		PurchaseorderBusiness PurchaseorderBusiness=new PurchaseorderBusiness();
		String l_ers="";
		ITEM_DETAILS ITEMDETAILS=new ITEM_DETAILS();
		PO_HEADER PO_HEADER=new PO_HEADER();
		PO_DETAILS PO_DETAILS=new PO_DETAILS();       			//bean
		PO_TC PO_TC=new PO_TC();								//bean
		bean.PO_CommonData PO_CommonData=new PO_CommonData();	//bean
		List<PO_HEADER> PO_HEADERList = new ArrayList<PO_HEADER>();

		try
		{
			int option = (int) krrcommon.ConvertNum(req.getParameter("Option"));
			Date_library Date_library=new Date_library();
			bean.VENDOR_SUPPLIER VENDOR_DETAILS=new VENDOR_SUPPLIER();
			org.ITEM_SERVICE ITEMSERVICE = new org.ITEM_SERVICE();
			List<PO_HEADER> SupplierList=new ArrayList<PO_HEADER>();
			List<bean.PO_DETAILS> PO_DETAILSList = new ArrayList<bean.PO_DETAILS>();
			List<bean.PO_TC> PO_TCList = new ArrayList<bean.PO_TC>();
			List<bean.MAJOR_MINOR_CODE> CONDITIONLIST=new ArrayList<bean.MAJOR_MINOR_CODE>();
			List<bean.VENDOR_SUPPLIER> vendor_details_list=new ArrayList<VENDOR_SUPPLIER>();
			List<bean.MAJOR_MINOR_CODE> majorUnitsList=new ArrayList<bean.MAJOR_MINOR_CODE>();
			PO_CommonData.setUSER_NAME(req.getParameter("USERNAME"));
			PO_CommonData.setLOGIN_DATE(req.getParameter("DATE"));
			PO_CommonData.setUSER_ROLE(req.getParameter("USERROLE"));
			PO_CommonData.setLOGIN_ID(req.getParameter("LOGINID"));
//			PO_CommonData.setPO_NO(krrcommon.CheckEmptyReturn(req.getParameter("PONO")));
			PO_CommonData.setPO_SUPPLIER_NAME(krrcommon.CheckEmptyReturn(req.getParameter("POSUPPLIERNAME")));

			PO_CommonData.setPAGE_NO((int)krrcommon.ConvertNum(req.getParameter("pageNo")));
			if(PO_CommonData.getPAGE_NO()==0) PO_CommonData.setPAGE_NO(1);
			String POSRNO= req.getParameter("POSRNO");
			PO_HEADER.setPOSRNO(krrcommon.ConvertNum(POSRNO));
			PO_CommonData.setSelected_Serial_No(PO_HEADER.getPOSRNO());

			con = initclass.getConnection(Global_Data, errors);
			if(krrcommon.isValuenull(PO_CommonData.getUSER_ROLE())|| krrcommon.isValuenull(PO_CommonData.getUSER_NAME())|| krrcommon.isValuenull(PO_CommonData.getLOGIN_DATE()))
			{
				l_ers="Purhase order Details is accessed after login only";
				errors.addError(new org.ActionError("Error","",l_ers));
			}
			if(errors.getErrors().size()==0)
			{
				switch(option) 
				{
				case 1://Grid Display
				{
					System.out.println("case 1");
					l_ers=PurchaseorderBusiness.get_POHEADER(con,PO_HEADERList,PO_CommonData);
					l_ers=l_ers+PurchaseorderBusiness.getSupplierName_POHEADER(con, SupplierList);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("message", req.getAttribute("message"));
					}
					else
					{
						req.setAttribute("message", l_ers);
					}
					req.setAttribute("poheaderList",PO_HEADERList);
					req.setAttribute("supplierlist",SupplierList);
					req.setAttribute("po_commondata", PO_CommonData);
					getServletContext().getRequestDispatcher("/purchaseHeaderGrid.jsp").forward( req , resp ) ;
				}
				break;
				case 2:	//Add PO display
				{
					System.out.println("case 2");
					l_ers=PurchaseorderBusiness.get_supplier_name_SUPPLIER_DETAILS(con, vendor_details_list);
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=l_ers+PurchaseorderBusiness.get_POHEADER_details(con, PO_HEADER);
						req.setAttribute("suppList",vendor_details_list);
						req.setAttribute("po_header", PO_HEADER);
						req.setAttribute("message", " ");
						req.setAttribute("po_commondata", PO_CommonData);
						getServletContext().getRequestDispatcher("/addPO.jsp").forward(req, resp);
					}
					else
					{
						l_ers=l_ers+PurchaseorderBusiness.get_POHEADER(con,PO_HEADERList,PO_CommonData);
						req.setAttribute("poheaderList",PO_HEADERList);
						req.setAttribute("supplierlist",SupplierList);
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/purchaseHeaderGrid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 3://Save PO Header
				{
					System.out.println("case 3");
					String PONO=req.getParameter("ADD_PONO");
					PO_HEADER.setPONO(PONO);
					PO_HEADER.setPODATE(req.getParameter("PODATE"));  
					PO_HEADER.setPOSUPPLIERCODE(krrcommon.ConvertNum(req.getParameter("PO_SUPPLIER_CODE")));
					PO_HEADER.setPOSUPPLIERNAME(req.getParameter("supplier_Code")); 
					PO_HEADER.setPOSUPPLIERADDRESSLINE1(req.getParameter("POSUPPLIERADDRESSLINE1")); 
					PO_HEADER.setPOSUPPLIERADDRESSLINE2(req.getParameter("POSUPPLIERADDRESSLINE2")); 
					PO_HEADER.setPOSUPPLIERADDRESSLINE3(req.getParameter("POSUPPLIERADDRESSLINE3")); 
					PO_HEADER.setPOSUPPLIERADDRESSLINE4(req.getParameter("POSUPPLIERADDRESSLINE4")); 
					PO_HEADER.setPOSUPPLIERCITY(req.getParameter("POSUPPLIERCITY"));        
					PO_HEADER.setPOSUPPLIERDISTRICT(req.getParameter("POSUPPLIERDISTRICT"));     
					PO_HEADER.setPOSUPPLIERSTATE(req.getParameter("POSUPPLIERSTATE"));        
					PO_HEADER.setPOSUPPLIERCOUNTRY(req.getParameter("POSUPPLIERCOUNTRY"));      
					PO_HEADER.setPOSUPPLIERPIN(krrcommon.ConvertNum(req.getParameter("POSUPPLIERPIN")));         
					PO_HEADER.setPOSUPPLIERCONTACTPERSON(req.getParameter("POSUPPLIERCONTACTPERSON"));
					PO_HEADER.setPOSUPPLIERCONTACTNO(krrcommon.ConvertNum(req.getParameter("POSUPPLIERCONTACTNO")));    
					PO_HEADER.setPOSUBJECT(req.getParameter("POSUBJECT")); 
					PO_HEADER.setPOVATRATE(krrcommon.ConvertDouble(req.getParameter("POVATRATE" )));             
					PO_HEADER.setPOCSTRATE(krrcommon.ConvertDouble(req.getParameter("POCSTRATE"))) ;             
					PO_HEADER.setPOEXCISEDUTYRATE(krrcommon.ConvertDouble(req.getParameter("POEXCISEDUTYRATE")));       
					PO_HEADER.setPOLABOURCHARGES(krrcommon.ConvertDouble(req.getParameter("POLABOURCHARGES")));       
					PO_HEADER.setPOFREIGHTAMT(krrcommon.ConvertDouble(req.getParameter("POFREIGHTAMT")));       
					PO_HEADER.setPOSERVICETAX(krrcommon.ConvertDouble(req.getParameter("POSERVICETAX")));
					PO_HEADER.setQUOTATIONNO(krrcommon.ConvertNum(req.getParameter("QUOTATIONNO")));
					PO_HEADER.setQUOTATIONDATE(req.getParameter("QUOTATIONDATE")); 
					PO_HEADER.setDOCREFNO(req.getParameter("DOCREFNO")); 	
					PO_HEADER.setDOCREFDATE(req.getParameter("DOCREFDATE")); 
					PO_HEADER.setACKNOWNO(req.getParameter("ACKNOWNO")); 	
					PO_HEADER.setACKNOWDATE(req.getParameter("ACKNOWDATE")); 
					PO_HEADER.setPAYADVNO(krrcommon.ConvertNum(req.getParameter("PAYADVNO")));
					PO_HEADER.setPAYADVDATE(req.getParameter("PAYADVDATE")); 
					PO_HEADER.setCHEQUENO(krrcommon.ConvertNum(req.getParameter("CHEQUENO")));
					PO_HEADER.setCHEQUEDATE(req.getParameter("CHEQUEDATE")); 
					PO_HEADER.setBANKCODE(req.getParameter("BANKCODE")); 
					PO_HEADER.setBANKNAME(req.getParameter("BANKNAME")); 
					PO_HEADER.setADVAMOUNT(krrcommon.ConvertDouble(req.getParameter("ADVAMOUNT")));       
					PO_HEADER.setBALAMOUNT(krrcommon.ConvertDouble(req.getParameter("BALAMOUNT")));       
					PO_HEADER.setPOREMARKS(req.getParameter("POREMARKS"));              
					l_ers=PurchaseorderBusiness.insert_update_POHEADER(con, PO_HEADER, PO_CommonData);
					l_ers=l_ers+PurchaseorderBusiness.get_supplier_name_SUPPLIER_DETAILS(con, vendor_details_list);
					if(krrcommon.isValuenull(l_ers))
					{
						PO_HEADER.setPODATE(Date_library.date_Conv(PO_HEADER.getPODATE(), "yyyyMMdd", "dd-MM-yyyy"));
						PO_HEADER.setQUOTATIONDATE(Date_library.date_Conv(PO_HEADER.getQUOTATIONDATE(), "yyyyMMdd", "dd-MM-yyyy"));
						PO_HEADER.setDOCREFDATE(Date_library.date_Conv(PO_HEADER.getDOCREFDATE(), "yyyyMMdd", "dd-MM-yyyy"));
						PO_HEADER.setACKNOWDATE(Date_library.date_Conv(PO_HEADER.getACKNOWDATE(), "yyyyMMdd", "dd-MM-yyyy"));
						PO_HEADER.setPAYADVDATE(Date_library.date_Conv(PO_HEADER.getPAYADVDATE(), "yyyyMMdd", "dd-MM-yyyy"));
						PO_HEADER.setCHEQUEDATE(Date_library.date_Conv(PO_HEADER.getCHEQUEDATE(), "yyyyMMdd", "dd-MM-yyyy"));
						req.setAttribute("suppList",vendor_details_list);
						req.setAttribute("po_header", PO_HEADER);
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("supplierBean", VENDOR_DETAILS);
						req.setAttribute("message", "Record saved successfully");
						getServletContext().getRequestDispatcher("/addPO.jsp").forward( req,resp) ;	
					}
					else
					{
						req.setAttribute("suppList",vendor_details_list);
						req.setAttribute("po_header", PO_HEADER);
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/addPO.jsp").forward(req, resp);
					}
				}
				break;

				case 4://Display addPO_Details.jsp
				{
					System.out.println("case 4");
					l_ers=PurchaseorderBusiness.isPOExists(con, req.getParameter("ADDPONO"));
					l_ers=l_ers+PurchaseorderBusiness.get_code_list(con, majorUnitsList, 3);
					if(krrcommon.isValuenull(l_ers))
					{
						if(krrcommon.isValue(req.getParameter("POITEMCODE")))
						{
							ITEMDETAILS.setITEMCODE(krrcommon.ConvertNum(req.getParameter("POITEMCODE")));
							l_ers=l_ers+PurchaseorderBusiness.get_Item_details(con, ITEMDETAILS);
							l_ers=l_ers+PurchaseorderBusiness.get_code_list(con, majorUnitsList, 3);
							PO_DETAILS.setPOITEMCODE(krrcommon.ConvertNum(req.getParameter("POITEMCODE"))); 
							PO_DETAILS.setPOITEMDESCRIPTION(ITEMDETAILS.getITEMDESCRIPTION());
							PO_DETAILS.setUOM(req.getParameter("UOM"));
							PO_DETAILS.setCATALOGNO(req.getParameter("CATALOGNO"));
							PO_DETAILS.setMNFGCODE(req.getParameter("MNFGCODE"));
							PO_DETAILS.setPOITEMUNITCOST(krrcommon.ConvertDouble(req.getParameter("POITEMUNITCOST")));
							PO_DETAILS.setQTYORDER(krrcommon.ConvertDouble(req.getParameter("QTYORDER")));
							PO_DETAILS.setPOITEMBONUS(krrcommon.ConvertNum(req.getParameter("POITEMBONUS")));
							PO_DETAILS.setEXPDELIVERYDT(req.getParameter("EXPDELIVERYDT"));
							PO_DETAILS.setORDQTYTOTCOST(krrcommon.ConvertDouble(req.getParameter("ORDQTYTOTCOST")));
							PO_DETAILS.setPODETAILSSRNO(krrcommon.ConvertNum(req.getParameter("POITEMSRNO")));
						}
						req.setAttribute("UOMLIST",majorUnitsList);
						req.setAttribute("po_details", PO_DETAILS);
						req.setAttribute("message", " ");
						req.setAttribute("ADDPONO", req.getParameter("ADDPONO"));
						req.setAttribute("po_commondata", PO_CommonData);
						getServletContext().getRequestDispatcher("/addPO_Details.jsp").forward(req, resp);
					}
					else
					{
						l_ers=l_ers+PurchaseorderBusiness.get_supplier_name_SUPPLIER_DETAILS(con, vendor_details_list);
						req.setAttribute("suppList",vendor_details_list);
						req.setAttribute("po_header", PO_HEADER);
						req.setAttribute("message", l_ers);
						req.setAttribute("po_commondata", PO_CommonData);
						getServletContext().getRequestDispatcher("/addPO.jsp").forward(req, resp);
					}
				}
				break;
				case 5://Save PO_Item details
				{
					System.out.println("case 5");
					PO_DETAILS.setPONUMBER(req.getParameter("ADDPONO"));
					PO_DETAILS.setPOITEMCODE(krrcommon.ConvertNum(req.getParameter("POITEMCODE"))); 
					PO_DETAILS.setPOITEMDESCRIPTION(req.getParameter("POITEMDESCRIPTION"));
					PO_DETAILS.setUOM(req.getParameter("UOM"));
					PO_DETAILS.setCATALOGNO(req.getParameter("CATALOGNO"));
					PO_DETAILS.setMNFGCODE(req.getParameter("MNFGCODE"));
					PO_DETAILS.setPOITEMUNITCOST(krrcommon.ConvertDouble(req.getParameter("POITEMUNITCOST")));
					PO_DETAILS.setQTYORDER(krrcommon.ConvertDouble(req.getParameter("QTYORDER")));
					PO_DETAILS.setPOITEMBONUS(krrcommon.ConvertNum(req.getParameter("POITEMBONUS")));
					PO_DETAILS.setEXPDELIVERYDT(req.getParameter("EXPDELIVERYDT"));
					PO_DETAILS.setORDQTYTOTCOST(krrcommon.ConvertDouble(req.getParameter("ORDQTYTOTCOST")));
					PO_DETAILS.setPODETAILSSRNO(krrcommon.ConvertNum(req.getParameter("POITEMSRNO")));

					l_ers=PurchaseorderBusiness.insert_update_PODETAILS(con, PO_DETAILS, PO_CommonData);
					l_ers=l_ers+PurchaseorderBusiness.getPODETAILSLIST(con, PO_DETAILSList,req.getParameter("ADDPONO"));
					PO_HEADER.setPONO(req.getParameter("ADDPONO"));
					l_ers=l_ers+PurchaseorderBusiness.get_POHEADER_display(con, PO_HEADER);
					l_ers=l_ers+PurchaseorderBusiness.get_code_list(con, majorUnitsList, 3);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("POITEMLIST", PO_DETAILSList);
						req.setAttribute("po_header", PO_HEADER);
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("message", "Record saved successfully");
						getServletContext().getRequestDispatcher("/poItemGrid.jsp").forward(req, resp);
					}
					else
					{
						req.setAttribute("UOMLIST",majorUnitsList);
						req.setAttribute("ADDPONO",req.getParameter("ADDPONO"));
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("po_details",PO_DETAILS);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/addPO_Details.jsp").forward(req, resp);
					}
				}
				break;
				case 6://Display addPO_TC.jsp
				{
					System.out.println("case 6");
					l_ers=ITEMSERVICE.get_code_list(con, CONDITIONLIST, 4);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("po_tc", PO_TC);
						req.setAttribute("message", "");
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("CONDITION", CONDITIONLIST);
						getServletContext().getRequestDispatcher("/addPotc.jsp").forward(req, resp);
					}
					else
					{
						l_ers=l_ers+PurchaseorderBusiness.get_supplier_name_SUPPLIER_DETAILS(con, vendor_details_list);
						req.setAttribute("suppList",vendor_details_list);
						req.setAttribute("po_header", PO_HEADER);
						req.setAttribute("message", l_ers);
						req.setAttribute("po_commondata", PO_CommonData);
						getServletContext().getRequestDispatcher("/addPO.jsp").forward(req, resp);
					}
				}
				break;

				case 7://Save PO_TC details
				{
					System.out.println("case 7");
					PO_TC.setPONO(req.getParameter("ADDPONO"));        
					PO_TC.setCONDITION(req.getParameter("CONDITION"));   
					PO_TC.setDESCRIPTION(req.getParameter("DESCRIPTION"));
					PO_TC.setPOTCSRNO(krrcommon.ConvertNum(req.getParameter("POTCSRNO")));
					l_ers=PurchaseorderBusiness.insert_update_POTC(con, PO_TC,PO_CommonData);
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=l_ers+PurchaseorderBusiness.getPOTCLIST(con, PO_TCList,req.getParameter("ADDPONO") );
						PO_HEADER.setPONO(req.getParameter("ADDPONO"));
						l_ers=l_ers+PurchaseorderBusiness.get_POHEADER_display(con, PO_HEADER);
						req.setAttribute("POTCLIST", PO_TCList);
						req.setAttribute("message", "Record saved successfully");
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("po_header", PO_HEADER);
						getServletContext().getRequestDispatcher("/poTerms&ConditionsGrid.jsp").forward(req, resp);
					}
					else
					{
						l_ers=l_ers+ITEMSERVICE.get_code_list(con, CONDITIONLIST, 4);
						req.setAttribute("po_tc", PO_TC);
						req.setAttribute("message",l_ers);
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("POTCLIST", PO_TCList);
						req.setAttribute("CONDITION", CONDITIONLIST);
						getServletContext().getRequestDispatcher("/addPotc.jsp").forward(req, resp);
					}
				}
				break;
				case 8://authorize PO
				{
					System.out.println("case 8");	
					l_ers=PurchaseorderBusiness.authorize_POHEADER(con, krrcommon.ConvertNum(POSRNO),PO_CommonData);
					l_ers=l_ers+PurchaseorderBusiness.get_POHEADER(con, PO_HEADERList, PO_CommonData);
					l_ers=l_ers+PurchaseorderBusiness.getSupplierName_POHEADER(con, SupplierList);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("message", "Record authorized successfully");
					}
					else
					{
						req.setAttribute("message",l_ers);
					}
					req.setAttribute("poheaderList",PO_HEADERList);
					req.setAttribute("supplierlist",SupplierList);
					req.setAttribute("po_commondata", PO_CommonData);
					getServletContext().getRequestDispatcher("/purchaseHeaderGrid.jsp").forward(req, resp);
				}
				break;

				case 9://delete PO
				{
					System.out.println("case 9");
					l_ers=PurchaseorderBusiness.delete_POHEADER(con,krrcommon.ConvertNum(POSRNO),PO_CommonData);
					l_ers=l_ers+PurchaseorderBusiness.get_POHEADER(con, PO_HEADERList, PO_CommonData);
					l_ers=l_ers+PurchaseorderBusiness.getSupplierName_POHEADER(con, SupplierList);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("message", "Record deleted successfully");
					}
					else
					{
						req.setAttribute("message",l_ers);
					}
					req.setAttribute("poheaderList",PO_HEADERList);
					req.setAttribute("supplierlist",SupplierList);
					req.setAttribute("po_commondata", PO_CommonData);
					getServletContext().getRequestDispatcher("/purchaseHeaderGrid.jsp").forward(req, resp);
				}
				break;
				case 10://PO Header details fill
				{
					System.out.println("case 10");
					l_ers=PurchaseorderBusiness.getALLPOSupplier_VENDORSUPPLIER(con, VENDOR_DETAILS, req.getParameter("supplier_Code"));
					l_ers=l_ers+PurchaseorderBusiness.get_supplier_name_SUPPLIER_DETAILS(con, vendor_details_list);
					if(krrcommon.isValuenull(l_ers))
					{
						PO_HEADER.setPONO(req.getParameter("ADD_PONO"));
						PO_HEADER.setPODATE(req.getParameter("PODATE"));                 
						PO_HEADER.setPOSUPPLIERNAME(VENDOR_DETAILS.getNAME());
						PO_HEADER.setPOSUPPLIERCODE(krrcommon.ConvertNum(req.getParameter("supplier_Code")));
						PO_HEADER.setPOSUPPLIERADDRESSLINE1(req.getParameter("POSUPPLIERADDRESSLINE1")); 
						PO_HEADER.setPOSUPPLIERADDRESSLINE2(req.getParameter("POSUPPLIERADDRESSLINE2")); 
						PO_HEADER.setPOSUPPLIERADDRESSLINE3(req.getParameter("POSUPPLIERADDRESSLINE3")); 
						PO_HEADER.setPOSUPPLIERADDRESSLINE4(req.getParameter("POSUPPLIERADDRESSLINE4")); 
						PO_HEADER.setPOSUPPLIERCITY(req.getParameter("POSUPPLIERCITY"));        
						PO_HEADER.setPOSUPPLIERDISTRICT(req.getParameter("POSUPPLIERDISTRICT"));     
						PO_HEADER.setPOSUPPLIERSTATE(req.getParameter("POSUPPLIERSTATE"));        
						PO_HEADER.setPOSUPPLIERCOUNTRY(req.getParameter("POSUPPLIERCOUNTRY"));      
						PO_HEADER.setPOSUPPLIERPIN(krrcommon.ConvertNum(req.getParameter("POSUPPLIERPIN")));         
						PO_HEADER.setPOSUPPLIERCONTACTPERSON(req.getParameter("POSUPPLIERCONTACTPERSON"));
						PO_HEADER.setPOSUPPLIERCONTACTNO(krrcommon.ConvertNum(req.getParameter("POSUPPLIERCONTACTNO")));    
						PO_HEADER.setPOSUBJECT(req.getParameter("POSUBJECT"));    
						PO_HEADER.setPOVATRATE(krrcommon.ConvertDouble(req.getParameter("POVATRATE")));             
						PO_HEADER.setPOCSTRATE(krrcommon.ConvertDouble(req.getParameter("POCSTRATE"))) ;             
						PO_HEADER.setPOEXCISEDUTYRATE(krrcommon.ConvertDouble(req.getParameter("POEXCISEDUTYRATE")));       
						PO_HEADER.setPOLABOURCHARGES(krrcommon.ConvertDouble(req.getParameter("POLABOURCHARGES")));       
						PO_HEADER.setPOFREIGHTAMT(krrcommon.ConvertDouble(req.getParameter("POFREIGHTAMT")));      
						PO_HEADER.setPOSERVICETAX(krrcommon.ConvertDouble(req.getParameter("POSERVICETAX")));
						PO_HEADER.setQUOTATIONNO(krrcommon.ConvertNum(req.getParameter("QUOTATIONNO")));
						PO_HEADER.setQUOTATIONDATE(req.getParameter("QUOTATIONDATE")); 
						PO_HEADER.setDOCREFNO(req.getParameter("DOCREFNO")); 	
						PO_HEADER.setDOCREFDATE(req.getParameter("DOCREFDATE")); 
						PO_HEADER.setACKNOWNO(req.getParameter("ACKNOWNO")); 	
						PO_HEADER.setACKNOWDATE(req.getParameter("ACKNOWDATE")); 
						PO_HEADER.setPAYADVNO(krrcommon.ConvertNum(req.getParameter("PAYADVNO")));
						PO_HEADER.setPAYADVDATE(req.getParameter("PAYADVDATE")); 
						PO_HEADER.setCHEQUENO(krrcommon.ConvertNum(req.getParameter("CHEQUENO")));
						PO_HEADER.setCHEQUEDATE(req.getParameter("CHEQUEDATE")); 
						PO_HEADER.setBANKCODE(req.getParameter("BANKCODE")); 
						PO_HEADER.setBANKNAME(req.getParameter("BANKNAME")); 
						PO_HEADER.setADVAMOUNT(krrcommon.ConvertDouble(req.getParameter("ADVAMOUNT")));       
						PO_HEADER.setBALAMOUNT(krrcommon.ConvertDouble(req.getParameter("BALAMOUNT")));    
						PO_HEADER.setPOREMARKS(req.getParameter("PO_REMARKS"));           
						req.setAttribute("po_header", PO_HEADER);
						PO_HEADER.setPOSUPPLIERADDRESSLINE1(VENDOR_DETAILS.getADDRESSLINE1());
						PO_HEADER.setPOSUPPLIERADDRESSLINE2(VENDOR_DETAILS.getADDRESSLINE2());
						PO_HEADER.setPOSUPPLIERADDRESSLINE3(VENDOR_DETAILS.getADDRESSLINE3());
						PO_HEADER.setPOSUPPLIERADDRESSLINE4(VENDOR_DETAILS.getADDRESSLINE4());
						PO_HEADER.setPOSUPPLIERCITY(VENDOR_DETAILS.getCITY());
						PO_HEADER.setPOSUPPLIERDISTRICT(VENDOR_DETAILS.getDISTRICT());
						PO_HEADER.setPOSUPPLIERSTATE(VENDOR_DETAILS.getSTATE());
						PO_HEADER.setPOSUPPLIERCOUNTRY(VENDOR_DETAILS.getCOUNTRY());
						PO_HEADER.setPOSUPPLIERPIN(VENDOR_DETAILS.getPIN());
						req.setAttribute("suppList",vendor_details_list);
						req.setAttribute("POITEMLIST", new ArrayList<PO_DETAILS>());
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("message", "");
						req.setAttribute("POTCLIST", PO_TCList);
						getServletContext().getRequestDispatcher("/addPO.jsp").forward(req, resp);
					}
					else
					{
						l_ers=l_ers+PurchaseorderBusiness.get_POHEADER_details(con, PO_HEADER);
						req.setAttribute("suppList",vendor_details_list);
						req.setAttribute("po_header", PO_HEADER);
						req.setAttribute("message", " ");
						req.setAttribute("po_commondata", PO_CommonData);
						getServletContext().getRequestDispatcher("/addPO.jsp").forward(req, resp);
					}
				}
				break;
				case 11://Complete PO
				{
					System.out.println("case 11");
					l_ers=PurchaseorderBusiness.complete_POHEADER(con, krrcommon.ConvertNum(POSRNO),PO_CommonData);
					l_ers=l_ers+PurchaseorderBusiness.get_POHEADER(con, PO_HEADERList, PO_CommonData);
					l_ers=l_ers+PurchaseorderBusiness.getSupplierName_POHEADER(con, SupplierList);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("message", "Record Completed successfully");
					}
					else
					{
						req.setAttribute("message",l_ers);
					}
					req.setAttribute("poheaderList",PO_HEADERList);
					req.setAttribute("supplierlist",SupplierList);
					req.setAttribute("po_commondata", PO_CommonData);
					getServletContext().getRequestDispatcher("/purchaseHeaderGrid.jsp").forward(req, resp);
					break;
				}
				case 12://edit PO_HEADER details
				{
					System.out.println("case 12");
					l_ers=PurchaseorderBusiness.get_POHEADER_details(con,PO_HEADER);
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=l_ers+PurchaseorderBusiness.get_supplier_name_SUPPLIER_DETAILS(con, vendor_details_list);
						req.setAttribute("suppList",vendor_details_list);
						req.setAttribute("po_header", PO_HEADER);
						req.setAttribute("message", " ");
						req.setAttribute("ADDPONO", req.getParameter("ADDPONO"));
						req.setAttribute("po_commondata", PO_CommonData);
						getServletContext().getRequestDispatcher("/addPO.jsp").forward(req, resp);
					}
					else
					{
						l_ers=l_ers+PurchaseorderBusiness.get_POHEADER(con,PO_HEADERList,PO_CommonData);
						l_ers=l_ers+PurchaseorderBusiness.getSupplierName_POHEADER(con, SupplierList);
						req.setAttribute("poheaderList",PO_HEADERList);
						req.setAttribute("supplierlist",SupplierList);
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/purchaseHeaderGrid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 13://edit PO_Item_Details
				{
					System.out.println("case 13");
					PO_DETAILS.setPODETAILSSRNO(krrcommon.ConvertNum(req.getParameter("POITEMSRNO")));
					l_ers=PurchaseorderBusiness.get_PODETAILS(con, PO_DETAILS);
					l_ers=l_ers+PurchaseorderBusiness.get_code_list(con, majorUnitsList, 3);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("po_details", PO_DETAILS);
						req.setAttribute("message", "");
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("UOMLIST",majorUnitsList);
						req.setAttribute("ADDPONO",req.getParameter("ADDPONO"));
						getServletContext().getRequestDispatcher("/addPO_Details.jsp").forward(req, resp);
					}
					else
					{
						l_ers=l_ers+PurchaseorderBusiness.getPODETAILSLIST(con, PO_DETAILSList,req.getParameter("ADDPONO"));
						l_ers=l_ers+PurchaseorderBusiness.get_POHEADER_display(con, PO_HEADER);
						req.setAttribute("po_header", PO_HEADER);
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("POITEMLIST", PO_DETAILSList);	
						getServletContext().getRequestDispatcher("/poItemGrid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 14://edit po_tc details
				{
					System.out.println("case 14");
					PO_TC.setPOTCSRNO(krrcommon.ConvertNum(req.getParameter("POTCSRNO")));
					l_ers=PurchaseorderBusiness.get_POTC_details(con,PO_TC);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("po_tc", PO_TC);
						req.setAttribute("message", "");
						req.setAttribute("ADDPONO",req.getParameter("ADDPONO"));
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("CONDITION", CONDITIONLIST);
						getServletContext().getRequestDispatcher("/addPotc.jsp").forward(req, resp);
					}
					else
					{
						l_ers=l_ers+PurchaseorderBusiness.getPOTCLIST(con, PO_TCList,req.getParameter("ADDPONO"));
						l_ers=l_ers+PurchaseorderBusiness.get_POHEADER_display(con, PO_HEADER);
						req.setAttribute("POTCLIST", PO_TCList);
						req.setAttribute("message", "");
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("po_header", PO_HEADER);
						getServletContext().getRequestDispatcher("/poTerms&ConditionsGrid.jsp").forward(req, resp);
					}
				}
				break;
				case 15://delete PO_Item_details
				{
					System.out.println("case 15");
					PO_DETAILS.setPODETAILSSRNO(krrcommon.ConvertNum(req.getParameter("POITEMSRNO")));
					l_ers=PurchaseorderBusiness.delete_PODETAILS(con, PO_DETAILS);
					PO_HEADER.setPONO(req.getParameter("ADDPONO"));
					l_ers=l_ers+PurchaseorderBusiness.get_POHEADER_display(con, PO_HEADER);
					l_ers=l_ers+PurchaseorderBusiness.getPODETAILSLIST(con, PO_DETAILSList,req.getParameter("ADDPONO"));
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("po_header", PO_HEADER);
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("POITEMLIST", PO_DETAILSList);	
						req.setAttribute("message", "Record deleted successfully");
						getServletContext().getRequestDispatcher("/poItemGrid.jsp").forward( req , resp ) ;
					}
					else
					{
						req.setAttribute("po_header", PO_HEADER);
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("POITEMLIST", PO_DETAILSList);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/poItemGrid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 16://delete POTC details
				{
					System.out.println("in case 16");
					PO_TC.setPOTCSRNO(krrcommon.ConvertNum(req.getParameter("POTCSRNO")));
					l_ers=PurchaseorderBusiness.delete_POTC(con, PO_TC);
					l_ers=l_ers+PurchaseorderBusiness.getPOTCLIST(con, PO_TCList, req.getParameter("ADDPONO"));
					PO_HEADER.setPONO(req.getParameter("ADDPONO"));
					l_ers=l_ers+PurchaseorderBusiness.get_POHEADER_display(con, PO_HEADER);
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("message", "Record deleted successfully");
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("POTCLIST", PO_TCList);
						req.setAttribute("po_header", PO_HEADER);
						getServletContext().getRequestDispatcher("/poTerms&ConditionsGrid.jsp").forward(req, resp);
					}
					else
					{
						req.setAttribute("message", l_ers);
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("POTCLIST", PO_TCList);
						req.setAttribute("po_header", PO_HEADER);
						getServletContext().getRequestDispatcher("/poTerms&ConditionsGrid.jsp").forward(req, resp);
					}
				}
				break;
				case 17://generate PDF
				{
					System.out.println("case 17");
					String POSrNo=req.getParameter("POSRNO");
					PO_HEADER.setPOSRNO(krrcommon.ConvertNum(POSrNo));
					l_ers=PurchaseorderBusiness.get_POHEADER_PDF(con, PO_HEADER);
					l_ers=l_ers+PurchaseorderBusiness.getPODETAILSLIST(con, PO_DETAILSList,PO_HEADER.getPONO());
					l_ers=l_ers+PurchaseorderBusiness.getPOTCLIST(con, PO_TCList,PO_HEADER.getPONO());
					l_ers=l_ers+PurchaseorderBusiness.get_POHEADER(con,PO_HEADERList,PO_CommonData);
					l_ers=l_ers+PurchaseorderBusiness.poTotalCostCalculation(con, PO_HEADER);
					ServletContext ctx=getServletContext();
					if (krrcommon.isValuenull(l_ers))
					{
						resp.addHeader("Content-Type", "application/pdf");
						resp.setHeader("Content-Disposition", "attachment; filename="+PO_HEADER.getPONO()+".PDF");
						ServletOutputStream outputStream = resp.getOutputStream();
						PurchaseorderBusiness.genpoAckPdf(outputStream,PO_HEADER,PO_DETAILSList,PO_TCList,ctx);
						req.setAttribute("message", "PDF generated Successfully");
					}
					else
					{
						l_ers=l_ers+PurchaseorderBusiness.getSupplierName_POHEADER(con, SupplierList);
						l_ers=l_ers+PurchaseorderBusiness.get_POHEADER(con,PO_HEADERList,PO_CommonData);
						req.setAttribute("poheaderList",PO_HEADERList);
						req.setAttribute("supplierlist",SupplierList);
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("message", l_ers);
						getServletContext().getRequestDispatcher("/purchaseHeaderGrid.jsp").forward( req , resp ) ;
					}
				}
				break;
				case 18://display PO_ITEM_DETAILS Grid page
				{
					System.out.println("case 18");
					l_ers=PurchaseorderBusiness.getPODETAILSLIST(con, PO_DETAILSList,req.getParameter("ADDPONO"));
					PO_HEADER.setPONO(req.getParameter("ADDPONO"));
					l_ers=l_ers+PurchaseorderBusiness.get_POHEADER_display(con, PO_HEADER);
					l_ers=l_ers+PurchaseorderBusiness.isPOExists(con, req.getParameter("ADDPONO"));
					if(krrcommon.isValuenull(l_ers))
					{
						req.setAttribute("po_header", PO_HEADER);
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("POITEMLIST", PO_DETAILSList);	
						getServletContext().getRequestDispatcher("/poItemGrid.jsp").forward( req , resp ) ;
					}
					else
					{
						l_ers=l_ers+PurchaseorderBusiness.get_supplier_name_SUPPLIER_DETAILS(con, vendor_details_list);
						req.setAttribute("suppList",vendor_details_list);
						req.setAttribute("po_header", new PO_HEADER());
						req.setAttribute("message", l_ers);
						req.setAttribute("po_commondata", PO_CommonData);
						getServletContext().getRequestDispatcher("/addPO.jsp").forward(req, resp);
					}
				}
				break;
				case 19:// display PO_TC grid page
				{
					System.out.println("case 19");
					l_ers=PurchaseorderBusiness.get_supplier_name_SUPPLIER_DETAILS(con, vendor_details_list);
					l_ers=l_ers+ITEMSERVICE.get_code_list(con, CONDITIONLIST, 4);
					l_ers=l_ers+PurchaseorderBusiness.isPOExists(con, req.getParameter("ADDPONO"));
					PO_HEADER.setPONO(req.getParameter("ADDPONO"));
					l_ers=l_ers+PurchaseorderBusiness.get_POHEADER_display(con, PO_HEADER);
					if(krrcommon.isValuenull(l_ers))
					{
						l_ers=l_ers+PurchaseorderBusiness.getPOTCLIST(con, PO_TCList,req.getParameter("ADDPONO") );
						req.setAttribute("POTCLIST", PO_TCList);
						req.setAttribute("message", "");
						req.setAttribute("po_commondata", PO_CommonData);
						req.setAttribute("po_header", PO_HEADER);
						getServletContext().getRequestDispatcher("/poTerms&ConditionsGrid.jsp").forward(req, resp);
					}
					else
					{
						req.setAttribute("suppList",vendor_details_list);
						req.setAttribute("po_header", PO_HEADER);
						req.setAttribute("POITEMLIST", new ArrayList<PO_DETAILS>());
						req.setAttribute("message", l_ers);
						req.setAttribute("po_commondata", PO_CommonData);
						getServletContext().getRequestDispatcher("/addPO.jsp").forward(req, resp);
					}
				}
				break;
				case 20://get item details
				{
					System.out.println("case 20");
					ITEMDETAILS.setITEMCODE(krrcommon.ConvertNum(req.getParameter("POITEMCODE")));
					l_ers=PurchaseorderBusiness.get_code_list(con, majorUnitsList, 3);
					if(krrcommon.isValuenull(l_ers) && krrcommon.isValue(req.getParameter("POITEMCODE")))
					{
						l_ers=PurchaseorderBusiness.get_Item_details(con, ITEMDETAILS);
						if(krrcommon.isValuenull(l_ers))
						{
							PO_DETAILS.setPOITEMCODE(krrcommon.ConvertNum(req.getParameter("POITEMCODE"))); 
							PO_DETAILS.setPOITEMDESCRIPTION(ITEMDETAILS.getITEMDESCRIPTION());
							PO_DETAILS.setUOM(req.getParameter("UOM"));
							PO_DETAILS.setCATALOGNO(req.getParameter("CATALOGNO"));
							PO_DETAILS.setMNFGCODE(req.getParameter("MNFGCODE"));
							PO_DETAILS.setPOITEMUNITCOST(krrcommon.ConvertDouble(req.getParameter("POITEMUNITCOST")));
							PO_DETAILS.setQTYORDER(krrcommon.ConvertDouble(req.getParameter("QTYORDER")));
							PO_DETAILS.setPOITEMBONUS(krrcommon.ConvertNum(req.getParameter("POITEMBONUS")));
							PO_DETAILS.setEXPDELIVERYDT(req.getParameter("EXPDELIVERYDT"));
							PO_DETAILS.setORDQTYTOTCOST(krrcommon.ConvertDouble(req.getParameter("ORDQTYTOTCOST")));
							PO_DETAILS.setPODETAILSSRNO(krrcommon.ConvertNum(req.getParameter("POITEMSRNO")));
							req.setAttribute("message", " ");
						}
						else
						{
							req.setAttribute("message", l_ers);
						}
						req.setAttribute("UOMLIST",majorUnitsList);
						req.setAttribute("po_details", PO_DETAILS);
						req.setAttribute("ADDPONO", req.getParameter("ADDPONO"));
						req.setAttribute("po_commondata", PO_CommonData);
						getServletContext().getRequestDispatcher("/addPO_Details.jsp").forward(req, resp);
					}
					else
					{
						l_ers=l_ers+"Enter Item Code to get Item Details";
						req.setAttribute("message", l_ers);
						req.setAttribute("UOMLIST",majorUnitsList);
						req.setAttribute("po_details", PO_DETAILS);
						req.setAttribute("ADDPONO", req.getParameter("ADDPONO"));
						req.setAttribute("po_commondata", PO_CommonData);
						getServletContext().getRequestDispatcher("/addPO_Details.jsp").forward(req, resp);
					}
				}
				break;
				default:
					break;
				}	
			}
			if(errors.getErrors().size()>0)
			{
				req.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/Errors.jsp").forward(req, resp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try
			{
				l_ers="error in purchase order controller";
				req.setAttribute("po_headerlist",PO_HEADERList);
				req.setAttribute("po_commondata", PO_CommonData);
				req.setAttribute("message", l_ers);
				getServletContext().getRequestDispatcher("/purchaseHeaderGrid.jsp").forward(req,resp) ;
			}
			catch(java.lang.Exception e1)
			{
				e.printStackTrace();
			}
		}
		finally
		{
			try
			{
				if (!(con==null )) con.close();
			}
			catch ( java.lang.Exception  e )
			{
				e.printStackTrace();
			}
		}
	}
}
