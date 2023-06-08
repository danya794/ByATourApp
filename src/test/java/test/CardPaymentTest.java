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

public class CardPaymentTest {
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
    public void buyTourApprovedCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getYear(1), getValidOwner(), getValidCVV());
        var mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkApprovedForm();
        assertEquals("APPROVED", DBUtils.getPaymentStatus());

    }

    @Test
    public void buyTourDeclinedCard() {
        CardInfo card = new CardInfo(getValidDeclinedCard(), getCurrentMonth(), getYear(1), getValidOwner(), getValidCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkApprovedForm();
        assertEquals("DECLINED", DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourInvalidPatternCard() {
        CardInfo card = new CardInfo(getInvalidPatternNumberCard(), getCurrentMonth(), getYear(1), getValidOwner(), getValidCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkCardNumberError();
        assertNull(DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourInvalidMonth() {
        CardInfo card = new CardInfo(getValidActiveCard(), getInvalidMonth(), getYear(0), getValidOwner(), getValidCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkMonthError();
        assertNull(DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourInvalidYearCard() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getYear(-1), getValidOwner(), getValidCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkYearError();
        assertNull(DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourCyrillicOwner() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getYear(1), getInvalidLocaleOwner(), getValidCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkOwnerError();
        assertNull(DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourInvalidCVV() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getYear(1), getValidOwner(), getInvalidCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkCVCError();
        assertNull(DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourEmptyCard() {
        CardInfo card = new CardInfo(getEmptyFieldValue(), getCurrentMonth(), getYear(1), getValidOwner(), getValidCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkCardNumberError();
        assertNull(DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourZeroCard() {
        CardInfo card = new CardInfo(getZeroNumberCard(), getCurrentMonth(), getYear(1), getValidOwner(), getValidCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkCardNumberError();
        assertNull(DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourZeroMonth() {
        CardInfo card = new CardInfo(getValidActiveCard(), getZeroMonth(), getYear(1), getValidOwner(), getValidCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkMonthError();
        assertNull(DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourEmptyMonth() {
        CardInfo card = new CardInfo(getValidActiveCard(), getEmptyFieldValue(), getYear(1), getValidOwner(), getValidCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkMonthError();
        assertNull(DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourZeroYear() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getYear(0), getValidOwner(), getValidCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkYearError();
        assertNull(DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourEmptyYear() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getEmptyFieldValue(), getValidOwner(), getValidCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkYearError();
        assertNull(DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourEmptyOwner() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getYear(1), getEmptyFieldValue(), getValidCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkOwnerError();
        assertNull(DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourZeroCVV() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getYear(1), getValidOwner(), getZeroCVV());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkCVCError();
        assertNull(DBUtils.getPaymentStatus());
    }

    @Test
    void buyTourEmptyCVV() {
        CardInfo card = new CardInfo(getValidActiveCard(), getCurrentMonth(), getYear(1), getValidOwner(), getEmptyFieldValue());
        MainPage mainPage = new MainPage();
        mainPage.checkPaymentButton().
                fillingForm(card).
                checkCVCError();
        assertNull(DBUtils.getPaymentStatus());
    }
}
