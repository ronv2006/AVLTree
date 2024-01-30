package AvlTree;


public class AVLTree {

  private AVLNode root;
  private int size;
  
  
  public boolean empty() { // O(1)
    return root==null;
  }
  
  

  public AVLTree(AVLNode root) { // O(1) 
	  this.root=root;
	  if(root != null) {
		  size++;
	  }
	  
  }
  
 /**
   * public String search(int k)
   *
   * Returns the info of an item with key k if it exists in the tree.
   * otherwise, returns null.
   */

//O(log(n)) because we use the function search(AVLNode node,int k)
  public String search(int k) { 
      AVLNode node = search(root, k);
      if(node==null) return null;
      return node.getValue();
  }
//O(log(n)) because we check only once per level 
//so each level we divide the number of numbers
//we need to check by 2
private AVLNode search(AVLNode node, int k) {
      if (node == null || !node.isRealNode())
          return null;

      if (k == node.getKey())
          return node;
      else if (k < node.getKey())
          return search((AVLNode)node.getLeft(), k);
      else
          return search((AVLNode)node.getRight(), k);
  }

  /**
   * public int insert(int k, String i)
   *
   * Inserts an item with key k and info i to the AVL tree.
   * The tree must remain valid, i.e. keep its invariants.
   * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
   * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
   * Returns -1 if an item with key k already exists in the tree.
   */

// O(log(n)) because first we search which is O(log(n)))
//if the key doesn't exist we insert it like into a BST
// which is O(log(n)) cause we go over only 1 number per level
//so we divide the amount of numbers we go over by 2
//for each level
// than we use rebalance(AVLNode node) which is O(log(n)))
// so in conclusion this function is O(log(n))
   public int insert(int k, String i) {
	   if(this.search(k)!=null) {
		   return -1;
	   }
		  AVLNode temp=root;
		 if(this.root == null) {
			 this.root = new AVLNode(k,i);
		 }
		  while((temp.getLeft().isRealNode()&& k<temp.getKey())||(temp.getRight().isRealNode()&& k>temp.getKey())) {
			  if(k<temp.getKey())temp=(AVLNode) temp.getLeft();
			  else temp=(AVLNode) temp.getRight();
		  }
		  
		  
		  AVLNode node = new AVLNode(k,i);
		  node.setParent(temp);
		  if(k<temp.getKey()) temp.setLeft(node);
		  else temp.setRight(node);
		  size++;
		  int rotations =  rebalance(temp);
		  
		  return rotations;
	  
	  
   }
   
   // O(log(n)) because we use only functions that are O(1) 
   // and we only do operations 1 time per level
   // so we divide the amount of number to checks by 2
   // for each level
   public int rebalance(AVLNode node) {
	   AVLNode temp = node;
	   int rotations = 0;
	   while(temp != null) {
		   int balanceFactor = Math.abs(temp.getBF());
		   int lastHeight = temp.getHeight();
		   temp.updateHeight();
		   if(balanceFactor < 2 && lastHeight == temp.getHeight()) {
			   return rotations;
		   }
		   else if(balanceFactor < 2 && lastHeight != temp.getHeight()) {
			   temp = (AVLNode) temp.getParent();
		   }
		   else if(balanceFactor == 2) {
			   AVLNode leftNode = (AVLNode) temp.getLeft();
			   AVLNode rightNode = (AVLNode) temp.getRight();
			   if(temp.getBF() == 2 && leftNode.getBF() == 1) {
				   rotateLeftLeft(temp);
				   rotations++;
			   }
			   else if(temp.getBF() == 2 && leftNode.getBF() == -1) {
				   leftRightRotate(temp);
				   rotations++;
			   }
			   else if(temp.getBF() == -2 && rightNode.getBF() == -1) {
				   rotateRightRight(temp);
				   rotations++;
			   }
			   else if(temp.getBF() == -2 && rightNode.getBF() == 1) {
				   
				   rightLeftRotate(temp);
				   rotations++;
			   }
			   temp = (AVLNode) temp.getParent();
		   }
		   
		   
			   
		   
		   
	   }
	   return rotations;
	   
   }
   
   


   /**
    * public String min()
    *
    * Returns the info of the item with the smallest key in the tree,
    * or null if the tree is empty.
    */
   
 //O(log(n)) because we check only once per level 
 //so each level we divide the number of numbers
 //we need to check by 2
   public String min()
   {
	   if(root==null)return null;
	    AVLNode temp=root;
	    while(temp.getLeft().isRealNode())temp=(AVLNode) temp.getLeft();
	    return temp.getValue();
   }

   /**
    * public String max()
    *
    * Returns the info of the item with the largest key in the tree,
    * or null if the tree is empty.
    */
 //O(log(n)) because we check only once per level 
 //so each level we divide the number of numbers
 //we need to check by 2
   public String max()
   {
	   if(root==null)return null;
	    AVLNode temp=root;
	    while(temp.getRight().isRealNode())temp=(AVLNode) temp.getRight();
	    return temp.getValue();
	   
   }

  /**
   * public int[] keysToArray()
   *
   * Returns a sorted array which contains all keys in the tree,
   * or an empty array if the tree is empty.
   */
  public int[] keysToArray()
  {
	  int[] sortedKeys = new int[size];
      int index = 0;

      if (root != null) {
          keysToArray(root, sortedKeys, index);
      }

      return sortedKeys;
  }
  
  
  
