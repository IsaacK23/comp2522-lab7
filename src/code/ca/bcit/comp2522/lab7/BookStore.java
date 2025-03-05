package ca.bcit.comp2522.lab7;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A class that simulates a bookstore holding novels as well as
 * some methods a bookstore may have.
 * @author Jonny Twist
 * @author Aaron Tsang
 * @author Alison Kim
 * @author Isaac Kehler
 * @version 3.0
 */
class BookStore<T extends Literature>
{
    private static final int MINIMUM_YEAR            = 1;
    private static final int PERCENT_MULTIPLIER      = 100;
    private static final int DECADE_UPPER_BOUND      = 10;
    private static final int INITIAL_NOVEL_COUNT     = 0;
    private static final int INITIAL_NOVELS_IN_RANGE = 0;
    private static final int INITIAL_SUBSTRING_COUNT = 0;
    private static final int NO_CHAR                 = 0;
    private static final int INITIAL_NUM_BOOKS_FOUND = 1;
    private static final int INCREMENT_BY_ONE        = 1;
    private static final int DEFAULT_INVALID_YEAR    = 0;
    private static final int NOTHING                 = 0;
    private static final int INITIAL_AVERAGE_LENGTH  = 0;
    private static final int CUTOFF_YEAR             = 1950;

    private final String         storeName;
    private final List<T>        inventory;
    private final Map<String, T> bookMap;
    private final Set<String>    keySet;

    /**
     * Constructs a Bookstore object.
     * @param storeName the name of the bookstore.
     */
    BookStore(final String storeName,
              final List<? extends T> books)
    {
        validateStoreName(storeName);

        bookMap = new HashMap<>();

        this.storeName = storeName;
        inventory      = new ArrayList<>(books);

        fillBookMap(bookMap, inventory);
        keySet = bookMap.keySet();
    }

    /*
     * Ensures the bookstore's name is not null or blank.
     * @param storeName the bookstore's name.
     */
    private static void validateStoreName(final String storeName)
    {
        if(storeName == null || storeName.isBlank())
        {
            throw new IllegalArgumentException(("Store name cannot be null or blank!"));
        }
    }

    /*
     * Fills the Map novelsMap with all the data inside novels.
     * @param novelsMap the Map to be filled with data.
     * @param novels contains the data to be put into novelsMap.
     */
    private static <T extends Literature> void fillBookMap(final Map<String, T> novelsMap,
                                                           final List<T>        inventory)
    {
        //todo changed this to lambda with forEach

        final Consumer<T> addToMap;
        addToMap = (book)->
        {
            if(book != null)
            {
                final String title;
                title = book.getTitle();
                novelsMap.put(title, book);
            }
        };

        inventory.forEach(addToMap);
    }

    /**
     * Adds a book to the bookstore inventory and Hash Map.
     * @param book book to add to bookstore inventory and HashMap
     */
    void addBook(final T book)
    {
        if(book != null)
        {
            inventory.add(book);
            bookMap.put(book.getTitle(), book);
        }
    }

    /**
     * Getter for the store name.
     * @return the store name.
     */
    public String getStoreName()
    {
        return storeName;
    }

    /**
     * Prints all the titles.
     */
    final void printItems()
    {
        //todo changed this to a lambda for a forEach
        final Consumer<T> print;

        print = (book) ->
        {
            if(book != null)
            {
                System.out.println(book.getTitle()); // todo format it "nicely"
            }
        };

        inventory.forEach(print);
    }


    /**
     * Prints all the titles in uppercase.
     */
    final void printAllTitles()
    {
        final Consumer<T> printUpperCase;

        printUpperCase = (book) ->
        {
            if(book != null)
            {
                System.out.println(book.getTitle().toUpperCase());
            }
        };

        inventory.forEach(printUpperCase);
    }

    /**
     * Prints all the titles that contain the specified parameter.
     *
     * @param substring the substring that we check if each title contains.
     */
    final void printBookTitle(final String substring)
    {
        if(substring != null && !substring.isBlank())
        {
            final Consumer<T> checkContainsSubstring;
            checkContainsSubstring = (item) -> {
                final boolean containsSubstring;
                containsSubstring = item.getTitle().toLowerCase()
                        .contains(substring.toLowerCase());
                if(containsSubstring)
                {
                    System.out.println(item.getTitle());
                }
            };

            inventory.forEach(checkContainsSubstring);
        }
    }

