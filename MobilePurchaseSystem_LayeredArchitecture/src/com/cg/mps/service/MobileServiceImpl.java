package com.cg.mps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.cg.mps.dao.MobileDao;
import com.cg.mps.dao.MobileDaoImpl;
import com.cg.mps.exception.MPSException;
import com.cg.mps.model.Mobiles;
import com.cg.mps.model.PurchaseDetails;

public class MobileServiceImpl implements MobileService{

	MobileDao mbDao = new MobileDaoImpl();
	boolean validateLoginFlag = false;
	boolean checkMobileFlag = false;
	boolean checkQuantityFlag = false;
	
	@Override
	public boolean validatePurchaseDetails(PurchaseDetails details) throws MPSException {
		
			boolean resultFlag = false;
			List<String> list = new ArrayList<>();

			if (validateCustomerName(details.getcName()) == false) {
				list.add("Customer name First letter capital size max 20 \n");
			}
			if (validateMailId(details.getMailId()) == false) {
				list.add("It should be a valid mail id \n");
			}
			if (validatePhoneNo(details.getPhoneNo()) == false) {
				list.add("It should contain exactly 10 digits \n");
			}
			if (validateMobileId(details.getMobileId()) == false) {
				list.add("It should contain exactly 4 digits");
			}
			if (list.size() > 0) {
				throw new MPSException(list + "");
			} else {
				resultFlag = true;
			}
			return resultFlag;
		}



	private boolean validatePhoneNo(String phoneNo) {
		String phoneNoRegEx = "\\d{10}";
		return Pattern.matches(phoneNoRegEx, phoneNo);
	}

	private boolean validateMobileId(Integer mobileId) {
		String mobileIdRegEx = "\\d{4}";
		return Pattern.matches(mobileIdRegEx, Integer.toString(mobileId));
	}

	private boolean validateMailId(String mailId) {
		String mailIdRegEx = "[a-z0-9._-]+@[a-zA-z]+\\.[a-zA-Z]+";
		return Pattern.matches(mailIdRegEx, mailId);

	}

	private boolean validateCustomerName(String cName) {
		String nameRegEx = "[A-Z]{1}[A-Za-z ]{4,20}";
		return Pattern.matches(nameRegEx, cName);
	}



	@Override
	public boolean validateLogin(int mobileId) throws MPSException {
		
		return validateLoginFlag = mbDao.validateLogin(mobileId);
	}



	@Override
	public boolean checkMobileData(int mobileId1) throws MPSException {
		return checkMobileFlag = mbDao.checkMobileData(mobileId1);
	}



	@Override
	public int insertMobileDetails(Mobiles mobiles) throws MPSException {
		return mbDao.insertMobileDetails(mobiles);
	}



	@Override
	public boolean checkQuantity(String quantity1) throws MPSException {
		
		return checkQuantityFlag = mbDao.checkQuantity(quantity1);
		
		/*boolean quantityFlag = true;

		if (Integer.parseInt(quantity1) >0 ) {
			quantityFlag = true;
		}
		return quantityFlag;*/
	}



	@Override
	public int insertPurchaseDetails(PurchaseDetails details) throws MPSException {
		return mbDao.insertPurchaseDetails(details);
	}


	
	
}
