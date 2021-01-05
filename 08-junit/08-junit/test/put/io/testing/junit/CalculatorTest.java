package put.io.testing.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calc = new Calculator();
    private Calculator calculator;

    @BeforeEach
    public void setUp(){
    calculator = new Calculator();
}

    @Test
    public void TestAdd()
    {
        assertEquals(-12,calculator.add(-5,-7));
        assertEquals(1000,calculator.add(100,900));
    }

    @Test
    public void TestMultiply()
    {
        assertEquals(1000,calculator.multiply(100,10));
        assertEquals(-12,calculator.multiply(-3,4));
    }

    @Test
    public void TestAddPositiveNumbers()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            assertEquals(900,calc.addPositiveNumbers(-100,1000));
        });
    }
}