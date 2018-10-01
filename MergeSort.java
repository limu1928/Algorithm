import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MergeSort {
  public List<Integer> sort(List<Integer> list){
    int size = list.size();
    if(size == 1){
      return list;
    }
    else{
      List<Integer> subList1 = list.subList(0,size/2);
      List<Integer> subList2 = list.subList(size/2, size);
      subList1 = sort(subList1);
      subList2 = sort(subList2);
      return merge(subList1, subList2);
    }
  }

  public List<Integer> merge(List<Integer> list1, List<Integer>list2){
    int size1 = list1.size();
    int size2 = list2.size();
    int cur1 =0;
    int cur2 = 0;
    List<Integer> mergedList = new ArrayList<Integer>();
    while(cur1 < size1 && cur2 <size2){
      int temp1 = list1.get(cur1);
      int temp2 = list2.get(cur2);
      if(temp1 <= temp2){
        mergedList.add(temp1);
        cur1 ++;
      }
      else {
        mergedList.add(temp2);
        cur2++;
      }
    }
    if(cur1 == size1){
      for(int i= cur2; i<size2; i++){
        mergedList.add(list2.get(i));
      }
    }
    else{
      for(int i = cur1; i<size1; i++){
        mergedList.add(list1.get(i));
      }
    }
    return mergedList;
  }

  public static void main(String[] args){
      MergeSort ms = new MergeSort();
      ArrayList<Integer> list = new ArrayList<Integer>();
      BufferedReader nbr = new BufferedReader(new InputStreamReader(System.in));
      String temp = new String();
      try{
        while((temp = nbr.readLine()) != null && !temp.isEmpty()){
          String[] intArray = temp.split(" ");
          for(String str: intArray){
            list.add(Integer.parseInt(str));
          }
        }
      }
      catch(Exception ex){
        ex.printStackTrace();
      }
      List<Integer> result = ms.sort(list);
      for(int i:result){
        System.out.print(i + " ");
      }
  }
}
