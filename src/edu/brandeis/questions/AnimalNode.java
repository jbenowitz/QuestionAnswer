package edu.brandeis.questions;

public class AnimalNode extends Node{
	
	private String animal;
	
	
	/** Basic Constructor, sets the question and leaves parent null*/
	public AnimalNode(String animal){
		this.animal = animal;
		this.parent = null;
		this.yes = null;
		this.no = null;
	}
	
	/** Constructor that sets the question and the parent Node */
	public AnimalNode(String animal, Node parent){
		this.animal = animal;
		this.parent = parent;
		this.yes = null;
		this.no = null;
	}
	

	/** @return question for this node */
	@Override
	public String getQuestion() {
		return "Are you thinking of a " + animal + "?";
	}

	/** Sets the yes node to the given node
	 * Sets the parent of the new Yes node to this*/
	@Override
	public void setYes(Node yesNode) {
		this.yes = yesNode;
		yesNode.parent = this;
	}

	/** Sets the no node to the given node 
	 * Sets the parent of the new No node to this*/
	@Override
	public void setNo(Node newno) {
		this.no = newno;
		newno.parent = this;
	}

	/** Returns the yes node */
	@Override
	public Node getYes() {
		return this.yes;
	}

	/** Returns the no node */
	@Override
	public Node getNo() {
		return this.no;
	}

	/** @return true if has a yes node, otherwise false (null yes node) */
	@Override
	public boolean hasYes() {
		if(this.yes == null){
			return false;
		}
		return true;
	}

	/** @return true if has a no node, otherwise false (null no node) */
	@Override
	public boolean hasNo() {
		if(this.no == null){
			return false;
		}
		return true;
	}

	/**
	 * Gets the animal string set in this node.
	 * 
	 */
	public String getAnimal() {
		return animal;
	}

	/**
	 * Returns the parent of this element (used during inserting nodes into tree)
	 */
	@Override
	public Node getParent() {
		return this.parent;
	}

}
