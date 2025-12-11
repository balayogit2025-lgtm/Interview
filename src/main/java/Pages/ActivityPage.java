package Pages;

import Elements.LoginPageElements;
import Utils.CommonUtil;
import Utils.TestLogger;
import Utils.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ActivityPage {
    private WebDriver driver;
    private CommonUtil commonUtil;
    private WaitHelper waitHelper;
    private static final Logger logger = LogManager.getLogger(ActivityPage.class);
    private  LoginPageElements loginPageElements;

    public ActivityPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
        commonUtil = new CommonUtil(driver);
        loginPageElements = new LoginPageElements(driver);
        this.waitHelper = new WaitHelper(driver);
        logger.info("Activity page initialized");
    }

    public void navigateToActivityPage() {
        TestLogger.info("Navigating to Activity page");
        WebElement activityLink = waitHelper.waitForElementClickable(loginPageElements.activityPage);
        activityLink.click();
        TestLogger.success("Navigated to Activity page successfully");
    }

    public String totalActivityCount() throws InterruptedException {
        TestLogger.info("Getting total activity count");
        navigateToActivityPage();
        commonUtil.clickLastPageButton();
        return commonUtil.getLastRowFirstColumnValue();

    }

}
