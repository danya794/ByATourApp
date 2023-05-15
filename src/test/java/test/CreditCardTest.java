package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.CardInfo;
import data.DBUtils;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.MainPage;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreditCardTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
        DBUtils.clearTables();
    }

    @Test
    public void buyTourApprovedCreditCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkApprovedForm();
        assertEquals("APPROVED", DBUtils.getCreditStatus());

    }

    @Test
    public void buyTourDeclinedCreditCard() {
        CardInfo card = new CardInfo(getValidDeclinedCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkApprovedForm();
        assertEquals("DECLINED", DBUtils.getCreditStatus());
    }

    @Test
    void buyTourInvalidPatternCreditCard() {
        CardInfo card = new CardInfo(getInvalidPatternNumberCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkCardNumberError();
        assertNull(DBUtils.getCreditStatus());
    }

    @Test
    void buyTourInvalidMonthCreditCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getInvalidMonth(), getCurrentYear(), getValidOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkMonthError();
        assertNull(DBUtils.getCreditStatus());
    }

    @Test
    void buyTourInvalidYearCreditCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getPreviousYear(), getValidOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkYearError();
        assertNull(DBUtils.getCreditStatus());
    }

    @Test
    void buyTourCyrillicOwnerCreditCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getInvalidLocaleOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkOwnerError();
        assertNull(DBUtils.getCreditStatus());
    }

    @Test
    void buyTourInvalidCVVCreditCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getInvalidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkCVCError();
        assertNull(DBUtils.getCreditStatus());
    }

    @Test
    void buyTourEmptyCreditCard() {
        CardInfo card = new CardInfo(getEmptyNumberCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkCardNumberError();
        assertNull(DBUtils.getCreditStatus());
    }

    @Test
    void buyTourZeroCreditCard() {
        CardInfo card = new CardInfo(getZeroNumberCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkCardNumberError();
        assertNull(DBUtils.getCreditStatus());
    }

    @Test
    void buyTourZeroMonthCreditCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getZeroMonth(), getNextYear(), getValidOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkMonthError();
        assertNull(DBUtils.getCreditStatus());
    }

    @Test
    void buyTourEmptyMonthCreditCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getEmptyMonth(), getNextYear(), getValidOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkMonthError();
        assertNull(DBUtils.getCreditStatus());
    }

    @Test
    void buyTourZeroYearCreditCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getZeroYear(), getValidOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkYearError();
        assertNull(DBUtils.getCreditStatus());
    }

    @Test
    void buyTourEmptyYearCreditCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getEmptyYear(), getValidOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkYearError();
        assertNull(DBUtils.getCreditStatus());
    }

    @Test
    void buyTourEmptyOwnerCreditCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getEmptyOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkOwnerError();
        assertNull(DBUtils.getCreditStatus());
    }

    @Test
    void buyTourZeroCVVCreditCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getZeroCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkCVCError();
        assertNull(DBUtils.getCreditStatus());
    }

    @Test
    void buyTourEmptyCVVCreditCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getNextYear(), getValidOwner(), getEmptyCVV());
        var mainPage = new MainPage();
        mainPage.checkCreditButton().
                fillingForm(card).
                checkCVCError();
        assertNull(DBUtils.getCreditStatus());
    }
}
