package animalchess.test.utils.provider.service;

import animalchess.utils.provider.Inject;

public class TestServiceRequest {
    @Inject
    private TestService testService;

    public TestService getTestService() {
        return testService;
    }
}
