package data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {


    public static String getValidActiveCard() {
        return "4444 4444 4444 4441";
    }

    public static String getValidDeclinedCard() {
        return ("4444 4444 4444 4442");
    }

    public static String getZeroNumberCard() {
        return ("0000 0000 0000 0000");
    }

    public static String getInvalidPatternNumberCard() {
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en"), new RandomService());
        return fakeValuesService.numerify("#### #### #### ##");
    }

    public static String getCurrentMonth() {return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));}
    public static String getZeroMonth() {
        return ("00");
    }
    public static String getInvalidMonth() {
        return ("13");
    }

    public static String getYear(int shiftYear) {
            return LocalDate.now().plusYears(shiftYear).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getValidOwner() {
        Faker faker = new Faker((new Locale("en")));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getInvalidLocaleOwner() {
        Faker faker = new Faker((new Locale("ru")));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

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

    public static String getEmptyFieldValue() {return ("");}
}