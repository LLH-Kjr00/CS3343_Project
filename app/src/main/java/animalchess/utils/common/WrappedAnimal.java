package animalchess.utils.common;

public record WrappedAnimal(
        WrappedAnimalType type,
        WrappedTile tile,
        WrappedTeam owner,
        boolean isTrapped
) {}
