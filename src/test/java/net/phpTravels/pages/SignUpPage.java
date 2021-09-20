package net.phpTravels.pages;

import net.phpTravels.utilities.ConfigurationReader;
import net.phpTravels.utilities.GlobalDataUtil;

import java.util.ArrayList;
import java.util.Arrays;

import static net.phpTravels.utilities.BrowserUtils.*;
import static net.phpTravels.utilities.DataUtils.*;

public class SignUpPage extends BasePage {

    GlobalDataUtil signUp = new GlobalDataUtil();
    public final String firstNameInputXPATH = "//input[@name='firstname']";
    public final String lastNameInputXPATH = "//input[@name='lastname']";
    public final String mobileNumberXPATH = "//input[contains(@name,'phone')]";
    public final String emailXPATH = "//input[contains(@name,'email')]";
    public final String passwordXPATH = "//input[@name='password'][1]";
    public final String confirmPasswordXPATH = "//input[contains(@name,'confirmpassword')]";
    public final String signUpButtonXPATH = "//button[contains(@class,'signupbtn')]";
    public final String userNameXPATH = "//i[contains(@class,'user')]";
    public final String warningMessageXPATH = "//div[contains(@class,'alert')]";

    public void signUpWithRandomInfo(){
        String firstName = generateFirstName();
        String lastName = generateLastName();
        String mobileNumber = generatePhoneNumber();
        String email = generatesRandomEmail();
        String password = ConfigurationReader.get("password");
        String confirmPassword = ConfigurationReader.get("password");
        signUp.setPersonalInfo(new ArrayList<>(Arrays.asList(firstName,lastName,mobileNumber,email,
                password,confirmPassword)));
        getElement(firstNameInputXPATH).sendKeys(firstName);
        getElement(lastNameInputXPATH).sendKeys(lastName);
        getElement(mobileNumberXPATH).sendKeys(mobileNumber);
        getElement(emailXPATH).sendKeys(email);
        getElement(passwordXPATH).sendKeys(password);
        getElement(confirmPasswordXPATH).sendKeys(confirmPassword);
        clickElement(signUpButtonXPATH);
    }

    public void backToSignUpPage(){
        waitUntilTitleContains("My Account");
        clickElement(userNameXPATH);
        clickALinkInMyAccount("Logout");
        clickALinkInMyAccount("My Account");
        clickALinkInMyAccount("Sign Up");
    }

    public void signUpAttemptWithPrevInfo(){
        getElement(firstNameInputXPATH).sendKeys(signUp.getPersonalInfo().get(0));
        getElement(lastNameInputXPATH).sendKeys(signUp.getPersonalInfo().get(1));
        getElement(mobileNumberXPATH).sendKeys(signUp.getPersonalInfo().get(2));
        getElement(emailXPATH).sendKeys(signUp.getPersonalInfo().get(3));
        getElement(passwordXPATH).sendKeys(signUp.getPersonalInfo().get(4));
        getElement(confirmPasswordXPATH).sendKeys(signUp.getPersonalInfo().get(5));
        clickElement(signUpButtonXPATH);
    }
}
