package api;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;
import java.util.Map;

import static utils.PropertiesReader.getProperty;

public class BaseApiTest {

    protected static Playwright playwright;
    protected static APIRequestContext request;

    @BeforeAll
    static void createAPIRequestContext() {
        playwright = Playwright.create();
        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(getProperty("host")));
    }

}
