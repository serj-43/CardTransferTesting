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
        var authInfo = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCodeFor(authInfo);
        var cardNumber2 = DataHelper.getClientCard2(authInfo);
        float transferSumm = 500.0F;

        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashBoardPage = verificationPage.validCode(code);
        float expected1 = dashBoardPage.getBalance(dashBoardPage.getBalance1()) + transferSumm;
        float expected2 = dashBoardPage.getBalance(dashBoardPage.getBalance2()) - transferSumm;

        var transferPage = dashBoardPage.depositCard1();
        transferPage.transfer(transferSumm, cardNumber2.getCardNumber());

        assertEquals(expected1, dashBoardPage.getBalance(dashBoardPage.getBalance1()));
        assertEquals(expected2, dashBoardPage.getBalance(dashBoardPage.getBalance2()));
    }

    @Test
    void shouldTransferBetweenCards2() {
        var authInfo = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCodeFor(authInfo);
        var cardNumber1 = DataHelper.getClientCard1(authInfo);
        float transferSumm = 5000;

        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashBoardPage = verificationPage.validCode(code);
        float expected1 = dashBoardPage.getBalance(dashBoardPage.getBalance1()) - transferSumm;
        float expected2 = dashBoardPage.getBalance(dashBoardPage.getBalance2()) + transferSumm;

        var transferPage = dashBoardPage.depositCard2();
        transferPage.transfer(transferSumm, cardNumber1.getCardNumber());

        assertEquals(expected1, dashBoardPage.getBalance(dashBoardPage.getBalance1()));
        assertEquals(expected2, dashBoardPage.getBalance(dashBoardPage.getBalance2()));
    }

    @Test
    void shouldNotTransferWithoutCard1() {
        var authInfo = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCodeFor(authInfo);
        var cardNumber = DataHelper.getWrongClientCard();
        float transferSumm = 5000;

        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashBoardPage = verificationPage.validCode(code);

        var transferPage = dashBoardPage.depositCard1();
        transferPage.transfer(transferSumm, cardNumber.getCardNumber());
        transferPage.checkError();
    }

    @Test
    void shouldNotTransferWithoutCard2() {
        var authInfo = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCodeFor(authInfo);
        var cardNumber = DataHelper.getWrongClientCard();
        float transferSumm = 1;

        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashBoardPage = verificationPage.validCode(code);

        var transferPage = dashBoardPage.depositCard2();
        transferPage.transfer(transferSumm, cardNumber.getCardNumber());
        transferPage.checkError();
    }
    @Test
    void shouldTransferOverSum1() {
        var authInfo = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCodeFor(authInfo);
        var cardNumber2 = DataHelper.getClientCard2(authInfo);

        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashBoardPage = verificationPage.validCode(code);
        float transferSumm = (dashBoardPage.getBalance(dashBoardPage.getBalance2()) + 5000);

        var transferPage = dashBoardPage.depositCard1();
        transferPage.transfer(transferSumm, cardNumber2.getCardNumber());
        transferPage.checkError();
    }

    @Test
    void shouldTransferOverSum2() {
        var authInfo = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCodeFor(authInfo);
        var cardNumber1 = DataHelper.getClientCard1(authInfo);

        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashBoardPage = verificationPage.validCode(code);
        float transferSumm = (dashBoardPage.getBalance(dashBoardPage.getBalance1()) + 5000);

        var transferPage = dashBoardPage.depositCard1();
        transferPage.transfer(transferSumm, cardNumber1.getCardNumber());
        transferPage.checkError();
    }
}
