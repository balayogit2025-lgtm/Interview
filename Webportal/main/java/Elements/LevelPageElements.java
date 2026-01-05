package Elements;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LevelPageElements {

    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(LevelPageElements.class);

    @FindBy(xpath = "//span[text()='Level']")
    protected WebElement Level;

    @FindBy(xpath = "//button[text()='Add Region']")
    protected WebElement AddRegion;

    @FindBy(xpath = "//input[@placeholder='Enter region name']")
    protected WebElement Regionname;

    @FindBy(xpath = "//button[text()='Submit']")
    protected WebElement Submitbutton;

    @FindBy(xpath = "(//span[@class='font-medium truncate'])[last()]")
    protected WebElement Newlycreatedregionname;

    @FindBy(xpath = "//button[text()='Add State']")
    protected WebElement Addstate;

    @FindBy(xpath = "//input[@placeholder='Enter state name']")
    protected WebElement Statename;

    @FindBy(xpath = "(//span[@class='font-medium truncate']) [last()]")
    protected WebElement Newlycreatedstatename;

    @FindBy(xpath = "//button[text()='Add Territory']")
    protected WebElement AddTerritory;

    @FindBy(xpath = "//input[@placeholder='Enter territory name']")
    protected WebElement Territoryname;

    @FindBy(xpath = "(//span[@class='font-medium truncate']) [last()]")
    protected WebElement NewlycreatedTerritoryname;

    @FindBy(xpath = "//button[text()='Add Cluster']")
    protected WebElement Addcluster;

    @FindBy(xpath = "//input[@placeholder='Enter cluster name']")
    protected WebElement Clustername;

    @FindBy(xpath = "(//span[@class='font-medium truncate']) [last()]")
    protected WebElement Newlycreatedclustername;

    @FindBy(xpath = "//button[text()='Add Location']")
    protected WebElement AddLocation;

    @FindBy(xpath = "//input[@placeholder='Enter location name']")
    protected WebElement Locationname;

    @FindBy(xpath = "//button[text()='Cancel']")
    protected WebElement Cancelbutton;

    @FindBy(xpath = "(//button[@data-slot='popover-trigger']) [1]")
    protected WebElement Managebutton;

    @FindBy(xpath = "//button[text()='Edit']")
    protected WebElement Editbutton;

    @FindBy(xpath = "//button[@aria-label='Expand search']")
    protected WebElement Searchicon;

    @FindBy(xpath = "//input[@placeholder='Search region...']")
    protected WebElement Searchregion;

    @FindBy(xpath = "//input[@placeholder='Search state...']")
    protected WebElement Searchstate;

    @FindBy(xpath = "//input[@placeholder='Search territory...']")
    protected WebElement Searchterritory;

    @FindBy(xpath = "//input[@placeholder='Search cluster...']")
    protected WebElement Searchcluster;

    @FindBy(xpath = "//input[@placeholder='Search location...']")
    protected WebElement Searchlocation;

    @FindBy(xpath = "//tr[@class='border-y border-transparent border-b-slate-100 bg-white dark:bg-slate-900']  |  //tr[@class='border-y border-transparent border-b-slate-100 bg-gray-50 dark:bg-slate-800']")
    private List<WebElement> Leveltablerows;

   @FindBy(xpath = "//td[text()='No results found']")
    protected WebElement ErrormsgofNoResults;

    @FindBy(xpath = "(//span[@class='font-medium truncate']) [2]")
    protected WebElement FirstRegion;

    @FindBy(xpath = "(//span[@class='font-medium truncate']) [3]")
    protected WebElement FirstState;

    @FindBy(xpath = "(//span[@class='font-medium truncate']) [4]")
    protected WebElement FirstTerritory;

    @FindBy(xpath = "(//span[@class='font-medium truncate']) [5]")
    protected WebElement FirstCluster;

    @FindBy(xpath = "(//span[@class='text-xl']) [4]")
    protected WebElement LastPageButton;

    @FindBy(xpath = "//th[@class='border border-primary-50 whitespace-nowrap bg-primary/80 px-4 py-1.5 uppercase text-primary-foreground text-xs+ lg:px-5']")
    private List<WebElement> Listofheaders;

    @FindBy(xpath = "//table[@class='w-full text-left  border border-primary-50 rounded-lg table']")
    protected WebElement Leveltable;

    @FindBy(xpath = "//p[text()='name should contain minimum 2 characters']")
    protected WebElement ErrorMsgofEmptyLevelName;

    @FindBy(xpath = "//p[text()='name should start and end with an alphanumeric character']")
    protected WebElement ErrorMsgForUsingspaceInLevelName;

  /*  @FindBy(xpath = "")
    protected WebElement ;

    @FindBy(xpath = "")
    protected WebElement ;

    @FindBy(xpath = "")
    protected WebElement ;*/


    public LevelPageElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("LevelPageElement initialized");
    }

    public WebElement getLevel() {return Level;}

    public WebElement getAddRegion() {return AddRegion;}

    public WebElement getRegionname() {return Regionname;}

    public WebElement getSubmitbutton() {return Submitbutton;}

    public WebElement getAddstate() {return Addstate;}

    public WebElement getStatename() {return Statename;}

   public WebElement getAddTerritory() {return AddTerritory;}

    public WebElement getTerritoryname() {return Territoryname;}

    public WebElement getAddcluster() {return Addcluster;}

    public WebElement getClustername() {return Clustername;}

    public WebElement getAddLocation() {return AddLocation;}

    public WebElement getLocationname() {return Locationname;}

    public WebElement getNewlycreatedregionname() {return Newlycreatedregionname;}

    public WebElement getNewlycreatedstatename() {return Newlycreatedstatename;}

    public WebElement getNewlycreatedTerritoryname() {return NewlycreatedTerritoryname;}

    public WebElement getNewlycreatedclustername() {return Newlycreatedclustername;}

    public WebElement getCancelbutton() {return Cancelbutton;}

    public WebElement getManagebutton() {return Managebutton;}

    public WebElement getEditbutton() {return Editbutton;}

    public WebElement getSearchicon() {return Searchicon;}

    public WebElement getSearchregion() {return Searchregion;}

    public WebElement getSearchstate() {return Searchstate;}

    public WebElement getSearchterritory() {return Searchterritory;}

    public WebElement getSearchcluster() {return Searchcluster;}

    public WebElement getSearchlocation() {return Searchlocation;}

    public List<WebElement> getLeveltablerows() {return Leveltablerows;}

    public WebElement getErrormsgofNoResults() {return ErrormsgofNoResults;}

    public WebElement getFirstRegion() {return FirstRegion;}

    public WebElement getFirstState() {return FirstState;}

    public WebElement getFirstTerritory() {return FirstTerritory;}

    public WebElement getFirstCluster() {return FirstCluster;}

    public WebElement getLastPageButton() {return LastPageButton;}

    public List<WebElement> getListofheaders() {return Listofheaders;}

    public WebElement getLeveltable() {return Leveltable;}

    public WebElement getErrorMsgofEmptyLevelName() {return ErrorMsgofEmptyLevelName;}

    public WebElement getErrorMsgForUsingspaceInLevelName() {return ErrorMsgForUsingspaceInLevelName;}


 //levelpageelements

}
