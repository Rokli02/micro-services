package hu.me.microservice.product.models;

public enum TestStatus {    
    CREATED(0), UNKNOWN(1);

    private Integer val;
    TestStatus(Integer value) {
        this.val = value;
    }

    static TestStatus getStatus(Integer val) {
        for (TestStatus ts : TestStatus.values()) {
            if (ts.val.equals(val)) {
                return ts;
            }
        }

        return TestStatus.UNKNOWN;
    }
}
