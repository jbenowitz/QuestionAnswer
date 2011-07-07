package edu.brandeis.questions;

public abstract class Node {

	protected Node parent;
	protected Node yes;
	protected Node no;
	
	/** Getter for question String*/
	public abstract String getQuestion();
	
	/** Sets yes child to @param Node*/
	public abstract void setYes(Node yesNode);
	
	/** @return what the yes child is*/
	public abstract Node getYes();
	
	/** Sets the no child to @param Node*/
	public abstract void setNo(Node newno);
	
	/**@returns what the no child is*/
	public abstract Node getNo();
	
	/**@return whether this question node has a yes child*/
	public abstract boolean hasYes();
	
	/**@returns whether this question node has a no child*/
	public abstract boolean hasNo();
	
	/**@returns the Parent of this node*/
	public abstract Node getParent();
}
