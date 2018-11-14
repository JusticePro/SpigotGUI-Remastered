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

public class MailWindow extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JTextField subjectField;
	private JScrollPane scrollPane;
	private JTextArea bodyArea;
	private JButton btnSend;
	private JLabel lblUser;
	private JLabel lblSubject;
	
	/**
	 * Create the frame.
	 * @param client 
	 */
	public MailWindow(RClient client) {
		setTitle("Compose Mail - Remote Admin");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userField = new JTextField();
		userField.setBounds(75, 12, 563, 22);
		contentPane.add(userField);
		userField.setColumns(10);
		
		subjectField = new JTextField();
		subjectField.setColumns(10);
		subjectField.setBounds(75, 46, 563, 22);
		contentPane.add(subjectField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 80, 626, 414);
		contentPane.add(scrollPane);
		
		bodyArea = new JTextArea();
		scrollPane.setViewportView(bodyArea);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.sendPacket(new PacketMail(userField.getText(), subjectField.getText(), bodyArea.getText()));
				dispose();
			}
		});
		btnSend.setBounds(557, 505, 81, 24);
		contentPane.add(btnSend);
		
		lblUser = new JLabel("User:");
		lblUser.setBounds(12, 14, 56, 18);
		contentPane.add(lblUser);
		
		lblSubject = new JLabel("Subject:");
		lblSubject.setBounds(12, 48, 56, 18);
		contentPane.add(lblSubject);
	}
}
