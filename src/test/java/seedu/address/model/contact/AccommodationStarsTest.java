package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.AccommodationStars.Stars;

public class AccommodationStarsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        seedu.address.testutil.Assert.assertThrows(
                NullPointerException.class, () -> new AccommodationStars((String) null));
    }

    @Test
    public void constructor_invalidAccommodationStars_throwsIllegalArgumentException() {
        String invalidAccommodationStars = "";
        seedu.address.testutil.Assert.assertThrows(
                IllegalArgumentException.class, () -> new AccommodationStars(invalidAccommodationStars));
    }

    @Test
    public void isValidAccommodationStars() {
        // null accommodation stars
        seedu.address.testutil.Assert.assertThrows(
                NullPointerException.class, () -> AccommodationStars.isValidAccommodationStars(null));

        // invalid accommodation stars
        assertFalse(AccommodationStars.isValidAccommodationStars("")); // empty string
        assertFalse(AccommodationStars.isValidAccommodationStars(" ")); // spaces only
        assertFalse(AccommodationStars.isValidAccommodationStars("0"));
        assertFalse(AccommodationStars.isValidAccommodationStars("6"));
        assertFalse(AccommodationStars.isValidAccommodationStars("12"));
        assertFalse(AccommodationStars.isValidAccommodationStars("a"));
        assertFalse(AccommodationStars.isValidAccommodationStars("#"));

        // valid accommodation stars
        assertTrue(AccommodationStars.isValidAccommodationStars("1"));
        assertTrue(AccommodationStars.isValidAccommodationStars("2"));
        assertTrue(AccommodationStars.isValidAccommodationStars("3"));
        assertTrue(AccommodationStars.isValidAccommodationStars("4"));
        assertTrue(AccommodationStars.isValidAccommodationStars("5"));
    }

    @Test
    public void starsFromString_validInputs_success() {
        assertEquals(Stars.ONE_STAR, Stars.fromString("1"));
        assertEquals(Stars.TWO_STAR, Stars.fromString("2"));
        assertEquals(Stars.THREE_STAR, Stars.fromString("3"));
        assertEquals(Stars.FOUR_STAR, Stars.fromString("4"));
        assertEquals(Stars.FIVE_STAR, Stars.fromString("5"));
    }

    @Test
    public void starsFromString_invalidInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Stars.fromString("0"));
        assertThrows(IllegalArgumentException.class, () -> Stars.fromString("6"));
        assertThrows(IllegalArgumentException.class, () -> Stars.fromString("abc"));
    }

    @Test
    public void constructor_enumValue_success() {
        AccommodationStars stars = new AccommodationStars(Stars.THREE_STAR);
        assertEquals("3", stars.toString());
    }

    @Test
    public void toString_validValue_success() {
        assertEquals("1", new AccommodationStars("1").toString());
        assertEquals("5", new AccommodationStars("5").toString());
    }

    @Test
    public void equals() {
        AccommodationStars stars = new AccommodationStars("3");

        // same values -> returns true
        assertTrue(stars.equals(new AccommodationStars("3")));

        // same object -> returns true
        assertTrue(stars.equals(stars));

        // null -> returns false
        assertFalse(stars.equals(null));

        // different types -> returns false
        assertFalse(stars.equals(5));

        // different values -> returns false
        assertFalse(stars.equals(new AccommodationStars("4")));
    }

    @Test
    public void hashCode_sameValue_sameHashCode() {
        assertEquals(new AccommodationStars("3").hashCode(), new AccommodationStars("3").hashCode());
    }
}
