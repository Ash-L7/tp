package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.contact.Accommodation;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Attraction;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.FavouriteStatus;
import seedu.address.model.contact.Fnb;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Person;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.tour.Tour;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Contact objects.
 */
public abstract class ContactBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_FAVOURITE_STATUS = "false";

    protected Name name;
    protected Phone phone;
    protected Email email;
    protected Address address;
    protected Set<Tag> tags;
    protected Set<Tour> tours;
    protected FavouriteStatus favouriteStatus;

    /**
     * Creates a {@code ContactBuilder} with the default details.
     */
    public ContactBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        favouriteStatus = new FavouriteStatus(DEFAULT_FAVOURITE_STATUS);
        tags = new HashSet<>();
        tours = new HashSet<>();
    }

    /**
     * Instantiates a ContactBuilder with the data of {@code contactToCopy}.
     */
    public static ContactBuilder fromContact(Contact contactToCopy) {
        if (contactToCopy instanceof Person person) {
            PersonBuilder personBuilder = new PersonBuilder();
            personBuilder.name = person.getName();
            personBuilder.phone = person.getPhone();
            personBuilder.email = person.getEmail();
            personBuilder.address = person.getAddress();
            personBuilder.tags = new HashSet<>(person.getTags());
            personBuilder.tours = new HashSet<>(person.getTours());
            personBuilder.favouriteStatus = person.getFavouriteStatus();
            return personBuilder;
        }

        if (contactToCopy instanceof Fnb fnb) {
            FnbBuilder builder = new FnbBuilder();
            builder.name = fnb.getName();
            builder.phone = fnb.getPhone();
            builder.email = fnb.getEmail();
            builder.address = fnb.getAddress();
            builder.tags = new HashSet<>(fnb.getTags());
            builder.tours = new HashSet<>(fnb.getTours());
            builder.favouriteStatus = fnb.getFavouriteStatus();
            builder.halalStatus = fnb.getHalalStatus();
            return builder;
        }

        if (contactToCopy instanceof Attraction attraction) {
            AttractionBuilder builder = new AttractionBuilder();
            builder.name = attraction.getName();
            builder.phone = attraction.getPhone();
            builder.email = attraction.getEmail();
            builder.address = attraction.getAddress();
            builder.tags = new HashSet<>(attraction.getTags());
            builder.tours = new HashSet<>(attraction.getTours());
            builder.favouriteStatus = attraction.getFavouriteStatus();
            builder.openingHour = attraction.getOpeningHour();
            builder.closingHour = attraction.getClosingHour();
            return builder;
        }

        if (contactToCopy instanceof Accommodation accommodation) {
            AccommodationBuilder builder = new AccommodationBuilder();
            builder.name = accommodation.getName();
            builder.phone = accommodation.getPhone();
            builder.email = accommodation.getEmail();
            builder.address = accommodation.getAddress();
            builder.tags = new HashSet<>(accommodation.getTags());
            builder.tours = new HashSet<>(accommodation.getTours());
            builder.favouriteStatus = accommodation.getFavouriteStatus();
            builder.stars = accommodation.getStars();
            return builder;
        }
        return null;
    }

    /**
     * Sets the {@code Name} of the {@code Contact} that we are building.
     */
    public ContactBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Contact} that we are building.
     */
    public ContactBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code FavouriteStatus} of the {@code Contact} that we are building.
     */
    public ContactBuilder withFavouriteStatus(String favouriteStatus) {
        this.favouriteStatus = new FavouriteStatus(favouriteStatus);
        return this;
    }

    /**
     * Parses the {@code tours} into a {@code Set<Tour>} and set it to the {@code Contact} that we are building.
     */
    public ContactBuilder withTours(String ... tours) {
        this.tours = SampleDataUtil.getTourSet(tours);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Contact} that we are building.
     */
    public ContactBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Contact} that we are building.
     */
    public ContactBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Contact} that we are building.
     */
    public ContactBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Returns an instance of the contact initialised with data.
     */
    public abstract Contact build();
}
