package me.justicepro.spigotgui;

import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class ReporterWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ReporterWindow(Exception e) {
		setTitle(e.toString() + " - Bug Reporter");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 650, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnExitSpigotgui = new JButton("Exit SpigotGUI");
		btnExitSpigotgui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		JButton btnContinueSpigotgui = new JButton("Continue SpigotGUI");
		btnContinueSpigotgui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnCopyStacktrace = new JButton("Copy Stacktrace");
		btnCopyStacktrace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getToolkit().getSystemClipboard().setContents(new StringSelection(getStacktrace(e)), null);
			}
		});
		
		JLabel lblPleaseReportThis = new JLabel("Please report this in the SpigotGUI Remastered Thread.");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnExitSpigotgui)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnContinueSpigotgui)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCopyStacktrace))
						.addComponent(lblPleaseReportThis))
					.addContainerGap(273, Short.MAX_VALUE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, Short.MAX_VALUE)
					.addComponent(lblPleaseReportThis)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExitSpigotgui)
						.addComponent(btnContinueSpigotgui)
						.addComponent(btnCopyStacktrace)))
		);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		
		textArea.setText(getStacktrace(e));
		
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
	}
	
	public static String getStacktrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		return sw.toString();
	}
}