package guru.qa.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.*;

public class SelenideTest {

    private static final String REPOSITORY = "selenide/selenide";
    private static final String ISSUE_NAME = "Emulate css print media type";

    @Test
    public void testGithubIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();
        $(linkText(REPOSITORY)).click();
        $(partialLinkText("Issues")).click();
        $(withText(ISSUE_NAME)).should(Condition.visible);
    }
}
