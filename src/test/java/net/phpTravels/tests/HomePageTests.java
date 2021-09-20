package net.phpTravels.tests;

import net.phpTravels.pages.HomePage;
import net.phpTravels.pages.VisaPage;
import net.phpTravels.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static net.phpTravels.utilities.BrowserUtils.*;
import static net.phpTravels.utilities.DataUtils.generateSingleNum;
import static net.phpTravels.utilities.DataUtils.getTestData;
import static org.testng.Assert.*;

public class HomePageTests extends TestBase {

    @Ignore
    @Test(description = "dummy location selection example for Flight/Car booking")
    public void locationTest(){
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("Flights");
        homePage.enterLocation("From","toronto");
        homePage.selectLocationFromList("YYZ");
        homePage.selectLocation("To","oslo","OSL");
        homePage.clicksOnSearchCategory("Cars");
        homePage.enterLocation("Pick up","toronto");
        homePage.selectLocationFromList("Airport");
        homePage.selectLocation("Drop off","toronto","Bay Street");
    }

    @Ignore
    @Test(description = "dummy random location selection example for Flight/car booking")
    public void randomlocationTest(){
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("Flights");
        homePage.selectRandomLocation("From",getTestData("CityList").getKey());
        homePage.selectRandomLocation("To",getTestData("CityList").getKey());
        homePage.clicksOnSearchCategory("Cars");
        String city = getTestData("CityList").getKey();
        homePage.selectRandomLocation("Pick up",city);
        homePage.selectRandomLocation("Drop off",city);
    }

    @Ignore
    @Test(description = "dummy positive test for guest number setting")
    public void guestNumberTest(){
        HomePage homePage = new HomePage();
        String category = "boats";
        String type = "Adults";
        int gustNumber = generateSingleNum(1,10);
        homePage.setGuestNumber(category,type,gustNumber);
        assertEquals(gustNumber, homePage.getGuestNumber(category, type));
    }

    @Ignore
    @Test(description = "dummy negative test for guest number setting")
    public void negativeGuestNumberTest(){
        HomePage homePage = new HomePage();
        String category = "flights";
        String type = "Child";
        int gustNumber = -1;
        homePage.setGuestNumber(category,type,gustNumber);
        assertFalse(gustNumber==homePage.getGuestNumber(category,type));
    }

    @Ignore
    @Test(description = "Dummy calendar module test for date selection")
    public void selectRandomDate() {
        HomePage homePage = new HomePage();
        homePage.dateSelector("hotels","Check in","random");
        homePage.dateSelector("hotels","Check out","random");
        String expectedCheckInDate = homePage.formatDate(homePage.searchTab.getStartDate());
        String actualCheckInDate = homePage.getSelectedDate("Check in");
        assertEquals(actualCheckInDate,expectedCheckInDate);
        String expectedCheckOutDate = homePage.formatDate(homePage.searchTab.getEndDate());
        String actualCheckOutDate = homePage.getSelectedDate("Check out");
        assertEquals(actualCheckOutDate,expectedCheckOutDate);
    }

    @Ignore
    @Test(description = "Dummy calendar module test for user defined date selection")
    public void selectDefinedDate() {
        HomePage homePage = new HomePage();
        homePage.dateSelector("boats","Date","30-03-2021");
        String expectedDate = homePage.formatDate(new int[] {30,3,2021});
        String actualDate = homePage.getSelectedDate("Date");
        assertEquals(actualDate,expectedDate);
    }

    @Ignore
    @Test(description = "Dummy country module test for user defined country selection")
    public void selectCountry() {
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("visa");
        homePage.selectCountry("From Country","United States");
        homePage.selectCountry("To Country","Canada");
        String expectedFromCountry = "United States";
        String actualFromCountry = homePage.getSelectedCountry("From Country");
        assertEquals(actualFromCountry,expectedFromCountry);
        String expectedToCountry = "Canada";
        String actualToCountry = homePage.getSelectedCountry("To Country");
        assertEquals(actualToCountry,expectedToCountry);
    }

    @Ignore
    @Test(description = "Dummy country module test for random country selection")
    public void selectRandomCountry() {
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("visa");
        homePage.selectCountry("From Country","random");
        homePage.selectCountry("To Country","random");
        String expectedFromCountry = homePage.searchTab.getFromCountry();
        String actualFromCountry = homePage.getSelectedCountry("From Country");
        assertEquals(actualFromCountry,expectedFromCountry);
        String expectedToCountry = homePage.searchTab.getToCountry();
        String actualToCountry = homePage.getSelectedCountry("To Country");
        assertEquals(actualToCountry,expectedToCountry);
    }

