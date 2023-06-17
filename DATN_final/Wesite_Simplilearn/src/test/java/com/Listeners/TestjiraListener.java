package com.Listeners;

import java.text.Annotation;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.ITest;

import com.aventstack.extentreports.model.service.util.ExceptionUtil;

import utils.Jira.JiraPolicy;
import utils.Jira.JiraServiceProvider;

public class TestjiraListener implements ITestListener{
	@Override
	public void onTestFailure(ITestResult result) {
		JiraPolicy jiraPolicy= result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
		boolean isTicketReady=jiraPolicy.logTicketReady();
		if(isTicketReady) {
			System.out.println("is ticket ready for Jira"+isTicketReady);
			JiraServiceProvider jiraSP=new JiraServiceProvider("https://huyenntt.atlassian.net","thanhhuyennguyen280@gmail.com",
					"ATATT3xFfGF0jQaeDRIGzLjlwsrmTu1_aSOxVO5sTwmNTDQj3GTBNJQT1bb5pI80npRNwUCPkPtaQ9WRf_YNnjG4k89Fl-ALfvC8V_3BJqYTAoT1aYsnheKi6ZbjZBx6nze0F-mOyxQlVAysS0qYSwOsiDVenqPLx1B9R2P-KKBKbfwvTrKBcLE=1FD76827","DN");
			String issueSummaryString=result.getMethod().getConstructorOrMethod().getMethod().getName()+"got field due to some asser";
			String issueDescriptionString=result.getThrowable().getMessage()+"\n";
			issueDescriptionString.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
			jiraSP.createJiraTicket("Bug", issueSummaryString, issueDescriptionString, "huyenntt");
		}
		
		
		
		
		
	}
}
