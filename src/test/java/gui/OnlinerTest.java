package gui;

import com.microsoft.playwright.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.OnlinerPage;

@Feature("Onliner tests")
public class OnlinerTest {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private OnlinerPage onlinerPage;

    @BeforeEach
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    public void tearDown() {
        playwright.close();
    }

    @Test
    @Story("Search LEGO on onliner.by")
    public void onlinerTest() {
        onlinerPage = new OnlinerPage(page);
        onlinerPage.openOnliner();
        onlinerPage.typeInSearchField("Lego");
        onlinerPage.chooseFirstCategory();
        onlinerPage.selectCheckBox("Star Wars");
        onlinerPage.chooseFirstProduct();
        onlinerPage.productShouldHasTitle("LEGO Star Wars 75192");
    }
}
