package animalchess.test.utils.provider;

import animalchess.test.utils.provider.service.TestService;
import animalchess.test.utils.provider.service.TestServiceRequest;
import animalchess.test.utils.provider.service.TestServiceStub;
import animalchess.test.utils.provider.service.TestServiceStub2;
import animalchess.utils.provider.ProviderModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProviderTest {

    @Test
    public void testInject() {
        String abc = "hello-world";
        TestServiceStub stub = new TestServiceStub();

        ProviderModule.builder()
                .singleton(abc)
                .singleton(stub)
                .resolve();

        assertNotNull(stub.getTestString());
        assertEquals(abc, stub.getTestString());
    }

    @Test
    public void testInterfaceBinding() {
        TestServiceStub stub = new TestServiceStub();
        TestServiceStub2 stub2 = new TestServiceStub2();

        TestServiceRequest req = new TestServiceRequest();

        ProviderModule.builder()
                .singleton("")
                .singleton(stub)
                .singleton(stub2)
                .singleton(req)
                .bind(TestService.class, TestServiceStub.class)
                .resolve();

        assertNotNull(req.getTestService());
        assertEquals(stub, req.getTestService());
    }

}
