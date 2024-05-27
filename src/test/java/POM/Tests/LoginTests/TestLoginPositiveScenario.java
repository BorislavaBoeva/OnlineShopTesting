package POM.Tests.LoginTests;

import POM.Pages.LoginPage;
import POM.Pages.ProductPage;
import POM.Tests.BaseTestToURL;
import com.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.List;

public class TestLoginPositiveScenario extends BaseTestToURL {

    @Test(dataProvider = "userNameAndPassword")
    public void Login(String userName, String password) {
        //Open Login page, enter user_name and password and click on Login button
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login(userName, password);
        Assert.assertTrue(productPage.isAt());
    }

    //csv file : user_name,user_password
    @DataProvider(name = "userNameAndPassword")
    public Object[][] readUsersAndPassword() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/UserList.csv"));
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




