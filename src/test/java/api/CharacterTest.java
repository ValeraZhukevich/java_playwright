package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.Character;

import java.util.List;

import static utils.PropertiesReader.getProperty;

@Tag("api")
public class CharacterTest extends BaseApiTest {

    @ParameterizedTest
    @ValueSource(strings= {"Rick Sanchez", "Morty Smith", "Beth Smith",
    "Jerry Smith", "Summer Smith"})
    @Story("Get character by '{name}' name")
    public void getCharactersByName(String name) throws JsonProcessingException {
        APIResponse response = request.get(getProperty("character"),
                RequestOptions.create()
                        .setQueryParam("name", name));
        Assertions.assertEquals(200, response.status());
        String jsonString = response.text();
        JsonNode node = objectMapper.readTree(jsonString);
        String arrayString = node.get("results").toString();
        List<Character> characters = objectMapper.readValue(arrayString, new TypeReference<>() {});

        characters.forEach(character -> {
            try {
                Assertions.assertEquals(character.getName(), name);
            } catch (AssertionError e) {
                Allure.addAttachment("The name is different: ", character.toString());
                throw e;
            }
        });
    }
}
