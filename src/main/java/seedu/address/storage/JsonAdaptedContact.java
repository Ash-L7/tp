package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
import seedu.address.model.contact.Person;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.tour.Tour;

/**
 * Jackson-friendly version of {@link Contact}.
 */
class JsonAdaptedContact {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Contact's %s field is missing!";
    public static final String INVALID_FIELD_MESSAGE_FORMAT = "Contact's %s field is invalid!";
    public static final String IRRELEVANT_FIELD_MESSAGE_FORMAT = "Contact of type %s should not have field: %s";

    private final String type;
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String halalStatus;
    private final String openingHour;
    private final String closingHour;
    private final String stars;
    private final String favouriteStatus;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedTour> tours = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedContact} with the given contact details.
     */
    @JsonCreator
    public JsonAdaptedContact(@JsonProperty("type") String type,
                              @JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags,
                              @JsonProperty("tours") List<JsonAdaptedTour> tours,
                              @JsonProperty("halalStatus") String halalStatus,
                              @JsonProperty("openingHour") String openingHour,
                              @JsonProperty("closingHour") String closingHour,
                              @JsonProperty("stars") String stars,
                              @JsonProperty("favouriteStatus") String favouriteStatus) {
        this.type = type;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (tours != null) {
            this.tours.addAll(tours);
        }
        this.halalStatus = halalStatus;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.stars = stars;
        this.favouriteStatus = favouriteStatus;
    }

    /**
     * Converts a given {@code Contact} into this class for Jackson use.
     */
    public JsonAdaptedContact(Contact source) {
        type = source.getClass().getSimpleName();
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        tours.addAll(source.getTours().stream()
                .map(JsonAdaptedTour::new)
                .collect(Collectors.toList()));
        favouriteStatus = String.valueOf(source.getFavouriteStatus().isFavourite);

        String halalStatus = null;
        String openingHour = null;
        String closingHour = null;
        String stars = null;
        if (source instanceof Fnb fnb) {
            halalStatus = String.valueOf(fnb.getHalalStatus().isHalal);
        }
        if (source instanceof Attraction attraction) {
            openingHour = attraction.getOpeningHour().toString();
            closingHour = attraction.getClosingHour().toString();
        }
        if (source instanceof Accommodation accommodation) {
            stars = accommodation.getStars().toString();
        }

        this.halalStatus = halalStatus;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.stars = stars;
    }

    /**
     * Converts this Jackson-friendly adapted contact object into the model's {@code Contact} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
     */
    public Contact toModelType() throws IllegalValueException {
        final List<Tag> contactTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            contactTags.add(tag.toModelType());
        }
        final List<Tour> contactTours = new ArrayList<>();
        for (JsonAdaptedTour tour : tours) {
            contactTours.add(tour.toModelType());
        }
        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Contact.class.getSimpleName()));
        }
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(contactTags);

        final Set<Tour> modelTours = new HashSet<>(contactTours);

        final FavouriteStatus modelFavouriteStatus;
        if (favouriteStatus == null) {
            modelFavouriteStatus = new FavouriteStatus("false");
        } else {
            if (!FavouriteStatus.isValidFavouriteStatus(favouriteStatus)) {
                throw new IllegalValueException(FavouriteStatus.MESSAGE_CONSTRAINTS);
            }
            modelFavouriteStatus = new FavouriteStatus(favouriteStatus);
        }

        if (type.equals(Person.class.getSimpleName())) {
            if (halalStatus != null) {
                throw new IllegalValueException(String.format(
                        IRRELEVANT_FIELD_MESSAGE_FORMAT, type, HalalStatus.class.getSimpleName()));
            }
            if (openingHour != null) {
                throw new IllegalValueException(String.format(
                        IRRELEVANT_FIELD_MESSAGE_FORMAT, type, OpeningHour.class.getSimpleName()));
            }
            if (closingHour != null) {
                throw new IllegalValueException(String.format(
                        IRRELEVANT_FIELD_MESSAGE_FORMAT, type, ClosingHour.class.getSimpleName()));
            }
            if (stars != null) {
                throw new IllegalValueException(String.format(
                        IRRELEVANT_FIELD_MESSAGE_FORMAT, type, AccommodationStars.class.getSimpleName()));
            }
            return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelTours,
                    modelFavouriteStatus);
        }

        if (type.equals(Fnb.class.getSimpleName())) {
            if (openingHour != null) {
                throw new IllegalValueException(String.format(
                        IRRELEVANT_FIELD_MESSAGE_FORMAT, type, OpeningHour.class.getSimpleName()));
            }
            if (closingHour != null) {
                throw new IllegalValueException(String.format(
                        IRRELEVANT_FIELD_MESSAGE_FORMAT, type, ClosingHour.class.getSimpleName()));
            }
            if (stars != null) {
                throw new IllegalValueException(String.format(
                        IRRELEVANT_FIELD_MESSAGE_FORMAT, type, AccommodationStars.class.getSimpleName()));
            }
            if (halalStatus == null) {
                throw new IllegalValueException(String.format(
                        MISSING_FIELD_MESSAGE_FORMAT, HalalStatus.class.getSimpleName()));
            }
            if (!HalalStatus.isValidHalalStatus(halalStatus)) {
                throw new IllegalValueException(HalalStatus.MESSAGE_CONSTRAINTS);
            }
            final HalalStatus modelHalalStatus = new HalalStatus(halalStatus);
            return new Fnb(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelHalalStatus, modelTours,
                    modelFavouriteStatus);
        }

        if (type.equals(Attraction.class.getSimpleName())) {
            if (halalStatus != null) {
                throw new IllegalValueException(String.format(
                        IRRELEVANT_FIELD_MESSAGE_FORMAT, type, HalalStatus.class.getSimpleName()));
            }
            if (stars != null) {
                throw new IllegalValueException(String.format(
                        IRRELEVANT_FIELD_MESSAGE_FORMAT, type, AccommodationStars.class.getSimpleName()));
            }
            if (openingHour == null) {
                throw new IllegalValueException(String.format(
                        MISSING_FIELD_MESSAGE_FORMAT, OpeningHour.class.getSimpleName()));
            }
            if (!OpeningHour.isValidOpeningHour(openingHour)) {
                throw new IllegalValueException(OpeningHour.MESSAGE_CONSTRAINTS);
            }

            if (closingHour == null) {
                throw new IllegalValueException(String.format(
                        MISSING_FIELD_MESSAGE_FORMAT, ClosingHour.class.getSimpleName()));
            }
            if (!ClosingHour.isValidClosingHour(closingHour)) {
                throw new IllegalValueException(ClosingHour.MESSAGE_CONSTRAINTS);
            }

            final OpeningHour modelOpeningHour = new OpeningHour(openingHour);
            final ClosingHour modelClosingHour = new ClosingHour(closingHour);

            return new Attraction(modelName, modelPhone, modelEmail, modelAddress, modelTags,
                    modelOpeningHour, modelClosingHour, modelTours, modelFavouriteStatus);
        }

        if (type.equals(Accommodation.class.getSimpleName())) {
            if (halalStatus != null) {
                throw new IllegalValueException(String.format(
                        IRRELEVANT_FIELD_MESSAGE_FORMAT, type, HalalStatus.class.getSimpleName()));
            }
            if (openingHour != null) {
                throw new IllegalValueException(String.format(
                        IRRELEVANT_FIELD_MESSAGE_FORMAT, type, OpeningHour.class.getSimpleName()));
            }
            if (closingHour != null) {
                throw new IllegalValueException(String.format(
                        IRRELEVANT_FIELD_MESSAGE_FORMAT, type, ClosingHour.class.getSimpleName()));
            }
            if (stars == null) {
                throw new IllegalValueException(String.format(
                        MISSING_FIELD_MESSAGE_FORMAT, AccommodationStars.class.getSimpleName()));
            }
            if (!AccommodationStars.isValidAccommodationStars(stars)) {
                throw new IllegalValueException(AccommodationStars.MESSAGE_CONSTRAINTS);
            }

            final AccommodationStars modelStars = new AccommodationStars(stars);
            return new Accommodation(modelName, modelPhone, modelEmail, modelAddress, modelTags,
                    modelStars, modelTours, modelFavouriteStatus);
        }

        throw new IllegalValueException(String.format(INVALID_FIELD_MESSAGE_FORMAT, type));
    }

}
