package me.justicepro.spigotgui.RemoteAdmin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import me.justicepro.spigotgui.RemoteAdmin.Packets.PacketLogin;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField passwordField;
	private JButton btnLogin;
	
	private AdminWindow adminWindow;
	
	public String ip = "";
	public boolean loggedIn = false;
	
	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		this.ip = JOptionPane.showInputDialog(null, "What's the IP want to connect to?");
		try {
			adminWindow = new AdminWindow(this, ip);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "There is no admin server at " + ip + ".");
			dispose();
			return;
		}
		
		setTitle("Login - Remote Admin");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 517, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(12, 42, 483, 22);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(12, 106, 483, 22);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 76, 154, 18);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(12, 12, 154, 18);
		contentPane.add(lblUsername);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminWindow.client.sendPacket(new PacketLogin(usernameField.getText(), passwordField.getText()));
				usernameField.setText("");
				passwordField.setText("");
			}
		});
		btnLogin.setBounds(414, 152, 81, 24);
		contentPane.add(btnLogin);
		
	}
	
	/**
	 * When the motd packet is sent it uses that as authentication confirm.
	 */
	public void onMotdSent() {
		if (!loggedIn) {
			if (adminWindow != null) {
				adminWindow.setVisible(true);
			}
			dispose();
			loggedIn = true;
		}
		
	}

}
