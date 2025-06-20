package courseplayw;

import base.BaseTest;
import com.microsoft.playwright.Route;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;


public class StatusCodeInterceptionTest extends BaseTest {

    @BeforeEach
    void setUp() {
        // Перехват запроса к /status_codes/404
        context.route("**/status_codes/404", route -> {
            route.fulfill(new Route.FulfillOptions()
                    .setStatus(200)
                    .setHeaders(Collections.singletonMap("Content-Type", "text/html"))
                    .setBody("<h3>Mocked Success Response</h3>")
            );
        });
    }

    @Test
    void testMockedStatusCode() {
        page.navigate("https://the-internet.herokuapp.com/status_codes");
        // Клик по ссылке "404"
        page.getByText("404").click();
        // ??? здесь нужно ждать загрузки страницы ?
        // Проверка мок-текста
        Assertions.assertTrue(page.isVisible("h3 >> text='Mocked Success Response'"), "Заголовок Mocked Success Response не найден"
        );

    }


}
