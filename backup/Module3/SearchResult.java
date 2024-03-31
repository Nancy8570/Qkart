package QKART_SANITY_LOGIN.Module1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a
     * search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
        WebElement el=parentElement.findElement(By.className("css-yg30e6"));
        titleOfSearchResult=el.getText();

        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it
            WebElement element=parentElement.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div/div/div[1]/button"));
            element.click();
            Thread.sleep(1000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            WebElement size=parentElement.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div/div/div[1]/button"));
            status= size.getText().equals("SIZE CHART");
            /*
             * Check if the size chart element exists. If it exists, check if the text of
             * the element is "SIZE CHART". If the text "SIZE CHART" matches for the
             * element, set status = true , else set to false
             */
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the
     * expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders, List<List<String>> expectedTableBody,
            WebDriver driver) {
        Boolean status = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {

            WebElement sizeChart = driver.findElement(By.className("MuiDialog-paperScrollPaper"));           

            WebElement table = sizeChart.findElement(By.tagName("table"));
            

            List<WebElement> tableHeader = table.findElement(By.tagName("thead")).findElements(By.tagName("th"));
            String tempHeaderValue;

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
                * Locate the table element when the size chart modal is open
                * 
                * Validate that the contents of expectedTableHeaders is present as the table
                * header in the same order
                * 
                * Validate that the contents of expectedTableBody are present in the table body
                * in the same order
                */

            System.out.println(tableHeader.size());

        
            for(int i=0;i<expectedTableHeaders.size();i++){
                tempHeaderValue = tableHeader.get(i).getText();


                if(!expectedTableHeaders.get(i).equals(tempHeaderValue)){
                    System.out.println("Failure in header comparision "+expectedTableHeaders.get(i));  
                    
                    
                    status = false;
                }
                System.out.println(tempHeaderValue);
                

            }
            

            Thread.sleep(1000);


            List<WebElement> tableBodyRows = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

            List<WebElement> tempBodyRow;


            for(int i=0;i<expectedTableBody.size();i++){
                tempBodyRow = tableBodyRows.get(i).findElements(By.tagName("td"));

                for(int j=0;j<expectedTableBody.get(i).size();j++){
                    tempHeaderValue = tempBodyRow.get(j).getText();




                    Thread.sleep(1000);

                    if(!expectedTableBody.get(i).get(j).equals(tempHeaderValue)){                        
                        status= false;
                    }
                }
            }

            return status;

        } catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // If the size dropdown exists and is displayed return true, else return false
            WebElement sizeDropDown = parentElement.findElement(By.xpath("//*[@id='uncontrolled-native']"));
            // for(int i=0;i<4;i++){
            //     System.out.println("hii");
            // }
            
            if(sizeDropDown.isDisplayed()){
                status = true;
            }
            return status;

        } catch (Exception e) {
            return status;
        }
    }
}