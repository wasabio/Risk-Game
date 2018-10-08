package controller;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import view.SetUpView;
import view.View;
import model.map.Map;

public class GameController {

	public GameController()
	{
		execute();
	}

	
	private void execute() 
	{
		SetUpView setUpView = new SetUpView();
		setUpView.print();
		Map map = new Map();
		map.load(null);
		
	}
	
	
		
	}
	
	//private void 

