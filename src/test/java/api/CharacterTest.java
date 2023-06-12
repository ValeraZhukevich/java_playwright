package api;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.PropertiesReader;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.PropertiesReader.getProperty;

public class CharacterTest extends BaseApiTest {

    @ParameterizedTest
    @ValueSource(strings= {"Rick Sanchez", "Morty Smith", "Beth Smith",
    "Jerry Smith", "Summer Smith"})
    @Story("Get character by '{name}' name")
    public void getCharactersByName(String name) {
        APIResponse response = request.get(getProperty("character"),
                RequestOptions.create()
                        .setQueryParam("name", name));
        System.out.println(response.status());
        System.out.println(response.url());
        System.out.println(response.text());
        assertTrue(response.ok());


//        characters.forEach(character -> {
//            try {
//                Assert.assertEquals(character.getName(), name);
//            } catch (AssertionError e) {
//                Allure.addAttachment("The name is different: ", character.toString());
//                throw e;
//            }
//        });
    }
}
