package project;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import project.Gift.Type;

public class MainFrame {

	private JFrame frame;
	private JTextField usernameTextField;
	private JTextField emailTextField;
	private JTextField nameTextField;
	private JTable bungeeTable;
	private JTable bungeeLabelTable;
	private JTable driftingTable;
	private JTable driftingLabelTable;
	private JTable divingTable;
	private JTable divingLabelTable;
	private JTable skydivingTable;
	private JTable skydivingLabelTable;
	private JTable purchasesTable;
	private JTable purchasesLabelTable;
	private JTable customersTable;
	private JTable customersLabelTable;
	private JLabel loggedInLabel;
	Map<Type, List<Gift>> giftMap = new HashMap<>();
	Map<String, List<Purchase>> purchasesMap = new HashMap<>();
	Map<String, Customer> customerMap = new HashMap<>();
	Map<Integer, Gift> giftIdMap = new HashMap<>();
	DateWrapper date = new DateWrapper("2020-01-01");
	private int counter = 1;
	private JTextField nameBTextField;
	private JTextField priceBTextField;
	private JTextField validityBTextField;
	private JTextField heightBtextField;
	private JTextField nameSTextField;
	private JTextField priceSTextField;
	private JTextField validitySTextField;
	private JTextField heightSTextField;
	private JTextField nameDiTextField;
	private JTextField priceDiTextField;
	private JTextField validityDiTextField;
	private JTextField depthDTextField;
	private JTextField nameDrTextField;
	private JTextField priceDrTextField;
	private JTextField validityDrTextField;
	private JTextField carDTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Shop shop = new Shop("gifts.txt", "purchases.txt", "customers.txt");

		for (Gift g : shop.getGifts()) {
			if (giftMap.get(g.getType()) == null) {
				giftMap.put(g.getType(), new LinkedList<Gift>());
			}
			giftMap.get(g.getType()).add(g);
			giftIdMap.put(g.getId(), g);
		}

		for (Purchase p : shop.getPurchases()) {
			if (purchasesMap.get(p.getUsername()) == null) {
				purchasesMap.put(p.getUsername(), new LinkedList<Purchase>());
			}
			purchasesMap.get(p.getUsername()).add(p);
		}

