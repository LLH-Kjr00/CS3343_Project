package animalchess.test.utils.provider.service;

import animalchess.utils.provider.Inject;

public class TestServiceStub implements TestService {

    @Inject
    private String testString;

    public TestServiceStub() {}

    public String getTestString() {
        return testString;
    }

}
