package edu.miracosta.cs113;

import java.util.*;

public class DoubleLinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;
    //inner classes
    private static class Node<E>
    {
        private Node prev=null;
        private Node next=null;
        private E data;

        private Node(E dataItem)
        {
            data=dataItem;
        }
    }
    private class MyListIterator implements ListIterator<E>
    {
        private Node<E> nextItem;
        private Node<E> lastItemReturned;
        private int index;

        public MyListIterator(int i)
        {
            if(i<0 || i>size) {
                throw new IndexOutOfBoundsException("Invalid index " + i);
            }
            lastItemReturned=null;
            if(i==size){
                index=size;
                nextItem=null;
            }
            else{
                nextItem=head;
                for (index=0;index<i;index++){
                    nextItem=nextItem.next;
                }
            }

        }

        @Override
        public boolean hasNext() {
            return nextItem!=null;
        }

        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            lastItemReturned=nextItem;
            nextItem=nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        @Override
        public boolean hasPrevious() {
            if(size==0)
            {
                return false;
            }
            return (nextItem==null&&size!=0)||nextItem.prev!=null;
        }

        @Override
        public E previous() {
            if(!hasPrevious())
            {
                throw new NoSuchElementException();
            }
            if(nextItem==null){
                nextItem=tail;
            }
            else{
                nextItem=nextItem.prev;
            }
            lastItemReturned=nextItem;
            index--;
            return lastItemReturned.data;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index-1;
        }

        @Override
        public void remove() {
            if(lastItemReturned!=null)
            {
                if(lastItemReturned.next==null)
                {
                    tail=lastItemReturned.prev;
                    lastItemReturned.prev.next=null;
                }
                else if(lastItemReturned.prev==null){
                    head=lastItemReturned.next;
                    lastItemReturned.next.prev=null;

                }
                else
                {
                    lastItemReturned.prev.next=lastItemReturned.next;
                    lastItemReturned.next.prev=lastItemReturned.prev;
                }

                lastItemReturned=null;
                size--;
            }
            else throw new IllegalStateException();

        }

        @Override
        public void set(E o) {
            if(lastItemReturned!=null)
            {
                lastItemReturned.data=o;
            }
            else throw new IllegalStateException();

        }

        @Override
        public void add(E o) {
            if(head==null)
            {
                head=new Node<E>(o);
                tail=head;
            }
            else if(nextItem==head)
            {
                Node<E> newNode=new Node<E>(o);
                newNode.next=nextItem;
                nextItem.prev=newNode;
                head=newNode;
            }
            else if(nextItem==null)
            {
                Node<E> newNode=new Node<E>(o);
                tail.next=newNode;
                newNode.prev=tail;
                tail=newNode;
            }
            else{
                Node<E> newNode=new Node<E>(o);
                newNode.prev=nextItem.prev;
                nextItem.prev.next=newNode;
                newNode.next=nextItem;
                nextItem.prev=newNode;
            }
            size++;
            index++;
            lastItemReturned=null;

        }
    }

    // List methods
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size==0)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        if(indexOf(o) == -1)
        {
            return false;
        }
        return true;
    }


    @Override
    public Iterator<E> iterator() {
        return new MyListIterator(0);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        listIterator(size).add(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(indexOf(o)==-1)
        {
            return false;
        }
        remove(indexOf(o));
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head=null;
        tail=null;
        size=0;
    }

    @Override
    public E get(int index) {
        if(index>=size || index<0)
        {
            throw new IndexOutOfBoundsException();
        }
        MyListIterator iter=listIterator(index);
        if(iter.hasNext()) {
            return listIterator(index).next();
        }
        else
        {
            return null;
        }
    }

    @Override
    public E set(int index, E element) {
        if(index==size)
        {
            throw new IndexOutOfBoundsException();
        }
        MyListIterator iter=listIterator(index);
        E changedItem=iter.next();
        iter.set(element);
        return changedItem;
    }

    @Override
    public void add(int index, E element) {
        listIterator(index).add(element);

    }

    @Override
    public E remove(int index) {
        if(index==size)
        {
            throw new IndexOutOfBoundsException();
        }
        MyListIterator iter=listIterator(index);
        if(iter.hasNext())
        {
            E dataRemoved = iter.next();
            iter.remove();
            return dataRemoved;
        }
        else return null;
    }

    @Override
    public int indexOf(Object o) {
        MyListIterator iter=listIterator(0);
        while(iter.hasNext())
        {
            if(iter.next().equals(o))
            {
                return iter.previousIndex();
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        MyListIterator iter=listIterator(size);
        while(iter.hasPrevious())
        {
            if(iter.previous().equals(o))
            {
                return iter.nextIndex();
            }
        }
        return -1;
    }

    @Override
    public MyListIterator listIterator() {
        return new MyListIterator(0);
    }

    @Override
    public MyListIterator listIterator(int index) {
        return new MyListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString()
    {
        String result="[";
        for(E obj:this)
        {
             result+=obj+", ";
        }
        if(size!=0)
        {
            result=result.substring(0,result.length()-2);
        }
        result+="]";
        return result;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o instanceof List)
        {
            if(((List)o).size()==size)
            {
                for(int i=0;i<size;i++)
                {
                    if(!get(i).equals(((List)o).get(i)))
                    {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }
}
