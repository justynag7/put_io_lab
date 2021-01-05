package put.io.testing.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;
class FailureOrErrorTest {

    @Test
    void test1(){
    assertEquals(5,2+2);
    }

    @Test
    void test2(){
        String s = null;
        s.trim();
    }

    @Test
    void test3(){
        try{
            assertEquals(5,2+2);
        }
        catch(AssertionFailedError e){
                System.out.println(e.getStackTrace());
        }
    }
}
