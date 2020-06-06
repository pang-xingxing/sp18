/** A Deque is an irregular acronym of double-ended queue.*/
public class LinkedListDeque<T> {
    private class StuffNode{
        public T item;
        public StuffNode last;
        public StuffNode next;

        public StuffNode(T i, StuffNode l, StuffNode n){
            item = i;
            last = l;
            next = n;
        }
    }

    private StuffNode sentinel;
    private int size;

    /** Creates an empty Deque. */
    public LinkedListDeque(){
        sentinel = new StuffNode(null, null, null);
        sentinel.last = sentinel;
        sentinel.last = sentinel;
        size = 0;
    }


    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T x) {
        sentinel.next = new StuffNode(x, sentinel, sentinel.next);
        sentinel.next.next.last = sentinel.next;
        size = size + 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T x) {
        sentinel.last = new StuffNode(x, sentinel.last, sentinel);
        sentinel.last.last.next = sentinel.last;
        size = size + 1;
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
        StuffNode p = sentinel;
        while (p.next != null){
            p = p.next;
            System.out.print(p.item + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst(){
        StuffNode first = sentinel.next;
        if (isEmpty()){
            return null;
        }else{
            sentinel.next.next.last = sentinel;
            sentinel.next = first.next;
            size -= 1;
            return first.item;
        }
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast(){
        StuffNode lst = sentinel.last;
        if (isEmpty()){
            return null;
        }else{
            sentinel.last.last.next = sentinel;
            sentinel.last = lst.last;
            size -= 1;
            return lst.item;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque! */
    public T get(int index){
        if(index >= size){
            return null;
        }
        StuffNode p = sentinel;
        for (int i = 0; i <= index; i++){
            p = p.next;
        }
        return p.item;
    }

    private T getRecursive(int index, StuffNode curr){
        if (index == 0){
            return curr.item;
        }else{
            return getRecursive(index - 1, curr.next);
        }
    }
    public T getRecursive(int index){
        return getRecursive(index, sentinel.next);
    }
}
