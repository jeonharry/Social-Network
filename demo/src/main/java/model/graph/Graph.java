package model.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    private final Map<String,ArrayList<String>> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public Map<String, ArrayList<String>> getGraph() {
        return graph;
    }

    public ArrayList<String> values(String key){
        if(getGraph().containsKey(key))
            return graph.get(key);
        return null;
    }

    public void insert(String key){
        insertValue(key, null);
    }
    public void insert(String v, String u){
        insertValue(u,v);
        insertValue(v,u);
    }
     private void insertValue(String key, String value){
        if(!graph.containsKey(key))
            graph.put(key, new ArrayList<>());
        if(value!=null)
            graph.get(key).add(value);
     }
}
