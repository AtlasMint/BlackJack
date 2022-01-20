import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

//===================================================================================

/* The task now is just to decorate the GUI so it looks fab and fresh
 *  1. Background, understanding of environment
 *  2. Buttons, fresh, intimidating look
 *  	- make sure to implement the border thing onto them
 *  3. Boundaries, exquisite, comfortable
 *  4. Interface, inviting, challenging
 */

//===================================================================================

public class RunThis {
	private static final String Welcome = "Welcome";
	private static final String LogIn = "log in";
	private static final String Game = "game";
	
	private CardLayout cardLayout = new CardLayout();
	private JPanel mainPanel = new JPanel(cardLayout);
	private WelcomePanel welcomePanel = new WelcomePanel();
	private LogInPanel logInPanel = new LogInPanel();
	//private GamePanel gamePanel = new GamePanel();
	private PlayPanel playPanel = new PlayPanel();
	
	private static String[][] allRecords;
	private boolean selStatus = false;
	private int selIndex;
	
	public LogInTask logInTask;
	public static CardIcons cardIcons;
	//public PlayerNow playerNow;
	
	public RunThis() {
		mainPanel.add(welcomePanel.getMainPanel(), Welcome);
		mainPanel.add(logInPanel.getMainPanel(), LogIn);
		mainPanel.add(playPanel.getMainPanel(), Game);
		
		SwitchLayoutButtHandler slButtHandler = new SwitchLayoutButtHandler();
		
		welcomePanel.addStartButtActionListener(slButtHandler);
		logInPanel.addPlayButtActionListener(slButtHandler);
		logInPanel.addBackButtActionListener(slButtHandler);
		logInPanel.addEnterButtActionListener(slButtHandler);
		playPanel.addHitButtActionListener(slButtHandler);
		playPanel.addStandButtActionListener(slButtHandler);
		playPanel.addQuitButtActionListener(slButtHandler);
		
	}//RunThis()
	
	private JComponent getMainPanel() {
		return mainPanel;
	}//getMainPanel()
	
	private static void createAndShowUI() {
		cardIcons = new CardIcons();
		JFrame frame = new JFrame("Black Jack");
		frame.setIconImage(cardIcons.getCardBack().getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().add(new RunThis().getMainPanel());
		frame.setSize(900,700); //size of frame is 900 x 700
		frame.setResizable(false);
	    //frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}//createAndShowUI()
	
	public static void main(String[] args) {
		
		
		//GUI runs here
		createAndShowUI();
		//BlackJack will in Panels
	}//main(string[] args)
	
	private class SwitchLayoutButtHandler implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			
			if (event.getActionCommand().equals("Quit") || event.getActionCommand().equals("Back") || event.getActionCommand().equals("Exit to Main Menu")) {
				cardLayout.show(mainPanel, Welcome);
			}
			
			else if (event.getActionCommand().equals("Start") || event.getActionCommand().equals("Change player")) {
				logInPanel.updateProfileTable();
				cardLayout.show(mainPanel, LogIn);
			}
			
			else if (event.getActionCommand().equals("Play again") || (JButton)event.getSource() == logInPanel.getEnterButt()) {
				playPanel.resetGameOver();
				cardLayout.show(mainPanel, Game);
			}
			
			else if (event.getActionCommand().equals("Play") && (JButton)event.getSource() == logInPanel.getPlayButt()) {
				playPanel.resetGameOver();
				if (logInPanel.getSelStatus()) {
					cardLayout.show(mainPanel, Game);
					allRecords = logInPanel.getAllRecords();
					//playerNow = new PlayerNow(allRecords[selIndex]);
				}
			}
			/*
			else if (event.getActionCommand().equals("Hit") || event.getActionCommand().equals("Stand")) {
				//this one works
				// perhaps can be used to switch layout
				//nah, dont want liao
			}
			*/
			//all the if statements
		}//actionPerformed()
	}//class SwitchLayoutButtHandler
	
	//=============================================================================
	
	class WelcomePanel {
		private JPanel mainPanel = new JPanel();
		private JButton startButt = new JButton("Start");
		private JButton exitButt = new JButton("Exit");
		
