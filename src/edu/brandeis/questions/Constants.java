package edu.brandeis.questions;

import android.provider.BaseColumns;

public interface Constants extends BaseColumns {

	public static final String TABLE_NAME = "questionanimal";
	
	//Columns in the Question Animal Tree database
	public static final String QUESTION = "question";
	public static final String ANIMAL = "animal";
	public static final String PARENT = "parent id";
	public static final String YESCHILD = "yes child id";
	public static final String NOCHILD = "no child id";
}
