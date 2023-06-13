package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pojo.Location;

import static utils.PropertiesReader.getProperty;

@Tag("api")
@Feature("Rick & Morty. Location")
public class LocationTest extends BaseApiTest {

    @ParameterizedTest
    @CsvSource({"1, Earth (C-137)",
            "2, Abadango",
            "3, Citadel of Ricks",
            "4, Worldender's lair",
            "5, Anatomy Park"})
    @Story("Get location by id")
    public void getLocationById(String id, String name) throws JsonProcessingException {
        APIResponse response = request.get(getProperty("location") + id);
        Assertions.assertEquals(200, response.status());
        String jsonString = response.text();
        Location location = objectMapper.readValue(jsonString, Location.class);

        Assertions.assertEquals(name, location.getName());
    }
}
