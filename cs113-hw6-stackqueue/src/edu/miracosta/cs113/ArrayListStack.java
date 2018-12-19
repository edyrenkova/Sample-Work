package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.EmptyStackException;
/**
 * ArrayListStack : a stack data structure built using ArrayList
 */
public class ArrayListStack<E> implements StackInterface<E> {
    private ArrayList<E> stack;
    /**
     * Creates an empty instance of ArrayListStack
     */
    public ArrayListStack()
    {
        stack=new ArrayList<E>();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean empty() {
        if(stack.size()==0)
        {
            return true;
        }
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public E peek() {
        if(empty()) throw new EmptyStackException();
        return stack.get(stack.size()-1);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public E pop() {
        if(empty()) throw new EmptyStackException();
        E removed = stack.remove(stack.size()-1);
        return removed;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public E push(E obj) {
        stack.add(obj);
        return stack.get(stack.size()-1);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(){
        String result="[";
        for (E item:stack) {
            result+=item+", ";
        }
        result=result.substring(0,result.length()-2);
        result+="]";
        return result;
    }
}
