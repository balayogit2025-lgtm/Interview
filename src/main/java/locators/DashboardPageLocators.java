package com.sterna.ams.pages.locators;

import org.openqa.selenium.By;

/**
 * Locators class for DashboardPage.
 * Contains all element locators for the Dashboard page.
 */
public class DashboardPageLocators {

    // ==================== Dashboard Header/Title Locators ====================
    public static final String DASHBOARD_HEADER_XPATH = "//h1[contains(text(),'Dashboard')] | //h2[contains(text(),'Dashboard')] | //span[contains(text(),'Dashboard')]";
    public static final By DASHBOARD_INDICATOR = By.xpath("//*[contains(text(),'Dashboard')] | //nav | //div[contains(@class,'sidebar')]");

    // ==================== User Profile Locators ====================
    public static final String USER_PROFILE_XPATH = "//div[contains(@class,'profile')] | //div[contains(@class,'avatar')] | //img[contains(@class,'avatar')]";
    public static final By USER_MENU = By.xpath("//div[contains(@class,'user-menu')] | //div[contains(@class,'profile')] | //button[contains(@class,'user')]");

    // ==================== Username Display Locators ====================
    public static final String USERNAME_DISPLAY_XPATH = "//span[contains(@class,'user')] | //div[contains(@class,'user-name')]";

    // ==================== Logout Locators ====================
    public static final String LOGOUT_BUTTON_XPATH = "//button[contains(text(),'Logout') or contains(text(),'Log Out') or contains(text(),'Sign Out')] | //a[contains(text(),'Logout')]";
    public static final String LOGOUT_ELEMENT_XPATH = "//*[contains(@class,'logout')] | //*[contains(@id,'logout')]";
    public static final By LOGOUT = By.xpath("//*[contains(text(),'Logout') or contains(text(),'Log out') or contains(text(),'Sign out')] | //*[contains(@class,'logout')]");

    // ==================== Navigation Menu Locators ====================
    public static final String NAVIGATION_MENU_XPATH = "//nav | //div[contains(@class,'sidebar')] | //div[contains(@class,'menu')]";

    // ==================== Settings Link Locators ====================
    public static final String SETTINGS_LINK_XPATH = "//a[contains(text(),'Settings')] | //*[contains(@class,'settings')]";

    // ==================== Welcome Message Locators ====================
    public static final String WELCOME_MESSAGE_XPATH = "//*[contains(text(),'Welcome')] | //*[contains(text(),'Hello')]";

    // ==================== Main Content Locators ====================
    public static final String MAIN_CONTENT_XPATH = "//div[contains(@class,'dashboard')] | //main | //div[contains(@class,'content')]";

    private DashboardPageLocators() {
        // Private constructor to prevent instantiation
    }
}
