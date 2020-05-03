package com.cg.plp.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cg.plp.dao.ClaimDetailsDao;
import com.cg.plp.dao.QueryMapper;
import com.cg.plp.exception.InsuranceException;
import com.cg.plp.model.ClaimQuestion;
import com.cg.plp.model.PolicyDetails;
import com.cg.plp.utility.JdbcUtility;

public class ClaimDetailsDaoImpl implements ClaimDetailsDao {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;

	@Override
	public List<ClaimQuestion> claimQuestionsView() throws InsuranceException {
		connection = JdbcUtility.getConnection();
		List<ClaimQuestion> list = new ArrayList<>();

		try {
			statement = connection.prepareStatement(QueryMapper.selectQuestionsView);
			
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				String question = resultSet.getString(1);
			
				ClaimQuestion claimQuestion = new ClaimQuestion();
			
				claimQuestion.setQuestionDesc(question);
				

				list.add(claimQuestion);
			
			}

		} catch (SQLException e) {
			throw new InsuranceException("Problem" + e);
		}

		return list;
	
	}

	@Override
	public List<PolicyDetails> claimAnswerView() throws InsuranceException {
		connection = JdbcUtility.getConnection();
		List<PolicyDetails> list1 = new ArrayList<>();

		try {
			statement = connection.prepareStatement(QueryMapper.selectAnswersView);
			
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				String answer = resultSet.getString(1);

				PolicyDetails policydetails = new PolicyDetails();

				policydetails.setAnswer(answer);

				list1.add(policydetails);
			}

		} catch (SQLException e) {
			throw new InsuranceException("Problem" + e);
		}

		return list1;
		
	}

}
