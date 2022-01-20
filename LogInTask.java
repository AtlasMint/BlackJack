
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

//also includes writing files lazy

/* TO READ:
	logInTask.openReadFile();
	logInTask.readTotalRecords();
	logInTask.createAllRecords();
	logInTask.closeReadFile();
	logInTask.openReadFile();
	logInTask.readAllRecords();
	allRecords = logInTask.getAllRecords();		
	logInTask.closeReadFile();
 * 
 * TO WRITE:
	logInTask.openWriteFile();
	logInTask.writeAllRecords(allRecords);
	logInTask.closeWriteFile();
 */

public class LogInTask {
	public String direction;
	private Scanner input;
	private String[][] allRecords;
	private int totalRecords;
	private int playerNowIndex;
	private Formatter frmtr;
	private String loca = "";
	/*please enter the custom directory to loca if needed,
	 * be reminded as well that all backslashes ("\\") must be replaced with a slash ("/") and
	 * a slash  must be added as the last character
	*/
	
	public LogInTask(String direction) {
		if (loca.equals("")) {
			this.direction = direction;
		}
		else {
			this.direction = loca + direction;
		}
	}
	
	public void setPlayerNowIndex (int index) {
		this.playerNowIndex = index;
	}
	
	public void openReadFile() {
		try {
			input = new Scanner(new File (direction));
		}
		
		catch (FileNotFoundException fnfException) {
			System.err.println("Error opening file.");
			System.exit(1);
		}
	}
	
	public void openWriteFile() {
		try {
			frmtr = new Formatter(direction);
		}
		
		catch (SecurityException sException) {
			System.err.println("You do not have write access to this file.");
			System.exit(1);
		}
		
		catch (FileNotFoundException fnfException) {
			System.err.println("Error opening or creating file.");
			System.exit(1);
		}
	}
	
	public void readTotalRecords() {
		totalRecords = 0;
		
		try {
			while (input.hasNextLine()) {
				totalRecords++;
				input.nextLine();
			}
		}

        catch (NoSuchElementException nseException)
        {
            System.err.println("File improperly formed.");
            input.close();
            System.exit(1);
        }
        catch (IllegalStateException isException)
        {
            System.err.println("Error reading from file.");
            System.exit(1);
        }

	}
	
	public int getTotalRecords() {
		return totalRecords;
	}

	//all records
	public String[][] getAllRecords() {
		return allRecords;
	}
	
	public void setAllRecords(String[][] records) {
		allRecords = records;
	}
	
	//GUI uses this command
	public void createAllRecords() {
		allRecords = new String[totalRecords][6];
	}

	public void readAllRecords() {
		int index = 0;
		try { 
			while(input.hasNextLine() && index < totalRecords) {
				allRecords[index][0] = Integer.toString(input.nextInt());
				allRecords[index][1] = input.next();
				allRecords[index][2] = Integer.toString(input.nextInt());
				allRecords[index][3] = Integer.toString(input.nextInt());
				allRecords[index][4] = Integer.toString(input.nextInt());
				allRecords[index][5] = Integer.toString(input.nextInt());
				index++;
			}
			/*
			while(input.hasNextLine() && index < totalRecords) {
				for (int i = 0; i < 6; i++) {
					System.out.println(input.next());
				}
 				index++;
			}
			*/
		}

        catch (NoSuchElementException nseException)
        {
            System.err.println("File improperly formed.");
            input.close();
            System.exit(1);
        }
        catch (IllegalStateException isException)
        {
            System.err.println("Error reading from file.");
            input.close();
            System.exit(1);
        }
		
	}
	
	public void writeAllRecords(String[][] allRecords) {
		for (int j = 0; j < allRecords.length; j++) {
			try { 
				frmtr.format("%s %s %s %s %s %s\n", 
						allRecords[j][0], allRecords[j][1],
						allRecords[j][2], allRecords[j][3],
						allRecords[j][4], allRecords[j][5]);
			}
			
			catch (FormatterClosedException fcException) {
				System.err.println("Error writing to file.");
				return;
			}
			
			catch (NoSuchElementException eException) {
				System.err.println("Invalid input. Please try again.");
			}
		}
	}
	
	public String[] selectRecord(int pn) {
		//before do readAllRec, after this do setRec in playerNow
		// playerNow = foo.selectRec(pn) 
		//binary search- wait, can't I minus one
		/*
		int min = 0; int max = allRecords.length; int i = 0; int num;
		while (min < max) {
			i = (int) Math.floorDiv((min + max), 2);
			num = Integer.parseInt(allRecords[i][0]);
			if (pn == num) {
				break;
			}
			
			else if (pn > num) {
				min = i++;
			}
			
			else {
				max = i;
			}
		}
		*/
		this.playerNowIndex = pn - 1;
		return allRecords[playerNowIndex];
	}
	/*
	public void updateRecord(String[] updatedPlayer) {
		allRecords[playerNowIndex] = updatedPlayer;
		CreateFile cf = new CreateFile();
		cf.openFile();
		cf.writeRecords(allRecords);
		cf.closeFile();
	} //not used
	*/
    public void closeReadFile()
    {
        if (input != null)
            input.close();
    }
    
    public void closeWriteFile() {
    	if (frmtr != null) {
    		frmtr.close();
    	}
    }
}

/* methods:
 * void openFile()
 * void readTotalRecords()
 * void getTotalRecords()
 * void getAllRecords()
 * void createAllRecords()
 * void readAllRecords()
 * void selectRecord()
 * void updateRecord()
 * void closeFile()
 */
