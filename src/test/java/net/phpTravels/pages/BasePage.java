package net.phpTravels.pages;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.phpTravels.utilities.BrowserUtils.*;

public abstract class BasePage {

    //Header  Elements
    public final String My_Account_Menu = "//div[contains(@class,'dropdown')]//a[contains(.,'%s')]";
    public final String phoneNumberXpath = "//div[contains(@class,'navbar-phone')]";
    public final String companyButtonXpath = "//div[@class='header-nav']//a[.='company']";
    public final String contactButtonXpath = "//div[@class='header-nav']//a[contains(@href,'contact')]";
    public final String aboutUsButtonXpath = "//div[@class='header-nav']//a[contains(@href,'about')]";
    public final String homeOptionXpath = "//a[.='Home']";
    public final String companyLogoXpath = "//img[@class='']";
    public final String languageTab = "//a[@id='dropdownLangauge']";
    public final String randomLanguage = "//div[contains(@class,'dropdown-menu-right ')]//a";
    public final String currencyDropdownXpath = "//div[contains(@class,'dropdown dropdown-currency')]/a";
    public final String currencyDropdownMenuXpath = "//div[@class='dropdown-menu dropdown-menu-right show']//a";

    //Footer Elements
    public final String GEN_FOOTER_LINK_XPATH = "//footer//a[contains(.,'%s')]";
    public final String BACK_TO_TOP_ICON = "//a[@id='back-to-top']";
    public final String supportModule_Links_XPATH = "//a[.='support']/..//li/a";
    public final String FooterLinks = "//footer//li//a[contains(.,'%s')]/..//li/a";
    public final String social_media_XPATH = "//a[@target='_black']";
    public final String appStoreXpath = "(//a[contains(@class,'app')])[1]";
    public final String playMarketXpath = "(//a[contains(@class,'app')])[2]";
    public final String PoweredByXpath = "//div[@class='text-center']";
    public final String CopyRightXpath = "//p[@class='footer-copy-right']";
    public final String SponsorsLinkXpath = "//*[@id='footer']/div[1]/div[2]/div/div/a";
    public final String subscribeMessage = "//div[@class='wow fadeIn subscriberesponse']";
    public final String subscriptionEmailInputBox = "//input[@id='exampleInputEmail1']";
    public final String subscribeButton = "//button[@class='btn btn-secondary sub_newsletter']";


    public boolean containExpectedCurrencies(String[]expectedCurrencies) {
        List<String> allCurrencies = new ArrayList<>();
        List<WebElement> currency = getElements(currencyDropdownMenuXpath);
        for (WebElement each : currency) {
            allCurrencies.add(each.getText());
        }
        for (String each : expectedCurrencies) {
            if (!allCurrencies.contains(each)) {
                return false;
            }
        }
        return true;
    }

    /**
     *This method will click on the selected element
     * @param text;
     */
    public void clickALinkInMyAccount(String text) {
        clickElement(My_Account_Menu, text);
    }

    public boolean containAllLanguages(String[]expectedLanguages) {
        List<String> allLanguages = new ArrayList<>();
        List<WebElement> languages = getElements(randomLanguage);
        for (WebElement each : languages) {
            allLanguages.add(each.getText());
        }
        for (String each : expectedLanguages) {
            if (!allLanguages.contains(each)) {
                return false;
            }
        }
        return true;
    }

    //Footer Elements & Methods

    public void navigateToFooterLink(String item) {
        scrollTo(GEN_FOOTER_LINK_XPATH, item);
        clickElement(GEN_FOOTER_LINK_XPATH, item);
    }

    public void scrollToFooter(String item) {
        scrollTo(GEN_FOOTER_LINK_XPATH, item);
    }

    public void backToTop() {
        clickElement(BACK_TO_TOP_ICON);
    }

    public boolean pageAtTop() {
        return elementDisplayed(companyLogoXpath);
    }

    /**
     *This method will get the links under Company
     * @param value;
     * @return list of footer links
     */
    public List<WebElement> GetFooterLink(String value){
        return getElements(String.format(FooterLinks,value));
    }

    /**
     *This method will get random links in footer
     * @param value;
     * @return random footer link
     */
    public WebElement getRandomFooterLink(String value){
       List<WebElement> links = getElements(String.format(FooterLinks,value));
       Random random = new Random();
       return links.get(random.nextInt(links.size()));
    }
}