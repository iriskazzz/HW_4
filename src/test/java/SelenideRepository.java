import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class SelenideRepository {

  @Test
  void shouldFindSoftAssertions(){
    // Откройте страницу Selenide в Github
    open("https://github.com");
    $("[placeholder='Search or jump to...']").click();
    $("#query-builder-test").setValue("selenide").pressEnter();
    $$("div [data-testid='results-list'] div").first().$("a").click();
    $("#repository-container-header").shouldHave(text("selenide / selenide"));

    // Перейдите в раздел Wiki проекта
    $("#wiki-tab").click();

    // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
    $("#wiki-pages-filter").setValue("SoftAssertions").pressEnter();
    $("#wiki-pages-box").shouldHave(text("SoftAssertions"));

    // Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
    $(byText("SoftAssertions")).click();
    $(".markdown-body").shouldHave(text(
            "@ExtendWith({SoftAssertsExtension.class})\n" +
                    "class Tests {\n" +
                    "  @Test\n" +
                    "  void test() {\n" +
                    "    Configuration.assertionMode = SOFT;\n" +
                    "    open(\"page.html\");\n" +
                    "\n" +
                    "    $(\"#first\").should(visible).click();\n" +
                    "    $(\"#second\").should(visible).click();\n" +
                    "  }\n" +
                    "}"));
  }
}
