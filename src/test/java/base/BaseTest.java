package base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeEach
    void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
    }

//    @AfterEach
//    void onTestFailure(TestInfo testInfo) {
//        if (testInfo.getExecutionException().isPresent()) {
//            page.screenshot(new Page.ScreenshotOptions()
//                    .setPath(Paths.get("errors/" + testInfo.getDisplayName() + ".png")));
//        }
//    }

    @AfterEach
    void tearDown() {
        if (playwright != null) {
            playwright.close();
        }
    }
}