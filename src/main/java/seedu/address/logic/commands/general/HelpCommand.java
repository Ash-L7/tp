package seedu.address.logic.commands.general;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
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
                    + "  Command: add type/TYPE n/NAME p/PHONE e/EMAIL a/ADDRESS [h/HALAL] [o/OPEN] [c/CLOSE] "
                    + "[s/STARS] [t/TAG]...\n"
                    + "  Info:\n"
                    + "    Types: person, fnb, accomm, attraction\n"
                    + "    FNB: h/true or false (default: false)\n"
                    + "    Attraction: o/HH:mm c/HH:mm (default: 08:00 - 22:00)\n"
                    + "    Accommodation: s/1-5 (default: 3)\n"
                    + "    Note: Fields not applicable to a type will be ignored\n"
                    + "  Example: add type/person n/John Doe p/98765432 e/john@gmail.com\n"
                    + "\n"
                    + "LIST CONTACTS\n"
                    + "  Command: list\n"
                    + "\n"
                    + "EDIT CONTACT\n"
                    + "  Command: edit INDEX [PREFIX/NEW_VALUE]...\n"
                    + "  Info:\n"
                    + "    - INDEX must be a positive integer\n"
                    + "    - At least one field required\n"
                    + "    - Existing values will be overwritten\n"
                    + "    - Use t/ (prefix only) to clear all tags\n"
                    + "  Example: edit 1 p/91234567 e/johndoe@gmail.com t/\n"
                    + "\n"
                    + "DELETE CONTACT\n"
                    + "  Command: delete INDEX\n"
                    + "  Info: INDEX must be a positive integer\n"
                    + "  Example: delete 2\n"
                    + "\n"
                    + "FIND CONTACT\n"
                    + "  Command: find KEYWORD [MORE_KEYWORDS]\n"
                    + "  Info: Case-insensitive, matches full words (OR search)\n"
                    + "  Example: find John\n"
                    + "\n"
                    + "-----------------------------------------------------------\n"
                    + "TOUR MANAGEMENT\n"
                    + "-----------------------------------------------------------\n"
                    + "ADD TOUR\n"
                    + "  Command: tour-add n/NAME\n"
                    + "  Example: tour-add n/Le Royal Tour\n"
                    + "\n"
                    + "LIST TOURS\n"
                    + "  Command: tour-list\n"
                    + "\n"
                    + "ASSIGN TOUR\n"
                    + "  Command: tour-assign CONTACT_INDEX tour/TOUR_INDEX\n"
                    + "  Info: CONTACT_INDEX and TOUR_INDEX must be positive integers\n"
                    + "  Example: tour-assign 1 tour/2\n"
                    + "\n"
                    + "UNASSIGN TOUR\n"
                    + "  Command: tour-unassign CONTACT_INDEX tour/TOUR_INDEX\n"
                    + "  Info: CONTACT_INDEX and TOUR_INDEX must be positive integers\n"
                    + "  Example: tour-unassign 3 tour/5\n"
                    + "\n"
                    + "VIEW TOUR\n"
                    + "  Command: tour-view INDEX\n"
                    + "  Info: INDEX must be a positive integer\n"
                    + "  Example: tour-view 1\n"
                    + "\n"
                    + "FIND TOUR\n"
                    + "  Command: tour-find KEYWORD [MORE_KEYWORDS]\n"
                    + "  Info: Case-insensitive, matches full words (OR search)\n"
                    + "  Example: tour-find Foodie\n"
                    + "\n"
                    + "DELETE TOUR\n"
                    + "  Command: tour-delete INDEX\n"
                    + "  Info: INDEX must be a positive integer\n"
                    + "  Example: tour-delete 1\n"
                    + "\n"
                    + "-----------------------------------------------------------\n"
                    + "OTHER\n"
                    + "-----------------------------------------------------------\n"
                    + "UNDO\n"
                    + "  Command: undo\n"
                    + "  Info: Reverts the most recent command that modified data\n"
                    + "  Example: undo\n"
                    + "\n"
                    + "REDO\n"
                    + "  Command: redo\n"
                    + "  Info: Restores the most recently undone command\n"
                    + "  Example: redo\n"
                    + "\n"
                    + "HELP\n"
                    + "  Command: help\n"
                    + "\n"
                    + "=============================================================\n"
                    + "Auto-save: Data is saved automatically after every command.\n"
                    + "Undo/Redo: Use 'undo' and 'redo' commands to revert or restore changes.\n"
                    + "=============================================================\n";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE);
    }
}
