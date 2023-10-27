package animalchess.utils;

import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Consumer;

public class Observable<V> {
    
    private V value;
    private final LinkedList<Subscriber<V>> subscribers = new LinkedList<>();

    public Observable(V value) {
        this.value = value;
    }

    public Disposable subscribe(Consumer<V> consumer) {
        SubscriberImpl<V> subscriber = new SubscriberImpl<>(consumer);
        subscribers.add(subscriber);
        return subscriber;
    }

    public void update(V value) {
        Objects.requireNonNull(value);
        this.value = value;
        notifySubscribers();
    }

    private void notifySubscribers() {
        subscribers.removeIf(Disposable::isDisposed);
        subscribers.forEach(subscriber -> subscriber.subscribe(value));
    }

    public static <V> Observable<V> of(V value) {
        return new Observable<>(value);
    }
    
}
