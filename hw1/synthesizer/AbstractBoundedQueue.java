package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public int fillCount() {
        return this.fillCount;
    }

    /* public boolean isEmpty() {
        return fillCount == 0;
    }
    public boolean isFull() {
        return fillCount == capacity;
    }

    public abstract T peek();
    public abstract T dequeue();
    public abstract void enqueue(T x);
    */

}