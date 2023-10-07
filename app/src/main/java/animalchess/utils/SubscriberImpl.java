package animalchess.utils;

import java.util.function.Consumer;

public class SubscriberImpl<V> implements Subscriber<V> {

    private boolean disposed;

    private final Consumer<V> consumer;

    protected SubscriberImpl(Consumer<V> consumer) {
        this.consumer = consumer;
    }

    @Override
    public boolean isDisposed() {
        return disposed;
    }

    @Override
    public void dispose() {
        disposed = true;
    }

    @Override
    public void subscribe(V value) {
        if(disposed) throw new RuntimeException("Subscriber is disposed");
        consumer.accept(value);
    }
}
