package vendorportal.model;

import lombok.Data;

@Data
public class VendorPortalTestData {

    private String username;
    private String password;
    private String monthlyEarning;
    private String annualEarning;
    private String profitMargin;
    private String availableInventory;
    private String searchKeyword;
    private Integer searchResultsCount;

}