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
    private SelenideElement depositButton1 = $x("//div[contains(text(),\"0001\")]//button[@data-test-id='action-deposit']");
    private SelenideElement depositButton2 = $x("//div[contains(text(),\"0002\")]//button[@data-test-id='action-deposit']");
    private SelenideElement balance1 = $x("//li[@class='list__item'][1]");
    private SelenideElement balance2 = $x("//li[@class='list__item'][2]");

    public DashBoardPage() {
        heading.shouldBe(visible);
    }

    public Float getBalance(SelenideElement balanceString) {
        String[] text = balanceString.innerText().split(" ");
        return Float.parseFloat(text[5]);
    }

    public TransferPage depositCard1() {
        depositButton1.click();
        return new TransferPage();
    }

    public TransferPage depositCard2() {
        depositButton2.click();
        return new TransferPage();
    }

}
