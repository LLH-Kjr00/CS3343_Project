package animalchess.test.utils.provider;

import animalchess.utils.provider.Inject;

public class TestServiceStub {

    @Inject
    private String testString;

    public TestServiceStub() {}

    public String getTestString() {
        return testString;
    }
}
