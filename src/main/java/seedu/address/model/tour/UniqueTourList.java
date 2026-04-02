package seedu.address.model.tour;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tour.exceptions.DuplicateTourException;
import seedu.address.model.tour.exceptions.TourNotFoundException;

/**
 * A list of tours that enforces uniqueness between its elements and does not allow nulls.
 * A tour is considered unique by comparing using {@code Tour#equals(Tour)}.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueTourList implements Iterable<Tour> {

    private final ObservableList<Tour> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tour> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tour as the given argument.
     */
    public boolean contains(Tour toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a tour to the list.
     * The tour must not already exist in the list.
     */
    public void add(Tour toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTourException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent tour from the list.
     * The tour must exist in the list.
     */
    public void remove(Tour toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TourNotFoundException();
        }
    }

    public void setTours(UniqueTourList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tours}.
     * {@code tours} must not contain duplicate contacts.
     */
    public void setTours(List<Tour> tours) {
        requireAllNonNull(tours);
        if (!toursAreUnique(tours)) {
            throw new DuplicateTourException();
        }

        internalList.setAll(tours);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tour> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tour> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueTourList)) {
            return false;
        }

        UniqueTourList otherUniqueTourList = (UniqueTourList) other;
        return internalList.equals(otherUniqueTourList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code tours} contains only unique tours.
     */
    private boolean toursAreUnique(List<Tour> tours) {
        requireNonNull(tours);
        for (int i = 0; i < tours.size() - 1; i++) {
            for (int j = i + 1; j < tours.size(); j++) {
                if (tours.get(i).equals(tours.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

