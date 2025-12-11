package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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


}
