import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
        private SelenideElement codeField = $("[data-test-id=code] input");
        private SelenideElement verifyButton = $("[data-test-id=action-verify]");

        public VerificationPage() {
            codeField.shouldBe(visible, Duration.ofSeconds(20));
        }

        public DashboardPage validVerify (DataHelper.VerificationCode verificationCode){
            codeField.setValue(verificationCode.getCode());
            verifyButton.click();
            return new DashboardPage();
    }
//    public VerificationPage validVerify (DataHelper.VerificationCode verificationCode){
//        codeField.setValue(verificationCode.getCode());
//        verifyButton.click();
//        return new VerificationPage();
//    }
    }

