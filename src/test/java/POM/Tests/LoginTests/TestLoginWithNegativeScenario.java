package POM.Tests.LoginTests;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import POM.Pages.LoginPage;
import POM.Tests.BaseTestToURL;

public class TestLoginWithNegativeScenario extends BaseTestToURL {

    @Test(dataProvider = "wrongUsers")
    public void unsuccessfulLogin(String userName,String password){
    //Open Login page, enter user_name and password and click on Login button
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, password);
        LoginPage afterFailLogin = new LoginPage(driver);
        Assert.assertTrue(afterFailLogin.checkForErrorButton());
    }
    //csv file : wrong user_name,wrong user_password, and 1 correct user and password to check the test
    @DataProvider(name = "wrongUsers")
    public Object[][] readWrongUsers(){
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/wrongUsers.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataResult = new Object[csvData.size()][2];
            for (int i = 0; i < csvData.size(); i++) {
                csvDataResult[i] = csvData.get(i);
            }
            return csvDataResult;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
