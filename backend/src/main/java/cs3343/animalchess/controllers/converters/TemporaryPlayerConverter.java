package cs3343.animalchess.controllers.converters;

import cs3343.animalchess.entities.TemporaryPlayer;
import cs3343.animalchess.exceptions.EntityNotFoundException;
import cs3343.animalchess.exceptions.TemporaryPlayerTokenExpiredException;
import cs3343.animalchess.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TemporaryPlayerConverter implements Converter<String, TemporaryPlayer> {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public TemporaryPlayer convert(@NonNull String source) {
        Optional<TemporaryPlayer> optionalPlayer = playerRepository.findById(source);
        TemporaryPlayer player = optionalPlayer.orElseThrow(
                () -> new EntityNotFoundException(TemporaryPlayer.class.getName(), source)
        );
        if(player.isExpired()) throw new TemporaryPlayerTokenExpiredException(player);
        player.extendTokenLife();
        return player;
    }
}
