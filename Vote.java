package catsvsdogs;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Dan Harrison
 */
public class Vote {
    
    static Map<Integer, String> keepMap = new TreeMap<>();
    static Map<Integer, String> kickMap = new TreeMap<>();
    static Map<String, Integer> conflictMap = new TreeMap<>();
    static ArrayList<String> mirror = new ArrayList<>();
    static ArrayList<Integer> mirror2 = new ArrayList<>();
    
    public Vote(Integer voter, String keep, String kick) {
        this.setKeepVote(voter, keep);
        this.setKickVote(voter, kick);
    }
    
    public void setKeepVote(Integer voter, String vote) {
        keepMap.put(voter, vote);
    }
    
    public void setKickVote(Integer voter, String vote) {
        kickMap.put(voter, vote);
    }
    
    public static void populateConflictMap(int numVoters) {
        String v = "V";
        for(int i = 1; i <= numVoters; i++) {
            conflictMap.put(v+i, 0);
        }
    }
    
    public static void mirrorConflictKey() {        
        for(Map.Entry<String,Integer> c : conflictMap.entrySet()) {
            String v = c.getKey();
            mirror.add(v);
        }
    }
    
    public static void mirrorConflictValues() {
        for(Map.Entry<String,Integer> c : conflictMap.entrySet()) {
            Integer v = c.getValue();
            mirror2.add(v);
        }
    }
    
    public static void compareVotes() {
        for(int c = 0; c < mirror.size(); c++) {
            String v = mirror.get(c);
            conflictMap.remove(v);
            conflictMap.put(v, 0);
        }
        mirror.clear();
        for(Map.Entry<Integer,String> i : keepMap.entrySet()) {
            String keep = i.getValue();
            for(Map.Entry<Integer,String> j : kickMap.entrySet()) {
                String kick = j.getValue();
                String voter = j.getKey().toString();
                voter = "V"+voter;
                if(keep.equals(kick)) {
                    Integer value = conflictMap.get(voter);
                    value = value + 1;
                    conflictMap.remove(voter);
                    conflictMap.put(voter, value);
                } 
            }
        }
    }
    
    public static void checkConflicts() {
        mirrorConflictValues();
        Map.Entry<String, Integer> check = 
                conflictMap.entrySet().stream().max(Map.Entry.comparingByValue(Integer::compareTo)).get();
        String max = check.toString().substring(1);
        Scanner scan = new Scanner(max).useDelimiter("="); 
        int voterInt = scan.nextInt();
        int test = scan.nextInt();
        scan.close();
        String voter = Integer.toString(voterInt);
        if(test > 0){            
            keepMap.remove(voterInt);
            kickMap.remove(voterInt);
            conflictMap.remove("V" + voter);
            if(conflictMap.size() > 1) {
                for(Integer i : mirror2) {
                    if (test == mirror2.get(i)) {
                        String iString = Integer.toString(i+1);
                        keepMap.remove(i+1);
                        kickMap.remove(i+1);
                        conflictMap.remove("V" + iString);
                    }
                }
            }
            reCompare();
        }
    }
    
    /*public static void checkConflicts() {
        Map.Entry<String, Integer> check = 
                conflictMap.entrySet().stream().max(Map.Entry.comparingByValue(Integer::compareTo)).get();
        String max = check.toString().substring(1);
        Scanner scan = new Scanner(max).useDelimiter("="); 
        int voterInt = scan.nextInt();
        int test = scan.nextInt();
        scan.close();
        String voter = Integer.toString(voterInt);
        if(test > 0){
            catKeepMap.remove(voterInt);
            catKickMap.remove(voterInt);
            dogKeepMap.remove(voterInt);
            dogKickMap.remove(voterInt); 
            conflictMap.remove("V" + voter);
            reCompare();
        }
    }*/
    
    public static int getMapSize() {
        return conflictMap.size();
    }
    
    public static void clearMaps() {
        keepMap.clear();
        kickMap.clear();
        conflictMap.clear();
    }
    
    public static void reCompare() {
        mirrorConflictKey();
        compareVotes();
        checkConflicts();
    }
}