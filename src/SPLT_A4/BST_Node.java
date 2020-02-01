package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node par; //parent...not necessarily required, but can be useful in splay tree
  boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
            //I personally use it to indicate to my SPLT insert whether or not we increment size.
  boolean contains;
  
  BST_Node(String data){ 
    this.data=data;
    this.justMade=true;
  }
  
  BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
    this.data=data;
    this.left=left;
    this.right=right;
    this.par=par;
    this.justMade=true;
    this.contains = true;
  }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- Some example methods that could be helpful ------------------------------------------
  //
  // add the meat of correct implementation logic to them if you wish

  // you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
  // make them take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  /*
  public BST_Node containsNode(String s){ return false; } //note: I personally find it easiest to make this return a Node,(that being the node splayed to root), you are however free to do what you wish.
  public BST_Node insertNode(String s){ return false; } //Really same logic as above note
  public boolean removeNode(String s){ return false; } //I personal do not use the removeNode internal method in my impl since it is rather easily done in SPLT, feel free to try to delegate this out, however we do not "remove" like we do in BST
  public BST_Node findMin(){ return left; } 
  public BST_Node findMax(){ return right; }
  public int getHeight(){ return 0; }

  private void splay(BST_Node toSplay) { return false; } //you could have this return or take in whatever you want..so long as it will do the job internally. As a caller of SPLT functions, I should really have no idea if you are "splaying or not"
                        //I of course, will be checking with tests and by eye to make sure you are indeed splaying
                        //Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!
  */

  
  // copied from example
  public BST_Node containsNode(String s){ //it was me
		if(data.equals(s)) {
			splay(this);
			contains = true;
			return this;
		}
		if(data.compareTo(s)>0){//s lexiconically less than data
			if(left==null) {
				splay(this);
				contains = false;
				return this;
			}
			return left.containsNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null) {
				splay(this);
				contains = false;
				return this;
			}
			return right.containsNode(s);
		}
		splay(this);
		contains = false;
		return this; //shouldn't hit
	}
  
  
	public BST_Node insertNode(String s){
		if(data.compareTo(s)>0){
			if(left==null){
				left=new BST_Node(s);
				left.par = this;
				justMade = true;
				BST_Node returnVal = left;
				splay(left);
				return returnVal;
			}
			return left.insertNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null){
				right=new BST_Node(s);
				right.par = this;
				justMade = true;
				BST_Node returnVal = right;
				splay(right);
				return returnVal;
			}
			return right.insertNode(s);
		}
		splay(this);
		justMade = false;
		return this;//ie we have a duplicate
	}
	
//	public boolean removeNode(String s, boolean contains){ //DIO
//		if (contains == false) {
//			return false;
//		} else {
//			return true;
//		}
//	}
	
	public BST_Node findMin(){
		if(left!=null)return left.findMin();
		BST_Node min = this;
		splay(this);
		return min;
	}
	public BST_Node findMax(){
		if(right!=null)return right.findMax();
		BST_Node max = this;
		splay(this);
		return max;
	}
	public int getHeight(){
		int l=0;
		int r=0;
		if(left!=null)l+=left.getHeight()+1;
		if(right!=null)r+=right.getHeight()+1;
		return Integer.max(l, r);
	}
	public String toString(){
		return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")+",Right: "+((this.right!=null)?right.data:"null");
	}
	
	
	
	// splay
	//you could have this return or take in whatever you want..so long as it will do the job internally. As a caller of SPLT functions, I should really have no idea if you are "splaying or not"
    //I of course, will be checking with tests and by eye to make sure you are indeed splaying
    //Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!
	

    /** function splay **/
    private void splay(BST_Node ing) {
        while (ing.par != null) {
        	BST_Node parent = ing.par;
        	BST_Node grandP = parent.par;
            if (grandP == null) {
                if (ing == parent.left)
                	rotateRight(ing);
                else
                	rotateLeft(ing);                 
            } else {
                if (ing == parent.left) {
                    if (parent == grandP.left) {
                    	rotateRight(parent);
                    	rotateRight(ing);
                    } else {
                    	rotateRight(ing);
                        rotateLeft(ing);
                    }
                } else {
                    if (parent == grandP.left) {
                    	rotateLeft(ing);
                    	rotateRight(ing);
                    } else {
                    	rotateLeft(parent);
                    	rotateLeft(ing);
                    }
                }
            }
        }
    }
	
    
    public void rotateRight(BST_Node child) {
    	BST_Node parent = child.par;
        if ((child == null) || (parent == null) || (parent.left != child) || (child.par != parent))
            throw new RuntimeException("Problem when rotateRght");

        if (parent.par != null) {
            if (parent == parent.par.left)
            	parent.par.left = child;
            else parent.par.right = child;
        }
        if (child.right != null) child.right.par = parent;

        child.par = parent.par;
        parent.par = child;
        parent.left = child.right;
        child.right = parent;
    }


    public void rotateLeft(BST_Node child) {
    	BST_Node parent = child.par;
        if ((child == null) || (parent == null) || (parent.right != child) || (child.par != parent))
            throw new RuntimeException("Problem when rotateLeft");
        if (parent.par != null) {
            if (parent == parent.par.left) parent.par.left = child;
            else parent.par.right = child;
        }
        if (child.left != null) child.left.par = parent;
        
        child.par = parent.par;
        parent.par = child;
        parent.right = child.left;
        child.left = parent;
    }
    
    public boolean getJustMade() {
    	return justMade;
    }
    
    public boolean getContains() {
    	return contains;
    }
  // --- end example methods --------------------------------------

  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  
}