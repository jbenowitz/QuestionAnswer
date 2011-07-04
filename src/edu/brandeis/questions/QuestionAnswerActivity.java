package edu.brandeis.questions;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;

public class QuestionAnswerActivity extends Activity {
	
	TextView tv;
	EditText edittext;
	String input;
	boolean again=true;
	boolean x;
	Node head, newAnimal, newQuestion, headparent;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView) this.findViewById(R.id.outputtext);
        
        head = new QuestionNode("Does it have fur?");
		head.setYes(new AnimalNode("dog"));
		head.setNo(new AnimalNode("dolphin"));
		
		headparent = head;
		
		
		x = false;
		tv.setText(head.getQuestion());
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
	
	
    public String getNextQuestion(){
    	if(input.toLowerCase().equals("y")){
    		if (head.hasYes()) {
				head = head.getYes();
				return head.getQuestion();
			}
    		else{
    			return ("I won! Enter 1 to play again and 0 to quit");
    		}
    	}
    	else if (input.toLowerCase().equals("n")){
    		if(head.hasNo()){
    			head = head.getNo();
    			return head.getQuestion();
    		}
    		else{
    			return ("What is a question that the answer would be yes for your animal, but no for " + head.geAnimal() + "?");
    		}
    	}
    	else if (input.contains("?")){
    		newQuestion = new QuestionNode(input);
    		return ("What was the animal you were thinking of?");
    	}
    	else if(input.equals("1")){
    		head = headparent;
    		return head.getQuestion();
    	}
    	else if(input.equals("0")){
    		head = null;
    		return "Goodbye";
    	}
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
		boolean nobranch=false;
		if(parent.getNo().equals(head)){
			nobranch = true;
		}
		
		
		if(nobranch){
			parent.setNo(newQuestion);
		}else{
			parent.setYes(newQuestion);
		}
		
		newQuestion.setNo(head);
		newQuestion.setYes(newAnimal);
		
	}
  
}