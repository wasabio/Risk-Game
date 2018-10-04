package view;

import java.util.Scanner;

import model.utilities.Message;

public class MessageView {
	
	public static Scanner input = new Scanner(System.in);

	public void welcome() {
		System.out.println("*****    Welcome to Risk Game    *****");
		System.out.println();
		System.out.println("Choose an option by entering the number :");
		System.out.println("1 - Play");
		System.out.println("2 - Map Editor");
	}

}
