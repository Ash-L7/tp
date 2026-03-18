package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tour.Tour;

/**
 * Jackson-friendly version of {@link Tour}.
 */
class JsonAdaptedTour {
    private final String tourName;

    /**
     * Constructs a {@code JsonAdaptedTour} with the given {@code tourName}.
     */
    @JsonCreator
    public JsonAdaptedTour(String tourName) {
        this.tourName = tourName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTour(Tour source) {
        tourName = source.tourName;
    }

    @JsonValue
    public String getTourName() {
        return tourName;
    }

    /**
     * Converts this Jackson-friendly adapted tour object into the model's {@code Tour} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tour.
     */
    public Tour toModelType() throws IllegalValueException {
        if (!Tour.isValidTourName(tourName)) {
            throw new IllegalValueException(Tour.MESSAGE_CONSTRAINTS);
        }
        return new Tour(tourName);
    }

}
