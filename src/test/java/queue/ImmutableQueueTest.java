package src.test.java.queue;

import org.junit.jupiter.api.Test;
import src.main.java.queue.ImmutableQueue;
import src.main.java.queue.Queue;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableQueueTest {

    @Test
    public void test_immutableQueu_normal_case() {
        Queue<Integer> queue = new ImmutableQueue<>();

        // test enQueue
        Queue<Integer> queue1 = queue.enQueue(1);
        Queue<Integer> queue2 = queue1.enQueue(2);
        assert queue != queue1;
        assert queue1 != queue2;
        assert queue.isEmpty();
        assert !queue1.isEmpty();
        assert !queue2.isEmpty();
        assert queue.head() == null;
        assert queue1.head() == 1;
        assert queue2.head() == 1;

        // test deque
        Queue<Integer> afterDeque = queue2.deQueue();
        assert queue2 != afterDeque;
        assert afterDeque.head() == 2;

        // remove all elements from queue
        Queue<Integer> queueDequedAll = afterDeque.deQueue();
        assert queueDequedAll.isEmpty();
        assert queueDequedAll.head() == null;
    }

    @Test
    public void test_queue_remove_and_add_again() {
        Queue<Integer> queue = new ImmutableQueue<>();
        Queue<Integer> queue1 = queue.enQueue(1).enQueue(2);
        assert !queue1.isEmpty();
        assert queue1.head() == 1;

        Queue<Integer> queue2 = queue1.deQueue().deQueue();
        assert queue2.isEmpty();
        assert queue2.head() == null;

        Queue<Integer> queue3 = queue2.enQueue(3).enQueue(4);
        assert !queue3.isEmpty();
        assert queue3.head() == 3;
    }

    @Test
    public void test_deque_from_empty_queue() {
        Queue<Integer> queue = new ImmutableQueue<>();
        assertThrows(EmptyStackException.class, () -> {
            queue.deQueue();
        });
    }
}
