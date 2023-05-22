package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class PositiveTest {
    private String Date(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void validFormCompletion() {
        open ("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Санкт-Петербург");
        String currentDate = Date(5, "dd.MM.yyyy");
        $("[data-test-id = date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(currentDate);
        $("[data-test-id = name] input").setValue("Есенин Сергей");
        $("[data-test-id = phone] input").setValue("+79117778899");
        $("[data-test-id = agreement]").click();
        $(".button").click();
        $(".notification__content")
               .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));

    }
    @Test
    void threeHundredSixtyFive() {
        open ("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Москва");
        String currentDate = Date(365, "dd.MM.yyyy");
        $("[data-test-id = date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id = date] input").sendKeys(currentDate);
        $("[data-test-id = name] input").setValue("Есенин Сергей");
        $("[data-test-id = phone] input").setValue("+79117778899");
        $("[data-test-id = agreement]").click();
        $(".button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));

    }
}