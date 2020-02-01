package SPLT_A4;

public class SPLT_Playground {
  public static void main(String[] args){
//    genTest1();
//    genTest2();
//    genTest3();
    genTest4();
  }
  
  public static void genTest1(){
    SPLT tree= new SPLT();
    tree.insert("hello");
    printLevelOrder(tree);
    tree.insert("world");
    printLevelOrder(tree);
    tree.insert("my");
    printLevelOrder(tree);
    tree.insert("name");
    printLevelOrder(tree);
    tree.insert("is");
    printLevelOrder(tree);
    tree.insert("blank");
    printLevelOrder(tree);
    tree.remove("hello");
    System.out.println("size is "+tree.size());
    
    printLevelOrder(tree);
  }
  
  public static void genTest2(){
	    SPLT tree= new SPLT();
	    tree.insert("a");
	    tree.insert("b");
	    tree.remove("a");
	    tree.remove("b");
	    printLevelOrder(tree);
	    
	    tree.insert("c");
	    tree.insert("b");
	    tree.insert("a");
	    System.out.println("root: " + tree.getRoot());
	    printLevelOrder(tree);
  }
  
  public static void genTest3(){
	    SPLT tree= new SPLT();
	    tree.insert("a");
	    tree.insert("b");
	    tree.insert("c");
	    System.out.println("min is " + tree.findMin());
	    printLevelOrder(tree);
}
  
  public static void genTest4(){
	    SPLT tree= new SPLT();
	    tree.insert("a");
	    tree.insert("b");
	    tree.insert("c");
	    tree.insert("a");
	    tree.insert("b");
	    tree.insert("c");
	    tree.insert("d");
	    tree.insert("e");
	    tree.insert("f");
	    tree.remove("a");
	    System.out.println("min is " + tree.findMin());
	    System.out.println("max is " + tree.findMax());
	    System.out.println("height is " + tree.height());
	    System.out.println("size is " + tree.size());
	    printLevelOrder(tree);
	    System.out.println("contains 's' is " + tree.contains("s"));
	    
	    tree.remove("b");
	    tree.remove("c");
	    tree.remove("d");
	    tree.remove("e");
	    tree.remove("f");
	    System.out.println("min is " + tree.findMin());
	    System.out.println("max is " + tree.findMax());
	    System.out.println("height is " + tree.height());
	    System.out.println("size is " + tree.size());
	    printLevelOrder(tree);
	    
	    System.out.println("contains 's' is " + tree.contains("s"));
	    
}

    static void printLevelOrder(SPLT tree){ 
    //will print your current tree in Level-Order...Requires a correct height method
    //https://en.wikipedia.org/wiki/Tree_traversal
      int h=tree.getRoot().getHeight();
      for(int i=0;i<=h;i++){
        System.out.print("Level "+i+":");
        printGivenLevel(tree.getRoot(), i);
        System.out.println();
      }
      
    }
    static void printGivenLevel(BST_Node root,int level){
      if(root==null)return;
      if(level==0)System.out.print(root.data+" ");
      else if(level>0){
        printGivenLevel(root.left,level-1);
        printGivenLevel(root.right,level-1);
      }
    }
   static void printInOrder(BST_Node root){
      if(root!=null){
      printInOrder(root.getLeft());
      System.out.print(root.getData()+" ");
      printInOrder(root.getRight());
      }
  }
  
}