package uk.ac.aber.dcs.nid21.cs21120.assignment1.datastructures;

import java.util.List;


public class Stack<E extends Object>  {

    private Object[] stackArray;
    private int pointer = -1;
    private int size;

    public Stack(List<E> initialElements){
        this((E[]) initialElements.toArray());
    }

    public Stack(E[] initialElements){
        stackArray = initialElements;
        pointer = initialElements.length - 1;
    }

    public Stack(int size){
        this.size = size;
        stackArray = new Object[this.size];
    }

    public Stack() { this(16); }

    public void push(E element) {
        if ((pointer + 1) >= size) {
            //Grow the currentBracket
            // http://blog.mozilla.org/nnethercote/2014/11/04/please-grow-your-buffers-exponentially/
            size *= 2;
            Object[] tempStackArray = new Object[size];
            for(int i = 0; i < stackArray.length; i++){
                tempStackArray[i] = stackArray[i];
            }
            stackArray = tempStackArray;
        }
        stackArray[++pointer] = element;
    }

    public E pop() {
        if(pointer == -1 || stackArray[pointer] == null)
            return null;
        return (E)stackArray[pointer--];
    }

    public boolean isEmpty(){
        return pointer < 0;
    }

    public E peak(){
        return (E)stackArray[pointer];
    }

    public int size() {
        return size;
    }

    public int itemCount(){
        return pointer + 1;
    }
}
