package animalchess.utils.event;

import animalchess.exceptions.InvalidEventHandlerParameterException;
import animalchess.utils.common.Pair;
import animalchess.utils.flow.Disposable;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;

public class EventManager {

    private final HashMap<Class<? extends Event>, ObservableEvent<?>> eventObservables = new HashMap<>();

    public EventManager() {}

    public <T extends Event> void registerEvent(Class<T> eventClass) {
        if(eventObservables.containsKey(eventClass)) {
            throw new IllegalArgumentException("Event already registered");
        }
        eventObservables.put(eventClass, new ObservableEvent<T>());
    }

    public <T extends Event> void push(T event) {
        if(!eventObservables.containsKey(event.getClass())) {
            throw new IllegalArgumentException("Event not registered");
        }
        ObservableEvent<T> observable = (ObservableEvent<T>) eventObservables.get(event.getClass());
        observable.update(event);
    }

    public <T extends Event> Disposable listen(Class<T> eventClass, EventPriority priority, Consumer<T> consumer) {
        return ((ObservableEvent<T>) eventObservables.get(eventClass)).subscribe(new Pair<>(priority, consumer));
    }

    public void registerListeners(Object listenerInstance) {
        Arrays.stream(listenerInstance.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(EventHandler.class))
                .forEach((method) -> {
                    EventHandler eventHandler = method.getAnnotation(EventHandler.class);
                    if(method.getParameterCount() != 1 || !method.getParameterTypes()[0].isAssignableFrom(Event.class)) {
                        throw new InvalidEventHandlerParameterException(listenerInstance.getClass(), method);
                    }
                    Class<? extends Event> eventClass = (Class<? extends Event>) method.getParameterTypes()[0];
                    listen(eventClass, eventHandler.priority(), (eventInstance) -> {
                        try {
                            method.invoke(listenerInstance, eventInstance);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
                });
    }

}
