package edu.brandeis.questions;
import java.util.Scanner;


public class QuestionAnswer {
	
	static Scanner scanner = new Scanner(System.in);
	static String input;
	static boolean again=true;
	
	
	public static void main(String[] args){
		Node head = new QuestionNode("Does it have fur?");
		head.setYes(new AnimalNode("dog"));
		head.setNo(new AnimalNode("dolphin"));
		
		while(again){
			System.out.println("Think if an animal.");
			gameRecursion(head);
		}	
	}
	
	
	/**
	 * Recursive Game loop.
	 * 
	 * @param head - tree or subtree head to read question from and decide choice
	 */
	public static void gameRecursion(Node head){
		System.out.println(head.getQuestion());
		input = scanner.nextLine();
		
		//If user answers yes to a question
		if(input.equals("y")){
			//If the head node (node we are on) has a yes node, then recursively call yes subtree
			//Otherwise, computer has won the game.  Asks if user would like to play again.
			if(head.hasYes()){
				gameRecursion(head.getYes());
			}else{
				playAgain();
			}
		}
		
		//If user answers no to a question
		else if(input.equals("n")){
			//If the head node (node we are on) has a no node, then recursively call on no subtree
			//Otherwise, resets and asks if wants to play again.
			if(head.hasNo()){
				gameRecursion(head.getNo());
			}else{
				rebranch(head);
				playAgain();
			}
			
		}else{
			gameRecursion(head);
		}
	}
	
	
	/**
	 * Method that prompts user whether or not they would like to play again.
	 * Sets a loop boolean to true or false depending on whether they would.
	 */
	public static void playAgain(){
		System.out.println("Would you like to play again?");
		input = scanner.nextLine();
		if(input.equals("y")){
			again = true;
		}else{
			again = false;
		}
	}
	
	
	/**
	 * Branches from this head point, replaces this head with a new question, and then sets the yes and no.
	 * 
	 * @param head
	 */
	public static void rebranch(Node head){
		Node parent = head.getParent();
		boolean nobranch=false;
		if(parent.getNo().equals(head)){
			nobranch = true;
		}
		
		QuestionNode newquest = createNewQuestion(head);
		
		if(nobranch){
			parent.setNo(newquest);
		}else{
			parent.setYes(newquest);
		}
		
		newquest.setNo(head);
		newquest.setYes(createNewAnimal());
		
	}


	/**
	 * Prompts user for a question based on their animal and the last one,
	 * makes a new question node from this.
	 * 
	 * @param head
	 * @return the new question node
	 */
	private static QuestionNode createNewQuestion(Node head) {
		System.out.println("What is a question that would be no for " + head.geAnimal() + " and yes for your animal?");
		input = scanner.nextLine();
		
		QuestionNode newquest = new QuestionNode(input);
		return newquest;
	}
	
	
	/**
	 * Prompts user for what animal they were thinking of
	 * creates new animal
	 * @return
	 */
	public static Node createNewAnimal(){
		System.out.println("What animal were you thinking of?");
		input = scanner.nextLine();
		return new AnimalNode(input);
	}

}
