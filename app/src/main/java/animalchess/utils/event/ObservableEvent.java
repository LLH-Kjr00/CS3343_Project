package animalchess.utils.event;

import animalchess.utils.common.Pair;
import animalchess.utils.flow.Disposable;
import animalchess.utils.flow.Observable;

import java.util.TreeSet;
import java.util.function.Consumer;

public class ObservableEvent<T extends Event> implements Observable<Pair<EventPriority, Consumer<T>>, T> {

    private final TreeSet<EventConsumer<T>> consumers = new TreeSet<>();

    @Override
    public Disposable subscribe(Pair<EventPriority, Consumer<T>> pair) {
        EventConsumer<T> consumer = new EventConsumer<>(this, pair.getSecond(), pair.getFirst());
        consumers.add(consumer);
        return consumer;
    }

    @Override
    public void update(T value) {
        consumers.forEach((consumer) -> consumer.subscribe(value));
    }

    protected void remove(EventConsumer<T> value) {
        consumers.remove(value);
    }

}
