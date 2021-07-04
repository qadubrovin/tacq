package ru.tacq.tinkoff.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TacqPromoPage {

    //selectors
    public SelenideElement acceptingPaymentsTab = $(withText("Прием платежей")),
            mainApplication = $("div.application"),
            tacqMore = $("a[href='/business/acquiring/']", 1).find(byText("Подробнее")),
            downloadTariffsButton = $x("//*[@data-module-type='productFooter']//*[contains(text(),'Тарифы')]/../.."),
            plugButton = $("button[type='submit']");

    public String locatorForTariffs = "//*[@data-module-type='productFooter']//*[contains(text(),'Тарифы')]/../..";

    //methods
    public void openTBusiness() {
        open("https://www.tinkoff.ru/business/");
        $("div.application").shouldHave(text("Начало бизнеса не выходя из дома"));
    }

    public void openTacqPromo() {
        open("https://www.tinkoff.ru/business/acquiring/");
        $("div.application").shouldHave(text("Эквайринг за три шага"));
    }

    public void inputCalcSum(String sum) {
        $x("//*[contains(text(),'Оборотные средства')]/..").scrollIntoView(true);
        $x("//*[contains(text(),'Оборотные средства')]/..").click();

        int i = 0;
        while (i < 5) {
            actions().sendKeys(Keys.chord(Keys.CONTROL, "a")).perform();
            actions().sendKeys(Keys.chord(Keys.BACK_SPACE)).perform();
            i++;
        }
        actions().sendKeys(sum).perform();
    }

    public void inputTerminalCount(String terminals) {
        $x("//*[contains(text(),'Количество терминалов')]/..").scrollIntoView(true);
        $x("//*[contains(text(),'Количество терминалов')]/..").click();
        actions().sendKeys(Keys.chord(Keys.CONTROL, "a")).perform();
        actions().sendKeys(Keys.chord(Keys.BACK_SPACE)).perform();
        actions().sendKeys(terminals).perform();
    }

    public void checkCalcSum(String calcSum) {
        $x("//*[contains(text(),'Стоимость эквайринга в месяц')]/..").shouldHave(text("от " + calcSum + " ₽"));
    }


    public void switchToEnglish() {
        $("[href='https://www.tinkoff.ru/eng/']").scrollIntoView(true);
        $("[href='https://www.tinkoff.ru/eng/']").click();
    }


}
