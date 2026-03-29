package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class ClosingHourTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClosingHour(null));
    }

    @Test
    public void constructor_invalidClosingHour_throwsIllegalArgumentException() {
        String invalidClosingHour = "";
        assertThrows(IllegalArgumentException.class, () -> new ClosingHour(invalidClosingHour));
    }

    @Test
    public void isValidClosingHour() {
        // null closing hour
        assertThrows(NullPointerException.class, () -> ClosingHour.isValidClosingHour(null));

        // invalid closing hours
        assertFalse(ClosingHour.isValidClosingHour("")); // empty string
        assertFalse(ClosingHour.isValidClosingHour(" ")); // spaces only
        assertFalse(ClosingHour.isValidClosingHour("7:00")); // missing leading zero
        assertFalse(ClosingHour.isValidClosingHour("07:0")); // invalid minutes
        assertFalse(ClosingHour.isValidClosingHour("24:00")); // invalid hour
        assertFalse(ClosingHour.isValidClosingHour("12:60")); // invalid minute
        assertFalse(ClosingHour.isValidClosingHour("ab:cd"));
        assertFalse(ClosingHour.isValidClosingHour("22.00"));

        // valid closing hours
        assertTrue(ClosingHour.isValidClosingHour("00:00"));
        assertTrue(ClosingHour.isValidClosingHour("22:00"));
        assertTrue(ClosingHour.isValidClosingHour("12:34"));
        assertTrue(ClosingHour.isValidClosingHour("23:59"));
    }

    @Test
    public void getClosingHour_validValue_success() {
        ClosingHour closingHour = new ClosingHour("22:00");
        assertEquals(LocalTime.of(22, 0), closingHour.getClosingHour());
    }

    @Test
    public void toString_validValue_success() {
        assertEquals("22:00", new ClosingHour("22:00").toString());
        assertEquals("23:59", new ClosingHour("23:59").toString());
    }

    @Test
    public void equals() {
        ClosingHour closingHour = new ClosingHour("22:00");

        // same values -> returns true
        assertTrue(closingHour.equals(new ClosingHour("22:00")));

        // same object -> returns true
        assertTrue(closingHour.equals(closingHour));

        // null -> returns false
        assertFalse(closingHour.equals(null));

        // different types -> returns false
        assertFalse(closingHour.equals(5));

        // different values -> returns false
        assertFalse(closingHour.equals(new ClosingHour("21:00")));
    }

    @Test
    public void hashCode_sameValue_sameHashCode() {
        assertEquals(new ClosingHour("22:00").hashCode(), new ClosingHour("22:00").hashCode());
    }
}
