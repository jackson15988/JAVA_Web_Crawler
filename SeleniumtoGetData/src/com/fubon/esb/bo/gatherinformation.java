package com.fubon.esb.bo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.fubon.esb.core.Login;
import com.fubon.esb.util.LineNotify;

public class gatherinformation {

	private JFrame frame;
	private static JTextField readPathText;

	private static String path = "";
	private static String sec = "";
	private static String strategyName = "";
	private static boolean isDemoRun = true;
	private boolean airshipCheckBoxValue = false; // 幸運飛艇是否有打開值
	private boolean speedRacingCheckBoxValue = false; // 極速賽車是否有打開值
	private boolean stegosaurusCheckBoxValue = false; // 劍龍策略是否有開啟
	private String luckBitMoneyList[] = null; // 下單陣列 這要回傳後台的
	private String racngCarBitMoneyList[] = null; // 下單陣列 這要回傳後台的
	private String stegosaurusBitMoneyList[] = null; // 下單陣列 這要回傳後台的
	private String stegosaurusDouble; // 劍龍策略 掛之後澳加碼幾倍
	private boolean StegosaurusAutomaticRenewalinit;
	private String lineGoldenKey = ""; // LINE 金鑰匙
	private String googleDriverPathStr = "";
	private boolean sizeSingleAndDoubleCheckbox;

	private static JTextField delaySecondsText;
	private static JTextField strategyNameText;
	private static JTextField luckyAirshipOrderAmount; // 幸運飛艇下單金額
	private static JTextField carOrderAmount; // 極速賽車下單金額
	private JTextField checkAccount; // 檢核帳號
	private String version = "V1.3.14";
	private JTextField textField;
	private static JTextField stegosaurusBitMony;
	private static JTextField stegosaurusDoubletext;
	private JPasswordField passwordField;
	private static JTextField LINEGoldenKey;
	private static JTextField googleDriverPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			getPropValues("C:/Users/IMI-JAVA-Ryan/Desktop/gould.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public gatherinformation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("金石彩票全自動化交易系統");
		readPathText = new JTextField(); // 讀取路徑
		delaySecondsText = new JTextField(); // 延遲秒數
		strategyNameText = new JTextField(); // 策略名稱

		// new 出來區域
		JCheckBox luckyAirshipCheckBox = new JCheckBox("酉道飛艇策略");
		JCheckBox speedRacingCheckBox = new JCheckBox("運行極速賽車");
		JCheckBox stegosaurusCheckBox = new JCheckBox("劍龍策略");

		frame.setBounds(100, 100, 891, 553);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, delaySecondsText, 1, SpringLayout.NORTH, luckyAirshipCheckBox);
		springLayout.putConstraint(SpringLayout.WEST, readPathText, 126, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, strategyNameText, 14, SpringLayout.SOUTH, readPathText);
		springLayout.putConstraint(SpringLayout.EAST, strategyNameText, 0, SpringLayout.EAST, readPathText);
		springLayout.putConstraint(SpringLayout.EAST, readPathText, -550, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);

