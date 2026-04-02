package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FAVOURITE_STATUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAVOURITE_STATUS_FALSE;
import static seedu.address.storage.JsonAdaptedContact.INVALID_FIELD_MESSAGE_FORMAT;
import static seedu.address.storage.JsonAdaptedContact.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.AL_AZHAR;
import static seedu.address.testutil.TypicalContacts.BENSON;
import static seedu.address.testutil.TypicalContacts.HOTEL;
import static seedu.address.testutil.TypicalContacts.USS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Accommodation;
import seedu.address.model.contact.AccommodationStars;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Attraction;
import seedu.address.model.contact.ClosingHour;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.FavouriteStatus;
import seedu.address.model.contact.Fnb;
import seedu.address.model.contact.HalalStatus;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.OpeningHour;
import seedu.address.model.contact.Phone;
import seedu.address.model.tour.Tour;

public class JsonAdaptedContactTest {
    private static final String INVALID_TYPE = "Bruh";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_HALAL_STATUS = "yes";
    private static final String INVALID_OPENING_HOUR = "12:";
    private static final String INVALID_CLOSING_HOUR = "nine pm";
    private static final String INVALID_STARS = "6";

    private static final String VALID_TYPE_PERSON = BENSON.getClass().getSimpleName();
    private static final String VALID_NAME_PERSON = BENSON.getName().toString();
    private static final String VALID_PHONE_PERSON = BENSON.getPhone().toString();
    private static final String VALID_EMAIL_PERSON = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS_PERSON = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS_PERSON = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_HALAL_STATUS = String.valueOf(AL_AZHAR.getHalalStatus().isHalal);
    private static final String VALID_OPENING_HOUR = USS.getOpeningHour().toString();
    private static final String VALID_CLOSING_HOUR = USS.getClosingHour().toString();
    private static final String VALID_STARS = HOTEL.getStars().toString();
    private static final String NULL_PARAMETER = null;
    private static final List<JsonAdaptedTour> EMPTY_TOUR = List.of();
    private static final String INVALID_TOUR = "Street Food Tour!";
    private static final String VALID_TOUR = "Street Food Tour";

    @Test
    public void toModelType_validPersonDetails_returnsContact() throws Exception {
        JsonAdaptedContact contact = new JsonAdaptedContact(BENSON);
        assertEquals(BENSON, contact.toModelType());
    }