  private int keysToArray(AVLNode node, int[] sortedKeys, int index) {
      if (node.isRealNode()) {
          index = keysToArray((AVLNode) node.getLeft(), sortedKeys, index);
          sortedKeys[index] = node.getKey();
          
          index = keysToArray((AVLNode) node.getRight(), sortedKeys, index + 1);
      }
      return index;
  }
  /**
   * public String[] infoToArray()
   *
   * Returns an array which contains all info in the tree,
   * sorted by their respective keys,
   * or an empty array if the tree is empty.
   */
  public String[] infoToArray()
  {
	  if(this.size==0) return null;
	  String[] infos = new String[size];
	  infoToArray(this.root, infos, 0);
	  return infos;
  }
   public int size()
   {
	   return size; 
   }
   
   private int infoToArray(AVLNode node, String[] infosSortedByKeys, int index) {
	      if (node.isRealNode()) {
	          index = infoToArray((AVLNode) node.getLeft(),infosSortedByKeys, index);
	          infosSortedByKeys[index] = node.getValue();
	          
	          index = infoToArray((AVLNode) node.getRight(), infosSortedByKeys, index + 1);
	      }
	      return index;
	  }
   
  
   public IAVLNode getRoot()
   {
	   return root;
   }
   
   //O(1)
   public void rotateRightRight(AVLNode root) {
		  IAVLNode right = root.getRight();
		  IAVLNode rightSubLeft = right.getLeft();
		  rightSubLeft.setParent(root);
		  right.setParent(root.getParent());
		  if(root.getParent()!= null) {
			  root.getParent().setRight(right);
			  
		  }
		  else
		  {
			  this.root = (AVLNode) right;
		  }
		  root.setRight(rightSubLeft);
		  root.setParent(right);
		  
		  right.setLeft(root);
		  
		  AVLNode left = (AVLNode)(right.getLeft());
		  left.updateHeight();
		  AVLNode rightNode = (AVLNode)right;
		  rightNode.updateHeight();
		  
		  root = (AVLNode)right;
		  
		  
	   }
 //O(1)
	   public void rotateLeftLeft(AVLNode root) {
		      IAVLNode left = root.getLeft();
			  IAVLNode leftSubRight = left.getRight();
			  leftSubRight.setParent(root);
			  left.setParent(root.getParent());
			  if(root.getParent()!= null) {
				  root.getParent().setLeft(left);
				  
			  }
			  else
			  {
				  this.root = (AVLNode) left;
			  }
			  root.setLeft(leftSubRight);
			  root.setParent(left);
			  
			  left.setRight(root);
			  
			  AVLNode right = (AVLNode)(left.getRight());
			  right.updateHeight();
			  AVLNode leftNode = (AVLNode)left;
			  leftNode.updateHeight();
			  
			  root = (AVLNode)left;
		   }
	 //O(1)
	   public void leftRightRotate(AVLNode root) {
		    AVLNode left = (AVLNode) root.getLeft();
		    AVLNode leftSubRight = (AVLNode) left.getRight();
		    
		    root.setLeft(leftSubRight.getRight());
		    leftSubRight.getRight().setParent(root);
		    left.setRight(leftSubRight.getLeft());
		    leftSubRight.getLeft().setParent(left);
		    leftSubRight.setRight(root);
		    leftSubRight.setParent(root.getParent());
		    leftSubRight.setLeft(left);
		    
		    if (root.getParent() == null) {
		        this.root = leftSubRight;
		    } else {
		        if (leftSubRight.getKey() < root.getParent().getKey()) {
		            root.getParent().setLeft(leftSubRight);
		        } else {
		            root.getParent().setRight(leftSubRight);
		        }
		    }
		    
		    root.setParent(leftSubRight);
		    left.setParent(leftSubRight);
		    
		    root.updateHeight();
		    left.updateHeight();
		    leftSubRight.updateHeight();
		    
		    root = leftSubRight;
		}
	 //O(1)
	   public void rightLeftRotate(AVLNode root) {
		   
           AVLNode right = (AVLNode) root.getRight();
           AVLNode rightSubLeft = (AVLNode) right.getLeft();
           
           
           root.setRight(rightSubLeft.getLeft());
           rightSubLeft.getLeft().setParent(root);
           right.setLeft(rightSubLeft.getRight());
           rightSubLeft.getRight().setParent(right);
           rightSubLeft.setLeft(root);
           rightSubLeft.setParent(root.getParent());
           rightSubLeft.setRight(right);
   
           
           if(root.getParent() == null) {
        	   this.root = rightSubLeft;
           }
           else
           {
        	   if(rightSubLeft.getKey() <root.getParent().getKey()) {
        		   root.getParent().setLeft(rightSubLeft);
        	   }
        	   else
        	   {
        		   root.getParent().setRight(rightSubLeft);
        	   }
           }
           
          
           root.setParent(rightSubLeft);
           right.setParent(rightSubLeft);
           
           
           
           root.updateHeight();
           right.updateHeight();
           rightSubLeft.updateHeight();
           
           root = rightSubLeft;
           
       }
}
  
	

