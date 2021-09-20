package net.phpTravels.pages;

import net.phpTravels.utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static net.phpTravels.utilities.BrowserUtils.*;
import static net.phpTravels.utilities.DataUtils.generateSingleNum;

public class HomePage extends BasePage {

    public GlobalDataUtil searchTab = new GlobalDataUtil();
    public final String GEN_SEARCH_CATEGORIES = "//a[@data-name='%s']";
    private final String GEN_FLIGHT_VEHICLE_LOCAITON_XPATH = "//div[@id='%s']//div[label[contains(.,'%s')]]/descendant::a";
    private final String GEN_LOCATION_SEARCH_RESULT_XPATH = "//li[contains(@class,'selectable')]";
    private final String GEN_LOC_SEARCH_BAR = "//li[contains(.,'Searching')]";
    public final String GEN_GUEST_MODULE_XPATH = "//div[contains(@class,'active') and @id='%s']//label[contains(.,'%s')]/..//div[@class='form-icon-left']//%s";
    public final String GEN_FULL_DATE = "//div[contains(@class,'- active')]//div[@data-date='%s' and @data-month='%s' and @data-year='%s']";
    public final String GEN_CALENDAR_BOX = "//nav[@class='menu-horizontal-02']/following-sibling::div/div[contains(@class,'active')]//label[contains(.,'%s')]/..//input";
    public final String GEN_YEAR_XPATH = "//div[contains(@class,'- active')]//div[contains(@class,'cell-year') and .=%s]";
    public final String GEN_MONTH_XPATH = "//div[contains(@class,'- active')]//div[contains(@class,'cell-month') and @data-month='%s']";
    public final String YEAR_MONTH_SELECTOR_XPATH = "//div[contains(@class,'- active')]//div[@class='datepicker--nav-title']";
    public final String GEN_CALENDAR_PAGE_NAVI_XPATH = "//div[contains(@class,'- active')]//div[@data-action='%s']";
    public final String GEN_COUNTRY_INPUT_BOX = "//div[contains(@class,'active')]//label[.='%s']/../div[last()]";
    public final String GEN_COUNTRY_OPTION_XPATH = "//div[contains(@class,'chosen-container-active')]//li[contains(.,'%s')]";
    public final String GEN_SELECTED_COUNTRY_XPATH = "//label[.='%s']/..//a";
    public final String DEFAULT_AIRLINE_CLASS_XPATH = "//div[contains(@class,'active') and @id='flights']//div[@class='form-icon-left flightclass']/div/a";
    public final String GEN_AIRLINE_CLASSES_XPATH = "//ul[contains(@class,'chosen')]//li";
    public final String DEFAULT_TRIP_SELECTION_XPATH = "//label[.='One Way']";
    public final String GEN_TRIP_SELECTION_XPATH = "//div[contains(@class,'active') and @id='flights']//div[@class='row row-reverse']/div[contains(.,'%s')]";
    public final String GEN_CHECK_TRIPS_SELECTED_XPATH = "//div[contains(@class,'active') and @id='flights']//div[@class='row row-reverse']/div[contains(.,'%s')]/input";
    public final String GEN_DESTINATION_TYPE_XPATH = "//div[@class='select2-result-label'][.='%s']/..//ul/li";
    public final String DESTINATION_SEARCH_XPATH = "//div[contains(@class,'active') and @id='hotels']//a";
    public final String GEN_SEARCH_BUTTON = "//div[contains(@class,'active') and @id='%s']//button[@type='submit']";

