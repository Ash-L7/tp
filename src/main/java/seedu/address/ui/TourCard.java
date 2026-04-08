package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.tour.Tour;

/**
 * A UI component that displays information of a {@code Tour}.
 */
public class TourCard extends UiPart<Region> {
    private static final String FXML = "TourListCard.fxml";

    public final Tour tour;

    @FXML
    private Label tourName;
    @FXML
    private Label id;
    @FXML
    private Label tourFavouriteStatus;

    /**
     * Creates a {@code TourCard} with the given {@code Tour} and index to display.
     */
    public TourCard(Tour tour, int displayedIndex) {
        super(FXML);
        this.tour = tour;
        id.setText(displayedIndex + ". ");
        tourName.setText(tour.getTourName());
        tourFavouriteStatus.setVisible(tour.isTourFavourite());
        tourFavouriteStatus.setManaged(tour.isTourFavourite());
    }
}
