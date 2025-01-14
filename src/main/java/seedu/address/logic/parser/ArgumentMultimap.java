package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_TAG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Stores mapping of prefixes to their respective arguments.
 * Each key may be associated with only one argument value except for tags which can be associated with
 * multiple argument values.
 * Values for a given key are stored in a list, and the insertion ordering is maintained.
 * Keys are unique, but the list of argument values for tags may contain duplicate argument values,
 * i.e. the same argument value can be inserted multiple times for tags.
 */
public class ArgumentMultimap {

    /** Prefixes mapped to their respective arguments**/
    private final Map<Prefix, List<String>> argMultimap = new HashMap<>();

    /**
     * Associates the specified argument value with {@code prefix} key in this map.
     * If the map previously contained a mapping for the key, a ParseException is thrown when the prefix is not a tag.
     *
     * @param prefix   Prefix key with which the specified argument value is to be associated
     * @param argValue Argument value to be associated with the specified prefix key
     */
    public void put(Prefix prefix, String argValue) throws ParseException {
        List<String> argValues = getAllValues(prefix);
        argValues.add(argValue);
        if (!prefix.equals(PREFIX_STUDENT_TAG) && argMultimap.containsKey(prefix)) {
            throw new ParseException(String.format("Duplicate prefix %s is not allowed", prefix.getPrefix()));
        }
        argMultimap.put(prefix, argValues);
    }

    /**
     * Returns the last value of {@code prefix}.
     */
    public Optional<String> getValue(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns all values of {@code prefix}.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     */
    public List<String> getAllValues(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(argMultimap.get(prefix));
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     */
    public String getPreamble() {
        return getValue(new Prefix("", "preamble")).orElse("");
    }
}
