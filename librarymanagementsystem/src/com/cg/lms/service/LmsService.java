package com.cg.lms.service;

import com.cg.lms.exception.LmsException;
import com.cg.lms.model.LmsModel;

public interface LmsService {
	public boolean validateFields(LmsModel lmsModel)throws LmsException;
	public String insertValues(LmsModel lmsModel)throws LmsException;
}