    @Test
    public void toModelType_validFavouriteStatus_returnsContact() throws Exception {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_TYPE_PERSON, VALID_NAME_PERSON, VALID_PHONE_PERSON,
                VALID_EMAIL_PERSON, VALID_ADDRESS_PERSON, VALID_TAGS_PERSON, EMPTY_TOUR,
                NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, VALID_FAVOURITE_STATUS_FALSE);
        assertEquals(BENSON, contact.toModelType());
    }

    @Test
    public void toModelType_nullFavouriteStatus_returnsContact() throws Exception {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_TYPE_PERSON, VALID_NAME_PERSON, VALID_PHONE_PERSON,
                VALID_EMAIL_PERSON, VALID_ADDRESS_PERSON, VALID_TAGS_PERSON, EMPTY_TOUR,
                NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, null);
        assertEquals(BENSON, contact.toModelType());
    }

    @Test
    public void toModelType_invalidType_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(INVALID_TYPE, VALID_NAME_PERSON, VALID_PHONE_PERSON, VALID_EMAIL_PERSON,
                        VALID_ADDRESS_PERSON, VALID_TAGS_PERSON, EMPTY_TOUR,
                        NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER);
        String expectedMessage = String.format(INVALID_FIELD_MESSAGE_FORMAT, INVALID_TYPE);
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_TYPE_PERSON, INVALID_NAME, VALID_PHONE_PERSON, VALID_EMAIL_PERSON,
                        VALID_ADDRESS_PERSON, VALID_TAGS_PERSON, EMPTY_TOUR,
                        NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_TYPE_PERSON, null, VALID_PHONE_PERSON,
                VALID_EMAIL_PERSON, VALID_ADDRESS_PERSON, VALID_TAGS_PERSON, EMPTY_TOUR,
                NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_TYPE_PERSON, VALID_NAME_PERSON, INVALID_PHONE, VALID_EMAIL_PERSON,
                        VALID_ADDRESS_PERSON, VALID_TAGS_PERSON, EMPTY_TOUR,
                        NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_TYPE_PERSON, VALID_NAME_PERSON, null,
                VALID_EMAIL_PERSON, VALID_ADDRESS_PERSON, VALID_TAGS_PERSON, EMPTY_TOUR,
                NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_TYPE_PERSON, VALID_NAME_PERSON, VALID_PHONE_PERSON, INVALID_EMAIL,
                        VALID_ADDRESS_PERSON, VALID_TAGS_PERSON, EMPTY_TOUR,
                        NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_TYPE_PERSON, VALID_NAME_PERSON, VALID_PHONE_PERSON,
                null, VALID_ADDRESS_PERSON, VALID_TAGS_PERSON, EMPTY_TOUR,
                NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_TYPE_PERSON, VALID_NAME_PERSON, VALID_PHONE_PERSON, VALID_EMAIL_PERSON,
                        INVALID_ADDRESS, VALID_TAGS_PERSON, EMPTY_TOUR,
                        NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_TYPE_PERSON, VALID_NAME_PERSON, VALID_PHONE_PERSON,
                VALID_EMAIL_PERSON,
                null, VALID_TAGS_PERSON, EMPTY_TOUR,
                NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS_PERSON);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_TYPE_PERSON, VALID_NAME_PERSON, VALID_PHONE_PERSON, VALID_EMAIL_PERSON,
                        VALID_ADDRESS_PERSON, invalidTags, EMPTY_TOUR,
                        NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER);
        assertThrows(IllegalValueException.class, contact::toModelType);
    }

    @Test
    public void toModelType_invalidFavouriteStatus_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_TYPE_PERSON, VALID_NAME_PERSON, VALID_PHONE_PERSON,
                VALID_EMAIL_PERSON,
                VALID_EMAIL_PERSON, VALID_TAGS_PERSON, EMPTY_TOUR,
                NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, INVALID_FAVOURITE_STATUS);
        String expectedMessage = FavouriteStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_validFnb_returnsFnb() throws Exception {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(Fnb.class.getSimpleName(),
                        AL_AZHAR.getName().toString(),
                        AL_AZHAR.getPhone().toString(),
                        AL_AZHAR.getEmail().toString(),
                        AL_AZHAR.getAddress().toString(),
                        AL_AZHAR.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        EMPTY_TOUR,
                        VALID_HALAL_STATUS,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        NULL_PARAMETER);
        assertEquals(AL_AZHAR, contact.toModelType());
    }

    @Test
    public void toModelType_fnbMissingHalalStatus_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(Fnb.class.getSimpleName(),
                        AL_AZHAR.getName().toString(),
                        AL_AZHAR.getPhone().toString(),
                        AL_AZHAR.getEmail().toString(),
                        AL_AZHAR.getAddress().toString(),
                        AL_AZHAR.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        EMPTY_TOUR,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        NULL_PARAMETER);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, HalalStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_fnbInvalidHalalStatus_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(Fnb.class.getSimpleName(),
                        AL_AZHAR.getName().toString(),
                        AL_AZHAR.getPhone().toString(),
                        AL_AZHAR.getEmail().toString(),
                        AL_AZHAR.getAddress().toString(),
                        AL_AZHAR.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        EMPTY_TOUR,
                        INVALID_HALAL_STATUS,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        NULL_PARAMETER);

        assertThrows(IllegalValueException.class, HalalStatus.MESSAGE_CONSTRAINTS, contact::toModelType);
    }

    @Test
    public void toModelType_validAttraction_returnsAttraction() throws Exception {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(Attraction.class.getSimpleName(),
                        USS.getName().toString(),
                        USS.getPhone().toString(),
                        USS.getEmail().toString(),
                        USS.getAddress().toString(),
                        USS.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        EMPTY_TOUR,
                        NULL_PARAMETER,
                        VALID_OPENING_HOUR,
                        VALID_CLOSING_HOUR,
                        NULL_PARAMETER,
                        NULL_PARAMETER);
        assertEquals(USS, contact.toModelType());
    }

    @Test
    public void toModelType_attractionMissingOpeningHour_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(Attraction.class.getSimpleName(),
                        USS.getName().toString(),
                        USS.getPhone().toString(),
                        USS.getEmail().toString(),
                        USS.getAddress().toString(),
                        USS.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        EMPTY_TOUR,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        VALID_CLOSING_HOUR,
                        NULL_PARAMETER,
                        NULL_PARAMETER);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, OpeningHour.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_attractionInvalidOpeningHour_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(Attraction.class.getSimpleName(),
                        USS.getName().toString(),
                        USS.getPhone().toString(),
                        USS.getEmail().toString(),
                        USS.getAddress().toString(),
                        USS.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        EMPTY_TOUR,
                        NULL_PARAMETER,
                        INVALID_OPENING_HOUR,
                        VALID_CLOSING_HOUR,
                        NULL_PARAMETER,
                        NULL_PARAMETER);

        assertThrows(IllegalValueException.class, OpeningHour.MESSAGE_CONSTRAINTS, contact::toModelType);
    }

    @Test
    public void toModelType_attractionMissingClosingHour_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(Attraction.class.getSimpleName(),
                        USS.getName().toString(),
                        USS.getPhone().toString(),
                        USS.getEmail().toString(),
                        USS.getAddress().toString(),
                        USS.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        EMPTY_TOUR,
                        NULL_PARAMETER,
                        VALID_OPENING_HOUR,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        NULL_PARAMETER);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClosingHour.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_attractionInvalidClosingHour_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(Attraction.class.getSimpleName(),
                        USS.getName().toString(),
                        USS.getPhone().toString(),
                        USS.getEmail().toString(),
                        USS.getAddress().toString(),
                        USS.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        EMPTY_TOUR,
                        NULL_PARAMETER,
                        VALID_OPENING_HOUR,
                        INVALID_CLOSING_HOUR,
                        NULL_PARAMETER,
                        NULL_PARAMETER);

        assertThrows(IllegalValueException.class, ClosingHour.MESSAGE_CONSTRAINTS, contact::toModelType);
    }

    @Test
    public void toModelType_validAccommodation_returnsAccommodation() throws Exception {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(Accommodation.class.getSimpleName(),
                        HOTEL.getName().toString(),
                        HOTEL.getPhone().toString(),
                        HOTEL.getEmail().toString(),
                        HOTEL.getAddress().toString(),
                        HOTEL.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        EMPTY_TOUR,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        VALID_STARS,
                        NULL_PARAMETER);
        assertEquals(HOTEL, contact.toModelType());
    }

    @Test
    public void toModelType_accommodationMissingStars_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(Accommodation.class.getSimpleName(),
                        HOTEL.getName().toString(),
                        HOTEL.getPhone().toString(),
                        HOTEL.getEmail().toString(),
                        HOTEL.getAddress().toString(),
                        HOTEL.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        EMPTY_TOUR,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        NULL_PARAMETER);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                AccommodationStars.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_accommodationInvalidStars_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(Accommodation.class.getSimpleName(),
                        HOTEL.getName().toString(),
                        HOTEL.getPhone().toString(),
                        HOTEL.getEmail().toString(),
                        HOTEL.getAddress().toString(),
                        HOTEL.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()),
                        EMPTY_TOUR,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        NULL_PARAMETER,
                        INVALID_STARS,
                        NULL_PARAMETER);

        assertThrows(IllegalValueException.class, AccommodationStars.MESSAGE_CONSTRAINTS, contact::toModelType);
    }

    @Test
    public void toModelType_invalidTours_throwsIllegalValueException() {
        List<JsonAdaptedTour> invalidTours = new ArrayList<>();
        invalidTours.add(new JsonAdaptedTour(INVALID_TOUR));
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_TYPE_PERSON, VALID_NAME_PERSON, VALID_PHONE_PERSON, VALID_EMAIL_PERSON,
                        VALID_ADDRESS_PERSON, VALID_TAGS_PERSON, invalidTours,
                        NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER);
        assertThrows(IllegalValueException.class, contact::toModelType);
    }


    @Test
    public void toModelType_validTours_returnsContact() throws Exception {
        List<JsonAdaptedTour> validTours = List.of(new JsonAdaptedTour(VALID_TOUR));
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_TYPE_PERSON, VALID_NAME_PERSON, VALID_PHONE_PERSON, VALID_EMAIL_PERSON,
                        VALID_ADDRESS_PERSON, VALID_TAGS_PERSON, validTours,
                        NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER);
        Contact result = contact.toModelType();
        assertEquals(1, result.getTours().size());
        assertEquals(new Tour(VALID_TOUR), result.getTours().iterator().next());
    }

    @Test
    public void toModelType_multipleTours_returnsContact() throws Exception {
        List<JsonAdaptedTour> multipleTours = List.of(
                new JsonAdaptedTour(VALID_TOUR),
                new JsonAdaptedTour("Halloween Tour")
        );
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_TYPE_PERSON, VALID_NAME_PERSON, VALID_PHONE_PERSON, VALID_EMAIL_PERSON,
                        VALID_ADDRESS_PERSON, VALID_TAGS_PERSON, multipleTours,
                        NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER, NULL_PARAMETER);
        Contact result = contact.toModelType();
        assertEquals(2, result.getTours().size());
    }
}
