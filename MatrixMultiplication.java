import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.tree.ExpandVetoException;

public class MatrixMultiplication {
  public void findSequence(List<Integer> dimension){
    int numMatrix = dimension.size() -1;
    Map<Integer, List<Integer>> costMap = new HashMap<Integer, List<Integer>>();
    Map<Integer, List<Integer>> cutMap = new HashMap<Integer, List<Integer>>();
    for(int i=1; i<=numMatrix; i++){
      costMap.put(i, new ArrayList<Integer>());
      costMap.get(i).add(0);
      cutMap.put(i, new ArrayList<Integer>());
      cutMap.get(i).add(i);
    }
    for(int l=2; l<=numMatrix; l++){
      for(int i=1; i<=numMatrix - l +1; i++){
        int minValue = Integer.MAX_VALUE;
        int cut = 0;
        int j = i+l-1;
        for(int k=i; k<j; k++){
          int temp = costMap.get(i).get(k-i) + costMap.get(k+1).get(j-(k+1)) + dimension.get(i-1) * dimension.get(k) * dimension.get(j);
          if(temp < minValue){
            minValue = temp;
            cut = k;
          }
        }
        costMap.get(i).add(minValue);
        cutMap.get(i).add(cut);
      }
    }

    printResults(cutMap,1, numMatrix);

  }

  public void printResults(Map<Integer, List<Integer>> cutMap, int start, int end ){
    if(start == end){
      System.out.print("A" + start);
    }
    else{
      System.out.print("(");
      int cut = cutMap.get(start).get(end - start);
      printResults(cutMap, start, cut);
      printResults(cutMap, cut+1, end);
      System.out.print(")");
    }
  }

  public static void main(String[] args){
    List<Integer> dimension = new ArrayList<Integer>();
    MatrixMultiplication mm = new MatrixMultiplication();
    try{
      BufferedReader nbr = new BufferedReader(new InputStreamReader(System.in));
      String line;
      int argIndex = 1;
      while((line = nbr.readLine())!= null && !line.isEmpty()){
        String[] temp = line.split(" ");
        if(argIndex == 1){
          dimension.add(Integer.parseInt(temp[0]));
          dimension.add(Integer.parseInt(temp[1]));
        }
        else{
          dimension.add(Integer.parseInt(temp[1]));
        }
        argIndex++;
      }
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
    mm.findSequence(dimension);
  }

}
