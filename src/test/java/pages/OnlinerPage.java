package pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static utils.PropertiesReader.getProperty;

@Feature("Search on onliner.by")
public class OnlinerPage {

    private final Page page;

    public OnlinerPage(Page page) {
        this.page = page;
    }

    @Step("Open Onliner.by")
    public void openOnliner() {
        page.navigate(getProperty("gui.host"));
    }

    @Step("Type '{product}' in search field")
    public void typeInSearchField(String product) {
        page.locator(".fast-search__input").fill(product);
    }

    @Step("Choose first search result category")
    public void chooseFirstCategory() {
        page.frameLocator("#fast-search-modal iframe")
                .locator("a.category__title").click();
    }

    @Step("Select '{checkboxName}' checkbox")
    public void selectCheckBox(String checkboxName) {
        page.locator("(//span[@class='schema-filter__checkbox-text' and text()='" + checkboxName + "'])[1]")
                .click();
    }

    @Step("Choose first product")
    public void chooseFirstProduct() {
        page.locator(".schema-product__title").first().click();
    }

    @Step("Product should has '{title}' title")
    public void productShouldHasTitle(String title) {
        assertThat(page.locator(".catalog-masthead__title.js-nav-header"))
                .containsText(title);
    }

    @Step("All results should have '{productName}' product name")
    public void allResultsShouldHaveText(String productName) {
        page.frameLocator(".modal-iframe")
                .locator("a.product__title-link")
                .all()
                .forEach(result ->  assertThat(result).containsText(productName));
    }

}