    @Ignore
    @Test(description = "Check default Airline class selection")
    public void verifyDefaultAirLineClass(){
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("Flights");
        assertEquals(getElement(homePage.DEFAULT_AIRLINE_CLASS_XPATH).getText(),"Economy");
    }

    @Ignore
    @Test(description = "Airline classes selection for Flights")
    public void selectAirlineClasses() {
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("Flights");
        clickElement(homePage.DEFAULT_AIRLINE_CLASS_XPATH);
        String[] expectedClass = {"First", "Business", "Economy"};
        int index = generateSingleNum(expectedClass.length);
        List<WebElement> elements = getElements(homePage.GEN_AIRLINE_CLASSES_XPATH);
        clickElement(elements.get(index));
        String expected = expectedClass[index];
        WebElement element = getElement(homePage.DEFAULT_AIRLINE_CLASS_XPATH);
        assertEquals(element.getText(), expected);
    }

    @Ignore
    @Test(description = "Check default trip selection ")
    public void verifyDefaultTripSelection(){
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("Flights");
        assertEquals(getElement(homePage.DEFAULT_TRIP_SELECTION_XPATH).getText(),"ONE WAY");
    }

    @Ignore
    @Test(description = "Trip selection example for Flights")
    public void selectTripOption() {
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("Flights");
        clickElement(homePage.GEN_TRIP_SELECTION_XPATH,"Round Trip");
        assertTrue(elementSelected(homePage.GEN_CHECK_TRIPS_SELECTED_XPATH,"Round Trip"));
        clickElement(homePage.GEN_TRIP_SELECTION_XPATH,"One Way");
        assertTrue(elementSelected(homePage.GEN_CHECK_TRIPS_SELECTED_XPATH,"One Way"));
    }

    @Ignore
    @Test(description = "dummy test for positive destination functionality")
    public void selectDestination() {
        HomePage homePage = new HomePage();
        String type = "Hotels", name = "tria";
        homePage.setDestination(type,name);
        assertEquals(getElement(homePage.DESTINATION_SEARCH_XPATH).getText(),homePage.searchTab.getDestination());
    }

    @Ignore
    @Test(description = "dummy test for negative destination functionality")
    public void selectDestinationNegative(){
        HomePage homePage = new HomePage();
        String type = "Hotels", name = "urumqi";
        homePage.setDestination(type,name);
        assertFalse(getElement(homePage.DESTINATION_SEARCH_XPATH).getText().equals(homePage.searchTab.getDestination()));
    }

    @Ignore
    @Test(description = "dummy destination test with random selection")
    public void selectRandomDestination(){
        HomePage homePage = new HomePage();
        String randomName = homePage.getDestinationName("City");
        homePage.setDestination("Locations",randomName);
        assertEquals(getElement(homePage.DESTINATION_SEARCH_XPATH).getText(),homePage.searchTab.getDestination());
    }
    @Ignore
    @Test(description = "Dummy Cars module test for random time selection")
    public void selectRandomTime(){
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("cars");
        homePage.selectRandomTimeCarsModule("depart");
        String actualTimeModule = homePage.getSelectedTimesModule("depart");
        String expectedTimeModule = homePage.searchTab.getTimeText();
        assertEquals(actualTimeModule,expectedTimeModule);
    }

    @Ignore
    @Test(description ="Dummy Cars modules test for entering random time")
    public void enterRandomTime(){
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("cars");
        homePage.enterRandomTimeCarsModule("depart");
        String actualTimeModule = homePage.getSelectedTimesModule("depart");
        String expectedTimeModule = homePage.searchTab.getTimeText();
        assertEquals(actualTimeModule,expectedTimeModule);
    }

    @Ignore
    @Test(description ="Dummy Cars modules test for entering random time(Manually)")
    public void enterRandomTimeManually(){
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("cars");
        homePage.enterRandomTimeCarsModule("depart","14:30");
        BrowserUtils.sleep(2);
        String expectedTimeModule = homePage.searchTab.getTimeText();
        String actualTimeModule =  homePage.getSelectedTimesModule("depart");
        assertEquals(actualTimeModule,expectedTimeModule);
    }

