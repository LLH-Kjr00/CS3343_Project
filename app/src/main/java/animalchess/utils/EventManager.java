package animalchess.utils;

import java.util.HashMap;
import java.util.function.Consumer;

public class EventManager {

    private final HashMap<Class<? extends Event>, Observable<? extends Event>> eventObservables = new HashMap<>();

    public EventManager() {}

    public <T extends Event> void register(Class<T> eventClass) {
        if(eventObservables.containsKey(eventClass)) {
            throw new IllegalArgumentException("Event already registered");
        }
        eventObservables.put(eventClass, new Observable<T>(null));
    }

    public <T extends Event> void push(T event) {
        if(!eventObservables.containsKey(event.getClass())) {
            throw new IllegalArgumentException("Event not registered");
        }
        Observable<T> observable = (Observable<T>) eventObservables.get(event.getClass());
        observable.update(event);
    }

    public <T extends Event> Disposable listen(Class<T> eventClass, Consumer<T> consumer) {
        return ((Observable<T>) eventObservables.get(eventClass)).subscribe(consumer);
    }

}
