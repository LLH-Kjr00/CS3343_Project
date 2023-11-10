package animalchess.utils.event;

public enum EventPriority {
    LOWEST(5),
    LOW(4),
    NORMAL(3),
    HIGH(2),
    HIGHEST(1);

    private final int priorityValue;
    EventPriority(int priorityValue) {
        this.priorityValue = priorityValue;
    }

    public int getPriorityValue() {
        return priorityValue;
    }
}
