package POM.Tests.Logout;

import POM.Pages.LoginPage;
import POM.Pages.ProductPage;
import POM.Tests.BaseTestToURL;
import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;
import java.util.List;

public class TestLogout extends BaseTestToURL {

    @Test(dataProvider = "userList")
    public void successfulLogout(String userName, String password) {
        //Open Login page, enter user_name and password and click on Login button
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login(userName, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(productPage.isAt());

        LoginPage loginPageAfterLogout = productPage.logout();
        softAssert.assertTrue(loginPageAfterLogout.isAt());
        softAssert.assertAll();
    }

    //csv file with user_name and password
    @DataProvider(name = "userList")
    public Object[][] readUserList() {
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


