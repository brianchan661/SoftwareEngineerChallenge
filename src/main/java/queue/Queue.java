package src.main.java.queue;

import java.util.EmptyStackException;

public interface Queue<T> {
    public Queue<T> enQueue(T t);
    public Queue<T> deQueue() throws EmptyStackException;
    public T head();
    public boolean isEmpty();
}
