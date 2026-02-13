import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;

import java.util.ArrayList;

class ProgramControlTest {
    @Mock private ProgramControl programControl;

    @BeforeEach
    void setUp() {
        programControl = new ProgramControl();
    }

    @Test
    void testAvailableFilesNotNull() {
        assertNotNull(programControl.getAvailableFiles());
    }

    @Test
    void testAvailableFilesContainsTestFiles() {
        ArrayList<String> files = programControl.getAvailableFiles();
        assertTrue(files.contains("test1.txt") && files.contains("test2.txt"));
    }

    @Test
    void testGetFileContentsFile1() {
        assertTrue(programControl.getFileContentsByNumber(1).contains("This is a text file"));
    }

    @Test
    void testGetFileContentsFile2() {
        assertTrue(programControl.getFileContentsByNumber(2).contains("This is test 2"));
    }

    @Test
    void testGetFileContentsInvalidZero() {
        assertTrue(programControl.getFileContentsByNumber(0).startsWith("Error:"));
    }

    @Test
    void testGetFileContentsInvalidNegative() {
        assertTrue(programControl.getFileContentsByNumber(-5).startsWith("Error:"));
    }

    @Test
    void testGetFileContentsInvalidTooHigh() {
        assertTrue(programControl.getFileContentsByNumber(9999).startsWith("Error:"));
    }

    @Test
    void testRunNull() {
        assertDoesNotThrow(() -> programControl.run(null));
    }

    @Test
    void testRunEmpty() {
        assertDoesNotThrow(() -> programControl.run(""));
    }

    @Test
    void testRunValidNumber() {
        assertDoesNotThrow(() -> programControl.run("1"));
    }

    @Test
    void testRunInvalidNumber() {
        assertDoesNotThrow(() -> programControl.run("9999"));
    }

    @Test
    void testRunNonNumber() {
        assertDoesNotThrow(() -> programControl.run("notanumber"));
    }
}