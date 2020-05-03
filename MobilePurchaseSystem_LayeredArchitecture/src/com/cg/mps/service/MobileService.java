package com.cg.mps.service;

import com.cg.mps.exception.MPSException;
import com.cg.mps.model.Mobiles;
import com.cg.mps.model.PurchaseDetails;

public interface MobileService {

	boolean validateLogin(int mobileId) throws MPSException;

	boolean checkMobileData(int mobileId1)throws MPSException;

	int insertMobileDetails(Mobiles mobiles)throws MPSException;

	boolean checkQuantity(String quantity1)throws MPSException;

	boolean validatePurchaseDetails(PurchaseDetails details)throws MPSException;

	int insertPurchaseDetails(PurchaseDetails details)throws MPSException;

	
	
	

	

}
