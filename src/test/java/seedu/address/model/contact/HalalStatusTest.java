package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HalalStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new HalalStatus(null));
    }

    @Test
    public void constructor_invalidHalalStatus_throwsIllegalArgumentException() {
        String invalidHalalStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new HalalStatus(invalidHalalStatus));
    }

    @Test
    public void isValidHalalStatus() {
        // null halal status
        assertThrows(NullPointerException.class, () -> HalalStatus.isValidHalalStatus(null));

        // invalid halal statuses
        assertFalse(HalalStatus.isValidHalalStatus("")); // empty string
        assertFalse(HalalStatus.isValidHalalStatus(" ")); // spaces only
        assertFalse(HalalStatus.isValidHalalStatus("yes"));
        assertFalse(HalalStatus.isValidHalalStatus("no"));
        assertFalse(HalalStatus.isValidHalalStatus("1"));

        // valid halal statuses
        assertTrue(HalalStatus.isValidHalalStatus("true"));
        assertTrue(HalalStatus.isValidHalalStatus("false"));
        assertTrue(HalalStatus.isValidHalalStatus("TRUE"));
        assertTrue(HalalStatus.isValidHalalStatus("FALSE"));
    }

    @Test
    public void toString_validValues_success() {
        assertEquals("Halal", new HalalStatus("true").toString());
        assertEquals("Non-Halal", new HalalStatus("false").toString());
    }

    @Test
    public void equals() {
        HalalStatus halalStatus = new HalalStatus("true");

        // same values -> returns true
        assertTrue(halalStatus.equals(new HalalStatus("true")));

        // same object -> returns true
        assertTrue(halalStatus.equals(halalStatus));

        // null -> returns false
        assertFalse(halalStatus.equals(null));

        // different types -> returns false
        assertFalse(halalStatus.equals(5));

        // different values -> returns false
        assertFalse(halalStatus.equals(new HalalStatus("false")));
    }

    @Test
    public void hashCode_sameValue_sameHashCode() {
        assertEquals(new HalalStatus("true").hashCode(), new HalalStatus("true").hashCode());
        assertEquals(new HalalStatus("false").hashCode(), new HalalStatus("false").hashCode());
    }

    @Test
    public void constructor_uppercaseTrue_expectedToBehaveLikeTrue() {
        HalalStatus halalStatus = new HalalStatus("TRUE");
        assertTrue(halalStatus.isHalal);
    }
}
