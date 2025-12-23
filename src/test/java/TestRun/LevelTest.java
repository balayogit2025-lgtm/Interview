package TestRun;

import Base.BaseTest;
import Pages.LevelPage;
import Pages.LoginPage;
import Utils.TestLogger;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class LevelTest extends BaseTest {

    private LoginPage loginPage;
    private LevelPage levelPage;

    @BeforeMethod
    public void setupLevelTest() {
        navigateToApp();
        loginPage = new LoginPage(getDriver());
        levelPage = new LevelPage(getDriver());
        loginPage.login("admin@gmail.com", "Sterna@123");
    }

    @Test(priority = 1, description = "Navigate to Level page")
    public void navigateToLevelPage() {
        getExtentTest().log(Status.INFO, "Navigate to Level page");
        levelPage.navigateToLevelPage();
        Assert.assertTrue(levelPage.isAddRegionButtonDisplayed(), "Add Region button is not displayed");
        getExtentTest().log(Status.PASS, "Successfully Navigated to Level page");
    }

    @Test(priority = 2, description = "Create a new Region")
    public void createNewRegion() {
        getExtentTest().log(Status.INFO, "Create a new Region");
        levelPage.addnewregion();
        Assert.assertTrue(levelPage.isAddRegionButtonDisplayed(), "Failed to create a new Region");
        getExtentTest().log(Status.PASS, "Successfully Created a new Region");
    }

    @Test(priority = 3, description = "Create a new State")
    public void createaNewState() {
        getExtentTest().log(Status.INFO, "Create a new State");
        levelPage.addnewstate();
        Assert.assertTrue(levelPage.isAddStateButtonDisplayed(), "Failed to create a new State");
        getExtentTest().log(Status.PASS, "Successfully Created a new State");
    }
    @Test(priority = 4, description = "Create a new Territory")
    public void createaNewTerritory() {
        getExtentTest().log(Status.INFO, "Create a new Territory");
        levelPage.addnewterritory();
        Assert.assertTrue(levelPage.isAddTerritoryButtonDisplayed(), "Failed to create a new Territory");
        getExtentTest().log(Status.PASS, "Successfully Created a new Territory");
    }
    @Test(priority = 5, description = "Create a new Cluster")
    public void createaNewCluster() {
        getExtentTest().log(Status.INFO, "Create a new Cluster");
        levelPage.addnewcluster();
        Assert.assertTrue(levelPage.isAddClusterButtonDisplayed(), "Failed to create a new Cluster");
        getExtentTest().log(Status.PASS, "Successfully Created a new Cluster");
    }
    @Test(priority = 6, description = "Create a new Location")
    public void createaNewLocation() {
        getExtentTest().log(Status.INFO, "Create a new Location");
        levelPage.addnewlocation();
        Assert.assertTrue(levelPage.isAddLocationButtonDisplayed(), "Failed to create a new Location");
        getExtentTest().log(Status.PASS, "Successfully Created a new Location");
    }
    @Test(priority = 7, description = "Verify the cancel button functionality in Create a new Region")
    public void CancelOptionInCreateNewRegion() {
        getExtentTest().log(Status.INFO, "Verify the cancel button functionality in Create a new Region");
        levelPage.Cancelbuttoninaddnewregion();
        Assert.assertTrue(levelPage.isAddRegionButtonDisplayed(), "Failed to click the cancel button in creating a new Region");
        getExtentTest().log(Status.PASS, "Successfully Verified the cancel button functionality in Created a new Region");
    }

    @Test(priority = 8, description = "Verify the cancel button functionality in Create a new State")
    public void CancelOptionInCreateaNewState() {
        getExtentTest().log(Status.INFO, "Verify the cancel button functionality in Create a new State");
        levelPage.Cancelbuttoninaddnewstate();
        Assert.assertTrue(levelPage.isAddStateButtonDisplayed(), "Failed to click the cancel button in creating a new State");
        getExtentTest().log(Status.PASS, "Successfully Verified the cancel button functionality in Created a new State");
    }
    @Test(priority = 9, description = "Verify the cancel button functionality in Create a new Territory")
    public void CancelOptionInCreateaNewTerritory() {
        getExtentTest().log(Status.INFO, "Verify the cancel button functionality in Create a new Territory");
        levelPage.Cancelbuttoninaddnewterritory();
        Assert.assertTrue(levelPage.isAddTerritoryButtonDisplayed(), "Failed to click the cancel button in creating a new Territory");
        getExtentTest().log(Status.PASS, "Successfully Verified the cancel button functionality in Created a new Territory");
    }
    @Test(priority = 10, description = "Verify the cancel button functionality in Create a new Cluster")
    public void CancelOptionInCreateaNewCluster() {
        getExtentTest().log(Status.INFO, "Verify the cancel button functionality in Create a new Cluster");
        levelPage.Cancelbuttoninaddnewcluster();
        Assert.assertTrue(levelPage.isAddClusterButtonDisplayed(), "Failed to click the cancel button in creating a new Cluster");
        getExtentTest().log(Status.PASS, "Successfully Verified the cancel button functionality in Created a new Cluster");
    }
    @Test(priority = 11, description = "Verify the cancel button functionality in Create a new Location")
    public void CancelOptionInCreateaNewLocation() {
        getExtentTest().log(Status.INFO, "Verify the cancel button functionality in Create a new Location");
        levelPage.Cancelbuttoninaddnewlocation();
        Assert.assertTrue(levelPage.isAddLocationButtonDisplayed(), "Failed to click the cancel button in creating a new Location");
        getExtentTest().log(Status.PASS, "Successfully Verified the cancel button functionality in Created a new Location");
    }
    @Test(priority = 12, description = "Edit the Region details")
    public void EditRegion() {
        getExtentTest().log(Status.INFO, "Edit the Region details");
        levelPage.EditRegion();
        Assert.assertTrue(levelPage.isAddRegionButtonDisplayed(), "Failed to edit the region details");
        getExtentTest().log(Status.PASS, "Successfully edited the region details");
    }
    @Test(priority = 13, description = "Edit the State details")
    public void EditState() {
        getExtentTest().log(Status.INFO, "Edit the state details");
        levelPage.Editstate();
        Assert.assertTrue(levelPage.isAddStateButtonDisplayed(), "Failed to edit the state details");
        getExtentTest().log(Status.PASS, "Successfully edited the state details");
    }
    @Test(priority = 14, description = "Edit the Territory details")
    public void EditTerritory() {
        getExtentTest().log(Status.INFO, "Edit the Territory details");
        levelPage.Editterritory();
        Assert.assertTrue(levelPage.isAddTerritoryButtonDisplayed(), "Failed to edit the Territory details");
        getExtentTest().log(Status.PASS, "Successfully edited the Territory details");
    }
    @Test(priority = 15, description = "Edit the Cluster details")
    public void EditCluster() {
        getExtentTest().log(Status.INFO, "Edit the cluster details");
        levelPage.Editcluster();
        Assert.assertTrue(levelPage.isAddClusterButtonDisplayed(), "Failed to edit the cluster details");
        getExtentTest().log(Status.PASS, "Successfully edited the cluster details");
    }
    @Test(priority = 16, description = "Edit the Location details")
    public void EditLocation() {
        getExtentTest().log(Status.INFO, "Edit the Location details");
        levelPage.Editlocation();
        Assert.assertTrue(levelPage.isAddLocationButtonDisplayed(), "Failed to edit the location details");
        getExtentTest().log(Status.PASS, "Successfully edited the location details");
    }
    @Test(priority = 17, description = "Verify the cancel button functionality in Edit Region")
    public void CancelOptionInEditRegion() {
        getExtentTest().log(Status.INFO, "Verify the cancel button functionality in Edit Region");
        levelPage.CancelButtonInEditRegion();
        Assert.assertTrue(levelPage.isAddRegionButtonDisplayed(), "Failed to click the cancel button in edit Region");
        getExtentTest().log(Status.PASS, "Successfully Verified the cancel button functionality in edit Region");
    }

    @Test(priority = 18, description = "Verify the cancel button functionality in Edit State")
    public void CancelOptionInEditState() {
        getExtentTest().log(Status.INFO, "Verify the cancel button functionality in Edit State");
        levelPage.CancelButtonInEditstate();
        Assert.assertTrue(levelPage.isAddStateButtonDisplayed(), "Failed to click the cancel button in Edit State");
        getExtentTest().log(Status.PASS, "Successfully Verified the cancel button functionality in Edit State");
    }
    @Test(priority = 19, description = "Verify the cancel button functionality in Edit Territory")
    public void CancelOptionInEditTerritory() {
        getExtentTest().log(Status.INFO, "Verify the cancel button functionality in Edit Territory");
        levelPage.CancelButtonInEditterritory();
        Assert.assertTrue(levelPage.isAddTerritoryButtonDisplayed(), "Failed to click the cancel button in Edit Territory");
        getExtentTest().log(Status.PASS, "Successfully Verified the cancel button functionality in Edit Territory");
    }
    @Test(priority = 20, description = "Verify the cancel button functionality in Edit Cluster")
    public void CancelOptionInEditCluster() {
        getExtentTest().log(Status.INFO, "Verify the cancel button functionality in Edit Cluster");
        levelPage.CancelButtonInEditcluster();
        Assert.assertTrue(levelPage.isAddClusterButtonDisplayed(), "Failed to click the cancel button in Edit Cluster");
        getExtentTest().log(Status.PASS, "Successfully Verified the cancel button functionality in Edit Cluster");
    }
    @Test(priority = 21, description = "Verify the cancel button functionality in Edit Location")
    public void CancelOptionInEditLocation() {
        getExtentTest().log(Status.INFO, "Verify the cancel button functionality in Edit Location");
        levelPage.CancelButtonInEditlocation();
        Assert.assertTrue(levelPage.isAddLocationButtonDisplayed(), "Failed to click the cancel button in Edit Location");
        getExtentTest().log(Status.PASS, "Successfully Verified the cancel button functionality in Edit Location");
    }
    @Test(priority = 22, description = "Verify search functionality with valid Region name")
    public void searchRegionWithValidRegionName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with valid Region name");
        levelPage.SearchValidRegion();
        int rowCount = levelPage.LeveltableRows();
        Assert.assertTrue(rowCount > 0, "it returns an empty table");
        getExtentTest().log(Status.PASS, "Verfied the reults of the search functionality with valid Region name");
    }
    @Test(priority = 23, description = "Verify search functionality with Invalid Region name")
    public void searchRegionWithInvalidRegionName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with Invalid Region name");
        levelPage.SearchInvalidRegion();
        Assert.assertTrue(levelPage.isErrormsgofNoResultsDisplayed(), "the Error message of No results not displayed");
        getExtentTest().log(Status.PASS, "Verfied the reults of the search functionality with Invalid Region name");
    }
    @Test(priority = 24, description = "Verify search functionality with valid State name")
    public void searchRegionWithValidStateName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with valid State name");
        levelPage.SearchValidState();
        int rowCount = levelPage.LeveltableRows();
        Assert.assertTrue(rowCount > 0, "it returns an empty table");
        getExtentTest().log(Status.PASS, "Verfied the reults of the search functionality with valid State name");
    }
    @Test(priority = 25, description = "Verify search functionality with Invalid State name")
    public void searchRegionWithInvalidStateName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with Invalid State name");
        levelPage.SearchInvalidState();
        Assert.assertTrue(levelPage.isErrormsgofNoResultsDisplayed(), "the Error message of No results not displayed");
        getExtentTest().log(Status.PASS, "Verfied the reults of the search functionality with Invalid State name");
    }
    @Test(priority = 26, description = "Verify search functionality with valid Territory name")
    public void searchRegionWithValidTerritoryName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with valid Territory name");
        levelPage.SearchValidTerritory();
        int rowCount = levelPage.LeveltableRows();
        Assert.assertTrue(rowCount > 0, "it returns an empty table");
        getExtentTest().log(Status.PASS, "Verfied the reults of the search functionality with valid Territory name");
    }
    @Test(priority = 27, description = "Verify search functionality with Invalid Territory name")
    public void searchRegionWithInvalidTerritoryName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with Invalid Territory name");
        levelPage.SearchInvalidTerritory();
        Assert.assertTrue(levelPage.isErrormsgofNoResultsDisplayed(), "the Error message of No results not displayed");
        getExtentTest().log(Status.PASS, "Verfied the reults of the search functionality with Invalid Territory name");
    }
    @Test(priority = 28, description = "Verify search functionality with valid Cluster name")
    public void searchRegionWithValidClusterName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with valid Cluster name");
        levelPage.SearchValidCluster();
        int rowCount = levelPage.LeveltableRows();
        Assert.assertTrue(rowCount > 0, "it returns an empty table");
        getExtentTest().log(Status.PASS, "Verfied the reults of the search functionality with valid Cluster name");
    }
    @Test(priority = 29, description = "Verify search functionality with Invalid Cluster name")
    public void searchRegionWithInvalidClusterName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with Invalid Cluster name");
        levelPage.SearchInvalidCluster();
        Assert.assertTrue(levelPage.isErrormsgofNoResultsDisplayed(), "the Error message of No results not displayed");
        getExtentTest().log(Status.PASS, "Verfied the reults of the search functionality with Invalid Cluster name");
    }
    @Test(priority = 30, description = "Verify search functionality with valid Location name")
    public void searchRegionWithValidLocationName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with valid Location name");
        levelPage.SearchValidLocation();
        int rowCount = levelPage.LeveltableRows();
        Assert.assertTrue(rowCount > 0, "it returns an empty table");
        getExtentTest().log(Status.PASS, "Verfied the reults of the search functionality with valid Location name");
    }
    @Test(priority = 31, description = "Verify search functionality with Invalid Location name")
    public void searchRegionWithInvalidLocationName() {
        getExtentTest().log(Status.INFO, "Test: Verify search functionality with Invalid Location name");
        levelPage.SearchInvalidLocation();
        Assert.assertTrue(levelPage.isErrormsgofNoResultsDisplayed(), "the Error message of No results not displayed");
        getExtentTest().log(Status.PASS, "Verfied the reults of the search functionality with Invalid Location name");
    }
    @Test(priority = 32, description = "Verify the Total regions count")
    public void verifyTotalRegionCount() throws InterruptedException {
        logger.info("Started verify region Count");
        getExtentTest().log(Status.INFO, "Verifying the total region count");
        String Expectedcount = levelPage.TotalRegionResults();
        String Actualcount = levelPage.TotalRegionCount();
        Assert.assertEquals(Actualcount,Expectedcount,"The count is mismatched");
        getExtentTest().log(Status.PASS, "Expected & Actual region count is matched");
        TestLogger.success("Region count verification passed");
        logger.info("Region count verification PASSED" );
    }
    @Test(priority = 33, description = "Verify the Total state count")
    public void verifyTotalStateCount() throws InterruptedException {
        logger.info("Started verify state Count");
        getExtentTest().log(Status.INFO, "Verifying the total state count");
        String Expectedcount = levelPage.TotalStateResults();
        String Actualcount = levelPage.TotalStateCount();
        Assert.assertEquals(Actualcount,Expectedcount,"The count is mismatched");
        getExtentTest().log(Status.PASS, "Expected & Actual state count is matched");
        TestLogger.success("state count verification passed");
        logger.info("state count verification PASSED" );
    }
    @Test(priority = 34, description = "Verify the Total territory count")
    public void verifyTotalTerritoryCount() throws InterruptedException {
        logger.info("Started verify territory Count");
        getExtentTest().log(Status.INFO, "Verifying the total territory count");
        String Expectedcount = levelPage.TotalTerritoryResults();
        String Actualcount = levelPage.TotalTerritoryCount();
        Assert.assertEquals(Actualcount,Expectedcount,"The count is mismatched");
        getExtentTest().log(Status.PASS, "Expected & Actual territory count is matched");
        TestLogger.success("territory count verification passed");
        logger.info("territory count verification PASSED" );
    }
    @Test(priority = 35, description = "Verify the Total cluster count")
    public void verifyTotalClusterCount() throws InterruptedException {
        logger.info("Started verify cluster Count");
        getExtentTest().log(Status.INFO, "Verifying the total cluster count");
        String Expectedcount = levelPage.TotalClusterResults();
        String Actualcount = levelPage.TotalClustersCount();
        Assert.assertEquals(Actualcount,Expectedcount,"The count is mismatched");
        getExtentTest().log(Status.PASS, "Expected & Actual cluster count is matched");
        TestLogger.success("cluster count verification passed");
        logger.info("cluster count verification PASSED" );
    }
    @Test(priority = 36, description = "Verify the Total location count")
    public void verifyTotalLocationCount() throws InterruptedException {
        logger.info("Started verify location Count");
        getExtentTest().log(Status.INFO, "Verifying the total location count");
        String Expectedcount = levelPage.TotalLocationResults();
        String Actualcount = levelPage.TotalLocationCount();
        Assert.assertEquals(Actualcount,Expectedcount,"The count is mismatched");
        getExtentTest().log(Status.PASS, "Expected & Actual location count is matched");
        TestLogger.success("location count verification passed");
        logger.info("location count verification PASSED" );
    }
    @Test(priority = 37, description = "Verify the column header names in region list screen")
    public void verifyRegioncolumnheadernames() {
        getExtentTest().log(Status.INFO, "Test: Verify the column header names in region list screen");
        levelPage.navigateToLevelPage();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        List<String> regionexpectedHeaders = Arrays.asList("S.NO", "NAME", "CREATED BY","UPDATED BY","ACTION");
        List<String> regionactualHeaders = levelPage.getHeaderTextsofRegion();
        Assert.assertEquals(regionactualHeaders, regionexpectedHeaders, "Table headers do not match!");
        getExtentTest().log(Status.PASS, "The column header names are displayed as expected");
    }
    @Test(priority = 38, description = "Verify the column header names in state list screen")
    public void verifyStatecolumnheadernames() {
        getExtentTest().log(Status.INFO, "Test: Verify the column header names in state list screen");
        levelPage.navigateToLevelPage().clickFirstRegion();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        List<String> stateexpectedHeaders = Arrays.asList("S.NO", "NAME", "PARENT NAME", "CREATED BY","UPDATED BY","ACTION");
        List<String> stateactualHeaders = levelPage.getHeaderTextsofState();
        Assert.assertEquals(stateactualHeaders, stateexpectedHeaders, "Table headers do not match!");
        getExtentTest().log(Status.PASS, "The column header names are displayed as expected");
    }
    @Test(priority = 39, description = "Verify the column header names in Territory list screen")
    public void verifyTerritorycolumnheadernames() {
        getExtentTest().log(Status.INFO, "Test: Verify the column header names in Territory list screen");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        List<String> territoryexpectedHeaders = Arrays.asList("S.NO", "NAME", "PARENT NAME", "CREATED BY","UPDATED BY","ACTION");
        List<String> territoryactualHeaders = levelPage.getHeaderTextsofTerritory();
        Assert.assertEquals(territoryactualHeaders, territoryexpectedHeaders, "Table headers do not match!");
        getExtentTest().log(Status.PASS, "The column header names are displayed as expected");
    }
    @Test(priority = 40, description = "Verify the column header names in cluster list screen")
    public void verifyClustercolumnheadernames() {
        getExtentTest().log(Status.INFO, "Test: Verify the column header names in cluster list screen");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        List<String> clusterexpectedHeaders = Arrays.asList("S.NO", "NAME","PARENT NAME", "CREATED BY","UPDATED BY","ACTION");
        List<String> clusteractualHeaders = levelPage.getHeaderTextsofCluster();
        Assert.assertEquals(clusteractualHeaders, clusterexpectedHeaders, "Table headers do not match!");
        getExtentTest().log(Status.PASS, "The column header names are displayed as expected");
    }
    @Test(priority = 41, description = "Verify the column header names in location list screen")
    public void verifyLocationcolumnheadernames() {
        getExtentTest().log(Status.INFO, "Test: Verify the column header names in location list screen");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory().clickFirstCluster();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        List<String> locationexpectedHeaders = Arrays.asList("S.NO", "NAME", "PARENT NAME","CREATED BY","UPDATED BY","ACTION");
        List<String> locationactualHeaders = levelPage.getHeaderTextsofLocation();
        Assert.assertEquals(locationactualHeaders, locationexpectedHeaders, "Table headers do not match!");
        getExtentTest().log(Status.PASS, "The column header names are displayed as expected");
    }
    @Test(priority = 42, description = "Verify Region table is displayed")
    public void verifyRegionTableDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verify Region table is displayed");
        levelPage.navigateToLevelPage();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean isTableDisplayed = levelPage.isLevelTableDisplayed();
        Assert.assertTrue(isTableDisplayed, "Region table is not displayed");
        getExtentTest().log(Status.PASS, "Verified the Region table is displayed");
    }
    @Test(priority = 43, description = "Verify state table is displayed")
    public void verifyStateTableDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verify state table is displayed");
        levelPage.navigateToLevelPage().clickFirstRegion();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean isTableDisplayed = levelPage.isLevelTableDisplayed();
        Assert.assertTrue(isTableDisplayed, "state table is not displayed");
        getExtentTest().log(Status.PASS, "Verified the state table is displayed");
    }
    @Test(priority = 44, description = "Verify Territory table is displayed")
    public void verifyTerritoryTableDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verify Territory table is displayed");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean isTableDisplayed = levelPage.isLevelTableDisplayed();
        Assert.assertTrue(isTableDisplayed, "Territory table is not displayed");
        getExtentTest().log(Status.PASS, "Verified the Territory table is displayed");
    }
    @Test(priority = 45, description = "Verify Cluster table is displayed")
    public void verifyClusterTableDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verify Cluster table is displayed");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean isTableDisplayed = levelPage.isLevelTableDisplayed();
        Assert.assertTrue(isTableDisplayed, "Cluster table is not displayed");
        getExtentTest().log(Status.PASS, "Verified the Cluster table is displayed");
    }
    @Test(priority = 46, description = "Verify location table is displayed")
    public void verifyLocationTableDisplayed() {
        getExtentTest().log(Status.INFO, "Test: Verify location table is displayed");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory().clickFirstCluster();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean isTableDisplayed = levelPage.isLevelTableDisplayed();
        Assert.assertTrue(isTableDisplayed, "location table is not displayed");
        getExtentTest().log(Status.PASS, "Verified the location table is displayed");
    }
    @Test(priority = 47, description = "Verify region creation when the region name is empty")
    public void AddRegionwithemptyRegionName() {
        getExtentTest().log(Status.INFO, "Test: Verify region creation when the region name is empty");
        levelPage.navigateToLevelPage().clickaddregion().clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofemptyregionnamefieldsdisplayed = levelPage.isErrorMsgOfEmptyLevelNameDisplayed();
        Assert.assertTrue(iserrormsgofemptyregionnamefieldsdisplayed, "Error message for all mandatory fields is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the error message when the region name field is empty");
    }
    @Test(priority = 48, description = "Verify state creation when the state name is empty")
    public void AddStatewithemptyStateName() {
        getExtentTest().log(Status.INFO, "Test: Verify state creation when the state name is empty");
        levelPage.navigateToLevelPage().clickFirstRegion().clickaddstatebutton().clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofemptystatenamefieldsdisplayed = levelPage.isErrorMsgOfEmptyLevelNameDisplayed();
        Assert.assertTrue(iserrormsgofemptystatenamefieldsdisplayed, "Error message for all mandatory fields is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the error message when the state name field is empty");
    }
    @Test(priority = 49, description = "Verify Territory creation when the Territory name is empty")
    public void AddTerritoryWithemptyTerritoryName() {
        getExtentTest().log(Status.INFO, "Test: Verify Territory creation when the Territory name is empty");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState().clickaddTerritorybutton().clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofemptyterritorynamefieldsdisplayed = levelPage.isErrorMsgOfEmptyLevelNameDisplayed();
        Assert.assertTrue(iserrormsgofemptyterritorynamefieldsdisplayed, "Error message for all mandatory fields is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the error message when the Territory name field is empty");
    }
    @Test(priority = 50, description = "Verify Cluster creation when the Cluster name is empty")
    public void AddClusterWithemptyClusterName() {
        getExtentTest().log(Status.INFO, "Test: Verify Cluster creation when the Cluster name is empty");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory().clickaddclusterbutton().clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofemptyclusternamefieldsdisplayed = levelPage.isErrorMsgOfEmptyLevelNameDisplayed();
        Assert.assertTrue(iserrormsgofemptyclusternamefieldsdisplayed, "Error message for all mandatory fields is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the error message when the Cluster name field is empty");
    }
    @Test(priority = 51, description = "Verify location creation when the location name is empty")
    public void AddLocationWithemptyLocationName() {
        getExtentTest().log(Status.INFO, "Test: Verify location creation when the location name is empty");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory().clickFirstCluster().clickaddlocationbutton().clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofemptylocationnamefieldsdisplayed = levelPage.isErrorMsgOfEmptyLevelNameDisplayed();
        Assert.assertTrue(iserrormsgofemptylocationnamefieldsdisplayed, "Error message for all mandatory fields is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the error message when the location name field is empty");
    }
    @Test(priority = 52, description = "Verify region creation when the region name created using spaces")
    public void AddRegionwithSpacesInRegionName() {
        getExtentTest().log(Status.INFO, "Test: Verify region creation when the region name is created using spaces");
        levelPage.navigateToLevelPage().clickaddregion().enterregionname(" Region ").clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofusingspacesinregionnamefieldsdisplayed = levelPage.isErrorMsgOfUsingSpacesInLevelNameDisplayed();
        Assert.assertTrue(iserrormsgofusingspacesinregionnamefieldsdisplayed, "Error message for creating a region name using spaces is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the error message when the region name is created using spaces");
    }
    @Test(priority = 53, description = "Verify state creation when the State is created using spaces")
    public void AddStateWithSpacesInStateName() {
        getExtentTest().log(Status.INFO, "Test: Verify state creation when the state name is created using spaces");
        levelPage.navigateToLevelPage().clickFirstRegion().clickaddstatebutton().enterstatename(" State ").clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofusingspacesinstatenamefieldsdisplayed = levelPage.isErrorMsgOfUsingSpacesInLevelNameDisplayed();
        Assert.assertTrue(iserrormsgofusingspacesinstatenamefieldsdisplayed, "Error message for creating the state name using spaces is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the error message when the state name is created using spaces");
    }
    @Test(priority = 54, description = "Verify Territory creation when the Territory name is created using spaces")
    public void AddTerritoryWithSpacesInTerritoryName() {
        getExtentTest().log(Status.INFO, "Test: Verify Territory creation when the Territory name is created using spaces");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState().clickaddTerritorybutton().enterterritoryname(" Territory ").clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofusingspacesinterritorynamefieldsdisplayed = levelPage.isErrorMsgOfUsingSpacesInLevelNameDisplayed();
        Assert.assertTrue(iserrormsgofusingspacesinterritorynamefieldsdisplayed, "Error message for creating the territory name using spaces is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the error message when the Territory name field is created using spaces");
    }
    @Test(priority = 55, description = "Verify Cluster creation when the Cluster name is created using spaces")
    public void AddClusterWithSpacesInClusterName() {
        getExtentTest().log(Status.INFO, "Test: Verify Cluster creation when the Cluster name is created using spaces");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory().clickaddclusterbutton().enterclustername(" Cluster ").clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofusingspacesinclusternamefieldsdisplayed = levelPage.isErrorMsgOfUsingSpacesInLevelNameDisplayed();
        Assert.assertTrue(iserrormsgofusingspacesinclusternamefieldsdisplayed, "Error message for creating the cluster name using spaces is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the error message when the Cluster name field is created using spaces");
    }
    @Test(priority = 56, description = "Verify location creation when the location name is created using spaces")
    public void AddLocationWithSpacesInLocationName() {
        getExtentTest().log(Status.INFO, "Test: Verify location creation when the location name is created using spaces");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory().clickFirstCluster().clickaddlocationbutton().enterlocationname(" Location ").clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean iserrormsgofusingspacesinlocationnamefieldsdisplayed = levelPage.isErrorMsgOfUsingSpacesInLevelNameDisplayed();
        Assert.assertTrue(iserrormsgofusingspacesinlocationnamefieldsdisplayed, "Error message for creating the location name using spaces is not displayed");
        getExtentTest().log(Status.PASS, "Verfied the error message when the location name created using spaces");
    }
    @Test(priority = 57, description = "Verify the duplicate region creation")
    public void AddDuplicateRegion() {
        getExtentTest().log(Status.INFO, "Test: Verify the duplicate region creation");
        levelPage.navigateToLevelPage().clickaddregion().enterregionname("South1").clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean submitbuttonDisplayed = levelPage.isSubmitbuttonDisplayed();
        Assert.assertTrue(submitbuttonDisplayed, "Duplicate region Created Successfully");
        getExtentTest().log(Status.PASS, "Duplicate  region Creation is restricted");
    }
    @Test(priority = 58, description = "Verify the duplicate state creation")
    public void AddDuplicateState() {
        getExtentTest().log(Status.INFO, "Test: Verify the duplicate state creation");
        levelPage.navigateToLevelPage().clickFirstRegion().clickaddstatebutton().enterstatename("kerala").clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean submitbuttonDisplayed = levelPage.isSubmitbuttonDisplayed();
        Assert.assertTrue(submitbuttonDisplayed, "Duplicate state Created Successfully");
        getExtentTest().log(Status.PASS, "Duplicate  state Creation is restricted");
    }
    @Test(priority = 59, description = "Verify the duplicate territory creation")
    public void AddDuplicateTerritory() {
        getExtentTest().log(Status.INFO, "Test: Verify the duplicate territory creation");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState().clickaddTerritorybutton().enterterritoryname("Coimbatore").clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean submitbuttonDisplayed = levelPage.isSubmitbuttonDisplayed();
        Assert.assertTrue(submitbuttonDisplayed, "Duplicate territory Created Successfully");
        getExtentTest().log(Status.PASS, "Duplicate  territory Creation is restricted");
    }
    @Test(priority = 60, description = "Verify the duplicate cluster creation")
    public void AddDuplicateCluster() {
        getExtentTest().log(Status.INFO, "Test: Verify the duplicate cluster creation");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory().clickaddclusterbutton().enterclustername("RS puram").clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean submitbuttonDisplayed = levelPage.isSubmitbuttonDisplayed();
        Assert.assertTrue(submitbuttonDisplayed, "Duplicate cluster Created Successfully");
        getExtentTest().log(Status.PASS, "Duplicate  cluster Creation is restricted");
    }
    @Test(priority = 61, description = "Verify the duplicate location creation")
    public void AddDuplicateLocation() {
        getExtentTest().log(Status.INFO, "Test: Verify the duplicate location creation");
        levelPage.navigateToLevelPage().clickFirstRegion().clickFirstState().clickFirstTerritory().clickFirstCluster().clickaddlocationbutton().enterlocationname("RO1").clicksubmitbutton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        boolean submitbuttonDisplayed = levelPage.isSubmitbuttonDisplayed();
        Assert.assertTrue(submitbuttonDisplayed, "Duplicate location Created Successfully");
        getExtentTest().log(Status.PASS, "Duplicate  location Creation is restricted");

    }
}
