package page;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DashBoardPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement depositButton1 = $x("//div[contains(text(),\"0001\")]//button[@data-test-id='action-deposit']");
    private SelenideElement depositButton2 = $x("//div[contains(text(),\"0002\")]//button[@data-test-id='action-deposit']");
    private String balanceExpression = "//li[@class='list__item']";

    public DashBoardPage() {
        heading.shouldBe(visible);
    }

    public Float getBalance(int shortNumber) {
        SelenideElement balance = $x(balanceExpression + "[" + shortNumber + "]");
        String[] text = balance.innerText().split(" ");
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
