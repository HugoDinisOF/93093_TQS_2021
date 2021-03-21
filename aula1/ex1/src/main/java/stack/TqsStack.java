package stack;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> {
    private LinkedList<T> stack;
    private int maxSize=0;
    public TqsStack(){
        stack = new LinkedList<>();
    }
    public TqsStack(int i){
        stack = new LinkedList<>();
        maxSize=i;
    }

    public boolean isEmpty(){
        return size() == 0;
    }
    public int size(){
        return stack.size();
    }
    public T pop() throws NoSuchElementException {
        if (isEmpty()){
            throw(new NoSuchElementException());
        }
        return stack.pop();
    }
    public void push(T x) throws IllegalStateException{
        if(maxSize != 0 && size() >= maxSize){
            throw(new IllegalStateException());
        }
        stack.push(x);
    }
    public T peek() throws NoSuchElementException{
        if (isEmpty()){
            throw(new NoSuchElementException());
        }
        return stack.peek();
    }
}
