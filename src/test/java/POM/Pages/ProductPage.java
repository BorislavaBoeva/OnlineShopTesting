package POM.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {
    private final static String BASE_PRODUCT_ID = "add-to-cart-sauce-labs-";
    @FindBy(className = "title")
    private WebElement productPageTitle;
    @FindBy(id = "react-burger-menu-btn")
    private WebElement navMenu;
    @FindBy(id = "logout_sidebar_link")
    private WebElement logOutBtn;
    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;
    @FindBy(id = "shopping_cart_container")
    private WebElement shoppingCartContainer;
    @FindBy(css = "[src=\"/static/media/sauce-backpack-1200x1500.0a0b85a3.jpg\"]")
    private WebElement backpack;
    @FindBy(css = "[src=\"/static/media/bike-light-1200x1500.37c843b0.jpg\"]")
    private WebElement bikeLight;
    @FindBy(css = "[src=\"/static/media/bolt-shirt-1200x1500.c2599ac5.jpg\"]")
    private WebElement boltTShirt;
    @FindBy(css = "[src=\"/static/media/sauce-pullover-1200x1500.51d7ffaf.jpg\"]")
    private WebElement fleeceJacket;
    @FindBy(css = "[src=\"/static/media/red-onesie-1200x1500.2ec615b2.jpg\"]")
    private WebElement onesie;
    @FindBy(css = "[src=\"/static/media/red-tatt-1200x1500.30dadef4.jpg\"]")
    private WebElement redTShirt;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getProductPageTitle() {
        return productPageTitle;
    }

    @Override
    public boolean isAt() {
        return productPageTitle.isDisplayed();
    }

    public void addToCart(String itemName) {
        String addItemButtonID = BASE_PRODUCT_ID + itemName;
        WebElement addItem = driver.findElement(By.id(addItemButtonID));
        addItem.click();
    }

    public String ShoppingCartBadge() {
        return shoppingCartBadge.getText();
    }

    public CheckoutPage clickOnShoppingCartContainer() {
        shoppingCartContainer.click();
        return new CheckoutPage(driver);
    }

    public boolean checkImgBackpack() {
        if (!driver.findElements(By.cssSelector("[src=\"/static/media/sauce-backpack-1200x1500.0a0b85a3.jpg\"]")).isEmpty())
            return backpack.isDisplayed();
        else return false;
    }

    public boolean checkImgBikeLight() {
        if (!driver.findElements(By.cssSelector("[src=\"/static/media/bike-light-1200x1500.37c843b0.jpg\"]")).isEmpty())
            return bikeLight.isDisplayed();
        else return false;
    }

    public boolean checkImgBoltTShirt() {
        if (!driver.findElements(By.cssSelector("[src=\"/static/media/bolt-shirt-1200x1500.c2599ac5.jpg\"]")).isEmpty())
            return boltTShirt.isDisplayed();
        else return false;
    }

    public boolean checkImgFleeceJacket() {
        if (!driver.findElements(By.cssSelector("[src=\"/static/media/sauce-pullover-1200x1500.51d7ffaf.jpg\"]")).isEmpty())
            return fleeceJacket.isDisplayed();
        else return false;
    }

    public boolean checkImgOnesie() {
        if (!driver.findElements(By.cssSelector("[src=\"/static/media/red-onesie-1200x1500.2ec615b2.jpg\"]")).isEmpty())
            return onesie.isDisplayed();
        else return false;
    }

    public boolean checkRedTShirt() {
        if (!driver.findElements(By.cssSelector("[src=\"/static/media/red-tatt-1200x1500.30dadef4.jpg\"]")).isEmpty())
            return redTShirt.isDisplayed();
        else return false;
    }

    public LoginPage logout() {
        navMenu.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(logOutBtn));

        logOutBtn.click();
        return new LoginPage(driver);
    }
}
