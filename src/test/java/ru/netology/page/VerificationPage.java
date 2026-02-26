package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private final SelenideElement code = $("[data-test-id=code] input");
    private final SelenideElement button = $("[data-test-id=action-verify]");
    private final SelenideElement error = $("[data-test-id=error-notification]");
    public void verify(String c) {
        code.setValue(c);
        button.click();
    }
        public void verifyErrorNotification(String text) {
            error.shouldHave(com.codeborne.selenide.Condition.text(text));
        }
    }
