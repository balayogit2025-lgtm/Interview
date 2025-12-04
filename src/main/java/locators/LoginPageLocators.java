package com.sterna.ams.pages.locators;

import org.openqa.selenium.By;

/**
 * Locators class for LoginPage.
 * Contains all element locators for the Login page.
 */
public class LoginPageLocators {

    // ==================== Email/Username Field Locators ====================
    public static final String EMAIL_ID = "email";
    public static final String EMAIL_NAME = "email";
    public static final String EMAIL_XPATH = "//input[@type='email']";
    public static final By EMAIL_INPUT = By.cssSelector("input[type='email'], input[name='email'], input#email");

    // ==================== Password Field Locators ====================
    public static final String PASSWORD_ID = "password";
    public static final String PASSWORD_NAME = "password";
    public static final String PASSWORD_XPATH = "//input[@type='password']";
    public static final By PASSWORD_INPUT = By.cssSelector("input[type='password'], input[name='password'], input#password");

    // ==================== Login Button Locators ====================
    public static final String LOGIN_BUTTON_XPATH = "//button[@type='submit']";
    public static final String LOGIN_BUTTON_BY_TEXT_XPATH = "//button[contains(text(),'Login') or contains(text(),'Sign In') or contains(text(),'Log In')]";
    public static final By LOGIN_BUTTON = By.cssSelector("button[type='submit'], input[type='submit']");

    // ==================== Remember Me Checkbox Locators ====================
    public static final String REMEMBER_ME_ID = "remember";
    public static final String REMEMBER_ME_XPATH = "//input[@type='checkbox']";

    // ==================== Forgot Password Link Locators ====================
    public static final String FORGOT_PASSWORD_XPATH = "//a[contains(text(),'Forgot') or contains(@href,'forgot')]";

    // ==================== Error Message Locators ====================
    public static final String ERROR_MESSAGE_XPATH = "//div[contains(@class,'error') or contains(@class,'alert')]";
    public static final String VALIDATION_ERROR_XPATH = "//span[contains(@class,'error') or contains(@class,'invalid')]";
    public static final By ERROR_MESSAGE = By.xpath("//*[contains(@class,'error') or contains(@class,'alert') or contains(@class,'invalid')]");

    // ==================== Page Header Locators ====================
    public static final String PAGE_HEADER_XPATH = "//h1 | //h2[contains(text(),'Login') or contains(text(),'Sign In')]";

    private LoginPageLocators() {
        // Private constructor to prevent instantiation
    }
}
