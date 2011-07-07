package edu.brandeis.questions;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * The interaction between the user and the game, along with the game loop.
 * 
 * Game loop iterated by user input.
 * 
 * TextField: could offer some bugs, but simple solution to problem
 * 
 * @author Jackie
 *
 */
public class QuestionAnswerActivity extends Activity {
	
	TextView tv;
	EditText edittext;
	String input;
	boolean again=true;
	Node head, newAnimal, newQuestion, root;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView) this.findViewById(R.id.outputtext);
        
        //Create a head/root node.  The starting point is always the same.
        head = new QuestionNode("Does it have fur?");
		head.setYes(new AnimalNode("dog"));
		head.setNo(new AnimalNode("dolphin"));
		root = head;
		
		//Sets up the first question to be viewed
		tv.setText(head.getQuestion());
		
		/**The edittext (user input) set-up
		*It also acts as the gameloop (after a user inputs and enters a string, it finds the next question or 
		*next direction to be viewed in tv).
		*/
		edittext = (EditText) findViewById(R.id.edittext);
		edittext.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                	input = edittext.getText().toString().toLowerCase();
                  edittext.setText(null); 
                  tv.setText(getNextQuestion()); 
                  return true;
                }
                return false;
            }
        });
	
        
    }
	
	/**
	 * the prompt to find the next output (not only a question, could be a direction)
	 * 
	 * @return The string for the output
	 */
    public String getNextQuestion(){
    	//If a user answers y, which signifies yes, the program iterates down the yes subtree, if one exists.
    	// Otherwise, Wins and asks if user wants to play again.
    	if(input.toLowerCase().equals("y")){
    		if (head.hasYes()) {
				head = head.getYes();
				return head.getQuestion();
			}
    		else{
    			return ("I won! Enter 1 to play again and 0 to quit");
    		}
    	}
    	
    	//If a user answers n, which signifies no, does same as yes down no subtree.
    	else if (input.toLowerCase().equals("n")){
    		if(head.hasNo()){
    			head = head.getNo();
    			return head.getQuestion();
    		}
    		else{
    			if(head instanceof AnimalNode){
    				AnimalNode animal = (AnimalNode) head;
    				return ("What is a question that the answer would be yes for your animal, but no for " + animal.getAnimal() + "?");
    			}else{
    				return null;
    			}
    		}
    	}
    	
    	//If the user inputs a question, simplified by a string with a '?' in it, 
    	// this sets up a new question node with the given string input.
    	// Then prompts user for the animal.
    	else if (input.contains("?")){
    		newQuestion = new QuestionNode(input);
    		return ("What was the animal you were thinking of?");
    	}
    	
    	//If user enters a 1, restarts the game
    	else if(input.equals("1")){
    		head = root;
    		return head.getQuestion();
    	}
    	
    	//If the user enters a 0, ends the game.
    	else if(input.equals("0")){
    		finish();
    		return null;
    	}
    	
    	//Otherwise, computer thinks input is an animal.
    	//Rebranches with newAnimal insertion created here, and newQuestion created beforehand.
    	//Prompts user to play again or quit.
    	else{
    		newAnimal= new AnimalNode(input);
    		rebranch();
    		return("Enter 1 to play again and 0 to quit");
    	}
    }
    
    
    
    /**
	 * Branches from this head point, replaces this head with a new question, and then sets the yes and no.
	 * 
	 * @param head
	 */
	public void rebranch(){
		Node parent = head.getParent();
		
		//Checks to see if rebranching on no subtree or yes subtree.
		//Allows for improvement on game.
		if(parent.getNo().equals(head)){
			parent.setNo(newQuestion);
		}else{
			parent.setYes(newQuestion);
		}
		
		newQuestion.setNo(head);
		newQuestion.setYes(newAnimal);
		
	}
  
}