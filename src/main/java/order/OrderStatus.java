package order;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PROCESSING("Processing"),
    IN_DELIVERY("In delivery"),
    COMPLETED("Completed");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

}
