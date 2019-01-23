package ru.unn.agile.ModifiedStack.Model;

import java.util.*; 

public class ModifiedStack { 
    private Stack<Integer> StackElems; 
    private Integer MinElem; 
  
    public ModifiedStack() {
		StackElems = new Stack<Integer>(); 
	}

    Integer getMin() { 
        if (StackElems.isEmpty()) 
			System.out.println("Stack is empty");
        else
			return MinElem; 
    } 

    void peek() { 
        if (StackElems.isEmpty()) 
        { 
			System.out.println("Stack is empty "); 
			return; 
        } 
  
		Integer top = StackElems.peek();

        if (top < MinElem)
			return MinElem;
        else
			return top; 
    } 
  
    Integer pop() { 
        if (StackElems.isEmpty()) 
        { 
            System.out.println("Stack is empty"); 
            return; 
        } 
  
        Integer top = StackElems.pop(); 

        if (top < MinElem) 
        { 
            MinElem = 2*MinElem - top;
			return	MinElem;		
        } 
        else
			return	t;
    } 
  
    void push(Integer x) { 
        if (StackElems.isEmpty()) 
        { 
            MinElem = x; 
            StackElems.push(x); 
            return; 
        }
  
        if (x < MinElem) 
        { 
            StackElems.push(2*x - MinElem); 
            MinElem = x; 
        }
        else
            StackElems.push(x);

    } 
}; 