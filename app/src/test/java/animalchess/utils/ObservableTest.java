package animalchess.utils;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObservableTest {

    @Test
    void testSubscriberCalls() {
        Observable<Integer> observable = Observable.of(0);
        LinkedList<Integer> results = new LinkedList<>();

        observable.subscribe(results::add);
        IntStream.range(1, 10).forEach((i) -> {
            observable.update(i);
            assertEquals(i, results.size());
            assertEquals(i, results.getLast());
        });
    }

}
