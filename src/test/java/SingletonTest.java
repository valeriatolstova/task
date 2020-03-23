import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class SingletonTest {
    private static final int numberOfTreads = 100;

    @Test
    public void getSingleton() {
        ConcurrentSkipListSet<Integer> listSet = new ConcurrentSkipListSet<>();
        CountDownLatch startCountDownLatch = new CountDownLatch(numberOfTreads);
        CountDownLatch endCountDownLatch = new CountDownLatch(numberOfTreads);
        Executor executor = Executors.newFixedThreadPool(numberOfTreads);
        for (int i = 0; i < numberOfTreads; i++) {
            executor.execute(() -> {
                startCountDownLatch.countDown();
                try {
                    startCountDownLatch.await();
                } catch (InterruptedException e) {
                    fail();
                }
                Singleton singleton = Singleton.getSingleton();
                listSet.add(singleton.getId());
                endCountDownLatch.countDown();
            });
        }
        try {
            endCountDownLatch.await();
        } catch (InterruptedException e) {
            fail();
        }
        assertEquals(1, listSet.size());
        //assertEquals((long) 0, (long) listSet.first());
    }
}