package org.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By productImage = By.cssSelector(".product-item-photo .product-image-photo");
    private By searchInput = By.name("q"); 
    private By searchMessageLocator = By.cssSelector(".message.notice");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public ProductPage selectFirstProduct() {
        WebElement firstProductImage = wait.until(ExpectedConditions.elementToBeClickable(productImage));
        firstProductImage.click();
        return new ProductPage(driver);
    }

    public void reSearch(String searchTerm) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        searchField.clear();
        searchField.sendKeys(searchTerm);
        searchField.submit();
    }

    public String getSearchResultsMessageText() {
        try {
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(searchMessageLocator));
            return message.getText().trim();
        } catch (Exception e) {
            System.out.println("No search results message found or visible with locator: " + searchMessageLocator.toString());
            return null;
        }
    }

    public boolean isNoResultsMessageDisplayed() {
        String messageText = getSearchResultsMessageText();
        return messageText != null && messageText.contains("Your search returned no results.");
    }

    public boolean isMinimumQueryLengthMessageDisplayed() {
        String messageText = getSearchResultsMessageText();
        return messageText != null && messageText.contains("Minimum Search query length is 3");
    }

    public boolean isAnySearchMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchMessageLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
