package org.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By searchInput = By.name("q");
    private By productLinkMenHoodies = By.xpath("//a[normalize-space()='Hoodies and Sweatshirts']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Adjusted wait time
        PageFactory.initElements(driver, this);
    }

    public void navigateToMenHoodiesAndSweatshirts() {
        // More robust navigation, assuming you start from the homepage
        // Hovering over "Men" and then clicking "Hoodies and Sweatshirts" might be required
        // For simplicity, directly navigating to the URL for now based on your original code
        driver.get("https://magento.softwaretestingboard.com/men/tops-men/hoodies-and-sweatshirts-men.html");
        wait.until(ExpectedConditions.urlContains("hoodies-and-sweatshirts-men"));
    }

    public SearchResultsPage performSearch(String searchTerm) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        searchField.sendKeys(searchTerm);
        searchField.submit();
        return new SearchResultsPage(driver);
    }
}