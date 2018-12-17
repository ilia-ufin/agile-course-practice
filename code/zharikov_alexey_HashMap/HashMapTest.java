package ru.unn.agile.HashMap.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.*;

public class HashMapTest {
    private HashMap map; 
    @Before
    public void setUp() {
        this.map = new HashMap();
    }

    @Test(expected= NoSuchElementException.class)
    public void testThrowsExceptionIfKeyDoesNotExist(){
        map.get("LOL");
    }

    @Test
    public void testContainsKeyForExistingKey(){
        map.add("LOL", 5);
        assertTrue(map.containsKey("LOL"));
    }

    @Test
    public void testRemoveDecrementsSize(){
        map.add("LOL", 5);
        map.add("KEK", 6);
        map.remove("LOL");
        
        assertEquals(1, map.size());
        
        map.remove("KEK");
        
        assertEquals(0, map.size());
    }

    @Test
    public void testAddMakesIsEmptyFalse(){
        map.add("LOL", 5);
        
        assertFalse(map.isEmpty());
    }

    @Test
    public void testSizeForNewMap(){
        assertEquals(0, map.size());
    }

    @Test
    public void testSizeIncrementsWhenAddingElements(){
        map.add("LOL", 5);
        assertEquals(1, map.size());
        
        map.add("KEK", 5);
        assertEquals(2, map.size());
    }

    @Test
    public void testGetReturnsCorrectValue(){
        map.add("LOL", 5);
        map.add("KEK", 6);
        
        assertEquals(5, map.get("LOL"));
        assertEquals(6, map.get("KEK"));
    }

    @Test
    public void testReplacesValueWithSameKey(){
        map.add("LOL", 5);
        map.add("LOL", 6);
        
        assertEquals(6, map.get("LOL"));
    }

    @Test
    public void testDoesNotOverwriteSeperateKeysWithSameHash(){
        map.add("Ea", 5);
        map.add("FB", 6);
        
        assertEquals(5, map.get("Ea"));
        assertEquals(6, map.get("FB"));
    }

    @Test
    public void testRemoveDoesNotEffectNewMap(){
        map.remove("LOL");

        assertEquals(0, map.size());
    }

    @Test(expected= NoSuchElementException.class)
    public void testRemoveDeletesElement(){
        map.add("LOL", 5);
        map.remove("LOL");
        
        map.get("LOL");
    }

    @Test
    public void testContainsKeyForKeyWithEquivalentHash(){
        map.add("Ea", 5);
        
        assertFalse(map.containsKey("FB"));
    }

    @Test
    public void testContainsKeyForNewMap(){
        assertFalse(map.containsKey("LOL"));
    }

    @Test
    public void testContainsKeyForNonExistingKey(){
        map.add("LOL", 5);
        
        assertFalse(map.containsKey("KEK"));
    }

    @Test
    public void testIsEmptyForNewMap(){
        assertTrue(map.isEmpty());
    }

    @Test
    public void testCollisionResolution(){
        map.add("LOL", 5);
        map.add("LOL", 5);
        map.add("LOL", 5);
        map.add("LOL", 5);
        
        assertFalse(sizearray("LOL") == 4);
    } 
    
    
};
