package uk.ac.aber.dcs.nid21.cs21120.assignment1.datastructures;

import java.util.List;

public class Queue<E extends Object> {

    private Object[] queue;
    private int headPointer = 0;    //The Last item in the queue (ADD +1) BACK
    private int tailPointer = 0;    //The First item in the queue (REMOVE +0) FRONT
    private int allocatedSize = 0;  //Allocated array allocatedSize
    private int itemCount = 0;      //Items in the queue

    public Queue(E[] queue){
        this.queue = queue;
        this.allocatedSize = queue.length;
        headPointer = this.allocatedSize - 1;
        itemCount = queue.length;
    }

    public Queue(List<E> queue){
        this((E[])queue.toArray());
    }

    public Queue(int allocatedSize){
        queue = new Object[allocatedSize];
        this.allocatedSize = allocatedSize;
        this.headPointer = -1;
    }

    public Queue(){
        this(16);
    }

    public void push(E object){
        if(itemCount >= allocatedSize) {
            //Grow the queue
            // http://blog.mozilla.org/nnethercote/2014/11/04/please-grow-your-buffers-exponentially/
            Object[] temperyQueue = new Object[allocatedSize * 2];
            int tmpQPtr = 0;
            while(!this.isEmpty()){
                temperyQueue[tmpQPtr++] = this.pop();
            }
            queue = temperyQueue;
            tailPointer = 0;
            headPointer = tmpQPtr;
            allocatedSize *=2;
            itemCount = tmpQPtr;
        }
        headPointer = (headPointer + 1) % allocatedSize;
        queue[headPointer] = object;
        ++itemCount;
    }

    public E pop(){
        if(itemCount <= 0) {
            headPointer = 0;
            tailPointer = 0;
            return null;
        }
        E object = (E)queue[tailPointer];
        tailPointer = (tailPointer + 1) % allocatedSize;
        --itemCount;
        return object;
    }

    public boolean isEmpty(){
        return itemCount <= 0;
    }

    public int allocatedSize(){
        return allocatedSize;
    }

    public int itemCount() { return itemCount; }
}
