package guru.qa.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.*;

public class WebSteps {

    @Step("Open the main page")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Search for a repository {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
    }

    @Step("Click on the repository link {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Click on the Issues tab")
    public void openIssuesTab() {
        $(partialLinkText("Issues")).click();
    }

    @Step("Check that there is an Issue with the name {issueName}")
    public void shouldSeeIssueWithNumber(String issueName) {
        $(withText(issueName)).should(Condition.visible);
        attachScreenshot();
        attachPageSource();
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "PageSource", type = "text/html", fileExtension = ".html")
    public byte[] attachPageSource() {
        return (WebDriverRunner.getWebDriver()).getPageSource().getBytes(StandardCharsets.UTF_8);
    }
}