    @Test(description = "PHPT-89")
    public void increaseGuestNumber(){
        HomePage homePage = new HomePage();
        homePage.selectRandomGuestModule();
        String selectedCategory = homePage.searchTab.getSearchCategory();
        String selectedGuestType = homePage.searchTab.getGuestType();
        int expectedGuestNumber = 5;
        homePage.setGuestNumber(selectedCategory,selectedGuestType,expectedGuestNumber);
        int actualGuestNumber = homePage.getGuestNumber(selectedCategory,selectedGuestType);
        assertEquals(actualGuestNumber,expectedGuestNumber);
    }

    @Test(description = "PHPT-90")
    public void decreaseGuestNumber(){
        HomePage homePage = new HomePage();
        homePage.selectRandomGuestModule();
        String selectedCategory = homePage.searchTab.getSearchCategory();
        String selectedGuestType = homePage.searchTab.getGuestType();
        homePage.setGuestNumber(selectedCategory,selectedGuestType,6);
        int expectedGuestNumber = 3;
        homePage.setGuestNumber(selectedCategory,selectedGuestType,expectedGuestNumber);
        int actualGuestNumber = homePage.getGuestNumber(selectedCategory,selectedGuestType);
        assertEquals(actualGuestNumber,expectedGuestNumber);
    }

    @Test(description = "PHPT-91&92")
    public void invalidGuestNumber(){
        int[] invalidData = {-1,101};
        HomePage homePage = new HomePage();
        homePage.selectRandomGuestModule();
        String selectedCategory = homePage.searchTab.getSearchCategory();
        String selectedGuestType = homePage.searchTab.getGuestType();
        int expectedGuestNumber = invalidData[generateSingleNum(2)];
        homePage.setGuestNumber(selectedCategory,selectedGuestType,expectedGuestNumber);
        int actualGuestNumber = homePage.getGuestNumber(selectedCategory,selectedGuestType);
        assertNotEquals(expectedGuestNumber, actualGuestNumber);
    }

    @Test(description = "PHPT-93")
    public void pickRandomDate(){
       HomePage homePage = new HomePage();
       String category = homePage.getRandomCategory();
       String dateType = homePage.getRandomDateType();
       homePage.dateSelector(category,dateType,"random");
       String actualResult = homePage.getSelectedDate(dateType);
       String expectedResult = homePage.getExpectedDate();
       assertEquals(actualResult,expectedResult);
    }

    @Test(description = "PHPT-94")
    public void negativeTestForCheckin(){
        HomePage homePage = new HomePage();
        homePage.dateSelector("hotels","Check in","07-02-2021");
        String expectedResult = homePage.formatDate(new int[] {7,2,2021});
        String actualResult = homePage.getSelectedDate("Check in");
        assertNotEquals(expectedResult, actualResult);
    }

    @Test(description = "PHPT-99")
    public void selectAirportByCity(){
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("Flights");
        homePage.enterLocation("From","toronto");
        homePage.selectLocationFromList("YYZ");
        assertTrue(homePage.getSelectedLocation().contains("(YYZ)"),"selection does not match");
    }

    @Test(description = "PHPT-100")
    public void selectAirportByCode(){
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("Flights");
        homePage.enterLocation("To","YYZ");
        homePage.selectLocationFromList("YYZ");
        assertTrue(homePage.getSelectedLocation().contains("(YYZ)"),"selection does not match");
    }

    @Test(description = "PHPT-102")
    public void carRentalByCity(){
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("Cars");
        homePage.enterLocation("Pick up","toronto");
        homePage.selectLocationFromList("Airport");
        assertTrue(homePage.getSelectedLocation().contains("Airport"),"selection does not match");

    }

    @Test(description = "PHPT-101")
    public void airportRandomSelection(){
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("Flights");
        String expected = getTestData("CityList").getKey();
        homePage.selectRandomLocation("From",expected);
        assertTrue(homePage.getSelectedLocation().contains(expected),"selection does not match");
    }

    @Test(description = "PHPT-103")
    public void carRentalRandomSelection(){
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("Cars");
        String expected = getTestData("CityList").getKey();
        homePage.selectRandomLocation("Pick up",expected);
        assertTrue(homePage.getSelectedLocation().contains(expected),"selection does not match");
    }

