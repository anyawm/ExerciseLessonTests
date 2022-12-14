package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static tests.TestData.*;

public class RegistrationWithStaticTestDataTests extends TestBase {

 //    String userName = "Alex";
 //   String userSurname = "Egorov";
 //   String userEmail = "alex@egorov.com";

    //String userName = "Alex",
    //       userSurname = "Egorov",
    //      userEmail = "alex@egorov.com";

/*    static String userName,
            userSurname,
            userEmail;*/

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    //static void beforeEach () {
    //    userName = getNewUserName();
    //    userSurname = getNewUserSurname();
    //   userEmail = getNewUserEmail();
    //}

    @Test
    void successfulRegistrationTest() {
       // String userName = "Alex";

        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()"); //удаление конкретной рекламы
        executeJavaScript("$('footer').remove()"); //удаление футера

        $("#firstName").setValue(userName);
        $("#lastName").setValue(userSurname);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText("Other")).click(); // best
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2008");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click(); //если в зоне видимости две одинаковые даты разных месяцев
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("img/1.png");
        $("#currentAddress").setValue("Some address 1");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(userName), text("Egorov"),
                text("alex@egorov.com"), text("1234567890"));
    }
}