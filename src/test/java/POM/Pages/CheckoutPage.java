package POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {
    @FindBy(className = "title")
    private WebElement yourCartTitle;
    @FindBy(id = "checkout")
    private WebElement checkOutBtn;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isAt() {
        return yourCartTitle.isDisplayed();
    }

    public UserDetailsPage ClickOnCheckOutBtn() {
        checkOutBtn.click();
        return new UserDetailsPage(driver);
    }
}
