package view;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import model.utilities.StringAnalyzer;


public class SetUpView extends View {

	public int print() 
	{
		System.out.println("Choose the number of players (2-6): ");
		int player_num;
		boolean correctValue;
		
		do {
			player_num = getInteger();
			correctValue = isValueCorrect(player_num, 2, 6);
		}
		while(!correctValue);
		
		return player_num;
	}

	public String selectMap() {
		System.out.println("Select the map file (.map)...");
		JFileChooser jf = new JFileChooser() {
			
		protected JDialog createDialog(Component parent) throws HeadlessException {
			JDialog jDialog = super.createDialog(parent);
			jDialog.setAlwaysOnTop(true);
			return jDialog;		
			}
		};
		
		/*To open in current directory*/
		File workingDirectory = new File(System.getProperty("user.dir") + System.getProperty("file.separator")+ "maps");
		jf.setCurrentDirectory(workingDirectory);
		
		jf.showOpenDialog(null);
		File path = jf.getSelectedFile();

		if(StringAnalyzer.checkMapType(path))
			return path.getAbsolutePath();
		else
			selectMap();
		
		return null;
	}
	
	
}
