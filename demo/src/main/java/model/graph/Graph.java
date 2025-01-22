package model.graph;

import java.util.*;

public class Graph {
    private final Map<String, Set<String>> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public Map<String, Set<String>> getGraph() {
        return graph;
    }

    public Set<String> values(String key){
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
            graph.put(key, new HashSet<>());
        if(value!=null)
            graph.get(key).add(value);
     }

     public void remove(String key){
        for(String v : graph.get(key)){
            graph.get(v).remove(key);
        }
        graph.remove(key);
     }
     public void remove(String u, String v){
        values(u).remove(v);
        values(v).remove(u);
     }
}
