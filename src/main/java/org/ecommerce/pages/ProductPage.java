package org.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By sizeOptionLocator = By.cssSelector(".swatch-option.text"); 
    private By colorOptionLocator = By.cssSelector(".swatch-option.color"); 
    private By addToCartButton = By.id("product-addtocart-button");
    private By productAddedMessage = By.cssSelector(".message-success.success.message");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void selectSize(String size) {
        By specificSizeOption = By.xpath("//div[@option-label='" + size + "' and contains(@class, 'swatch-option text')]");
        WebElement sizeElement = wait.until(ExpectedConditions.elementToBeClickable(specificSizeOption));
        sizeElement.click();
    }

    public void selectColor(String color) {
        By specificColorOption = By.xpath("//div[@option-label='" + color + "' and contains(@class, 'swatch-option color')]");
        WebElement colorElement = wait.until(ExpectedConditions.elementToBeClickable(specificColorOption));
        colorElement.click();
    }

    public void clickAddToCart() {
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartBtn.click();
    }

    public boolean isProductAddedToCartMessageDisplayed() {
        try {
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(productAddedMessage));
            return message.getText().contains("You added");
        } catch (Exception e) {
            return false;
        }
    }
}
