package net.phpTravels.pages;



public class LoginPage extends BasePage{

    public final String emailInputBoxXpath="//input[contains(@name,'username')]";
    public final String passwordInputBoxXpath="//input[contains(@name,'password')]";
    public final String logInButtonXpath="//button[contains(@class,'login')]";
    public final String signUpButtonXpath="//a[contains(@class,'form')]";
    public final String forgetPasswordButtonXpath="//a[contains(@href,'Password')]";
    public final String checkBoxXpath="//label[contains(@class,'control')]";
    public final String popUpXpath="//h4[contains(@class,'title')]";

}