    /**
     * Prints all the titles in alphabetical order.
     */
    final void printTitlesInAlphaOrder()
    {
        final List<String> sortedTitles;
        sortedTitles = new ArrayList<>();

        final Consumer<T> addToSortedTitlesList;

        addToSortedTitlesList = (book) ->
        {
            if(book != null)
            {
                sortedTitles.add(book.getTitle());
            }
        };

        inventory.forEach(addToSortedTitlesList);

        sortedTitles.sort(String::compareToIgnoreCase);

        final Consumer<String> print;
        print = (title) -> {
            if(title != null)
            {
                System.out.println(title);
            }
        };

        sortedTitles.forEach(print);
    }

    /**
     * Prints all the titles of novels
     * that were published in a given decade.
     * @param decade a year that we will get the decade of.
     */
    final void printGroupByDecade(final int decade)
    {

        validateYear(decade);

        final Consumer<T> printIfWithinDecade;

        printIfWithinDecade = (book) ->
        {
            if(book != null)
            {
                final int year;
                year = book.getYearPublished();
                if(year >= decade && year < decade + DECADE_UPPER_BOUND)
                {
                    System.out.println(book.getTitle());
                }
            }
        };

        inventory.forEach(printIfWithinDecade);
    }

    /*
     * Validates that a year is not 0 or negative.
     * @param year the year to be validated.
     */
    private static void validateYear(final int year)
    {
        if(year < MINIMUM_YEAR)
        {
            throw new IllegalArgumentException("Year cannot be less than " + MINIMUM_YEAR);
        }
    }

    /*
     * Gets the longest novel title.
     */
    private void getLongest()
    {
        String longestTitle;
        longestTitle = "";

        if(!inventory.isEmpty())
        {
            longestTitle = inventory.getFirst().getTitle();

            //todo come back and see if we can make this not this loop
            for(final T book : inventory)
            {
                if(book != null && book.getTitle().length() > longestTitle.length())
                {
                    longestTitle = book.getTitle();
                }
            }
        }
        System.out.println(longestTitle);


    }

    /**
     * Checks if a book was published in a specified year.
     * @param year the year we will check if a book was published in.
     * @return true if there was a book written in the specified year. Else false.
     */
    final boolean isThereABookWrittenIn(final int year)
    {
        boolean bookWrittenInYear;
        bookWrittenInYear = false;

        //todo check if this can be lambda later
        for (final T book : inventory)
        {
            if (book != null && book.getYearPublished() == year)
            {
                bookWrittenInYear = true;
                break;
            }
        }

        return bookWrittenInYear;
    }

