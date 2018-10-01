import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class RodCut {
  public void cutRod(Map<Integer, Integer> priceMap, int len){
    if(len <= priceMap.size()){
      Map<Integer, Integer> cutPosition = new HashMap<Integer, Integer>();
      Map<Integer, Integer> maxMap = new HashMap<Integer, Integer>();
      maxMap.put(0,0);
      for(int i = 1; i <= len; i++){
        int maxValue = Integer.MIN_VALUE;
        int cut = 0;
        for(int j=1; j<=i; j++) {
          int tempValue = priceMap.get(j) + maxMap.get(i - j);
          if (tempValue > maxValue) {
            maxValue = tempValue;
            cut = j;
          }
        }
        maxMap.put(i, maxValue);
        cutPosition.put(i,cut);
      }
      //to print out the result
      System.out.println("The max value is: " + maxMap.get(len));
      System.out.print("The optimal cut is: ");
      printResult(cutPosition, len);
      System.out.println();
    }

  }

  public void printResult(Map<Integer,Integer> cutPosition, int len){
    if(len != 0){
      int cut = cutPosition.get(len);
      System.out.print(cut + " ");
      printResult(cutPosition, len - cut);
    }
  }

  public static void main(String[] args){
    RodCut rc = new RodCut();
    int len=0;
    Map<Integer, Integer> priceMap = new HashMap<Integer, Integer>();
    try{
      BufferedReader nbr = new BufferedReader(new InputStreamReader(System.in));
      String line;
      String[] pair;
      while((line = nbr.readLine()) != null && !line.isEmpty()){
        if(line.trim().length() == 1) len = Integer.parseInt(line.trim());
        else{
          pair = line.split(" ");
          priceMap.put(Integer.parseInt(pair[0]), Integer.parseInt(pair[1]));
        }
      }
    }
    catch (Exception ex){
      ex.printStackTrace();
    }
    rc.cutRod(priceMap, len);
  }
}
