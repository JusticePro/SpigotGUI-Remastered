package me.justicepro.spigotgui.Instructions;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import me.justicepro.spigotgui.Module;
import me.justicepro.spigotgui.ModuleManager;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class InstructionWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public InstructionWindow() throws Exception {
		setTitle("Instruction Manual - SpigotGUI Remastered");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 643, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		HashMap<String, String> pages = new HashMap<>();
		
		InputStream pageinput = getClass().getResourceAsStream("/manuels");
		
		String m = "";
		
		int b;
		while ((b = pageinput.read()) != -1) {
			m = m + (char)b;
		}
		
		ArrayList<String> pagesList = new ArrayList<>();
		
		for (String manuel : m.split("\\r?\\n")) {
			InputStream input = getClass().getResourceAsStream("/manuel/" + manuel);
			String content = "";
			
			int b1;
			while ((b1 = input.read()) != -1) {
				content = content + (char)b1;
			}
			
			input.close();
			
			pages.put(manuel, content);
			pagesList.add(manuel);
		}
		
		for (Module module : ModuleManager.modules) {
			
			if (module.getManual() != null) {
				pages.put(module.getName(), module.getManual());
				pagesList.add(module.getName());
			}
			
		}
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		scrollPane.setViewportView(editorPane);
		
		String[] pagesArray = (String[]) pagesList.toArray(new String[pagesList.size()]);
		
		JList<String> list = new JList<String>();
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				editorPane.setText(pages.get(list.getSelectedValue()));
				editorPane.setCaretPosition(0);
			}
		});
		
		list.setModel(new AbstractListModel<String>() {
			String[] values = pagesArray;
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		splitPane.setLeftComponent(list);
		
		
	}

}
