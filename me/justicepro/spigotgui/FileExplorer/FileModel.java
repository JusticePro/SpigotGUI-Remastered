package me.justicepro.spigotgui.FileExplorer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class FileModel extends DefaultListModel<String> {
	
	private JList<String> list;
	private File dir;
	
	public FileModel(JList<String> list) {
		this.list = list;
	}
	
	public void loadDirectory(File dir) {
		this.dir = dir;
		clear();
		
		ArrayList<File> dirs = new ArrayList<>();
		ArrayList<File> fils = new ArrayList<>();
		
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				dirs.add(file);
			}else {
				fils.add(file);
			}
		}
		
		addElement("..");
		
		for (File d : dirs) {
			addElement("/" + d.getName());
		}
		
		for (File f : fils) {
			addElement("" + f.getName());
		}
		
	}
	
	public void refresh() {
		loadDirectory(dir);
	}

	public MouseListener createMouseListener() {
		return new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getButton()==MouseEvent.BUTTON1 && e.getClickCount()>=2) {
					onFileRun();
				}
				
			}
			
		};
	}
	
	public void onFileRun() {
		
		// If there is an item selected.
		if (list.getSelectedIndex() != -1) {
			
			String item = getElementAt(list.getSelectedIndex());
			
			// If the item starts with '/' it is a directory.
			if (item.startsWith("/")) {
				System.out.println(dir.getAbsolutePath() + item);
				loadDirectory(new File(dir.getAbsolutePath() + item));
			}else {
				
				if (item.equalsIgnoreCase("..")) {
					// Go up a directory
					loadDirectory(new File(dir.getAbsolutePath() + item).getParentFile());
				}else {
					// Edit File
					FileEditor editor = new FileEditor();
					try {
						editor.openFile(new File(dir.getAbsolutePath() + "/" + item));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					editor.setVisible(true);
				}
				
			}
			
		}
		
	}
	
	public void onFileDelete() {
		
		// If there is an item selected.
		if (list.getSelectedIndex() != -1) {
			
			String item = getElementAt(list.getSelectedIndex());
			
			// If the item starts with '/' it is a directory.
			if (!item.startsWith("/")) {
				
				if (!item.equalsIgnoreCase("..")) {
					
					int result = JOptionPane.showConfirmDialog(null, "Do you want to delete '" + item + "'?");
					
					if (result==JOptionPane.YES_OPTION) {
						File file = new File(dir.getAbsolutePath() + "/" + item);
						try {
							Files.deleteIfExists(file.toPath());
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "Couldn't delete file: '" + e.getMessage() + "'");
						}
						refresh();
					}
					
				}
				
			}
			
		}
		
	}
	
	public KeyListener createKeyListener() {
		return new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					onFileRun();
				}
				
				if (e.getKeyCode()==KeyEvent.VK_DELETE) {
					onFileDelete();
				}
				
			}
			
		};
	}
	
}