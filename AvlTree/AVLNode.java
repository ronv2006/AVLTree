package AvlTree;

public class AVLNode implements IAVLNode{

	private IAVLNode left;
	private IAVLNode right;
	private IAVLNode parent;
	
	private int height;
	private int key;
	private String value;
	
	public AVLNode(int key, String value) { // O(1) 
		if(key!=-1) {
			this.left=new AVLNode(-1,null);
			this.right=new AVLNode(-1,null);
			this.parent=null;
			this.height=0;
			this.key=key;
			this.value=value;
		}
		else {
			this.left=null;
			this.right=null;
			this.parent=null;
			this.key=key;
			this.height = -1;
		}
	}
	
	public int getKey() { // O(1)	
		return key; 
	}
	public String getValue() {// O(1) 
		return value; 
	}
	public void setLeft(IAVLNode node) { // O(1) 
		this.left = node;
	}
	public IAVLNode getLeft() { // O(1) 
		return left;
	}
	public void setRight(IAVLNode node) // O(1)
	{
		this.right = node;
	}
	public IAVLNode getRight() { // O(1)
		return right; 
	}
	public void setParent(IAVLNode node) { // O(1)
		this.parent = node; 
	}
	public IAVLNode getParent()	{ // O(1)
		return parent; 
	}
	public boolean isRealNode()	{ // O(1)
		return key != -1; 
	}
	public void setHeight(int height){ // O(1)
		this.height = height; 
	}
	public int getHeight(){ // O(1)
		return height; 
	}
	public boolean isLeaf() { // O(1)
		return !this.left.isRealNode()&&!this.right.isRealNode();
	}
	public void updateHeight() { // O(1)
		
		   this.setHeight(Math.max(this.getLeft().getHeight(), this.getRight().getHeight()) + 1) ;
	   }
	
	public int getRealHeight() { // O(1)
		return Math.max(this.getLeft().getHeight(), this.getRight().getHeight() + 1);
	}
	
	public int getBF() { // O(1)
		  return this.getLeft().getHeight() - this.getRight().getHeight();
	  }
	
}

