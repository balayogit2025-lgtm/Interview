package Pages;

import Elements.DashboardPageElements;
import Utils.CommonUtil;
import Utils.TestLogger;
import Utils.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class DashboardPage {

    private WebDriver driver;
    private DashboardPageElements elements;
    private WaitHelper waitHelper;
    private static final Logger logger = LogManager.getLogger(DashboardPage.class);
private CommonUtil commonUtil;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.elements = new DashboardPageElements();
        this.waitHelper = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
        this.commonUtil = new CommonUtil(driver);     // ✅ Initialize & pass driver
        PageFactory.initElements(driver, elements);
        logger.info("Dashboard initialized");
    }



    /**
     * Get breadcrumb home text
     * @return Breadcrumb text
     */
    public String getBreadcrumbHeaderText() {
        TestLogger.info("Getting breadcrumb home text");
        WebElement breadcrumbHome = waitHelper.waitForElementVisible( elements. dashboardBreadcrumpHeaderTitle);
        String text = breadcrumbHome.getText();
        TestLogger.success("Breadcrumb home text: " + text);
        return text;
    }

    public String getBreadcrumbSubText() {
        TestLogger.info("Getting breadcrumb sub text");
        WebElement breadcrumbHome = waitHelper.waitForElementVisible( elements.dashboardBreadcrumpHeaderSubtitle);
        String text = breadcrumbHome.getText();
        TestLogger.success("Breadcrumb sub text: " + text);
        return text;
    }


    /**
     * Get user greeting text
     * @return Greeting text
     */
    public String getUserGreeting() {
        TestLogger.info("Getting user greeting text");
        WebElement userGreeting = waitHelper.waitForElementVisible((By) elements.greetingText);
        String greeting = userGreeting.getText();
        TestLogger.success("User greeting: " + greeting);
        return greeting;
    }

    /**
     * Get current date displayed
     * @return Date text
     */
    public String getCurrentDate() {
        TestLogger.info("Getting current date");
        WebElement currentDate = waitHelper.waitForElementVisible( elements.headerDate);
        String date = currentDate.getText();
        TestLogger.success("Current date: " + date);
        return date;
    }

    /**
     * Get current time displayed
     * @return Time text
     */
    public String getCurrentTime() {
        TestLogger.info("Getting current time");
        WebElement currentTime = waitHelper.waitForElementVisible((By) elements.headerTime);
        String time = currentTime.getText();
        TestLogger.success("Current time: " + time);
        return time;
    }

    public Boolean isActivityCardDisplayed() {
        TestLogger.info("Activity card displayed or not");
        WebElement activityCard = waitHelper.waitForElementVisible((By) elements.cardActivityTitle);
        Boolean activityCardDisplayed = activityCard.isDisplayed();
        TestLogger.success("activity card is displayed: " + activityCardDisplayed);
        return activityCardDisplayed;
    }

    public String getActivityCardTitle() {
        TestLogger.info("Getting activity card title");
        String titleText = commonUtil.getTitleText((By) elements.cardActivityTitle,"Activity") ;
        TestLogger.success("activity card title: " + titleText);
        return titleText;
    }

    public String getActivityCardCount() {
        TestLogger.info("Getting activity card count");
        WebElement activityCardCount = waitHelper.waitForElementVisible(elements.cardActivityCount);
        String count = activityCardCount.getText();
        TestLogger.success("Activity card count: " + count);
        return count;
    }

    public Boolean isRolesCardDisplayed() {
        TestLogger.info("Roles card displayed or not");
        WebElement rolesCard = waitHelper.waitForElementVisible((By) elements.cardTotalRolesTitle);
        Boolean roleCardDisplayed = rolesCard.isDisplayed();
        TestLogger.success("role card is displayed: " + roleCardDisplayed);
        return roleCardDisplayed;
    }

    public String getRolesCardTitle() {
        TestLogger.info("Getting roles card title");
        String titleText = commonUtil.getTitleText((By) elements.cardTotalRolesTitle,"Total Roles") ;
        TestLogger.success("role card title is displayed: " + titleText);
        return titleText;
    }

    public String getRolesCardCount() {
        TestLogger.info("Getting roles card count");
        WebElement rolesCardCount = waitHelper.waitForElementVisible(elements.cardTotalRolesCount);
        String count = rolesCardCount.getText();
        TestLogger.success("Roles card count: " + count);
        return count;
    }

    public Boolean isTotalUserCardDisplayed() {
        TestLogger.info("Total Users card displayed or not");
        WebElement totalUserCard = waitHelper.waitForElementVisible((By) elements.cardTotalUsersTitle);
        Boolean totalUserCardDisplayed = totalUserCard.isDisplayed();
        TestLogger.success("Total users card is displayed: " + totalUserCardDisplayed);
        return totalUserCardDisplayed;
    }

    public String getTotalUserCardTitle() {
        TestLogger.info("Getting total users card title");
        String titleText = commonUtil.getTitleText((By) elements.cardTotalUsersTitle, "Total Users");
        TestLogger.success("Total users card is displayed: " + titleText);
        return titleText;
    }

    public String getTotalUsersCardCount() {
        TestLogger.info("Getting total users card count");
        WebElement totalUsersCardCount = waitHelper.waitForElementVisible(elements.cardTotalUsersCount);
        String count = totalUsersCardCount.getText();
        TestLogger.success("Total users card count: " + count);
        return count;
    }

    public Boolean isWebUserCardDisplayed() {
        TestLogger.info("Web User card displayed or not");
        WebElement webUserCard = waitHelper.waitForElementVisible((By) elements.cardWebUsersTitle);
        Boolean webUserCardDisplayed = webUserCard.isDisplayed();
        TestLogger.success("Web user card is displayed: " + webUserCardDisplayed);
        return webUserCardDisplayed;
    }

    public String getWebUserCardTitle() {
        TestLogger.info("Getting web users card title");
        String titleText = commonUtil.getTitleText((By) elements.cardWebUsersTitle, "Total Users");
        TestLogger.success("Total web card is displayed: " + titleText);
        return titleText;
    }


    public Boolean isKeySafeUserCardDisplayed() {
        TestLogger.info("Key Safe Users card displayed or not");
        WebElement keyUserCard = waitHelper.waitForElementVisible((By) elements.cardKeySafeUsersTitle);
        Boolean keySafeUserCardDisplayed = keyUserCard.isDisplayed();
        TestLogger.success("KeySafe users card is displayed: " + keySafeUserCardDisplayed);
        return keySafeUserCardDisplayed;
    }
    public String getTotalKeySafeCardTitle() {
        TestLogger.info("Getting key safe users card title");
        String titleText = commonUtil.getTitleText((By) elements.cardKeySafeUsersTitle, "Total Users");
        TestLogger.success("Total key safe users card is displayed: " + titleText);
        return titleText;
    }


    public Boolean isKeyBoxChartTitleDisplayed() {
        TestLogger.info("Key Box chart title displayed or not");
        WebElement keyBoxChartTitle = waitHelper.waitForElementVisible((By) elements.keyBoxChartTitle);
        Boolean keyBocChartTitleDisplayed = keyBoxChartTitle.isDisplayed();
        TestLogger.success("Key Box chart is displayed: " + keyBocChartTitleDisplayed);
        return keyBocChartTitleDisplayed;
    }

    public String getKeyBoxChartTitle() {
        TestLogger.info("Getting key box chart title");
        String titleText = commonUtil.getTitleText((By) elements.keyBoxChartTitle, "Total Users");
        TestLogger.success("Total key box chart title is displayed: " + titleText);
        return titleText;
    }

    public Boolean isUsersChartTitleDisplayed() {
        TestLogger.info("Users chart title displayed or not");
        WebElement usersChartTitle = waitHelper.waitForElementVisible((By) elements.usersChartTitle);
        Boolean usersChartTitleDisplayed = usersChartTitle.isDisplayed();
        TestLogger.success("Users chart is displayed: " + usersChartTitleDisplayed);
        return usersChartTitleDisplayed;
    }

    public String getUsersChartTitle() {
        TestLogger.info("Getting Users chart title");
        String titleText = commonUtil.getTitleText((By) elements.usersChartTitle, "Total Users");
        TestLogger.success("Total Users chart title is displayed: " + titleText);
        return titleText;
    }

    public Boolean isActivitiesChartTitleDisplayed() {
        TestLogger.info("Activites chart title displayed or not");
        WebElement activitiesChartTitle = waitHelper.waitForElementVisible((By) elements.activitiesChartTitle);
        Boolean activitiesChartTitleDisplayed = activitiesChartTitle.isDisplayed();
        TestLogger.success("Activities chart is displayed: " + activitiesChartTitleDisplayed);
        return activitiesChartTitleDisplayed;
    }
    public String getActivitiesChartTitle() {
        TestLogger.info("Getting Activites chart title");
        String titleText = commonUtil.getTitleText((By) elements.activitiesChartTitle, "Total Users");
        TestLogger.success("Total Activites chart title is displayed: " + titleText);
        return titleText;
    }

    public Boolean isActivityCardCountDisplayed()
    {
        TestLogger.info("Activity card count is displayed or not");
        WebElement activitiesChartCount = waitHelper.waitForElementVisible((By) elements.cardActivityCount);
       Boolean count = activitiesChartCount.isDisplayed();
        TestLogger.info("Activity card count is displayed :" + count);
        return count;
    }

    public Boolean isTotalUsersCardCountDisplayed()
    {
        TestLogger.info("Total Users card count is displayed or not");
        WebElement totalUsersChartCount = waitHelper.waitForElementVisible((By) elements.cardTotalUsersCount);
        Boolean count = totalUsersChartCount.isDisplayed();
        TestLogger.info("Total Users card count is displayed :" + count);
        return count;
    }

    public Boolean isTotalRolesCardCountDisplayed()
    {
        TestLogger.info("Total Roles card count is displayed or not");
        WebElement totalRolesChartCount = waitHelper.waitForElementVisible((By) elements.cardTotalRolesCount);
        Boolean count = totalRolesChartCount.isDisplayed();
        TestLogger.info("Total Roles card count is displayed :" + count);
        return count;
    }


    public Boolean isWebUsersCardCountDisplayed()
    {
        TestLogger.info("Web Users card count is displayed or not");
        WebElement webUsersCardCount = waitHelper.waitForElementVisible((By) elements.cardWebUsersCount);
        Boolean count = webUsersCardCount.isDisplayed();
        TestLogger.info("Web Users card count is displayed :" + count);
        return count;
    }

    public String getWebUserCount() {
        TestLogger.info("Getting web user count");
        WebElement webUserCount = waitHelper.waitForElementVisible(elements.cardWebUsersCount);
        String count = webUserCount.getText();
        TestLogger.success("Web user count: " + count);
        return count;
    }

    public Boolean isKeySafeUsersCardCountDisplayed()
    {
        TestLogger.info("Key Safe Users card count is displayed or not");
        WebElement keySafeUsersCardCount = waitHelper.waitForElementVisible((By) elements.cardKeySafeUsersCount);
        Boolean count = keySafeUsersCardCount.isDisplayed();
        TestLogger.info("Key Safe Users card count is displayed :" + count);
        return count;
    }

    public Boolean isKeyBoxChartDisplayed()
    {
        TestLogger.info("Key box chart is displayed or not");
        WebElement keyBoxChart = waitHelper.waitForElementVisible((By) elements.keyBoxChartContainer);
        Boolean isDisplayed = keyBoxChart.isDisplayed();
        TestLogger.info("Key Box chart is displayed :" + isDisplayed);
        return isDisplayed;
    }

    public Boolean isUsersChartDisplayed()
    {
        TestLogger.info("Users chart is displayed or not");
        WebElement usersChart = waitHelper.waitForElementVisible((By) elements.usersChartContainer);
        Boolean isDisplayed = usersChart.isDisplayed();
        TestLogger.info("Users chart is displayed :" + isDisplayed);
        return isDisplayed;
    }


    public Boolean isActivitiesChartDisplayed()
    {
        TestLogger.info("Activites chart is displayed or not");
        WebElement activitiedChart = waitHelper.waitForElementVisible((By) elements.activitiesChartContainer);
        Boolean isDisplayed = activitiedChart.isDisplayed();
        TestLogger.info("Activites chart is displayed :" + isDisplayed);
        return isDisplayed;
    }

    public Boolean isKeyBoxChartCenterValueDisplayed()
    {
        TestLogger.info("Key box chart center value is displayed or not");
        WebElement keyBoxChartCenterValue = waitHelper.waitForElementVisible((By) elements.keyBoxChartCenterValue);
        Boolean isDisplayed = keyBoxChartCenterValue.isDisplayed();
        TestLogger.info("Key Box chart center value displayed :" + isDisplayed);
        return isDisplayed;
    }

    public Boolean isUsersChartCenterValueDisplayed()
    {
        TestLogger.info("Users chart Center Value is displayed or not");
        WebElement usersChartCenterValue = waitHelper.waitForElementVisible((By) elements.usersChartCenterValue);
        Boolean isDisplayed = usersChartCenterValue.isDisplayed();
        TestLogger.info("Users chart center value displayed :" + isDisplayed);
        return isDisplayed;
    }


    public Boolean isActivitiesChartCenterValueDisplayed()
    {
        TestLogger.info("Activites chart center value is displayed or not");
        WebElement activitiesChartCenterValue = waitHelper.waitForElementVisible((By) elements.activitiesChartCenterValue);
        Boolean isDisplayed = activitiesChartCenterValue.isDisplayed();
        TestLogger.info("Activites chart center value displayed :" + isDisplayed);
        return isDisplayed;
    }


    public Boolean isKeyboxLegendDisplayed()
    {
        TestLogger.info("Key Box chart Legend is displayed or not");
        WebElement registered = waitHelper.waitForElementVisible((By) elements.keyBoxLegendRegistered);
        Boolean isDisplayedRegistered = registered.isDisplayed();
        WebElement unregistered = waitHelper.waitForElementVisible((By) elements.keyBoxLegendUnregistered);
        Boolean isDisplayedUnRegistered = unregistered.isDisplayed();
        TestLogger.info("KeyBox chart Registered legend displayed :" + isDisplayedRegistered);
        TestLogger.info("KeyBox chart Unregistered legend displayed :" + isDisplayedUnRegistered);
        return isDisplayedUnRegistered&&isDisplayedRegistered  ;
    }

    public String keyboxRegisteredLegendText()
    {
        TestLogger.info("Key Box chart registered Legend text is displayed or not");
        WebElement registered = waitHelper.waitForElementVisible((By) elements.keyBoxLegendRegistered);
        String registeredText = registered.getText().trim();
        TestLogger.info("KeyBox chart Registered legend text :" + registeredText);
        return registeredText;
    }

    public String keyboxUnRegisteredLegendText()
    {
        TestLogger.info("Key Box chart Unregistered Legend text is displayed or not");
        WebElement unregistered = waitHelper.waitForElementVisible((By) elements.keyBoxLegendUnregistered);
        String unregisteredText = unregistered.getText().trim();
        TestLogger.info("KeyBox chart UnRegistered legend text :" + unregisteredText);
        return unregisteredText;
    }


    public Boolean isUserLegendDisplayed()
    {
        TestLogger.info("Users chart Legend is displayed or not");
        WebElement active = waitHelper.waitForElementVisible((By) elements.usersLegendActive);
        Boolean isDisplayedActive = active.isDisplayed();
        WebElement inActive = waitHelper.waitForElementVisible((By) elements.usersLegendInactive);
        Boolean isDisplayedInActive = inActive.isDisplayed();
        TestLogger.info("Users chart Active legend displayed :" + isDisplayedActive);
        TestLogger.info("Users chart Unregistered legend displayed :" + isDisplayedInActive);
        return isDisplayedActive&&isDisplayedInActive  ;
    }


    public String usersActiveLegendText()
    {
        TestLogger.info("Users chart Active Legend text is displayed or not");
        WebElement active = waitHelper.waitForElementVisible((By) elements.usersLegendActive);
        String activeText = active.getText().trim();
        TestLogger.info("Users chart Active legend text :" + activeText);
        return activeText;
    }

    public String usersInActiveLegendText()
    {
        TestLogger.info("Users chart InActive Legend text is displayed or not");
        WebElement inactive = waitHelper.waitForElementVisible((By) elements.usersLegendInactive);
        String inactiveText = inactive.getText().trim();
        TestLogger.info("Users chart inActive legend text :" + inactiveText);
        return inactiveText;
    }




    public Boolean isActivitiesLegendDisplayed()
    {
        TestLogger.info("Activities chart Legend is displayed or not");
        WebElement active = waitHelper.waitForElementVisible((By) elements.activitiesLegendActive);
        Boolean isDisplayedActive = active.isDisplayed();
        WebElement inActive = waitHelper.waitForElementVisible((By) elements.activitiesLegendInactive);
        Boolean isDisplayedInActive = inActive.isDisplayed();
        TestLogger.info("Activities chart Active legend displayed :" + isDisplayedActive);
        TestLogger.info("Activities chart Unregistered legend displayed :" + isDisplayedInActive);
        return isDisplayedActive&&isDisplayedInActive  ;
    }


    public String activitiesActiveLegendText()
    {
        TestLogger.info("Activities chart Active Legend text is displayed or not");
        WebElement active = waitHelper.waitForElementVisible((By) elements.activitiesLegendActive);
        String activeText = active.getText().trim();
        TestLogger.info("Activities chart Active legend text :" + activeText);
        return activeText;
    }

    public String activitiesInActiveLegendText()
    {
        TestLogger.info("Activities chart InActive Legend text is displayed or not");
        WebElement inactive = waitHelper.waitForElementVisible((By) elements.activitiesLegendInactive);
        String inactiveText = inactive.getText().trim();
        TestLogger.info("Activities chart inActive legend text :" + inactiveText);
        return inactiveText;
    }

}
