import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JUnitTests {
    // Unit Test: verifies individual components of the system
    // Integration Test: checks the interaction between different components
    // System Test: validates the complete and integrated software product

    // Test for Minmum Word Count (Testing only the minimum word count requirement)
    @Test
    public void testMinimumWordCount() {
        String validMessage = "This is a valid message with eight words";
        String invalidMessage = "Too short";

        assertTrue(validMessage.split(" ").length >= 8, "Message should have at least 8 words");
        assertFalse(invalidMessage.split(" ").length >= 8, "Message should have less than 8 words");
    }

    // Test to ensure word count is preserved after word swapping 
    @Test
    public void testWordCountPreservation() {
        String originalMessage = "This is a test message for word swapping functionality";
        String[] words = originalMessage.split(" ");

        // Simulate word swapping
        String swappedMessage = words[3] + " " + words[1] + " " + words[0] + " " + words[2] + " " +
                                words[4] + " " + words[5] + " " + words[6] + " " + words[7];

        assertEquals(originalMessage.split(" ").length, swappedMessage.split(" ").length,
                     "Word count should be preserved after swapping");
    }

    // Test to ensure message changes after word swapping 
    @Test
    public void testMessageChange() {
        String originalMessage = "This is a test message for word swapping functionality";
        String[] words = originalMessage.split(" ");

        // Simulate word swapping
        String swappedMessage = words[3] + " " + words[1] + " " + words[0] + " " + words[2] + " " +
                                words[4] + " " + words[5] + " " + words[6] + " " + words[7];

        assertNotEquals(originalMessage, swappedMessage, "Message should change after word swapping");
    }
}