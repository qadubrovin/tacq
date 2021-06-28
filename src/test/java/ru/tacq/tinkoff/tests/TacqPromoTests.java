package ru.tacq.tinkoff.tests;

import ru.tacq.tinkoff.helpers.DriverUtils;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class TacqPromoTests extends TestBase {
    @Test
    @DisplayName("Переход из Тинькофф бизнес в раздел -Торговый эквайринг-")
    void toTacqFromSme() {
        step("Открыть https://www.tinkoff.ru/business/", () -> {
            open("https://www.tinkoff.ru/business/");
        });

        step("Нажать на таб -Прием платежей-", () -> {
            step("// todo: just add selenium action");
        });

        step("Проверить, что на экране есть текст -Сервисы Тинькофф для приема платежей от физлиц онлайн, в торговой точке и для доставки-", () -> {
            step("// todo: just add selenium action");
        });

        step("Нажать на кнопку -Подробнее- на плитке -Торговый эквайринг-", () -> {
            step("// todo: just add selenium action");
        });

        step("Убедиться, что на странице есть текст -Эквайринг за три шага-", () -> {
            step("// todo: just add selenium action");
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