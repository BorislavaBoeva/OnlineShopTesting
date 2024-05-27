package POM.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserDetailsPage extends BasePage {
    @FindBy(className = "title")
    private WebElement userInfoTitle;
    @FindBy(id = "first-name")
    private WebElement firstNameInput;
    @FindBy(id = "last-name")
    private WebElement lastNameInput;
    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;
    @FindBy(id = "continue")
    private WebElement submitBtn;
    @FindBy(className = "error-button")
    private WebElement errorBtnInputMsg;

    public UserDetailsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isAt() {
        return userInfoTitle.isDisplayed();
    }

    public OverviewPage enterUserDetails(String firstName, String lastName, String postalCode) {
        firstNameInput.click();
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        lastNameInput.click();
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        postalCodeInput.click();
        postalCodeInput.clear();
        postalCodeInput.sendKeys(postalCode);
        submitBtn.click();
        return new OverviewPage(driver);
    }

    public boolean getErrorBtnInputMsg() {
        return errorBtnInputMsg.isDisplayed();
    }
}
