package POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CompleteCheckoutPage extends BasePage {
    @FindBy(id = "checkout_complete_container")
    private WebElement getMsgCompleteOrder;
    @FindBy(id = "react-burger-menu-btn")
    private WebElement navigateMenu;
    @FindBy(id = "logout_sidebar_link")
    private WebElement logOutBtn;

    public CompleteCheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isAt() {
        return getMsgCompleteOrder.isDisplayed();
    }

    public LoginPage backToLoginPage() {
        navigateMenu.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(logOutBtn));
        logOutBtn.click();
        return new LoginPage(driver);
    }
}



