import java.awt.Image;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class CardIcons {
	public ImageIcon pkrbg;
	public ImageIcon pkrft;
	public ImageIcon david;
	public ImageIcon cardBack;
	public ImageIcon diamondA;
	public ImageIcon clubA;
	public ImageIcon heartA;
	public ImageIcon spadeA;
	public ImageIcon diamond2;
	public ImageIcon club2;
	public ImageIcon heart2;
	public ImageIcon spade2;
	public ImageIcon diamond3;
	public ImageIcon club3;
	public ImageIcon heart3;
	public ImageIcon spade3;
	public ImageIcon diamond4;
	public ImageIcon club4;
	public ImageIcon heart4;
	public ImageIcon spade4;
	public ImageIcon diamond5;
	public ImageIcon club5;
	public ImageIcon heart5;
	public ImageIcon spade5;
	public ImageIcon diamond6;
	public ImageIcon club6;
	public ImageIcon heart6;
	public ImageIcon spade6;
	public ImageIcon diamond7;
	public ImageIcon club7;
	public ImageIcon heart7;
	public ImageIcon spade7;
	public ImageIcon diamond8;
	public ImageIcon club8;
	public ImageIcon heart8;
	public ImageIcon spade8;
	public ImageIcon diamond9;
	public ImageIcon club9;
	public ImageIcon heart9;
	public ImageIcon spade9;
	public ImageIcon diamond10;
	public ImageIcon club10;
	public ImageIcon heart10;
	public ImageIcon spade10;
	public ImageIcon diamondJ;
	public ImageIcon clubJ;
	public ImageIcon heartJ;
	public ImageIcon spadeJ;
	public ImageIcon diamondQ;
	public ImageIcon clubQ;
	public ImageIcon heartQ;
	public ImageIcon spadeQ;
	public ImageIcon diamondK;
	public ImageIcon clubK;
	public ImageIcon heartK;
	public ImageIcon spadeK;
	
	ImageIcon[] diamonds;
	ImageIcon[] clubs;
	ImageIcon[] hearts;
	ImageIcon[] spades;
	ImageIcon[] deck = new ImageIcon[52];
	
	public CardIcons() {
		//your file location here
		String loca = "";
		
		pkrbg = new ImageIcon(loca + "poker_table_login_background.jpg");
		pkrft = new ImageIcon(loca + "playing_cards_background.jpg");
		david = new ImageIcon(loca + "david-ktorza.jpg");
		cardBack = new ImageIcon(loca + "1_card_back.png");
		diamondA = new ImageIcon(loca + "ace_of_diamonds.png");
		clubA = new ImageIcon(loca + "ace_of_clubs.png");
		heartA = new ImageIcon(loca + "ace_of_hearts.png");
		spadeA = new ImageIcon(loca + "ace_of_spades.png");
		diamond2 = new ImageIcon(loca + "2_of_diamonds.png");
		club2 = new ImageIcon(loca + "2_of_clubs.png");
		heart2 = new ImageIcon(loca + "2_of_hearts.png");
		spade2 = new ImageIcon(loca + "2_of_spades.png");
		diamond3 = new ImageIcon(loca + "3_of_diamonds.png");
		club3 = new ImageIcon(loca + "3_of_clubs.png");
		heart3 = new ImageIcon(loca + "3_of_hearts.png");
		spade3 = new ImageIcon(loca + "3_of_spades.png");
		diamond4 = new ImageIcon(loca + "4_of_diamonds.png");
		club4 = new ImageIcon(loca + "4_of_clubs.png");
		heart4 = new ImageIcon(loca + "4_of_hearts.png");
		spade4 = new ImageIcon(loca + "4_of_spades.png");
		diamond5 = new ImageIcon(loca + "5_of_diamonds.png");
		club5 = new ImageIcon(loca + "5_of_clubs.png");
		heart5 = new ImageIcon(loca + "5_of_hearts.png");
		spade5 = new ImageIcon(loca + "5_of_spades.png");
		diamond6 = new ImageIcon(loca + "6_of_diamonds.png");
		club6 = new ImageIcon(loca + "6_of_clubs.png");
		heart6 = new ImageIcon(loca + "6_of_hearts.png");
		spade6 = new ImageIcon(loca + "6_of_spades.png");
		diamond7 = new ImageIcon(loca + "7_of_diamonds.png");
		club7 = new ImageIcon(loca + "7_of_clubs.png");
		heart7 = new ImageIcon(loca + "7_of_hearts.png");
		spade7 = new ImageIcon(loca + "7_of_spades.png");
		diamond8 = new ImageIcon(loca + "8_of_diamonds.png");
		club8 = new ImageIcon(loca + "8_of_clubs.png");
		heart8 = new ImageIcon(loca + "8_of_hearts.png");
		spade8 = new ImageIcon(loca + "8_of_spades.png");
		diamond9 = new ImageIcon(loca + "9_of_diamonds.png");
		club9 = new ImageIcon(loca + "9_of_clubs.png");
		heart9 = new ImageIcon(loca + "9_of_hearts.png");
		spade9 = new ImageIcon(loca + "9_of_spades.png");
		diamond10 = new ImageIcon(loca + "10_of_diamonds.png");
		club10 = new ImageIcon(loca + "10_of_clubs.png");
		heart10 = new ImageIcon(loca + "10_of_hearts.png");
		spade10 = new ImageIcon(loca + "10_of_spades.png");
		diamondJ = new ImageIcon(loca + "jack_of_diamonds2.png");
		clubJ = new ImageIcon(loca + "jack_of_clubs2.png");
		heartJ = new ImageIcon(loca + "jack_of_hearts2.png");
		spadeJ = new ImageIcon(loca + "jack_of_spades2.png");
		diamondQ = new ImageIcon(loca + "queen_of_diamonds2.png");
		clubQ = new ImageIcon(loca + "queen_of_clubs2.png");
		heartQ = new ImageIcon(loca + "queen_of_hearts2.png");
		spadeQ = new ImageIcon(loca + "queen_of_spades2.png");
		diamondK = new ImageIcon(loca + "king_of_diamonds2.png");
		clubK = new ImageIcon(loca + "king_of_clubs2.png");
		heartK = new ImageIcon(loca + "king_of_hearts2.png");
		spadeK = new ImageIcon(loca + "king_of_spades2.png");
		
		diamonds = new ImageIcon[] {diamondA, diamond2, diamond3, diamond4, diamond5,
				diamond6, diamond7, diamond8, diamond9, diamond10,
				diamondJ, diamondQ, diamondK};
	

		clubs = new ImageIcon[] {clubA, club2, club3, club4, club5,
				club6, club7, club8, club9, club10,
				clubJ, clubQ, clubK};
		
		hearts = new ImageIcon[] {heartA, heart2, heart3, heart4, heart5,
				heart6, heart7, heart8, heart9, heart10,
				heartJ, heartQ, heartK};
		
		spades = new ImageIcon[] {spadeA, spade2, spade3, spade4, spade5,
				spade6, spade7, spade8, spade9, spade10,
				spadeJ, spadeQ, spadeK};
		
		cardBack = new ImageIcon(cardBack.getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH));
		scaleDownIcons(diamonds);
		scaleDownIcons(clubs);
		scaleDownIcons(hearts);
		scaleDownIcons(spades);

		//every array length is 13
		for (int j = 0; j < diamonds.length; j++) {
			deck[j] = diamonds[j];
		}
		for (int j = 0; j < clubs.length; j++) {
			deck[j + 13] = clubs[j];
		}
		for (int j = 0; j < hearts.length; j++) {
			deck[j + 26] = hearts[j];
		}
		for (int j = 0; j < spades.length; j++) {
			deck[j + 39] = spades[j];
		}
		/*
		System.arraycopy(diamonds, 0, deck, 0, 13);
		System.arraycopy(clubs, 0, deck, 13, 26);
		System.arraycopy(hearts, 0, deck, 26, 39);
		System.arraycopy(spades, 0, deck, 39, 52);
		*/
	}
	
	public ImageIcon getCardIcon(String card) {
		String[] str = card.split(" ");
		ImageIcon icon = cardBack;
		/*
		for (int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
		}
		*/
		if (str[0].equals("Diamond")) {
			icon = subFinder(str[1], diamonds);
		}
		else if (str[0].equals("Club")) {
			icon = subFinder(str[1], clubs);
		}
		else if (str[0].equals("Hearts")) {
			icon = subFinder(str[1], hearts);
		}
		else if (str[0].equals("Spade")) {
			icon = subFinder(str[1], spades);
		}
		return icon;
	}
	
	public ImageIcon subFinder(String str, ImageIcon[] icons) {
		if (str.equals("A")) {
			return icons[0];
		}
		else if (str.equals("Jack")) {
			return icons[10];
		}
		else if (str.equals("Queen")) {
			return icons[11];
		}
		else if (str.equals("King")) {
			return icons[12];
		}
		else {
			return icons[Integer.parseInt(str)-1];
		}
	}

	private void scaleDownIcons(ImageIcon[] icons) {
		
		for (int i = 0; i < icons.length; i++) {
			icons[i] = new ImageIcon(icons[i].getImage().getScaledInstance(100, 145, Image.SCALE_SMOOTH));
		}
	}
	
    public Image scaleImage(Image image, int w, int h) {
        return image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
    }
	
	public ImageIcon[] getRandomCardIcons() {
		ImageIcon[] tempo = new ImageIcon[5];
		for (int i = 0; i < tempo.length; i++) {
			int index = (int)(Math.random()*deck.length);
			tempo[i] = deck[index];
		}
		
		return tempo;
	}
	
	public ImageIcon getRandomCardIcon() {
		int index = (int)(Math.random()*deck.length);
		return deck[index];
	}
	
	public ImageIcon getCardBack() {
		return cardBack;
	}
	
	public ImageIcon getDavid() {
		return david;
	}
	
	public ImageIcon getPkrft() {
		return pkrft;
	}
	
	public ImageIcon getPkrbg() {
		return pkrbg;
	}
	
	public ImageIcon[] getTestIcons() {
		return hearts;
	}
}

/* methods:
 * public CardIcons()
 * public ImageIcon getCardIcon(String )
 * public ImageIcon subFinder(String , ImageIcon[] )
 * private void scaleDownIcons(ImageIcon[] )
 * public Image scaleImage(Image , int , int)
 * public ImageIcon[] getRandomCardIcons()
 * public ImageIcon getRandomCardIcon()
 * public ImageIcon getCardBack()
 * public ImageIcon getDavid()
 * public ImageIcon getPkrft()
 * public ImageIcon getPkrbg()
 * public ImageIcon[] getTestIcons()
 */
