package animalchess.utils.common;

import animalchess.animals.Animal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Tile {

    private Location location;
    private Animal animal;

}
