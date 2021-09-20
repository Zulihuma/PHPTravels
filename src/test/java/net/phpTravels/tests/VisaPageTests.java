package net.phpTravels.tests;

import net.phpTravels.pages.HomePage;
import net.phpTravels.pages.VisaPage;
import org.testng.annotations.Test;

import static net.phpTravels.utilities.BrowserUtils.*;
import static net.phpTravels.utilities.DataUtils.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VisaPageTests extends TestBase{

    @Test(description = "PHPT-126")
    public void submitValidVisaBooking() {
        new HomePage().searchVisaBooking();
        VisaPage visaPage = new VisaPage();
        visaPage.enterBookingDetails();
        visaPage.submitBooking();
        String expectedMessage = "Visa Submitted";
        String actualMessage = getElement(visaPage.BOOKING_CONFIRMATION).getText();
        assertEquals(actualMessage,expectedMessage);
    }

    @Test(description = "PHPT-127")
    public void submitInvalidVisaBooking() {
        new HomePage().searchVisaBooking();
        VisaPage visaPage = new VisaPage();
        String firstname = generateFirstName();
        String lastname = generateLastName();
        String email = generatesRandomEmail();
        String confirmEmail = generatesRandomEmail();
        String contactNumber = generatePhoneNumber();
        visaPage.enterInfo("First Name",firstname);
        visaPage.enterInfo("Last Name",lastname);
        visaPage.enterInfo("Email",email);
        visaPage.enterInfo("Confirm Email",confirmEmail);
        visaPage.enterInfo("Contact Number",contactNumber);
        visaPage.submitBooking();
        String expectedError = "Confirm email must be same";
        String actualError = getElement(visaPage.EMAIL_ERROR_MESSAGE).getText();
        assertEquals(actualError,expectedError);
    }

    @Test(description = "PHPT-128")
    public void notesCheckBox() {
        new HomePage().searchVisaBooking();
        VisaPage visaPage = new VisaPage();
        clickElement(visaPage.NOTES_CHECK_BOX);
        assertTrue(getElement(visaPage.NOTES_CHECK_BOX).isSelected()
                            && elementDisplayed(visaPage.NOTES_TEXT_AREA));
    }
}
