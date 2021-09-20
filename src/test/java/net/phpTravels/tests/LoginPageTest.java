package net.phpTravels.tests;

import net.phpTravels.pages.HomePage;
import net.phpTravels.pages.LoginPage;
import net.phpTravels.pages.MyAccountPage;
import net.phpTravels.utilities.ConfigurationReader;
import net.phpTravels.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static net.phpTravels.utilities.BrowserUtils.*;
import static org.testng.Assert.assertEquals;

public class LoginPageTest extends TestBase{

    @Test(description = "PHP-63",groups = {"smoke"})
    public void verifyLogin() {
        HomePage homePage=new HomePage();
        homePage.clickALinkInMyAccount("My Account");
        homePage.clickALinkInMyAccount("Login");
        LoginPage loginPage=new LoginPage();
        getElement(loginPage.emailInputBoxXpath).sendKeys(ConfigurationReader.get("email"));
        getElement(loginPage.passwordInputBoxXpath).sendKeys(ConfigurationReader.get("password"));
        clickElement(loginPage.logInButtonXpath);
        String expectedUrl="https://www.phptravels.net/account/";
        waitUntilUrlToBe(expectedUrl);
        Assert.assertEquals(Driver.get().getCurrentUrl(),expectedUrl,"My account page is not displayed");
    }

    @Ignore
    @Test(description = "PHP-64")
    public void verifyCheckbox() {
        HomePage homePage=new HomePage();
        homePage.clickALinkInMyAccount("My Account");
        homePage.clickALinkInMyAccount("Login");
        LoginPage loginPage=new LoginPage();
        clickElement(loginPage.checkBoxXpath);
        Assert.assertFalse(elementSelected(loginPage.checkBoxXpath),"Check box is selected");
    }

    @Test(description = "PHP-65",groups = {"smoke"})
    public void verifyForgetPasswordButton() {
        HomePage homePage=new HomePage();
        homePage.clickALinkInMyAccount("My Account");
        homePage.clickALinkInMyAccount("Login");
        LoginPage loginPage=new LoginPage();
        clickElement(loginPage.forgetPasswordButtonXpath);
        Assert.assertTrue(elementDisplayed(loginPage.popUpXpath),"pop up is not displayed");
    }

    @Test(description = "PHP-66")
    public void verifySignupButton() {
        HomePage homePage=new HomePage();
        homePage.clickALinkInMyAccount("My Account");
        homePage.clickALinkInMyAccount("Login");
        LoginPage loginPage=new LoginPage();
        clickElement(loginPage.signUpButtonXpath);
        String ExpectedTitle="Register";
        assertEquals(Driver.get().getTitle(),ExpectedTitle,"Sign up page is not displayed");
    }

    @Ignore
    @Test(description = "dummy account category select")
    public void verifySelectAccountCategory() {
        HomePage homePage=new HomePage();
        homePage.clickALinkInMyAccount("My Account");
        homePage.clickALinkInMyAccount("Login");
        LoginPage loginPage=new LoginPage();
        getElement(loginPage.emailInputBoxXpath).sendKeys(ConfigurationReader.get("email"));
        getElement(loginPage.passwordInputBoxXpath).sendKeys(ConfigurationReader.get("password"));
        clickElement(loginPage.logInButtonXpath);
        waitUntilTitleContains("My Account");
        MyAccountPage myAccountPage = new MyAccountPage();
        myAccountPage.clicksOnAccountCategory("Newsletter");
        String actual = getElement(myAccountPage.accountCategoriesActiveTab).getText();
        String expected = "Newsletter".toUpperCase();
        waitUntilTextToBe(getElement(myAccountPage.accountCategoriesActiveTab),expected);
        assertEquals(actual,expected);
    }
}
