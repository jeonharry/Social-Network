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

     public void edite(String key, String newKey){
         for(String v : graph.get(key)){
             graph.get(v).remove(key);
             graph.get(v).add(newKey);
         }
         graph.put(newKey,graph.get(key));
         graph.remove(key);
     }

     public ArrayList<String> findSharing(String key){
         Map<String, Integer> mp = new HashMap<>();
         for(String connectionKey : graph.get(key)){
             for(String share : graph.get(connectionKey)){
                 if(!graph.get(key).contains(share) && share.compareTo(key)!=0){
                     if(!mp.containsKey(share))
                         mp.put(share,1);
                     else
                        mp.put(share,mp.get(share)+1);

                 }
             }
         }
         Comparator<Map.Entry<Double,String>> comparator = (p1, p2) -> Double.compare(p2.getKey(), p1.getKey());
         PriorityQueue<Map.Entry<Double,String>> pairs = new PriorityQueue<>(comparator);
         for(Map.Entry<String,Integer> entry : mp.entrySet()){
             int num = graph.get(key).size()+graph.get(entry.getKey()).size() - 2 * entry.getValue();
             double chance = 1;
             if(num!=0){
                 chance = (double) entry.getValue() /num;
             }
             pairs.add(new AbstractMap.SimpleEntry<>(chance,entry.getKey()));
         }
         int cnt = 6;
         ArrayList<String> answer = new ArrayList<>();
         while (cnt>0 && !pairs.isEmpty()){
             answer.add(pairs.poll().getValue());
             cnt--;
         }
         return answer;
     }


}
