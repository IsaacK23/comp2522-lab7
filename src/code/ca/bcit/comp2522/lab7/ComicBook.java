package ca.bcit.comp2522.lab7;

/**
 * Represents a comic book, a type of illustrated story.
 * @author Jonny Twist
 * @author Alison Kim
 * @version 1.0
 */
class ComicBook extends Book
{
    private final String author;
    private final String artist;

    /**
     * Constructs a ComicBook object.
     * @param title title of comic book
     * @param yearPublished year comic book was published
     * @param artist comic book artist
     */
    ComicBook(final String title,
              final int    yearPublished,
              final String author,
              final String artist)
    {
        super(title, yearPublished);

        validateCreator(author);
        validateCreator(artist);

        this.author = author;
        this.artist = artist;
    }

    /**
     * Returns the comic book author.
     * @return author
     */
    public final String getAuthor()
    {
        return author;
    }

    /**
     * Returns the comic book artist.
     * @return artist
     */
    public final String getArtist()
    {
        return artist;
    }

    /*
     * Validates that the creator is not null or blank.
     * @param creator
     */
    private static void validateCreator(final String creator)
    {
        if(creator == null || creator.isBlank())
        {
            throw new IllegalArgumentException("Creator cannot be null or blank!");
        }
    }

    @Override
    public String toString()
    {
        final StringBuilder details;
        final String sentence;

        details = new StringBuilder();
        details.append(super.toString());
        details.append(" and was written ");

        if(author.equals(artist))
        {
            details.append("and illustrated by ");
            details.append(author);
        }
        else
        {
            details.append("by ");
            details.append(author);
            details.append(" and illustrated by ");
            details.append(artist);
        }
        details.append(".");

        sentence = details.toString();
        return sentence;
    }
}
