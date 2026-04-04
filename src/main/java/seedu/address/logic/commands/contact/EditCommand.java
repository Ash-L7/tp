package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLOSING_HOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HALAL_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPENING_HOUR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.AccommodationStars;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.ClosingHour;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.FavouriteStatus;
import seedu.address.model.contact.HalalStatus;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.OpeningHour;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.tour.Tour;


/**
 * Edits the details of an existing contact in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the contact identified "
            + "by the index number used in the displayed contact list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] \n"
            + "[" + PREFIX_HALAL_STATUS + "HALAL STATUS (for FnB contacts)]... "
            + "[" + PREFIX_OPENING_HOUR + "OPENING HOUR (for Attraction contacts)] \n"
            + "[" + PREFIX_CLOSING_HOUR + "CLOSING HOUR (for Attraction contacts)] "
            + "[" + PREFIX_STARS + "STARS (for for Accommodations)] "
            + "[" + PREFIX_TAG + "TAG]... \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_CONTACT_SUCCESS = "Edited Contact: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CONTACT = "This contact already exists in the address book.";

    private final Index index;
    private final EditContactDescriptor editContactDescriptor;

    /**
     * @param index of the contact in the filtered contact list to edit
     * @param editContactDescriptor details to edit the contact with
     */
    public EditCommand(Index index, EditContactDescriptor editContactDescriptor) {
        requireNonNull(index);
        requireNonNull(editContactDescriptor);

        this.index = index;
        this.editContactDescriptor = new EditContactDescriptor(editContactDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToEdit = lastShownList.get(index.getZeroBased());
        Contact editedContact = createEditedContact(contactToEdit, editContactDescriptor);

        if (!contactToEdit.isSameContact(editedContact) && model.hasContact(editedContact)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }

        model.setContact(contactToEdit, editedContact);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_EDIT_CONTACT_SUCCESS, Messages.format(editedContact)));
    }

    /**
     * Creates and returns a {@code Contact} with the details of {@code contactToEdit}
     * edited with {@code editContactDescriptor}.
     */
    private static Contact createEditedContact(Contact contactToEdit, EditContactDescriptor editContactDescriptor) {
        assert contactToEdit != null;

        return contactToEdit.edit(editContactDescriptor);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editContactDescriptor.equals(otherEditCommand.editContactDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editContactDescriptor", editContactDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the contact with. Each non-empty field value will replace the
     * corresponding field value of the contact.
     */
    public static class EditContactDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private HalalStatus isHalal;
        private OpeningHour openingHour;
        private ClosingHour closingHour;
        private AccommodationStars stars;
        private Set<Tour> tours;
        private FavouriteStatus isFavourite;


        public EditContactDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditContactDescriptor(EditContactDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setTours(toCopy.tours);
            setFavourite(toCopy.isFavourite);
            setHalal(toCopy.isHalal);
            setOpeningHour(toCopy.openingHour);
            setClosingHour(toCopy.closingHour);
            setStars(toCopy.stars);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, tours, isFavourite,
                    isHalal, openingHour, closingHour, stars);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public Optional<HalalStatus> getHalalStatus() {
            return Optional.ofNullable(this.isHalal);
        }

        public void setHalal(HalalStatus isHalal) {
            this.isHalal = isHalal;
        }

        public Optional<FavouriteStatus> getFavouriteStatus() {
            return Optional.ofNullable(this.isFavourite);
        }

        public void setFavourite(FavouriteStatus isFavourite) {
            this.isFavourite = isFavourite;
        }

        public Optional<OpeningHour> getOpeningHour() {
            return Optional.ofNullable(openingHour);
        }

        public void setOpeningHour(OpeningHour openingHour) {
            this.openingHour = openingHour;
        }

        public Optional<ClosingHour> getClosingHour() {
            return Optional.ofNullable(closingHour);
        }

        public void setClosingHour(ClosingHour closingHour) {
            this.closingHour = closingHour;
        }


        public Optional<AccommodationStars> getStars() {
            return Optional.ofNullable(stars);
        }

        public void setStars(AccommodationStars stars) {
            this.stars = stars;
        }

        /**
         * Sets the tours of the contact being edited.
         * A defensive copy of {@code tours} is used to prevent external modifications.
         * {@code null} indicates that the tours field was not specified in the edit command
         * and the contact's existing tours should remain unchanged.
         *
         * @param tours the new set of tours, or {@code null} if unspecified
         */
        public void setTours(Set<Tour> tours) {
            this.tours = (tours != null) ? new HashSet<>(tours) : null;
        }

        /**
         * Returns the tours of the contact being edited.
         * Returns {@code Optional.empty()} if tours were not specified in the edit command.
         *
         * @return an {@code Optional} containing the new set of tours, or {@code Optional.empty()} if unspecified
         */
        public Optional<Set<Tour>> getTours() {
            return (tours != null) ? Optional.of(Collections.unmodifiableSet(tours)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditContactDescriptor)) {
                return false;
            }

            EditContactDescriptor otherEditContactDescriptor = (EditContactDescriptor) other;
            return Objects.equals(name, otherEditContactDescriptor.name)
                    && Objects.equals(phone, otherEditContactDescriptor.phone)
                    && Objects.equals(email, otherEditContactDescriptor.email)
                    && Objects.equals(address, otherEditContactDescriptor.address)
                    && Objects.equals(tags, otherEditContactDescriptor.tags)
                    && Objects.equals(tours, otherEditContactDescriptor.tours)
                    && Objects.equals(isFavourite, otherEditContactDescriptor.isFavourite)
                    && Objects.equals(isHalal, otherEditContactDescriptor.isHalal)
                    && Objects.equals(openingHour, otherEditContactDescriptor.openingHour)
                    && Objects.equals(closingHour, otherEditContactDescriptor.closingHour)
                    && Objects.equals(stars, otherEditContactDescriptor.stars);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .add("halalStatus", isHalal)
                    .add("openingHour", openingHour)
                    .add("closingHour", closingHour)
                    .add("stars", stars)
                    .add("tours", tours)
                    .add("favouriteStatus", isFavourite)
                    .toString();
        }
    }
}
