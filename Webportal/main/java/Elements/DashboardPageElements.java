package Elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class DashboardPageElements {

  //  private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(DashboardPageElements.class);


    public By getDashboardBreadcrumpHeaderTitle() {
        return dashboardBreadcrumpHeaderTitle;
    }

    // Page heading: Dashboard
    public By dashboardBreadcrumpHeaderTitle = By.xpath("//h1[normalize-space()='Dashboard']");

    public By getDashboardBreadcrumpHeaderSubtitle() {
        return dashboardBreadcrumpHeaderSubtitle;
    }

    // Sub text: Welcome to Access Management System
    public By dashboardBreadcrumpHeaderSubtitle = By.xpath("//div[@class='ml-1']//p[contains(@class,'slogan')]");

    // Greeting text: Good Afternoon, sterna admin
    public By greetingText = By.xpath("//span[contains(text(),'Good')]");

    // Header date: e.g. 09 Dec 2025
    public By headerDate = By.xpath("//div[contains(@class,'flex flex-row text-center items-center justify-center')]/div[1]");

    // Header time: e.g. 02:54:29 PM
    public By headerTime = By.xpath("//div[contains(@class,'flex flex-row text-center items-center justify-center')]/div[last()]");

    // ---------- Top metric cards (Activity / Roles / Users / Web Users / Key Safe Users) ----------

    // Card: Activity (title)
    public By cardActivityTitle = By.xpath("//div[text()='Activity']");

    // Card: Activity count (6)
    public By cardActivityCount = By.xpath("//div[contains(@class,'grid')]//div[.//div[normalize-space()='Activity']]//div[contains(@class,'text-3xl')]");

    // Card: Total Roles (title)
    public By cardTotalRolesTitle = By.xpath("//div[text()='Total Roles']");

    // Card: Total Roles count (18)
    public By cardTotalRolesCount = By.xpath("//div[contains(@class,'grid')]//div[.//div[normalize-space()='Total Roles']]//div[contains(@class,'text-3xl')]");

    // Card: Total Users (title) - Fixed syntax error from original
    public By cardTotalUsersTitle = By.xpath("//div[text()='Total Users']");

    // Card: Total Users count (20)
    public By cardTotalUsersCount = By.xpath("//div[contains(@class,'grid')]//div[.//div[normalize-space()='Total Users']]//div[contains(@class,'text-3xl')]");

    // Card: Web Users (title)
    public By cardWebUsersTitle = By.xpath("//div[text()='Web Users']");

    // Card: Web Users count (19)
    public By cardWebUsersCount = By.xpath("//div[contains(@class,'grid')]//div[.//div[normalize-space()='Web Users']]//div[contains(@class,'text-3xl')]");

    // Card: Key Safe Users (title)
    public By cardKeySafeUsersTitle = By.xpath("//div[text()='Key Safe Users']");

    // Card: Key Safe Users count (9)
    public By cardKeySafeUsersCount = By.xpath("//div[contains(@class,'grid')]//div[.//div[normalize-space()='Key Safe Users']]//div[contains(@class,'text-3xl')]");

    // ---------- Donut / Pie chart cards (Key Box / Users / Activities) ----------

    // Key Box chart card title
    public By keyBoxChartTitle = By.xpath("//div[@data-slot='card-title' and normalize-space()='Key Box']");

    // Key Box chart container
    public By keyBoxChartContainer = By.xpath("//div[@data-slot='card-title' and normalize-space()='Key Box']/ancestor::div[@data-slot='card']//div[@data-slot='chart' and @data-chart='chart-_r_k_']");

    // Key Box chart center percentage (20%)
    public By keyBoxChartCenterValue = By.xpath("//div[@data-chart='chart-_r_k_']//tspan[contains(@class,'text-3xl')]");

    // Key Box legend: Registered
    public By keyBoxLegendRegistered = By.xpath("//div[@data-slot='card-title' and normalize-space()='Key Box']/ancestor::div[@data-slot='card']//span[normalize-space()='Registered']");

    // Key Box legend: Unregistered
    public By keyBoxLegendUnregistered = By.xpath("//div[@data-slot='card-title' and normalize-space()='Key Box']/ancestor::div[@data-slot='card']//span[normalize-space()='Unregistered']");

    // Users chart card title
    public By usersChartTitle = By.xpath("//div[@data-slot='card-title' and normalize-space()='Users']");

    // Users chart container
    public By usersChartContainer = By.xpath("//div[@data-slot='card-title' and normalize-space()='Users']/ancestor::div[@data-slot='card']//div[@data-slot='chart' and @data-chart='chart-_r_l_']");

    // Users chart center percentage (100%)
    public By usersChartCenterValue = By.xpath("//div[@data-chart='chart-_r_l_']//tspan[contains(@class,'text-3xl')]");

    // Users legend: Active
    public By usersLegendActive = By.xpath("//div[@data-slot='card-title' and normalize-space()='Users']/ancestor::div[@data-slot='card']//span[normalize-space()='Active']");

    // Users legend: Inactive
    public By usersLegendInactive = By.xpath("//div[@data-slot='card-title' and normalize-space()='Users']/ancestor::div[@data-slot='card']//span[normalize-space()='Inactive']");

    // Activities chart card title
    public By activitiesChartTitle = By.xpath("//div[@data-slot='card-title' and normalize-space()='Activities']");

    // Activities chart container
    public By activitiesChartContainer = By.xpath("//div[@data-slot='card-title' and normalize-space()='Activities']/ancestor::div[@data-slot='card']//div[@data-slot='chart' and @data-chart='chart-_r_m_']");

    // Activities chart center percentage (100%)
    public By activitiesChartCenterValue = By.xpath("//div[@data-chart='chart-_r_m_']//tspan[contains(@class,'text-3xl')]");

    // Activities legend: Active
    public By activitiesLegendActive = By.xpath("//div[@data-slot='card-title' and normalize-space()='Activities']/ancestor::div[@data-slot='card']//span[normalize-space()='Active']");

    // Activities legend: Inactive
    public By activitiesLegendInactive = By.xpath("//div[@data-slot='card-title' and normalize-space()='Activities']/ancestor::div[@data-slot='card']//span[normalize-space()='Inactive']");





}