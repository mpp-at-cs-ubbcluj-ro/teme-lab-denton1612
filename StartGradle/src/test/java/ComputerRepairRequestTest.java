import ubb.scs.model.ComputerRepairRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComputerRepairRequestTest {
    @Test
    @DisplayName("First Test")
    public void testExample() {
        ComputerRepairRequest request = new ComputerRepairRequest();
        assertEquals("", request.getOwnerName());
        assertEquals("", request.getOwnerAddress());
    }

    @Test
    @DisplayName("Test Exemplu")
    public void testExample2() {
        assertEquals(2, 2, "Numerele trebuie sa fie egale");
    }
}