    public final String GEN_DEPART_RETURN_TIME_XPATH = "//select[contains(@name,'%s')]/following-sibling::div/a";
    public final String GEN_DEPART_RETURN_OPTIONS_XPATH = "//select[contains(@name,'%s')]/following-sibling::div/div/ul/li";
    public final String GEN_DEPART_RETURN_TIME_DROPDOWN_INPUT_BOX_XPATH = "//select[contains(@name,'%s')]/following-sibling::div/div/div//input";
    public final String GEN_DEPART_RETURN_TIME_DROPDOWN_RESULT_XPATH = "//select[contains(@name,'%s')]/following-sibling::div/div/ul";
    public final String CATEGORIES_ACTIVE_XPATH = "//ul[@class='nav row-reverse ']//a[contains(@class,'active')]";
    public final String GEN_LOC_INPUT_BOX = "//div[@id='select2-drop']//input[contains(@class,'select2-input')]";

    /**
     * This method will click on user defined search category
     * @param category| hotels|flights|boats|rentals|tours|cars|visa
     */
    public void clicksOnSearchCategory(String category) {
        searchTab.setActiveTab(category);
        String str = String.format(GEN_SEARCH_CATEGORIES, category.toLowerCase());
        clickElement(str);
    }

    public void enterLocation(String type, String location) {
        searchTab.setLocationType(type);
        String xpath = String.format(GEN_FLIGHT_VEHICLE_LOCAITON_XPATH,searchTab.getActiveTab().toLowerCase(),type);
        clickElement(xpath);
        waitUntilAttributeContains(GEN_LOC_INPUT_BOX,"class","focused");
        getElement(GEN_LOC_INPUT_BOX).sendKeys(location);
    }

    public void selectLocationFromList(String key) {
        waitForLocationSearchResult();
        getElements(GEN_LOCATION_SEARCH_RESULT_XPATH)
                .stream().filter(element -> element.getText().contains(key))
                .collect(Collectors.toList()).forEach(WebElement::click);
    }

    public void selectLocation(String tripType, String location, String key) {
        enterLocation(tripType, location);
        selectLocationFromList(key);
    }

    public void selectRandomLocation(String tripType, String location) {
        enterLocation(tripType, location);
        waitForLocationSearchResult();
        new HashSet<>(getElements(GEN_LOCATION_SEARCH_RESULT_XPATH))
                .stream().findFirst().get().click();
    }

    public String getSelectedLocation() {
        return getElement(String.format(GEN_FLIGHT_VEHICLE_LOCAITON_XPATH,
                searchTab.getActiveTab().toLowerCase(), searchTab.getLocationType())).getText();
    }

