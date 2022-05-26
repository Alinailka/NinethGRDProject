import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestClass {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {

        var loginPage = new LoginPage();
        // можно заменить на var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

       }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {

        var loginPage = new LoginPage();
        // можно заменить на var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        DashboardPage dashboardPage = new DashboardPage();
        $(byText("Ваши карты")).shouldBe(Condition.visible);
        $(".list__item div / ").click();
        //$$("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").find(exactText("Пополнить")).click();
        //$x("//*[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] >[type=\"button\"]" ).click();
      //  $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").click();
       $("[data-test-id='amount'] input").setValue("1000");
        $("[data-test-id='from'] input").setValue("5559 0000 0000 0001");
        $("[data-test-id='action-transfer'] input").click();



    }

}
