package src.main.java.queue;

import src.main.java.stack.ImmutableStack;
import src.main.java.stack.Stack;

import java.util.EmptyStackException;

/**
 * We use 2 stack to maintain the queue, frontStack and backStack.
 * whenever we do enqueue or deque, we modify the stack by pushing to the back stack and popping from the front stack respectively.
 * @param <T>
 */
public class ImmutableQueue<T> implements Queue<T> {

    final Stack<T> front;    // represent in the same order as queue, i.e top element of stack = first element of queue
    final Stack<T> back;    // represent in the reverse order of queue, i.e. the bottom element = first element of queue.

    public ImmutableQueue() {
        this.front = new ImmutableStack();
        this.back = new ImmutableStack();
    }

    public ImmutableQueue(Stack front, Stack back) {
        this.front = front;
        this.back = back;
    }

    /**
         * insert item into the queue, return a new queue after enqueue
         * We want the head element of the queue be retrieve with O(1) time, so we try to maintain the head element at the top of the front stack
         * if there is already an element in the front (front.isEmpty == false) , we can directly push to the back stack.
         * time complexity : O(1), O(n) for the worst case when the front is empty
         * @param item
         * @return Queue<T>
         */
    @Override
    public Queue<T> enQueue(T item) {
        if (this.front.isEmpty())
            return new ImmutableQueue<T>(this.back.reverse().push(item), new ImmutableStack());
        return new ImmutableQueue<T>(this.front, this.back.push(item));
    }

    /**
         * Get the first element of the queue, return a new queue after deque
         * If the front stack is empty, we will need to get the bottom element from the back stack by calling stack.reverse, which takes O(n) time.
         * Otherwise, we just need to simply get the top element in the front stack. If popping made the front stack empty, we reverse to back and set as the front,
         * in order to make the next deque faster.
         * time complexity : O(1), O(n) for the worst case if reverse stack is required
         * @return Queue<T>
         * @throws EmptyStackException
         */
    @Override
    public Queue<T> deQueue() throws EmptyStackException {
        if(this.front.isEmpty()) {
            return new ImmutableQueue<T>(this.back.reverse().pop(), new ImmutableStack<>());
        } else {
            Stack resultFront = this.front.pop();
            if (resultFront.isEmpty()) {
                return new ImmutableQueue<T>(this.back.reverse(), new ImmutableStack());
            }
            return new ImmutableQueue<T>(resultFront, this.back);
        }
    }

    /**
         * return the first element in the queue. If queue is empty, return null;
         * @return T
         */
    @Override
    public T head() {
        return this.front.peek();
    }

    /**
         * return true if queue is empty, else return false
         * @return boolean
         */
    @Override
    public boolean isEmpty() {
        return this.front.isEmpty() && this.back.isEmpty();
    }
}
