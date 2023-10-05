package cs3343.animalchess.exceptions;

import cs3343.animalchess.entities.TemporaryPlayer;
import lombok.Getter;

@Getter
public class TemporaryPlayerTokenExpiredException extends RuntimeException {

    TemporaryPlayer player;

    public TemporaryPlayerTokenExpiredException(TemporaryPlayer player) {
        super("Temporary player token expired: " + player.getToken());
        this.player = player;
    }

}
