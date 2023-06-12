package gui;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Feature("Onliner.by")
public class OnlinerSecondTest extends OnlinerBaseTest {

    @ParameterizedTest
    @ValueSource(strings= {"Пылесос", "Наушники", "Микроволновая печь"})
    @Story("Search '{product}' product: ")
    void secondSearchProduct(String product){
        onlinerPage.openOnliner();
        onlinerPage.typeInSearchField(product);
        onlinerPage.allResultsShouldHaveText(product);
    }
}
