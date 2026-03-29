package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FavoriteStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FavoriteStatus(null));
    }

    @Test
    public void constructor_invalidFavoriteStatus_throwsIllegalArgumentException() {
        String invalidFavoriteStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new FavoriteStatus(invalidFavoriteStatus));
    }

    @Test
    public void isValidFavoriteStatus() {
        // null favorite status
        assertThrows(NullPointerException.class, () -> FavoriteStatus.isValidFavoriteStatus(null));

        // invalid favorite statuses
        assertFalse(FavoriteStatus.isValidFavoriteStatus("")); // empty string
        assertFalse(FavoriteStatus.isValidFavoriteStatus(" ")); // spaces only
        assertFalse(FavoriteStatus.isValidFavoriteStatus("yes"));
        assertFalse(FavoriteStatus.isValidFavoriteStatus("no"));
        assertFalse(FavoriteStatus.isValidFavoriteStatus("1"));

        // valid favorite statuses
        assertTrue(FavoriteStatus.isValidFavoriteStatus("true"));
        assertTrue(FavoriteStatus.isValidFavoriteStatus("false"));
        assertTrue(FavoriteStatus.isValidFavoriteStatus("TRUE"));
        assertTrue(FavoriteStatus.isValidFavoriteStatus("FALSE"));
    }

    @Test
    public void toString_validValues_success() {
        assertEquals("Favorite", new FavoriteStatus("true").toString());
        assertEquals("Regular", new FavoriteStatus("false").toString());
    }

    @Test
    public void equals() {
        FavoriteStatus favoriteStatus = new FavoriteStatus("true");

        // same values -> returns true
        assertTrue(favoriteStatus.equals(new FavoriteStatus("true")));

        // same object -> returns true
        assertTrue(favoriteStatus.equals(favoriteStatus));

        // null -> returns false
        assertFalse(favoriteStatus.equals(null));

        // different types -> returns false
        assertFalse(favoriteStatus.equals(5));

        // different values -> returns false
        assertFalse(favoriteStatus.equals(new FavoriteStatus("false")));
    }

    @Test
    public void hashCode_sameValue_sameHashCode() {
        assertEquals(new FavoriteStatus("true").hashCode(), new FavoriteStatus("true").hashCode());
        assertEquals(new FavoriteStatus("false").hashCode(), new FavoriteStatus("false").hashCode());
    }

    @Test
    public void constructor_uppercaseTrue_expectedToBehaveLikeTrue() {
        FavoriteStatus favoriteStatus = new FavoriteStatus("TRUE");
        assertTrue(favoriteStatus.isFavorite);
    }
}
