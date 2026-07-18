import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class RegistrationCardTest {


    public String generateData(int day, String pattern) {
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern(pattern));

    }
    @Test
    void ShouldAcceptedForm() {
        Selenide.open("http://localhost:9999");
        String planningDate = generateData(4, "dd.MM.yyyy");
        $("[data-test-id=city] input").setValue("Москва");
        $ ("[data-test-id=date] input").press(Keys.chord(Keys.LEFT_CONTROL, "a"), Keys.DELETE) .setValue(planningDate);
        $("[data-test-id=name] input").setValue ("Иван Иванович Иванов");
        $("[data-test-id=phone] input") .setValue ("+79051234569");
        $("[data-test-id=agreement]") .click();
        $$("button").findBy(text("Забронировать")).click();
        $("div.notification__title").shouldHave(text("Успешно"), Duration.ofSeconds(15));
        $("div.notification__content").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));

    }
}
