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

    // Locators
    private By productImage = By.cssSelector(".product-item-photo .product-image-photo");
    private By searchInput = By.name("q"); // Search box is often persistent
    // *** UPDATED LOCATOR: Targeting .message.notice as requested ***
    private By searchMessageLocator = By.cssSelector(".message.notice"); // This is often used for "no results" and other info messages

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

    /**
     * Gets the text of the search results message (info or error).
     * @return The message text if found, null otherwise.
     */
    public String getSearchResultsMessageText() {
        try {
            // Wait for ANY message that matches the locator
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(searchMessageLocator));
            return message.getText().trim();
        } catch (Exception e) {
            System.out.println("No search results message found or visible with locator: " + searchMessageLocator.toString());
            return null;
        }
    }

    /**
     * Checks if the "Your search returned no results." message is displayed.
     * @return true if the message is displayed, false otherwise.
     */
    public boolean isNoResultsMessageDisplayed() {
        String messageText = getSearchResultsMessageText();
        return messageText != null && messageText.contains("Your search returned no results.");
    }

    /**
     * Checks if the "Minimum Search query length is 3" message is displayed.
     * @return true if the message is displayed, false otherwise.
     */
    public boolean isMinimumQueryLengthMessageDisplayed() {
        String messageText = getSearchResultsMessageText();
        return messageText != null && messageText.contains("Minimum Search query length is 3");
    }

    /**
     * Checks if any search-related message (like no results or query length) is displayed.
     * @return true if any message is displayed, false otherwise.
     */
    public boolean isAnySearchMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchMessageLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}