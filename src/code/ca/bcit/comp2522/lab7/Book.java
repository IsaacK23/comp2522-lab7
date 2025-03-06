package ca.bcit.comp2522.lab7;

/**
 * Represents a book.
 * @author Jonny Twist
 * @author Alison Kim
 * @author Isaac Kehler
 * @version 2.0
 */
abstract class Book extends Literature implements Comparable<Book>
{
    private static final int    MIN_YEAR_PUBLISHED     = 1;
    private static final int    CURRENT_YEAR           = 2025;
    private static final String DEFAULT_TITLE          = "Untitled";
    private static final int    DEFAULT_YEAR_PUBLISHED = CURRENT_YEAR;

    private final String title;
    private final int    yearPublished;

    /**
     * Constructs a Book.
     * @param title title of book
     * @param yearPublished year book was published
     */
    Book(final String title,
         final int    yearPublished)
    {
        validateTitle(title);
        validateYearPublished(yearPublished);

        this.title         = title;
        this.yearPublished = yearPublished;
    }

    /**
     * Parameterless constructor for a Book.
     * Assigns default values for all instance variables.
     */
    Book()
    {
        title         = DEFAULT_TITLE;
        yearPublished = DEFAULT_YEAR_PUBLISHED;
    }

    /*
     * Validates that the title is not null or blank.
     * @param title the title of a book.
     */
    private static void validateTitle(final String title)
    {
        if(title == null || title.isBlank())
        {
            throw new IllegalArgumentException("Title cannot be null or blank!");
        }
    }

    /*
     * Validates that the book was not published in the future or in a
     * negative year or year 0.
     * @param yearPublished the year the book was published.
     */
    private static void validateYearPublished(final int yearPublished)
    {
        if(yearPublished < MIN_YEAR_PUBLISHED || yearPublished > CURRENT_YEAR)
        {
            throw new IllegalArgumentException("Year published must be between years " +
                                                       MIN_YEAR_PUBLISHED + " and " + CURRENT_YEAR);
        }
    }

    /**
     * Getter for the year published.
     * @return the year published.
     */
    public final int getYearPublished()
    {
        return yearPublished;
    }

    /**
     * Getter for the title of the book.
     * @return the title of the book.
     */
    public final String getTitle()
    {
        return title;
    }

    /**
     * Compares two Books based off title.
     * @param o the object to be compared.
     * @return the comparison of this title and other title.
     */
    @Override
    public int compareTo(final Book o)
    {
        return this.title.compareToIgnoreCase(o.title);
    }

    /**
     * Custom toString for book objects to print their title
     * and year published.
     * @return a fancy string containing the instance variables.
     */
    @Override
    public String toString()
    {
        final StringBuilder details;
        final String sentence;

        details = new StringBuilder();
        details.append(title);
        details.append(" was published in ");
        details.append(yearPublished);

        sentence = details.toString();
        return sentence;
    }
}
