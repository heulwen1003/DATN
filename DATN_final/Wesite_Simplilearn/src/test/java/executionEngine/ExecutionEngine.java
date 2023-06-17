package executionEngine;

import java.util.ArrayList;


import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.google.errorprone.annotations.Var;

import config.ActionKeywords;
import utils.ExcelUtils;
import utils.LogHelpers;
import utils.ReportListener;
import utils.ScreenshotHelpers;
import utils.Extentreports.ExtentTestManager;
import utils.Jira.JiraPolicy;
import utils.Extentreports.ExtentManager_Class;

@Listeners(ReportListener.class)
 
public class ExecutionEngine {
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String locatorType;
	public static String locatorValue;
	public static String testData;
	public static String tsId_Login;
	public static String tsId_Register;
	public static String tsId;
	 
	public String sPath = System.getProperty("user.dir") + "\\data\\DataEngine_Simplilearn_ver6.xlsx";
	ArrayList<String> arrEmail = new ArrayList<String>();
	ArrayList<String> arrPassword = new ArrayList<String>();
	ArrayList<String> arrResult = new ArrayList<String>();
	ArrayList<String> arrtestcaseID = new ArrayList<String>();
	ArrayList<String> arrTsId = new ArrayList<String>();
	ArrayList<String> arrfirstname = new ArrayList<String>();
	ArrayList<String> arrlastname = new ArrayList<String>();
	ArrayList<String> arremailRegister = new ArrayList<String>();
	ArrayList<String> arrphonenumber = new ArrayList<String>();
	ArrayList<String> arrpasswordRegister = new ArrayList<String>();  
	ArrayList<String> arrname = new ArrayList<String>();
	ArrayList<String> arremail = new ArrayList<String>();
	ArrayList<String> arrphone = new ArrayList<String>();
	ArrayList<String> arrkeywords = new ArrayList<String>();
	ArrayList<String> arr_valueCountryCode = new ArrayList<String>();
	ArrayList<String> arrConfirmEmail = new ArrayList<String>();
	 //có bao nhiêu cái var là bấy nhiêu cái array nhế
//	@Test(priority = 0,description = "Login page")
//		public void TestSuite_Login() throws Exception {
//			// testcase đăng nhập thành công
//			ExcelUtils.setExcelFile(sPath, "Login");
//			Sheet sheet = ExcelUtils.getSheet("Login");
//			int rowCount = sheet.getLastRowNum();
//			int row = 1;
//			String tmp;
//	 
//			ExcelUtils.setExcelFile(sPath, "login_data2");
//			Sheet data = ExcelUtils.getSheet("login_data2");
//			int rowCountTest = data.getLastRowNum();
//			
//			while (row <= rowCountTest) {
//				tmp = ExcelUtils.getCellData("login_data2", row, 3) + "";
//				arrEmail.add(tmp);	 
//				tmp = ExcelUtils.getCellData("login_data2",row, 4) + "";
//				arrPassword.add(tmp);
//				tmp = ExcelUtils.getCellData("login_data2",row, 5) + "";
//				arrResult.add(tmp);
//				tmp = ExcelUtils.getCellData("login_data2", row, 1) + "";
//				arrtestcaseID.add(tmp); 
//				tmp = ExcelUtils.getCellData("login_data2", row, 2) + "";
//				arrTsId.add(tmp); 
//				row = row + 1;   
//			}
//			 
//			for (int i = 0; i < arrtestcaseID.size(); i++) { 
//				for (int iRow = 1; iRow <= rowCount; iRow++) {
//					sActionKeyword = ExcelUtils.getCellData("Login", iRow, 4);					
//					locatorType = ExcelUtils.getCellData("Login",iRow, 5);					
//					locatorValue = ExcelUtils.getCellData("Login", iRow, 6);					
//					testData = ExcelUtils.getCellData("Login",iRow, 7);	
//					tsId_Login = ExcelUtils.getCellData("Login",iRow, 2);	
//					 
//					if(arrTsId.get(i).toString().equalsIgnoreCase(tsId_Login.toString()))
//					{
//						executeAction(testData, arrtestcaseID.get(i),null, null,null,null,null, arrEmail.get(i),
//								arrPassword.get(i), arrResult.get(i),null,null,null,null, null,null);
//					}
//				} 
//
//			}
//		}
//	 
//	@Test(priority = 1, description = "Register page")
//
//	public void TestSuite_Register() throws Exception {
//		ExcelUtils.setExcelFile(sPath, "Register_script");
//		Sheet sheet = ExcelUtils.getSheet("Register_script");
//		int rowCount = sheet.getLastRowNum();
//		int row = 1;
//		String tmp;
//		ExcelUtils.setExcelFile(sPath, "Register_data");
//		Sheet data = ExcelUtils.getSheet("Register_data");
//		int rowCountTest = data.getLastRowNum();
//		while (row <= rowCountTest) {
//			tmp = ExcelUtils.getCellData("Register_data", row, 3) + "";
//			arrfirstname.add(tmp);	 
//			tmp = ExcelUtils.getCellData("Register_data",row, 4) + "";
//			arrlastname.add(tmp);
//			tmp = ExcelUtils.getCellData("Register_data",row, 5) + "";
//			arremailRegister.add(tmp);
//			tmp = ExcelUtils.getCellData("Register_data",row, 6) + "";
//			arrphonenumber.add(tmp);
//			tmp = ExcelUtils.getCellData("Register_data",row, 7) + "";
//			arrpasswordRegister.add(tmp);
//			tmp = ExcelUtils.getCellData("Register_data",row, 8) + "";
//			arrResult.add(tmp);
//			tmp = ExcelUtils.getCellData("Register_data", row, 1) + "";
//			arrtestcaseID.add(tmp); 
//			tmp = ExcelUtils.getCellData("Register_data", row, 2) + "";
//			arrTsId.add(tmp); 
//			row = row + 1;   
//		} 
//		for (int i = 0; i < arrtestcaseID.size(); i++) { 
//			for (int iRow = 1; iRow <= rowCount; iRow++) {
//				sActionKeyword = ExcelUtils.getCellData("Register_script", iRow, 4);					
//				locatorType = ExcelUtils.getCellData("Register_script",iRow, 5);					
//				locatorValue = ExcelUtils.getCellData("Register_script", iRow, 6);					
//				testData = ExcelUtils.getCellData("Register_script",iRow, 7);	
//				tsId_Register = ExcelUtils.getCellData("Register_script",iRow, 2);	
//				 
//				if(arrTsId.get(i).toString().equalsIgnoreCase(tsId_Register.toString()))
//				{
//					executeAction(testData, arrtestcaseID.get(i), arrfirstname.get(i),arrlastname.get(i),arremailRegister.get(i),arrphonenumber.get(i),
//							arrpasswordRegister.get(i), null, null, arrResult.get(i),null,null,null,null, null,null);
//				} 
//			} 
//
//		}
//	}
//	@Test(priority = 2, description = "Apply_course page")
//	public void Apply_Course() throws Exception {
//		ExcelUtils.setExcelFile(sPath, "ApplyCourse_scripts");
//		Sheet sheet = ExcelUtils.getSheet("ApplyCourse_scripts");
//		int rowCount = sheet.getLastRowNum();
//		int row = 1;
//		String tmp;
// 
//		ExcelUtils.setExcelFile(sPath, "ApplyCourse_data");
//		Sheet data = ExcelUtils.getSheet("ApplyCourse_data");
//		int rowCountTest = data.getLastRowNum();
//		
//		while (row <= rowCountTest) {
//			tmp = ExcelUtils.getCellData("ApplyCourse_data", row, 3) + "";
//			arrname.add(tmp);	 
//			tmp = ExcelUtils.getCellData("ApplyCourse_data",row, 4) + "";
//			arremail.add(tmp);
//			tmp = ExcelUtils.getCellData("ApplyCourse_data",row, 5) + "";
//			arrphone.add(tmp);
//			tmp = ExcelUtils.getCellData("ApplyCourse_data",row, 6) + "";
//			arrResult.add(tmp);
//			tmp = ExcelUtils.getCellData("ApplyCourse_data", row, 1) + "";
//			arrtestcaseID.add(tmp); 
//			tmp = ExcelUtils.getCellData("ApplyCourse_data", row, 2) + "";
//			arrTsId.add(tmp); 
//			row = row + 1;   
//		}
//		 
//		for (int i = 0; i < arrtestcaseID.size(); i++) { 
//			for (int iRow = 1; iRow <= rowCount; iRow++) {
//				sActionKeyword = ExcelUtils.getCellData("ApplyCourse_scripts", iRow, 4);					
//				locatorType = ExcelUtils.getCellData("ApplyCourse_scripts",iRow, 5);					
//				locatorValue = ExcelUtils.getCellData("ApplyCourse_scripts", iRow, 6);					
//				testData = ExcelUtils.getCellData("ApplyCourse_scripts",iRow, 7);	
//				tsId = ExcelUtils.getCellData("ApplyCourse_scripts",iRow, 2);	
//				 
//				if(arrTsId.get(i).toString().equalsIgnoreCase(tsId.toString()))
//				{
//					executeAction(testData, arrtestcaseID.get(i), null, null,null,null, null,null, null, 
//							arrResult.get(i),arrname.get(i),arremail.get(i),arrphone.get(i),null, null,null);
//				} 
//			} 
//
//		}
//	}
//	@Test(priority = 3, description = "Search")
//	public void Search() throws Exception {
//		ExcelUtils.setExcelFile(sPath, "Search_scripts");
//		Sheet sheet = ExcelUtils.getSheet("Search_scripts");
//		int rowCount = sheet.getLastRowNum();
//		int row = 1;
//		String tmp;
// 
//		ExcelUtils.setExcelFile(sPath, "Search_data"); 
//		Sheet data = ExcelUtils.getSheet("Search_data");
//		int rowCountTest = data.getLastRowNum();
//		
//		while (row <= rowCountTest) {
//			tmp = ExcelUtils.getCellData("Search_data", row, 3) + "";
//			arrkeywords.add(tmp);	 
//
//			tmp = ExcelUtils.getCellData("Search_data",row, 4) + "";
//			arrResult.add(tmp);
//			tmp = ExcelUtils.getCellData("Search_data", row, 1) + "";
//			arrtestcaseID.add(tmp); 
//			tmp = ExcelUtils.getCellData("Search_data", row, 2) + "";
//			arrTsId.add(tmp); 
//			row = row + 1;   
//		}
//		 
//		for (int i = 0; i < arrtestcaseID.size(); i++) { 
//			for (int iRow = 1; iRow <= rowCount; iRow++) {
//				sActionKeyword = ExcelUtils.getCellData("Search_scripts", iRow, 4);					
//				locatorType = ExcelUtils.getCellData("Search_scripts",iRow, 5);					
//				locatorValue = ExcelUtils.getCellData("Search_scripts", iRow, 6);					
//				testData = ExcelUtils.getCellData("Search_scripts",iRow, 7);	
//				tsId = ExcelUtils.getCellData("Search_scripts",iRow, 2);	
//				 
//				if(arrTsId.get(i).toString().equalsIgnoreCase(tsId.toString()))
//				{
//					executeAction(testData, arrtestcaseID.get(i), null, null,null,null, null,null, null, 
//							arrResult.get(i),null,null,null, arrkeywords.get(i), null,null);
//				} 
//			} 
//
//		}
//	}
	//@JiraPolicy(logTicketReady = true)
	@Test(priority = 4, description = "Enroll_course page")
	public void Apply_Course() throws Exception {
		ExcelUtils.setExcelFile(sPath, "Enroll_course");
		Sheet sheet = ExcelUtils.getSheet("Enroll_course");
		int rowCount = sheet.getLastRowNum();
		int row = 1;
		String tmp;
 
		ExcelUtils.setExcelFile(sPath, "Enroll_data");
		Sheet data = ExcelUtils.getSheet("Enroll_data");
		int rowCountTest = data.getLastRowNum();
		
		while (row <= rowCountTest) {
			tmp = ExcelUtils.getCellData("Enroll_data", row, 3) + "";
			arrname.add(tmp);	 
			tmp = ExcelUtils.getCellData("Enroll_data",row, 4) + "";
			arremail.add(tmp);
			tmp = ExcelUtils.getCellData("Enroll_data",row, 5) + "";
			arrConfirmEmail.add(tmp);
			tmp = ExcelUtils.getCellData("Enroll_data",row, 6) + "";
			arr_valueCountryCode.add(tmp);
			tmp = ExcelUtils.getCellData("Enroll_data",row, 7) + "";
			arrphonenumber.add(tmp);
			tmp = ExcelUtils.getCellData("Enroll_data",row, 8) + "";
			arrResult.add(tmp);
			tmp = ExcelUtils.getCellData("Enroll_data", row, 1) + "";
			arrtestcaseID.add(tmp); 
			tmp = ExcelUtils.getCellData("Enroll_data", row, 2) + "";
			arrTsId.add(tmp); 
			row = row + 1;   
		} 
		 
		for (int i = 0; i < arrtestcaseID.size(); i++) { 
			for (int iRow = 1; iRow <= rowCount; iRow++) {
				sActionKeyword = ExcelUtils.getCellData("Enroll_course", iRow, 4);					
				locatorType = ExcelUtils.getCellData("Enroll_course",iRow, 5);					
				locatorValue = ExcelUtils.getCellData("Enroll_course", iRow, 6);					
				testData = ExcelUtils.getCellData("Enroll_course",iRow, 7);	
				tsId = ExcelUtils.getCellData("Enroll_course",iRow, 2);	
				 
				if(arrTsId.get(i).toString().equalsIgnoreCase(tsId.toString()))
				{
					executeAction(testData, arrtestcaseID.get(i), null, null,null,null, null,null, null, 
							arrResult.get(i),arrname.get(i),arremail.get(i),arrphonenumber.get(i),null,
							arrConfirmEmail.get(i),arr_valueCountryCode.get(i));
				} 
			} 

		} 
	}
	public void executeAction(String testData,String stestcaseID,
			String sfirstname,String slastname, String sEmailRegister,String sphone_register, String sPasswordRegister,
		 
			  String sEmailLogin, String spwLogin, String sResult,
			  String sname_apply, String semail_apply, String sphone_apply,
			  String skeywords,
			  String sConfirmEmail, String sValue_CountryCode
			  )
			throws Exception {
		try {
			switch (sActionKeyword) {
			case "openBrowser":
				ActionKeywords.openBrowser(testData);
				break;
			case "navigate":
				ActionKeywords.navigate(testData);
				break; 
			case "movetoElement":

				ActionKeywords.moveToElement(locatorType, locatorValue);
				ActionKeywords.clickElement(locatorType, locatorValue);

				break;
			case "selectOptionByValue":

				ActionKeywords.selectOptionByValue(locatorType, locatorValue, testData);

				break;
				 
			case "scrollToElement":

				ActionKeywords.scrollToElement(locatorType, locatorValue);

				break;
				
			case "clickElementWithJs":  

				ActionKeywords.clickElementWithJs(locatorType, locatorValue);

				break;
			case "verifyElementPresent": 
				if (ActionKeywords.verifyElementPresent(locatorType, locatorValue)==true) {
					LogHelpers.info(stestcaseID.toString() + " Testcase pass");
					ExtentTestManager.logMessage(Status.PASS, stestcaseID.toString() + " Testcase pass");
				} 
				else {
					LogHelpers.error(stestcaseID.toString() + " Testcase fail");
					ExtentTestManager.logMessage(Status.FAIL, stestcaseID.toString() + " Testcase fail");
					ScreenshotHelpers.captureScreenshot(stestcaseID);
					Thread.sleep(3000);
				}
				 

				break;
			case "verifyUrl":
				if (ActionKeywords.verifyUrl(sResult)==true) {
					LogHelpers.info(stestcaseID.toString() + " Testcase pass");
					ExtentTestManager.logMessage(Status.PASS, stestcaseID.toString() + " Testcase pass");
				} 
				else {
					LogHelpers.error(stestcaseID.toString() + " Testcase fail");
					ExtentTestManager.logMessage(Status.FAIL, stestcaseID.toString() + " Testcase fail");
					ScreenshotHelpers.captureScreenshot(stestcaseID);
					Thread.sleep(3000);
				}
				
			case "verifyPageTitle":
				if (ActionKeywords.verifyPageTitle(sResult)==true) { 
					LogHelpers.info(stestcaseID.toString() + " Testcase pass");
					ExtentTestManager.logMessage(Status.PASS, stestcaseID.toString() + " Testcase pass");
				} 
				else {
					LogHelpers.error(stestcaseID.toString() + " Testcase fail");
					ExtentTestManager.logMessage(Status.FAIL, stestcaseID.toString() + " Testcase fail");
					ScreenshotHelpers.captureScreenshot(stestcaseID);
					Thread.sleep(3000);
				}
			case "clickElement":

				ActionKeywords.clickElement(locatorType, locatorValue);
				ActionKeywords.WindowHelper();
				break;
			case "selectCheckbox":

				ActionKeywords.selectCheckbox1(locatorType, locatorValue);
				 
				break;
		  
			case "verifyElementText":
				if (ActionKeywords.verifyElementText(locatorType, locatorValue, sResult) == true) {
					LogHelpers.info(stestcaseID.toString() + " Testcase pass");
					ExtentTestManager.logMessage(Status.PASS, stestcaseID.toString() + " Testcase pass");		 
				} else {
					LogHelpers.error(stestcaseID.toString() + " Testcase fail");
					ExtentTestManager.logMessage(Status.FAIL, stestcaseID.toString() + " Testcase fail");
					ScreenshotHelpers.captureScreenshot(stestcaseID);
					Thread.sleep(3000);
				}
				break;		 
			case "setText":
				if (testData.equalsIgnoreCase("varEmaillogin"))
					ActionKeywords.setText(locatorType, locatorValue, sEmailLogin);
				else if(testData.equalsIgnoreCase("varPasswordlogin"))
					ActionKeywords.setText(locatorType, locatorValue, spwLogin);
				
				else if(testData.equalsIgnoreCase("varfirstNameregister"))
					ActionKeywords.setText(locatorType, locatorValue, sfirstname);
				
				else if(testData.equalsIgnoreCase("varlastnameregister"))
					
					ActionKeywords.setText(locatorType, locatorValue, slastname);
				
				else if(testData.equalsIgnoreCase("varEmailregister"))
					
					ActionKeywords.setText(locatorType, locatorValue, sEmailRegister);
				
				else if(testData.equalsIgnoreCase("varphoneNumberRegister"))
					
					ActionKeywords.setText(locatorType, locatorValue, sphone_register);
				
				else if(testData.equalsIgnoreCase("varPasswordRegister"))
					
					ActionKeywords.setText(locatorType, locatorValue, sPasswordRegister);
				
				else if(testData.equalsIgnoreCase("varname_apply"))
					
					ActionKeywords.setText(locatorType, locatorValue, sname_apply);
				else if(testData.equalsIgnoreCase("varemail_apply"))
									
					ActionKeywords.setText(locatorType, locatorValue, semail_apply);
				else if(testData.equalsIgnoreCase("varphoneNumberApply"))
					
					ActionKeywords.setText(locatorType, locatorValue, sphone_apply);
				 
				else if(testData.equalsIgnoreCase("varkeyword"))
					
					ActionKeywords.setText(locatorType, locatorValue, skeywords);
				else if(testData.equalsIgnoreCase("varConfirmemail_apply"))
					
					ActionKeywords.setText(locatorType, locatorValue, sConfirmEmail);
				else if(testData.equalsIgnoreCase("var_value"))
	
					ActionKeywords.setText(locatorType, locatorValue, sValue_CountryCode);
				break;
  
			case "closeBrower":
				ActionKeywords.quitDriver();
				
				break;
			default:
				System.out.println("[>>ERROR<<]: |Keyword Not Found " + sActionKeyword);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}


}
