import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.lang.model.type.ArrayType;
import sun.awt.image.ImageWatched.Link;

//Using sort() instead of min-priority queue
public class Dijkstra {
  static class Node implements Comparable<Node>{
    public int id;
    public int d;
    public Node parent;

    public Node(int id){
      this.id = id;
    }

    @Override
    public boolean equals(Object other){
      if(this == other) return true;
      else if(!(other instanceof Node)) return false;
      else{
        Node n = (Node) other;
        if(this.id == n.id) return true;
        else return false;
      }
    }

    @Override
    public int hashCode(){
      int result = 17;
      result += 31 * result + id;
      return result;
    }

    @Override
    public int compareTo(Node other){
      return d - other.d;
    }
  }

  public void findShortestPath(Map<Node, List<Node>> adjMap, int[][]w, Node s, List<Node> allNodes){
    initialize(allNodes,s);
    List<Node> copyList = new ArrayList<Node>();
    while(allNodes.size() > 0){
      Collections.sort(allNodes);
      Node u = allNodes.get(0);
      allNodes.remove(0);
      copyList.add(u);
      List<Node> adjList = adjMap.get(u);
      for(Node v: adjList){
        relax(u,v,w);
      }
    }
    printResult(copyList,s);
  }

  public void initialize(List<Node> allNodes, Node s){
    for(Node node : allNodes){
      node.parent = null;
      node.d = Integer.MAX_VALUE;
    }
    s.d =0;
    allNodes.set(s.id, s);
  }

  public void relax(Node u, Node v, int[][]w){
    int dis = u.d + w[u.id][v.id];
    if(v.d > dis){
      v.d = dis;
      v.parent = u;
    }
  }

  public void printResult(List<Node> allNodes, Node s){
    for(Node node : allNodes){
      if(!node.equals(s)){
        if(node.d == Integer.MAX_VALUE){
          System.out.println(node.id + ": " + "No path exists");
        }
        else{
          System.out.print(node.id + ": shortest path is " );
          printPath(node);
          System.out.print(" The distance is " + node.d +"\n");
        }
      }
    }
  }

  public void printPath(Node node){
    if(node.parent != null){
      printPath(node.parent);
    }
    System.out.print(node.id+ " ");
  }

  public static void main(String[] args){
    Node node0 = new Node(0);
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    List<Node> allnodes= new LinkedList<Node>();
    allnodes.add(node0);
    allnodes.add(node1);
    allnodes.add(node2);
    allnodes.add(node3);
    allnodes.add(node4);
    Map<Node, List<Node>> adjMap = new HashMap<Node, List<Node>>();
    adjMap.put(node0, new ArrayList<Node>());
    adjMap.put(node1, new ArrayList<Node>());
    adjMap.put(node2, new ArrayList<Node>());
    adjMap.put(node3, new ArrayList<Node>());
    adjMap.put(node4, new ArrayList<Node>());
    adjMap.get(node0).add(node3);
    adjMap.get(node1).add(node0);
    adjMap.get(node1).add(node4);
    adjMap.get(node2).add(node0);
    adjMap.get(node2).add(node1);
    adjMap.get(node3).add(node4);
    int[][] w= new int[5][5];
    for(int i=0;i<5;i++){
      for(int j=0;j<5;j++){
        w[i][j] = Integer.MAX_VALUE;
      }
    }
    w[0][3] = 1;
    w[1][0] = 2;
    w[1][4] = 2;
    w[2][0] = 10;
    w[2][1] = 5;
    w[3][4] = 1;

    Dijkstra ds = new Dijkstra();
    ds.findShortestPath(adjMap,w,node2,allnodes);

  }
}
