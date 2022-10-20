package page;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class DashBoardPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement cardFrom = $("[data-test-id=from] input");
    private SelenideElement transferAmount = $("[data-test-id=amount] input");
    private SelenideElement undoButton = $("[data-test-id=action-cancel]");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement reloadButton = $("[data-test-id=action-reload]");
    private SelenideElement depositButton1 = $x("//div[contains(text(),\"0001\")]//button[@data-test-id='action-deposit']");
    private SelenideElement depositButton2 = $x("//div[contains(text(),\"0002\")]//button[@data-test-id='action-deposit']");
    private SelenideElement balance1 = $x("//li[@class='list__item'][1]");
    private SelenideElement balance2 = $x("//li[@class='list__item'][2]");
    private SelenideElement errorNotification = $x("//div[@data-test-id='error-notification']//div[text()='Ошибка']");
    public DashBoardPage() {
        heading.shouldBe(visible);
    }

    public Float getBalance(SelenideElement balanceString) {
        String [] text = balanceString.innerText().split(" ");
        return Float.parseFloat(text[5]);
    }

    public DashBoardPage depositCard1(float summ, String cardNumber) {
        depositButton1.click();
        transferAmount.should(visible, Duration.ofSeconds(20));
        transferAmount.setValue(Float.toString(summ));
        cardFrom.setValue(cardNumber);
        transferButton.click();
        return this;
    }

    public DashBoardPage depositCard2(float summ, String cardNumber) {
        depositButton2.click();
        transferAmount.should(visible, Duration.ofSeconds(20));
        transferAmount.setValue(Float.toString(summ));
        cardFrom.setValue(cardNumber);
        transferButton.click();
        return this;
    }

    public void checkError(){
        errorNotification.shouldBe(visible, Duration.ofSeconds(20));
    }

}
