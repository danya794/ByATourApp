package data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    // Генерация для поля "Номер Карты"
    public static String getValidActiveCard() {
        return "4444 4444 4444 4441";
    }

    public static String getValidDeclinedCard() {
        return ("4444 4444 4444 4442");
    }

    public static String getZeroNumberCard() {
        return ("0000 0000 0000 0000");
    }

    public static String getEmptyNumberCard() {
        return ("");
    }

    public static String getInvalidNumberCard() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"), new RandomService());
        return fakeValuesService.numerify("#### #### #### ####");
    }

    public static String getInvalidPatternNumberCard() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"), new RandomService());
        return fakeValuesService.numerify("#### #### #### ##");
    }

    // Генерация для поля "Месяц"
    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getPreviousMonth() {
        int currentMonth = LocalDate.now().getMonthValue();
        if (currentMonth == 1) {
            return getLastMonth();
        } else {
            return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
        }
    }

    public static String getFirstMonth() {
        int subtractMonth = LocalDate.now().getMonthValue();
        return LocalDate.now().minusMonths(subtractMonth - 1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getLastMonth() {
        int plusMonth = LocalDate.now().getMonthValue();
        return LocalDate.now().plusMonths(12 - plusMonth).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getZeroMonth() {
        return ("00");
    }

    public static String getInvalidMonth() {
        return ("13");
    }

    public static String getEmptyMonth() {
        return ("");
    }

    // Генерация для поля "Год"
    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getPreviousYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getNextYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getZeroYear() {
        return ("00");
    }

    public static String getEmptyYear() {
        return ("");
    }

    // Генерация для поля "Владелец"
    public static String getValidOwner() {
        Faker faker = new Faker((new Locale("en")));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getInvalidLocaleOwner() {
        Faker faker = new Faker((new Locale("ru")));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getInvalidOwner() {
        Faker faker = new Faker((new Locale("en")));
        return faker.name().firstName();
    }

    public static String getEmptyOwner() {
        return ("");
    }

    // Генерация для поля "CVC/CVV"
    public static String getValidCVV() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"), new RandomService());
        return fakeValuesService.numerify("###");
    }

    public static String getZeroCVV() {
        return ("000");
    }

    public static String getInvalidCVV() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"), new RandomService());
        return fakeValuesService.numerify("##");
    }

    public static String getEmptyCVV() {
        return ("");
    }

}