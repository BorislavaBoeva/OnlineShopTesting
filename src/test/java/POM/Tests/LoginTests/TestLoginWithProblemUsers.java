package POM.Tests.LoginTests;

import POM.Pages.LoginPage;
import POM.Pages.ProductPage;
import POM.Tests.BaseTestToURL;
import com.opencsv.CSVReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.time.Duration;
import java.util.List;

public class TestLoginWithProblemUsers extends BaseTestToURL {

    @Test(dataProvider = "problemUserDetails")
    public void LoginWithProblemUser(String userName, String password) {
        //Open Login page, enter user_name and password and click on Login button
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login(userName, password);
        Assert.assertTrue(productPage.isAt());

        //Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(productPage.getProductPageTitle()));
        Assert.assertTrue(productPage.getProductPageTitle().isDisplayed());
    }

    //csv file : user_name,user_password
    @DataProvider(name = "problemUserDetails")
    public Object[][] readUsersAndPassword() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/problemUser.csv"));
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

