package view.mapEditor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * this class is for main view bar of the map editor
 * @author skyba
 *
 */
public class MapEditorView 
{

	private JFrame frame;
	private JMenuBar menuBar;
	
	/**
	 * Create the application.
	 */
	public MapEditorView() 
	{
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		
		frame = new JFrame();
		frame.setBounds(400, 100, 1089, 788);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu MEVFile = new JMenu("File");
		menuBar.add(MEVFile);

		JMenuItem MEVNew = new JMenuItem("new");
		MEVFile.add(MEVNew);
		MEVNew.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		
		JMenuItem MEVLoad = new JMenuItem("load");
		MEVFile.add(MEVLoad);
		MEVLoad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		
		JMenuItem MEVSave = new JMenuItem("save");
		MEVFile.add(MEVSave);
		MEVLoad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		
		JMenuItem MEVSaveAs = new JMenuItem("save as");
		MEVFile.add(MEVSaveAs);
		MEVSaveAs.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		
		JMenuItem MEVExit = new JMenuItem("exit");
		MEVFile.add(MEVExit);
		MEVExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
	}
}