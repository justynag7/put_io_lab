package put.io.testing.audiobooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import put.io.testing.junit.Calculator;

import static org.junit.jupiter.api.Assertions.*;


//Pytanie 5.1: Będzię to testowanie typu whitebox
//Pytanie 5.2: W metodzie calculate znajdują się 4 możliwe ścieżki

class AudiobookPriceCalculatorTest {
    private AudiobookPriceCalculator priceCalc;
    private Customer customer;
    private Audiobook audiobook;

    @BeforeEach
    public void setUp() {
        audiobook = new Audiobook("title",100.0);
        priceCalc = new AudiobookPriceCalculator();
    }

    @Test
    public void TestSubscriberPrice()
    {
        customer = new Customer("test", Customer.LoyaltyLevel.STANDARD, true);
        assertEquals(0,priceCalc.calculate(customer,audiobook));
    }

    @Test
    public void TestSilverLoyalityPrice()
    {
        customer = new Customer("test", Customer.LoyaltyLevel.SILVER,false);
        assertEquals(0.9*audiobook.getStartingPrice(),priceCalc.calculate(customer,audiobook));
    }

    @Test
    public void TestGoldLoyalityPrice()
    {
        customer = new Customer("test", Customer.LoyaltyLevel.GOLD, false);
        assertEquals(0.8*audiobook.getStartingPrice(),priceCalc.calculate(customer,audiobook));
    }

    @Test
    public void TestGetDefaultPrice()
    {
        customer = new Customer("test", Customer.LoyaltyLevel.STANDARD, false);
        assertEquals(audiobook.getStartingPrice(),priceCalc.calculate(customer,audiobook));
    }
}