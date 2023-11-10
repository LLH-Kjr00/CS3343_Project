package animalchess.utils.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<K, V> {
    private K first;
    private V second;
}
