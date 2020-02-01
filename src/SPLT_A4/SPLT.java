package SPLT_A4;

public class SPLT implements SPLT_Interface{
  private BST_Node root;
  private int size;
  
  public SPLT() {
    this.size = 0;
  } 
  
  public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
    return root;
  }

@Override
public void insert(String s) {
	if (empty()) {
		root = new BST_Node(s);
		size++;
	} else {
		root = root.insertNode(s);
		boolean success = root.getJustMade();
		if (success) size++;
	}
}

@Override
public void remove(String s) {
	root = root.containsNode(s);
	if (root.getContains()) {
		BST_Node l = root.left;
		BST_Node r = root.right;
		
		// unhook
		root.left = null;
		root.right = null;

		if (size == 1) {
			// only root
			root = new BST_Node(null);
			size--;
			return;
		}
		// only one branch
		if (l == null || r == null) {
			if (l == null) {
				root = r;
				r.par = null;
				size--;
				return;
			} else {
				root = l;
				l.par = null;
				size--;
				return;
			}
		}
		
		// two branches, split into two part
		l.par = null;
		r.par = null;
		root = l.findMax();
		root.right = r;
		r.par = root;
		size--;
	} else {
		// does not contain, just return --> contains already did the splay
		return;
	}
}

@Override
public String findMin() {
	if (empty()) {
		return null;
	} else {
		root = root.findMin();
		return root.data;
	}
}

@Override
public String findMax() {
	if (empty()) {
		return null;
	} else {
		root = root.findMax();
		return root.data;
	}
}

@Override
public boolean empty() {
	if (size == 0) return true;
	else return false;
}

@Override
public boolean contains(String s) {
	if (empty()) {
		return false;
	}
	root = root.containsNode(s);
	return root.getContains();
}

@Override
public int size() {
	return size;
}

@Override
public int height() {
	if (empty()) return -1;
	return root.getHeight();
}  

}