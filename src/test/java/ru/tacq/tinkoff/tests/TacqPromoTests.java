package ru.tacq.tinkoff.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.commands.ScrollIntoView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.tacq.tinkoff.helpers.DriverUtils;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;


public class TacqPromoTests extends TestBase {
    @Test
    @DisplayName("Переход из Тинькофф бизнес в раздел -Торговый эквайринг-")
    void toTacqFromSme() {
        step("Открыть https://www.tinkoff.ru/business/", () -> {
            open("https://www.tinkoff.ru/business/");
            $("div.application").shouldHave(text("Начало бизнеса не выходя из дома"));
        });

        step("Нажать на таб -Прием платежей-", () -> {
            $(withText("Прием платежей")).click();
        });

        step("Проверить, что на экране есть текст -Сервисы Тинькофф для приема платежей от физлиц онлайн, в торговой точке и для доставки-", () -> {
            $("div.application").shouldHave(text("Сервисы Тинькофф для приема платежей от физлиц онлайн, в торговой точке и для доставки"));
        });

        step("Нажать на кнопку -Подробнее- на плитке -Торговый эквайринг-", () -> {
            $("a[href='/business/acquiring/']", 1).find(byText("Подробнее")).click();
            ;
        });

        step("Убедиться, что на странице есть текст -Эквайринг за три шага-", () -> {
            $("div.application").shouldHave(text("Эквайринг за три шага"));
        });
    }

    @Test
    @DisplayName("Скачивание Тарифов, проверка текстовки документа")
    void downloadTariffs() {

        step("Открыть https://www.tinkoff.ru/business//acquiring/", () -> {
            open("https://www.tinkoff.ru/business/acquiring/");
            $("div.application").shouldHave(text("Эквайринг за три шага"));
        });

        step("Скачать pdf с тарифами в подвале страницы, убедиться, что в документе есть текст " +
                "-списание остатка до минимальной платы происходит в конце месяца-", () -> {

            $x("//*[@data-module-type='productFooter']//*[contains(text(),'Тарифы')]/../..").scrollIntoView(true);
            File tariffsPdf = $$x("//*[@data-module-type='productFooter']//*[contains(text(),'Тарифы')]/../..").last().download();
            PDF pdf = new PDF(tariffsPdf);
            assertThat(pdf, PDF.containsText("списание остатка до минимальной платы происходит в конце месяца"));
        });
    }

    @Test
    @DisplayName("Проверка работы калькулятора, считающего стоимость эквайринга ")
    void checkCalc() {

        step("Открыть https://www.tinkoff.ru/business/acquiring/", () -> {
            open("https://www.tinkoff.ru/business/acquiring/");
            $("div.application").shouldHave(text("Эквайринг за три шага"));
        });

        step("Вписать в калькулятор,  в оборотные средства 150000", () -> {

            $x("//*[contains(text(),'Оборотные средства')]/..").scrollIntoView(true);
            $x("//*[contains(text(),'Оборотные средства')]/..").click();

            int i = 0;
            while (i < 5) {
                actions().sendKeys(Keys.chord(Keys.CONTROL, "a")).perform();
                actions().sendKeys(Keys.chord(Keys.BACK_SPACE)).perform();
                i++;
            }
            actions().sendKeys("150000").perform();

        });

        step("Вписать в количество терминалов - 3", () -> {

            $x("//*[contains(text(),'Количество терминалов')]/..").scrollIntoView(true);
            $x("//*[contains(text(),'Количество терминалов')]/..").click();
            actions().sendKeys(Keys.chord(Keys.CONTROL, "a")).perform();
            actions().sendKeys(Keys.chord(Keys.BACK_SPACE)).perform();
            actions().sendKeys("3").perform();

        });

        step("Проверить, что стоимость эквайринга 5970", () -> {

            $x("//*[contains(text(),'Стоимость эквайринга в месяц')]/..").shouldHave(text("от 5 970 ₽"));

        });

    }

    @Test
    @DisplayName("Телефон обязателен для отправки заявки")
    void phoneNeeedfulForApplication() {
        step("Open url 'https://www.tinkoff.ru/business/acquiring/'", () ->
                open("https://www.tinkoff.ru/business/acquiring/"));

        step("Нажимаем на -Подключить- не введя телефон", () -> {
            $("button[type='submit']").click();
        });

        step("Убеждаемся, что появилась нотификация -Укажите номер телефона-", () -> {
            $("div.application").shouldHave(text("Укажите номер телефона"));
        });
    }

    @Test
    @DisplayName("Переключение на английскую версию")
    void switchToEnglish() {
        step("Open url 'https://www.tinkoff.ru/business/acquiring/'", () ->
                open("https://www.tinkoff.ru/business/acquiring/"));

        step("Нажимаем на кнопку English  подвале страницы", () -> {
            $("[href='https://www.tinkoff.ru/eng/']").scrollIntoView(true);
            $("[href='https://www.tinkoff.ru/eng/']").click();
        });

        step("Убеждаемся, что отображается версия на английском", () -> {

            $("div.application").shouldHave(text("Our Products"));
        });
    }


    @Test
    @DisplayName("Page title should have header text")
    void titleTest() {
        step("Open url 'https://www.tinkoff.ru/business/'", () ->
                open("https://www.tinkoff.ru/business/"));

        step("Page title should have text 'Банк для малого и среднего бизнеса'", () -> {
            String expectedTitle = "Банк для малого и среднего бизнеса";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Open url 'https://www.tinkoff.ru/business/'", () ->
                open("https://www.tinkoff.ru/business/"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}