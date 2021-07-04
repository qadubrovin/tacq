package ru.tacq.tinkoff.tests;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.tacq.tinkoff.pages.TacqPromoPage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.title;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;


public class TacqPromoTests extends TestBase {

    TacqPromoPage promo = new TacqPromoPage();

    String acqPromoHeader = "Сервисы Тинькофф для приема платежей от физлиц онлайн, в торговой точке и для доставки",
            tacqPromoHeader = "Эквайринг за три шага",
            tariffsText = "списание остатка до минимальной платы происходит в конце месяца",
            alertNotificationPhone = "Укажите номер телефона",
            englishHeader = "Our Products";

    @Test
    @DisplayName("Переход из Тинькофф бизнес в раздел -Торговый эквайринг-")
    void toTacqFromSme() {
        step("Открыть https://www.tinkoff.ru/business/", () -> {
            promo.openTBusiness();
        });

        step("Нажать на таб -Прием платежей-", () -> {
            promo.acceptingPaymentsTab.click();
        });

        step("Проверить, что на экране есть текст -Сервисы Тинькофф для приема платежей от физлиц онлайн, в торговой точке и для доставки-", () -> {
            promo.mainApplication.shouldHave(text(acqPromoHeader));
        });

        step("Нажать на кнопку -Подробнее- на плитке -Торговый эквайринг-", () -> {
            promo.tacqMore.click();
        });

        step("Убедиться, что на странице есть текст -Эквайринг за три шага-", () -> {
            promo.mainApplication.shouldHave(text(tacqPromoHeader));
        });
    }

    @Test
    @DisplayName("Скачивание Тарифов, проверка текстовки документа")
    void downloadTariffs() {

        step("Открыть https://www.tinkoff.ru/business/acquiring/", () -> {
            promo.openTacqPromo();
        });

        step("Скачать pdf с тарифами в подвале страницы, убедиться, что в документе есть текст " +
                "-списание остатка до минимальной платы происходит в конце месяца-", () -> {

            promo.downloadTariffsButton.scrollIntoView(true);
            File tariffsPdf = $$x(promo.locatorForTariffs).last().download();
            PDF pdf = new PDF(tariffsPdf);
            assertThat(pdf, PDF.containsText(tariffsText));
        });
    }

    @Test
    @DisplayName("Проверка работы калькулятора, считающего стоимость эквайринга ")
    void checkCalc() {

        step("Открыть https://www.tinkoff.ru/business/acquiring/", () -> {
            promo.openTacqPromo();
        });

        step("Вписать в калькулятор,  в оборотные средства 150000", () -> {
            promo.inputCalcSum("150000");
        });

        step("Вписать в количество терминалов - 3", () -> {
            promo.inputTerminalCount("3");
        });

        step("Проверить, что стоимость эквайринга 5970", () -> {
            promo.checkCalcSum("5 970");
        });

    }

    @Test
    @DisplayName("Телефон обязателен для отправки заявки")
    void phoneNeeedfulForApplication() {
        step("Открыть https://www.tinkoff.ru/business/acquiring/", () -> {
            promo.openTacqPromo();
        });

        step("Нажимаем на -Подключить- не введя телефон", () -> {
            promo.plugButton.click();
        });

        step("Убеждаемся, что появилась нотификация -Укажите номер телефона-", () -> {
            promo.mainApplication.shouldHave(text(alertNotificationPhone));
        });
    }

    @Test
    @DisplayName("Переключение на английскую версию")
    void switchToEnglish() {
        step("Открыть https://www.tinkoff.ru/business/acquiring/", () -> {
            promo.openTacqPromo();
        });

        step("Нажимаем на кнопку English  подвале страницы", () -> {
            promo.switchToEnglish();
        });

        step("Убеждаемся, что отображается версия на английском", () -> {
            promo.mainApplication.shouldHave(text(englishHeader));
        });
    }


    @Test
    @DisplayName("Title содержит текст -Банк для малого и среднего бизнеса-")
    void titleTest() {
        step("Открыть https://www.tinkoff.ru/business/", () -> {
            promo.openTBusiness();
        });

        step("Тайтл должениметь текст 'Банк для малого и среднего бизнеса'", () -> {
            String expectedTitle = "Банк для малого и среднего бизнеса";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

}