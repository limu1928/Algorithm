import java.util.List;
import java.util.Random;

public class BinarySearchTree {
  public treeNode root ;

  //define treeNode class, allow duplicate values
  public class treeNode{
    public int key;
    public treeNode parent;
    public treeNode leftChild;
    public treeNode rightChild;
    public treeNode(int key){
      this.key = key;
    }
  }

  //build the tree
  public treeNode buildBst(List<Integer> list){
     int len;
     Random rnd = new Random();
     while((len = list.size()) > 0){
       int picked = rnd.nextInt(len);
       insert(list.get(picked));
       list.remove(picked);
    }
    return root;
  }

  //insert a new element into tree
   public void insert(int newKey){
    if(root != null) addChild(newKey, root);
    else root = new treeNode(newKey);
   }


  //delete a child in the tree
  public void delete(int key){
    treeNode found = treeSearch(key,root);
    if (found != null){
      if(found.leftChild == null) transplant(found, found.rightChild);
      else if(found.rightChild ==null) transplant(found, found.leftChild);
      else{
        treeNode successor = findSuccessor(key);
        if(successor.parent != found){
         transplant(successor, successor.rightChild);
         successor.rightChild = found.rightChild;
         successor.rightChild.parent = successor;
        }
        successor.leftChild = found.leftChild;
        successor.leftChild.parent = successor;
        transplant(found, successor);
      }
    }
  }

  //find the successor. Successor of x: the node with smallest value that is greater than x.
  public treeNode findSuccessor (int key){
    treeNode found = treeSearch(key,root);
    if(found == null) return null;
    else{
      if(found.rightChild != null){
        return treeMinimum(found.rightChild);
      }
      else{
        treeNode curParent = found.parent;
        while(curParent != null && found == curParent.rightChild){
          found = curParent;
          curParent = curParent.parent;
        }
        return curParent;
      }
    }
  }

  //find the predecessor. Predecessor of x: the node with the largest value that is smaller than x;
  public treeNode findPredecessor(int key){
    treeNode found = treeSearch(key, root);
    if(found == null) return null;
    else{
      if(found.leftChild != null) {
        return treeMaximum(found.leftChild);
      }
      else{
        treeNode curParent = found.parent;
        while(curParent != null && found == curParent.leftChild){
          found = curParent;
          curParent = curParent.parent;
        }
        return curParent;
      }
    }
  }

  //inorder traverse
  public void inOrderTraverse(){
    inOrderTreeWalk(root);
  }

  //preorder traverse
  public void preOrderTraverse(){
    preOrderTreeWalk(root);
  }

  //postorder traverse
  public void postOrderTraverse(){
    postOrderTreeWalk(root);
  }

  //search for a given element
  public treeNode search(int key){
    return treeSearch(key, root);
  }

  //find Minimum
  public treeNode findMinimum(){
    return treeMinimum(root);
  }

  //find Maximum
  public treeNode findMaximum(){
    return treeMaximum(root);
  }

  //helper function to add a child node given parent node;
  private void addChild(int newKey, treeNode parentNode){
    if(parentNode != null){
      if(newKey <= parentNode.key) {
        if(parentNode.leftChild != null){
          addChild(newKey, parentNode.leftChild);
        }
        else{
          treeNode newNode = new treeNode(newKey);
          newNode.parent = parentNode;
          parentNode.leftChild = newNode;
        }
      }
      else{
        if(parentNode.rightChild != null){
          addChild(newKey, parentNode.rightChild);
        }
        else{
          treeNode newNode = new treeNode(newKey);
          newNode.parent = parentNode;
          parentNode.rightChild = newNode;
        }
      }
    }
  }

  //helper function to help transplant a subtree to a node
  private void transplant(treeNode u, treeNode v){
    if(u == root){
      root = v;
    }
    else{
      if(u == u.parent.leftChild)  u.parent.leftChild = v;
      else u.parent.rightChild = v;
      if(v != null) v.parent = u.parent;
    }
  }

  //Three hekper functions to traverse the tree
  private void inOrderTreeWalk(treeNode root){
    if(root != null){
      inOrderTreeWalk(root.leftChild);
      System.out.print(root.key + " ");
      inOrderTreeWalk(root.rightChild);
    }
  }

  private void preOrderTreeWalk(treeNode root){
    if(root != null){
      System.out.print(root.key + " ");
      preOrderTreeWalk(root.leftChild);
      preOrderTreeWalk(root.rightChild);
    }
  }

  private void postOrderTreeWalk(treeNode root){
    if(root != null){
      postOrderTreeWalk(root.leftChild);
      postOrderTreeWalk(root.rightChild);
      System.out.print(root.key + " ");
    }
  }

  // helper function to seach an node with given value. Return null if not found
  private treeNode treeSearch(int key, treeNode curNode){
    if(curNode == null || curNode.key == key) return curNode;
    else{
      if(key < curNode.key) return treeSearch(key, curNode.leftChild);
      else return treeSearch(key,curNode.rightChild);
    }
  }

  // find the minimum in the tree
  private treeNode treeMinimum(treeNode curNode){
    if(curNode == null) return null;
    else{
      treeNode curLeft = curNode.leftChild;
      if(curLeft == null) return curNode;
      else return treeMinimum(curLeft);
    }

  }

  //find the maximum of the tree
  private treeNode treeMaximum(treeNode curNode){
    if(curNode == null) return null;
    else{
      treeNode curRight = curNode.rightChild;
      if(curRight == null) return curNode;
      else return treeMaximum(curRight);
    }

  }










}
