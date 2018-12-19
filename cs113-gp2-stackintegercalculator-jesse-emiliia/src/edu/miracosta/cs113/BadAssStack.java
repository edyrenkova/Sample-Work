package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class BadAssStack<E>{

        private ArrayList<E> theData;

        public BadAssStack() {
            theData = new ArrayList<E>();
        }

        public boolean empty() {
            return theData.isEmpty();
        }

        public E peek() {
            if(empty()) {
                throw new EmptyStackException();
            }
            return theData.get(theData.size() - 1);
        }

        public E pop() {
            if(empty()) {
                throw new EmptyStackException();
            }
            return theData.remove(theData.size() - 1);
        }

        public E push(E obj) {
            theData.add(obj);
            return obj;
        }

        public String toString() {
            return " " + theData;
        }
}
