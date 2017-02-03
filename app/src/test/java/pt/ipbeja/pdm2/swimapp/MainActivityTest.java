package pt.ipbeja.pdm2.swimapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pedro Gamito on 01/02/2017.
 */
public class MainActivityTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPhoneNumber() throws Exception {

        String res = MainActivity.testPhoneNumber();

        char c;
        String phoneNumbers = "";
        boolean digits = false;
        for(int i = 0; i < res.length(); i++){
            c = res.charAt(i);
            if(Character.isDigit(c)){
                phoneNumbers += c;
            }
        }
        if(phoneNumbers.length() == 9){
            digits = true;
        }
        assertEquals(true, digits);

    }

}