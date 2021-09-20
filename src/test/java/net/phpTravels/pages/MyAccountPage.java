package net.phpTravels.pages;

import static net.phpTravels.utilities.BrowserUtils.clickElement;

public class MyAccountPage extends BasePage{

    public final String accountCategories = "//li[@class='nav-item']//a[contains(.,'%s')]";
    public final String accountCategoriesActiveTab = "//li[@class='nav-item']//a[contains(@class,'active')]";

    /**
     * This method will click on user defined account category
     * @param accountCategory| Bookings| My Profile| Wishlist| Newsletter
     */
    public void clicksOnAccountCategory(String accountCategory){
        String str = String.format(accountCategories, accountCategory);
        clickElement(str);
    }
}
