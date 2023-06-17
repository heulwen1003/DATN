package utils.Jira;

import java.lang.reflect.Field;

import org.bouncycastle.crypto.BasicAgreement;
import net.rcarz.jiraclient.*;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Component.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraServiceProvider {
	public JiraClient jira;
	public String projectString;
	public JiraServiceProvider(String jiraURL, String usename, String Password, String project) {
		BasicCredentials creds=new BasicCredentials(usename, Password);
		jira=new JiraClient(jiraURL,creds);
		this.projectString=project;
	}
	public void createJiraTicket(String issueType, String sumary, String description, String reportname){
		try {
			net.rcarz.jiraclient.Issue.FluentCreate fluentCreate=jira.createIssue(projectString,issueType);
			fluentCreate.field(net.rcarz.jiraclient.Field.SUMMARY, sumary);
			fluentCreate.field(net.rcarz.jiraclient.Field.DESCRIPTION, description);
			Issue newIssue=fluentCreate.execute();
			System.out.println("new issue created in jira with ID "+ newIssue);
		}catch (JiraException e) {
			e.printStackTrace();
		}
	}
}