		for (Customer c : shop.getCustomers()) {
			if (customerMap.get(c.getUsername()) == null) {
				customerMap.put(c.getUsername(), c);
			}
		}

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 345);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 27, 414, 240);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		

		JPanel loginPanel = new JPanel();
		layeredPane.add(loginPanel, "name_49318416793909");
		GridBagLayout gbl_loginPanel = new GridBagLayout();
		gbl_loginPanel.columnWidths = new int[] { 95, 109, 109, 0 };
		gbl_loginPanel.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_loginPanel.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_loginPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		loginPanel.setLayout(gbl_loginPanel);

		JRadioButton registerRadioBtn = new JRadioButton("Register");
		
		JPanel AddGiftPanel = new JPanel();
		layeredPane.add(AddGiftPanel, "name_51616950702958");
		AddGiftPanel.setLayout(null);
		AddGiftPanel.setVisible(false);

		registerRadioBtn.setSelected(true);
		GridBagConstraints gbc_registerRadioBtn = new GridBagConstraints();
		gbc_registerRadioBtn.anchor = GridBagConstraints.NORTHWEST;
		gbc_registerRadioBtn.insets = new Insets(0, 0, 5, 5);
		gbc_registerRadioBtn.gridx = 1;
		gbc_registerRadioBtn.gridy = 0;
		loginPanel.add(registerRadioBtn, gbc_registerRadioBtn);

		JRadioButton loginRadioBtn = new JRadioButton("Login");

		GridBagConstraints gbc_loginRadioBtn = new GridBagConstraints();
		gbc_loginRadioBtn.insets = new Insets(0, 0, 5, 0);
		gbc_loginRadioBtn.anchor = GridBagConstraints.NORTHWEST;
		gbc_loginRadioBtn.gridx = 2;
		gbc_loginRadioBtn.gridy = 0;
		loginPanel.add(loginRadioBtn, gbc_loginRadioBtn);

		JLabel usernameLabel = new JLabel("Username");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 2;
		loginPanel.add(usernameLabel, gbc_usernameLabel);

		usernameTextField = new JTextField();
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameTextField.gridx = 0;
		gbc_usernameTextField.gridy = 3;
		loginPanel.add(usernameTextField, gbc_usernameTextField);
		usernameTextField.setColumns(10);
		
		JPanel mainPanel = new JPanel();
		layeredPane.add(mainPanel, "name_49332477927712");
		mainPanel.setLayout(null);
		
		JPanel addBungeeGiftPanel = new JPanel();
		layeredPane.add(addBungeeGiftPanel, "name_53209803390815");
				addBungeeGiftPanel.setLayout(null);
		
				loggedInLabel = new JLabel("");
				loggedInLabel.setBounds(150, 14, 0, 0);
				addBungeeGiftPanel.add(loggedInLabel);
				loggedInLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblBungeeGiftName = new JLabel("Bungee Gift name");
		lblBungeeGiftName.setBounds(12, 14, 178, 28);
		addBungeeGiftPanel.add(lblBungeeGiftName);
		
		JLabel lblNewLabel = new JLabel("Price");
		lblNewLabel.setBounds(32, 82, 70, 15);
		addBungeeGiftPanel.add(lblNewLabel);
		
		nameBTextField = new JTextField();
		nameBTextField.setBounds(12, 51, 261, 19);
		addBungeeGiftPanel.add(nameBTextField);
		nameBTextField.setColumns(10);
		
		priceBTextField = new JTextField();
		priceBTextField.setBounds(12, 98, 261, 19);
		addBungeeGiftPanel.add(priceBTextField);
		priceBTextField.setColumns(10);
		
		JLabel lblValidity = new JLabel("Validity");
		lblValidity.setBounds(12, 109, 145, 15);
		addBungeeGiftPanel.add(lblValidity);
		
		validityBTextField = new JTextField();
		validityBTextField.setBounds(12, 137, 261, 19);
		addBungeeGiftPanel.add(validityBTextField);
		validityBTextField.setColumns(10);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(22, 168, 70, 15);
		addBungeeGiftPanel.add(lblHeight);
		
		heightBtextField = new JTextField();
		heightBtextField.setBounds(12, 189, 261, 19);
		addBungeeGiftPanel.add(heightBtextField);
		heightBtextField.setColumns(10);
		
		JButton btnNewButton_9 = new JButton("Back");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addBungeeGiftPanel.setVisible(false);
				AddGiftPanel.setVisible(true);
			}
		});
		btnNewButton_9.setBounds(22, 220, 117, 25);
		addBungeeGiftPanel.add(btnNewButton_9);
		
		JButton buyBGiftBt = new JButton("Add Gift");
		buyBGiftBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String name = nameBTextField.getText();
				 double price = Double.parseDouble(priceBTextField.getText());
				 int height = Integer.parseInt(heightBtextField.getText());
				 int validity = Integer.parseInt(validityBTextField.getText());
				 
				 shop.addBungeeGift(shop.getGifts().size() + counter, name, price, validity, height);
				 loadBungeeTableModel(bungeeTable, giftMap.get(Type.BUNGEE_JUMPING));
				 counter++;
				 addBungeeGiftPanel.setVisible(false);
				 buyBGiftBt.setVisible(false);
				 mainPanel.setVisible(true);
				 
			}
		});
		buyBGiftBt.setBounds(285, 220, 117, 25);
		addBungeeGiftPanel.add(buyBGiftBt);
		
		JPanel addSkydivingGiftPanel = new JPanel();
		addSkydivingGiftPanel.setLayout(null);
		layeredPane.add(addSkydivingGiftPanel, "name_55266703703237");
		
		JLabel loggedInLabel_1 = new JLabel("");
		loggedInLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		loggedInLabel_1.setBounds(150, 14, 0, 0);
		addSkydivingGiftPanel.add(loggedInLabel_1);
		
		JLabel lblSkydivingGiftName = new JLabel("Sky-Diving Gift name");
		lblSkydivingGiftName.setBounds(12, 14, 178, 28);
		addSkydivingGiftPanel.add(lblSkydivingGiftName);
		
		JLabel lblNewLabel_1 = new JLabel("Price");
		lblNewLabel_1.setBounds(32, 82, 70, 15);
		addSkydivingGiftPanel.add(lblNewLabel_1);
		
		nameSTextField = new JTextField();
		nameSTextField.setColumns(10);
		nameSTextField.setBounds(12, 51, 261, 19);
		addSkydivingGiftPanel.add(nameSTextField);
		
		priceSTextField = new JTextField();
		priceSTextField.setColumns(10);
		priceSTextField.setBounds(12, 98, 261, 19);
		addSkydivingGiftPanel.add(priceSTextField);
		
		JLabel lblValidity_1 = new JLabel("Validity");
		lblValidity_1.setBounds(22, 129, 145, 15);
		addSkydivingGiftPanel.add(lblValidity_1);
		
		validitySTextField = new JTextField();
		validitySTextField.setColumns(10);
		validitySTextField.setBounds(12, 142, 261, 19);
		addSkydivingGiftPanel.add(validitySTextField);
		
		JLabel lblHeight_1 = new JLabel("Height");
		lblHeight_1.setBounds(22, 168, 70, 15);
		addSkydivingGiftPanel.add(lblHeight_1);
		
		heightSTextField = new JTextField();
		heightSTextField.setColumns(10);
		heightSTextField.setBounds(12, 189, 261, 19);
		addSkydivingGiftPanel.add(heightSTextField);
		
		JButton btnNewButton_9_1 = new JButton("Back");
		btnNewButton_9_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addSkydivingGiftPanel.setVisible(false);
				AddGiftPanel.setVisible(true);
			}
		});
		btnNewButton_9_1.setBounds(22, 220, 117, 25);
		addSkydivingGiftPanel.add(btnNewButton_9_1);
		
		JButton buySGiftBt = new JButton("Add Gift");
		buySGiftBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String name = nameBTextField.getText();
				 double price = Double.parseDouble(priceSTextField.getText());
				 int validity = Integer.parseInt(validitySTextField.getText());
				 int height = Integer.parseInt(heightSTextField.getText());
				 
				 shop.addSkyDivingGift(shop.getGifts().size() + counter, name, price, validity, height, 1500);
				 loadSkydivingTableModel(skydivingTable, giftMap.get(Type.DRIFTING_LESSON));
				 counter++;
				 addBungeeGiftPanel.setVisible(false);
				 buySGiftBt.setVisible(false);
				 mainPanel.setVisible(true);
			}
		});
		buySGiftBt.setBounds(285, 220, 117, 25);
		addSkydivingGiftPanel.add(buySGiftBt);
		
		JPanel addDivingGiftPanel = new JPanel();
		addDivingGiftPanel.setLayout(null);
		layeredPane.add(addDivingGiftPanel, "name_55442700613853");
		
		JLabel loggedInLabel_1_1 = new JLabel("");
		loggedInLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		loggedInLabel_1_1.setBounds(150, 14, 0, 0);
		addDivingGiftPanel.add(loggedInLabel_1_1);
		
		JLabel lblDivingName = new JLabel("Diving Gift name");
		lblDivingName.setBounds(12, 14, 178, 28);
		addDivingGiftPanel.add(lblDivingName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Price");
		lblNewLabel_1_1.setBounds(32, 82, 70, 15);
		addDivingGiftPanel.add(lblNewLabel_1_1);
		
		nameDiTextField = new JTextField();
		nameDiTextField.setColumns(10);
		nameDiTextField.setBounds(12, 51, 261, 19);
		addDivingGiftPanel.add(nameDiTextField);
		
		priceDiTextField = new JTextField();
		priceDiTextField.setColumns(10);
		priceDiTextField.setBounds(12, 98, 261, 19);
		addDivingGiftPanel.add(priceDiTextField);
		
		JLabel lblValidity_1_1 = new JLabel("Validity");
		lblValidity_1_1.setBounds(22, 129, 145, 15);
		addDivingGiftPanel.add(lblValidity_1_1);
		
		validityDiTextField = new JTextField();
		validityDiTextField.setColumns(10);
		validityDiTextField.setBounds(12, 142, 261, 19);
		addDivingGiftPanel.add(validityDiTextField);
		
		JLabel lblHeight_1_1 = new JLabel("Depth");
		lblHeight_1_1.setBounds(22, 168, 70, 15);
		addDivingGiftPanel.add(lblHeight_1_1);
		
		depthDTextField = new JTextField();
		depthDTextField.setColumns(10);
		depthDTextField.setBounds(12, 189, 261, 19);
		addDivingGiftPanel.add(depthDTextField);
		
		JButton btnNewButton_9_1_1 = new JButton("Back");
		btnNewButton_9_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addDivingGiftPanel.setVisible(false);
				AddGiftPanel.setVisible(true);
			}
		});
		btnNewButton_9_1_1.setBounds(22, 220, 117, 25);
		addDivingGiftPanel.add(btnNewButton_9_1_1);
		
		JButton buyDiGftBt = new JButton("Add Gift");
		buyDiGftBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String name = nameDiTextField.getText();
				 double price = Double.parseDouble(priceDiTextField.getText());
				 int validity = Integer.parseInt(validityDiTextField.getText());
				 int depth = Integer.parseInt(depthDTextField.getText());
				 
				 shop.addDivingGift(shop.getGifts().size() + counter, name, price, validity, 90, depth);
				 loadDivingTableModel(divingTable, giftMap.get(Type.DIVING));
				 counter++;
				 addBungeeGiftPanel.setVisible(false);
				 buyDiGftBt.setVisible(false);
				 mainPanel.setVisible(true);
			}
		});
		buyDiGftBt.setBounds(285, 220, 117, 25);
		addDivingGiftPanel.add(buyDiGftBt);
		
		JPanel addDriftingGiftPanel = new JPanel();
		addDriftingGiftPanel.setLayout(null);
		layeredPane.add(addDriftingGiftPanel, "name_55525039455078");
		
		JLabel loggedInLabel_1_1_1 = new JLabel("");
		loggedInLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		loggedInLabel_1_1_1.setBounds(150, 14, 0, 0);
		addDriftingGiftPanel.add(loggedInLabel_1_1_1);
		
		JLabel lblDriftingGiftName = new JLabel("Drifting Gift name");
		lblDriftingGiftName.setBounds(12, 14, 178, 28);
		addDriftingGiftPanel.add(lblDriftingGiftName);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setBounds(32, 82, 70, 15);
		addDriftingGiftPanel.add(lblNewLabel_1_1_1);
		
		nameDrTextField = new JTextField();
		nameDrTextField.setColumns(10);
		nameDrTextField.setBounds(12, 51, 261, 19);
		addDriftingGiftPanel.add(nameDrTextField);
		
		priceDrTextField = new JTextField();
		priceDrTextField.setColumns(10);
		priceDrTextField.setBounds(12, 98, 261, 19);
		addDriftingGiftPanel.add(priceDrTextField);
		
		JLabel lblValidity_1_1_1 = new JLabel("Validity");
		lblValidity_1_1_1.setBounds(22, 129, 145, 15);
		addDriftingGiftPanel.add(lblValidity_1_1_1);
		
		validityDrTextField = new JTextField();
		validityDrTextField.setColumns(10);
		validityDrTextField.setBounds(12, 142, 261, 19);
		addDriftingGiftPanel.add(validityDrTextField);
		
		JLabel lblHeight_1_1_1 = new JLabel("Car - Brand");
		lblHeight_1_1_1.setBounds(22, 168, 70, 15);
		addDriftingGiftPanel.add(lblHeight_1_1_1);
		
		carDTextField = new JTextField();
		carDTextField.setColumns(10);
		carDTextField.setBounds(12, 189, 261, 19);
		addDriftingGiftPanel.add(carDTextField);
		
		JButton btnNewButton_9_1_1_1 = new JButton("Back");
		btnNewButton_9_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addDriftingGiftPanel.setVisible(false);
				AddGiftPanel.setVisible(true);
			}
		});
		btnNewButton_9_1_1_1.setBounds(22, 220, 117, 25);
		addDriftingGiftPanel.add(btnNewButton_9_1_1_1);
		
		JButton addDrGiftBt = new JButton("Add Gift");
		addDrGiftBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String name = nameDrTextField.getText();
				 double price = Double.parseDouble(priceDrTextField.getText());
				 int validity = Integer.parseInt(validityDrTextField.getText());
				 String car = carDTextField.getText();
				 shop.addDriftingGift(shop.getGifts().size() + counter, name, price, validity, 45, car, 500);
				 loadDriftingTableModel(driftingTable, giftMap.get(Type.DRIFTING_LESSON));
				 counter++;
				 addBungeeGiftPanel.setVisible(false);
				 addDrGiftBt.setVisible(false);
				 
				 mainPanel.setVisible(true);
				
			}
		});
		addDrGiftBt.setBounds(285, 220, 117, 25);
		addDriftingGiftPanel.add(addDrGiftBt);
		
		JButton btnNewButton_8 = new JButton("Add Bangee Gift");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddGiftPanel.setVisible(false);
				addBungeeGiftPanel.setVisible(true);
			}
		});
		btnNewButton_8.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_8.setBounds(12, 12, 174, 99);
		AddGiftPanel.add(btnNewButton_8);
		
		JButton btnNewButton_8_1 = new JButton("Add Drifting Gift");
		btnNewButton_8_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddGiftPanel.setVisible(false);
				addDriftingGiftPanel.setVisible(true);
			}
		});
		btnNewButton_8_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_8_1.setBounds(228, 12, 174, 99);
		AddGiftPanel.add(btnNewButton_8_1);
		
		JButton btnNewButton_8_2 = new JButton("Add Diving Gift");
		btnNewButton_8_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddGiftPanel.setVisible(false);
				addDivingGiftPanel.setVisible(true);
			}
		});
		btnNewButton_8_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_8_2.setBounds(12, 129, 174, 99);
		AddGiftPanel.add(btnNewButton_8_2);
		
		JButton btnNewButton_8_3 = new JButton("Add SkyDiving Gift");
		btnNewButton_8_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddGiftPanel.setVisible(false);
				addSkydivingGiftPanel.setVisible(true);
			}
		});
		btnNewButton_8_3.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_8_3.setBounds(228, 129, 174, 99);
		AddGiftPanel.add(btnNewButton_8_3);
		

		JPanel bungeePanel = new JPanel();
		layeredPane.add(bungeePanel, "name_49335725432997");
		bungeePanel.setLayout(null);

		JPanel driftingPanel = new JPanel();
		driftingPanel.setLayout(null);
		layeredPane.add(driftingPanel, "name_57101318397503");

		JPanel skydivingPanel = new JPanel();
		skydivingPanel.setLayout(null);
		layeredPane.add(skydivingPanel, "name_57112535712973");

		JPanel divingPanel = new JPanel();
		divingPanel.setLayout(null);
		layeredPane.add(divingPanel, "name_57109656312234");

		JPanel purchasesPanel = new JPanel();
		purchasesPanel.setLayout(null);
		layeredPane.add(purchasesPanel, "name_57960451281599");

		JPanel customersPanel = new JPanel();
		customersPanel.setLayout(null);
		layeredPane.add(customersPanel, "name_57963651067792");

		JPanel buyGiftsPanel = new JPanel();
		buyGiftsPanel.setLayout(null);
		layeredPane.add(buyGiftsPanel, "name_61032699072513");

		JButton makePurchaseBtn = new JButton("New Purchase");
		
		makePurchaseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(false);
				buyGiftsPanel.setVisible(true);
			}
		});
		
		JButton btnNewButton_7 = new JButton("Back");
		btnNewButton_7.setBounds(143, 215, 117, 25);
		AddGiftPanel.add(btnNewButton_7);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddGiftPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		makePurchaseBtn.setBounds(102, 28, 200, 23);
		mainPanel.add(makePurchaseBtn);
		makePurchaseBtn.setVisible(false);

		JButton showPurchasesBtn = new JButton("Show Purchases");
		showPurchasesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(false);
				purchasesPanel.setVisible(true);
				loadPurchasesTableModel(purchasesTable, shop.getPurchases());
			}
		});
		showPurchasesBtn.setBounds(102, 74, 200, 23);
		mainPanel.add(showPurchasesBtn);

		JButton showUserInfoBtn = new JButton("Show user info");
		showUserInfoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(false);
				customersPanel.setVisible(true);
				loadCustomersTableModel(customersTable, shop.getCustomers());
			}
		});
		showUserInfoBtn.setBounds(102, 119, 200, 23);
		mainPanel.add(showUserInfoBtn);
		
		JButton addGiftBt = new JButton("Add Gift");
		addGiftBt.setVisible(false);
		addGiftBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(false);
				AddGiftPanel.setVisible(true);
			}
		});
		addGiftBt.setBounds(104, 171, 198, 39);
		mainPanel.add(addGiftBt);

		bungeeTable = new JTable();
		this.loadBungeeTableModel(bungeeTable, giftMap.get(Type.BUNGEE_JUMPING));
		bungeeTable.setBounds(0, 27, 404, 160);
		bungeePanel.add(bungeeTable);

		bungeeLabelTable = new JTable();
		bungeeLabelTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		bungeeLabelTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		bungeeLabelTable.setModel(new DefaultTableModel(new Object[][] { { "Name", "Validity", "Price", "Height" }, },
				new String[] { "New column", "New column", "New column", "New column" }));
		bungeeLabelTable.setBounds(0, 11, 404, 16);
		bungeePanel.add(bungeeLabelTable);

		JButton bungeeBuyBtn = new JButton("Buy");
		bungeeBuyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = bungeeTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(frame, "Please select a gift first!");
				} else {
					String username = getLoggedInUser(loggedInLabel.getText());
					int giftId = giftMap.get(Type.BUNGEE_JUMPING).get(selectedRow).getId();
					shop.makePurchase(username, giftId, date.getDate());
				}
				bungeePanel.setVisible(false);
				bungeeBuyBtn.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		bungeeBuyBtn.setBounds(315, 198, 89, 23);
		bungeePanel.add(bungeeBuyBtn);

		JButton btnNewButton = new JButton("Back");

		btnNewButton.setBounds(10, 198, 89, 23);
		bungeePanel.add(btnNewButton);

		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				skydivingPanel.setVisible(false);
				divingPanel.setVisible(false);
				bungeePanel.setVisible(false);
				driftingPanel.setVisible(false);
				buyGiftsPanel.setVisible(false);
				customersPanel.setVisible(false);
				purchasesPanel.setVisible(false);
				mainPanel.setVisible(false);
				AddGiftPanel.setVisible(false);
				addBungeeGiftPanel.setVisible(false);
				addDivingGiftPanel.setVisible(false);
				addDriftingGiftPanel.setVisible(false);
				addSkydivingGiftPanel.setVisible(false);
				loginPanel.setVisible(true);
				addGiftBt.setVisible(false);
					
				
				loggedInLabel.setText("");

			}
		});
		logoutButton.setVisible(false);
		logoutButton.setBounds(335, 2, 89, 23);
		frame.getContentPane().add(logoutButton);

		JButton loginBtn = new JButton("Register");
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (loginBtn.getText().equals("Register")) {
					String username = usernameTextField.getText();
					String email = emailTextField.getText();
					String name = nameTextField.getText();
					if (shop.login(username)) {
						JOptionPane.showMessageDialog(frame, "This username is taken!");
					} else {
						shop.addCustomer(username, name, email);
						shop.login(username);
						loginPanel.setVisible(false);
						mainPanel.setVisible(true);
						loggedInLabel.setText("You are logged in as: " + username);
						
					}
				} else {
					String username = usernameTextField.getText();
					if (!shop.login(username)) {
						JOptionPane.showMessageDialog(frame, "Invalid username!");
					} else {
						loginPanel.setVisible(false);
						mainPanel.setVisible(true);
						loggedInLabel.setText("You are logged in as: " + username);
						logoutButton.setVisible(true);
						
						if(username.equals("admin")) {
							makePurchaseBtn.setVisible(false);
							addGiftBt.setVisible(true);
						}
						else {
							addGiftBt.setVisible(false);
							makePurchaseBtn.setVisible(true);
						}
						
						logoutButton.setVisible(true);
					}
				}
			}
		});
		GridBagConstraints gbc_loginBtn = new GridBagConstraints();
		gbc_loginBtn.gridwidth = 2;
		gbc_loginBtn.insets = new Insets(0, 0, 5, 5);
		gbc_loginBtn.gridx = 1;
		gbc_loginBtn.gridy = 3;
		loginPanel.add(loginBtn, gbc_loginBtn);

		JLabel emailLabel = new JLabel("Email");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 4;
		loginPanel.add(emailLabel, gbc_emailLabel);

		emailTextField = new JTextField();
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 0;
		gbc_emailTextField.gridy = 5;
		loginPanel.add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);

		JLabel nameLabel = new JLabel("Full Name");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 6;
		loginPanel.add(nameLabel, gbc_nameLabel);

		nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.insets = new Insets(0, 0, 0, 5);
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.gridx = 0;
		gbc_nameTextField.gridy = 7;
		loginPanel.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		driftingTable = new JTable();
		this.loadDriftingTableModel(driftingTable, giftMap.get(Type.DRIFTING_LESSON));
		driftingTable.setBounds(0, 27, 404, 160);
		driftingPanel.add(driftingTable);

		driftingLabelTable = new JTable();
		driftingLabelTable
				.setModel(new DefaultTableModel(new Object[][] { { "Name", "Validity", "Price", "Car", "Hpw" }, },
						new String[] { "New column", "New column", "New column", "New column", "New column" }));
		driftingLabelTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		driftingLabelTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		driftingLabelTable.setBounds(0, 11, 404, 16);
		driftingPanel.add(driftingLabelTable);

		JButton driftingBuyBtn = new JButton("Buy");
		driftingBuyBtn.setBounds(315, 198, 89, 23);
		driftingPanel.add(driftingBuyBtn);
		driftingBuyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = driftingTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(frame, "Please select a gift first!");
				} else {
					String username = getLoggedInUser(loggedInLabel.getText());
					int giftId = giftMap.get(Type.DRIFTING_LESSON).get(selectedRow).getId();
					shop.makePurchase(username, giftId, date.getDate());
				}
				driftingPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});

		JButton btnNewButton_1 = new JButton("Back");

		btnNewButton_1.setBounds(10, 198, 89, 23);
		driftingPanel.add(btnNewButton_1);

		divingTable = new JTable();
		this.loadDivingTableModel(divingTable, giftMap.get(Type.DIVING));
		divingTable.setBounds(0, 27, 404, 160);
		divingPanel.add(divingTable);

		divingLabelTable = new JTable();
		divingLabelTable.setModel(
				new DefaultTableModel(new Object[][] { { "Name", "Validity", "Price", "Depth", "Duration" }, },
						new String[] { "New column", "New column", "New column", "New column", "New column" }));
		divingLabelTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		divingLabelTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		divingLabelTable.setBounds(0, 11, 404, 16);
		divingPanel.add(divingLabelTable);

		JButton divingBuyBtn = new JButton("Buy");
		divingBuyBtn.setBounds(315, 198, 89, 23);
		divingPanel.add(divingBuyBtn);
		divingBuyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = divingTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(frame, "Please select a gift first!");
				} else {
					String username = getLoggedInUser(loggedInLabel.getText());
					int giftId = giftMap.get(Type.DIVING).get(selectedRow).getId();
					shop.makePurchase(username, giftId, date.getDate());
				}
				divingPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});

		JButton btnNewButton_3 = new JButton("Back");

		btnNewButton_3.setBounds(10, 198, 89, 23);
		divingPanel.add(btnNewButton_3);

		skydivingTable = new JTable();
		this.loadSkydivingTableModel(skydivingTable, giftMap.get(Type.SKYDIVING));
		skydivingTable.setBounds(0, 27, 404, 160);
		skydivingPanel.add(skydivingTable);

		skydivingLabelTable = new JTable();
		skydivingLabelTable.setModel(
				new DefaultTableModel(new Object[][] { { "Name", "Validity", "Price", "Height", "Max speed" }, },
						new String[] { "New column", "New column", "New column", "New column", "New column" }));
		skydivingLabelTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		skydivingLabelTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		skydivingLabelTable.setBounds(0, 11, 404, 16);
		skydivingPanel.add(skydivingLabelTable);

		JButton skydivingBuyBtn = new JButton("Buy");
		skydivingBuyBtn.setBounds(315, 198, 89, 23);
		skydivingPanel.add(skydivingBuyBtn);
		skydivingBuyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = skydivingTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(frame, "Please select a gift first!");
				} else {
					String username = getLoggedInUser(loggedInLabel.getText());
					int giftId = giftMap.get(Type.SKYDIVING).get(selectedRow).getId();
					shop.makePurchase(username, giftId, date.getDate());
				}
				skydivingPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});

		JButton btnNewButton_2 = new JButton("Back");

		btnNewButton_2.setBounds(10, 198, 89, 23);
		skydivingPanel.add(btnNewButton_2);

		purchasesTable = new JTable();
		purchasesTable.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, },
				new String[] { "New column", "New column", "New column", "New column" }));
		purchasesTable.setBounds(0, 27, 404, 160);
		purchasesPanel.add(purchasesTable);

		purchasesLabelTable = new JTable();
		purchasesLabelTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"Product", "Purchase Date", "Expire Date", "Expired"},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		purchasesLabelTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		purchasesLabelTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		purchasesLabelTable.setBounds(0, 11, 404, 16);
		purchasesPanel.add(purchasesLabelTable);

		JButton backBt = new JButton("Back");

		backBt.setBounds(10, 198, 97, 23);
		purchasesPanel.add(backBt);

		customersTable = new JTable();
		customersTable.setBounds(0, 27, 404, 160);
		customersPanel.add(customersTable);

		customersLabelTable = new JTable();
		customersLabelTable.setModel(new DefaultTableModel(new Object[][] { { "Username", "Name", "Email" }, },
				new String[] { "New column", "New column", "New column" }));
		customersLabelTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		customersLabelTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		customersLabelTable.setBounds(0, 11, 404, 16);
		customersPanel.add(customersLabelTable);

		JButton btnNewButton_4 = new JButton("Back");

		btnNewButton_4.setBounds(10, 198, 89, 23);
		customersPanel.add(btnNewButton_4);

		JButton btnNewButton_6 = new JButton("Bungee Jumping");
		btnNewButton_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buyGiftsPanel.setVisible(false);
				bungeePanel.setVisible(true);
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_6.setBounds(10, 11, 203, 100);
		buyGiftsPanel.add(btnNewButton_6);

		JButton btnNewButton_6_1 = new JButton("Sky Diving");
		btnNewButton_6_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buyGiftsPanel.setVisible(false);
				skydivingPanel.setVisible(true);
			}
		});
		btnNewButton_6_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_6_1.setBounds(211, 11, 203, 100);
		buyGiftsPanel.add(btnNewButton_6_1);

		JButton btnNewButton_6_2 = new JButton("Diving");
		btnNewButton_6_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buyGiftsPanel.setVisible(false);
				divingPanel.setVisible(true);
			}
		});
		btnNewButton_6_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_6_2.setBounds(10, 112, 203, 100);
		buyGiftsPanel.add(btnNewButton_6_2);

		JButton btnNewButton_6_3 = new JButton("Drifting");
		btnNewButton_6_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buyGiftsPanel.setVisible(false);
				driftingPanel.setVisible(true);
			}
		});
		btnNewButton_6_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_6_3.setBounds(211, 112, 203, 100);
		buyGiftsPanel.add(btnNewButton_6_3);

		JButton btnNewButton_5 = new JButton("Back");

		btnNewButton_5.setBounds(10, 217, 89, 23);
		buyGiftsPanel.add(btnNewButton_5);

		JLabel currentTimeLabel = new JLabel("Current Time:");
		currentTimeLabel.setBounds(20, 281, 83, 14);
		frame.getContentPane().add(currentTimeLabel);

		JLabel dateLabel = new JLabel(date.getDate().toString());
		dateLabel.setBounds(98, 281, 89, 14);
		frame.getContentPane().add(dateLabel);

		JButton dateButton = new JButton("+1 Month");
		dateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				date.addMonth();
				dateLabel.setText(date.getDate().toString());
				checkValidity(shop.getPurchases());
				loadPurchasesTableModel(purchasesTable, shop.getPurchases());
			}
		});
		dateButton.setBounds(335, 278, 89, 23);
		frame.getContentPane().add(dateButton);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bungeePanel.setVisible(false);
				buyGiftsPanel.setVisible(true);
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				driftingPanel.setVisible(false);
				buyGiftsPanel.setVisible(true);
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				skydivingPanel.setVisible(false);
				buyGiftsPanel.setVisible(true);
			}
		});

		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				divingPanel.setVisible(false);
				buyGiftsPanel.setVisible(true);
			}
		});

		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				customersPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});

		backBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				purchasesPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});

		btnNewButton_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buyGiftsPanel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});

		loginRadioBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				emailLabel.setVisible(false);
				emailTextField.setVisible(false);
				nameLabel.setVisible(false);
				nameTextField.setVisible(false);
				registerRadioBtn.setSelected(false);
				loginBtn.setText("Login");
			}
		});
		registerRadioBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				emailLabel.setVisible(true);
				emailTextField.setVisible(true);
				nameLabel.setVisible(true);
				nameTextField.setVisible(true);
				loginRadioBtn.setSelected(false);
				loginBtn.setText("Register");
			}
		});
		
	}

	private String getLoggedInUser(String loggedInLabel) {
		return loggedInLabel.substring(loggedInLabel.indexOf(':') + 2);
	}

	private void loadBungeeTableModel(JTable table, List<Gift> gifts) {
		Object[][] arr = new Object[gifts.size()][4];
		for (int i = 0; i < gifts.size(); i++) {
			BungeeGift g = (BungeeGift) gifts.get(i);
			arr[i] = new Object[] { g.getName(), g.getValidity(), g.getPrice(), g.getHeight() };
		}
		table.setModel(
				new DefaultTableModel(arr, new String[] { "New column", "New column", "New column", "New column" }));
	}

	private void loadDriftingTableModel(JTable table, List<Gift> gifts) {
		Object[][] arr = new Object[gifts.size()][5];
		for (int i = 0; i < gifts.size(); i++) {
			DriftingGift g = (DriftingGift) gifts.get(i);
			arr[i] = new Object[] { g.getName(), g.getValidity(), g.getPrice(), g.getCarBrand(), g.getHorsePower() };
		}
		table.setModel(new DefaultTableModel(arr,
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
	}

	private void loadDivingTableModel(JTable table, List<Gift> gifts) {
		Object[][] arr = new Object[gifts.size()][5];
		for (int i = 0; i < gifts.size(); i++) {
			DivingGift g = (DivingGift) gifts.get(i);
			arr[i] = new Object[] { g.getName(), g.getValidity(), g.getPrice(), g.getDepth(), g.getDuration() };
		}
		table.setModel(new DefaultTableModel(arr,
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
	}

	private void loadSkydivingTableModel(JTable table, List<Gift> gifts) {
		Object[][] arr = new Object[gifts.size()][5];
		for (int i = 0; i < gifts.size(); i++) {
			SkyDivingGift g = (SkyDivingGift) gifts.get(i);
			arr[i] = new Object[] { g.getName(), g.getValidity(), g.getPrice(), g.getHeight(), g.getMaxSpeed() };
		}
		table.setModel(new DefaultTableModel(arr,
				new String[] { "New column", "New column", "New column", "New column", "New column" }));
	}
	
	private void loadCustomersTableModel(JTable table, List<Customer> customers) {
		Object[][] arr = new Object[customers.size()][3];

		
		String username = this.getLoggedInUser(loggedInLabel.getText());
		int cnt = 0;
		for (int i = 0; i < customers.size(); i++) {
			Customer c = customers.get(i);
			if (username.equals("admin") || username.equals(c.getUsername())) {
				arr[cnt++] = new Object[] { c.getUsername(), c.getFullName(), c.getEmail() };
			}
		}
		table.setModel(
				new DefaultTableModel(arr, new String[] { "New column", "New Column", "New Column" }));
	}
	
	private void loadPurchasesTableModel(JTable table, List<Purchase> purchases) {
		Object[][] arr = new Object[purchases.size()][3];
		int cnt = 0;
		String username = this.getLoggedInUser(loggedInLabel.getText());
		for(int i = 0; i < purchases.size(); i++) {
			Purchase p = purchases.get(i);
			if(username.equals(p.getUsername()) || username.equals("admin")) {
				Gift g = giftIdMap.get(p.getGiftId());
				boolean bool = false;
				if(this.date.getDate().isAfter((p.getDate().plusMonths(g.getValidity()))))
					bool = true;
				arr[cnt++] = new Object[] { g.getName(), p.getDate(), p.getDate().plusMonths(g.getValidity()), bool };
			}
		}
		table.setModel(
				new DefaultTableModel(arr, new String[] { "New column", "New Column", "New Column", "New column"}));
	}
	
	private void checkValidity(List<Purchase> purchases) {
		for(int i = 0; i < purchasesMap.size(); i++) {
			/*
			 * Purchase p = purchases.get(i); Gift g = giftIdMap.get(p.getGiftId());
			 * if(this.date.getDate().isAfter((p.getDate().plusMonths(g.getValidity())))) {
			 * purchases.remove(p); }
			 */
		}
    }
}
