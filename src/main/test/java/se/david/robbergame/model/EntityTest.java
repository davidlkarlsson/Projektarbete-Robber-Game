package se.david.robbergame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {


    @Test
     void testTakeHit() {

        Resident resident = new Resident(100,5,"David");
        Burglar burglar = new Burglar(100,15,"Burglar");

        burglar.takeHit(20);
        assertEquals(80, burglar.getHealth());
    }

    @Test
    void testPunch() {

        Resident resident = new Resident(100,5,"David");
        Burglar burglar = new Burglar(100,15,"Burglar");

        resident.punch(burglar);
        assertEquals(95, burglar.getHealth());
    }

    @Test
    void testIsConscious () {

        Resident resident = new Resident(100,5,"David");
        Burglar burglar = new Burglar(100,15,"Burglar");

        assertTrue(resident.isConscious());
    }

    @Test
    void testIsNotConscious () {

        Resident resident = new Resident(100,5,"David");
        Burglar burglar = new Burglar(100,15,"Burglar");

        resident.takeHit(100);
        assertFalse(resident.isConscious());
    }
}