package animalchess.utils.flow;

public interface Subscriber<V> extends Disposable {
    void subscribe(V value);
}
