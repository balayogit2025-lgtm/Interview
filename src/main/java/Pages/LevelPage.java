package Pages;

import Elements.LevelPageElements;
import Utils.CommonUtil;
import Utils.TestLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class LevelPage {

    private WebDriver driver;
    private LevelPageElements elements;
    private CommonUtil commonUtil;
    private static final Logger logger = LogManager.getLogger(LevelPage.class);

    public LevelPage(WebDriver driver) {
        this.driver = driver;
        this.elements = new LevelPageElements(driver);
        this.commonUtil = new CommonUtil(driver);
        logger.info("LevelPage initialized");
    }

    public LevelPage navigateToLevelPage() {
        try {
             WebElement level = elements.getLevel();
             level.click();
            logger.info("Navigated to Level Page");
        } catch (Exception e) {
            logger.error("Failed to navigate to Level Page: " + e.getMessage());
            throw new RuntimeException("Unable to navigate to Level Page: ", e);
        }
        return this;
    }

    public boolean isAddRegionButtonDisplayed() {
        try {
            return elements.getAddRegion().isDisplayed() && elements.getAddRegion().isEnabled();
        } catch (Exception e) {
            logger.debug("Add Region button not displayed: " + e.getMessage());
            return false;
        }
    }

    public LevelPage clickaddregion() {
        try {
            WebElement Addregion = elements.getAddRegion();
            Addregion.click();
            logger.info("Clicked the add region button");
        } catch (Exception e) {
            logger.error("Failed to click the add region button: " + e.getMessage());
            throw new RuntimeException("Unable to click the add region button: ", e);
        }
        return this;
    }

    public LevelPage enterregionname(String regionname) {
        try {
            WebElement Regionname = elements.getRegionname();
            Regionname.sendKeys(regionname);
            logger.info("Entered the region name");
        } catch (Exception e) {
            logger.error("Failed to Enter the region name: " + e.getMessage());
            throw new RuntimeException("Unable to Enter the region name: ", e);
        }
        return this;

    }

    public LevelPage clicksubmitbutton() {
        try {
            WebElement Submitbutton = elements.getSubmitbutton();
            Submitbutton.click();
            logger.info("Clicked the submit button");
        } catch (Exception e) {
            logger.error("Failed to Click the submit button: " + e.getMessage());
            throw new RuntimeException("Unable to Click the submit button: ", e);
        }
        return this;
    }

    public LevelPage addnewregion() {
    navigateToLevelPage().clickaddregion().enterregionname("N REG").clicksubmitbutton();
    return this;
}
    public LevelPage clicknewlycreatedregion() {
        try {
            WebElement Newlycreatedregion = elements.getNewlycreatedregionname();
            Newlycreatedregion.click();
            logger.info("Clicked the Newly created Region name");
        } catch (Exception e) {
            logger.error("Failed to Click the Newly created Region name: " + e.getMessage());
            throw new RuntimeException("Unable to Click the Newly created Region name: ", e);
        }
        return this;
    }
    public LevelPage clicknewlycreatedstate() {
        try {
            WebElement Newlycreatedstate = elements.getNewlycreatedstatename();
            Newlycreatedstate.click();
            logger.info("Clicked the Newly created state name");
        } catch (Exception e) {
            logger.error("Failed to Click the Newly created state name: " + e.getMessage());
            throw new RuntimeException("Unable to Click the Newly created state name: ", e);
        }
        return this;
    }
    public LevelPage clicknewlycreatedterritory() {
        try {
            WebElement NewlycreatedTerritory = elements.getNewlycreatedTerritoryname();
            NewlycreatedTerritory.click();
            logger.info("Clicked the Newly created territory name");
        } catch (Exception e) {
            logger.error("Failed to Click the Newly created territory name: " + e.getMessage());
            throw new RuntimeException("Unable to Click the Newly created territory name: ", e);
        }
        return this;
    }
    public LevelPage clicknewlycreatedcluster() {
        try {
            WebElement Newlycreatedcluster = elements.getNewlycreatedclustername();
            Newlycreatedcluster.click();
            logger.info("Clicked the Newly created cluster name");
        } catch (Exception e) {
            logger.error("Failed to Click the Newly created cluster name: " + e.getMessage());
            throw new RuntimeException("Unable to Click the Newly created cluster name: ", e);
        }
        return this;
    }
    public LevelPage clickaddstatebutton() {
        try {
            WebElement Addstatebutton = elements.getAddstate();
            Addstatebutton.click();
            logger.info("Clicked the Add state button");
        } catch (Exception e) {
            logger.error("Failed to Click the Add State button: " + e.getMessage());
            throw new RuntimeException("Unable to Click the Add state button: ", e);
        }
        return this;
    }
    public LevelPage enterstatename(String statename ) {
        try {
            WebElement State = elements.getStatename();
            State.sendKeys(statename);
            logger.info("Entered the state name");
        } catch (Exception e) {
            logger.error("Failed to Enter the state name: " + e.getMessage());
            throw new RuntimeException("Unable to Enter the state name: ", e);
        }
        return this;
    }
    public LevelPage addnewstate() {
        navigateToLevelPage().clicknewlycreatedregion().clickaddstatebutton().enterstatename("N STA").clicksubmitbutton();
        return this;
    }
    public boolean isAddStateButtonDisplayed() {
        try {
            return elements.getAddstate().isDisplayed() && elements.getAddstate().isEnabled();
        } catch (Exception e) {
            logger.debug("Add Region button not displayed: " + e.getMessage());
            return false;
        }
    }
    public LevelPage clickaddTerritorybutton() {
        try {
            WebElement addterritory = elements.getAddTerritory();
            addterritory.click();
            logger.info("Clicked the Add Territory button");
        } catch (Exception e) {
            logger.error("Failed to Click the Add Territory button: " + e.getMessage());
            throw new RuntimeException("Unable to Click the Add Territory button: ", e);
        }
        return this;
    }
    public LevelPage enterterritoryname(String Territoryname ) {
        try {
            WebElement territory = elements.getTerritoryname();
            territory.sendKeys(Territoryname);
            logger.info("Entered the Territory name");
        } catch (Exception e) {
            logger.error("Failed to Enter the Territory name: " + e.getMessage());
            throw new RuntimeException("Unable to Enter the Territory name: ", e);
        }
        return this;
    }
    public LevelPage addnewterritory() {
        navigateToLevelPage().clicknewlycreatedregion().clicknewlycreatedstate().clickaddTerritorybutton().enterterritoryname("N TERRI").clicksubmitbutton();
        return this;
    }
    public LevelPage clickaddclusterbutton() {
        try {
            WebElement AddCluster = elements.getAddcluster();
            AddCluster.click();
            logger.info("Clicked the Add cluster button");
        } catch (Exception e) {
            logger.error("Failed to Click the Add Cluster button: " + e.getMessage());
            throw new RuntimeException("Unable to Click the Add Cluster button: ", e);
        }
        return this;
    }
    public LevelPage enterclustername(String clustername ) {
        try {
            WebElement Cluster = elements.getClustername();
            Cluster.sendKeys(clustername);
            logger.info("Entered the cluster name");
        } catch (Exception e) {
            logger.error("Failed to Enter the cluster name: " + e.getMessage());
            throw new RuntimeException("Unable to Enter the cluster name: ", e);
        }
        return this;
    }
    public LevelPage addnewcluster() {
        navigateToLevelPage().clicknewlycreatedregion().clicknewlycreatedstate().clicknewlycreatedterritory().clickaddclusterbutton().enterclustername("N CLUST").clicksubmitbutton();
        return this;
    }
    public LevelPage clickaddlocationbutton() {
        try {
            WebElement Addlocation = elements.getAddLocation();
            Addlocation.click();
            logger.info("Clicked the Add location button");
        } catch (Exception e) {
            logger.error("Failed to Click the Add location button: " + e.getMessage());
            throw new RuntimeException("Unable to Click the Add location button: ", e);
        }
        return this;
    }
    public LevelPage enterlocationname(String locationname ) {
        try {
            WebElement location = elements.getLocationname();
            location.sendKeys(locationname);
            logger.info("Entered the location name");
        } catch (Exception e) {
            logger.error("Failed to Enter the location name: " + e.getMessage());
            throw new RuntimeException("Unable to Enter the location name: ", e);
        }
        return this;
    }
    public LevelPage addnewlocation() {
        navigateToLevelPage().clicknewlycreatedregion().clicknewlycreatedstate().clicknewlycreatedterritory().clicknewlycreatedcluster().clickaddlocationbutton().enterlocationname("N LOC").clicksubmitbutton();
        return this;
    }
    public boolean isAddTerritoryButtonDisplayed() {
        try {
            return elements.getAddTerritory().isDisplayed() && elements.getAddTerritory().isEnabled();
        } catch (Exception e) {
            logger.debug("Add Region button not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean isAddClusterButtonDisplayed() {
        try {
            return elements.getAddcluster().isDisplayed() && elements.getAddcluster().isEnabled();
        } catch (Exception e) {
            logger.debug("Add Region button not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean isAddLocationButtonDisplayed() {
        try {
            return elements.getAddLocation().isDisplayed() && elements.getAddLocation().isEnabled();
        } catch (Exception e) {
            logger.debug("Add Region button not displayed: " + e.getMessage());
            return false;
        }
    }
    public LevelPage clickCancelbutton() {
        try {
            WebElement Cancelbutton = elements.getCancelbutton();
            Cancelbutton.click();
            logger.info("Clicked the cancel button");
        } catch (Exception e) {
            logger.error("Failed to Click the cancel button: " + e.getMessage());
            throw new RuntimeException("Unable to Click the cancel button: ", e);
        }
        return this;
    }
    public LevelPage Cancelbuttoninaddnewregion() {
        navigateToLevelPage().clickaddregion().enterregionname("CANCEL REGION").clickCancelbutton();
        return this;
    }
    public LevelPage Cancelbuttoninaddnewstate() {
        navigateToLevelPage().clicknewlycreatedregion().clickaddstatebutton().enterstatename("CANCEL STATE").clickCancelbutton();
        return this;
    }
    public LevelPage Cancelbuttoninaddnewterritory() {
        navigateToLevelPage().clicknewlycreatedregion().clicknewlycreatedstate();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        clickaddTerritorybutton().enterterritoryname("CANCEL TERRITORY").clickCancelbutton();
        return this;
    }
    public LevelPage Cancelbuttoninaddnewcluster() {
        navigateToLevelPage().clicknewlycreatedregion().clicknewlycreatedstate().clicknewlycreatedterritory();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        clickaddclusterbutton().enterclustername("CANCEL CLUSTER").clickCancelbutton();
        return this;
    }
    public LevelPage Cancelbuttoninaddnewlocation() {
        navigateToLevelPage().clicknewlycreatedregion().clicknewlycreatedstate().clicknewlycreatedterritory().clicknewlycreatedcluster();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        clickaddlocationbutton().enterlocationname("CANCEL LOCATION").clickCancelbutton();
        return this;
    }
    public LevelPage clickManagebutton() {
        try {
            WebElement Managebutton = elements.getManagebutton();
            Managebutton.click();
            logger.info("Clicked the Manage button");
        } catch (Exception e) {
            logger.error("Failed to Click the Manage button: " + e.getMessage());
            throw new RuntimeException("Unable to Click the Manage button: ", e);
        }
        return this;
    }
    public LevelPage clickEditbutton() {
        try {
            WebElement Editbutton = elements.getEditbutton();
            Editbutton.click();
            logger.info("Clicked the Edit button");
        } catch (Exception e) {
            logger.error("Failed to Click the Edit button: " + e.getMessage());
            throw new RuntimeException("Unable to Click the Edit button: ", e);
        }
        return this;
    }
    public LevelPage ClearRegionName() {
        try {
            WebElement Regname = elements.getRegionname();
            Regname.sendKeys(Keys.CONTROL+"a");
            Regname.sendKeys(Keys.DELETE);
            logger.info("Cleared the region name");
        } catch (Exception e) {
            logger.error("Failed to Clear the region name: " + e.getMessage());
            throw new RuntimeException("Unable to Clear the region name: ", e);
        }
        return this;
    }
    public LevelPage ClearStateName() {
        try {
            WebElement Staname = elements.getStatename();
            Staname.sendKeys(Keys.CONTROL+"a");
            Staname.sendKeys(Keys.DELETE);
            logger.info("Cleared the state name");
        } catch (Exception e) {
            logger.error("Failed to Clear the state name: " + e.getMessage());
            throw new RuntimeException("Unable to Clear the state name: ", e);
        }
        return this;
    }
    public LevelPage ClearTerritoryName() {
        try {
            WebElement Tername = elements.getTerritoryname();
            Tername.sendKeys(Keys.CONTROL+"a");
            Tername.sendKeys(Keys.DELETE);
            logger.info("Cleared the territory name");
        } catch (Exception e) {
            logger.error("Failed to Clear the territory name: " + e.getMessage());
            throw new RuntimeException("Unable to Clear the territory name: ", e);
        }
        return this;
    }
    public LevelPage ClearClusterName() {
        try {
            WebElement Clustname = elements.getClustername();
            Clustname.sendKeys(Keys.CONTROL+"a");
            Clustname.sendKeys(Keys.DELETE);
            logger.info("Cleared the cluster name");
        } catch (Exception e) {
            logger.error("Failed to Clear the cluster name: " + e.getMessage());
            throw new RuntimeException("Unable to Clear the cluster name: ", e);
        }
        return this;
    }
    public LevelPage ClearLocationName() {
        try {
            WebElement Locname = elements.getLocationname();
            Locname.sendKeys(Keys.CONTROL+"a");
            Locname.sendKeys(Keys.DELETE);
            logger.info("Cleared the location name");
        } catch (Exception e) {
            logger.error("Failed to Clear the location name: " + e.getMessage());
            throw new RuntimeException("Unable to Clear the location name: ", e);
        }
        return this;
    }
    public LevelPage EditRegion() {
        navigateToLevelPage().clickManagebutton().clickEditbutton().ClearRegionName().enterregionname("E Region").clicksubmitbutton();
        return this;
    }
    public LevelPage Editstate() {
        navigateToLevelPage().clicknewlycreatedregion().clickManagebutton().clickEditbutton().ClearStateName().enterstatename("E STATE").clicksubmitbutton();
        return this;
    }
    public LevelPage Editterritory() {
        navigateToLevelPage().clicknewlycreatedregion().clicknewlycreatedstate().clickManagebutton().clickEditbutton().ClearTerritoryName().enterterritoryname("E TERRITORY").clicksubmitbutton();
        return this;
    }
    public LevelPage Editcluster() {
        navigateToLevelPage().clicknewlycreatedregion().clicknewlycreatedstate().clicknewlycreatedterritory().clickManagebutton().clickEditbutton().ClearClusterName().enterclustername("E CLUSTER").clicksubmitbutton();
        return this;
    }
    public LevelPage Editlocation() {
        navigateToLevelPage().clicknewlycreatedregion().clicknewlycreatedstate().clicknewlycreatedterritory().clicknewlycreatedcluster().clickManagebutton().clickEditbutton().ClearLocationName().enterlocationname("E LOCATION").clicksubmitbutton();
        return this;
    }
    public LevelPage CancelButtonInEditRegion() {
        navigateToLevelPage().clickManagebutton().clickEditbutton().ClearRegionName().enterregionname("ER Region").clickCancelbutton();
        return this;
    }
    public LevelPage    CancelButtonInEditstate() {
        navigateToLevelPage().clicknewlycreatedregion().clickManagebutton().clickEditbutton().ClearStateName().enterstatename("ER STATE").clickCancelbutton();
        return this;
    }
    public LevelPage CancelButtonInEditterritory() {
        navigateToLevelPage().clicknewlycreatedregion().clicknewlycreatedstate().clickManagebutton().clickEditbutton().ClearTerritoryName().enterterritoryname("ER TERRITORY").clickCancelbutton();
        return this;
    }
    public LevelPage CancelButtonInEditcluster() {
        navigateToLevelPage().clicknewlycreatedregion().clicknewlycreatedstate().clicknewlycreatedterritory().clickManagebutton().clickEditbutton().ClearClusterName().enterclustername("ER CLUSTER").clickCancelbutton();
        return this;
    }
    public LevelPage CancelButtonInEditlocation() {
        navigateToLevelPage().clicknewlycreatedregion().clicknewlycreatedstate().clicknewlycreatedterritory().clicknewlycreatedcluster().clickManagebutton().clickEditbutton().ClearLocationName().enterlocationname("ER LOCATION").clickCancelbutton();
        return this;
    }
    public LevelPage clicksearchicon() {
        try {
            WebElement Searchicon = elements.getSearchicon();
            Searchicon.click();
            logger.info("Clicked the Search icon");
        } catch (Exception e) {
            logger.error("Failed to Click the Search icon: " + e.getMessage());
            throw new RuntimeException("Unable to Click the Search icon: ", e);
        }
        return this;
    }
    public LevelPage entersearchregion(String searchregion ) {
        try {
            WebElement Searchregionname = elements.getSearchregion();
            Searchregionname.sendKeys(searchregion);
            logger.info("Entered the region name in search box");
        } catch (Exception e) {
            logger.error("Failed to Enter the region name in search box: " + e.getMessage());
            throw new RuntimeException("Unable to Enter the region name in search box: ", e);
        }
        return this;
    }
    public LevelPage entersearchstate(String searchstate ) {
        try {
            WebElement Searchstatename = elements.getSearchstate();
            Searchstatename.sendKeys(searchstate);
            logger.info("Entered the state name in search box");
        } catch (Exception e) {
            logger.error("Failed to Enter the state name in search box: " + e.getMessage());
            throw new RuntimeException("Unable to Enter the state name in search box: ", e);
        }
        return this;
    }
    public LevelPage entersearchterritory(String searchterritory ) {
        try {
            WebElement Searchterritoryname = elements.getSearchterritory();
            Searchterritoryname.sendKeys(searchterritory);
            logger.info("Entered the territory name in search box");
        } catch (Exception e) {
            logger.error("Failed to Enter the territory name in search box: " + e.getMessage());
            throw new RuntimeException("Unable to Enter the territory name in search box: ", e);
        }
        return this;
    }
    public LevelPage entersearchcluster(String searchcluster ) {
        try {
            WebElement Searchclustername = elements.getSearchcluster();
            Searchclustername.sendKeys(searchcluster);
            logger.info("Entered the cluster name in search box");
        } catch (Exception e) {
            logger.error("Failed to Enter the cluster name in search box: " + e.getMessage());
            throw new RuntimeException("Unable to Enter the cluster name in search box: ", e);
        }
        return this;
    }
    public LevelPage entersearchlocation(String searchlocation ) {
        try {
            WebElement Searchlocationname = elements.getSearchlocation();
            Searchlocationname.sendKeys(searchlocation);
            logger.info("Entered the location name in search box");
        } catch (Exception e) {
            logger.error("Failed to Enter the location name in search box: " + e.getMessage());
            throw new RuntimeException("Unable to Enter the location name in search box: ", e);
        }
        return this;
    }
    public int LeveltableRows() {
        try {
            return elements.getLeveltablerows().size();
        } catch (Exception e) {
            logger.debug("Unable to get level table rows : " + e.getMessage());
        }
        return 0;
    }
    public LevelPage SearchValidRegion() {
        navigateToLevelPage().clicksearchicon();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        entersearchregion("New Region");
        return this;
    }
    public LevelPage SearchInvalidRegion() {
        navigateToLevelPage().clicksearchicon();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        entersearchregion("Tamilnadu");
        return this;
    }
    public boolean isErrormsgofNoResultsDisplayed() {
        try {
            return elements.getErrormsgofNoResults().isDisplayed();
        } catch (Exception e) {
            logger.debug("Error message of No Results not displayed: " + e.getMessage());
            return false;
        }
    }
    public LevelPage clickFirstRegion() {
        try {
            WebElement FirstRegion = elements.getFirstRegion();
            FirstRegion.click();
            logger.info("Clicked the First Region");
        } catch (Exception e) {
            logger.error("Failed to Click the First Region: " + e.getMessage());
            throw new RuntimeException("Unable to Click the First Region: ", e);
        }
        return this;
    }
    public LevelPage clickFirstState() {
        try {
            WebElement Firststate = elements.getFirstState();
            Firststate.click();
            logger.info("Clicked the First State");
        } catch (Exception e) {
            logger.error("Failed to Click the First State: " + e.getMessage());
            throw new RuntimeException("Unable to Click the First State: ", e);
        }
        return this;
    }
    public LevelPage clickFirstTerritory() {
        try {
            WebElement FirstTerritory = elements.getFirstTerritory();
            FirstTerritory.click();
            logger.info("Clicked the First Territory");
        } catch (Exception e) {
            logger.error("Failed to Click the First Territory: " + e.getMessage());
            throw new RuntimeException("Unable to Click the First Territory: ", e);
        }
        return this;
    }
    public LevelPage clickFirstCluster() {
        try {
            WebElement FirstCluster = elements.getFirstCluster();
            FirstCluster.click();
            logger.info("Clicked the First Cluster");
        } catch (Exception e) {
            logger.error("Failed to Click the First Cluster: " + e.getMessage());
            throw new RuntimeException("Unable to Click the First Cluster: ", e);
        }
        return this;
    }
    public LevelPage SearchValidState() {
        navigateToLevelPage().clickFirstRegion().clicksearchicon();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        entersearchstate("New State");
        return this;
    }
    public LevelPage SearchInvalidState() {
        navigateToLevelPage().clickFirstRegion().clicksearchicon();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        entersearchstate("cbe");
        return this;
    }
    public LevelPage SearchValidTerritory() {
        navigateToLevelPage().clickFirstRegion().clickFirstState().clicksearchicon();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        entersearchterritory("Coimbatore");
        return this;
    }
    public LevelPage SearchInvalidTerritory() {
        navigateToLevelPage().clickFirstRegion().clickFirstState().clicksearchicon();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        entersearchterritory("Bangalore");
        return this;
    }
    public LevelPage SearchValidCluster() {
        navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory().clicksearchicon();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        entersearchcluster("RS puram");
        return this;
    }
    public LevelPage SearchInvalidCluster() {
        navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory().clicksearchicon();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        entersearchcluster("Trichy");
        return this;
    }
    public LevelPage SearchValidLocation() {
        navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory().clickFirstCluster().clicksearchicon();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        entersearchlocation("RO1");
        return this;
    }
    public LevelPage SearchInvalidLocation() {
        navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory().clickFirstCluster().clicksearchicon();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        entersearchlocation("Erode");
        return this;
    }
    public LevelPage clickLastpagebutton() {
        try {
            WebElement Lastpagebutton = elements.getLastPageButton();
            Lastpagebutton.click();
            logger.info("Clicked the Last page button");
        } catch (Exception e) {
            logger.error("Failed to Click the last page button: " + e.getMessage());
            throw new RuntimeException("Unable to Click the last page button: ", e);
        }
        return this;
    }
    public String TotalRegionResults()  {
        TestLogger.info("Getting total Region count");
        navigateToLevelPage();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        String text = driver.findElement(By.xpath("//div[@class='flex items-center gap-4']")).getText();
        String regionvalue = text.split(" ")[6];
        return regionvalue;
    }
    public String TotalRegionCount() throws InterruptedException {
        TestLogger.info("Getting total Region count");
        navigateToLevelPage();
        commonUtil.clickLastPageButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        String actualregioncount = commonUtil.getLastRowFirstColumnValue();
        return actualregioncount;

    }
    public String TotalStateResults()  {
        TestLogger.info("Getting total State count");
        navigateToLevelPage().clickFirstRegion();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        String text = driver.findElement(By.xpath("//div[@class='flex items-center gap-4']")).getText();
        String statevalue = text.split(" ")[6];
        return statevalue;
    }
    public String TotalStateCount() throws InterruptedException {
        TestLogger.info("Getting total State count");
        navigateToLevelPage().clickFirstRegion();
        //commonUtil.clickLastPageButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        String actualstatecount = commonUtil.getLastRowFirstColumnValue();
        return actualstatecount;

    }
    public String TotalTerritoryResults()  {
        TestLogger.info("Getting total Territory count");
        navigateToLevelPage().clickFirstRegion().clickFirstState();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        String text = driver.findElement(By.xpath("//div[@class='flex items-center gap-4']")).getText();
        String territoryvalue = text.split(" ")[6];
        return territoryvalue;
    }

    public String TotalTerritoryCount() throws InterruptedException {
        TestLogger.info("Getting total Territory count");
        navigateToLevelPage();
       // commonUtil.clickLastPageButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        String actualterritorycount = commonUtil.getLastRowFirstColumnValue();
        return actualterritorycount;
    }

    public String TotalClusterResults()  {
        TestLogger.info("Getting total Cluster count");
        navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        String text = driver.findElement(By.xpath("//div[@class='flex items-center gap-4']")).getText();
        String clustervalue = text.split(" ")[6];
        return clustervalue;
    }
    public String TotalClustersCount() throws InterruptedException {
        TestLogger.info("Getting total Cluster count");
        navigateToLevelPage();
        //commonUtil.clickLastPageButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        String actualclustercount = commonUtil.getLastRowFirstColumnValue();
        return actualclustercount;

    }
    public String TotalLocationResults()  {
        TestLogger.info("Getting total location count");
        navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory().clickFirstCluster();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        String text = driver.findElement(By.xpath("//div[@class='flex items-center gap-4']")).getText();
        String locationvalue = text.split(" ")[6];
        return locationvalue;
    }
    public String TotalLocationCount() throws InterruptedException {
        TestLogger.info("Getting total location count");
        navigateToLevelPage();
       // commonUtil.clickLastPageButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        String actuallocationcount = commonUtil.getLastRowFirstColumnValue();
        return actuallocationcount;
    }
    public List<String> getHeaderTextsofRegion() {
        List<String> headerTexts = new ArrayList<>();
        for (WebElement header : elements.getListofheaders()) {
            headerTexts.add(header.getText().trim());
        }
        return headerTexts;
    }
    public List<String> getHeaderTextsofState() {
        List<String> headerTexts = new ArrayList<>();
        for (WebElement header : elements.getListofheaders()) {
            headerTexts.add(header.getText().trim());
        }
        return headerTexts;
    }
    public List<String> getHeaderTextsofTerritory() {
        List<String> headerTexts = new ArrayList<>();
        for (WebElement header : elements.getListofheaders()) {
            headerTexts.add(header.getText().trim());
        }
        return headerTexts;
    }
    public List<String> getHeaderTextsofCluster() {
        List<String> headerTexts = new ArrayList<>();
        for (WebElement header : elements.getListofheaders()) {
            headerTexts.add(header.getText().trim());
        }
        return headerTexts;
    }
    public List<String> getHeaderTextsofLocation() {
        List<String> headerTexts = new ArrayList<>();
        for (WebElement header : elements.getListofheaders()) {
            headerTexts.add(header.getText().trim());
        }
        return headerTexts;
    }
    public boolean isLevelTableDisplayed() {
        try {
            return elements.getLeveltable().isDisplayed();
        } catch (Exception e) {
            logger.debug("Level Table is not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean isErrorMsgOfEmptyLevelNameDisplayed() {
        try {
            return elements.getErrorMsgofEmptyLevelName().isDisplayed();
        } catch (Exception e) {
            logger.debug("Error message of empty level name is not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean isErrorMsgOfUsingSpacesInLevelNameDisplayed() {
        try {
            return elements.getErrorMsgForUsingspaceInLevelName().isDisplayed();
        } catch (Exception e) {
            logger.debug("Error message of using spaces in level name is not displayed: " + e.getMessage());
            return false;
        }
    }
    public boolean isSubmitbuttonDisplayed() {
        try {
            return elements.getSubmitbutton().isDisplayed();
        } catch (Exception e) {
            logger.debug("Submit button is not displayed: " + e.getMessage());
            return false;
        }
    }


}