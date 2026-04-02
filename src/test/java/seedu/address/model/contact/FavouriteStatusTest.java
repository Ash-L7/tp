package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FavouriteStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FavouriteStatus(null));
    }

    @Test
    public void constructor_invalidFavouriteStatus_throwsIllegalArgumentException() {
        String invalidFavouriteStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new FavouriteStatus(invalidFavouriteStatus));
    }

    @Test
    public void isValidFavouriteStatus() {
        // null favourite status
        assertThrows(NullPointerException.class, () -> FavouriteStatus.isValidFavouriteStatus(null));

        // invalid favourite statuses
        assertFalse(FavouriteStatus.isValidFavouriteStatus("")); // empty string
        assertFalse(FavouriteStatus.isValidFavouriteStatus(" ")); // spaces only
        assertFalse(FavouriteStatus.isValidFavouriteStatus("yes"));
        assertFalse(FavouriteStatus.isValidFavouriteStatus("no"));
        assertFalse(FavouriteStatus.isValidFavouriteStatus("1"));

        // valid favourite statuses
        assertTrue(FavouriteStatus.isValidFavouriteStatus("true"));
        assertTrue(FavouriteStatus.isValidFavouriteStatus("false"));
        assertTrue(FavouriteStatus.isValidFavouriteStatus("TRUE"));
        assertTrue(FavouriteStatus.isValidFavouriteStatus("FALSE"));
    }

    @Test
    public void toString_validValues_success() {
        assertEquals("Favourite", new FavouriteStatus("true").toString());
        assertEquals("Regular", new FavouriteStatus("false").toString());
    }

    @Test
    public void equals() {
        FavouriteStatus favouriteStatus = new FavouriteStatus("true");

        // same values -> returns true
        assertTrue(favouriteStatus.equals(new FavouriteStatus("true")));

        // same object -> returns true
        assertTrue(favouriteStatus.equals(favouriteStatus));

        // null -> returns false
        assertFalse(favouriteStatus.equals(null));

        // different types -> returns false
        assertFalse(favouriteStatus.equals(5));

        // different values -> returns false
        assertFalse(favouriteStatus.equals(new FavouriteStatus("false")));
    }

    @Test
    public void hashCode_sameValue_sameHashCode() {
        assertEquals(new FavouriteStatus("true").hashCode(), new FavouriteStatus("true").hashCode());
        assertEquals(new FavouriteStatus("false").hashCode(), new FavouriteStatus("false").hashCode());
    }

    @Test
    public void constructor_uppercaseTrue_expectedToBehaveLikeTrue() {
        FavouriteStatus favouriteStatus = new FavouriteStatus("TRUE");
        assertTrue(favouriteStatus.isFavourite);
    }
}
