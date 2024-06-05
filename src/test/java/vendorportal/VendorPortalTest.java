package vendorportal;

import abstracted.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.admin.DashboardPage;
import pages.admin.LoginPage;
import vendorportal.model.VendorPortalTestData;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath) {
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest() {
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.getUsername(), testData.getPassword());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest() {
        Assert.assertTrue(dashboardPage.isAt());

        // finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.getMonthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.getAnnualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.getProfitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.getAvailableInventory());

        // order history search
        dashboardPage.searchOrderHistoryBy(testData.getSearchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.getSearchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest() {
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}
