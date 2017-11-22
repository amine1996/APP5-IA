/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package problemes.tortues;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author pc
 */
public class EtatTortueTest {

    EtatTortue et0;
    EtatTortue et02;
    EtatTortue et1;
    EtatTortue et2;
    EtatTortue et3;
    EtatTortue et4;
    EtatTortue et5;
    EtatTortue et6;

    @Before
    public void setUp() {
        boolean[] tab1 = {true, true, false, false, false, false};      //110000
        boolean[] tab2 = {true, false, false, true, false, false};      //100100
        boolean[] tab3 = {false, false, false, false, false, false};    //000000
        boolean[] tab4 = {true, false, true, false, false, false};      //101000
        boolean[] tab5 = {false, false, true, true, true, false};       //001110
        boolean[] tab6 = {true, false, true, false, true, true};      //000011

        et0 = new EtatTortue();                                         // 011000
        et02 = new EtatTortue();                                        // 011000
        et1 = new EtatTortue(tab1);
        et2 = new EtatTortue(tab2);
        et3 = new EtatTortue(tab3);
        et4 = new EtatTortue(tab4);
        et5 = new EtatTortue(tab5);
        et6 = new EtatTortue(tab6);
    }

    // Test methods must be annotated with annotation @Test. For example:
    @Test
    public void TestEgal() {
        assertEquals(et0, et02);
    }

    @Test
    public void TestInverse1() {
        et3.inverseTortue(0);
        assertEquals(et1, et3);
    }

    @Test
    public void TestInverse2() {
        et1.inverseTortue(1);
        assertEquals(et4, et1);
    }

    @Test
    public void TestInverse3() {
        et2.inverseTortue(2);
        assertEquals(et4, et2);
    }

    @Test
    public void TestInverse4() {
        et4.inverseTortue(4);
        assertEquals(et6, et4);
    }

    @Test (expected= RuntimeException.class)
    public void TestInverse5() {
        et1.inverseTortue(5);
    }
    
    
}