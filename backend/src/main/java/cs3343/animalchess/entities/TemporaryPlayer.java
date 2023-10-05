package cs3343.animalchess.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class TemporaryPlayer extends Entity {

    private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 5;

    String token = generateToken();
    long tokenExpireAt = 0;
    String displayName = generateDisplayName();
    boolean isReady = false;

    public TemporaryPlayer() {
        super();
        extendTokenLife();
    }

    private static String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static String generateDisplayName() {
        // hardcode some name for demo purpose
        List<String> names = Arrays.asList("Nameless");
        Random random = new Random();
        return names.get(random.nextInt(names.size()));
    }

    public boolean isExpired() {
        return tokenExpireAt < System.currentTimeMillis();
    }

    public void extendTokenLife() {
        this.tokenExpireAt = System.currentTimeMillis() + TOKEN_EXPIRE_TIME;
    }

}
