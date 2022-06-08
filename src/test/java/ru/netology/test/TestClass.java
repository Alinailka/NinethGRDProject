package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClass {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {

        var loginPage = new LoginPage();
        // можно заменить на var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        DashboardPage dashboardPage = new DashboardPage();
        var firstNumber = DataHelper.getFirstNumber();
        var secondNumber = DataHelper.getSecondNumber();
        int amount = 1000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstNumber) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondNumber) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondNumber);
        dashboardPage = transferPage.makeTransfer(String.valueOf(amount), firstNumber);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstNumber);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondNumber);
        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
}
