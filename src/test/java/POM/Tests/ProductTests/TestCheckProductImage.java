package POM.Tests.ProductTests;

import POM.Pages.LoginPage;
import POM.Pages.ProductPage;
import POM.Tests.BaseTestToURL;
import com.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;
import java.util.List;

public class TestCheckProductImage extends BaseTestToURL {

    @Test(dataProvider = "userList")
    public void checkInventoryProductImage(String userName, String password) {
        //Open Login page, enter user_name and password and click on Login button
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login(userName, password);
        Assert.assertTrue(productPage.isAt());

        SoftAssert softAssert = new SoftAssert();

        //Validation InventoryProductImage
        softAssert.assertTrue(productPage.checkImgBackpack());
        softAssert.assertTrue(productPage.checkImgBikeLight());
        softAssert.assertTrue(productPage.checkImgBoltTShirt());
        softAssert.assertTrue(productPage.checkImgFleeceJacket());
        softAssert.assertTrue(productPage.checkImgOnesie());
        softAssert.assertTrue(productPage.checkRedTShirt());

        softAssert.assertAll();
        tearDown();
    }

    //csv file user_name, user_password, inventory product list
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



