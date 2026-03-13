package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE =
            "=============================================================\n"
                    + "                    BIVAGO - HELP GUIDE                      \n"
                    + "=============================================================\n"
                    + "\n"
                    + "CONTACT MANAGEMENT\n"
                    + "-----------------------------------------------------------\n"
                    + "ADD CONTACT\n"
                    + "  Command : add n/NAME e/EMAIL c/CATEGORY [p/PHONE] [a/ADDRESS] [t/TAG]...\n"
                    + "  Example : add n/John p/98736789 e/john@gmail.com c/person\n"
                    + "  Info    : Categories: person, fnb, accommodation, attraction\n"
                    + "\n"
                    + "DELETE CONTACT\n"
                    + "  Command : delete INDEX\n"
                    + "  Example : delete 1\n"
                    + "  Info    : INDEX refers to the number shown in the displayed list\n"
                    + "\n"
                    + "SEARCH CONTACT\n"
                    + "  Command : search PARTIAL_NAME\n"
                    + "  Example : search John\n"
                    + "  Info    : Case-insensitive, searches across all categories\n"
                    + "\n"
                    + "SET OPERATING HOURS\n"
                    + "  Command : op-hours INDEX from/HH:mm to/HH:mm\n"
                    + "  Example : op-hours 3 from/07:00 to/20:00\n"
                    + "  Info    : 24-hour format only. Applies to restaurants & attractions\n"
                    + "\n"
                    + "TOUR MANAGEMENT\n"
                    + "-----------------------------------------------------------\n"
                    + "CREATE TOUR\n"
                    + "  Command : tour-add n/TOUR_NAME [t/TAG]...\n"
                    + "  Example : tour-add n/Island Hopper Tour\n"
                    + "\n"
                    + "ASSIGN CONTACT TO TOUR\n"
                    + "  Command : tour-assign INDEX to/TOUR_NAME\n"
                    + "  Example : tour-assign 3 to/Island Hopper Tour\n"
                    + "  Info    : INDEX refers to the number shown in the displayed list\n"
                    + "\n"
                    + "SEARCH TOUR\n"
                    + "  Command : tour-search PARTIAL_TOUR_NAME\n"
                    + "  Example : tour-search Island\n"
                    + "  Info    : Case-insensitive partial search\n"
                    + "\n"
                    + "OTHER\n"
                    + "-----------------------------------------------------------\n"
                    + "HELP\n"
                    + "  Command : help\n"
                    + "  Info    : Displays this help guide\n"
                    + "\n"
                    + "=============================================================\n"
                    + "  Tip: Data is saved automatically after every command.\n"
                    + "=============================================================\n";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE);
    }
}
