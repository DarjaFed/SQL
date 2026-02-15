package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement login = $("[data-test-id=login] input");
    private SelenideElement password = $("[data-test-id=password] input");
    private SelenideElement button = $("[data-test-id=action-login]");

    public VerificationPage login(String l, String p) {
        login.setValue(l);
        password.setValue(p);
        button.click();
        return new VerificationPage();
    }
}