    @Ignore
    @Test(description = "PHPT-95")
    public void negativeTestForCheckout(){
        HomePage homePage = new HomePage();
        homePage.dateSelector("cars","Depart","10-05-2021");
        String expectedDepart = homePage.formatDate(new int[] {10,5,2021});
        String actualDepart = homePage.getSelectedDate("Depart");
        assertEquals(actualDepart,expectedDepart);
        homePage.dateSelector("cars","Return","09-05-2021");
        String expectedReturn = homePage.formatDate(new int[]{9,5,2021});
        String actualReturn = homePage.getSelectedDate("Return");
        assertNotEquals(expectedReturn, actualReturn);
    }

    @Test(description = "PHPT-85")
    public void pickDateWithinSameMonth(){
        HomePage homePage = new HomePage();
        String checkInDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String checkOutDate = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        homePage.dateSelector("hotels","Check in",checkInDate);
        homePage.dateSelector("hotels","Check out",checkOutDate);
        String expectedCheckInDate = homePage.formatDate(homePage.convertDateToArray(checkInDate));
        String actualCheckInDate = homePage.getSelectedDate("Check in");
        assertEquals(actualCheckInDate,expectedCheckInDate);
        String expectedCheckOutDate = homePage.formatDate(homePage.convertDateToArray(checkOutDate));
        String actualCheckOutDate = homePage.getSelectedDate("Check out");
        assertEquals(actualCheckOutDate,expectedCheckOutDate);
    }

