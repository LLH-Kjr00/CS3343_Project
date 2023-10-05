package cs3343.animalchess.controllers;

import cs3343.animalchess.controllers.responses.ResponseError;
import cs3343.animalchess.exceptions.EntityNotFoundException;
import cs3343.animalchess.exceptions.TemporaryPlayerTokenExpiredException;
import cs3343.animalchess.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingController {

    private final PlayerRepository playerRepository;

    @Autowired
    public ExceptionHandlingController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseError handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseError("entity not found");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseError handlePlayerTokenExpiredException(TemporaryPlayerTokenExpiredException e) {
        playerRepository.delete(e.getPlayer());
        return new ResponseError("invalid player");
    }

}
