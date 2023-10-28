package animalchess.utils.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class WrappedGameResult {

    private List<WrappedTeamResult> results;

    @Data
    public static class WrappedTeamResult {
        private WrappedTeam team;
        private WrappedGameResultType resultType;
    }

    public enum WrappedGameResultType {
        WIN, LOSE
    }

}
