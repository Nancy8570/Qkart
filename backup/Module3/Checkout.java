package QKART_SANITY_LOGIN.Module1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/checkout";

    public Checkout(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckout() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    /*
     * Return Boolean denoting the status of adding a new address
     */
    public Boolean addNewAddress(String addresString) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Click on the "Add new address" button, enter the addressString in the address text
             * box and click on the "ADD" button to save the address
             */
            WebElement element = driver.findElement(By.className("css-rfvjbl"));
            element.click();
            Thread.sleep(1000);
            WebElement element2 = driver.findElement(By.className("css-u36398"));
            element2.sendKeys(addresString);
            WebElement element3 = driver.findElement(
                    By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div[2]/button[1]"));
            element3.click();
            // Thread.sleep(2000);

            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred while entering address: " + e.getMessage());
            return false;

        }
    }

    /*
     * Return Boolean denoting the status of selecting an available address
     */
    public Boolean selectAddress(String addressToSelect) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through all the address boxes to find the address box with matching text,
             * addressToSelect and click on it
             */
            List<WebElement> elements = driver.findElements(
                    By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[1]/div/div[1]"));
            for (WebElement el : elements) {
                System.out.println(el.findElement(By.xpath(".//p")).getText());
                System.out.println(addressToSelect);
                if(addressToSelect.replaceAll(" ", "").contains(el.findElement(By.xpath(".//p")).getText().replaceAll(" ", ""))){
                //
                el.findElement(By.xpath("./span/input")).click();
                Thread.sleep(1000);
                return true;
                }
            }
            // Thread.sleep(2000);
            System.out.println("Unable to find the given address");
            return false;
        } catch (Exception e) {
            System.out.println(
                    "Exception Occurred while selecting the given address: " + e.getMessage());
            return false;
        }

    }

    /*
     * Return Boolean denoting the status of place order action
     */
    public Boolean placeOrder() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find the "PLACE ORDER" button and click on it
            WebElement element = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/button[2]"));
            element.click();
            Thread.sleep(1000);
            return false;

        } catch (Exception e) {
            System.out.println("Exception while clicking on PLACE ORDER: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the insufficient balance message is displayed
     */
    public Boolean verifyInsufficientBalanceMessage() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 08: MILESTONE 7
            WebElement element=driver.findElement(By.id("notistack-snackbar"));
            System.out.println("hi");
            if(element.isDisplayed() && element.getText().equals("You do not have enough balance in your wallet for this purchase")){
                System.out.println("hi");
                return true;
            }

            return false;
        } catch (Exception e) {
            System.out.println(
                    "Exception while verifying insufficient balance message: " + e.getMessage());
            return false;
        }
    }
}
