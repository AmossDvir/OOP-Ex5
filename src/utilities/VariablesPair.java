package utilities;

public class VariablesPair extends Pair<String ,Boolean>{
    private String name;
    public VariablesPair(String name,String type,boolean isFinal){
        super(type,isFinal);

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
