package guru.qa.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.*;

public class LambdaStepTest {

    private static final String REPOSITORY = "selenide/selenide";
    private static final String ISSUE_NAME = "Emulate css print media type";

    @Test
    public void testGithubIssueName() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open the main page", () -> {
            open("https://github.com");
        });
        step("Search for a repository " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Click on the repository link " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Click on the Issues tab", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Check that there is an Issue with the name " + ISSUE_NAME, () -> {
            $(withText(ISSUE_NAME)).should(Condition.visible);
            Allure.getLifecycle().addAttachment(
                    "PageSource",
                    "text/html",
                    ".html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8));
        });
    }
}