    @Test(description = "PHPT-86")
    public void pickDifferentMonthsWithinSameYear(){
        HomePage homePage = new HomePage();
        String checkInDate = LocalDate.now().plusMonths(2).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String checkOutDate = LocalDate.now().plusMonths(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        homePage.dateSelector("flights","Depart",checkInDate);
        clickElement(homePage.GEN_TRIP_SELECTION_XPATH,"Round Trip");
        homePage.dateSelector("flights","Return",checkOutDate);
        String expectedCheckInDate = homePage.formatDate(homePage.convertDateToArray(checkInDate));
        String actualCheckInDate = homePage.getSelectedDate("Depart");
        assertEquals(actualCheckInDate,expectedCheckInDate);
        String expectedCheckOutDate = homePage.formatDate(homePage.convertDateToArray(checkOutDate));
        String actualCheckOutDate = homePage.getSelectedDate("Return");
        assertEquals(actualCheckOutDate,expectedCheckOutDate);
    }

    @Test(description = "PHPT-87")
    public void pickDateFromDifferentYears(){
        HomePage homePage = new HomePage();
        String checkInDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String checkOutDate = LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        homePage.dateSelector("cars","Depart",checkInDate);
        homePage.dateSelector("cars","Return",checkOutDate);
        String expectedCheckInDate = homePage.formatDate(homePage.convertDateToArray(checkInDate));
        String actualCheckInDate = homePage.getSelectedDate("Depart");
        assertEquals(actualCheckInDate,expectedCheckInDate);
        String expectedCheckOutDate = homePage.formatDate(homePage.convertDateToArray(checkOutDate));
        String actualCheckOutDate = homePage.getSelectedDate("Return");
        assertEquals(actualCheckOutDate,expectedCheckOutDate);
    }

    @Test(description = "PHPT-80")
    public void VerifyNavigateToRandomCategory() {
        HomePage homePage = new HomePage();
        String category = homePage.getRandomCategory();
        homePage.clicksOnSearchCategory(category);
        String expected = category.toUpperCase();
        waitUntilTextToBe(getElement(homePage.CATEGORIES_ACTIVE_XPATH),expected);
        String actual = getElement(homePage.CATEGORIES_ACTIVE_XPATH).getText();
        assertEquals(actual,expected);

    }

    @Test(description = "PHPT-72")
    public void searchDestinationByHotelName() {
        HomePage homePage=new HomePage();
        String randomHotelName=homePage.getDestinationName("Hotel");
        homePage.setDestination("Hotels",randomHotelName);
        String actualDestination=getElement(homePage.DESTINATION_SEARCH_XPATH).getText();
        String expectedDestination=homePage.searchTab.getDestination();
        assertEquals(actualDestination,expectedDestination);
    }

    @Test(description = "PHPT-75")
    public void searchDestinationByLocationName() {
        HomePage homePage = new HomePage();
        String randomLocationName = homePage.getDestinationName("City");
        homePage.setDestination("Locations",randomLocationName);
        String actualDestination=getElement(homePage.DESTINATION_SEARCH_XPATH).getText();
        String expectedDestination=homePage.searchTab.getDestination();
        assertEquals(actualDestination,expectedDestination);
    }

    @Test(description = "PHPT-104")
    public void searchDestinationByInvalidHotelName() {
        HomePage homePage = new HomePage();
        String invalidHotelName="TDB";
        homePage.setDestination("Hotels",invalidHotelName);
        String actualDestination=getElement(homePage.DESTINATION_SEARCH_XPATH).getText();
        String expectedDestination=homePage.searchTab.getDestination();
        assertNotEquals(actualDestination,expectedDestination);
    }

    @Test(description = "PHPT-105")
    public void searchDestinationByInvalidLocationName() {
        HomePage homePage = new HomePage();
        String invalidLocationName ="Keshker";
        homePage.setDestination("Location",invalidLocationName);
        String actualDestination=getElement(homePage.DESTINATION_SEARCH_XPATH).getText();
        String expectedDestination=homePage.searchTab.getDestination();
        assertNotEquals(actualDestination,expectedDestination);
    }

    @Test(description = "PHPT-76")
    public void selectTripType() {
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("Flights");
        homePage.clickOnTripType("Round Trip");
        assertTrue(elementSelected(homePage.GEN_CHECK_TRIPS_SELECTED_XPATH,"Round Trip"));
    }

    @Test(description = "PHPT-77")
    public void selectFlightClassType() {
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("Flights");
        homePage.selectRandomCLass();
        String expectedResult = homePage.searchTab.getAirlineClassType();
        String actualResult = getElement(homePage.DEFAULT_AIRLINE_CLASS_XPATH).getText().trim();
        assertEquals(actualResult,expectedResult);
    }

    @Test(description = "PHPT-111")
    public void searchVisaBooking(){
        HomePage homePage = new HomePage();
        homePage.clicksOnSearchCategory("visa");
        homePage.selectCountry("From Country","random");
        homePage.selectCountry("To Country","random");
        homePage.dateSelector(homePage.searchTab.getActiveTab(),"Date","random");
        String expectedFromCountry = homePage.searchTab.getFromCountry();
        String expectedToCountry = homePage.searchTab.getToCountry();
        homePage.submitVisa();

        VisaPage visaPage = new VisaPage();
        String actual = getElementText(getElement(visaPage.VISA_PROMO_TITLE));
        assertTrue(actual.contains(expectedFromCountry) && actual.contains(expectedToCountry),
                                        "Selected Country does not match");

    }

    @Test(description = "PHPT-107")
    public void selectRandomTimeForCars() {
        HomePage homePage=new HomePage();
        homePage.clicksOnSearchCategory("Cars");
        homePage.selectRandomTimeCarsModule("Depart");
        String actualDepartTime=homePage.getSelectedTimesModule("Depart");
        String expectedDepartTime=homePage.searchTab.getTimeText();
        assertEquals(actualDepartTime,expectedDepartTime);
        homePage.selectRandomTimeCarsModule("Return");
        String actualReturnTime= homePage.getSelectedTimesModule("Return");
        String expectedReturnTime=homePage.searchTab.getTimeText();
        assertEquals(actualReturnTime,expectedReturnTime);
    }

    @Test(description = "PHPT-112")
    public void enterRandomTimeForCars() {
        HomePage homePage=new HomePage();
        homePage.clicksOnSearchCategory("Cars");
        homePage.enterRandomTimeCarsModule("Depart");
        String actualDepartTime=homePage.getSelectedTimesModule("Depart");
        String expectedDepartTime=homePage.searchTab.getTimeText();
        assertEquals(actualDepartTime,expectedDepartTime);
        homePage.enterRandomTimeCarsModule("Return");
        String actualReturnTime= homePage.getSelectedTimesModule("Return");
        String expectedReturnTime=homePage.searchTab.getTimeText();
        assertEquals(actualReturnTime,expectedReturnTime);
    }

    @Test(description = "PHPT-113")
    public void enterInvalidTimeForCars() {
        HomePage homePage=new HomePage();
        String invalidTime="Two fifty";
        homePage.enterInvalidTime("cars","Depart",invalidTime);
        String actualDepartTime=homePage.getErrorMessage("Depart");
        String expectedDepartTime=homePage.searchTab.getTimeText();
        assertTrue(actualDepartTime.contains(expectedDepartTime));
        homePage.enterInvalidTime("cars","Return",invalidTime);
        String actualReturnTime=homePage.getErrorMessage("Return");
        String expectedReturnTime=homePage.searchTab.getTimeText();
        assertTrue(actualReturnTime.contains(expectedReturnTime));
    }
}
