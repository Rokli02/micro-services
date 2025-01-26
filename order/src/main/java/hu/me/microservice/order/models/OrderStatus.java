package hu.me.microservice.order.models;

public enum OrderStatus {
    UNKNOWN(-1), CREATED(0), PROCESSING(1), SHIPPING(2), RECEIVED(3);

    private int val;
    OrderStatus(int value) {
        this.val = value;
    }

    public static OrderStatus getStatus(int val) {
        for (OrderStatus ts : OrderStatus.values()) {
            if (ts.val == val) {
                return ts;
            }
        }

        return OrderStatus.UNKNOWN;
    }

    public int valueOf() {
        return this.val;
    }
}
