package me.justicepro.spigotgui.RemoteAdmin;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.DefaultCaret;

import me.justicepro.spigotgui.JRemoteModulePanel;
import me.justicepro.spigotgui.Module;
import me.justicepro.spigotgui.ModuleManager;
import me.justicepro.spigotgui.Server;
import me.justicepro.spigotgui.RemoteAdmin.Client.RClient;
import me.justicepro.spigotgui.RemoteAdmin.Packets.PacketConsole;
import me.justicepro.spigotgui.RemoteAdmin.Packets.PacketMail;
import me.justicepro.spigotgui.RemoteAdmin.Packets.PacketMailRefresh;
import me.justicepro.spigotgui.RemoteAdmin.Packets.PacketMotd;
import me.justicepro.spigotgui.RemoteAdmin.Packets.PacketQueryMail;
import me.justicepro.spigotgui.RemoteAdmin.Packets.PacketServerStart;
import me.justicepro.spigotgui.RemoteAdmin.Packets.SendMessage;
import me.justicepro.spigotgui.RemoteAdmin.Server.RConnection;
import me.justicepro.spigotgui.RemoteAdmin.Server.RServer;
import java.awt.BorderLayout;
import javax.swing.AbstractListModel;

public class AdminWindow extends JFrame implements PacketHandler {

	private JPanel contentPane;

	public RClient client;
	private JTextField chatTxtField;
	private JTextArea chatTxt;
	private JTextArea consoleTxt;
	private LoginWindow login;
	private JTextField consoleField;
	private DefaultListModel<String> mailList;
	private JTextField customArgs;
	private JTextField customSwitches;

	private JSpinner minRam;
	private JSpinner maxRam;

