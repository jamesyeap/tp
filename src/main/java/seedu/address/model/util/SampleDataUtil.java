package seedu.address.model.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.LessonBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyLessonBook;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static Lesson[] getSampleLessons() {
        Calendar calendar = Calendar.getInstance();

        Date firstLessonDate;
        Date secondLessonDate;
        Date thirdLessonDate;

        calendar.add(Calendar.DATE, 10);
        firstLessonDate = calendar.getTime();

        calendar.add(Calendar.DATE, 10);
        secondLessonDate = calendar.getTime();

        calendar.add(Calendar.DATE, 10);
        thirdLessonDate = calendar.getTime();

        return new Lesson[] {
            Lesson.makeTemporaryLesson("Make up lesson for George", "Geography", firstLessonDate, 2),
            Lesson.makeTemporaryLesson("Trial lesson for Jake", "Biology", secondLessonDate, 2),
            Lesson.makeTemporaryLesson("Make up lesson for Henry", "Physics", thirdLessonDate, 2)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyLessonBook getSampleLessonBook() {
        LessonBook sampleLb = new LessonBook();
        for (Lesson sampleLesson : getSampleLessons()) {
            sampleLb.addLesson(sampleLesson);
        }
        return sampleLb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
