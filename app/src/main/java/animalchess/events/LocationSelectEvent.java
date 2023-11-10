package animalchess.events;

import animalchess.utils.common.WrappedLocation;
import animalchess.utils.event.Cancellable;
import animalchess.utils.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LocationSelectEvent implements Event, Cancellable {
    @Getter
    @NonNull
    private WrappedLocation location;

    private boolean cancelled = false;
    @Override
    public void cancel() {
        cancelled = true;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }
}
