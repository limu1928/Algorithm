import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LongestCommonString {
  public void findLCS(String s1, String s2) {
    int len1 = s1.length();
    int len2 = s2.length();
    int[][] lenMatrix = new int[len1+1][len2+1];
    String[][] dirMatrix = new String[len1+1][len2+1];
    for (int i = 0; i < len1 + 1; i++) {
      for (int j = 0; j < len2 + 1; j++) {
        lenMatrix[i][j] = 0;
      }
    }
    for (int i = 0; i < len1; i++) {
      for (int j = 0; j < len2; j++) {
        int index1 = i+1;
        int index2 = j+1;
        if(s1.charAt(i) == s2.charAt(j)){
          lenMatrix[index1][index2] = lenMatrix[i][j] + 1;
          dirMatrix[index1][index2] = "d";
        }
        else{
          if(lenMatrix[i][index2] >= lenMatrix[index1][j]){
            lenMatrix[index1][index2] = lenMatrix[i][index2];
            dirMatrix[index1][index2] = "u";
          }
          else{
            lenMatrix[index1][index2] = lenMatrix[index1][j];
            dirMatrix[index1][index2] = "l";
          }
        }
      }
    }
    System.out.println("The length of the longest common String is " + lenMatrix[len1][len2]);
    System.out.println(getResult(dirMatrix, s1, s2, len1, len2));
  }

  public String getResult(String[][] dirMatrix, String s1, String s2, int i, int j){
    if(i==0 || j ==0){
      return "";
    }
    String result="";
    String dir = dirMatrix[i][j];
    if(dir.equals("d")){
      result += getResult(dirMatrix,s1,s2,i-1,j-1);
      result += s1.substring(i-1,i);
    }
    else if(dir.equals("u")){
      result += getResult(dirMatrix,s1,s2,i-1,j);
    }
    else{
      result += getResult(dirMatrix,s1,s2,i,j-1);
    }

    return result;
  }

  public static void main(String[] args){
    String s1="";
    String s2 = "";
    LongestCommonString lcs = new LongestCommonString();
    try{
      BufferedReader nbr = new BufferedReader(new InputStreamReader(System.in));
      s1 = nbr.readLine();
      s2 = nbr.readLine();
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
    lcs.findLCS(s1, s2);
  }
}
