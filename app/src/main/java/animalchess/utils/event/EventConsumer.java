package animalchess.utils.event;

import animalchess.utils.flow.Subscriber;

import java.util.function.Consumer;

public class EventConsumer<T extends Event> implements Subscriber<T>, Comparable<EventConsumer<T>> {

    private boolean disposed = false;
    private final ObservableEvent<T> observable;
    private final EventPriority priority;
    private final Consumer<T> consumerAction;

    public EventConsumer(ObservableEvent<T> observable, Consumer<T> consumerAction, EventPriority eventPriority) {
        this.observable = observable;
        this.consumerAction = consumerAction;
        this.priority = eventPriority;
    }

    @Override
    public boolean isDisposed() {
        return disposed;
    }

    @Override
    public void dispose() {
        observable.remove(this);
        disposed = true;
    }

    @Override
    public void subscribe(T value) {
        if(!disposed) consumerAction.accept(value);
    }

    @Override
    public int compareTo(EventConsumer<T> another) {
        return Integer.compare(priority.getPriorityValue(), another.priority.getPriorityValue());
    }
}
