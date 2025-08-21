public enum Command {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    BYE;

    public static Command fromString(String input) throws ShrekException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ShrekException("I don't speak your language. I don't understand: " + input);
        }
    }
}