		JButton srartbutton = new JButton("開始運行");
		springLayout.putConstraint(SpringLayout.WEST, srartbutton, 439, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(srartbutton);
		srartbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("程式開始運行．．．");
				if (strategyName.isEmpty() || path.isEmpty() || sec.isEmpty() || luckBitMoneyList == null
						|| racngCarBitMoneyList == null || lineGoldenKey == null) {
					JOptionPane.showMessageDialog(null, "您未設置運行參數，或尚未按保存設定!!", "錯誤", JOptionPane.ERROR_MESSAGE);
				} else {
					HashMap<String, int[]> betList = new HashMap<>();
					// 兩套策略金額要回傳
					betList.put("luckAirshpBitMoney", StringArrToIntArr(luckBitMoneyList));
					betList.put("carRacingBitMoney", StringArrToIntArr(racngCarBitMoneyList));
					betList.put("stegosaurusBitMoney", StringArrToIntArr(stegosaurusBitMoneyList));

					System.out.println("幸運飛艇下注金額:" + luckBitMoneyList);

					HashMap<String, Boolean> strategySwitch = new HashMap<>();
					strategySwitch.put("airshipCheckBoxValue", airshipCheckBoxValue);
					strategySwitch.put("speedRacingCheckBoxValue", speedRacingCheckBoxValue);
					strategySwitch.put("stegosaurusCheckBoxValue", stegosaurusCheckBoxValue);
					strategySwitch.put("sizeSingleAndDoubleCheckbox", sizeSingleAndDoubleCheckbox);

					System.out.println("幸運飛艇是否打開值:" + airshipCheckBoxValue);
					System.out.println("極速賽車是否有打開值:" + speedRacingCheckBoxValue);
					System.out.println("劍龍策略是否有打開值:" + stegosaurusCheckBoxValue);

					HashMap<String, String> accPas = new HashMap<>();
					accPas.put("account", "jackson15988");
					accPas.put("password", "536225ab");

					HashMap<String, Object> otherParameters = new HashMap<>();
					otherParameters.put("rxecutiveRacing", "1"); //
					otherParameters.put("stegosaurusDouble", stegosaurusDouble); // 建隆策略掛之後加碼幾倍
					otherParameters.put("isDemoRun", isDemoRun);
					otherParameters.put("StegosaurusAutomaticRenewalinit", StegosaurusAutomaticRenewalinit);
					otherParameters.put("lineGoldenKey", lineGoldenKey);
					otherParameters.put("googleDriverPathStr", googleDriverPathStr);

					try {
						Login.loginWEB(path, strategyName, betList, strategySwitch, accPas, otherParameters);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				try {

				} catch (Exception e1) {

				}
			}
		});

