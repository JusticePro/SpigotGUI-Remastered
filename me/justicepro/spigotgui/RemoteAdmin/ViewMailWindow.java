package me.justicepro.spigotgui.RemoteAdmin;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import me.justicepro.spigotgui.RemoteAdmin.Client.RClient;
import me.justicepro.spigotgui.RemoteAdmin.Packets.PacketMail;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewMailWindow extends JFrame {

	private JPanel contentPane;
	private JTextField subjectField;
	private JScrollPane scrollPane;
	private JTextArea bodyArea;
	
	/**
	 * Create the frame.
	 * @param client 
	 */
	public ViewMailWindow(String subject, String body) {
		setTitle("View Mail - Remote Admin");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		subjectField = new JTextField();
		subjectField.setEditable(false);
		subjectField.setColumns(10);
		subjectField.setBounds(12, 14, 626, 22);
		contentPane.add(subjectField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 50, 626, 477);
		contentPane.add(scrollPane);
		
		bodyArea = new JTextArea();
		bodyArea.setLineWrap(true);
		bodyArea.setEditable(false);
		scrollPane.setViewportView(bodyArea);
		
		subjectField.setText(subject);
		bodyArea.setText(body);
	}
}
