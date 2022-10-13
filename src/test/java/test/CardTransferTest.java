package test;

import data.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.DashBoardPage;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTransferTest {

    @BeforeAll
    static void initOperations() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferBetweenCards1() {
        var AuthInfo = DataHelper.getAuthInfo();
        var Code = DataHelper.getVerificationCodeFor(AuthInfo);
        var CardNumbers = DataHelper.getClientCards(AuthInfo);
       // var CardShortNumbers = DataHelper.getClientCardLastNumber(CardNumbers);
        float TransferSumm = 500.0F;


        var LoginPage = new LoginPage();
        var VerificationPage = LoginPage.validLogin(AuthInfo);
        var DashBoardPage = VerificationPage.validCode(Code);
        DashBoardPage.depositCard1(TransferSumm, CardNumbers.getCardArray()[1]);

        float expected1 = DashBoardPage.getBalance(DashBoardPage.getBalance1()) + TransferSumm;
        float expected2 = DashBoardPage.getBalance(DashBoardPage.getBalance2()) - TransferSumm;
        assertEquals(expected1, DashBoardPage.getBalance(DashBoardPage.getBalance1()));
        assertEquals(expected2, DashBoardPage.getBalance(DashBoardPage.getBalance2()));
    }
}
