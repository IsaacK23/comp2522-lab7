package ca.bcit.comp2522.lab7;

@FunctionalInterface
interface BookFilter
{
    boolean filter(Book book);
}
