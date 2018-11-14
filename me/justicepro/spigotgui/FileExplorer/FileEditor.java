package me.justicepro.spigotgui.FileExplorer;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FileEditor extends JFrame {

	private JPanel contentPane;
	private boolean newFile = true;
	
	private JTextArea textArea;
	
	private File openedFile;
	private boolean saved = false;
	
	/**
	 * Create the frame.
	 */
	public FileEditor() {
		setTitle("New - File Editor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 663, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		textArea.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				if (e.getKeyCode()==KeyEvent.VK_S && e.isControlDown()) {
					try {
						saveFile();
						saved = true;
						JOptionPane.showMessageDialog(null, "Saved File");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
			
		});
		scrollPane.setViewportView(textArea);
		
		JMenuBar menuBar = new JMenuBar();
		scrollPane.setColumnHeaderView(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newFile = false;
				openedFile = null;
				setTitle("New - File Editor");
				saved = true;
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				
				int result = chooser.showOpenDialog(null);
				
				if (result==JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						openFile(file);
						saved = true;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveFile();
					saved = true;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnFile.add(mntmSave);
	}
	
	/**
	 * Save the file
	 * @throws IOException
	 */
	public void saveFile() throws IOException
	{
		
		if (newFile) {
			JFileChooser chooser = new JFileChooser();
			
			int result = chooser.showSaveDialog(null);
			
			if (result==JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				
				FileOutputStream output = new FileOutputStream(file);
				
				output.write(textArea.getText().getBytes());
				newFile = false;
				openedFile = file;
				output.close();
				setTitle(file.getName() + " - File Editor");
			}
			
		}else {
			File file = openedFile;
			
			FileOutputStream output = new FileOutputStream(file);
			
			output.write(textArea.getText().getBytes());
			output.close();
			setTitle(file.getName() + " - File Editor");
		}
		
	}
	
	/**
	 * Open a file
	 * @param file The file to open.
	 * @throws IOException
	 */
	public void openFile(File file) throws IOException
	{
		newFile = false;
		FileInputStream input = new FileInputStream(file);
		setTitle(file.getName() + " - File Editor");
		
		String text = "";
		
		int b;
		while ((b = input.read()) != -1) {
			text = text + (char)b;
		}
		
		textArea.setText(text);
		openedFile = file;
		input.close();
	}
	
}