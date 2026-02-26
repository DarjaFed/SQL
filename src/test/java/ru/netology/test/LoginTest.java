package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DbHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.DashboardPage;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    LoginPage loginPage;
    DataHelper.AuthInfo authInfo = DataHelper.getValidUser();

    @AfterAll
    static void tearDownAll() {
        DbHelper.cleanDatabase();
    }

    @BeforeEach
    void setup() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @Test
    @DisplayName("Should successfully login with valid user")
    void shouldSuccessfulLogin() {
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DbHelper.getVerificationCode();
        verificationPage.verify(verificationCode.getCode());
    }

    @Test
    @DisplayName("Should get error if user does not exist")
    void shouldGetErrorNotificationIfRandomUser() {
        var randomUser = DataHelper.generateRandomUser();
        loginPage.login(randomUser);
        loginPage.verifyErrorNotification("Ошибка! Неверно указан логин или пароль");
    }

    @Test
    @DisplayName("Should get error if verification code is wrong")
    void shouldGetErrorIfWrongCode() {
        var verificationPage = loginPage.validLogin(authInfo);
        var wrongCode = DataHelper.generateRandomVerificationCode();
        verificationPage.verify(wrongCode.getCode());
        verificationPage.verifyErrorNotification("Ошибка! Неверно указан код! Попробуйте ещё раз.");
    }
}