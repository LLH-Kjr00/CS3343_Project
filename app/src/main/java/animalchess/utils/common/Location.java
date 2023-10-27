package animalchess.utils.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Location {
    private int x;
    private int y;

    public Location move(int offsetX, int offsetY) {
        return Location.builder()
                .x(this.x + offsetX)
                .y(this.y + offsetY)
                .build();
    }
}
