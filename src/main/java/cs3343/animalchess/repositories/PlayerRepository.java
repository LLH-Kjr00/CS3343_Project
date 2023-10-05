package cs3343.animalchess.repositories;

import cs3343.animalchess.entities.TemporaryPlayer;
import cs3343.animalchess.events.TemporaryPlayerTokenExpiredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class PlayerRepository implements CrudRepository<TemporaryPlayer, String> {

    private final ApplicationEventPublisher eventPublisher;

    private final HashMap<String, TemporaryPlayer> map = new HashMap<>();

    @Autowired
    public PlayerRepository(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    @NonNull
    public <S extends TemporaryPlayer> S save(@NonNull S entity) {
        map.put(entity.getToken(), entity);
        return entity;
    }

    @Override
    @NonNull
    public <S extends TemporaryPlayer> Iterable<S> saveAll(@NonNull Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    @NonNull
    public Optional<TemporaryPlayer> findById(@NonNull String s) {
        return Optional.ofNullable(map.get(s));
    }

    @Override
    @NonNull
    public boolean existsById(@NonNull String s) {
        return map.containsKey(s);
    }

    @Override
    @NonNull
    public Iterable<TemporaryPlayer> findAll() {
        return map.values();
    }

    @Override
    @NonNull
    public Iterable<TemporaryPlayer> findAllById(@NonNull Iterable<String> strings) {
        return StreamSupport.stream(strings.spliterator(), false)
                .filter(this::existsById)
                .map(map::get)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return map.size();
    }

    @Override
    public void deleteById(@NonNull String s) {
        map.remove(s);
    }

    @Override
    public void delete(@NonNull TemporaryPlayer entity) {
        deleteById(entity.getToken());
    }

    @Override
    public void deleteAllById(@NonNull Iterable<? extends String> strings) {
        strings.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(@NonNull Iterable<? extends TemporaryPlayer> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        map.clear();
    }

    @Scheduled(cron = "* * * * * *")
    protected void garbagePlayerTokenCollection() {
        List<TemporaryPlayer> tokenExpired = map.values()
                .stream()
                .filter(TemporaryPlayer::isExpired)
                .toList();
        tokenExpired.forEach((player) -> {
            eventPublisher.publishEvent(new TemporaryPlayerTokenExpiredEvent(this, player));
            deleteById(player.getToken());
        });
    }
}