	/**
	 * Create the frame.
	 * @param loginWindow 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public AdminWindow(LoginWindow login, String ip) throws UnknownHostException, IOException {
		RClient.clientPacketHandlers.add(this);
		this.login = login;
		client = new RClient(ip, this);
		client.start();

		setTitle("Remote Admin - SpigotGUI Remastered");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 642, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setBounds(0, 0, 632, 474);
		contentPane.add(tabbedPane);
		
				JPanel panel_2 = new JPanel();
				tabbedPane.addTab("Console", null, panel_2, null);
				panel_2.setLayout(null);
				
						JScrollPane scrollPane_2 = new JScrollPane();
						scrollPane_2.setBounds(12, 12, 606, 382);
						panel_2.add(scrollPane_2);
						
								consoleTxt = new JTextArea();
								consoleTxt.setLineWrap(true);
								consoleTxt.setEditable(false);
								scrollPane_2.setViewportView(consoleTxt);
								
										consoleField = new JTextField();
										consoleField.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												client.sendPacket(new PacketConsole(consoleField.getText()));
												consoleField.setText("");
											}
										});
										consoleField.setColumns(10);
										consoleField.setBounds(12, 406, 606, 22);
										DefaultCaret caret2 = (DefaultCaret)consoleTxt.getCaret();
										panel_2.add(consoleField);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Staff Chat", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 12, 606, 382);
		panel_1.add(scrollPane_1);

		chatTxt = new JTextArea();
		chatTxt.setLineWrap(true);
		chatTxt.setEditable(false);
		DefaultCaret caret = (DefaultCaret)chatTxt.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane_1.setViewportView(chatTxt);

		chatTxtField = new JTextField();
		chatTxtField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!chatTxtField.getText().equalsIgnoreCase("")) {
					client.sendPacket(new SendMessage(chatTxtField.getText()));
					chatTxtField.setText("");
				}
			}
		});
		chatTxtField.setBounds(12, 406, 606, 22);
		panel_1.add(chatTxtField);
		chatTxtField.setColumns(10);
		caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Server Settings", null, panel_6, null);
		panel_6.setLayout(null);

		minRam = new JSpinner();
		minRam.setModel(new SpinnerNumberModel(new Integer(1024), null, null, new Integer(1)));
		minRam.setBounds(12, 40, 100, 22);
		panel_6.add(minRam);

		maxRam = new JSpinner();
		maxRam.setModel(new SpinnerNumberModel(new Integer(1024), null, null, new Integer(1)));
		maxRam.setBounds(12, 102, 95, 22);
		panel_6.add(maxRam);

		JLabel label = new JLabel("Min Ram");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setBounds(12, 12, 100, 16);
		panel_6.add(label);

		JLabel label_1 = new JLabel("Max Ram");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setBounds(12, 74, 100, 16);
		panel_6.add(label_1);

		customArgs = new JTextField();
		customArgs.setColumns(10);
		customArgs.setBounds(124, 41, 218, 20);
		panel_6.add(customArgs);

		customSwitches = new JTextField();
		customSwitches.setColumns(10);
		customSwitches.setBounds(124, 103, 218, 20);
		panel_6.add(customSwitches);

		JLabel label_2 = new JLabel("Custom Arguments");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(124, 12, 218, 16);
		panel_6.add(label_2);

		JLabel label_3 = new JLabel("Custom Switches");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(124, 74, 218, 16);
		panel_6.add(label_3);

		JButton btnStartServer = new JButton("Start Server");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PacketServerStart packet = new PacketServerStart(
						customSwitches.getText() + " " + Server.makeMemory(minRam.getValue() + "M", maxRam.getValue() + "M") + "",
						customArgs.getText() + " ");
				client.sendPacket(packet);

			}
		});
		btnStartServer.setBounds(12, 396, 100, 33);
		panel_6.add(btnStartServer);

		JButton btnStopServer = new JButton("Stop Server");
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.sendPacket(new PacketConsole("stop"));
			}
		});
		btnStopServer.setBounds(124, 396, 100, 33);
		panel_6.add(btnStopServer);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Modules", null, panel_3, null);
		panel_3.setLayout(null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 12, 606, 416);
		panel_3.add(scrollPane_3);

		JList<String> moduleList = new JList<String>();
		DefaultListModel<String> moduleListModel = new DefaultListModel<>();

		moduleList.setModel(moduleListModel);

		for (Module module : ModuleManager.modules) {
			moduleListModel.addElement(module.getName());
		}

		JPopupMenu popupMenu_1 = new JPopupMenu();
		popupMenu_1.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {

			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				popupMenu_1.removeAll();

				if (moduleList.getSelectedIndex() != -1) {

					JMenuItem mntmModuleName = new JMenuItem(moduleList.getSelectedValue());
					popupMenu_1.add(mntmModuleName);

					JMenuItem menuItem_2 = new JMenuItem(" ");
					popupMenu_1.add(menuItem_2);
					System.out.println(moduleList.getSelectedValue());
					Module module = ModuleManager.getModule(moduleList.getSelectedValue());

					if (module != null) {

						if (module.getRemoteMenuItems(client) != null) {

							for (JMenuItem item : module.getRemoteMenuItems(client)) {
								popupMenu_1.add(item);
							}

						}else {
							JMenuItem noModuleMenuItem = new JMenuItem("No Module Items");
							popupMenu_1.add(noModuleMenuItem);
						}

					}

				}

			}
		});
		addPopup(moduleList, popupMenu_1);

		scrollPane_3.setViewportView(moduleList);

		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Mail", null, panel_5, null);
		panel_5.setLayout(null);

		JButton btnComposeMail = new JButton("Compose Mail");
		btnComposeMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MailWindow mailWindow = new MailWindow(client);
				mailWindow.setVisible(true);
			}
		});
		btnComposeMail.setBounds(12, 12, 99, 24);
		panel_5.add(btnComposeMail);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(12, 48, 606, 380);
		panel_5.add(scrollPane_4);

		JList<String> mailJList = new JList<String>();
		mailList = new DefaultListModel<>();
		mailJList.setModel(mailList);
		scrollPane_4.setViewportView(mailJList);

		JButton btnReadMail = new JButton("Read Mail");
		btnReadMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (mailJList.getSelectedIndex() != -1) {
					client.sendPacket(new PacketQueryMail(mailJList.getSelectedIndex()));
				}

			}
		});
		btnReadMail.setBounds(122, 12, 81, 24);
		panel_5.add(btnReadMail);

		addWindowListener(new WindowAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void windowClosing(WindowEvent arg0) {
				client.stop();
			}
		});

		for (Module module : ModuleManager.modules) {

			if (module.getRemotePage(client) != null) {
				JPanel panel1 = new JPanel();
				JRemoteModulePanel p = module.getRemotePage(client);

				JScrollPane sp = new JScrollPane();
				sp.add(p);

				panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

				panel1.add(p);

				tabbedPane.addTab(p.getTitle(), null, panel1, null);
			}

		}

	}

	@Override
	public void onPacketRecievedServer(Packet packet, RConnection connection, RServer server) {}

	@Override
	public void onPacketRecievedClient(Packet packet, RClient client) {

		if (packet.getPacketName().equalsIgnoreCase("motd")) {
			PacketMotd motd = new PacketMotd(packet);
			login.onMotdSent();

		}

		if (packet.getPacketName().equalsIgnoreCase("serverchat_send")) {
			SendMessage sm = new SendMessage(packet);

			addToChat(sm.getMessage());
		}

		if (packet.getPacketName().equalsIgnoreCase("console_send")) {
			PacketConsole pc = new PacketConsole(packet);

			addToConsole(pc.getMessage());
		}

		if (packet.getPacketName().equalsIgnoreCase("mail_refresh")) {
			PacketMailRefresh mailRefresh = new PacketMailRefresh(packet);

			if (mailList != null) {
				mailList.clear();
				for (String s : mailRefresh.getMailList()) {
					mailList.addElement(s);
				}
				
			}

		}

		if (packet.getPacketName().equalsIgnoreCase("mail")) {
			PacketMail mail = new PacketMail(packet);

			ViewMailWindow viewMail = new ViewMailWindow(mail.getSubject(), mail.getBody());
			viewMail.setVisible(true);
		}

		if (packet.getPacketName().equalsIgnoreCase("acceptEula")) {

			if (JOptionPane.showConfirmDialog(null, "Do you agree to the Minecraft Eula? (https://account.mojang.com/documents/minecraft_eula)", "Message", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				PacketServerStart serverStart = new PacketServerStart(
						customSwitches.getText() + " " + Server.makeMemory(minRam.getValue() + "M", maxRam.getValue() + "M") + "",
						customArgs.getText() + " ", true);
				client.sendPacket(serverStart);
			}

		}

		if (packet.getPacketName().equalsIgnoreCase("msgbox")) {
			JOptionPane.showMessageDialog(null, packet.getData());
		}

	}
	
	/**
	 * Add message to the chatbox.
	 * @param message The message to add.
	 */
	public void addToChat(String message)
	{
		chatTxt.setText(chatTxt.getText() + message + "\n");
	}
	
	/**
	 * Add message to the console.
	 * @param message The message to add.
	 */
	public void addToConsole(String message)
	{
		consoleTxt.setText(consoleTxt.getText() + message + "\n");
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