		JButton btnNewsaveSettings = new JButton("停止運行");
		springLayout.putConstraint(SpringLayout.EAST, delaySecondsText, 0, SpringLayout.EAST, btnNewsaveSettings);
		JLabel AirshipOrderText = new JLabel("極速賽車下單順序");
		springLayout.putConstraint(SpringLayout.NORTH, srartbutton, 0, SpringLayout.NORTH, btnNewsaveSettings);
		springLayout.putConstraint(SpringLayout.EAST, srartbutton, -6, SpringLayout.WEST, btnNewsaveSettings);
		springLayout.putConstraint(SpringLayout.WEST, btnNewsaveSettings, 532, SpringLayout.WEST,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewsaveSettings, -10, SpringLayout.SOUTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewsaveSettings, -34, SpringLayout.EAST,
				frame.getContentPane());
		frame.getContentPane().add(btnNewsaveSettings);
		btnNewsaveSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("中止");
				try {

					System.exit(1);
				} catch (Exception e1) {

				}
			}
		});

		JLabel label = new JLabel("彩哥哥讀取檔案路徑");
		springLayout.putConstraint(SpringLayout.EAST, label, -755, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, readPathText, -3, SpringLayout.NORTH, label);
		frame.getContentPane().add(label);
		frame.getContentPane().add(readPathText);
		readPathText.setColumns(10);

		JButton button = new JButton("預設");
		springLayout.putConstraint(SpringLayout.NORTH, button, 0, SpringLayout.NORTH, srartbutton);
		frame.getContentPane().add(button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					readPathText.setText("D:/LuckeyAirship/20200225.txt");
					delaySecondsText.setText("40");
					strategyNameText.setText("彩票A計畫");
					luckyAirshipOrderAmount.setText("10,20,40,80,160,320,650,1310");
					carOrderAmount.setText("10,20,40,80,160,320,650,1310");
					stegosaurusBitMony.setText("10,20,40");
					stegosaurusDoubletext.setText("2");
					LINEGoldenKey.setText("3icAXIGgf2a6FdtX6h8bbWAl5Q3EY9lZBdS69Ki8mi4");
					googleDriverPath.setText("C:/Users/admin/Desktop/chromedriver.exe");

				} catch (Exception e1) {

				}
			}
		});

		JButton saveSettings = new JButton("我叫做讓程式立即吃這設定");
		springLayout.putConstraint(SpringLayout.EAST, button, -6, SpringLayout.WEST, saveSettings);
		springLayout.putConstraint(SpringLayout.NORTH, saveSettings, 0, SpringLayout.NORTH, srartbutton);
		springLayout.putConstraint(SpringLayout.EAST, saveSettings, -6, SpringLayout.WEST, srartbutton);
		frame.getContentPane().add(saveSettings);
		frame.getContentPane().add(delaySecondsText);
		delaySecondsText.setColumns(10);

		JLabel label_1 = new JLabel("延遲秒數");
		springLayout.putConstraint(SpringLayout.SOUTH, label_1, 0, SpringLayout.SOUTH, delaySecondsText);
		springLayout.putConstraint(SpringLayout.EAST, label_1, -6, SpringLayout.WEST, delaySecondsText);
		frame.getContentPane().add(label_1);
		frame.getContentPane().add(strategyNameText);
		strategyNameText.setColumns(10);

		JLabel label_2 = new JLabel("策略名稱");
		springLayout.putConstraint(SpringLayout.NORTH, label_2, 94, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, label_2, -755, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, label, -20, SpringLayout.NORTH, label_2);
		frame.getContentPane().add(label_2);

		JLabel label_3 = new JLabel("幸運飛艇下注排序");
		frame.getContentPane().add(label_3);

		luckyAirshipOrderAmount = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, strategyNameText, 0, SpringLayout.WEST, luckyAirshipOrderAmount);
		springLayout.putConstraint(SpringLayout.WEST, luckyAirshipOrderAmount, 126, SpringLayout.WEST,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, luckyAirshipOrderAmount, -356, SpringLayout.EAST,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, label_3, 3, SpringLayout.NORTH, luckyAirshipOrderAmount);
		springLayout.putConstraint(SpringLayout.EAST, label_3, -6, SpringLayout.WEST, luckyAirshipOrderAmount);
		frame.getContentPane().add(luckyAirshipOrderAmount);
		luckyAirshipOrderAmount.setColumns(10);

		springLayout.putConstraint(SpringLayout.EAST, AirshipOrderText, 0, SpringLayout.EAST, label);
		frame.getContentPane().add(AirshipOrderText);

		carOrderAmount = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, carOrderAmount, 170, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, AirshipOrderText, 3, SpringLayout.NORTH, carOrderAmount);
		springLayout.putConstraint(SpringLayout.SOUTH, luckyAirshipOrderAmount, -18, SpringLayout.NORTH,
				carOrderAmount);
		springLayout.putConstraint(SpringLayout.WEST, carOrderAmount, 126, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, carOrderAmount, 0, SpringLayout.EAST, luckyAirshipOrderAmount);
		frame.getContentPane().add(carOrderAmount);
		carOrderAmount.setColumns(10);

		springLayout.putConstraint(SpringLayout.NORTH, luckyAirshipCheckBox, 28, SpringLayout.SOUTH, carOrderAmount);
		springLayout.putConstraint(SpringLayout.WEST, luckyAirshipCheckBox, 0, SpringLayout.WEST, readPathText);
		frame.getContentPane().add(luckyAirshipCheckBox);
		luckyAirshipCheckBox.isSelected();

		checkAccount = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, checkAccount, 7, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, checkAccount, -314, SpringLayout.EAST, btnNewsaveSettings);
		frame.getContentPane().add(checkAccount);
		checkAccount.setColumns(10);

		JLabel label_5 = new JLabel("運行帳號");
		springLayout.putConstraint(SpringLayout.NORTH, label_5, 3, SpringLayout.NORTH, checkAccount);
		springLayout.putConstraint(SpringLayout.EAST, label_5, -6, SpringLayout.WEST, checkAccount);
		frame.getContentPane().add(label_5);

		springLayout.putConstraint(SpringLayout.NORTH, speedRacingCheckBox, 0, SpringLayout.NORTH,
				luckyAirshipCheckBox);
		springLayout.putConstraint(SpringLayout.WEST, speedRacingCheckBox, 6, SpringLayout.EAST, luckyAirshipCheckBox);
		frame.getContentPane().add(speedRacingCheckBox);

		JLabel lblNewLabel = new JLabel("運行策略方案");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 35, SpringLayout.SOUTH, AirshipOrderText);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 0, SpringLayout.EAST, label);
		frame.getContentPane().add(lblNewLabel);

		JLabel label_6 = new JLabel("");
		springLayout.putConstraint(SpringLayout.WEST, label_6, 314, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, label_6, 0, SpringLayout.SOUTH, srartbutton);
		frame.getContentPane().add(label_6);

		JLabel lblVersion = new JLabel("版本號碼 : " + version);
		springLayout.putConstraint(SpringLayout.NORTH, lblVersion, 4, SpringLayout.NORTH, srartbutton);
		springLayout.putConstraint(SpringLayout.WEST, lblVersion, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblVersion);

		JCheckBox sizeSingleAndDouble = new JCheckBox("官網大小單雙");
		springLayout.putConstraint(SpringLayout.WEST, sizeSingleAndDouble, 6, SpringLayout.EAST, speedRacingCheckBox);
		springLayout.putConstraint(SpringLayout.SOUTH, sizeSingleAndDouble, 0, SpringLayout.SOUTH,
				luckyAirshipCheckBox);
		frame.getContentPane().add(sizeSingleAndDouble);

		springLayout.putConstraint(SpringLayout.NORTH, stegosaurusCheckBox, 6, SpringLayout.SOUTH,
				luckyAirshipCheckBox);
		springLayout.putConstraint(SpringLayout.WEST, stegosaurusCheckBox, 0, SpringLayout.WEST, readPathText);
		frame.getContentPane().add(stegosaurusCheckBox);

		JLabel label_4 = new JLabel("官網大小單雙");
		springLayout.putConstraint(SpringLayout.WEST, label_4, 16, SpringLayout.EAST, luckyAirshipOrderAmount);
		springLayout.putConstraint(SpringLayout.SOUTH, label_4, 0, SpringLayout.SOUTH, label_3);
		frame.getContentPane().add(label_4);

		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, label_4);
		springLayout.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, btnNewsaveSettings);
		textField.setColumns(10);
		frame.getContentPane().add(textField);

		JLabel lblNewLabel_1 = new JLabel("劍龍策略籌碼");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, AirshipOrderText);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, label_4);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel label_7 = new JLabel("剩餘點數:1005");
		springLayout.putConstraint(SpringLayout.NORTH, label_7, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, label_7, 0, SpringLayout.WEST, label_3);
		frame.getContentPane().add(label_7);

		stegosaurusBitMony = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, stegosaurusBitMony, 170, SpringLayout.NORTH,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -18, SpringLayout.NORTH, stegosaurusBitMony);
		springLayout.putConstraint(SpringLayout.WEST, stegosaurusBitMony, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.EAST, stegosaurusBitMony, -112, SpringLayout.EAST, btnNewsaveSettings);
		stegosaurusBitMony.setColumns(10);
		frame.getContentPane().add(stegosaurusBitMony);

		JLabel label_8 = new JLabel("掛加碼幾倍");
		springLayout.putConstraint(SpringLayout.NORTH, label_8, 0, SpringLayout.NORTH, AirshipOrderText);
		springLayout.putConstraint(SpringLayout.WEST, label_8, 6, SpringLayout.EAST, stegosaurusBitMony);
		frame.getContentPane().add(label_8);

		stegosaurusDoubletext = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, stegosaurusDoubletext, -3, SpringLayout.NORTH, AirshipOrderText);
		springLayout.putConstraint(SpringLayout.WEST, stegosaurusDoubletext, -71, SpringLayout.EAST,
				frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, stegosaurusDoubletext, 0, SpringLayout.EAST, btnNewsaveSettings);
		frame.getContentPane().add(stegosaurusDoubletext);
		stegosaurusDoubletext.setColumns(10);

		JLabel label_9 = new JLabel("劍龍策略加碼層次");
		springLayout.putConstraint(SpringLayout.NORTH, label_9, 48, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, label_9, 0, SpringLayout.EAST, label);
		frame.getContentPane().add(label_9);

		JLabel label_11 = new JLabel("運行密碼");
		springLayout.putConstraint(SpringLayout.NORTH, label_11, 3, SpringLayout.NORTH, checkAccount);
		springLayout.putConstraint(SpringLayout.WEST, label_11, 0, SpringLayout.WEST, btnNewsaveSettings);
		frame.getContentPane().add(label_11);

		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 0, SpringLayout.NORTH, checkAccount);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 6, SpringLayout.EAST, label_11);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, -147, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(passwordField);

		JButton doubleButton = new JButton("劍龍兩倍加碼");
		springLayout.putConstraint(SpringLayout.WEST, doubleButton, 0, SpringLayout.WEST, readPathText);
		frame.getContentPane().add(doubleButton);

		doubleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login.stegosaurusOverweight = 1;
					LineNotify.callEvent(lineGoldenKey, "劍龍策略加碼這裡進行兩倍加碼!!");
				} catch (Exception e1) {

				}
			}
		});

		JButton quadrupling = new JButton("劍龍四倍加碼");
		springLayout.putConstraint(SpringLayout.NORTH, quadrupling, 6, SpringLayout.SOUTH, doubleButton);
		springLayout.putConstraint(SpringLayout.WEST, quadrupling, 0, SpringLayout.WEST, readPathText);
		frame.getContentPane().add(quadrupling);

		quadrupling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login.stegosaurusOverweight = 2;
					LineNotify.callEvent(lineGoldenKey, "劍龍策略加碼這裡進行4倍加碼!!");
				} catch (Exception e1) {

				}
			}
		});

		JButton eightTimesPlus = new JButton("劍龍八倍加碼");
		springLayout.putConstraint(SpringLayout.NORTH, eightTimesPlus, 6, SpringLayout.SOUTH, quadrupling);
		springLayout.putConstraint(SpringLayout.EAST, eightTimesPlus, 0, SpringLayout.EAST, doubleButton);
		frame.getContentPane().add(eightTimesPlus);

		eightTimesPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login.stegosaurusOverweight = 3;
					LineNotify.callEvent(lineGoldenKey, "劍龍策略加碼這裡進行8倍加碼!!");
				} catch (Exception e1) {

				}
			}
		});

		JButton pauseButton = new JButton("暫停下單");
		springLayout.putConstraint(SpringLayout.NORTH, pauseButton, 6, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pauseButton, 0, SpringLayout.EAST, stegosaurusCheckBox);
		frame.getContentPane().add(pauseButton);

		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login.stegosaurusstrategyTimeout = false;
					LineNotify.callEvent(lineGoldenKey, "已經暫時停止下單....!!!");
				} catch (Exception e1) {

				}
			}
		});

		JButton startBet = new JButton("(掛之後)開始下單");
		springLayout.putConstraint(SpringLayout.NORTH, startBet, 6, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, checkAccount, 99, SpringLayout.EAST, startBet);
		springLayout.putConstraint(SpringLayout.WEST, startBet, 6, SpringLayout.EAST, pauseButton);
		frame.getContentPane().add(startBet);

		JButton clearOverweightStatus = new JButton("清除加碼狀態");
		springLayout.putConstraint(SpringLayout.NORTH, clearOverweightStatus, 30, SpringLayout.SOUTH, eightTimesPlus);
		springLayout.putConstraint(SpringLayout.WEST, clearOverweightStatus, 0, SpringLayout.WEST, readPathText);
		springLayout.putConstraint(SpringLayout.EAST, clearOverweightStatus, 8, SpringLayout.EAST,
				luckyAirshipCheckBox);
		frame.getContentPane().add(clearOverweightStatus);

		JCheckBox isDemoRunCheckBox = new JCheckBox("模擬倉運行");
		isDemoRunCheckBox.setSelected(true);
		springLayout.putConstraint(SpringLayout.NORTH, isDemoRunCheckBox, 0, SpringLayout.NORTH, checkAccount);
		springLayout.putConstraint(SpringLayout.WEST, isDemoRunCheckBox, 40, SpringLayout.EAST, passwordField);
		frame.getContentPane().add(isDemoRunCheckBox);

		JLabel lblLine = new JLabel("line機器人金鑰");
		springLayout.putConstraint(SpringLayout.NORTH, lblLine, 0, SpringLayout.NORTH, label);
		springLayout.putConstraint(SpringLayout.EAST, lblLine, 0, SpringLayout.EAST, srartbutton);
		frame.getContentPane().add(lblLine);

		LINEGoldenKey = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, LINEGoldenKey, -3, SpringLayout.NORTH, label);
		springLayout.putConstraint(SpringLayout.WEST, LINEGoldenKey, 0, SpringLayout.WEST, btnNewsaveSettings);
		springLayout.putConstraint(SpringLayout.EAST, LINEGoldenKey, -54, SpringLayout.EAST, btnNewsaveSettings);
		frame.getContentPane().add(LINEGoldenKey);
		LINEGoldenKey.setColumns(10);

		JLabel lblGooglechrome = new JLabel("Google驅動路徑");
		springLayout.putConstraint(SpringLayout.NORTH, lblGooglechrome, 3, SpringLayout.NORTH, strategyNameText);
		springLayout.putConstraint(SpringLayout.WEST, lblGooglechrome, 6, SpringLayout.EAST, strategyNameText);
		frame.getContentPane().add(lblGooglechrome);

		googleDriverPath = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, googleDriverPath, -96, SpringLayout.EAST, srartbutton);
		springLayout.putConstraint(SpringLayout.SOUTH, googleDriverPath, 0, SpringLayout.SOUTH, strategyNameText);
		springLayout.putConstraint(SpringLayout.EAST, googleDriverPath, 223, SpringLayout.EAST, srartbutton);
		frame.getContentPane().add(googleDriverPath);
		googleDriverPath.setColumns(10);

		clearOverweightStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login.stegosaurusOverweight = 0;
					LineNotify.callEvent(lineGoldenKey, "手動清除加碼狀態");
				} catch (Exception e1) {

				}
			}
		});

		startBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login.stegosaurusstrategyTimeout = true;
					LineNotify.callEvent(lineGoldenKey, "繼續開始下單....!!");
				} catch (Exception e1) {

				}
			}
		});

		JButton browseCaiBrother = new JButton("瀏覽路徑");
		springLayout.putConstraint(SpringLayout.NORTH, browseCaiBrother, -4, SpringLayout.NORTH, label);
		springLayout.putConstraint(SpringLayout.WEST, browseCaiBrother, 6, SpringLayout.EAST, readPathText);
		springLayout.putConstraint(SpringLayout.EAST, browseCaiBrother, -439, SpringLayout.EAST,
				frame.getContentPane());
		browseCaiBrother.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();// 宣告filechooser
				int returnValue = fileChooser.showOpenDialog(null);// 叫出filechooser
				if (returnValue == JFileChooser.APPROVE_OPTION) // 判斷是否選擇檔案
				{
					File selectedFile = fileChooser.getSelectedFile();// 指派給File
					System.out.println(selectedFile.getPath()); // 印出檔名
					String path = selectedFile.getPath();
					path = path.replaceAll("\\\\", "\\/");
					readPathText.setText(path);
				}
			}
		});
		frame.getContentPane().add(browseCaiBrother);

		JButton browserDriver = new JButton("瀏覽路徑");
		springLayout.putConstraint(SpringLayout.NORTH, browserDriver, -1, SpringLayout.NORTH, strategyNameText);
		springLayout.putConstraint(SpringLayout.EAST, browserDriver, 0, SpringLayout.EAST, btnNewsaveSettings);
		frame.getContentPane().add(browserDriver);

		browserDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();// 宣告filechooser
				int returnValue = fileChooser.showOpenDialog(null);// 叫出filechooser
				if (returnValue == JFileChooser.APPROVE_OPTION) // 判斷是否選擇檔案
				{
					File selectedFile = fileChooser.getSelectedFile();// 指派給File
					System.out.println(selectedFile.getPath()); // 印出檔名
					String path = selectedFile.getPath();
					path = path.replaceAll("\\\\", "\\/");
					googleDriverPath.setText(path);
				}
			}
		});

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("執行冠軍策略");
		chckbxNewCheckBox_1.setSelected(true);
		springLayout.putConstraint(SpringLayout.NORTH, doubleButton, 6, SpringLayout.SOUTH, chckbxNewCheckBox_1);
		springLayout.putConstraint(SpringLayout.NORTH, chckbxNewCheckBox_1, -4, SpringLayout.NORTH, label_9);
		springLayout.putConstraint(SpringLayout.EAST, chckbxNewCheckBox_1, 0, SpringLayout.EAST, luckyAirshipCheckBox);
		frame.getContentPane().add(chckbxNewCheckBox_1);

		JCheckBox checkBox_1 = new JCheckBox("執行亞軍策略");
		springLayout.putConstraint(SpringLayout.NORTH, checkBox_1, 0, SpringLayout.NORTH, chckbxNewCheckBox_1);
		springLayout.putConstraint(SpringLayout.WEST, checkBox_1, 30, SpringLayout.EAST, chckbxNewCheckBox_1);
		frame.getContentPane().add(checkBox_1);

		JButton doubleButton_1 = new JButton("劍龍兩倍加碼");
		springLayout.putConstraint(SpringLayout.NORTH, doubleButton_1, 0, SpringLayout.NORTH, doubleButton);
		springLayout.putConstraint(SpringLayout.WEST, doubleButton_1, 0, SpringLayout.WEST, checkBox_1);
		frame.getContentPane().add(doubleButton_1);

		JButton quadrupling_1 = new JButton("劍龍四倍加碼");
		springLayout.putConstraint(SpringLayout.NORTH, quadrupling_1, 0, SpringLayout.NORTH, quadrupling);
		springLayout.putConstraint(SpringLayout.WEST, quadrupling_1, 0, SpringLayout.WEST, checkBox_1);
		frame.getContentPane().add(quadrupling_1);

		JButton eightTimesPlus_1 = new JButton("劍龍八倍加碼");
		springLayout.putConstraint(SpringLayout.WEST, eightTimesPlus_1, 0, SpringLayout.WEST, checkBox_1);
		springLayout.putConstraint(SpringLayout.SOUTH, eightTimesPlus_1, 0, SpringLayout.SOUTH, eightTimesPlus);
		frame.getContentPane().add(eightTimesPlus_1);

		JCheckBox checkBox_2 = new JCheckBox("執行第三名");
		springLayout.putConstraint(SpringLayout.NORTH, checkBox_2, -4, SpringLayout.NORTH, label_9);
		springLayout.putConstraint(SpringLayout.WEST, checkBox_2, 33, SpringLayout.EAST, checkBox_1);
		frame.getContentPane().add(checkBox_2);

		JButton doubleButton_1_1 = new JButton("劍龍兩倍加碼");
		springLayout.putConstraint(SpringLayout.NORTH, doubleButton_1_1, 0, SpringLayout.NORTH, doubleButton);
		springLayout.putConstraint(SpringLayout.WEST, doubleButton_1_1, 0, SpringLayout.WEST, label_5);
		frame.getContentPane().add(doubleButton_1_1);

		JButton quadrupling_1_1 = new JButton("劍龍四倍加碼");
		springLayout.putConstraint(SpringLayout.WEST, quadrupling_1_1, 0, SpringLayout.WEST, label_5);
		springLayout.putConstraint(SpringLayout.SOUTH, quadrupling_1_1, 0, SpringLayout.SOUTH, quadrupling);
		frame.getContentPane().add(quadrupling_1_1);

		JButton eightTimesPlus_1_1 = new JButton("劍龍八倍加碼");
		springLayout.putConstraint(SpringLayout.SOUTH, eightTimesPlus_1_1, 0, SpringLayout.SOUTH, eightTimesPlus);
		springLayout.putConstraint(SpringLayout.EAST, eightTimesPlus_1_1, 0, SpringLayout.EAST, doubleButton_1_1);
		frame.getContentPane().add(eightTimesPlus_1_1);

		JCheckBox stegosaurusAutomaticRenewalCheckBox = new JCheckBox("劍龍自動續單");
		stegosaurusAutomaticRenewalCheckBox.setSelected(true);
		springLayout.putConstraint(SpringLayout.NORTH, stegosaurusAutomaticRenewalCheckBox, 0, SpringLayout.NORTH,
				doubleButton);
		springLayout.putConstraint(SpringLayout.EAST, stegosaurusAutomaticRenewalCheckBox, 0, SpringLayout.EAST, label);
		frame.getContentPane().add(stegosaurusAutomaticRenewalCheckBox);

		saveSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					path = readPathText.getText();
					sec = delaySecondsText.getText();
					strategyName = strategyNameText.getText();
					airshipCheckBoxValue = luckyAirshipCheckBox.isSelected(); // 設定airship是否有開啟
					String luckBitMoney = luckyAirshipOrderAmount.getText();
					luckBitMoneyList = luckBitMoney.split(",");
					String carRacingStr = carOrderAmount.getText();
					racngCarBitMoneyList = carRacingStr.split(",");
					String stegosaurusStr = stegosaurusBitMony.getText();
					stegosaurusBitMoneyList = stegosaurusStr.split(",");
					stegosaurusDouble = stegosaurusDoubletext.getText();
					speedRacingCheckBoxValue = speedRacingCheckBox.isSelected();
					stegosaurusCheckBoxValue = stegosaurusCheckBox.isSelected();
					isDemoRun = isDemoRunCheckBox.isSelected();
					Login.stegosaurusAutomaticRenewal = stegosaurusAutomaticRenewalCheckBox.isSelected();
					StegosaurusAutomaticRenewalinit = stegosaurusAutomaticRenewalCheckBox.isSelected();
					lineGoldenKey = LINEGoldenKey.getText();
					googleDriverPathStr = googleDriverPath.getText();
					sizeSingleAndDoubleCheckbox = sizeSingleAndDouble.isSelected();

				} catch (Exception e1) {

				}
			}
		});
	}

	public static int[] StringArrToIntArr(String[] s) {
		int[] result = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			result[i] = Integer.parseInt(s[i]);
		}
		return result;
	}

	public static String getPropValues(String propetersPath) throws IOException {
		InputStream inputStream = null;
		String result = "";
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = new FileInputStream(propetersPath);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			Date time = new Date(System.currentTimeMillis());
			readPathText.setText(prop.getProperty("readPathText"));
			delaySecondsText.setText(prop.getProperty("readPathText"));
			strategyNameText.setText(prop.getProperty("readPathText"));
			luckyAirshipOrderAmount.setText(prop.getProperty("readPathText"));
			carOrderAmount.setText(prop.getProperty("readPathText"));
			stegosaurusBitMony.setText(prop.getProperty("readPathText"));
			stegosaurusDoubletext.setText(prop.getProperty("readPathText"));
			LINEGoldenKey.setText(prop.getProperty("readPathText"));
			googleDriverPath.setText(prop.getProperty("readPathText"));

			result = "";
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}

}
