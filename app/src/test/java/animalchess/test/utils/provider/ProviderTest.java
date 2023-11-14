package animalchess.test.utils.provider;

import animalchess.utils.provider.ProviderModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

}
