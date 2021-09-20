package net.phpTravels.pages;

import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.phpTravels.utilities.BrowserUtils.*;

public class HotelSearchResultPage {
    public final String HOTEL_NAME_XPATH = "//span[@class='text-primary']";
    public final String TOTAL_HOTEL_SEARCH_XPATH = "//p[@class='text-muted post-heading']";
    public final String GEN_HOTEL_SEARCH_LOCATION_XPATH = "//p[@class='location go-text-right']";

    /**
     * This will return the number that shows in the total listing found
     * @return listing number
     */
    public int expectedNumberOfHotelSearchResult(){
        waitUntilVisible(getElement(TOTAL_HOTEL_SEARCH_XPATH));
        String text = getElement(TOTAL_HOTEL_SEARCH_XPATH).getText();
        String totalNum = "";
        for (int i = 0; i < text.length(); i++) {
            if(Character.isDigit(text.charAt(i))){
                totalNum+=text.charAt(i);
            }
        }
        return Integer.parseInt(totalNum);
    }

    /**
     * This will return the number of lists of hotels
     * @return size of the list
     */
    public int actualNumberOfHotelSearchResult(){
        waitUntilVisibilityOfAllElements(getElements(GEN_HOTEL_SEARCH_LOCATION_XPATH));
        List<WebElement> listing = getElements(GEN_HOTEL_SEARCH_LOCATION_XPATH);
        return listing.size();
    }

    /**
     * This will return the location of listed hotel(s)
     * @return location of the hotel
     */
    public String hotelLocation(){
        waitUntilVisibilityOfAllElements(getElements(GEN_HOTEL_SEARCH_LOCATION_XPATH));
        List<String> lst = getTextOfElements(getElements(GEN_HOTEL_SEARCH_LOCATION_XPATH));
        Set<String> location = new HashSet<>(lst);
        if(location.size()>1) return null;else return location.stream().findFirst().get();
    }
}
