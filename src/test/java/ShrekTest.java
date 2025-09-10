import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import instruction.AddInstruction;
import instruction.Instruction;
import parser.Parser;
import shrek.Shrek;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;
import util.ShrekException;

/**
 * Test class for Shrek application functionality.
 * Contains unit tests for parsing commands and integration tests for user interactions.
 */
public class ShrekTest {

    /**
     * Tests parsing and execution of a todo command.
     * Verifies that a todo task is correctly added to the task list.
     *
     * @throws ShrekException if parsing or execution fails
     */
    @Test
    public void testParseTodoCommand() throws ShrekException {
        Instruction instr = Parser.parse("todo borrow book");
        assertInstanceOf(AddInstruction.class, instr);

        AddInstruction addInstr = (AddInstruction) instr;
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/test_dummy.txt");

        // execute the instruction on a fresh list
        addInstr.execute(list, ui, storage);

        assertEquals(1, list.size());
        assertEquals("[T][ ] borrow book", list.get(0).toString());
    }

    /**
     * Tests parsing and execution of a deadline command.
     * Verifies that a deadline task is correctly added to the task list with proper date formatting.
     *
     * @throws ShrekException if parsing or execution fails
     */
    @Test
    public void testParseDeadlineCommand() throws ShrekException {
        Instruction instr = Parser.parse("deadline return book /by 2025-01-01");
        assertInstanceOf(AddInstruction.class, instr);

        AddInstruction addInstr = (AddInstruction) instr;
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/test_dummy.txt");

        addInstr.execute(list, ui, storage);

        assertEquals(1, list.size());
        assertEquals("[D][ ] return book (by: Jan 1 2025)", list.get(0).toString());
    }

    /**
     * Normalizes line endings in a string by converting Windows line endings (\r\n) to Unix line endings (\n)
     * and trims whitespace.
     *
     * @param s the string to normalize
     * @return the normalized string with consistent line endings
     */
    private String normalize(String s) {
        return s.replace("\r\n", "\n").trim();
    }

    @Test
    public void testTodoAndMark() {
        String testFilePath = "./data/test_shrek.txt";
        File f = new File(testFilePath);
        if (f.exists()) {
            f.delete();
        }

        String input = "todo borrow book\nmark 1\nbye\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        new Shrek(testFilePath).runTextMode();

        String actualOutput = out.toString();

        String expected = "Hello I'm Shrek!\n"
                + "Welcome to my Swamp!\n"
                + "What can I do for you?\n"
                + "Okies, onion (task) added:\n"
                + "  [T][ ] borrow book\n"
                + "Now you have 1 tasks in the list.\n"
                + "Awesome! One layer of onion(task) has been removed\n"
                + "(marked done)\n"
                + "  [T][X] borrow book\n"
                + "Bye! I'm going to find Princess Fiona :)\n"
                + "See ya later\n";

        assertEquals(normalize(expected), normalize(actualOutput));

        f.delete();
    }
}
