package src.main.java.stack;

import java.util.EmptyStackException;

/**
 * Immutable stack
 * @param <T>
 */
public class ImmutableStack<T> implements Stack<T> {

    final T head;
    final ImmutableStack<T> tail;

    public ImmutableStack() {
        this.head = null;
        this.tail = null;
    }

    private ImmutableStack(T head, ImmutableStack tail) {
        this.head = head;
        this.tail = tail;
    }

    /**
        * return the peek item, return null if its empty
        * time complexity : O(1)
        * @return T
        */
    public T peek() throws EmptyStackException {
        return this.head;
    }

    /**
        * return true is stack is empty, else return false
        * time complexity : O(1)
        * @return boolean
        */
    public boolean isEmpty() {
        return peek() == null && this.tail == null;
    }

    /**
        * push an item to stack and return a new Immutable stack
        * time complexity : O(1)
        * @param item
        * @return ImmutableStack
        */
    public ImmutableStack push(T item) {
        return new ImmutableStack(item, this);
    }

    /**
        * remove the top element from stack and return a new ImmutableStack
        * time complexity : O(1)
        * @return ImmutableStack
        */
    public ImmutableStack pop() throws EmptyStackException {
        if (this.tail == null) throw new EmptyStackException();
        return this.tail;
    }

    /**
        *  create a new immutable stack with reverse order
        *  time complexity : O(n)
        * @return ImmutableStack
        */
    public ImmutableStack reverse() {
        ImmutableStack resultStack = new ImmutableStack();
        ImmutableStack curStack = this;
        while(!curStack.isEmpty()) {
            resultStack = resultStack.push(curStack.peek());
            curStack = curStack.pop();
        }
        return resultStack;
    }
}
