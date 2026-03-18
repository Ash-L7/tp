package seedu.address.logic.parser;

import java.util.List;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_TYPE = new Prefix("type/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_ISHALAL = new Prefix("h/");
    public static final Prefix PREFIX_OPENINGHOUR = new Prefix("o/");
    public static final Prefix PREFIX_CLOSINGHOUR = new Prefix("c/");
    public static final Prefix PREFIX_STARS = new Prefix("s/");
    public static final Prefix PREFIX_TOUR = new Prefix("tour/");

    /* Valid types */
    public static final String TYPE_PERSON = "person";
    public static final String TYPE_FNB = "fnb";
    public static final String TYPE_ATTRACTION = "attraction";
    public static final String TYPE_ACCOMMODATION = "accomm";
    public static final List<String> VALID_TYPES = List.of(TYPE_PERSON, TYPE_FNB, TYPE_ATTRACTION, TYPE_ACCOMMODATION);
}
