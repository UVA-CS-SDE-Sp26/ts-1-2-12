import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * ProgramControlTest.java
 * Team Member C
 *
 * Unit tests for ProgramControl.
 * Each test checks a specific behavior of ProgramControl.
 * Tests follow the same style as FileHandlerTest.java.
 */
class ProgramControlTest {

    private ProgramControl programControl;

    @BeforeEach
    void setUp() {
        // Use default constructor — relies on the real FileHandler and data folder
        programControl = new ProgramControl();
    }

    // --- Tests for getAvailableFiles() ---

    @Test
    void testAvailableFilesNotNull() {
        // The file list should never be null — at minimum it is an empty list
        assertNotNull(programControl.getAvailableFiles(), "Available files list should not be null.");
    }

    @Test
    void testAvailableFilesNotEmpty() {
        // Since the data folder has test files, the list should have at least one entry
        assertFalse(programControl.getAvailableFiles().isEmpty(), "Available files list should not be empty.");
    }

    @Test
    void testAvailableFilesContainsTestFiles() {
        // The data folder should contain test1.txt and test2.txt
        ArrayList<String> files = programControl.getAvailableFiles();
        assertTrue(files.contains("test1.txt"), "File list should contain test1.txt.");
        assertTrue(files.contains("test2.txt"), "File list should contain test2.txt.");
    }

    // --- Tests for getFileContentsByNumber() ---

    @Test
    void testGetFileContentsByNumberValidFile1() {
        // File number 1 should return non-null, non-empty content
        String contents = programControl.getFileContentsByNumber(1);
        assertNotNull(contents, "Contents of file 1 should not be null.");
        assertFalse(contents.isEmpty(), "Contents of file 1 should not be empty.");
    }

    @Test
    void testGetFileContentsByNumberValidFile2() {
        // File number 2 should return non-null, non-empty content
        String contents = programControl.getFileContentsByNumber(2);
        assertNotNull(contents, "Contents of file 2 should not be null.");
        assertFalse(contents.isEmpty(), "Contents of file 2 should not be empty.");
    }

    @Test
    void testGetFileContentsByNumberZeroIsInvalid() {
        // 0 is not a valid file number (list is 1-based)
        String result = programControl.getFileContentsByNumber(0);
        assertTrue(result.startsWith("Error:"), "File number 0 should return an error message.");
    }

    @Test
    void testGetFileContentsByNumberNegativeIsInvalid() {
        // Negative numbers should not be valid
        String result = programControl.getFileContentsByNumber(-5);
        assertTrue(result.startsWith("Error:"), "Negative file number should return an error message.");
    }

    @Test
    void testGetFileContentsByNumberTooHighIsInvalid() {
        // A very large number that exceeds the file list size should return an error
        String result = programControl.getFileContentsByNumber(9999);
        assertTrue(result.startsWith("Error:"), "Out-of-range file number should return an error message.");
    }

    @Test
    void testGetFileContentsByNumberReturnsCorrectText1() {
        // File 1 (test1.txt) should contain the expected text
        // This matches the content hardcoded in FileHandlerTest
        String contents = programControl.getFileContentsByNumber(1);
        assertTrue(contents.contains("This is a text file"), "test1.txt content should contain expected text.");
    }

    @Test
    void testGetFileContentsByNumberReturnsCorrectText2() {
        // File 2 (test2.txt) should contain the expected text
        String contents = programControl.getFileContentsByNumber(2);
        assertTrue(contents.contains("This is test 2"), "test2.txt content should contain expected text.");
    }

    // --- Tests for run() ---

    @Test
    void testRunWithNullArgumentDoesNotThrow() {
        // Passing null should just print the file list — no exception should be thrown
        assertDoesNotThrow(() -> programControl.run(null), "run(null) should not throw an exception.");
    }

    @Test
    void testRunWithEmptyStringDoesNotThrow() {
        // Passing an empty string should just print the file list — no exception
        assertDoesNotThrow(() -> programControl.run(""), "run(\"\") should not throw an exception.");
    }

    @Test
    void testRunWithValidNumberDoesNotThrow() {
        // Passing "1" should display the first file — no exception
        assertDoesNotThrow(() -> programControl.run("1"), "run(\"1\") should not throw an exception.");
    }

    @Test
    void testRunWithInvalidNumberDoesNotThrow() {
        // Passing a number that doesn't match any file — should handle gracefully
        assertDoesNotThrow(() -> programControl.run("9999"), "run(\"9999\") should not throw an exception.");
    }

    @Test
    void testRunWithNonNumberDoesNotThrow() {
        // Passing a non-number string — should print an error, not crash
        assertDoesNotThrow(() -> programControl.run("notanumber"), "run with a non-number should not throw an exception.");
    }
}