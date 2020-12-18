package cn.com.ljw;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple WebApplication.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        String str1 = "12";
        final String str2 = "1";
        String str3 = "1";
        boolean a = (str1 == (str2 + "2"));
        boolean b = (str1 == (str3 + "2"));
        System.out.println(a);
        System.out.println(b);
    }
}
