package task;

import util.ShrekException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    // Add a public getter method
    public LocalDate getBy() {
        return by;
    }

    public Deadline(String description, String by) throws ShrekException {
        super(description);
        try {
            this.by = LocalDate.parse(by); // convert from string to LocalDate
        } catch (DateTimeParseException e) {
            throw new ShrekException("Shrek needs the date in *year-month-day* format, e.g. 2025-01-01");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}