    /**
     * Counts how many books contain a specified string.
     * @param substring the specified string we check for.
     * @return the number of books that contain the string.
     */
    final int howManyBooksContain(final String substring)
    {
        int count;
        boolean containsSubstring;
        count = INITIAL_SUBSTRING_COUNT;

        for(final T book : inventory)
        {
            if(book != null && book.getTitle() != null)
            {
                containsSubstring = book.getTitle().toLowerCase()
                        .contains(substring.toLowerCase());
                if(containsSubstring)
                {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Calculates the percent of Novels which were
     * published between two specified years.
     * @param firstYear the starting year.
     * @param lastYear the ending year.
     * @return an int that represents the percent of novels published
     * between the specified years.
     */
    final int whichPercentWrittenBetween(final int firstYear,
                                         final int lastYear)
    {
        validateYear(firstYear);
        validateYear(lastYear);

        final int percentWrittenBetween;
        int totalValidNovels;
        int novelsBetweenYears;

        totalValidNovels = INITIAL_NOVEL_COUNT;
        novelsBetweenYears = INITIAL_NOVELS_IN_RANGE;

        for(final T book : inventory)
        {
            if (book != null)
            {
                totalValidNovels++;
                if (book.getYearPublished() >= firstYear &&
                    book.getYearPublished() <= lastYear)
                {
                    novelsBetweenYears++;
                }
            }
        }
        percentWrittenBetween = Math.round(((float) novelsBetweenYears
                                            / totalValidNovels) * PERCENT_MULTIPLIER);

        return percentWrittenBetween;
    }

    /**
     * Gets the oldest book.
     * @return the oldest book.
     */
    final T getOldestBook()
    {
        T oldestBook;
        oldestBook = null;

        if(!inventory.isEmpty())
        {
            oldestBook = inventory.getFirst();
            for(final T book : inventory)
            {
                if(book != null && book.getYearPublished() < oldestBook.getYearPublished())
                {
                    oldestBook = book;
                }
            }
        }

        return oldestBook;
    }

    /**
     * Stores all the Novels that have a title of a specified length in an
     * array list before returning the array list.
     * @param desiredTitleLength the title length we are checking for.
     * @return an Arraylist of Novels with titles that match the specified length.
     */
    final List<T> getBooksThisLength(final int desiredTitleLength)
    {
        final List<T> booksCorrectLength;
        final Consumer<T> checkIfDesiredLength;

        booksCorrectLength = new ArrayList<>();

        checkIfDesiredLength = (book) ->
        {
            if (book != null && book.getTitle().length() == desiredTitleLength)
            {
                booksCorrectLength.add(book);
            }
        };

        inventory.forEach(checkIfDesiredLength);

        return booksCorrectLength;
    }

    /**
     * Prints all the titles in the hashMap.
     */
    final void printTitlesFromMap()
    {
        final Iterator<String> keysIterator;
        keysIterator = keySet.iterator();

        while (keysIterator.hasNext())
        {
            final String nextTitle;
            nextTitle = keysIterator.next();
            System.out.println(bookMap.get(nextTitle));
        }
    }

    /**
     * Removes all the novels in the hashmap that contain
     * a specified string in their title.
     * @param substring the string that we check if the titles contain.
     */
    final void removeTitlesThatInclude(final String substring)
    {
        final Iterator<String> keysIterator;
        keysIterator = keySet.iterator();

        while (keysIterator.hasNext())
        {
            final String title;
            title = keysIterator.next().toLowerCase();
            if (title.contains(substring.toLowerCase()))
            {
                keysIterator.remove();
            }
        }
    }

    /**
     * Sorts the books into alphabetical order and prints
     * them all using the Novel toString.
     */
    final void displayBooksInAlphaOrder()
    {
        final List<String> keyList;
        keyList = new ArrayList<>(keySet);
        final Iterator<String> keysIterator;

        Collections.sort(keyList);

        keysIterator = keyList.iterator();

        while (keysIterator.hasNext())
        {
            final String nextTitle;
            nextTitle = keysIterator.next();
            System.out.println(bookMap.get(nextTitle));
        }
    }

    /**
     * Accepts a collection of novels and adds only novels from inventory.
     *
     * @param novelCollection novel collection to fill
     */
    void addNovelsToCollection(final List<? super Novel> novelCollection)
    {
        final Consumer<T> addNovelsToCollection;

        addNovelsToCollection = (book) ->
        {
            if (book instanceof Novel novel)
            {
                novelCollection.add(novel);
            }
        };

        inventory.forEach(addNovelsToCollection);
    }

    /**
     * Accepts a collection of comic books and adds only comics books from inventory.
     *
     * @param comicCollection novel collection to fill
     */
    void addComicsToCollection(final List<? super ComicBook> comicCollection)
    {
        final Consumer<T> addComicsToCollection;

        addComicsToCollection = (book) ->
        {
            if (book instanceof ComicBook comic)
            {
                comicCollection.add(comic);
            }
        };

        inventory.forEach(addComicsToCollection);

    }

    /**
     * Accepts a collection of magazines and adds only magazines from inventory.
     *
     * @param zineCollection magazine collection to fill
     */
    void addMagazinesToCollection(final List<? super Magazine> zineCollection)
    {
        final Consumer<T> addZinesToCollection;

        addZinesToCollection = (book) ->
        {
            if (book instanceof Magazine zine)
            {
                zineCollection.add(zine);
            }
        };

        inventory.forEach(addZinesToCollection);
    }

    /**
     * todo new thing from part B -
     * Prints books filtered by the specified filter
     *
     * @param filter as the filter
     */
    public void printBooks(final Predicate<Literature> filter, final List<Literature> books)
    {
        books.forEach(book -> {
            if(filter.test(book))
            {
                System.out.println(book);
            }
        });
    }

    /**
     * Drives the program.
     * @param args unused.
     */
    public static void main(final String[] args)
    {
        final BookStore<Literature> bookstore;
        final Literature            oldest;
        final List<Literature>      fifteenCharTitles;
        final List<Novel>           novelCollection;
        final List<ComicBook>       comicCollection;
        final List<Magazine>        zineCollection;

        final List<Literature> books = new ArrayList<>();

        novelCollection = new ArrayList<>();
        comicCollection = new ArrayList<>();
        zineCollection = new ArrayList<>();

        final Novel theAdventuresOfAugieMarch;
        final Novel allTheKingsMen;
        final Novel americanPastoral;
        final ComicBook ironMan;
        final Magazine newYorkTimes;
        final Novel androids;

        theAdventuresOfAugieMarch = new Novel("The Adventures of Augie March",
                                              "Saul Bellow",
                                              1953);
        allTheKingsMen            = new Novel("All the King’s Men",
                                              "Robert Penn Warren",
                                              1946);
        americanPastoral          = new Novel("American Pastoral",
                                              "Philip Roth",
                                              1997);
        ironMan                   = new ComicBook("Iron Man",
                                                  1968,
                                                  "Archie Goodwin",
                                                  "Gene Colan");
        newYorkTimes              = new Magazine("The New York Times",
                                                 1896);
        androids                  = new Novel("Do Androids Dream of Electric Sheep?",
                                              "Phillip K. Dick",
                                              1968);

        books.add(ironMan);
        books.add(allTheKingsMen);
        books.add(americanPastoral);
        books.add(androids);

        bookstore = new BookStore<>("Classic Masterpieces", books);

        bookstore.addBook(theAdventuresOfAugieMarch);
        bookstore.addBook(newYorkTimes);

        bookstore.addNovelsToCollection(novelCollection);
        bookstore.addComicsToCollection(comicCollection);
        bookstore.addMagazinesToCollection(zineCollection);

        System.out.println("All Titles Printed:");
        bookstore.printItems();
        System.out.println("\nAll Titles in UPPERCASE:");
        bookstore.printAllTitles();
        System.out.println("\nBook Titles Containing 'the':");
        bookstore.printBookTitle("the");
        System.out.println("\nAll Titles in Alphabetical Order:");
        bookstore.printTitlesInAlphaOrder();
        System.out.println("\nBooks from the 2000s:");
        bookstore.printGroupByDecade(2000);
        System.out.println("\nLongest Book Title:");
        bookstore.getLongest();
        System.out.println("\nIs there a book written in 1950?");
        System.out.println(bookstore.isThereABookWrittenIn(1950));
        System.out.println("\nHow many books contain 'heart'?");
        System.out.println(bookstore.howManyBooksContain("heart"));
        System.out.println("\nPercentage of books written between 1940 and 1950:");
        System.out.println(bookstore.whichPercentWrittenBetween(1940, 1950) + "%");
        System.out.println("\nOldest book:");
        oldest = bookstore.getOldestBook();
        System.out.println(oldest);
        System.out.println("\nBooks with titles 15 characters long:");
        fifteenCharTitles = bookstore.getBooksThisLength(15);
        fifteenCharTitles.forEach(novel -> System.out.println(novel.getTitle()));

        System.out.println("\nAll titles in the Hash Map:");
        bookstore.printTitlesFromMap();

        bookstore.removeTitlesThatInclude("the");

        System.out.println("\nBooks in alphabetical order from Hash Map:");
        bookstore.displayBooksInAlphaOrder();

        System.out.println("\nBookStoreInfo method: ");
        BookStoreInfo.displayInfo(bookstore.getStoreName(), bookstore.inventory.size());

        BookStore<Literature>.NovelStatistics stats = bookstore.new NovelStatistics();

        System.out.println("\nMost Common Publication Year:");
        System.out.println(stats.mostCommonPublicationYear());

        System.out.println("\nAverage Title Length:");
        System.out.println(stats.averageTitleLength());

        //todo make method reference or lambda
        System.out.println("\nAll Novels:");
        for (final Novel novel : novelCollection)
        {
            System.out.println(novel.getTitle());
        }

        System.out.println("\nAll Comic Books:");
        for (final ComicBook comic : comicCollection)
        {
            System.out.println(comic.getTitle());
        }

        System.out.println("\nAll Magazines:");
        for (final Magazine zine : zineCollection)
        {
            System.out.println(zine.getTitle());
        }

        //todo this was part 1 maybe it should sort inventory instead of books
        books.sort((b1, b2) -> b1.getTitle().compareTo(b2.getTitle()));

        System.out.println("\nTitles Sorted by Length:");
        books.forEach(System.out::println);

        //todo new thing from part B / maybe remove magic number in print and other thing
        System.out.printf("\nTitles published before %d:\n", CUTOFF_YEAR);
        //bookstore.printBooks(book -> book.getYearPublished() < 1950, books);
        Predicate<Literature> oldBooks = book -> book.getYearPublished() < CUTOFF_YEAR;
        bookstore.printBooks(oldBooks::test, books);

        System.out.println("\nBefore sort:");
        bookstore.printItems();
        stats.sortByTitle();
        System.out.println("\nAfter sort: ");
        bookstore.printItems();

        System.out.println("\nAverage title length:");
        System.out.println(stats.getAverageTitleLength());

        //todo figure this out (step 5)
//        Supplier<Book> bookSupplier = Book::new;
//
//        Book newBook = bookSupplier.get();
//        System.out.println(newBook);
//
//        Supplier<Novel> novelSupplier = Novel::new;
//        Novel newNovel = novelSupplier.get();
//        System.out.println(newNovel);

    }

    /**
     * Contains methods for printing information about the bookstore, including
     * its name and the number of books in inventory.
     */
    static class BookStoreInfo
    {
        /**
         * Prints bookstore information.
         * @param storeName name of bookstore
         * @param itemCount inventory count
         */
        public static void displayInfo(final String storeName,
                                       final int    itemCount)
        {
            System.out.println("Bookstore Name: " + storeName + "\n# of Items: " + itemCount);
        }
    }

    /**
     * Contains methods for statistical analysis, such as calculating the
     * average title length of novels or finding the most common
     * publication year.
     */
    class NovelStatistics
    {
        /**
         * Calculates and returns the average title length of the books
         * in inventory.
         * @return average title length
         */
        public final double averageTitleLength()
        {
            int totalLength;
            double averageLength;

            totalLength = NO_CHAR;
            averageLength = NO_CHAR;

            if(!inventory.isEmpty())
            {
                for(final T item : inventory)
                {
                    if(item != null)
                    {
                        totalLength += item.getTitle().length();
                    }
                }

                averageLength = (double)totalLength/inventory.size();
            }

            return averageLength;
        }

        /**
         * Calculates and returns the most common publication year of the
         * books in inventory.
         * @return most common publication year
         */
        public final int mostCommonPublicationYear()
        {
            final Map<Integer, Integer> yearCounter;
            int mostBooksPublishedInYear;
            int mostCommonPublishYear;

            yearCounter = new HashMap<>();
            mostBooksPublishedInYear = INITIAL_NOVEL_COUNT;
            mostCommonPublishYear = DEFAULT_INVALID_YEAR;

            // fill the hashMap with data from the inventory
            fillMapFromInventory(yearCounter);

            // find most common publication year
            for (final int yearPublished : yearCounter.keySet())
            {
                int publishedBookCount;
                publishedBookCount = yearCounter.get(yearPublished);

                if (publishedBookCount > mostBooksPublishedInYear)
                {
                    mostBooksPublishedInYear = publishedBookCount;
                    mostCommonPublishYear = yearPublished;
                }
            }

            return mostCommonPublishYear;
        }

        /**
         * Fills a Map with years as the key and the number of books published
         * that year as the value.
         * @param yearCounter the Map to be filled.
         */
        private void fillMapFromInventory(final Map<Integer, Integer> yearCounter)
        {
            final Consumer<T> countNumberOfBooksPerYear;

            countNumberOfBooksPerYear = (book) ->
            {
                if(book != null)
                {
                    //year exists in map already
                    if (yearCounter.get(book.getYearPublished()) != null)
                    {
                        final int n = yearCounter.get(book.getYearPublished()) + INCREMENT_BY_ONE;
                        yearCounter.put(book.getYearPublished(), n);
                    }
                    // year does not exist in map
                    else
                    {
                        yearCounter.put(book.getYearPublished(), INITIAL_NUM_BOOKS_FOUND);
                    }
                }
            };

            inventory.forEach(countNumberOfBooksPerYear);
        }

        /**
         * Sorts a list of books by their title.
         */
        public void sortByTitle()
        {
            inventory.sort(Comparator.comparing(Literature::getTitle));
        }

        /**
         * Gets the average title length of the bookstore's inventory.
         * @return the average title length.
         */
        public double getAverageTitleLength()
        {
            int totalLength;
            totalLength = INITIAL_AVERAGE_LENGTH;

            for(final Literature book : inventory)
            {
                totalLength += book.getTitle().length();
            }
            return(!inventory.isEmpty()) ? (double) totalLength / inventory.size() : NOTHING;
        }

    }
}
