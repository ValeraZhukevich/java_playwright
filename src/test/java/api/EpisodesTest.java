package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pojo.Episode;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.addAttachment;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static utils.PropertiesReader.getProperty;

@Tag("api")
@Feature("Rick & Morty. Episode")
public class EpisodesTest extends BaseApiTest {

    @Test
    @Story("All episodes sorted by id")
    public void allEpisodesSortedById() throws JsonProcessingException {
        List<Episode> episodes = new ArrayList<>();
        String page = "1";
        String nextUrl;
        JsonNode node;
        do {
            APIResponse response = request.get(getProperty("episode"),
                    RequestOptions.create()
                            .setQueryParam("page", page));
            Assertions.assertEquals(200, response.status());
            addAttachment("Response", response.text());
            node = objectMapper.readTree(response.text());
            nextUrl = node.get("info").get("next").toString();
            String arrayString = node.get("results").toString();
            episodes.addAll(objectMapper.readValue(arrayString, new TypeReference<>() {}));
            if (nextUrl.equals("null")) page = nextUrl.replaceAll("\\D", "");
            else break;
        } while (true);
        List<Episode> sortedEpisodes = episodes.stream()
                .sorted(comparing(Episode::getId))
                .collect(toList());
        Assertions.assertEquals(sortedEpisodes, episodes);
    }
}
