package net.phpTravels.tests;

import net.phpTravels.pages.HomePage;
import net.phpTravels.pages.HotelSearchResultPage;
import org.testng.annotations.Test;

import static net.phpTravels.utilities.BrowserUtils.*;
import static org.testng.Assert.assertEquals;

public class HotelSearchResultPageTest extends TestBase{

    @Test(description = "PHPT = 115")
    public void verifySearchResultLocation(){
        HomePage homePage = new HomePage();
        homePage.searchHotelByLocation();
        HotelSearchResultPage hotelSearchResultPage = new HotelSearchResultPage();
        String expectedResult = homePage.searchTab.getDestination();
        waitUntilTextToBe(getElement(hotelSearchResultPage.HOTEL_NAME_XPATH),expectedResult);
        String actualResult = getElementText(getElement(hotelSearchResultPage.HOTEL_NAME_XPATH));
        assertEquals(actualResult, expectedResult);
    }

    @Test(description = "PHPT-116")
    public void verifyNumberOfListingResult(){
        HomePage homePage = new HomePage();
        homePage.searchHotelByLocation();
        HotelSearchResultPage hotelSearchResultPage = new HotelSearchResultPage();
        assertEquals(hotelSearchResultPage.actualNumberOfHotelSearchResult(), hotelSearchResultPage.expectedNumberOfHotelSearchResult());
    }

    @Test(description = "PHPT=117")
    public void verifyTheLocationOfTheHotel(){
        HomePage homePage = new HomePage();
        homePage.searchHotelByLocation();
        String expectedResult = homePage.searchTab.getDestination().toLowerCase();
        HotelSearchResultPage hotelSearchResultPage = new HotelSearchResultPage();
        String actualResult = hotelSearchResultPage.hotelLocation();
        assertEquals(actualResult,expectedResult);
    }
}
