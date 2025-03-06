package ca.bcit.comp2522.lab7;

/**
 * Represents a magazine.
 * @author Jonny Twist
 * @author Alison Kim
 * @author Isaac Kehler
 * @version 2.0
 */
class Magazine extends Book
{
    /**
     * Constructor for a magazine object.
     * @param title the title of the magazine.
     * @param yearPublished the year the magazine was published.
     */
    Magazine(final String title,
             final int    yearPublished)
    {
        super(title, yearPublished);
    }

    /**
     * Parameterless constructor for a Magazine.
     * Assigns default values for all instance variables.
     */
    Magazine()
    {
        super();
    }

    /**
     * Custom toString for magazine objects to print their title, author
     * and year published.
     * @return a fancy string containing the instance variables.
     */
    @Override
    public String toString()
    {
        final StringBuilder details;
        final String sentence;

        details = new StringBuilder();
        details.append(super.toString());
        details.append(".");

        sentence = details.toString();
        return sentence;
    }
}
