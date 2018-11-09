package model.utilities;
/**
 * 
 * The class is for manage the message types of methods
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 *
 */
public class Message 
{

	/**
	 * The method is to showing the error message of the program.
	 * @param message The message of errors.
	 * @return Returning error messages and show to the user.
	 */
	public static String error(String message) 
	{
		return "Error : " + message;
	}
	
}
