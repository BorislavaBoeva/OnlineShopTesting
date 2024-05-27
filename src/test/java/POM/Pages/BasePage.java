package POM.Pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;

    //Page order:
    //BasePage base class
    //LoginPage -> ProductPage -> CheckoutPage -> UserDetailsPage -> OverviewPate -> CompleteCheckoutPage -> Logout

    //constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public abstract boolean isAt();
}
