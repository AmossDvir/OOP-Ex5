package utilities;

import java.util.List;

public class MethodsPair extends Pair<String, List<String>> {
    public MethodsPair(String methodName,List<String> typesList){
        super(methodName,typesList);
    }
    public List<String> getTypes(){
        return this.getSecond();
    }
    // TODO: DELETE!!!!!!
    public void printMethodsPair(){
        System.out.println(this.getFirst());
        for(String type:this.getTypes()){
            System.out.println(type);
        }
    }
}