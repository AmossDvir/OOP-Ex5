package utilities;

public abstract class Pair<T,S> {

    protected T first;
    protected S second;


    public Pair(T first,S second){
        this.first = first;
        this.second = second;
    }
    public T getFirst(){
        return this.first;
    }

    public S getSecond(){
        return this.second;
    }
}