    public void waitForLocationSearchResult() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), 8);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GEN_LOC_SEARCH_BAR)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Enter all lower case for category.
     * Enter first letter uppercase for type EXPECT for infant all lower case
     * '1' is the minimum for number
     * @param category hotels | flights | boats | rentals | tours
     * @param type     Adults  | Child | infant
     * @param number;
     */
    public void setGuestNumber(String category, String type, int number) {
        clicksOnSearchCategory(category);
        guestNumberModifier(category, type, number);
    }

    /**
     * get the number from type as int
     * @param category hotels | flights | boats | rentals | tours
     * @param type     Adults  | Child | infant
     * @return default guest number
     */
    public int getGuestNumber(String category, String type) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.get();
        WebElement inputBox = getElement(String.format(GEN_GUEST_MODULE_XPATH, category, type, "input"));
        String text = js.executeScript("return arguments[0].value;", inputBox).toString();
        if (text.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(text);
    }

    /**
     * @param category hotels | flights | boats | rentals | tours
     * @param type     Adults  | Child | infant
     * @param num;
     */
    public void guestNumberModifier(String category, String type, int num) {
        int difference = getGuestNumber(category, type) - num;
        if (getGuestNumber(category, type) < num) {
            while (getGuestNumber(category, type) < num && getGuestNumber(category, type) < 100) {
                clickElement(String.format(GEN_GUEST_MODULE_XPATH, category, type, "button[contains(.,'+')]"));
            }
        } else {
            for (int i = 0; i < difference; i++) {
                clickElement(String.format(GEN_GUEST_MODULE_XPATH, category, type, "button[contains(.,'-')]"));
            }
        }
    }

    /**
     * This module randomly sets the category and guest type of the Guest module
     */
    public void selectRandomGuestModule() {
        CsvUtil csvUtil = new CsvUtil("GuestModuleSearchCategories");
        List<String> categories = csvUtil.getRecordsByHeader("Categories");
        List<String> guestTypes = csvUtil.getRecordsByHeader("GuestType");
        Collections.shuffle(categories);
        String randomCategory = categories.get(0);
        int guestTypeIndex;
        String guestType = "";
        switch (randomCategory) {
            case "hotels":
                guestTypeIndex = generateSingleNum(1);
                guestType = guestTypes.get(guestTypeIndex);
                break;
            case "flights":
                guestTypeIndex = generateSingleNum(2);
                guestType = guestTypes.get(guestTypeIndex);
                break;
            case "boats":
            case "rentals":
            case "tours":
                guestType = guestTypes.get(0);
                break;
        }
        searchTab.setSearchCategory(randomCategory);
        searchTab.setGuestType(guestType);
    }

    /**
     * This method will use the user entered date and select it from the datePicker
     * @param category     Hotels | Flights | Boats | Rentals | Tours | Cars | Visa
     * @param dateType     Check in | Check out | Depart | Return | Date
     * @param dateOrRandom Date format: dd-MM-yyyy | Random
     */
    public void dateSelector(String category, String dateType, String dateOrRandom) {
        clicksOnSearchCategory(category);
        searchTab.setDateType(dateType);
        clickElement(String.format(GEN_CALENDAR_BOX, dateType));
        if (dateOrRandom.equalsIgnoreCase("random")) {
            pickRandomDate();
        } else {
            int[] selectedDate = convertDateToArray(dateOrRandom);
            selectDate(selectedDate[0], selectedDate[1], selectedDate[2]);
        }
    }

    /**
     * This method will select a date from the calendar based on the parameters
     * Parameter format: dd-MM-yyyy
     * @param date  day of month
     * @param month month of year
     * @param year  calendar year
     */
    public void selectDate(int date, int month, int year) {
        String specificDate = String.format(GEN_FULL_DATE, date, (month - 1), year);
        turnOffImplicitWait();
        boolean present;
        do {
            present = true;
            try {
                clickElement(Driver.get().findElement(By.xpath(specificDate)));
            } catch (NoSuchElementException e) {
                present = false;
            }
            if (!present) {
                yearMonthPicker(year, month);
            }
        } while (!present);
        turnOnImplicitWait();
    }

    /**
     * This internal method selects the month and the year from the calendar
     * @param year  calendar year
     * @param month month of year
     */
    public void yearMonthPicker(int year, int month) {
        clickElement(YEAR_MONTH_SELECTOR_XPATH);
        clickElement(YEAR_MONTH_SELECTOR_XPATH);
        turnOffImplicitWait();
        boolean present;
        do {
            present = true;
            try {
                clickElement(Driver.get().findElement(By.xpath(String.format(GEN_YEAR_XPATH, year))));
            } catch (NoSuchElementException e) {
                present = false;
            }
            if (!present) {
                clickElement(GEN_CALENDAR_PAGE_NAVI_XPATH, "next");
            }
        } while (!present);
        turnOnImplicitWait();
        clickElement(String.format(GEN_MONTH_XPATH, (month - 1)));
    }

    /**
     * This method will generate and select a random future date
     * If the generated date is for Check out or Return date, it will generate a date that is later than Check in
     */
    public void pickRandomDate() {
        int[] tabStartDate = searchTab.getStartDate();
        String dateType = searchTab.getDateType();
        if (dateType.equalsIgnoreCase("check out") || dateType.equalsIgnoreCase("return")) {
            LocalDate endDate = LocalDate.of(tabStartDate[2], tabStartDate[1], tabStartDate[0]).plusDays(new Random().nextInt(365));
            String end = endDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            searchTab.setEndDate(convertDateToArray(end));
            int[] tabEndDate = searchTab.getEndDate();
            selectDate(tabEndDate[0], tabEndDate[1], tabEndDate[2]);
        } else {
            searchTab.setStartDate(convertDateToArray(DataUtils.generateRandomFutureDate("dd-MM-yyyy")));
            tabStartDate = searchTab.getStartDate();
            selectDate(tabStartDate[0], tabStartDate[1], tabStartDate[2]);
        }
    }

    /**
     * This method will format the date data depending on the active search tab
     * @param date date array ex: {20,3,2021}
     * @return formatted date
     */
    public String formatDate(int[] date) {
        if (searchTab.getActiveTab().equalsIgnoreCase("flights")) {
            return DataUtils.formatDate(date[0], date[1], date[2], "yyyy-MM-dd");
        } else if (searchTab.getActiveTab().equalsIgnoreCase("visa")) {
            return DataUtils.formatDate(date[0], date[1], date[2], "dd-MM-yyyy");
        } else {
            return DataUtils.formatDate(date[0], date[1], date[2], "dd/MM/yyyy");
        }
    }

    /**
     * This internal method converts a string format date into an array
     * @param date date data in dd-MM-yyyy format
     * @return date array
     */
    public int[] convertDateToArray(String date) {
        int[] dateArray = new int[date.split("-").length];
        for (int i = 0; i < date.split("-").length; i++) {
            dateArray[i] = Integer.parseInt(date.split("-")[i]);
        }
        return dateArray;
    }

    /**
     * This internal method will return the value of value attribute of selected calendar input box
     * @param type Check in | Check out | Depart | Return | Date
     * @return value of value attribute of selected calendar input box
     */
    public String getSelectedDate(String type) {
        return getElement(GEN_CALENDAR_BOX, type).getAttribute("value");
    }

    /**
     * This method selects country based on user entry
     * @param countryBox From Country | to Country
     * @param country    Country name | random
     */
    public void selectCountry(String countryBox, String country) {
        clickElement(String.format(GEN_COUNTRY_INPUT_BOX, countryBox));
        searchTab.setCountryBoxType(countryBox);
        if (country.equalsIgnoreCase("random")) {
            selectRandomCountry();
        } else {
            scrollAndClick(GEN_COUNTRY_OPTION_XPATH, country);
        }
    }

    /**
     * This method selects a random country from the predefined excel sheet
     */
    public void selectRandomCountry() {
        ExcelUtil countries = new ExcelUtil("src/test/resources/CountryList.xlsx", "countries");
        String country = countries.getCellData(DataUtils.generateSingleNum(1, countries.rowCount()), 0);
        if (searchTab.getCountryBoxType().equalsIgnoreCase("From Country")) {
            searchTab.setFromCountry(country);
            scrollAndClick(GEN_COUNTRY_OPTION_XPATH, country);
        } else if (searchTab.getCountryBoxType().equalsIgnoreCase("To Country")) {
            while (searchTab.getFromCountry().equals(country)) {
                country = countries.getCellData(DataUtils.generateSingleNum(2, countries.rowCount()), 0);
            }
            searchTab.setToCountry(country);
            scrollAndClick(GEN_COUNTRY_OPTION_XPATH, country);
        }
    }

    /**
     * This returns the text of selected country in country input box of user's choice
     * @param countryBox From Country | to Country
     * @return text of selected country
     */
    public String getSelectedCountry(String countryBox) {
        return getElement(GEN_SELECTED_COUNTRY_XPATH, countryBox).getText().trim();
    }

    /**
     * This method makes the first letter of user entry "timeType" to upper case, and rest to lower case
     * @param xpath:GEN_DEPART_RETURN_TIME_XPATH | GEN_DEPART_RETURN_OPTIONS_XPATH | GEN_DEPART_RETURN_TIME_DROPDOWN_INPUT_BOX_XPATH                                      
                    GEN_DEPART_RETURN_TIME_DROPDOWN_RESULT_XPATH
     * @param timeType: Depart | Return
     * @return correspondent Xpath
     */
    public String timeModule(String xpath, String timeType) {
        timeType = timeType.substring(0, 1).toUpperCase() + "" + timeType.substring(1).toLowerCase();
        return String.format(xpath, timeType);
    }

    /**
     * This method selects a random time from "DEPART TIME" or "RETURN TIME" dropdown in Cars module
     * @param timeType: Depart | Return
     */
    public void selectRandomTimeCarsModule(String timeType) {
        clickElement(timeModule(GEN_DEPART_RETURN_TIME_XPATH, timeType));
        List<WebElement> timeList = getElements(timeModule(GEN_DEPART_RETURN_OPTIONS_XPATH, timeType));
        int index = generateSingleNum(timeList.size());
        searchTab.setTimeText(timeList.get(index).getText());
        clickElement(timeList.get(index));
    }

    public String getErrorMessage(String timeType){
        return BrowserUtils.getElement(timeModule(GEN_DEPART_RETURN_OPTIONS_XPATH,timeType)).getText();
    }

    public void enterInvalidTime(String category, String timeType, String time) {
        clicksOnSearchCategory(category);
        clickElement(timeModule(GEN_DEPART_RETURN_TIME_XPATH, timeType));
        searchTab.setTimeText(time);
        clickAndSend(timeModule(GEN_DEPART_RETURN_TIME_DROPDOWN_INPUT_BOX_XPATH, timeType),time);
    }

    /**
     * This method get the text of the time selected in Time Module input box.
     * @param timeType: Depart | Return
     * @return text of the time in input box
     */
    public String getSelectedTimesModule(String timeType) {
        return getElement(timeModule(GEN_DEPART_RETURN_TIME_XPATH, timeType)).getText();
    }

    /**
     * This method enters a random time in the inputBox in "DEPART TIME" or "RETURN TIME" dropdown in Cars module
     * @param timeType Depart | Return
     */

    public void enterRandomTimeCarsModule(String timeType) {
        clickElement(timeModule(GEN_DEPART_RETURN_TIME_XPATH, timeType));
        List<WebElement> timeTextList = getElements(timeModule(GEN_DEPART_RETURN_OPTIONS_XPATH, timeType));
        int index = generateSingleNum(timeTextList.size());
        searchTab.setTimeText(timeTextList.get(index).getText());
        getElement(timeModule(GEN_DEPART_RETURN_TIME_DROPDOWN_INPUT_BOX_XPATH,timeType)).sendKeys(searchTab.getTimeText());
        clickElement(timeModule(GEN_DEPART_RETURN_TIME_DROPDOWN_RESULT_XPATH,timeType));
    }

    /**
     * This method selects a time from "DEPART TIME" or "RETURN TIME" dropdown in Cars module that was entered manually
     * @param timeType Depart | Return
     * @param Time     please enter in format 24 hours format: hh:mm, and change the value of mm only by 30 (Ex: 15:30)
     */
    public void enterRandomTimeCarsModule(String timeType, String Time) {
        clickElement(timeModule(GEN_DEPART_RETURN_TIME_XPATH, timeType));
        getElements(timeModule(GEN_DEPART_RETURN_OPTIONS_XPATH, timeType));
        searchTab.setTimeText(Time);
        clickAndSend(timeModule(GEN_DEPART_RETURN_TIME_DROPDOWN_INPUT_BOX_XPATH, timeType),Time);
        clickElement(timeModule(GEN_DEPART_RETURN_TIME_DROPDOWN_RESULT_XPATH,timeType));
    }

    /**
     * This will set the destination search bar
     * @param type |Locations | Hotels
     * @param name name of any country
     */
    public void setDestination(String type, String name) {
        clickElement(DESTINATION_SEARCH_XPATH);
        waitUntilAttributeContains(GEN_LOC_INPUT_BOX,"class","focused");
        getElement(GEN_LOC_INPUT_BOX).sendKeys(name);
        turnOffImplicitWait();
        sleep(0.5);
        try {
            String destinationText = findElement(GEN_DESTINATION_TYPE_XPATH, type).getText();
            searchTab.setDestination(destinationText);
            clickElement(GEN_DESTINATION_TYPE_XPATH, type);
        } catch (Exception e) {
            searchTab.setDestination(null);
        }
        turnOnImplicitWait();
    }

    /**
     * This will return a random location from csv file
     * @param type | City| Hotel
     * @return text of random location
     */
    public String getDestinationName(String type) {
        String fileName = "";
        if (type.equalsIgnoreCase("city")) {
            fileName = "Destination_Locations";
        } else if (type.equalsIgnoreCase("hotel")) {
            fileName = "Destination_Hotels";
        }
        CsvUtil csvUtil = new CsvUtil(fileName);
        List<String> cities = csvUtil.getRecordsByHeader(type);
        int index = generateSingleNum(cities.size());
        return cities.get(index);
    }

    /**
     * This will return a random category
     * @return hotels | flights | boats | rentals | tours | cars | visa
     */
    public String getRandomCategory() {
        CsvUtil csvUtil = new CsvUtil("Categories");
        List<String> categories = csvUtil.getRecordsByHeader("category");
        int index = generateSingleNum(categories.size());
        String category = categories.get(index);
        searchTab.setActiveTab(category);
        return category;
    }

    /**
     * This will return a random date type from the category
     * @return Check in| Check out | Depart | Date | Return
     */
    public String getRandomDateType() {
        searchTab.setStartDate(convertDateToArray(DataUtils.generateRandomFutureDate("dd-MM-yyyy")));
        String category = searchTab.getActiveTab();
        switch (category) {
            case "hotels": {
                String[] types = {"Check in", "Check out"};
                int index = generateSingleNum(types.length);
                searchTab.setDateType(types[index]);
                return types[index];
            }
            case "cars": {
                String[] types = {"Depart", "Return"};
                int index = generateSingleNum(types.length);
                searchTab.setDateType(types[index]);
                return types[index];
            }
            case "flights":
                searchTab.setDateType("Depart");
                return "Depart";
            default:
                searchTab.setDateType("Date");
                return "Date";
        }
    }

    /**
     * This will return a random expected date in String format
     * @return random date
     */
    public String getExpectedDate() {
        if (searchTab.getDateType().equalsIgnoreCase("check out") || searchTab.getDateType().equalsIgnoreCase("return")) {
            return formatDate(searchTab.getEndDate());
        } else return formatDate(searchTab.getStartDate());
    }

    /**
     * This will select a trip type in flights category
     * @param tripType | Round Trip| One Way|
     */
    public void clickOnTripType(String tripType){
        String tripTypeBtn = String.format(GEN_TRIP_SELECTION_XPATH, tripType);
        clickElement(tripTypeBtn);
    }

    /**
     * THis will select a random class in flights category
     */
    public void selectRandomCLass() {
        String[] expectedClass = {"First", "Business", "Economy"};
        int index = generateSingleNum(expectedClass.length);
        clickElement(DEFAULT_AIRLINE_CLASS_XPATH);
        List<WebElement> elements = getElements(GEN_AIRLINE_CLASSES_XPATH);
        clickElement(elements.get(index));
        searchTab.setAirlineClassType(expectedClass[index]);
    }

    /**
     * This will click on SUBMIT button in Visa category
     */
    public void submitVisa(){
        clickElement(GEN_SEARCH_BUTTON, "visa");
    }

    public void searchVisaBooking() {
        clicksOnSearchCategory("visa");
        selectCountry("From Country","random");
        selectCountry("To Country","random");
        dateSelector("visa","Date","random");
        submitVisa();
    }

    /**
     * THis will enter City name in the hotel search box and click Search button
     */
    public void searchHotelByLocation(){
        String randomLocation = getDestinationName("City");
        setDestination("Locations", randomLocation);
        searchTab.setDestination(randomLocation);
        clickElement(GEN_SEARCH_BUTTON, "hotels");
    }
}
