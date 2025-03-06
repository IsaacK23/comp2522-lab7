package ca.bcit.comp2522.lab7;

/**
 * Functional interface to filter Literature.
 *
 * @author Jonny Twist
 * @author Isaac Kehler
 * @version 1.0
 */
@FunctionalInterface
interface BookFilter
{
    boolean filter(Literature book);
}
