package me.justicepro.spigotgui.RemoteAdmin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import me.justicepro.spigotgui.JModulePanel;
import me.justicepro.spigotgui.Module;
import me.justicepro.spigotgui.ModuleManager;
import me.justicepro.spigotgui.RemoteAdmin.Client.RClient;
import me.justicepro.spigotgui.RemoteAdmin.Packets.PacketLogin;
import me.justicepro.spigotgui.RemoteAdmin.Server.RServer;
import me.justicepro.spigotgui.Utils.Player;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class ServerWindow extends JFrame {

	private JPanel contentPane;

	public RServer server;
	private JTable table;
	private JTextField userField;
	private JTextField passField;

	/**
	 * Create the frame.
	 */
	public ServerWindow() {
		boolean online = false;
		this.server = new RServer();
		setTitle("Server - Remote Admin");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 642, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 632, 474);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Server", null, panel, null);
		panel.setLayout(null);

		JLabel lblStatus = new JLabel("Status: Offline");
		lblStatus.setBounds(12, 12, 140, 18);
		panel.add(lblStatus);

		JButton btnStartServer = new JButton("Start Server");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (server != null) {
					server = new RServer();
					server.start();
				}else {
					server.start();
				}
				lblStatus.setText("Status: Online");
			}
		});
		btnStartServer.setBounds(12, 405, 109, 24);
		panel.add(btnStartServer);

		JButton btnStopServer = new JButton("Stop Server");
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server.ss.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				server.stop();
				lblStatus.setText("Status: Offline");
			}
		});
		btnStopServer.setBounds(510, 405, 109, 24);
		panel.add(btnStopServer);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Users", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 606, 356);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Username", "Password"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = userField.getText();
				String password = passField.getText();
				
				if ( !(username.equalsIgnoreCase("") && password.equalsIgnoreCase("")) ) {
					User user = new User(username, password);
					User.users.add(user);
					setTableAsList(table, User.users);
					try {
						User.saveUsers();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnAddUser.setBounds(104, 379, 81, 24);
		panel_1.add(btnAddUser);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTableAsList(table, User.users);
			}
		});
		btnRefresh.setBounds(12, 379, 81, 24);
		panel_1.add(btnRefresh);
		
		userField = new JTextField();
		userField.setBounds(439, 380, 179, 22);
		panel_1.add(userField);
		userField.setColumns(10);
		
		passField = new JTextField();
		passField.setColumns(10);
		passField.setBounds(439, 414, 179, 22);
		panel_1.add(passField);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(358, 382, 76, 18);
		panel_1.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(358, 416, 76, 18);
		panel_1.add(lblPassword);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {

				boolean close = true;

				if (server.isAlive()) {
					JOptionPane.showMessageDialog(null, "A Server is running, you can't close this program unless you stop it.");
					close = false;
				}


				if (close) {
					dispose();
				}

			}
		});
		
		setTableAsList(table, User.users);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mntmUsername = new JMenuItem("Username");
		popupMenu.add(mntmUsername);
		
		JMenuItem menuItem = new JMenuItem(" ");
		popupMenu.add(menuItem);
		
		JMenuItem mntmDeleteUser = new JMenuItem("Delete User");
		mntmDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!mntmUsername.getText().equalsIgnoreCase("No User")) {
					User.removeUser(mntmUsername.getText());
					setTableAsList(table, User.users);
				}
				
			}
		});
		popupMenu.add(mntmDeleteUser);
		
		JMenuItem mntmManageUser = new JMenuItem("Manage User");
		mntmManageUser.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				User user = User.queryIgnoreCase(mntmUsername.getText());
				
				if (user != null) {
					UserWindow window = new UserWindow(user, ServerWindow.this);
					window.setVisible(true);
				}
				
			}
			
		});
		popupMenu.add(mntmManageUser);
		
		popupMenu.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				
				if (table.getSelectedRow() != -1) {
					
					String name = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
					
					if (name != null) {
						mntmUsername.setText(name);
					}else {
						mntmUsername.setText("No User");
					}
					
				}else {
					mntmUsername.setText("No User");
				}
				
			}
		});
		
		for (Module module : ModuleManager.modules) {
			
			if (module.getRemoteManagePage() != null) {
				JPanel panel1 = new JPanel();
				JModulePanel p = module.getRemoteManagePage();
				
				JScrollPane sp = new JScrollPane();
				sp.add(p);
				
				panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
				
				panel1.add(p);
				
				tabbedPane.addTab(p.getTitle(), null, panel1, null);
			}
			
		}
		
	}
	
	public void setTableAsList(JTable table, List<User> users) {

		for (int i = 0; i < table.getModel().getRowCount(); i++) {
			table.getModel().setValueAt(null, i, 0);
			table.getModel().setValueAt(null, i, 1);
		}

		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);

			table.getModel().setValueAt(user.username, i, 0);
			table.getModel().setValueAt(user.password, i, 1);
			/*table.getModel().setValueAt(player.whitelisted, i, 2);
			table.getModel().setValueAt(player.opped, i, 3);*/

		}

	}
	
	public void setTableAsList(List<User> users) {

		for (int i = 0; i < table.getModel().getRowCount(); i++) {
			table.getModel().setValueAt(null, i, 0);
			table.getModel().setValueAt(null, i, 1);
		}

		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);

			table.getModel().setValueAt(user.username, i, 0);
			table.getModel().setValueAt(user.password, i, 1);
			/*table.getModel().setValueAt(player.whitelisted, i, 2);
			table.getModel().setValueAt(player.opped, i, 3);*/

		}

	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}