package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeAll;

import static utils.PropertiesReader.getProperty;

public class BaseApiTest {

    protected static Playwright playwright;
    protected static APIRequestContext request;
    protected ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void createAPIRequestContext() {
        playwright = Playwright.create();
        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(getProperty("host")));
    }

}
