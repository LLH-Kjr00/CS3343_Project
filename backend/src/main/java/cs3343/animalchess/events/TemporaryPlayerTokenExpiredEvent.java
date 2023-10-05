package cs3343.animalchess.events;

import cs3343.animalchess.entities.TemporaryPlayer;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TemporaryPlayerTokenExpiredEvent extends ApplicationEvent {

    private final TemporaryPlayer player;

    public TemporaryPlayerTokenExpiredEvent(Object source, TemporaryPlayer player) {
        super(source);
        this.player = player;
    }

}
