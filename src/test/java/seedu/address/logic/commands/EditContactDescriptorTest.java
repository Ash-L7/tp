package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ACCOMMODATION;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ATTRACTION;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FNB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAVOURITE_STATUS_TRUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HALAL_STATUS_FALSE_FNB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contact.EditCommand.EditContactDescriptor;
import seedu.address.testutil.EditContactDescriptorBuilder;

public class EditContactDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditContactDescriptor descriptorWithSameValues = new EditContactDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditContactDescriptor editedAmy = new EditContactDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditContactDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditContactDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditContactDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditContactDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tours -> returns false
        editedAmy = new EditContactDescriptorBuilder(DESC_AMY)
                .withTours("Tour1")
                .build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different favourite status -> returns false
        editedAmy = new EditContactDescriptorBuilder(DESC_AMY).withFavouriteStatus(VALID_FAVOURITE_STATUS_TRUE).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different halal status -> returns false
        EditContactDescriptor editedFnb = new EditContactDescriptorBuilder(DESC_FNB)
                .withHalalStatus(VALID_HALAL_STATUS_FALSE_FNB).build();
        assertFalse(DESC_FNB.equals(editedFnb));

        // different opening hour -> returns false
        EditContactDescriptor editedAttraction = new EditContactDescriptorBuilder(DESC_ATTRACTION)
                .withOpeningHour("09:30").build();
        assertFalse(DESC_ATTRACTION.equals(editedAttraction));

        // different closing hour -> returns false
        editedAttraction = new EditContactDescriptorBuilder(DESC_ATTRACTION)
                .withClosingHour("23:30").build();
        assertFalse(DESC_ATTRACTION.equals(editedAttraction));

        // different stars -> returns false
        EditContactDescriptor editedAccommodation = new EditContactDescriptorBuilder(DESC_ACCOMMODATION)
                .withStars("3").build();
        assertFalse(DESC_ACCOMMODATION.equals(editedAccommodation));
    }

    @Test
    public void toStringMethod() {
        EditContactDescriptor editContactDescriptor = new EditContactDescriptor();
        String expected = EditContactDescriptor.class.getCanonicalName() + "{name="
                + editContactDescriptor.getName().orElse(null) + ", phone="
                + editContactDescriptor.getPhone().orElse(null) + ", email="
                + editContactDescriptor.getEmail().orElse(null) + ", address="
                + editContactDescriptor.getAddress().orElse(null) + ", tags="
                + editContactDescriptor.getTags().orElse(null) + ", halalStatus="
                + editContactDescriptor.getHalalStatus().orElse(null) + ", openingHour="
                + editContactDescriptor.getOpeningHour().orElse(null) + ", closingHour="
                + editContactDescriptor.getClosingHour().orElse(null) + ", stars="
                + editContactDescriptor.getStars().orElse(null) + ", tours="
                + editContactDescriptor.getTours().orElse(null) + ", favouriteStatus="
                + editContactDescriptor.getFavouriteStatus().orElse(null) + "}";
        assertEquals(expected, editContactDescriptor.toString());
    }
}
