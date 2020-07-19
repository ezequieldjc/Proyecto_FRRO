package UTest;

import Data.DataConnectioniMac;
import Entities.Persona.PersonaEmpleado;
import junit.framework.TestCase;

public class TestExample extends TestCase {

    private PersonaEmpleado p;

    public void escenario(){
        p = new PersonaEmpleado();
    }

    public void test(){
        assertEquals(4,4);
    }

    public void test2(){
        //assertEquals(4,5);
        assertEquals(3,3);
    }
}
