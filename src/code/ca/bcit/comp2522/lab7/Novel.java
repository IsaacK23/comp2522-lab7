package ca.bcit.comp2522.lab7;

/**
 * A class to define what a novel contains and how it should be printed.
 * @author Jonny Twist
 * @author Aaron Tsang
 * @version 1.0
 */
class Novel extends Book
{

    private final String authorName;

    /**
     * Constructor for a novel object.
     * @param title the title of the novel.
     * @param authorName the author of the novel.
     * @param yearPublished the year the novel was published.
     */
    Novel(final String title,
          final String authorName,
          final int    yearPublished)
    {
        super(title, yearPublished);
        validateAuthorName(authorName);

        this.authorName = authorName;
    }

    /*
     * validates that the author's name is not null or blank.
     * @param authorName the name of the author.
     */
    private static void validateAuthorName(final String authorName)
    {
        if(authorName == null || authorName.isBlank())
        {
            throw new IllegalArgumentException("Author name cannot be null or blank!");
        }
    }

    /**
     * Getter for the author's name.
     * @return the author's name.
     */
    public final String getAuthorName()
    {
        return authorName;
    }

    /**
     * Custom toString for novel objects to print their title, author
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
        details.append(" and was written by ");
        details.append(authorName);
        details.append(".");

        sentence = details.toString();
        return sentence;
    }

}
