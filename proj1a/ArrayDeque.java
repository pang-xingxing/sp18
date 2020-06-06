/** A Deque is an irregular acronym of double-ended queue.*/
public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextfirst;
    private int nextlast;

    /** Creates an empty Deque. */
    public ArrayDeque(){
        items = (T[]) new Object[8];
        nextfirst = 0;
        nextlast = 1;
        size = 0;
    }

    private boolean isFull(){
        return size == items.length;
    }

    private boolean isSparse(){
        return items.length >= 16 && size/items.length <= 0.25;
    }

    private int plusOne(int index){
        return (index + 1) % items.length;
    }

    private int minusOne(int index){
        return (index - 1 + items.length)/items.length;
    }

    public void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        int oldindex = plusOne(nextfirst);
        for (int newindex = 0; newindex < size; newindex++){
            a[newindex] = items[oldindex];
            oldindex = plusOne(oldindex);
        }
        items = a;
        nextfirst = capacity - 1;
        nextlast = size;
    }

    private void upSize(){
        resize(2 * size);
    }

    private void downSize(){
        resize(items.length / 2);
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T x) {
        if (isFull()){
            upSize();
        }
        items[nextfirst] = x;
        nextfirst = minusOne(nextfirst);
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T x) {
        if (isFull()){
            upSize();
        }
        items[nextlast] = x;
        nextfirst = plusOne(nextfirst);
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque(){
        for (int i = plusOne(nextfirst); i != nextlast; i = plusOne(i)){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst(){
        if (isSparse()){
            downSize();
        }
        nextfirst = plusOne(nextfirst);
        T temp = items[nextfirst];
        items[nextfirst] = null;
        if (!isEmpty()){
            size -= 1;
        }
        return temp;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast(){
        if (isSparse()){
            downSize();
        }
        nextlast = minusOne(nextlast);
        T temp = items[nextlast];
        items[nextlast] = null;
        if (!isEmpty()){
            size -= 1;
        }
        return temp;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index){
        if(index >= size){
            return null;
        }
        int start = plusOne(nextfirst);
        return items[(start + index) / items.length];
    }
}
