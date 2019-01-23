package ru.unn.agile.ModifiedStack.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.*;

public class ModifiedStackTest {
    private ModifiedStack Stack; 
    
    @Before
    public void setUp() {
        this.Stack = new ModifiedStack();
    }
    
    @Test
    public void testStackTopPeek(){
        Stack.push(3);
        Stack.push(5);
        assertEquals(5, Stack.peek());
    }

    @Test
    public void testStackTopPeek(){
        Stack.push(3);
        Stack.push(5);
        assertEquals(5, Stack.peek());
    }

    @Test
    public void testStackPop(){
        Stack.push(1);
        Stack.push(2);
        assertEquals(2, Stack.pop());
        assertEquals(1, Stack.pop());
    }

    @Test    
    public void testStackSearchMin(){
        Stack.push(1);
        Stack.push(2);
        assertEquals(1, Stack.getMin());
    }
        
};