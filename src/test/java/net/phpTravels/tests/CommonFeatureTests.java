package net.phpTravels.tests;

import net.phpTravels.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static net.phpTravels.utilities.BrowserUtils.*;
import static net.phpTravels.utilities.DataUtils.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CommonFeatureTests extends TestBase {

    @Test(description = "PHPT-9")
    public void test1_MyAccountVisibilityVerification() {
        HomePage homePage = new HomePage();
        assertTrue(elementDisplayed(homePage.My_Account_Menu,"My Account"),"My Account tab is NOT displayed");
    }

    @Test(description = "PHPT-10",groups = {"smoke"})
    public void test2_AccessToLogInPageVerification() {
        HomePage homePage = new HomePage();
        String expectedUrl = "https://www.phptravels.net/login";
        homePage.clickALinkInMyAccount("My Account");
        homePage.clickALinkInMyAccount("Login");
        waitUntilUrlToBe(expectedUrl);
        assertEquals(getCurrentPageURL(), expectedUrl, "Invalid URL");
    }

    @Test(description = "PHPT-11",groups = {"smoke"})
    public void test3_AccessToSignUpPageVerification() {
        HomePage homePage = new HomePage();
        String expectedURL = "https://www.phptravels.net/register";
        homePage.clickALinkInMyAccount("My Account");
        homePage.clickALinkInMyAccount("Sign Up");
        waitUntilUrlToBe(expectedURL);
        assertEquals(getCurrentPageURL(), expectedURL, "Invalid URL");
    }

    @Test(description = "PHPT-19")
    public void verifySupplierOptions() {
        HomePage homePage = new HomePage();
        Map.Entry<String, String> testData = getTestData("SupplierLink");
        homePage.navigateToFooterLink(testData.getKey());
        assertEquals(getChildWindowTitle(),
                testData.getValue(), "Page Title Does Not Match!");
    }

    @Test(description = "PHPT-22")
    public void verifyCompanyPhoneNumber() {
        HomePage homePage = new HomePage();
        String expectedCompanyPhoneNumber = "phone Call Now : +1-234-56789";
        String actualPhoneNumber = getElement(homePage.phoneNumberXpath).getText();
        waitUntilTextToBe(getElement(homePage.phoneNumberXpath),expectedCompanyPhoneNumber);
        assertEquals(actualPhoneNumber,expectedCompanyPhoneNumber,"Verify the phone number");
    }

    @Test(description = "PHPT-6",groups = {"smoke"})
    public void verifyContactPage() {
        HomePage homePage = new HomePage();
        String contactPageUrl = "https://www.phptravels.net/contact-us";
        hoverOver(homePage.companyButtonXpath);
        clickElement(homePage.contactButtonXpath);
        waitUntilUrlToBe(contactPageUrl);
        assertEquals(getCurrentPageURL(), contactPageUrl, "Contact page is not displayed");
    }

    @Test(description = "PHPT-7")
    public void verifyAboutUsPage() {
        HomePage homePage = new HomePage();
        String aboutUsPageURL = "https://www.phptravels.net/about-Us";
        hoverOver(homePage.companyButtonXpath);
        clickElement(homePage.aboutUsButtonXpath);
        waitUntilUrlToBe(aboutUsPageURL);
        assertEquals(getCurrentPageURL(), aboutUsPageURL, "ABOUT US page is not displayed");
    }

    @Test(description = "PHPT-25",groups = {"smoke"})
    public void verifyHomeOption() {
        HomePage homePage = new HomePage();
        String homePageUrl = "https://www.phptravels.net/home";
        clickElement(homePage.homeOptionXpath);
        waitUntilUrlToBe(homePageUrl);
        assertEquals(getCurrentPageURL(), homePageUrl, "verify URL does not match");
    }

    @Test(description = "PHPT-24")
    public void verifyCompanyLogo() {
        HomePage homePage = new HomePage();
        String homePageUrl = "https://www.phptravels.net/home";
        clickElement(homePage.companyLogoXpath);
        waitUntilUrlToBe(homePageUrl);
        assertEquals(getCurrentPageURL(),homePageUrl, "verify URL does not match");
    }

    @Test(description = "PHPT - 17")
    public void verifyAllLanguagesAdded() {
        HomePage homePage = new HomePage();
        clickElement(homePage.languageTab);
        String[] expectedLanguages = {"Vietnamese", "Russian", "English", "Arabic", "Farsi", "Turkish", "French", "Spanish", "German"};
        assertTrue(homePage.containAllLanguages(expectedLanguages));
    }

    @Test(description = "PHPT - 18")
    public void verifySelectDifferentLanguages() {
        String[] expectedLanguages = {"Vietnamese", "Russian", "English", "Arabic", "Farsi", "Turkish", "French", "Spanish", "German"};
        HomePage homePage = new HomePage();
        clickElement(homePage.languageTab);
        int index = generateSingleNum(expectedLanguages.length);
        List<WebElement>languages = getElements(homePage.randomLanguage);
        clickElement(languages.get(index));
        String actual = getElement(homePage.languageTab).getText();
        String expected = expectedLanguages[index].toUpperCase();
        waitUntilTextToBe(getElement(homePage.languageTab),expected);
        Assert.assertEquals(actual,expected);
    }

    @Test(description = "PHPT - 23",groups = {"smoke"})
    public void verifyDefaultLanguage() {
        HomePage homePage = new HomePage();
        assertEquals(getElement(homePage.languageTab).getText(), "ENGLISH");
    }


    @Test(description = "PHPT-12")
    public void VerifyAllExpectedCurrenciesAreAddedAsOptions() {
        HomePage homePage = new HomePage();
        clickElement(homePage.currencyDropdownXpath);
        String[] expectedCurrency = {"USD", "GBP", "SAR", "EUR", "PKR", "KWD", "JPY", "INR", "CNY","TRY","RUB"};
        assertTrue(homePage.containExpectedCurrencies(expectedCurrency));
    }

    @Test(description = "PHPT-13")
    public void VerifyUserCanSelectDifferentCurrencies() {
        String[] expectedCurrencies = {"USD", "GBP", "ريال", "EUR", "RS", "KWD", "JPY", "INR", "CNY", "TURKISH", "RUB",};
        HomePage homePage = new HomePage();
        clickElement(homePage.currencyDropdownXpath);
        int index = generateSingleNum(expectedCurrencies.length);
        List<WebElement>elements = getElements(homePage.currencyDropdownMenuXpath);
        clickElement(elements.get(index));
        String expectedCurrency = expectedCurrencies[index];
        WebElement element = getElement(homePage.currencyDropdownXpath);
        try{
            waitUntilTextToBe(element,expectedCurrencies[index]);
        }catch (Exception e){
            element = getElement(homePage.currencyDropdownXpath);
        }
        assertEquals(element.getText(),expectedCurrency);
    }

    @Test(description = "PHPT-14",groups = {"smoke"})
    public void VerifyDefaultCurrencyIsUSD() {
        HomePage homePage = new HomePage();
        assertEquals(getElement(homePage.currencyDropdownXpath).getText(),
                "USD","Currency is not displayed as USD");
    }

    //Footer tests
    @Test(description = "PHPT-29")
    public void verifyBackToTopIcon() {
        HomePage homePage = new HomePage();
        homePage.scrollToFooter("Supplier Login");
        homePage.backToTop();
        assertTrue(homePage.pageAtTop(), "Page Not At Top");
    }

    @Test(description = "PHPT - (37,38,39,41)")
    public void verifySupportModuleOptions() {
        HomePage homePage = new HomePage();
        String[] supportModLinkTexts = {"FAQ","Our Partners","Privacy Policy","Terms of Use"};
        List<String> linkTexts = getTextOfElements(getElements(homePage.supportModule_Links_XPATH));
        for (int i = 0; i < linkTexts.size(); i++) {
            String eachLink = linkTexts.get(i);
            homePage.navigateToFooterLink(eachLink);
            waitUntilTitleContains(supportModLinkTexts[i]);
            assertEquals(getCurrentPageTitle(), supportModLinkTexts[i], "Page title is incorrect");
            navigateBack();
            linkTexts = getTextOfElements(getElements(homePage.supportModule_Links_XPATH));
        }
    }

    @Test(description = "PHPT-31,PHPT-32,PHPT-33,PHPT-34")
    public void LinksUnderCompanyVerification(){
        HomePage homePage = new HomePage();
        String[]urls={"https://www.phptravels.net/contact-us",
                "https://www.phptravels.net/How-to-Book",
                "https://www.phptravels.net/Booking-Tips",
                "https://www.phptravels.net/about-Us"};
        List<WebElement> elements = homePage.GetFooterLink("company");
        for (int i = 0; i < elements.size(); i++) {
            scrollTo(elements.get(i));
            clickElement(elements.get(i));
            waitUntilUrlToBe(urls[i]);
            assertEquals(getCurrentPageURL(),urls[i]);
            navigateBack();
            elements = homePage.GetFooterLink("company");
        }
    }

    @Test(description = "PHPT - (47,48,49,50,51,52)")
    public void verifyAllSocialMedia() {
        HomePage homePage = new HomePage();
        String[] allSocialMediaTitles = {"Facebook", "Twitter", "YouTube", "WhatsApp", "Google Maps", "Instagram"};
        List<WebElement> socialMedia = getElements(homePage.social_media_XPATH);
        for (int i = 0; i < socialMedia.size(); i++) {
            WebElement eachSocialMedia = socialMedia.get(i);
            scrollTo(eachSocialMedia);
            eachSocialMedia.click();
            switchToChildWindow();
            waitUntilTitleContains(allSocialMediaTitles[i]);
            String expectedTitle = allSocialMediaTitles[i];
            String actualTitle = getCurrentPageTitle();
            assertTrue(actualTitle.contains(expectedTitle));
            closeCurrentPage();
            switchToParentWindow();
        }
    }

    @Test(description = "PHPT-44",groups = {"smoke"})
    public void appStoreDownloadVerification() {
        HomePage homePage = new HomePage();
        scrollTo(homePage.appStoreXpath);
        clickElement(homePage.appStoreXpath);
        String expectedTitle="Connecting to the iTunes Store.";
        String actualTitle = getChildWindowTitle();
        assertEquals(actualTitle,expectedTitle,"App Store page is not displayed");
        }

    @Test(description = "PHPT-45",groups = {"smoke"})
    public void playMarketDownloadVerification() {
        HomePage homePage = new HomePage();
        scrollTo(homePage.playMarketXpath);
        clickElement(homePage.playMarketXpath);
        String playMarketTitle = "PHPTRAVELS Native - Apps on Google Play";
        String actualTitle = getChildWindowTitle();
        assertEquals(actualTitle,playMarketTitle,"Google Play page is not displayed");
    }

    @Test (description = "PHPT-56")
    public void verifyPoweredByInformation() {
        HomePage homePage = new HomePage();
        String poweredByText = "Powered by PHPTRAVELS";
        String actualText = getElement(homePage.PoweredByXpath).getText();
        assertEquals(actualText,poweredByText, "Powered by information is not present or it is incorrect");
    }

    @Test (description = "PHPT-57")
    public void verifyCopyRightInformation() {
        HomePage homePage = new HomePage();
        scrollTo(homePage.CopyRightXpath);
        String copyRightText = "All Rights Reserved by PHPTRAVELS";
        String actualText = getElement(homePage.CopyRightXpath).getText();
        assertTrue(actualText.contains(copyRightText),
                "Copy right information is not present or it is incorrect");
    }

    @Test (description = "PHPT-58")
    public void verifySponsorsPage() {
        HomePage homePage = new HomePage();
        String sponsorPageURL = "https://www.phptravels.com/";
        homePage.scrollToFooter("PHPTRAVELS");
        clickElement(homePage.SponsorsLinkXpath);
        switchToChildWindow();
        waitUntilUrlToBe(sponsorPageURL);
        assertEquals(getCurrentPageURL(),sponsorPageURL,"Sponsor's page is not displayed");
    }

    @Test(description = "PHPT-36",groups = {"smoke"})
    public void verifyUserCanSubscribeToNewsletter() {
        HomePage homePage = new HomePage();
        scrollTo(homePage.subscriptionEmailInputBox);
        clickAndSend(homePage.subscriptionEmailInputBox,generatesRandomEmail());
        clickElement(homePage.subscribeButton);
        assertTrue( waitUntilTextToBe(getElement(homePage.subscribeMessage),"Subscribed Successfully"));
    }

    @Test(description = "PHPT-40")
    public void verifyDuplicatedEmail() {
        HomePage homePage = new HomePage();
        scrollTo(homePage.subscriptionEmailInputBox);
        clickAndSend(homePage.subscriptionEmailInputBox,generatesRandomEmail());
        clickElement(homePage.subscribeButton);
        clickElement(homePage.subscribeButton);
        assertTrue(waitUntilTextToBe(getElement(homePage.subscribeMessage),"Already Subscribed"));
    }

    @Test(description = "PHPT-53")
    public void verifyInvalidEmailAddressMessage() {
        HomePage homePage = new HomePage();
        scrollTo(homePage.subscriptionEmailInputBox);
        clickAndSend(homePage.subscriptionEmailInputBox,generatesRandomFullName());
        clickElement(homePage.subscribeButton);
        assertTrue(waitUntilTextToBe(getElement(homePage.subscribeMessage),"Kindly Enter a Valid Email Address."));
    }

    @Test(description = "PHPT-54")
    public void verifyEmailFieldIsEmptyMessage() {
        HomePage homePage = new HomePage();
        scrollTo(homePage.subscriptionEmailInputBox);
        clickElement(homePage.subscribeButton);
        assertTrue( waitUntilTextToBe(getElement(homePage.subscribeMessage),"The Email field is required."));
    }
}