package animalchess.utils.flow;

import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Consumer;

public class ObservableImpl<V> implements Observable<Consumer<V>, V> {
    
    private V value;
    private final LinkedList<Subscriber<V>> subscribers = new LinkedList<>();

    public ObservableImpl(V value) {
        this.value = value;
    }

    @Override
    public Disposable subscribe(Consumer<V> consumer) {
        SubscriberImpl<V> subscriber = new SubscriberImpl<>(consumer);
        subscribers.add(subscriber);
        return subscriber;
    }

    @Override
    public void update(V value) {
        Objects.requireNonNull(value);
        this.value = value;
        notifySubscribers();
    }

    private void notifySubscribers() {
        subscribers.removeIf(Disposable::isDisposed);
        subscribers.forEach(subscriber -> subscriber.subscribe(value));
    }

    public static <V> Observable<Consumer<V>, V> of(V value) {
        return new ObservableImpl<>(value);
    }
    
}
