package me.justicepro.spigotgui.RemoteAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import me.justicepro.spigotgui.Module;
import me.justicepro.spigotgui.ModuleManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class UserWindow extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public UserWindow(User user, ServerWindow window) {
		setTitle(user.username + " - Remote Admin");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 698, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Authentication", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		passwordField = new JPasswordField(user.password);
		
		JCheckBox chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (chckbxShowPassword.isSelected()) {
					passwordField.setEchoChar((char)0);
				}else {
					passwordField.setEchoChar('•');
				}
				
			}
		});
		
		JButton btnApplyPassword = new JButton("Apply Password");
		btnApplyPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				user.setPassword(passwordField.getText());
				try {
					User.saveUsers();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				window.setTableAsList(User.users);
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Permissions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		
		int y = 0;
		for (Permission permission : User.registeredPermissions) {
			boolean has = user.hasPermission(permission);
			JCheckBox box = new JCheckBox(permission.getName());
			box.setBounds(0, y, 238, 26);
			box.setSelected(has);
			
			box.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					user.setHasPermission(permission, box.isSelected());
					
					try {
						User.saveUsers();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			
			panel_3.add(box);
			y += 30;
			
		}
		
		scrollPane.setViewportView(panel_3);
		panel_3.setLayout(null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
					.addGap(14))
		);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(7)
					.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(7)
					.addComponent(chckbxShowPassword, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(144, Short.MAX_VALUE)
					.addComponent(btnApplyPassword, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(3)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(chckbxShowPassword)
					.addPreferredGap(ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
					.addComponent(btnApplyPassword))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
	}
}
