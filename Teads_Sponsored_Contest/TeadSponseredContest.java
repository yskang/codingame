import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Node {
    private ArrayList<Node> adjs = new ArrayList<Node>();
    private int name;
    private ArrayList<Integer> removeCandidate = new ArrayList<Integer>();
    
    public Node(int name) {
        this.name = name;
    }
    
    public void addAdj(Node node) {
        adjs.add(node);
    }
    
    public ArrayList<Node> getAdjs() {
        return adjs;
    }
    
    public int getName() {
        return this.name;
    }
    
    public int getAdjsCount() {
        return adjs.size();
    }
    
    public void setRemoveCandidate(int removeCandidate) {
        this.removeCandidate.add(removeCandidate);
    }
    
    public void removeAdj() {
        for (int i = 0 ; i < adjs.size(); i++) {
            for (int j = 0 ; j < removeCandidate.size(); j++) {
                if (adjs.get(i).getName() == removeCandidate.get(j)) {
                    adjs.remove(i);
                    return;
                }
            }
        }
    }
}
 
class Solution {
    public static void main(String args[]) {
        ArrayList<Node> tree = new ArrayList<Node> ();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of adjacency relations
        
        for (int i = 0; i <= n+10; i++) {
            tree.add(new Node(i));
        }
        
        for (int i = 0; i < n; i++) {
            int xi = in.nextInt(); // the ID of a person which is adjacent to yi
            int yi = in.nextInt(); // the ID of a person which is adjacent to xi
            tree.get(xi).addAdj(tree.get(yi));
            tree.get(yi).addAdj(tree.get(xi));
        }
        
        ArrayList<Integer> isolatedNodes = new ArrayList<Integer>();
        
        for (int i = 0; i< tree.size(); i++) {
            if (tree.get(i).getAdjsCount() == 0) {
                isolatedNodes.add(i);
            }
        }
        
        for (int i = isolatedNodes.size()-1 ; i>=0 ; i--) {
            tree.remove((int)isolatedNodes.get(i));
        }

        int index = 0;
        int count = 0;
        ArrayList<Integer> edges = new ArrayList<Integer>();
        ArrayList<Node> edgeAdjs = new ArrayList<Node>();
        
        while(true) {   
            for (Node node : tree) {
                if (node.getAdjsCount() == 1) {
                    edgeAdjs.add(node.getAdjs().get(0));
                    node.getAdjs().get(0).setRemoveCandidate(node.getName());
                    edges.add(index);
                }
                index++;
            }
            
            for (int i = 0; i < edgeAdjs.size(); i++) {
                edgeAdjs.get(i).removeAdj();
            }
            
            for (int i = edges.size() -1; i >= 0 ; i--) {
                tree.remove((int)edges.get(i));
            }
            
            count++;
            
            edges.clear();
            edgeAdjs.clear();
            index = 0;
            
            if (tree.size() <= 1) break;
        }
        
        // The minimal amount of steps required to completely propagate the advertisement
        System.out.println(count);
    }
    
}