package uk.ac.aber.dcs.nid21.cs21120.assignment1.datastructures;

public class PriorityQueue <E extends Comparable> {

    protected Object[] pq;
    protected int pqSize = 0;       //Size of pq array
    protected int pqTailPointer = 0;

    public PriorityQueue(){ this(16); }

    public PriorityQueue(int size){
        pq = new Object[size];
        pqSize = size;
    }

    public PriorityQueue(E[] objects){
        pqSize = objects.length;
        pq = new Object[pqSize];
        for(E object : objects){
            if(object != null) {
                pq[pqTailPointer++] = object;
            }
        }
        bubbleUp();
    }

    protected void bubbleUp(){
        Comparable child;
        Comparable parent;
        int parentIndex;
        for(int i = pqTailPointer - 1; i >= 0; i--){
            child = (Comparable) pq[i];          //Get the child element
            parentIndex = parent(i);
            parent = (Comparable) pq[parentIndex]; //Get this child's parent element
            if(parent != null && child != null){
                if (child.compareTo(parent) > 0) {
                    swap(i, parentIndex);
                }
            } else { System.out.println("Parent " + parent + " or child " + child + " was null"); }
        }
    }

    public void swap(int i, int j) {
        Object copy = pq[i];
        pq[i] = pq[j];
        pq[j] = copy;
    }

    public void push(E e){
        if(pqTailPointer > pqSize){
            //Grow the pq
            Object[] tempPq = new Object[pqSize * 2];
            for(int i = 0; i < pqSize; i++){
                tempPq[i] = pq[i];
            }
            pq = tempPq;
            pqSize *= 2;
        }
        pq[pqTailPointer++] = e;
        this.bubbleUp();
    }

    public E pop(){
        E headElement = (E)pq[0];
        pq[0] = pq[--pqTailPointer];
        pq[pqTailPointer] = null;
        bubbleUp();
        return headElement;
    }

    protected int parent(int n){
        return (n - 1) / 2;
    }

    protected int[] children(int n){
        return new int[] {n * 2 + 1, n * 2 + 2};
    }

    public int sibling(int index){
        int[] siblings = children(sibling(index));
        if(siblings[0] == index) return siblings[1];
        else return siblings[0];
    }

    public int itemCount() {
        return pqTailPointer - 1;
    }
}
