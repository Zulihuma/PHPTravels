package net.phpTravels.utilities;

import java.util.List;

public class GlobalDataUtil {

    private String activeTab;
    private String dateType;
    private String countryBoxType;
    private String fromCountry;
    private String toCountry;
    private String timeText;
    private String destination;
    private String guestType;
    private String searchCategory;
    private int[] startDate;
    private int[] endDate;
    private List<String> personalInfo;
    private String locationType;
    private String airlineClassType;

    public String getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(String activeTab) {
        this.activeTab = activeTab;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getCountryBoxType() {
        return countryBoxType;
    }

    public void setCountryBoxType(String countryBoxType) {
        this.countryBoxType = countryBoxType;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
    }

    public String getToCountry() {
        return toCountry;
    }

    public void setToCountry(String toCountry) {
        this.toCountry = toCountry;
    }

    public String getGuestType() {
        return guestType;
    }

    public void setGuestType(String guestType) {
        this.guestType = guestType;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    public String getDestination(){return destination;}

    public void setDestination(String destination){
        this.destination = destination;
    }

    public int[] getStartDate() {
        return startDate;
    }

    public void setStartDate(int[] startDate) {
        this.startDate = startDate;
    }

    public int[] getEndDate() {
        return endDate;
    }

    public void setEndDate(int[] endDate) {
        this.endDate = endDate;
    }

    public String getTimeText(){
        return timeText;
    }

    public void setTimeText(String timeText){
        this.timeText = timeText;
    }

    public List<String> getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(List<String> personalInfo) {
        this.personalInfo = personalInfo;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getAirlineClassType() {
        return airlineClassType;
    }

    public void setAirlineClassType(String airlineClassType) {
        this.airlineClassType = airlineClassType;
    }
}
