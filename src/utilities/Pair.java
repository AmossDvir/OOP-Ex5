package utilities;

/**
 * a generic class, represents a generic pair.
 * @param <T>: Type of the first element.
 * @param <S>: Type of the first element.
 */
public class Pair<T,S> {

    // Members:
    protected T first;
    protected S second;

    // Constructors:

    /**
     * Constructs a pair by getting 2 elements.
     * @param first: The first element.
     * @param second: The second element.
     */
    public Pair(T first,S second){
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first element.
     * @return : <T> type Element.
     */
    public T getFirst(){
        return this.first;
    }

    /**
     * Returns the second element.
     * @return : <S> type Element.
     */
    public S getSecond(){
        return this.second;
    }


}
