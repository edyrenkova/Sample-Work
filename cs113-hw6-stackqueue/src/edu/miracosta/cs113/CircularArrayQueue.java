package edu.miracosta.cs113;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
/**
 * CircularArrayQueue : a queue data structure built using circular array
 */
public class CircularArrayQueue <E> implements Queue<E> {
    private E[] data;
    private int capacity;
    private int size;
    private int rear, front;


    private class QueueIterator implements Iterator<E>{

        private int index;
        private int count=0;
        public QueueIterator(){
            index=front;
        }
        @Override
        public boolean hasNext() {
            return count<size;
        }

        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            E returnValue=data[index];
            index=(index+1)%capacity;
            count++;
            return returnValue;
        }
        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Creates an instance of CircularArrayQueue of given initial capacity
     * @param initialCapacity integer that represents initial capacity of a queue
     */
    public CircularArrayQueue(int initialCapacity){
        capacity=initialCapacity;
        data=(E[])new Object[initialCapacity];
        front=0;
        rear=capacity-1;
        size=0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        if(size==0)return true;
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
        for (E item:this) {
            if(item.equals(o)) return true;
        }
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(E e) {
        if(size==capacity){
            throw new IllegalStateException();
        }
        size++;
        rear=(rear+1)%capacity;
        data[rear]=e;
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        data=(E[])new Object[1];
        front=0;
        rear=capacity-1;
        size=0;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean offer(E e) {
        if(size==capacity){
            reallocate();
        }
        size++;
        rear=(rear+1)%capacity;
        data[rear]=e;
        return true;
    }

    private void reallocate(){
        int newCapacity=2*capacity;
        E[] newData=(E[])new Object[newCapacity];
        int j=front;
        for(int i=0;i<size;i++)
        {
            newData[i]=data[j];
            j=(j+1)%capacity;
        }
        front=0;
        rear=size-1;
        capacity=newCapacity;
        data=newData;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public E remove() {
        if(isEmpty()) throw new NoSuchElementException();
        return poll();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public E poll() {
        if(size==0) return null;
        E result=data[front];
        front=(front+1)%capacity;
        size--;
        return result;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public E element() {
        if(isEmpty()) throw new NoSuchElementException();
        return peek();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public E peek() {
        if(size==0) return null;
        return data[front];
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String result="[";
        for (E item:this) {
            result+=item.toString()+", ";
        }
        result=result.substring(0,result.length()-2);
        result+="]";
        return result;
    }
}
