package gui;

import com.microsoft.playwright.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import pages.OnlinerPage;

@Feature("Onliner tests")
public class OnlinerTest extends OnlinerBaseTest {

    @Test
    @Story("Search LEGO on onliner.by")
    public void onlinerTest() {
        onlinerPage.openOnliner();
        onlinerPage.typeInSearchField("Lego");
        onlinerPage.chooseFirstCategory();
        onlinerPage.selectCheckBox("Star Wars");
        onlinerPage.chooseFirstProduct();
        onlinerPage.productShouldHasTitle("LEGO Star Wars 75192");
    }
}
