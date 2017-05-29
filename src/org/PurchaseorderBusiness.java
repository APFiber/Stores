package org;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import bean.MAJOR_MINOR_CODE;
import bean.PO_CommonData;
import bean.PO_DETAILS;
import bean.PO_HEADER;
import bean.PO_TC;
import bean.VENDOR_SUPPLIER;

public class PurchaseorderBusiness {
	private static final String BODY_FONT = null;
	org.PO_DB PODB = new PO_DB();
	Date_library Date_library=new Date_library();
	ITEM_DB ITEMDB=new ITEM_DB();
	MAJOR_MINOR_DB MAJORMINORDB=new MAJOR_MINOR_DB();
	org.KrrCommon Krrcommon=new KrrCommon();
	public  String get_POHEADER(Connection con,List<PO_HEADER> PO_HEADERBeanlist, bean.PO_CommonData CommomData){

		String l_ers="";
		try{
			l_ers=PODB.get_PO_HEADER(con,PO_HEADERBeanlist, CommomData);
			CommomData.setTotal_NoOf_Records(PO_HEADERBeanlist.size());
			int excessValues = PO_HEADERBeanlist.size()%10;
			if(excessValues!=0 || PO_HEADERBeanlist.size()==0){
				while(excessValues <= 10){
					PO_HEADERBeanlist.add(new PO_HEADER());
					excessValues++;
				}
			}
			//Total No of pages
			CommomData.setTotal_NoOf_Pages( PO_HEADERBeanlist.size()/10);
		}
		catch (Exception e) {
			e.printStackTrace();
			l_ers = "error in PO search results"+e.getMessage();
		}
		return l_ers;
	}

//	supplier list in search
	public String getSupplierName_POHEADER(Connection con,List<PO_HEADER> POHEADERLIST)
	{
		String l_ers="";
		try
		{
			l_ers=PODB.get_suppliername_PO_HEADER(con, POHEADERLIST);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}


	public String get_supplier_name_SUPPLIER_DETAILS(Connection con,List<VENDOR_SUPPLIER> VENDORDETAILSLIST)
	{
		String l_ers="";
		try
		{
			l_ers=PODB.get_Supp_name_VENDOR_SUPPLIER(con, VENDORDETAILSLIST);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}

	public String getALLPOSupplier_VENDORSUPPLIER(Connection con,VENDOR_SUPPLIER VENDORDETAILS,String code)
	{
		String l_ers="";
		try
		{
			l_ers=PODB.get_supp_details_VENDOR_SUPPLIER(con, VENDORDETAILS, code);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return l_ers;
	}
//	Authorize
	public String authorize_POHEADER(Connection con,long poSrNo,PO_CommonData PO_CommonData)
	{
		String l_ers="";
		String ITEMSTATUS=PODB.get_PO_status(con, poSrNo);
		if(ITEMSTATUS.equalsIgnoreCase("COMPLETED"))
		{
			try 
			{	
				l_ers=PODB.change_po_status(con, poSrNo, "AUTHORIZED",PO_CommonData);
			} catch (Exception e) {
				l_ers="error in authorize PO details "+e.getMessage();
				e.printStackTrace();
			}
		}
		else
		{
			l_ers="Only completed records can be authorized";
		}

		return l_ers;
	}

//	Complete
	public String complete_POHEADER(Connection con,long poSrNo,PO_CommonData PO_CommonData)
	{
		String l_ers="";
		PO_HEADER POHEADER=new PO_HEADER();
		POHEADER.setPOSRNO(poSrNo);
		l_ers=PODB.getPOHEADEROnPOSrNo(con, POHEADER);
		String ITEMSTATUS=PODB.get_PO_status(con, poSrNo);
		if(ITEMSTATUS.equalsIgnoreCase("COMPLETED"))
		{
			l_ers="P.O is already in completed";
		}
		else {
			if(ITEMSTATUS.equalsIgnoreCase("ENTRY"))
			{
				try 
				{
					if(PODB.is_PO_ITEMexits(con, POHEADER.getPONO())==false)
					{
						l_ers="Add P.O Items to complete purchase order";
					}

					else
					{
						if(PODB.is_PO_TCexits(con, POHEADER.getPONO())==false)
						{
							l_ers=l_ers+"Add P.O Terms to complete purchase order";
						}
						else
						{
							l_ers=PODB.change_po_status(con, poSrNo, "COMPLETED",PO_CommonData);
						}
					}
				} 
				catch (Exception e) 
				{
					l_ers="error in completing P.O details "+e.getMessage();
					e.printStackTrace();
				}
			}
			else
			{
				l_ers="P.O items and T.C should exists to complete P.O";
			}
		}
		return l_ers;
	}

//	Delete 
	public String delete_POHEADER(Connection con,long poSrNo,PO_CommonData PO_CommonData)
	{
		String l_ers="";
		String ITEMSTATUS=PODB.get_PO_status(con, poSrNo);
		if(!ITEMSTATUS.equalsIgnoreCase("DELETED"))
		{
			try 
			{	
				l_ers=PODB.change_po_status(con, poSrNo, "DELETED",PO_CommonData);
			} catch (Exception e) {
				l_ers="error in deleting PO details "+e.getMessage();
				e.printStackTrace();
			}
		}
		else
		{
			l_ers="Selected P.O i s already in deleted status";
		}

		return l_ers;
	}

	public String insert_update_POHEADER(Connection con,PO_HEADER POHEADER,PO_CommonData poCommonData)
	{
		String l_ers="";
		try {
			if(POHEADER.getPOSRNO()!=0)
			{
				POHEADER.setPODATE(Date_library.date_Conv(POHEADER.getPODATE(), "dd-MM-yyyy", "yyyyMMdd"));
				POHEADER.setQUOTATIONDATE(Date_library.date_Conv(POHEADER.getQUOTATIONDATE(), "dd-MM-yyyy", "yyyyMMdd"));
				POHEADER.setDOCREFDATE(Date_library.date_Conv(POHEADER.getDOCREFDATE(), "dd-MM-yyyy", "yyyyMMdd"));
				POHEADER.setACKNOWDATE(Date_library.date_Conv(POHEADER.getACKNOWDATE(), "dd-MM-yyyy", "yyyyMMdd"));
				POHEADER.setPAYADVDATE(Date_library.date_Conv(POHEADER.getPAYADVDATE(), "dd-MM-yyyy", "yyyyMMdd"));
				POHEADER.setCHEQUEDATE(Date_library.date_Conv(POHEADER.getCHEQUEDATE(), "dd-MM-yyyy", "yyyyMMdd"));
				POHEADER.setPOEDITEDBY(poCommonData.getLOGIN_ID());
				POHEADER.setPOEDITEDDATE(Date_library.display_Current_Date("yyyyMMdd"));
				l_ers=PODB.update_PO_HEADER(con,POHEADER); 
			}
			else
			{
				if(PODB.is_PO_exits(con,POHEADER.getPONO()))
				{
					l_ers="PO number already exists";	
				}
				else
				{
					POHEADER.setPODATE(Date_library.date_Conv(POHEADER.getPODATE(),"dd-MM-yyyy", "yyyyMMdd"));
					POHEADER.setQUOTATIONDATE(Date_library.date_Conv(POHEADER.getQUOTATIONDATE(), "dd-MM-yyyy", "yyyyMMdd"));
					POHEADER.setDOCREFDATE(Date_library.date_Conv(POHEADER.getDOCREFDATE(), "dd-MM-yyyy", "yyyyMMdd"));
					POHEADER.setACKNOWDATE(Date_library.date_Conv(POHEADER.getACKNOWDATE(), "dd-MM-yyyy", "yyyyMMdd"));
					POHEADER.setPAYADVDATE(Date_library.date_Conv(POHEADER.getPAYADVDATE(), "dd-MM-yyyy", "yyyyMMdd"));
					POHEADER.setCHEQUEDATE(Date_library.date_Conv(POHEADER.getCHEQUEDATE(), "dd-MM-yyyy", "yyyyMMdd"));
					POHEADER.setCREATEDBY(poCommonData.getLOGIN_ID());
					POHEADER.setCREATEDDATE(Date_library.display_Current_Date("yyyyMMdd"));
					POHEADER.setPOSTATUS("ENTRY");
					l_ers=PODB.insert_PO_HEADER(con, POHEADER);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String isPOExists(Connection con,String PONO)
	{
		String l_ers="";
		try{
			if(PODB.is_PO_exits(con, PONO)==false)
			{
				l_ers="PO number should be exist to view PO Item details and Terms and conditions ";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}
	public String insert_update_PODETAILS(Connection con,PO_DETAILS PODETAILS,PO_CommonData poCommonData)
	{
		String l_ers="";
		try 
		{
			if(PODETAILS.getPODETAILSSRNO()!=0)
			{
				PODETAILS.setEXPDELIVERYDT(Date_library.date_Conv(PODETAILS.getEXPDELIVERYDT(), "dd-MM-yyyy", "yyyyMMdd"));
				l_ers=PODB.update_PO_DETAILS(con, PODETAILS);
			}
			else
			{
				PODETAILS.setEXPDELIVERYDT(Date_library.date_Conv(PODETAILS.getEXPDELIVERYDT(), "dd-MM-yyyy", "yyyyMMdd"));
				PODETAILS.setPOITEMCREATEDBY(poCommonData.getLOGIN_ID());
				PODETAILS.setPOITEMCREATEDDATE(Date_library.display_Current_Date("yyyyMMdd"));
				l_ers=PODB.insert_PO_DETAILS(con, PODETAILS);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String insert_update_POTC(Connection con,PO_TC POTC,PO_CommonData poCommonData)
	{
		String l_ers="";
		try 
		{
			if(POTC.getPOTCSRNO()!=0)
			{
				l_ers=PODB.update_PO_TC(con, POTC);
			}
			else
			{
				if(PODB.is_POTCCondition_exist(con, POTC))
				{
					l_ers="Condition already exists";	
				}
				else
				{
					l_ers=PODB.insertPO_TC(con, POTC);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String getPODETAILSLIST(Connection con,List<PO_DETAILS> PO_DETAILSLIST,String PONO)
	{
		String l_ers="";
		try
		{
			l_ers=PODB.get_PO_DETAILS_list(con,PONO, PO_DETAILSLIST);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String getPOTCLIST(Connection con,List<PO_TC> POTCLIST,String PONO)
	{
		String l_ers="";
		try
		{
			l_ers=PODB.getPO_TC(con, PONO, POTCLIST);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String get_POHEADER_details(Connection con,PO_HEADER POHEADER)
	{
		String l_ers="";
		String POSTATUS=PODB.get_PO_status(con,POHEADER.getPOSRNO());
		try{
			if(POSTATUS.equalsIgnoreCase("ENTRY"))
			{
				l_ers=PODB.getPOHEADEROnPOSrNo(con,POHEADER);
				l_ers = this.poTotalCostCalculation(con, POHEADER);
				POHEADER.setPODATE(Date_library.date_Conv(POHEADER.getPODATE(), "yyyyMMdd", "dd-MM-yyyy"));
				POHEADER.setQUOTATIONDATE(Date_library.date_Conv(POHEADER.getQUOTATIONDATE(), "yyyyMMdd", "dd-MM-yyyy"));
				POHEADER.setDOCREFDATE(Date_library.date_Conv(POHEADER.getDOCREFDATE(), "yyyyMMdd", "dd-MM-yyyy"));
				POHEADER.setACKNOWDATE(Date_library.date_Conv(POHEADER.getACKNOWDATE(), "yyyyMMdd", "dd-MM-yyyy"));
				POHEADER.setPAYADVDATE(Date_library.date_Conv(POHEADER.getPAYADVDATE(), "yyyyMMdd", "dd-MM-yyyy"));
				POHEADER.setCHEQUEDATE(Date_library.date_Conv(POHEADER.getCHEQUEDATE(), "yyyyMMdd", "dd-MM-yyyy"));
			}
			else
			{
				l_ers="only entry records can be edited";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String get_POHEADER_PDF(Connection con,PO_HEADER POHEADER)
	{
		String l_ers="";
		String POSTATUS=PODB.get_PO_status(con,POHEADER.getPOSRNO());
		try{
			if(POSTATUS.equalsIgnoreCase("COMPLETED"))
			{
				l_ers=PODB.getPOHEADEROnPOSrNo(con,POHEADER);
				POHEADER.setPODATE(Date_library.date_Conv(POHEADER.getPODATE(), "yyyyMMdd", "dd-MM-yyyy"));
			}
			else
			{
				l_ers="Only completed Purchase Order can generate PDF";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String get_POHEADER_display(Connection con,PO_HEADER POHEADER)
	{
		String l_ers="";
		try
		{
			l_ers=PODB.getPOHEADEROnPONO(con,POHEADER);
			POHEADER.setPODATE(Date_library.date_Conv(POHEADER.getPODATE(), "yyyyMMdd", "dd/MM/yyyy"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String get_POTC_details(Connection con,PO_TC po_tc)
	{
		String l_ers="";
		try
		{
			l_ers=PODB.getPOTCOnPOTCSrNo(con, po_tc);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String get_PODETAILS(Connection con,PO_DETAILS PODETAILS)
	{
		String l_ers="";
		try
		{
			l_ers=PODB.getPODETAILSOnPOITEMSRNO(con, PODETAILS);
			PODETAILS.setEXPDELIVERYDT(Date_library.date_Conv(PODETAILS.getEXPDELIVERYDT(), "yyyyMMdd", "dd-MM-yyyy"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String delete_PODETAILS(Connection con,PO_DETAILS PODETAILS)
	{
		String l_ers="";
		try
		{
			l_ers=PODB.deletePO_ITEM_DETAILS(con, PODETAILS);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String delete_POTC(Connection con,PO_TC POTC)
	{
		String l_ers="";
		try
		{
			l_ers=PODB.deletePO_TC_DETAILS(con, POTC);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String poTotalCostCalculation(Connection con, PO_HEADER POHEADER){
		String l_ers="";
		try
		{
			l_ers=PODB.getTotalPOItemCost(con, POHEADER);
			
			double totalPoItemCost = POHEADER.getTOTALPOCOST();
			
			double serviceTaxAmount=totalPoItemCost/100*POHEADER.getPOSERVICETAX();
 
			double exciseDutyAmount = totalPoItemCost/100*POHEADER.getPOEXCISEDUTYRATE();

			double vatAmount = totalPoItemCost/100*POHEADER.getPOVATRATE();
			
			double cstAmount = totalPoItemCost/100*POHEADER.getPOCSTRATE();

			POHEADER.setTOTALPOCOST(totalPoItemCost + serviceTaxAmount + exciseDutyAmount+ vatAmount+cstAmount+POHEADER.getPOLABOURCHARGES()+ POHEADER.getPOFREIGHTAMT());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public String get_Item_details(Connection con,bean.ITEM_DETAILS ITEMDETAILS)
	{
		String l_ers="";
		try
		{
			l_ers=ITEMDB.get_item_details_PO(con, ITEMDETAILS);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l_ers;
	}

	public void genpoAckPdf(OutputStream outputStream,PO_HEADER POHEADER,List<PO_DETAILS> PODETAILSLIST,List<PO_TC> POTCList,ServletContext ctx)
	{
		Document document=new Document();
		KrrCommon krrCommon=new KrrCommon();
		try
		{
			PdfWriter.getInstance(document, outputStream);
			document.open();
			System.out.println("opening the document..");
			PdfPTable table = new PdfPTable(1); 
			table.addCell(tableText("PURCHASE ORDER", 12, "BOLD", "ALIGN_CENTER", 2, "NO BOARDER"));
			table.addCell(tableText(" ", 12, "BOLD", "ALIGN_CENTER", 2, "NO BOARDER"));
			table.addCell(tableText("TO:    "+"                                                                           PO NO:"+POHEADER.getPONO(), 10, " ", "ALIGN_LEFT", 2, "NO BOARDER"));		
			table.addCell(tableText(" "+POHEADER.getPOSUPPLIERNAME()+","+"                                                                 			 PO Date : "+POHEADER.getPODATE(), 10, " ", "ALIGN_LEFT", 2, "NO BOARDER"));		
			table.addCell(tableText(" "+krrCommon.CheckEmptyReturn(POHEADER.getPOSUPPLIERADDRESSLINE1())+"," +krrCommon.CheckEmptyReturn(POHEADER.getPOSUPPLIERADDRESSLINE2())+",", 10, " ", "ALIGN_LEFT", 2, "NO BOARDER"));
			table.addCell(tableText(" "+krrCommon.CheckEmptyReturn(POHEADER.getPOSUPPLIERADDRESSLINE3())+"," +krrCommon.CheckEmptyReturn(POHEADER.getPOSUPPLIERADDRESSLINE4())+",", 10, " ", "ALIGN_LEFT", 2, "NO BOARDER"));
			table.addCell(tableText(" "+krrCommon.CheckEmptyReturn(POHEADER.getPOSUPPLIERCITY())+","+krrCommon.CheckEmptyReturn(POHEADER.getPOSUPPLIERDISTRICT())+",", 10, " ", "ALIGN_LEFT", 2, "NO BOARDER"));
			table.addCell(tableText(" "+krrCommon.CheckEmptyReturn(POHEADER.getPOSUPPLIERSTATE())+","+krrCommon.CheckEmptyReturn(POHEADER.getPOSUPPLIERCOUNTRY())+",", 10, " ", "ALIGN_LEFT", 2, "NO BOARDER"));
			table.addCell(tableText(" "+(POHEADER.getPOSUPPLIERPIN())+",", 10, " ", "ALIGN_LEFT", 2, "NO BOARDER"));
			table.addCell(tableText("TEL NO:"+(POHEADER.getPOSUPPLIERCONTACTNO()), 10, " ", "ALIGN_LEFT", 2, "NO BOARDER"));
			table.addCell(tableText(""+" ", 10, " ", "ALIGN_CENTER", 2, "NO BOARDER"));
			table.addCell(tableText("Subject: "+krrCommon.CheckEmptyReturn(POHEADER.getPOSUBJECT()), 10, " ", "ALIGN_LEFT", 2, "NO BOARDER"));
			table.addCell(tableText(""+" ", 10, " ", "ALIGN_CENTER", 2, "NO BOARDER"));
			table.addCell(tableText(""+"ITEM DETAILS", 10, "BOLD", "ALIGN_CENTER", 2, "NO BOARDER"));
			table.addCell(tableText(""+" ", 10, " ", "ALIGN_CENTER", 2, "NO BOARDER"));
			float widths[]={0.40f,0.15f,0.15f,0.15f,0.15f};
			PdfPTable data_table = new PdfPTable(widths);
			data_table.addCell(tableText("ITEM DESCRIPTION", 8, " ", "ALIGN_LEFT", 1, "BOARDER"));
			data_table.addCell(tableText("ORDERED", 8, " ", "ALIGN_LEFT", 1, "BOARDER"));
			data_table.addCell(tableText("RECEIVED", 8, " ", "ALIGN_LEFT", 1, "BOARDER"));
			data_table.addCell(tableText("UNIT COST", 8, " ", "ALIGN_LEFT", 1, "BOARDER"));
			data_table.addCell(tableText("TOTAL COST", 8, " ", "ALIGN_LEFT", 1, "BOARDER"));
			for(PO_DETAILS poItem :  PODETAILSLIST)
			{
				data_table.addCell(tableText(""+poItem.getPOITEMDESCRIPTION(),9, " ", "ALIGN_LEFT", 1, "BOARDER"));
				data_table.addCell(tableText(""+poItem.getQTYORDER(),9, " ", "ALIGN_LEFT", 1, "BOARDER"));
				data_table.addCell(tableText(""+poItem.getPOITEMQTYRECEIVED(),9, " ", "ALIGN_LEFT", 1, "BOARDER"));
				data_table.addCell(tableText(""+poItem.getPOITEMUNITCOST(),9, " ", "ALIGN_LEFT", 1, "BOARDER"));
				data_table.addCell(tableText(""+poItem.getORDQTYTOTCOST(),9, " ", "ALIGN_LEFT", 1, "BOARDER"));
			}
			data_table.addCell(tableText("Total PO Cost:", 10, " ", "ALIGN_RIGHT", 3, "NO BOARDER"));
			String TOTALPOCOST = String.format("%.2f", POHEADER.getTOTALPOCOST());
			data_table.addCell(tableText(""+TOTALPOCOST, 10, " ", "ALIGN_LEFT", 2, "NO BOARDER"));
			table.addCell(data_table);
			table.addCell(tableText(""+"", 10, "", "ALIGN_CENTER", 2, "NO BOARDER"));
			table.addCell(tableText(""+"TERMS AND CONDITIONS", 10, "BOLD", "ALIGN_LEFT", 2, "NO BOARDER"));
			table.addCell(tableText(""+"", 10, " ", "ALIGN_CENTER", 2, "NO BOARDER"));
			int i=1;
			for(PO_TC poTC :  POTCList)
			{
				table.addCell(tableText(i +" "+krrCommon.CheckEmptyReturn(poTC.getCONDITION())+" :   "+krrCommon.CheckEmptyReturn(poTC.getDESCRIPTION()), 10, " ", "ALIGN_LEFT", 2, "NO BOARDER"));
				i++;
			}
			document.add(table);

			document.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	
	public PdfPCell tableText(String data, int fontSize, String fontType, String  alignment, int colspan, String border)
	{
		PdfPCell cell=null;
		if (fontType.equalsIgnoreCase("BOLD"))
			cell=new PdfPCell(new Paragraph(data, FontFactory.getFont(BODY_FONT,  fontSize,Font.BOLD)));
		else
			cell=new PdfPCell(new Paragraph(data, FontFactory.getFont(BODY_FONT,  fontSize,Font.NORMAL)));
		if (alignment.equalsIgnoreCase("ALIGN_CENTER"))	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		if (alignment.equalsIgnoreCase("ALIGN_LEFT"))	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		if (alignment.equalsIgnoreCase("ALIGN_RIGHT"))	cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		if (alignment.equalsIgnoreCase("ALIGN_JUSTIFIED"))	cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell.setColspan(colspan);

		if (border.equalsIgnoreCase("BOARDER"))
			cell.setBorderWidth(1f);
		else
			cell.setBorderWidth(0f);
		return cell;
	}
	
	public String get_code_list(Connection con,List<MAJOR_MINOR_CODE> MAJORMINORCODELIST, long majorCode)
	{
		String l_ers="";
		try
		{
			l_ers=MAJORMINORDB.ge_code_list(con, MAJORMINORCODELIST, majorCode);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return l_ers;
	}	
}
