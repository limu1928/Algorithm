import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Knapsack {
  public void fillBag(Map<String, int[]> property, int totalWetght){
    Map<Integer, int[]> valueMap = new HashMap<Integer,int[]>();
    Map<Integer, String[]> resultMap = new HashMap<Integer, String[]>();

    List<String> nameList = new ArrayList<String>();
    nameList.add("");
    for(String key : property.keySet()){
      nameList.add(key);
    }
    for(int i=0; i<nameList.size();i++){
      valueMap.put(i,new int[totalWetght+1]);
      resultMap.put(i,new String[totalWetght+1]);
      for(int j=0; j <= totalWetght; j++){
        valueMap.get(i)[j] = 0;
        resultMap.get(i)[j] = "";
      }
    }
    for(int i =1; i<=totalWetght; i++){
      for(int j=1; j<nameList.size();j++){
        String name = nameList.get(j);
        int weight = property.get(name)[0];
        int value = property.get(name)[1];
        if(weight > i){
          valueMap.get(j)[i] = valueMap.get(j-1)[i];
          resultMap.get(j)[i] = "no";
        }
        else{
          int take = valueMap.get(j-1)[i - weight] + value;
          int notTake = valueMap.get(j-1)[i];
          if(take >= notTake){
            valueMap.get(j)[i] = take;
            resultMap.get(j)[i] = "yes";
          }
          else{
            valueMap.get(j)[i] = notTake;
            resultMap.get(j)[i] = "no";
          }
        }
      }
    }

    System.out.println("The maximun value is:" + valueMap.get(nameList.size()-1)[totalWetght]);
    List<String> result = getResult(property,nameList,resultMap,totalWetght);
    for(String str:result){
      System.out.println(str + " ");
    }


  }

  public List<String> getResult (Map<String, int[]> property, List<String> nameList, Map<Integer, String[]> resultMap, int totalWetght){
    List<String> result = new ArrayList<String>();
    int i=nameList.size()-1;
    int j=totalWetght;
    while(i > 0 && j > 0){
      if(resultMap.get(i)[j].equals("yes")){
        String name = nameList.get(i);
        result.add(name);
        i=i-1;
        j=totalWetght - property.get(name)[0];
      }
      else{
        i=i-1;
      }
    }
    return result;
  }

  public static void main(String[] args){
    Knapsack npsk = new Knapsack();
    Map<String, int[]> property = new HashMap<String, int[]>();
    int totalWeight = 0;
    String line;
    int argIndex = 1;
    try {
      BufferedReader nbr = new BufferedReader(new InputStreamReader(System.in));
      while((line = nbr.readLine())!=null && !line.isEmpty()){
        if (argIndex == 1){
          totalWeight = Integer.parseInt(line.trim());
          argIndex ++;
        }
        else{
          String[] input = line.split(" ");
          property.put(input[0], new int[2]);
          property.get(input[0])[0] = Integer.parseInt(input[1]);
          property.get(input[0])[1] = Integer.parseInt(input[2]);
        }
      }
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
    npsk.fillBag(property,totalWeight);
  }
}
