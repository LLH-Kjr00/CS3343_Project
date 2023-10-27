package animalchess.utils.event;

public interface Cancellable {
    void cancel();
    boolean isCancelled();
}
