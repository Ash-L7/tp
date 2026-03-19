package seedu.address.model.tour.exceptions;

/**
 * Signals that the operation will result in duplicate Tours (Tours are considered
 * duplicates if they have the same identity).
 */
public class DuplicateTourException extends RuntimeException {
    public DuplicateTourException() {
        super("Operation would result in duplicate tours");
    }
}
