import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//Heap sort with minimum heap
public class HeapSort2 {
  //top-down apprpach
  public void Heapify(List<Integer> list, int i){
    int len = list.size();
    if(i >=0 && i < len){
      int leftIndex = 2 * i + 1;
      int rightIndex = 2 * i + 2;
      int smallest = i;
      if(leftIndex < len) { if(list.get(leftIndex) < list.get(i)) smallest= leftIndex;}
      if(rightIndex < len){if(list.get(rightIndex) < list.get(smallest)) smallest = rightIndex;}
      if(smallest!= i){
        swap(list,smallest,i);
        Heapify(list,smallest);
      }
    }
  }

  //bottom-up approach
  public void buildHeap(List<Integer> list){
    int len = list.size();
    int start = len/2 -1;
    for(int i = start; i>=0; i--){
      Heapify(list,i);
    }
  }

  public Integer extractMin(List<Integer> heap){
    int len = heap.size();
    if(len > 0){
      int result = heap.get(0);
      swap(heap, 0, len-1);
      heap.remove(len-1);
      Heapify(heap, 0);
      return result;
    }
    return null;

  }

  public int[] sort(List<Integer> list){
    buildHeap(list);
    int len = list.size();
    int[] result = new int[len];
    for(int i=0; i<len; i++){
      int temp =  extractMin(list);
      result[i] = temp;
    }
    return result;

  }

  public  void swap(List<Integer> list, int i, int j){
    int temp = list.get(i);
    list.set(i,list.get(j));
    list.set(j, temp);
  }

  public static void main(String[] args){
    HeapSort2 hs2 = new HeapSort2();
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
    int[] result = hs2.sort(list);
    for(int i: result){
      System.out.print(i + " ");
    }


  }
}

