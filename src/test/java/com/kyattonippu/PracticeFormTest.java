package com.kyattonippu;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll () {
        Configuration.browserSize = System.getProperty("browserSize","1440x900");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.pageLoadStrategy = "eager";
        open("https://demoqa.com/automation-practice-form");
        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");
    }

    @Test
    void fullFormTest () {
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        $("#firstName").setValue("Test");
        $("#lastName").setValue("User");
        $("#userEmail").setValue("test@test.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("0987654321");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__day--001").click();
        $("#subjectsInput").setValue("Biology").pressEnter();
        $("[for='hobbies-checkbox-2']").click();
        $("#uploadPicture").uploadFromClasspath("testimage.jpg");
        $("#currentAddress").setValue("DC, Universe");
        $("#submit").scrollTo();
        $(byText("Select State")).click();
        $(byText("Rajasthan")).click();
        $(byText("Select City")).click();
        $(byText("Jaipur")).click();
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Student Name Test User"),
                text ("Student Email test@test.com"),
                text("Gender Female"),
                text("Mobile 0987654321"),
                text("Date of Birth 01 January,1990"),
                text("Subjects Biology"),
                text("Hobbies Reading"),
                text("Picture testimage.jpg"),
                text("Address DC, Universe"),
                text("State and City Rajasthan Jaipur"));

        $("#closeLargeModal").click();
    }
}
