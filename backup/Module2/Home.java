package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
            WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
            logout_button.click();

            // Wait for Logout to Complete
            Thread.sleep(3000);

            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    /*
     * Returns Boolean if searching for the given product name occurs without any
     * errors
     */
    public Boolean searchForProduct(String product) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Clear the contents of the search box and Enter the product name in the search
            // box

            WebElement search=driver.findElement(By.xpath("//*[@id='root']/div/div/div[1]/div[2]/div/input"));
            search.clear();
            search.sendKeys(product);
            Thread.sleep(2000);

            // WebElement search_button = driver.findElement(By.className("button"));
            // login_button.click();

            return true;
        } catch (Exception e) {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    /*
     * Returns Array of Web Elements that are search results and return the same
     */
    public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = driver.findElements(By.xpath("//*[@id='root']/div/div/div[3]/div/div[2]/div/div"));
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Find all webelements corresponding to the card content section of each of
            // search results
            
            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Check the presence of "No products found" text in the web page. Assign status
            // = true if the element is *displayed* else set status = false
           status=driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div/div[2]/div/h4")).isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if add product to cart is successful
     */
    public Boolean addProductToCart(String productName) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through each product on the page to find the WebElement corresponding
             * to the matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             * 
            */
            List<WebElement> elements=driver.findElements(By.className("css-sycj1h"));
            for(WebElement el:elements){
                if(productName.contains(el.findElement(By.className("css-yg30e6")).getText())){
                    el.findElement(By.tagName("button")).click();
                    return true;
                }
            }


            System.out.println("Unable to find the given product");
            return false;
        } catch (Exception e) {
            System.out.println("Exception while performing add to cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting the status of clicking on the checkout button
     */
    public Boolean clickCheckout() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find and click on the the Checkout button
            WebElement element=driver.findElement(By.className("css-177pwqq"));
            element.click();
            Thread.sleep(2000);

            return status;
        } catch (Exception e) {
            System.out.println("Exception while clicking on Checkout: " + e.getMessage());
            return status;
        }
    }

    /*
     * Return Boolean denoting the status of change quantity of product in cart
     * operation
     */
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 06: MILESTONE 5

            // Find the item on the cart with the matching productName
            List<WebElement> list=driver.findElements(By.className("css-zgtx0t"));
            int curr;
            for(WebElement el:list){
                if(productName.equals(el.findElement(By.xpath(".//div[2]/div[1]")).getText())){
                    String now=el.findElement(By.className("css-olyig7")).getText();
                    curr=Integer.parseInt(now);
                    while(curr!=quantity){
                        if(curr<quantity){
                            el.findElements(By.tagName("button")).get(1).click();
                            Thread.sleep(1000);
                            curr++;
                        }
                        else{
                            el.findElements(By.tagName("button")).get(0).click();
                            Thread.sleep(1000);
                            curr--;
                        }
                    }

                }
            }
            // Increment or decrement the quantity of the matching product until the current
            // quantity is reached (Note: Keep a look out when then input quantity is 0,
            // here we need to remove the item completely from the cart)


            return false;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println("exception occurred when updating cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6

            // Get all the cart items as an array of webelements
            List<WebElement> cartContent=driver.findElements(By.className("css-zgtx0t"));
            List<String> actualContent=new ArrayList<>();

            for(WebElement el:cartContent){
                actualContent.add(el.findElement(By.className("css-1gjj37g")).getText());

            }
            for(String str:expectedCartContents){
                if(actualContent.contains(str)){
                    return false;
                }
            }

            // Iterate through expectedCartContents and check if item with matching product
            // name is present in the cart


            return true;

        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}
