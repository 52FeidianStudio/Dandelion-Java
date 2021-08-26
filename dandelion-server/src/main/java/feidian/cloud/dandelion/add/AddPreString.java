package feidian.cloud.dandelion.add;


import java.util.List;

public class AddPreString {
    private String string=" path= ";

    public List<String> addPath(List<String> predicate){
        for (int i = 0; i < predicate.size(); i++) {
            String s = string + predicate.get(1);
            predicate.add(i,s);
        }
        return predicate;
    }
}
