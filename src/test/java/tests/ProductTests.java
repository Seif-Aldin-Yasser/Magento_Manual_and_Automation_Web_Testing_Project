package tests;

import org.ecommerce.pages.HomePage;
import org.ecommerce.pages.ProductPage;
import org.ecommerce.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTests extends BaseTest {

    @Test(priority = 1, description = "Verify product search with full name and add to cart with specific options")
    public void testProductSearchWithFullNameAndAddToCart() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToMenHoodiesAndSweatshirts(); 

  SearchResultsPage searchResultsPage = homePage.performSearch("Mars HeatTech™ Pullover");

  ProductPage productPage = searchResultsPage.selectFirstProduct();

        
        productPage.selectSize("M");
        productPage.selectColor("Black");
        productPage.clickAddToCart();

        Assert.assertTrue(productPage.isProductAddedToCartMessageDisplayed(), "Product added to cart message not displayed!");
    }

    @Test(priority = 2, description = "Verify product search with 3 characters and add to cart")
    public void testProductSearchWith3CharsAndAddToCart() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToMenHoodiesAndSweatshirts();
        SearchResultsPage searchResultsPage = homePage.performSearch("Mar");
        ProductPage productPage = searchResultsPage.selectFirstProduct();

        productPage.selectSize("M");
        productPage.selectColor("Black");
        productPage.clickAddToCart();

        Assert.assertTrue(productPage.isProductAddedToCartMessageDisplayed(), "Product added to cart message not displayed!");
    }

    @Test(priority = 3, description = "Verify invalid product search with 2 characters")
    public void testInvalidProductSearchWith2Chars() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToMenHoodiesAndSweatshirts();

        SearchResultsPage searchResultsPage = homePage.performSearch("Ma");

        Assert.assertTrue(searchResultsPage.isMinimumQueryLengthMessageDisplayed(),
                "Expected 'Minimum Search query length is 3' message not displayed for 2-character search!");
    }

}
