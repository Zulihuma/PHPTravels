package net.phpTravels.pages;

import static net.phpTravels.utilities.BrowserUtils.clickElement;
import static net.phpTravels.utilities.BrowserUtils.getElement;
import static net.phpTravels.utilities.DataUtils.*;

public class VisaPage extends BasePage {

    public final String VISA_PROMO_TITLE = "//h3[contains(@class,'title')]";
    public final String GEN_INPUT_BOX_XPATH = "//span[contains(.,'%s')]/../input";
    public final String SUBMIT_BOOKING_BUTTON = "//button[@id='sub']";
    public final String BOOKING_CONFIRMATION = "//h4[.='Visa Submitted']";
    public final String EMAIL_ERROR_MESSAGE = "//p[@class='text-danger']";
    public final String NOTES_CHECK_BOX = "//input[@type='checkbox']";
    public final String NOTES_TEXT_AREA = "//textarea[@name='notes']";

    public void enterBookingDetails() {
        String firstname = generateFirstName();
        String lastname = generateLastName();
        String email = generatesRandomEmail();
        String contactNumber = generatePhoneNumber();
        getElement(GEN_INPUT_BOX_XPATH,"First Name").sendKeys(firstname);
        getElement(GEN_INPUT_BOX_XPATH,"Last Name").sendKeys(lastname);
        getElement(GEN_INPUT_BOX_XPATH,"Email").sendKeys(email);
        getElement(GEN_INPUT_BOX_XPATH,"Confirm Email").sendKeys(email);
        getElement(GEN_INPUT_BOX_XPATH,"Contact Number").sendKeys(contactNumber);
    }

    public void submitBooking() {
        clickElement(SUBMIT_BOOKING_BUTTON);
    }

    public void enterInfo(String inputType, String input) {
        getElement(GEN_INPUT_BOX_XPATH,inputType).sendKeys(input);
    }

}
