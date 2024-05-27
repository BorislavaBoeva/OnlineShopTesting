package POM.Tests.CheckoutTests;

import POM.Pages.*;
import POM.Tests.BaseTestToURL;
import com.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.List;

public class TestCheckoutWithProblemUser extends BaseTestToURL {
    @Test(dataProvider = "userListWithTwoProductAndaCheckoutUserDetails")

    public void cartInventoryCheck(String userName, String password, String product1, String product2, String firstName, String lastName, String postalCode) {
        //Open Login page, enter user_name and password and click on Login button
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login(userName, password);
        Assert.assertTrue(productPage.isAt());

        //Add product1 and product2 and check ShoppingCart count
        productPage.addToCart(product1);
        productPage.addToCart(product2);
        String shoppingCart = productPage.ShoppingCartBadge();
        Assert.assertEquals(shoppingCart, "2");

        //Go to checkout pages
        CheckoutPage checkoutPage = productPage.clickOnShoppingCartContainer();
        Assert.assertTrue(checkoutPage.isAt());

        //go to User Details page
        UserDetailsPage userDetailPage = checkoutPage.ClickOnCheckOutBtn();
        Assert.assertTrue(userDetailPage.isAt());

        //validation user details - first name, last name, zip code
        OverviewPage overviewPage = userDetailPage.enterUserDetails(firstName, lastName, postalCode);
        Assert.assertTrue(overviewPage.isAt());

        //go to User Details page
        CompleteCheckoutPage completeCheckoutPage = overviewPage.clickFinish();
        Assert.assertTrue(completeCheckoutPage.isAt());

        //back to login page
        LoginPage loginPageAfterLogout = completeCheckoutPage.backToLoginPage();
        Assert.assertTrue(loginPageAfterLogout.isAt());

        tearDown();
    }

    //CSV file columns:
    //user_name,user_password,product1, product2, first_name, last_name, postal_code
    @DataProvider(name = "userListWithTwoProductAndaCheckoutUserDetails")
    public Object[][] readData() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/ProblemUserTwoProductsAndUserCheckoutDetails.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataResult = new Object[csvData.size()][7];

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
