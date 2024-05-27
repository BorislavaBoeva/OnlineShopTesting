package POM.Tests.CheckoutTests;

import POM.Pages.*;
import POM.Tests.BaseTestToURL;
import com.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.FileReader;
import java.util.List;

public class TestCheckoutNegativeScenario extends BaseTestToURL {
    @Test(dataProvider = "WrongUserCheckoutDetailsUserListWithThreeProducts")
    public void checkoutAddedProducts(String userName, String password, String product1, String product2, String product3, String firstName, String lastName, String postalCode) {
        //Open Login page, enter user_name and password and click on Login button
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login(userName, password);
        Assert.assertTrue(productPage.isAt());

        //Add product1, product2 and product3 then check shoppingCart count
        productPage.addToCart(product1);
        productPage.addToCart(product2);
        productPage.addToCart(product3);
        String shoppingCart = productPage.ShoppingCartBadge();
        Assert.assertEquals(shoppingCart, "3");

        //go to Checkout page
        CheckoutPage checkoutPage = productPage.clickOnShoppingCartContainer();
        Assert.assertTrue(checkoutPage.isAt());

        //go to User Details page
        UserDetailsPage userDetailPage = checkoutPage.ClickOnCheckOutBtn();
        Assert.assertTrue(userDetailPage.isAt());

        //validation user details - wrong: first name, last name, zip code
        UserDetailsPage afterUserDetails = new UserDetailsPage(driver);
        afterUserDetails.enterUserDetails(firstName,lastName,postalCode);
        Assert.assertTrue(afterUserDetails.getErrorBtnInputMsg());

        tearDown();
    }
    //CSV file columns:
    //user_name,user_password,product1,product2, product3, first_name, last_name, postal_code
    @DataProvider(name = "WrongUserCheckoutDetailsUserListWithThreeProducts")
    public Object[][] readData() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/UserListWithThreeProductsAndWrongUserDetails.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataResult = new Object[csvData.size()][8];
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

