import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JUnitTests {
    // Test for Minmum Word Count
    @Test
    public void testMinimumWordCount() {
        String validMessage = "This is a valid message with eight words";
        String invalidMessage = "Too short";

        assertTrue(validMessage.split(" ").length >= 8, "Message should have at least 8 words");
        assertFalse(invalidMessage.split(" ").length >= 8, "Message should have less than 8 words");
    }
}