package com.cg.mps.dao;

public class QueryMapper {

	public static String validateLoginQuery = "select mobileid from mobiles where mobileid=?";
	public static String selectGeneratedId = "select purchase_seq.CURRVAL from dual";
	public static String insertMobileQuery = "INSERT INTO mobiles(mobileid,name,price,quantity) VALUES(?,?,?,?) ";
	public static String insertPurchaseQuery =" INSERT INTO purchasedetails(purchaseid,cname,mailid,phoneno,purchasedate,mobileid) VALUES(purchase_seq.nextval,?,?,?,?,?) ";
	public static String validateCheckQuery = "select m.quantity from mobiles m , purchasedetails p where m.mobileid=p.mobileid";

}
