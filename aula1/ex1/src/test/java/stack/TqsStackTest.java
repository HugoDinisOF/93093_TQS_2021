package stack;

import org.junit.jupiter.api.DisplayName;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TqsStackTest {
    public TqsStack<String> newStack;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        newStack = new TqsStack<>();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @DisplayName("A stack is empty in construction.")
    @org.junit.jupiter.api.Test
    void isEmpty() {
        assertTrue(newStack.isEmpty());
    }

    @DisplayName("A stack has size 0 on construction")
    @org.junit.jupiter.api.Test
    void size() {
        assertEquals(0,newStack.size());
    }

    @DisplayName("If one pushes x then pops, the value popped is x.")
    @org.junit.jupiter.api.Test
    void pushAndPop() {
        newStack.push("Viseu");
        assertEquals("Viseu",newStack.pop());
    }
    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    @org.junit.jupiter.api.Test
    void peek() {
        newStack.push("Viseu");
        assertEquals("Viseu",newStack.peek());
        assertEquals(1,newStack.size());
    }
    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @org.junit.jupiter.api.Test
    void push() {
        newStack.push("Viseu");
        newStack.push("Aveiro");
        newStack.push("Lisboa");
        assertFalse(newStack.isEmpty());
        assertEquals(3,newStack.size());
    }
    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    @org.junit.jupiter.api.Test
    void pop() {
        newStack.push("Viseu");
        newStack.push("Aveiro");
        newStack.push("Lisboa");
        newStack.pop();
        newStack.pop();
        newStack.pop();
        assertTrue(newStack.isEmpty());
        assertEquals(0,newStack.size());
    }
    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    @org.junit.jupiter.api.Test
    void popEmpty() {
        assertThrows(NoSuchElementException.class, newStack::pop);
    }
    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    @org.junit.jupiter.api.Test
    void peekEmpty() {
        assertThrows(NoSuchElementException.class, newStack::peek);
    }
    @DisplayName("For bounded stacks only, pushing onto a full stack does throw an IllegalStateException")
    @org.junit.jupiter.api.Test
    void pushFullStack() {
        TqsStack<String> stack = new TqsStack<>(3);
        stack.push("Viseu");
        stack.push("Aveiro");
        stack.push("Lisboa");
        assertThrows(IllegalStateException.class, () ->{
            stack.push("Porto");
        });
    }

}