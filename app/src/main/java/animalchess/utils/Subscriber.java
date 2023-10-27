package animalchess.utils;

public interface Subscriber<V> extends Disposable {
    void subscribe(V value);
}