		public WelcomePanel() {
			//yeah I dont think needed this
			//mainPanel.setLayout(new GridLayout(1,3));
			
			mainPanel.add(startButt);
			mainPanel.add(exitButt);
			
			exitButt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Window wndw = SwingUtilities.getWindowAncestor(mainPanel);
					wndw.dispose();
				}
			});
		} //WelcomePanel()
		
		public void addStartButtActionListener(ActionListener listener) {
			startButt.addActionListener(listener);
		} //addStartButtActionListener()
		
		public JComponent getMainPanel() {
			return mainPanel;
		} //getMainPanel()
	} //class WelcomePanel

	//================================================================================================================

	class LogInPanel {
		private JPanel mainPanel = new JPanel();
		private JLabel mainLabel = new JLabel("Please select a profile");
		private JButton playButt = new JButton("Play");;
		private JButton backButt = new JButton("Back");;
		private JButton enter = new JButton("Play");
		private JButton newPlayerButt;
		private DefaultTableModel tableModel;
		private JTable profileTable;
		
		private String[] colLabels = {"No.", "Username", "Total games", "Total wins", "Total losses", "Total draws"};
		private boolean allowRowSel = true;
		
		@SuppressWarnings("serial")
		public LogInPanel() {
			/* do listing of all profiles here!!
			 * select one row in JTable
			 *  (it also highlights selected row)
			 * when hit Play button
			 *  should alr check in with the
			 *  player's profile
			 */
			//buttons here
			newPlayerButt = new JButton("New profile");
			playButt.setEnabled(false);
			
			//readerThread runs here
			logInTask = new LogInTask("LogBook.txt");
			logInTask.openReadFile();
			logInTask.readTotalRecords();
			logInTask.createAllRecords();
			logInTask.closeReadFile();
			logInTask.openReadFile();
			logInTask.readAllRecords();
			allRecords = logInTask.getAllRecords();		
			logInTask.closeReadFile();
			
			tableModel = new DefaultTableModel(allRecords, colLabels) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			profileTable = new JTable(tableModel);
			profileTable.setPreferredScrollableViewportSize(new Dimension(500, 100));
	        profileTable.setFillsViewportHeight(true);
			profileTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
	        if (allowRowSel) {
	        	ListSelectionModel rowSM = profileTable.getSelectionModel();
	        	rowSM.addListSelectionListener(new ListSelectionListener() {
	        		public void valueChanged(ListSelectionEvent lse) {
	        			if (lse.getValueIsAdjusting()) return;
	        			
	        			ListSelectionModel lsm = (ListSelectionModel) lse.getSource();
	        			if (lsm.isSelectionEmpty()) { //if no row selected
	        				selStatus = false;
	        				playButt.setEnabled(false);
	                    } else { //if a row is selected
	                    	selStatus = true;
	                    	playButt.setEnabled(true);
	                        selIndex = lsm.getMinSelectionIndex();
	                        //this is selIndex yeahhhh
	                    }
	        		}
	        	});
	        }
	        else {
	        	profileTable.setRowSelectionAllowed(false);
	        }
			//LogInTask loginTask = new LogInTask();
			//PlayerNow playerNow = new PlayerNow();
			
			mainPanel.add(new JScrollPane(profileTable));
			mainPanel.add(playButt);
			mainPanel.add(newPlayerButt);
			mainPanel.add(backButt);
			
			playButt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (selStatus) {
						/* getIndex of JTable (selIndex)
						 * get records of player
						 * set to PlayerNow
						 * then can retrieve or update or whatever
						 */
						
					}
					else {
						//remind to select
						mainLabel.setText("Profile not selected! Please select a profile.");
						mainLabel.setForeground(Color.red);
					}
					
				}
			}); //playButt action listener
			
			//new profile
			JFrame popupFrame = new JFrame("New profile");
			
			newPlayerButt.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!popupFrame.isVisible()) {
						JPanel popupPanel = new JPanel(new GridLayout(3,0));
						JLabel askingLabel = new JLabel("Enter a username");
						JTextField newUsername = new JTextField();
						popupPanel.add(askingLabel);
						popupPanel.add(newUsername);
						popupPanel.add(enter);
						popupFrame.add(popupPanel);
						popupFrame.setLocationRelativeTo(null);
						popupFrame.setSize(400,300);
						popupFrame.setVisible(true);
						
						enter.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (newUsername.getText().equals("")) {
									askingLabel.setForeground(Color.RED);
								}
								
								else {
									String[][] tempo3d = allRecords.clone();
									allRecords = new String[tempo3d.length + 1][tempo3d[0].length];
									/*
									for (int i = 0; i < tempo3d.length; i++) {
										for (int j = 0; j < tempo3d[i].length; j++) {
											System.out.println(tempo3d[i][j]);
										}
									}
									
									System.out.printf("%d %d \n", allRecords.length, tempo3d.length);
									for (int i = 0; i < allRecords.length; i++) {
										for (int j = 0; j < allRecords[i].length; j++) {
											System.out.println(allRecords[i][j]);
										}
									}
									*/
									
									for (int i = 0; i < tempo3d.length; i++) {
										for (int j = 0; j < tempo3d[i].length; j++) {
											allRecords[i][j] = tempo3d[i][j];
										}
									}

									selIndex = allRecords.length - 1;
									allRecords[selIndex] = new String[] {
											Integer.toString(allRecords.length),
											newUsername.getText(),
											"0", "0", "0", "0"};
									Window wndw = SwingUtilities.getWindowAncestor(popupPanel);
									wndw.dispose();
									
								}
							}
						});
					}
				}
			}); //new profile action listener
			
		} //LogInPanel()
		
		@SuppressWarnings("serial")
		public void updateProfileTable() {
			tableModel = new DefaultTableModel(allRecords, colLabels) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			profileTable.setModel(tableModel);
		} //updateProfileTable()
		
		public boolean getSelStatus() {
			return selStatus;
		}
		
		public String[][] getAllRecords() {
			return allRecords;
		}
		
		public JButton getEnterButt() {
			return enter;
		}
		
		public JButton getPlayButt() {
			return playButt;
		}

		public void addEnterButtActionListener(ActionListener listener) {
			enter.addActionListener(listener);
		} //addEnterButtActionListener()
		
		public void addPlayButtActionListener(ActionListener listener) {
			playButt.addActionListener(listener);
		} //addPlayButtActionListener()
		
		public void addBackButtActionListener(ActionListener listener) {
			backButt.addActionListener(listener);
		} //addBackButtActionListener()
		
		public JComponent getMainPanel() {
			return mainPanel;
		} //getMainPanel()

	} //class LogInPanel

	//======================================================================

	class PlayPanel {
		private JPanel mainPanel = new JPanel();
		public String result;
		public boolean gameOver;
		
		//private Font labelFont;
		public JLabel bigLabel = new JLabel();;
		private JLabel p1 = new JLabel();
		private JLabel p2 = new JLabel();
		private JLabel p3 = new JLabel();
		private JLabel p4 = new JLabel();
		private JLabel p5 = new JLabel();
		private JLabel d1 = new JLabel();
		private JLabel d2 = new JLabel();
		private JLabel d3 = new JLabel();
		private JLabel d4 = new JLabel();
		private JLabel d5 = new JLabel();
		private JLabel[] playerCards = new JLabel[] {p1, p2, p3, p4, p5};
		private JLabel[] dealerCards = new JLabel[] {d1, d2, d3, d4, d5};
		
		private JButton hitButt;
		private JButton standButt;
		private JButton quitButt;
		
	    public String[] dealerHands = new String[2];
	    public String[] playerHands = new String[2];
	    public String[] deck;
		public String[] tempx;
		public int remainingCards = 52;
		private String outcomeP, outcomeD;
		public boolean standP = false;
		String output_;
	    HitButtActionListener hitButtAL = new HitButtActionListener(); 
	    StandButtActionListener standButtAL = new StandButtActionListener();
	    
		private String[] makeStandardDeck() {
			String[] deck = new String[52];		
			int indi = 0;
			String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
			String[] kinds = {"Diamond ", "Club ", "Hearts ", "Spade "};
			
			while (indi < 52) {
			    for (int i = 0; i < 4; i++) {
			        for (int j = 0; j < 13; j++) {
			                deck[indi] = kinds[i] + ranks[j];
			            indi++;
			        }
			    }
			}	
			
			return deck;
		}
		
		private String[] shuffle(String[] deck){
		    for (int i = 0; i < deck.length; i++) {
			   int index = (int)(Math.random() *deck.length);
			   String temp = deck[i];
			   deck[i] = deck[index];
			   deck[index] = temp;
		    }
		    
		    return deck;
		}
		
		private String[] minusDeck(String[] deck) {
			String[] tempo = new String[remainingCards];
			
			for (int i = 0; i < tempo.length; i++) {
				tempo[i] = deck[i];
			}
			
			return tempo;
		}
		
		private String value(String[] cards) {
			String value = "0";
			
			if (cards[0].endsWith("A") && cards[1].endsWith("A") && cards.length == 2) {
				value = "double A";
			}
			
			else if (cards.length == 2 && cards[0].endsWith("A") && cards[1].endsWith("10")) {
				value = "BJ";
			}
			else if (cards.length == 2 && cards[0].endsWith("A") && cards[1].endsWith("Jack")) {
				value = "BJ";
			}
			else if (cards.length == 2 && cards[0].endsWith("A") && cards[1].endsWith("Queen")) {
				value = "BJ";
			}
			else if (cards.length == 2 && cards[0].endsWith("A") && cards[1].endsWith("King")) {
				value = "BJ";
			}
			else if (cards.length == 2 && cards[1].endsWith("A") && cards[0].endsWith("10")) {
				value = "BJ";
			}
			else if (cards.length == 2 && cards[1].endsWith("A") && cards[0].endsWith("Jack")) {
				value = "BJ";
			}
			else if (cards.length == 2 && cards[1].endsWith("A") && cards[0].endsWith("Queen")){
				value = "BJ";
			}
			else if (cards.length == 2 && cards[1].endsWith("A") && cards[0].endsWith("King")) {
				value = "BJ";
			}
			
			else if (cards.length == 2){
				for (int i = 0; i < cards.length; i++) {
					if (cards[i].endsWith("A")) {
						value = Integer.toString(Integer.valueOf(value) + 10);
					}				
					else if (cards[i].endsWith("2")) {
						value = Integer.toString(Integer.valueOf(value) + 2);
					}				
					else if (cards[i].endsWith("3")) {
						value = Integer.toString(Integer.valueOf(value) + 3);
					}			
					else if (cards[i].endsWith("4")) {
						value = Integer.toString(Integer.valueOf(value) + 4);
					}
					else if (cards[i].endsWith("5")) {
						value = Integer.toString(Integer.valueOf(value) + 5);
					}
					else if (cards[i].endsWith("6")) {
						value = Integer.toString(Integer.valueOf(value) + 6);
					}
					else if (cards[i].endsWith("7")) {
						value = Integer.toString(Integer.valueOf(value) + 7);
					}
					else if (cards[i].endsWith("8")) {
						value = Integer.toString(Integer.valueOf(value) + 8);
					}
					else if (cards[i].endsWith("9")) {
						value = Integer.toString(Integer.valueOf(value) + 9);
					}
					else if (cards[i].endsWith("10") || cards[i].endsWith("Jack") || cards[i].endsWith("Queen") || cards[i].endsWith("King")) {
						value = Integer.toString(Integer.valueOf(value) + 10);
					}
				}
			}

			else if (cards.length == 5 && Integer.valueOf(value) <= 21) {
				value = "5D";
			}
			
			else if (cards.length > 2 && cards.length <= 5){
				for (int i = 0; i < cards.length; i++) {
					if (cards[i].endsWith("A")) {
						value = Integer.toString(Integer.valueOf(value) + 1);
					}				
					else if (cards[i].endsWith("2")) {
						value = Integer.toString(Integer.valueOf(value) + 2);
					}				
					else if (cards[i].endsWith("3")) {
						value = Integer.toString(Integer.valueOf(value) + 3);
					}			
					else if (cards[i].endsWith("4")) {
						value = Integer.toString(Integer.valueOf(value) + 4);
					}
					else if (cards[i].endsWith("5")) {
						value = Integer.toString(Integer.valueOf(value) + 5);
					}
					else if (cards[i].endsWith("6")) {
						value = Integer.toString(Integer.valueOf(value) + 6);
					}
					else if (cards[i].endsWith("7")) {
						value = Integer.toString(Integer.valueOf(value) + 7);
					}
					else if (cards[i].endsWith("8")) {
						value = Integer.toString(Integer.valueOf(value) + 8);
					}
					else if (cards[i].endsWith("9")) {
						value = Integer.toString(Integer.valueOf(value) + 9);
					}
					else if (cards[i].endsWith("10") || cards[i].endsWith("Jack") || cards[i].endsWith("Queen") || cards[i].endsWith("King")) {
						value = Integer.toString(Integer.valueOf(value) + 10);
					}
				}
				
				if (Integer.valueOf(value) > 21) {
					value = "bust";
				}
			}

			else {
				value = "bust";
			}
			
			return value;
		}
		
		private String[] addHand(String[] hand, String card) {
			String[] tempo = hand;
			hand = new String[tempo.length+1];
			
			for (int i = 0; i < tempo.length; i++) {
				hand[i] = tempo[i];
			}
			
			hand[tempo.length] = card;
			
			return hand;
		}
		
		public PlayPanel() {
			hitButt = new JButton("Hit");
			standButt = new JButton("Stand");
			quitButt = new JButton("Quit");
			mainPanel.setLayout(null);
			
			//labelFont = bigLabel.getFont();
			//bigLabel.setText(labelFont.getFamily());
			
			for (int i = 0; i < dealerCards.length && i < playerCards.length; i++) {
				dealerCards[i].setPreferredSize(new Dimension(100,145));
				playerCards[i].setPreferredSize(new Dimension(100,145));
			}
			//adding shit
			mainPanel.add(bigLabel);
			mainPanel.add(d1);	mainPanel.add(d2);
			mainPanel.add(d3);	mainPanel.add(d4);
			mainPanel.add(d5);	mainPanel.add(p1);
			mainPanel.add(p2);	mainPanel.add(p3);
			mainPanel.add(p4);	mainPanel.add(p5);
		    mainPanel.add(hitButt);
		    mainPanel.add(standButt);
		    mainPanel.add(quitButt);
		    
		    bigLabel.setBounds(10,10,700,100);
		    d1.setBounds(10,120,100,145);
		    d2.setBounds(120,120,100,145);
		    d3.setBounds(230,120,100,145);
		    d4.setBounds(340,120,100,145);
		    d5.setBounds(450,120,100,145);
		    p5.setBounds(780,275,100,145);
		    p4.setBounds(670,275,100,145);
		    p3.setBounds(560,275,100,145);
		    p2.setBounds(450,275,100,145);
		    p1.setBounds(340,275,100,145);
		    hitButt.setBounds(10,430,250,30);
		    standButt.setBounds(270,430,250,30);
		    quitButt.setBounds(530,430,250,30);

		} //PlayPanel()
		
		private class HitButtActionListener implements ActionListener {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		if (hitButt.getText().equals("Hit")) {
		    		tempx = new String[playerHands.length];
					for (int i = 0; i < tempx.length; i++) {
						tempx[i] = playerHands[i];
					}
					playerHands = new String[tempx.length+1];
					playerHands = addHand(tempx, deck[remainingCards-1]);
					remainingCards--;
					deck = minusDeck(deck);
					outcomeP = value(playerHands);

					if (playerHands.length == 3) {
						p3.setIcon(cardIcons.getCardIcon(playerHands[2]));
					}
					else if (playerHands.length == 4) {
						p4.setIcon(cardIcons.getCardIcon(playerHands[3]));
					}
					else if (playerHands.length == 5) {
						p5.setIcon(cardIcons.getCardIcon(playerHands[4]));
					}
					
		    		dealerMove(); //dealer executes his decision
		    		//bigLabel.setText("Hit");
	    		}
	    	}
		}
		
		private class StandButtActionListener implements ActionListener {
			@Override
			public void actionPerformed (ActionEvent e) {
				standP = true;

	    		dealerMove(); //dealer executes his decision
	    		decideGameOver();

	    		//bigLabel.setText("Stand");
			}
		}
		
		public void runBlackJack() {
			//here lies the start of BlackJack
		    // dealing cards, etc.
			bigLabel.setText("BlackJack");
			bigLabel.setFont(new Font("Dialog", Font.PLAIN, 30));

			if (hitButt.getText().equals("Play again") && standButt.getText().equals("Change player")) {
				hitButt.setText("Hit");
				standButt.setText("Stand");
				quitButt.setText("Quit");
			}
			

		    //hit and stand + internal methods
			//HIT
		    hitButt.addActionListener(hitButtAL);
		    //STAND
		    standButt.addActionListener(standButtAL);
		    
			standP = false;
			remainingCards = 52;
			dealerHands = new String[2];
			playerHands = new String[2];
		    deck = makeStandardDeck();
			deck = shuffle(deck);
			
			/*
			for (int i = 0; i < deck.length; i++) {
				System.out.println(deck[i]);
			}
			*/
			while (remainingCards > 48) {
				if (remainingCards == 52) {
					//deal to player
					playerHands[0] = deck[remainingCards-1];
				}
				  
				else if (remainingCards == 51) {
					//deal to dealer
					dealerHands[0] = deck[remainingCards-1];
				}
				  
				else if (remainingCards == 50) {
					//deal to player
					playerHands[1] = deck[remainingCards-1];
				}
				  
				else if (remainingCards == 49) {
					//deal to dealer
					dealerHands[1] = deck[remainingCards-1];
				}
				remainingCards--;
			}
			deck = minusDeck(deck);
			outcomeP = value(playerHands);
			outcomeD = value(dealerHands);
			resetShownCards();
		} //runBlackJack();
		
		public void dealerMove() {
			if (!standP && Integer.valueOf(outcomeD) < 16 && dealerHands.length <=5) {
				tempx = new String[dealerHands.length];
				for (int i = 0; i < tempx.length; i++) {
					tempx[i] = dealerHands[i];
				}
				dealerHands = new String[tempx.length+1];
				dealerHands = addHand(tempx, deck[remainingCards-1]);
				remainingCards--;
				deck = minusDeck(deck);
				outcomeD = value(dealerHands);
				
				if (dealerHands.length == 3) {
					d3.setIcon(cardIcons.getCardBack());
				}
				else if (dealerHands.length == 4) {
					d4.setIcon(cardIcons.getCardBack());
				}
				else if (dealerHands.length == 5) {
					d5.setIcon(cardIcons.getCardBack());
				}
			}
			
			else {
				while (dealerHands.length <= 5) {
					//System.out.println(outcomeD);
					if (outcomeD.equals("5D") || outcomeD.equals("bust") ||
							Integer.valueOf(outcomeD) >= 16) {
						break;
					}
					else {
						tempx = new String[dealerHands.length];
						for (int i = 0; i < tempx.length; i++) {
							tempx[i] = dealerHands[i];
						}
						dealerHands = new String[tempx.length+1];
						dealerHands = addHand(tempx, deck[remainingCards-1]);
						remainingCards--;
						deck = minusDeck(deck);
						outcomeD = value(dealerHands);
					}
				}
				//setCardBackD(); I think here no need
			}
		} //dealerMove() //will be a long one
		
		private void resetShownCards() {
			d1.setIcon(cardIcons.getCardBack());
			d2.setIcon(cardIcons.getCardBack());
			d3.setIcon(null);
			d4.setIcon(null);
			d5.setIcon(null);
			p1.setIcon(cardIcons.getCardIcon(playerHands[0]));
			p2.setIcon(cardIcons.getCardIcon(playerHands[1]));
			p3.setIcon(null);
			p4.setIcon(null);
			p5.setIcon(null);
		}
		
		public void decideGameOver() {
			/* catches exceptions (results from game) from decideResult(),
			 * 	saves result to player record,
			 * 	then writes record to LogBook
			 */
			
			try {
				decideResult();
			}
			catch (WinException we) {
				//playerNow.setWins(playerNow.getWins() + 1);
				allRecords[selIndex][3] = Integer.toString(
						Integer.parseInt(allRecords[selIndex][3]) + 1);
				bigLabel.setText(we.getMessage() + " You won!");
			}
			catch (LoseException le) {
				//playerNow.setLosts(playerNow.getLosts() + 1);
				allRecords[selIndex][4] = Integer.toString(
						Integer.parseInt(allRecords[selIndex][4]) + 1);
				bigLabel.setText(le.getMessage() + " you lost...");
			}
			catch (DrawException de) {
				//playerNow.setDraws(playerNow.getDraws() + 1);
				allRecords[selIndex][5] = Integer.toString(
						Integer.parseInt(allRecords[selIndex][5]) + 1);
				bigLabel.setText(de.getMessage() + " It's a draw!");
			}
			finally {
				if (gameOver) {
					bigLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
					
					for (int i = 0; i < dealerHands.length; i++) {
						//dealerCards[i] indicates d[i] - JLabel
						dealerCards[i].setIcon(cardIcons.getCardIcon(dealerHands[i]));
					}
					
					//playerNow.setTotalGames(playerNow.getTotalGames() + 1);
					//allRecords[selIndex][2] = allRecords[selIndex][2] + 1;
					allRecords[selIndex][2] = Integer.toString(
							Integer.parseInt(allRecords[selIndex][2]) + 1);
					
					hitButt.removeActionListener(hitButtAL);
					standButt.removeActionListener(standButtAL);

					hitButt.setText("Play again");
					standButt.setText("Change player");
					quitButt.setText("Exit to Main Menu");
				    //val: 10,630,100,45 ; 120,630,100,45 ; 230,630,100,45

					logInTask.openWriteFile();
					logInTask.writeAllRecords(allRecords);
					logInTask.closeWriteFile();
					//printAllRecords();//printPlayerHands();//printDealerHands();
				} //else { }
			}//t-c-f block
			
		} //decideGameOver()
		
		public void decideResult() throws WinException, LoseException, DrawException {
			/* checks outcomeP & outcomeD,
			 *  determine the result of the game, 
			 *  gameOver = true
			 *  exceptions thrown here
			 */
			
			if (outcomeP.equals(outcomeD) && 
					(outcomeP.equals("double A") || outcomeP.equals("BJ") || outcomeP.equals("5D") || outcomeP.equals("bust"))) {
				gameOver = true;
				
				if (outcomeP.equals("bust")) {
					throw new DrawException("You both are busted.");
				}
				else {
					throw new DrawException("You both got the same result.");
				}
			}
			
			else if (outcomeP.equals("double A")) {
				gameOver = true;
				throw new WinException("You got a double A!");
			}
			
			else if (outcomeD.equals("double A")) {
				gameOver = true;
				throw new LoseException("The dealer got a double A.");
			}	
			
			else if (outcomeP.equals("BJ")) {
				gameOver = true;
				throw new WinException("You got a Black Jack!");
			}
			
			else if (outcomeD.equals("BJ")) {
				gameOver = true;
				throw new LoseException("The dealer got a Black Jack.");
			}
			
			else if (outcomeP.equals("5D")) {
				gameOver = true;
				throw new WinException("You got a Five-dragon!");
			}

			else if (outcomeD.equals("5D")) {
				gameOver = true;
				throw new LoseException("The dealer got a Five-dragon.");
			}

			else if (outcomeD.equals("bust")) {
				gameOver = true;
				throw new WinException("Dealer is busted.");
			}
			
			else if (outcomeP.equals("bust")) {
				gameOver = true;
				throw new LoseException("You are busted.");
			}
			
			if (standP) {
				if (outcomeP.equals(outcomeD)) {
					gameOver = true;
					throw new DrawException("You both got the same result.");
				}
				
				else if (Integer.valueOf(outcomeP) > Integer.valueOf(outcomeD)) {
					gameOver = true;
					throw new WinException("You have more value!");
				}
				
				else if (Integer.valueOf(outcomeD) > Integer.valueOf(outcomeP)) {
					gameOver = true;
					throw new LoseException("Dealer has more value.");
				}
			}
		}//decideResult()
		
		public void printAllRecords() { //for testing uses
			for (int i = 0; i < allRecords.length; i++) {
				for (int j = 0; j < allRecords[i].length; j++) {
					System.out.println(allRecords[i][j]);
				}
			}
		}
		
		public void printDealerHands() {
			output_ = "\nDealer's hand: ";
			for (int i = 0; i < (dealerHands.length-1); i++) {
				output_ = output_ + dealerHands[i] + ", ";
			}
			output_ = output_ + dealerHands[dealerHands.length-1] + "\n";
			System.out.println(output_);
		}
		
		public void printPlayerHands() {
			output_ = "\nYour hand: ";
			for (int i = 0; i < (playerHands.length-1); i++) {
				output_ = output_ + playerHands[i] + ", ";
			}
			output_ = output_ + playerHands[playerHands.length-1];
			System.out.println(output_);
		}
		
		public void printAllOutcomes() {
			System.out.printf("%s %s", outcomeD, outcomeP);
		}
		
		public boolean isGameOver() {
			return gameOver;
		} //isGameOver()
		
		public void resetGameOver() {
			gameOver = false;
			runBlackJack();
		}//resetGameOver()

		public void addHitButtActionListener(ActionListener listener) {
			hitButt.addActionListener(listener);
		} //addHitButtActionListener()
		
		public void addStandButtActionListener(ActionListener listener) {
			standButt.addActionListener(listener);
		} //addStandButtActionListener()
		
		public void addQuitButtActionListener(ActionListener listener) {
			quitButt.addActionListener(listener);
		} //addQuitButtActionListener()
		
		public JComponent getMainPanel() {
			return mainPanel;
		} //getMainPanel()
	} //class PlayPanel
}//class RunThis
