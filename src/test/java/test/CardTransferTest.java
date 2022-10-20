package test;

import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CardTransferTest {

    @BeforeEach
    void initOperations() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferBetweenCards1() {
        var AuthInfo = DataHelper.getAuthInfo();
        var Code = DataHelper.getVerificationCodeFor(AuthInfo);
        var CardNumbers = DataHelper.getClientCards(AuthInfo);
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

    @Test
    void shouldTransferBetweenCards2() {
        var AuthInfo = DataHelper.getAuthInfo();
        var Code = DataHelper.getVerificationCodeFor(AuthInfo);
        var CardNumbers = DataHelper.getClientCards(AuthInfo);
        float TransferSumm = 5000;

        var LoginPage = new LoginPage();
        var VerificationPage = LoginPage.validLogin(AuthInfo);
        var DashBoardPage = VerificationPage.validCode(Code);
        DashBoardPage.depositCard2(TransferSumm, CardNumbers.getCardArray()[0]);

        float expected1 = DashBoardPage.getBalance(DashBoardPage.getBalance1()) + TransferSumm;
        float expected2 = DashBoardPage.getBalance(DashBoardPage.getBalance2()) - TransferSumm;
        assertEquals(expected1, DashBoardPage.getBalance(DashBoardPage.getBalance1()));
        assertEquals(expected2, DashBoardPage.getBalance(DashBoardPage.getBalance2()));
    }

    @Test
    void shouldNotTransferWithoutCard1() {
        var AuthInfo = DataHelper.getAuthInfo();
        var Code = DataHelper.getVerificationCodeFor(AuthInfo);
        var CardNumbers = DataHelper.getWrongClientCard();
        float TransferSumm = 5000;

        var LoginPage = new LoginPage();
        var VerificationPage = LoginPage.validLogin(AuthInfo);
        var DashBoardPage = VerificationPage.validCode(Code);
        DashBoardPage.depositCard1(TransferSumm, CardNumbers.getCardArray()[0]);
        DashBoardPage.checkError();
    }

    @Test
    void shouldNotTransferWithoutCard2() {
        var AuthInfo = DataHelper.getAuthInfo();
        var Code = DataHelper.getVerificationCodeFor(AuthInfo);
        var CardNumbers = DataHelper.getWrongClientCard();
        float TransferSumm = 5000;

        var LoginPage = new LoginPage();
        var VerificationPage = LoginPage.validLogin(AuthInfo);
        var DashBoardPage = VerificationPage.validCode(Code);
        DashBoardPage.depositCard2(TransferSumm, CardNumbers.getCardArray()[0]);
        DashBoardPage.checkError();
    }

    @Test
    void shouldTransferOverSum1() {
        var AuthInfo = DataHelper.getAuthInfo();
        var Code = DataHelper.getVerificationCodeFor(AuthInfo);
        var CardNumbers = DataHelper.getClientCards(AuthInfo);

        var LoginPage = new LoginPage();
        var VerificationPage = LoginPage.validLogin(AuthInfo);
        var DashBoardPage = VerificationPage.validCode(Code);
        float TransferSumm = (DashBoardPage.getBalance(DashBoardPage.getBalance2()) + 5000);
        DashBoardPage.depositCard1(TransferSumm, CardNumbers.getCardArray()[1]);
        DashBoardPage.checkError();
    }

    @Test
    void shouldTransferOverSum2() {
        var AuthInfo = DataHelper.getAuthInfo();
        var Code = DataHelper.getVerificationCodeFor(AuthInfo);
        var CardNumbers = DataHelper.getClientCards(AuthInfo);

        var LoginPage = new LoginPage();
        var VerificationPage = LoginPage.validLogin(AuthInfo);
        var DashBoardPage = VerificationPage.validCode(Code);
        float TransferSumm = (DashBoardPage.getBalance(DashBoardPage.getBalance2()) + 5000);
        DashBoardPage.depositCard2(TransferSumm, CardNumbers.getCardArray()[0]);
        DashBoardPage.checkError();
    }
}
