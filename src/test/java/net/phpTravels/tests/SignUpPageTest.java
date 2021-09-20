package net.phpTravels.tests;

import net.phpTravels.pages.HomePage;
import net.phpTravels.pages.SignUpPage;
import net.phpTravels.utilities.ConfigurationReader;
import net.phpTravels.utilities.DataUtils;
import net.phpTravels.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static net.phpTravels.utilities.BrowserUtils.*;

public class SignUpPageTest extends TestBase{


    @Test(description = "PHPT-60",groups = {"smoke"})
    public void verifySignUp(){
        HomePage homePage = new HomePage();
        homePage.clickALinkInMyAccount("My Account");
        homePage.clickALinkInMyAccount("Sign Up");
        String firstName = DataUtils.generateFirstName();
        String lastName = DataUtils.generateLastName();
        String mobileNumber = DataUtils.generatePhoneNumber();
        String email = DataUtils.generatesRandomEmail();
        String password = ConfigurationReader.get("password");
        String confirmPassword = ConfigurationReader.get("password");
        SignUpPage signUpPage = new SignUpPage();
        getElement(signUpPage.firstNameInputXPATH).sendKeys(firstName);
        getElement(signUpPage.lastNameInputXPATH).sendKeys(lastName);
        getElement(signUpPage.mobileNumberXPATH).sendKeys(mobileNumber);
        getElement(signUpPage.emailXPATH).sendKeys(email);
        getElement(signUpPage.passwordXPATH).sendKeys(password);
        getElement(signUpPage.confirmPasswordXPATH).sendKeys(confirmPassword);
        clickElement(signUpPage.signUpButtonXPATH);
        String expectedTitle = "Register";
        String actualTitle = Driver.get().getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @Test(description = "PHPT-61")
    public void verifyNoRepeatSignUp(){
        HomePage homePage = new HomePage();
        homePage.clickALinkInMyAccount("My Account");
        homePage.clickALinkInMyAccount("Sign Up");
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.signUpWithRandomInfo();
        signUpPage.backToSignUpPage();
        signUpPage.signUpAttemptWithPrevInfo();
        Assert.assertTrue(elementDisplayed(signUpPage.warningMessageXPATH),"Alert message is not displayed");
    }
}
