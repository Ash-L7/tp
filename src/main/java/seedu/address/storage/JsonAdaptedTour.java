package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tour.Tour;
import seedu.address.model.tour.TourFavouriteStatus;

/**
 * Jackson-friendly version of {@link Tour}.
 */
class JsonAdaptedTour {
    private final String tourName;
    private final String tourFavouriteStatus;

    /**
     * Constructs a {@code JsonAdaptedTour} with the given {@code tourName}.
     */
    @JsonCreator
    public JsonAdaptedTour(@JsonProperty("tourName") String tourName,
                           @JsonProperty("tourFavouriteStatus") String tourFavouriteStatus) {
        this.tourName = tourName;
        this.tourFavouriteStatus = tourFavouriteStatus;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTour(Tour source) {
        tourName = source.tourName;
        tourFavouriteStatus = String.valueOf(source.getTourFavouriteStatus().isTourFavourite);
    }

    public String getTourName() {
        return tourName;
    }

    public String getTourFavouriteStatus() {
        return tourFavouriteStatus;
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

        final TourFavouriteStatus modelTourFavouriteStatus;
        if (tourFavouriteStatus == null) {
            modelTourFavouriteStatus = new TourFavouriteStatus("false");
        } else {
            if (!TourFavouriteStatus.isValidTourFavouriteStatus(tourFavouriteStatus)) {
                throw new IllegalValueException(TourFavouriteStatus.MESSAGE_CONSTRAINTS);
            }
            modelTourFavouriteStatus = new TourFavouriteStatus(tourFavouriteStatus);
        }

        return new Tour(tourName, modelTourFavouriteStatus);
    }

}
