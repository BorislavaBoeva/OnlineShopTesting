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

public class TestAddThreeProductsToCart extends BaseTestToURL {
    @Test(dataProvider = "addThreeProduct")
    public void addThreeItemsToTheCart(String userName, String password, String product1, String product2, String product3) {
        //login with user_name and user_password
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login(userName, password);
        Assert.assertTrue(productPage.isAt());

        //add product to the cart and check shopping cart badge
        productPage.addToCart(product1);
        productPage.addToCart(product2);
        productPage.addToCart(product3);
        String shoppingCart = productPage.ShoppingCartBadge();
        Assert.assertEquals(shoppingCart, "3");
    }

    //csv user_name, user_password, product1, product2, product3
    @DataProvider(name = "addThreeProduct")
    public Object[][] readDataAndProductItems() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/UserListWithThreeProducts.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataResult = new Object[csvData.size()][5];
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
