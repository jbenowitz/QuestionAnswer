package edu.brandeis.questions;
/**
 * Creates a Question node with a parent, yes, and no branch.
 * Also holds the question for the node.
 * @author Jackie
 *
 */
public class QuestionNode extends Node{
	
	private String question;
	
	/** Basic Constructor, sets the question and leaves parent null*/
	public QuestionNode(String question){
		this.question = question;
		this.parent = null;
		this.yes = null;
		this.no = null;
	}
	
	/** Constructor that sets the question and the parent Node */
	public QuestionNode(String question, Node parent){
		this.question = question;
		this.parent = parent;
		this.yes = null;
		this.no = null;
	}
	

	/** @return question for this node */
	@Override
	public String getQuestion() {
		return question;
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

	@Override
	public String geAnimal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getParent() {
		return this.parent;
	}
	
	

}
