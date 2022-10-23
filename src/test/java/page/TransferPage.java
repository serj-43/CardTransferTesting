package page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {


    private SelenideElement cardFrom = $("[data-test-id=from] input");
    private SelenideElement transferAmount = $("[data-test-id=amount] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement errorNotification = $x("//div[@data-test-id='error-notification']//div[text()='Ошибка']");

    public TransferPage() {
        transferAmount.shouldBe(visible);
    }

    public DashBoardPage transfer(float summ, String cardNumber) {
        transferAmount.setValue(Float.toString(summ));
        cardFrom.setValue(cardNumber);
        transferButton.click();
        return new DashBoardPage();
    }
    public void checkError(){
        errorNotification.shouldBe(visible, Duration.ofSeconds(20));
    }
}
