import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Randomized QuickSort
public class QuickSort {
    public int partition(List<Integer>list, int p, int r){
      if(r > p){
        int i = p-1;
        int curNum = list.get(r);
        for(int j =p; j<= r - 1; j++){
          if(list.get(j) < curNum){
            swap(list, i+1, j);
            i++;
          }
        }
        swap(list, i+1,r);
        return i+1;
      }
      return 0;
    }

    public void sort(List<Integer> list, int p, int r){
      if(r > p){
        Random rnd = new Random();
        int picked = rnd.nextInt(r-p + 1) + p;
        swap(list, picked, r);
        int partitionPoint = partition(list, p, r);
        sort(list, p,partitionPoint-1);
        sort(list,partitionPoint+1, r);
      }

    }
    public void swap(List<Integer> list, int i, int j){
    int temp = list.get(i);
    list.set(i,list.get(j));
    list.set(j, temp);
    }

  public static void main(String[] args){
    QuickSort qs = new QuickSort();
    List<Integer> list= new ArrayList<Integer>();
    String line;
    try{
      BufferedReader nbr = new BufferedReader(new InputStreamReader(System.in));
      while((line = nbr.readLine()) != null && !line.isEmpty()){
        String[] intArray = line.split(" ");
        for(String str: intArray){
          list.add(Integer.parseInt(str));
        }
      }
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
    qs.sort(list , 0, list.size()-1);
    for(int i: list){
      System.out.print(i + " ");
    }

  }


}
