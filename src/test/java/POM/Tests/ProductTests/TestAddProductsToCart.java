package POM.Tests.ProductTests;

import POM.Pages.LoginPage;
import POM.Pages.ProductPage;
import POM.Tests.BaseTestToURL;
import com.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.List;

public class TestAddProductsToCart extends BaseTestToURL {

    @Test(dataProvider = "loginDataAndProductItems")
    public void addProductsToTheCart(String userName, String password, String product) {
        //login with user-name and password
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login(userName, password);
        Assert.assertTrue(productPage.isAt());

        //add product to cart
        productPage.addToCart(product);
        String shoppingCart = productPage.ShoppingCartBadge();
        Assert.assertEquals(shoppingCart, "1");
    }

    //csv file user_name, user_password, product
    @DataProvider(name = "loginDataAndProductItems")
    public Object[][] readDataAndProductItems() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/UserListAndProduct.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataResult = new Object[csvData.size()][3];
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
