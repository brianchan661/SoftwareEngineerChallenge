package src.test.java.stack;
import org.junit.jupiter.api.Test;
import src.main.java.stack.ImmutableStack;
import src.main.java.stack.Stack;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableStackTest {

    @Test
    public void test_immutableStack_normal_case() {
        Stack<Integer> stack = new ImmutableStack();

        Stack<Integer> stack1 = stack.push(1);
        Stack<Integer> stack2 =  stack1.push(2);

        // assert every stack is new object
        assert stack != stack1;
        assert stack1 != stack2;

        // assert the element is inserted and return as a new immutable stack
        assert stack.isEmpty();
        assert !stack1.isEmpty();
        assert !stack2.isEmpty();

        // assert the element  is insert to the stack correctly
        assert stack1.peek() == 1;
        assert stack2.peek() == 2;

        // assert the element is correctly be pop
        Stack<Integer> stackAfterPop = stack2.pop();
        assert stack2 != stackAfterPop;
        assert stackAfterPop.peek() == 1;

        // pop all element from stack, expect peek is null
        Stack<Integer> stackPopAll = stackAfterPop.pop();
        assert stackPopAll.peek() == null;
    }

    @Test
    public void test_pop_from_empty_stack() {
        ImmutableStack<Integer> stack = new ImmutableStack<>();
        assertThrows(EmptyStackException.class, () -> {
            stack.pop();
        });
    }
}
