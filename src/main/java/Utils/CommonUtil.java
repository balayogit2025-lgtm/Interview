package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CommonUtil extends RuntimeException {

    private WebDriver driver;
private WaitHelper waitHelper;

    public CommonUtil(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);

    }

    public String getTitleText(By locator, String titleName) {
        TestLogger.info("Fetching text for: " + titleName);
        WebElement titleElement = waitHelper.waitForElementVisible(locator);
        String text = titleElement.getText().trim();
        TestLogger.success(titleName + " text fetched successfully: " + text);
        return text;
    }

    public Boolean isDisplayedField(By locator) {
        TestLogger.info("Verify the field is displayed or not");
        WebElement fieldElement = waitHelper.waitForElementVisible(locator);
        Boolean text = fieldElement.isDisplayed();
        TestLogger.success(fieldElement + " text fetched successfully: " + text);
        return text;
    }

    public void clickLastPageButton() {
        TestLogger.info("Clicking on the last page button");
        WebElement lastPageButton = waitHelper.waitForElementClickable(By.xpath("//a[@aria-label='Go to last page']"));
        lastPageButton.click();
        TestLogger.success("Clicked on the last page button successfully");
    }

    public String getLastRowFirstColumnValue() throws InterruptedException {
        TestLogger.info("Getting the first column value of the last row in the grid");
        Thread.sleep(1000);
        WebElement lastRow = waitHelper.waitForElementVisible(By.xpath("//table/tbody/tr[last()]"));
        WebElement firstColumn = lastRow.findElement(By.xpath("./td[1]"));
        String value = firstColumn.getText();
        TestLogger.success("The first column value of the last row is: " + value);
        return value;
    }

    
}
