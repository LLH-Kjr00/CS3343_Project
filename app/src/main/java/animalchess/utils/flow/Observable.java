package animalchess.utils.flow;

public interface Observable<SubscriberT, ValueT> {
    Disposable subscribe(SubscriberT value);
    void update(ValueT value);
}
