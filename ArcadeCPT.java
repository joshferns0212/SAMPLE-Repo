/*CPT
	Programmed by Alex Xu, Joshua Fernandes, and Kyle Alvares, 01/23/2017
	This program allows the user to play a plethora of games*/
import java.awt.*;
import java.awt.event.KeyEvent;
import hsa.Console;

public class ArcadeCPT extends Console
{
	// Console
	public static ArcadeCPT c = new ArcadeCPT();

	// Key Detection variables
	public static boolean wKey = false, sKey = false, upKey = false, downKey = false;

	// Various Integer Type Variables
	public static int userInput;

	// Fonts
	public static Font numTries           = new Font("Isocteur", Font.BOLD, 45);
	public static Font keyFont            = new Font("Lucida Console", Font.BOLD, 25);
	public static Font titleFont          = new Font("Lucidia Handwriting", Font.PLAIN, 73);
	public static Font sideFont           = new Font("Arial", Font.PLAIN, 11);
	public static Font instructions       = new Font("Lucida Console", Font.BOLD, 15);
	public static Font pong               = new Font("Lucida Console", Font.BOLD, 60);
	public static Font arcadeFont         = new Font("Broadway", Font.PLAIN, 120);
	public static Font gameFont           = new Font("Broadway", Font.PLAIN, 40);
	public static Font mastermindMenuFont = new Font("Bauhaus 93", Font.PLAIN, 45);
	public static Font yahtzeeMenuFont    = new Font("Allegro BT", Font.PLAIN, 45);
	public static Font pongMenuFont       = new Font("BankGothic Md BT", Font.PLAIN, 45);

	// Colors
	public static Color orange            = new Color(255, 157, 0);
	public static Color newBlue           = new Color(109, 165, 255);
	public static Color dotColor          = new Color(252, 235, 191);
	public static Color yahtzeeBackground = new Color(0, 100, 0);
	public static Color menuYahtzee       = new Color(60, 140, 113);
	public static Color menuMastermind    = new Color(208, 102, 100);
	public static Color pacmanBackground  = new Color(0, 35, 165);
	public static Color[] availableColors = { Color.red, Color.cyan, Color.green, Color.yellow, orange, Color.magenta };

	// Arcade CPT constructor
	public ArcadeCPT()
	{
		super("Arcade");
	} // End of ArcadeCPT

	public void keyPressed(KeyEvent k)
	{
		if (k.getKeyCode() == KeyEvent.VK_W)
		{
			wKey = true;
		} // checks if 'W' is pressed
		else if (k.getKeyCode() == KeyEvent.VK_S)
		{
			sKey = true;
		} // checks if 'S' is pressed
		else if (k.getKeyCode() == KeyEvent.VK_UP)
		{
			upKey = true;
		} // checks if 'up' is pressed
		else if (k.getKeyCode() == KeyEvent.VK_DOWN)
		{
			downKey = true;
		} // checks if 'down' is pressed
		userInput = k.getKeyCode();
	} // End of keyPressed method

	public void keyTyped(KeyEvent k)
	{} // End of keyTyped method

	public void keyReleased(KeyEvent k)
	{
		if (k.getKeyCode() == KeyEvent.VK_W)
		{
			wKey = false;
		} // checks if 'W' is released
		else if (k.getKeyCode() == KeyEvent.VK_S)
		{
			sKey = false;
		} // checks if 'S' is released
		else if (k.getKeyCode() == KeyEvent.VK_UP)
		{
			upKey = false;
		} // checks if 'up' is released
		else if (k.getKeyCode() == KeyEvent.VK_DOWN)
		{
			downKey = false;
		} // checks if 'down' is released
		userInput = 1;
	} // End of keyReleased method

	public static void main(String[] args)
	{
		mainMenuGraphics();
		while (true)
		{
			// Runs Yahtzee
			if (userInput == KeyEvent.VK_1)
			{
				c.setWindowTitle("Yahtzee");
				yahtzee();
				mainMenuGraphics();
			}
			// Runs Mastermind
			else if (userInput == KeyEvent.VK_2)
			{
				c.setWindowTitle("Mastermind");
				mastermind();
				mainMenuGraphics();
			}
			// Runs Pong
			else if (userInput == KeyEvent.VK_3)
			{
				c.setWindowTitle("Pong");
				pong();
				mainMenuGraphics();
			}
		} // end of main while loop
	} // end of main method

	public static void delay(int time)
	{
		try
		{
			Thread.sleep(time);
		} // End of try

		catch (Exception e)
		{
		} // End of catch
	} // End of delay method

	public static void yahtzee()
	{
		// Variable declarations
		c.setWindowTitle("Yahtzee");

		Font yahtzee     = new Font("Allegro BT", Font.BOLD + Font.ITALIC, 70);
		Font yahtzee2    = new Font("Allegro BT", Font.BOLD, 40);
		Font yahtzee3    = new Font("Allegro BT", Font.BOLD, 70);
		Font yahtzee4    = new Font("Allegro BT", Font.BOLD, 25);

		Color background = new Color(0, 100, 0);
		Color table      = new Color(139, 69, 19);

		int rollOne = 0, rollTwo = 0, scoreOne, scoreTwo, delayCounter;
		int[] xPosition  = {68, 158, 248, 338, 428};
		int[] yPosition  = {118, 368};

		while (userInput != KeyEvent.VK_ESCAPE)
		{
			scoreOne = 0;
			scoreTwo = 0;
			yahtzeeGame(background, table, yahtzee, yahtzee2, yahtzee3, yahtzee4, scoreOne, scoreTwo);

			while (scoreOne < 50 && scoreTwo < 50)
			{
				if (userInput == KeyEvent.VK_SPACE)
				{
					yahtzeeGame(background, table, yahtzee, yahtzee2, yahtzee3, yahtzee4, scoreOne, scoreTwo);

					// runs five times for each die
					for (int i = 0; i < 5; i++)
					{
						// makes dice roll
						for (int j = 0; j < 20; j++)
						{
							rollOne = dieRoll();
							rollTwo = dieRoll();
							c.setColor(Color.black);
							selectDieNumber(rollOne, i, 0, xPosition, yPosition);
							selectDieNumber(rollTwo, i, 1, xPosition, yPosition);
							delay(10 * (i + 1));
							c.setColor(Color.white);
							selectDieNumber(rollOne, i, 0, xPosition, yPosition);
							selectDieNumber(rollTwo, i, 1, xPosition, yPosition);
						} // End of inner for loop
						c.setColor(background);
						// displays score of roll
						drawScores(scoreOne, scoreTwo, yahtzee);
						scoreOne += rollOne;
						scoreTwo += rollTwo;
						c.setColor(Color.black);
						selectDieNumber(rollOne, i, 0, xPosition, yPosition);
						selectDieNumber(rollTwo, i, 1, xPosition, yPosition);
						drawScores(scoreOne, scoreTwo, yahtzee);
					} // End of outer for loop
				} // End of if

				// detects when to show instructions
				if (userInput == KeyEvent.VK_I)
				{
					yahtzeeInstructions(yahtzee3, yahtzee2, yahtzee4, background);
					while (userInput == KeyEvent.VK_I) {}
					yahtzeeGame(background, table, yahtzee, yahtzee2, yahtzee3, yahtzee4, scoreOne, scoreTwo);
				}
				// to go to main menu
				else if (userInput == KeyEvent.VK_ESCAPE)
				{
					break;
				}
			} // End of main while loop
			if (userInput == KeyEvent.VK_ESCAPE)
			{
				break;
			}
			delay(1000);

			// win screen
			c.setColor(background);
			c.fillRect(0, 0, c.maxx(), c.maxy());
			yahtzeeEndScreen(scoreOne, scoreTwo, yahtzee2, yahtzee4);
			while (userInput != KeyEvent.VK_SPACE)
			{
				if (userInput == KeyEvent.VK_ESCAPE)
				{
					break;
				}
			}
		} // End of outer while loop
	} // End of yahtzee method

	public static int dieRoll()
	{
		int num;
		num = (int) (Math.random() * 6 + 1);
		return num;
	} // End of dieRolls method

	public static void yahtzeeGame(Color gameBackground, Color gameTable, Font font1, Font font2, Font font3,
	                               Font font4, int gameScore1, int gameScore2)
	{
		yahtzeeGraphics(gameBackground, gameTable, font3, font2, font4);
		drawDie(40, 90);
		drawDie(40, c.maxy() - 160);
		drawScores(gameScore1, gameScore2, font1);
	} // End of yahtzeeGame method

	public static void yahtzeeGraphics(Color backgroundColor, Color tableColor, Font gameFont, Font playerFont,
	                                   Font instructionFont)
	{
		c.setFont(gameFont);
		c.setColor(backgroundColor);
		c.fillRect(0, 0, c.maxx(), c.maxy());
		c.setColor(tableColor);
		c.fillArc(-50, c.maxy() - 50, 100, 100, 0, 90);
		c.fillArc(c.maxx() - 50, c.maxy() - 50, 100, 100, 90, 90);
		c.fillArc(-50, -50, 100, 100, 270, 90);
		c.fillArc(c.maxx() - 50, -50, 100, 100, 180, 90);
		c.fillRect(0, c.maxy() - 10, c.maxx(), 10);
		c.fillRect(0, 0, c.maxx(), 10);
		c.fillRect(0, 0, 10, c.maxy());
		c.fillRect(c.maxx() - 10, 0, 10, c.maxy());
		c.setColor(Color.black);
		c.fillRect(0, (c.maxy() / 2) - 5, c.maxx(), 10);
		c.drawString("YAHTZEE!", 140, 70);
		c.setFont(playerFont);
		c.drawString("Player One", 30, 225);
		c.drawString("Player Two", 30, 305);
		c.setFont(instructionFont);
		c.drawString("Hold 'i' for instructions", 330, 480);
		c.drawString("Press 'Esc' to exit", 60, 480);
	} // End of yahtzeeGraphics method

	public static void drawDie(int xValue, int yValue)
	{
		for (int a = 0; a < 5; a++)
		{
			c.setColor(Color.white);
			c.fillRect(xValue, yValue, 70, 70);
			c.setColor(Color.black);
			c.drawRect(xValue, yValue, 70, 70);
			xValue += 90;
		} // End of for loop
	} // End of drawDie method

	public static void dieNumberOne(int x1, int y1)
	{
		c.fillOval(x1, y1, 15, 15);
	} // End of dieNumberOne method

	public static void dieNumberTwo(int x2, int y2)
	{
		c.fillOval(x2 - 17, y2 - 17, 15, 15);
		c.fillOval(x2 + 17, y2 + 17, 15, 15);
	} // End of dieNumberTwo method

	public static void dieNumberThree(int x3, int y3)
	{
		c.fillOval(x3 + 17, y3 - 17, 15, 15);
		c.fillOval(x3 - 17, y3 + 17, 15, 15);
		c.fillOval(x3, y3, 15, 15);
	} // End of dieNumberThree method

	public static void dieNumberFour(int x4, int y4)
	{
		c.fillOval(x4 + 17, y4 - 17, 15, 15);
		c.fillOval(x4 - 17, y4 + 17, 15, 15);
		c.fillOval(x4 - 17, y4 - 17, 15, 15);
		c.fillOval(x4 + 17, y4 + 17, 15, 15);
	} // End of dieNumberFour method

	public static void dieNumberFive(int x5, int y5)
	{
		c.fillOval(x5 + 17, y5 - 17, 15, 15);
		c.fillOval(x5 - 17, y5 + 17, 15, 15);
		c.fillOval(x5 - 17, y5 - 17, 15, 15);
		c.fillOval(x5 + 17, y5 + 17, 15, 15);
		c.fillOval(x5, y5, 15, 15);
	} // End of dieNumberFive method

	public static void dieNumberSix(int x6, int y6)
	{
		c.fillOval(x6 + 17, y6 - 17, 15, 15);
		c.fillOval(x6 - 17, y6 + 17, 15, 15);
		c.fillOval(x6 - 17, y6 - 17, 15, 15);
		c.fillOval(x6 + 17, y6 + 17, 15, 15);
		c.fillOval(x6 + 17, y6, 15, 15);
		c.fillOval(x6 - 17, y6, 15, 15);
	} // End of dieNumberSix method

	public static void selectDieNumber(int numberOne, int numberTwo, int numberThree, int[] x, int[] y)
	{
		if (numberOne == 1)
		{
			dieNumberOne(x[numberTwo], y[numberThree]);
		} // End of if
		else if (numberOne == 2)
		{
			dieNumberTwo(x[numberTwo], y[numberThree]);
		} // End of else if
		else if (numberOne == 3)
		{
			dieNumberThree(x[numberTwo], y[numberThree]);
		} // End of else if
		else if (numberOne == 4)
		{
			dieNumberFour(x[numberTwo], y[numberThree]);
		} // End of else if
		else if (numberOne == 5)
		{
			dieNumberFive(x[numberTwo], y[numberThree]);
		} // End of else if
		else
		{
			dieNumberSix(x[numberTwo], y[numberThree]);
		} // End of else
	} // End of selectDieNumber method

	public static void drawScores(int score1, int score2, Font scoreFont)
	{
		c.setFont(scoreFont);
		if (score1 >= 10)
		{
			c.drawString(String.valueOf(score1), 500, 150);
		} // End of if
		else
		{
			c.drawString(String.valueOf(score1), 538, 150);
		} // End of else

		if (score2 >= 10)
		{
			c.drawString(String.valueOf(score2), 500, 400);
		} // End of if
		else
		{
			c.drawString(String.valueOf(score2), 538, 400);
		} // End of else
	} // End of drawScores method

	public static void yahtzeeEndScreen(int firstScore, int secondScore, Font winFont, Font playFont)
	{
		c.setFont(winFont);
		c.setColor(Color.black);
		if (firstScore > secondScore)
		{
			c.drawString("Player One is the winner!", 100, c.maxy() / 2 - 20);
		} // End of if
		else if (firstScore == secondScore)
		{
			c.drawString("It's a tie!", 150, c.maxy() / 2 - 20);
		} // End of else if
		else
		{
			c.drawString("Player Two is the winner!", 100, c.maxy() / 2 - 20);
		} // End of else
		c.setFont(playFont);
		c.drawString("Press the 'space bar' to play again.", 130, 300);
	} // End of yahtzeeEndScreen method

	public static void yahtzeeInstructions(Font instructionsFont1, Font instructionsFont2, Font instructionsFont3,
	                                       Color instructionsColor)
	{
		c.setColor(instructionsColor);
		c.setFont(instructionsFont1);
		c.fillRect(0, 0, c.maxx(), c.maxy());
		c.setColor(Color.black);
		c.drawString("YAHTZEE RULES!", 55, 70);
		c.setFont(instructionsFont2);
		c.drawString("Goal:  To get to 50 points from the", 10, 120);
		c.drawString("numbers rolled on the 5 die.", 15, 165);
		c.setFont(instructionsFont3);
		c.drawString("1. Numbers rolled are added to your score.", 10, 220);
		c.drawString("2. If you both have above 50 points at the end of the roll,", 10, 255);
		c.drawString("the higher score wins.", 15, 285);
		c.drawString("3. Press the space bar to roll the die.", 10, 320);
		c.drawString("4. Remember to HAVE FUN!", 10, 355);
		drawDie(95, 390);
		dieNumberOne(123, 418);
		dieNumberTwo(213, 418);
		dieNumberThree(303, 418);
		dieNumberFour(393, 418);
		dieNumberFive(483, 418);
	} // End of yahtzeeInstructions method

	public static void mastermind()
	{
		while (userInput != KeyEvent.VK_ESCAPE)
		{
			// Variable declaration
			boolean userWin = false;
			Color[] randomColors = new Color[4];
			Color[] guessColors = new Color[4];
			int colorsCorrect = 0, positionsCorrect = 0;

			mastermindInstructions();
			background();
			mastermindGraphics(availableColors);
			randomizeColors(availableColors, randomColors);

			for (int i = 10; i > 0; i--)
			{
				c.setColor(Color.black);
				c.fillRect(483, 164, 80, 50);
				c.setColor(Color.white);
				c.setFont(numTries);
				c.drawString(String.valueOf(i), 488, 198);

				// gets input from user
				mastermindInput(availableColors, guessColors);

				// COmpares the user's input to the randomized values
				colorsCorrect = determineColors(randomColors, guessColors);
				positionsCorrect = determinePositions(randomColors, guessColors);

				c.setColor(Color.black);
				c.fillRect(553, 230, 30, 40);
				c.setColor(Color.cyan);
				c.drawString(String.valueOf(colorsCorrect), 556, 265);
				c.setColor(Color.black);
				c.fillRect(553, 280, 30, 40);
				c.setColor(Color.cyan);
				c.drawString(String.valueOf(positionsCorrect), 556, 315);

				// win detection
				if (determineWin(positionsCorrect))
				{
					background();
					c.setFont(instructions);
					c.setColor(Color.white);
					c.drawString("CONGRATULATIONS, YOU WIN! :)", 200, 200);
					c.drawString("Press [space] to play again.", 200, 220);

					while (!(userInput == KeyEvent.VK_SPACE || userInput == KeyEvent.VK_ESCAPE))
					{} // End of while Loop

					break;
				} // End of if statement

				// Loss detection
				if (i == 1)
				{
					background();
					c.setFont(instructions);
					c.setColor(Color.white);
					c.drawString("SORRY, YOU LOSE :(", 200, 200);
					c.drawString("Press [space] to play again.", 200, 220);
					c.drawString("Correct Combination:", 65, 280);

					for (int f = 0; f < 4; f++)
					{
						c.setColor(randomColors[f]);
						c.fillRect(f * 100 + 65, 293, 100, 65);
					} // End of for loop

					while (!(userInput == KeyEvent.VK_SPACE || userInput == KeyEvent.VK_ESCAPE))
					{} // End of while loop

					break;
				} // End of if statement

				// to go to the main menu
				if (userInput == KeyEvent.VK_ESCAPE)
					break;

				colorsCorrect = 0;
				positionsCorrect = 0;
			} // End of for loop

		} // End of while Loop

	} // End of mastermind method

	public static void randomizeColors(Color[] arrayOne, Color[] arrayTwo)
	{
		int a;

		do
		{
			for (int i = 0; i < 4; i++)
			{
				a = (int) (Math.random() * 6);
				arrayTwo[i] = arrayOne[a];
			} // End of inner for loop
		} // End of outer do-while loop

		while (arrayTwo[0] == arrayTwo[1] || arrayTwo[0] == arrayTwo[2] || arrayTwo[0] == arrayTwo[3]
			   || arrayTwo[1] == arrayTwo[2] || arrayTwo[1] == arrayTwo[3] || arrayTwo[2] == arrayTwo[3]);
	} // End of randomizeColors method

	public static void mastermindInput(Color[] colorChoices, Color[] guesses)
	{
		// Tiles for Users Guesses
		c.setColor(Color.LIGHT_GRAY);
		for (int m = 0; m < 4; m++)
		{
			c.fillRect(65 + m * 100, 293, 80, 65);
		}
		// User Input
		Color[] temp = new Color[4];
		int i = 0;
		while (i < 4)
		{
			if (userInput == KeyEvent.VK_R)
			{
				guesses[i] = colorChoices[0];
				temp[i] = colorChoices[0];
				c.setColor(guesses[i]);
				c.fillRect(100 * i + 65, 293, 80, 65);
				i++;
				delay(250);
			} // End of inner if
			else if (userInput == KeyEvent.VK_B)
			{
				guesses[i] = colorChoices[1];
				temp[i] = colorChoices[1];
				c.setColor(guesses[i]);
				c.fillRect(100 * i + 65, 293, 80, 65);
				i++;
				delay(250);
			} // End of inner else if
			else if (userInput == KeyEvent.VK_G)
			{
				guesses[i] = colorChoices[2];
				temp[i] = colorChoices[2];
				c.setColor(guesses[i]);
				c.fillRect(100 * i + 65, 293, 80, 65);
				i++;
				delay(250);
			} // End of inner else if
			else if (userInput == KeyEvent.VK_Y)
			{
				guesses[i] = colorChoices[3];
				temp[i] = colorChoices[3];
				c.setColor(guesses[i]);
				c.fillRect(100 * i + 65, 293, 80, 65);
				i++;
				delay(250);
			} // End of inner else if
			else if (userInput == KeyEvent.VK_O)
			{
				guesses[i] = colorChoices[4];
				temp[i] = colorChoices[4];
				c.setColor(guesses[i]);
				c.fillRect(100 * i + 65, 293, 80, 65);
				i++;
				delay(250);
			} // End of inner else if
			else if (userInput == KeyEvent.VK_M)
			{
				guesses[i] = colorChoices[5];
				temp[i] = colorChoices[5];
				c.setColor(guesses[i]);
				c.fillRect(100 * i + 65, 293, 80, 65);
				i++;
				delay(250);
			} // End of inner else if
			else if (userInput == KeyEvent.VK_BACK_SPACE && i != 0)
			{
				i--;
				c.setColor(Color.LIGHT_GRAY);
				c.fillRect(100 * i + 65, 293, 80, 65);
				delay(250);
			} // End of inner else if
			else if (userInput == KeyEvent.VK_ESCAPE)
			{
				break;
			} // End of inner else if
		} // End of while loop

		for (int q = 0; q < 4; q++)
		{
			c.setColor(temp[q]);
			c.fillRect(100 * q + 65, 193, 80, 65);
		}
	} // End of mastermindInput method

	public static int determineColors(Color[] colorsOne, Color[] colorsTwo)
	{
		int colors = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (colorsTwo[i] == colorsOne[j]) {
					colors++;
				} // End of inner if
			} // End of inner for loop
		} // End of outer for loop

		return colors;
	} // End of determineColors method

	public static int determinePositions(Color[] colors1, Color[] colors2)
	{
		int positions = 0;
		for (int i = 0; i < 4; i++)
		{
			if (colors2[i] == colors1[i])
			{
				positions++;
			} // End of inner if
		} // End of outer for loop

		return positions;
	} // End of determinePositions method

	public static boolean determineWin(int temp)
	{
		boolean winLoss = false;
		if (temp == 4)
		{
			winLoss = true;
		} // End of if

		return winLoss;
	} // End of determineWin method

	public static void mastermindGraphics(Color[] tileColor)
	{
		// Color Legend + Minor Extras (GRAPHICS MASTERMIND)
		c.setColor(Color.white);
		c.drawRect(50, 375, 539, 99);
		c.drawRect(50, 134, 409, 139);
		c.drawString("Previous Guess", 65, 164);
		c.setFont(instructions);
		c.drawString("Colour Legend", 60, 400);

		// Color Tiles For Legend
		String[] tileLabels = { "[R]", "[B]", "[G]", "[Y]", "[O]", "[M]" };
		c.setColor(Color.black);
		c.fillRect(60, 410, 520, 60);
		c.setFont(keyFont);
		for (int j = 0; j < 6; j++)
		{
			c.setColor(tileColor[j]);
			c.fillRect(70 + j * 85, 415, 75, 50);
			c.setColor(Color.black);
			c.drawString(tileLabels[j], 87 + j * 84, 448);
		}

		// Side Legend
		c.setColor(Color.white);
		c.drawRect(459, 324, 130, 50);
		c.drawRect(469, 334, 10, 10);
		c.setFont(sideFont);
		c.drawString("Press [Esc] to exit", 473, 360);
		c.drawRect(459, 274, 130, 50);
		c.drawRect(459, 224, 130, 50);
		c.drawRect(459, 134, 130, 90);
		c.drawString("Number of Tries Left:", 473, 154);
		c.setColor(Color.white);
		c.drawRect(50, 274, 408, 100);
		c.setColor(newBlue);
		c.fillRect(469, 284, 10, 10);
		c.drawString("Positions Correct", 473, 308);
		c.setColor(Color.pink);
		c.fillRect(469, 234, 10, 10);
		c.drawString("Colours Correct", 473, 256);

		// Title
		c.setFont(titleFont);
		Color titleTwo = new Color(218, 165, 32);
		c.setColor(titleTwo);
		c.drawString("MASTERMIND!", 63, 92);
		Color titleOne = new Color(165, 42, 42);
		c.setColor(titleOne);
		c.drawString("MASTERMIND!", 69, 90);
		c.setColor(Color.red);
		c.drawString("MASTERMIND!", 65, 90);
	}

	public static void background()
	{
		Color background = new Color(219, 112, 147);
		c.setColor(Color.black);
		c.fillRect(0, 0, c.maxx(), c.maxy());
	}

	public static void mastermindInstructions()
	{
		background();
		Color howTo = new Color(68, 255, 93);
		c.setColor(howTo);
		c.setFont(instructions);

		c.drawString("Mastermind Instructions", 40, 100);
		c.drawString("Objective: Guess the correct combination of colours.", 40, 180);
		c.drawString("Enter the key that corresponds with the colour.", 40, 220);
		c.drawString("This game tests, memory, thinking & accuracy - NO MISTAKES!", 40, 260);
		c.drawString("**Note: Colours only appear once in a combination.", 40, 300);
		c.drawString("Press [space] to START", 40, 380);
		delay(200);
		while (userInput != KeyEvent.VK_SPACE)
		{}

		background();
	}

	public static void pong()
	{
		// Variable Declaration
		int paddleOneY = 300, paddleTwoY = 300, ballX = c.maxx() / 2 - 10, ballY = c.maxy() / 2 - 10, deltaX = -3,
			deltaY = 2, scoreOne = 0, scoreTwo = 0;

		// instructions
		pongInstructions();
		while (userInput != KeyEvent.VK_SPACE && userInput != KeyEvent.VK_ESCAPE)
		{}

		pongBackground();

		while (userInput != KeyEvent.VK_ESCAPE)
		{
			// key detection
			if (wKey && paddleOneY >= 0)
			{
				paddleOneY += -3;
			}
			if (sKey && paddleOneY <= c.maxy() - 100)
			{
				paddleOneY += 3;
			}
			if (upKey && paddleTwoY > 0)
			{
				paddleTwoY += -3;
			}
			if (downKey && paddleTwoY < c.maxy() - 100)
			{
				paddleTwoY += 3;
			}

			// collision detection
			if (deltaX < 0 && ballX >= 70 && ballX <= 72 && ballY > paddleOneY - 10 && ballY < paddleOneY + 100)
			{
				deltaX *= -1;
			}
			else if (deltaX > 0 && ballX >= c.maxx() - 92 && ballX <= c.maxx() - 90 && ballY > paddleTwoY - 10
			         && ballY < paddleTwoY + 100)
			{
				deltaX *= -1;
			}

			// win detection
			if (ballX < -20)
			{
				scoreTwo++;
				c.setColor(Color.BLACK);
				c.fillRect(c.maxx() / 2 + 70, 0, 140, 75);

				// when player two wins the game
				if (scoreTwo == 15)
				{
					pongBackground();
					pongWinScreen("Player Two");
					ballX = c.maxx() / 2 - 10; // resets values
					ballY = c.maxy() / 2 - 10;
					deltaX = -3;
					deltaY = 2;
					scoreOne = 0;
					scoreTwo = 0;
					while (userInput != KeyEvent.VK_SPACE && userInput != KeyEvent.VK_ESCAPE)
					{
						delay(5);
					}
					pongBackground();
				}
				// when player two gains a point but doesn't win
				else
				{
					while (userInput != KeyEvent.VK_SPACE && userInput != KeyEvent.VK_ESCAPE)
					{
						pongGraphics(paddleOneY, paddleTwoY, scoreOne, scoreTwo);

						// key detection
						if (wKey && paddleOneY >= 0)
						{
							paddleOneY += -3;
						}
						if (sKey && paddleOneY <= c.maxy() - 100)
						{
							paddleOneY += 3;
						}
						if (upKey && paddleTwoY > 0)
						{
							paddleTwoY += -3;
						}
						if (downKey && paddleTwoY < c.maxy() - 100)
						{
							paddleTwoY += 3;
						}
						delay(5);
					}
					deltaX = -3;
					ballX = c.maxx() - 90;
					ballY = paddleTwoY + 40;
					pongBackground();
				}
			}
			else if (ballX > c.maxx())
			{
				scoreOne++;
				c.setColor(Color.BLACK);
				c.fillRect(c.maxx() / 2 - 120, 0, 140, 75);

				// when player one wins the game
				if (scoreOne == 15)
				{
					pongBackground();
					pongWinScreen("Player One");
					ballX = c.maxx() / 2 - 10; // resets values
					ballY = c.maxy() / 2 - 10;
					deltaX = -3;
					deltaY = 2;
					scoreOne = 0;
					scoreTwo = 0;
					while (userInput != KeyEvent.VK_SPACE && userInput != KeyEvent.VK_ESCAPE)
					{
						delay(5);
					}
					pongBackground();
				}

				// when player one gains a point but doesn't win
				else
				{
					while (userInput != KeyEvent.VK_SPACE && userInput != KeyEvent.VK_ESCAPE)
					{
						pongGraphics(paddleOneY, paddleTwoY, scoreOne, scoreTwo);
						if (wKey && paddleOneY >= 0)
						{
							paddleOneY += -3;
						}
						if (sKey && paddleOneY <= c.maxy() - 100)
						{
							paddleOneY += 3;
						}
						if (upKey && paddleTwoY > 0)
						{
							paddleTwoY += -3;
						}
						if (downKey && paddleTwoY < c.maxy() - 100)
						{
							paddleTwoY += 3;
						}
						delay(5);
					}
					deltaX = 3;
					ballX = 70;
					ballY = paddleOneY + 40;
				}
			}
			// sideline detection
			else if (ballY < 0)
			{
				deltaY *= -1;
			}
			else if (ballY > c.maxy() - 15)
			{
				deltaY *= -1;
			}

			// ballmovement
			ballX += deltaX;
			ballY += deltaY;

			pongBall(ballX, ballY);
			pongGraphics(paddleOneY, paddleTwoY, scoreOne, scoreTwo);
			delay(5);
		} // end of while loop
	}

	public static void pongBackground()
	{
		// screen
		c.setColor(Color.BLACK);
		c.fillRect(0, 0, c.maxx() + 3, c.maxy() + 3);
	}

	public static void pongGraphics(int paddleOne, int paddleTwo, int playerOneScore, int playerTwoScore)
	{
		// dividing lines
		c.setColor(Color.WHITE);
		for (int y = 0; y < c.maxy(); y += 58)
		{
			c.fillRect(c.maxx() / 2 - 5, y, 5, 40);
		}

		// paddle one
		c.setColour(Color.BLACK);
		c.fillRect(50, paddleOne - 3, 20, 106);
		c.setColour(Color.WHITE);
		c.fillRect(50, paddleOne, 20, 100);

		// paddle two
		c.setColour(Color.BLACK);
		c.fillRect(c.maxx() - 70, paddleTwo - 3, 20, 106);
		c.setColour(Color.WHITE);
		c.fillRect(c.maxx() - 70, paddleTwo, 20, 100);
		c.setFont(pong);

		// draws score of player 1
		if (playerOneScore < 10)
		{
			c.drawString(String.valueOf(playerOneScore), c.maxx() / 2 - 120, 70);
		}

		else
		{
			c.drawString(String.valueOf(playerOneScore), c.maxx() / 2 - 140, 70);
		}

		// draws score of player 2
		if (playerTwoScore < 10)
		{
			c.drawString(String.valueOf(playerTwoScore), c.maxx() / 2 + 70, 70);
		}

		else
		{
			c.drawString(String.valueOf(playerTwoScore), c.maxx() / 2 + 50, 70);
		}
	}

	public static void pongBall(int positionX, int positionY)
	{
		// ball
		c.setColor(Color.BLACK);
		c.fillOval(positionX - 5, positionY - 5, 30, 30);
		c.setColor(Color.WHITE);
		c.fillOval(positionX, positionY, 20, 20);
	}

	public static void pongInstructions()
	{
		pongBackground();
		c.setColor(Color.WHITE);
		c.setFont(instructions);

		// pong instructions
		c.drawString("Pong Instructions", 100, 50);
		c.drawString("Move the paddles to hit the ball", 100, 100);
		c.drawString("If the ball goes past the paddle,", 100, 150);
		c.drawString("the player on the other side gets a point", 100, 175);
		c.drawString("The ball is served towards that person's side", 100, 200);
		c.drawString("The first to 15 wins!", 100, 225);
		c.drawString("W/A - Left Paddle", 100, 270);
		c.drawString("Up/Down - Right Paddle", 100, 290);
		c.drawString("Press 'Space' to start and to continue", 100, 340);
		c.drawString("Press 'Esc' to go back to the start menu", 100, 370);
	}

	public static void pongWinScreen(String winner)
	{
		pongBackground();
		c.setColor(Color.WHITE);
		c.setFont(pong);

		// displays winner
		c.drawString(winner + " Wins!", 10, 100);
		c.setFont(instructions);
		c.drawString("Press space to play again", 100, 300);
	}

	public static void mainMenuGraphics()
	{
		c.setWindowTitle("Main Menu");
		arcadeGraphics();
		ballGraphics();
		pacmanGraphics();
		wordGraphics();
	} // End of mainMenuGraphics

	public static void arcadeGraphics()
	{
		// Pacman Grid
		c.setColor(Color.BLACK);
		c.fillRect(0, 0, c.maxx(), c.maxy());
		c.setColor(pacmanBackground);
		c.fillRect(0, 0, c.maxx(), 9);
		c.fillRect(0, c.maxy() - 10, c.maxx(), 10);

		// Top Rectangles Pointing Down
		c.fillRoundRect(104, 3, 16, 70, 15, 15);
		c.fillRoundRect(296, 3, 16, 70, 15, 15);
		c.fillRoundRect(488, 3, 16, 70, 15, 15);

		// Top Block Rectangles
		c.fillRoundRect(8, 41, 64, 32, 15, 15);
		c.fillRoundRect(152, 41, 112, 32, 15, 15);
		c.fillRoundRect(344, 41, 112, 32, 15, 15);
		c.fillRoundRect(536, 41, 64, 32, 15, 15);

		// Far Left Side
		c.fillRoundRect(8, 105, 16, 112, 15, 15);
		c.fillRoundRect(8, 249, 16, 64, 15, 15);
		c.fillRoundRect(8, 345, 64, 16, 15, 15);
		c.fillRoundRect(8, 393, 16, 64, 15, 15);
		c.fillRoundRect(-16, 441, 88, 16, 15, 15);

		// The Second-ish Row With Ts
		c.fillRoundRect(56, 105, 112, 16, 15, 15);
		c.fillRoundRect(104, 105, 16, 64, 15, 15);
		c.fillRoundRect(248, 105, 112, 16, 15, 15);
		c.fillRoundRect(296, 105, 16, 64, 15, 15);
		c.fillRoundRect(440, 105, 112, 16, 15, 15);
		c.fillRoundRect(488, 105, 16, 64, 15, 15);

		// The Second-ish Row With Crosses
		c.fillRoundRect(200, 105, 16, 112, 15, 15);
		c.fillRoundRect(152, 153, 112, 16, 15, 15);
		c.fillRoundRect(392, 105, 16, 112, 15, 15);
		c.fillRoundRect(344, 153, 112, 16, 15, 15);

		// The Third-ish Row
		c.fillRoundRect(56, 153, 16, 64, 15, 15);
		c.fillRoundRect(536, 153, 16, 64, 15, 15);

		// The Third-ish Row With Zigzags
		c.fillRoundRect(56, 249, 64, 16, 15, 15);
		c.fillRoundRect(105, 201, 16, 64, 15, 15);
		c.fillRoundRect(105, 201, 64, 16, 15, 15);
		c.fillRoundRect(440, 201, 64, 16, 15, 15);
		c.fillRoundRect(488, 201, 16, 64, 15, 15);
		c.fillRoundRect(488, 249, 64, 16, 15, 15);

		// The Fourth-ish Row With Upside Down Ls
		c.fillRoundRect(152, 249, 64, 16, 15, 15);
		c.fillRoundRect(200, 249, 16, 64, 15, 15);
		c.fillRoundRect(392, 249, 16, 64, 15, 15);
		c.fillRoundRect(392, 249, 64, 16, 15, 15);

		// The Fourth-ish Row With Ts
		c.fillRoundRect(56, 297, 112, 16, 15, 15);
		c.fillRoundRect(104, 297, 16, 64, 15, 15);
		c.fillRoundRect(248, 297, 112, 16, 15, 15);
		c.fillRoundRect(296, 297, 16, 64, 15, 15);
		c.fillRoundRect(440, 297, 112, 16, 15, 15);
		c.fillRoundRect(488, 297, 16, 64, 15, 15);

		// The Fourth-ish Row With Lines
		c.fillRoundRect(152, 345, 112, 16, 15, 15);
		c.fillRoundRect(344, 345, 112, 16, 15, 15);

		// Bottoms Ts
		c.fillRoundRect(56, 393, 112, 16, 15, 15);
		c.fillRoundRect(104, 393, 16, 64, 15, 15);
		c.fillRoundRect(248, 393, 112, 16, 15, 15);
		c.fillRoundRect(296, 393, 16, 64, 15, 15);
		c.fillRoundRect(440, 393, 112, 16, 15, 15);
		c.fillRoundRect(488, 393, 16, 64, 15, 15);

		// Bottom Upside Down Ts
		c.fillRoundRect(200, 393, 16, 64, 15, 15);
		c.fillRoundRect(152, 441, 112, 16, 15, 15);
		c.fillRoundRect(392, 393, 16, 64, 15, 15);
		c.fillRoundRect(344, 441, 112, 16, 15, 15);

		// Far Right Side
		c.fillRoundRect(584, 105, 16, 112, 15, 15);
		c.fillRoundRect(584, 249, 16, 64, 15, 15);
		c.fillRoundRect(536, 345, 64, 16, 15, 15);
		c.fillRoundRect(584, 393, 16, 64, 15, 15);
		c.fillRoundRect(536, 441, 112, 16, 15, 15);
		c.fillRoundRect(632, 41, 16, 32, 15, 15);
		c.fillRoundRect(632, 105, 16, 16, 15, 15);
		c.fillRoundRect(632, 152, 16, 64, 15, 15);
		c.fillRoundRect(632, 249, 16, 64, 15, 15);
		c.fillRoundRect(632, 345, 16, 64, 15, 15);

		// Middle Box
		c.fillRect(248, 201, 8, 64);
		c.fillRect(352, 201, 8, 64);
		c.fillRect(248, 257, 112, 8);
		c.fillRect(248, 201, 38, 8);
		c.fillRect(322, 201, 38, 8);
		c.setColor(Color.gray);
		c.fillRect(286, 202, 36, 6);
	} // End of arcadeGraphics method

	public static void ballGraphics()
	{
		c.setColor(dotColor);
		// Row 1 Balls
		for (int i = 7; i <= 87; i += 16)
		{
			c.fillOval(i, 25, 3, 3);
		} // End of Row 1 #1 for loop

		for (int i = 135; i <= 263; i += 16)
		{
			c.fillOval(i, 25, 3, 3);
		} // End of Row 1 #2 for loop

		c.fillOval(276, 22, 9, 9);
		for (int i = 327; i <= 471; i += 16)
		{
			c.fillOval(i, 25, 3, 3);
		} // End of Row 1 #3 for loop

		c.fillOval(516, 22, 9, 9);
		for (int i = 535; i <= c.maxx(); i += 16)
		{
			c.fillOval(i, 25, 3, 3);
		} // End of Row 1 #4 for loop

		// Row 2 Balls
		c.fillOval(87, 41, 3, 3);
		c.fillOval(135, 41, 3, 3);
		c.fillOval(279, 41, 3, 3);
		c.fillOval(327, 41, 3, 3);
		c.fillOval(471, 41, 3, 3);
		c.fillOval(519, 41, 3, 3);
		c.fillOval(615, 41, 3, 3);

		// Row 3 Balls
		c.fillOval(87, 57, 3, 3);
		c.fillOval(135, 57, 3, 3);
		c.fillOval(279, 57, 3, 3);
		c.fillOval(327, 57, 3, 3);
		c.fillOval(471, 57, 3, 3);
		c.fillOval(519, 57, 3, 3);
		c.fillOval(615, 57, 3, 3);

		// Row 4 Balls
		c.fillOval(87, 73, 3, 3);
		c.fillOval(135, 73, 3, 3);
		c.fillOval(279, 73, 3, 3);
		c.fillOval(327, 73, 3, 3);
		c.fillOval(471, 73, 3, 3);
		c.fillOval(519, 73, 3, 3);
		c.fillOval(615, 73, 3, 3);

		// Row 5 Balls
		for (int i = 7; i < c.maxx(); i += 16)
		{
			c.fillOval(i, 89, 3, 3);
		} // End of Row 5 for loop

		// Row 6 Balls
		c.fillOval(39, 105, 3, 3);
		c.fillOval(183, 105, 3, 3);
		c.fillOval(231, 105, 3, 3);
		c.fillOval(375, 105, 3, 3);
		c.fillOval(423, 105, 3, 3);
		c.fillOval(567, 105, 3, 3);
		c.fillOval(615, 105, 3, 3);

		// Row 7 Balls
		c.fillOval(39, 121, 3, 3);
		c.fillOval(183, 121, 3, 3);
		c.fillOval(231, 121, 3, 3);
		c.fillOval(375, 121, 3, 3);
		c.fillOval(423, 121, 3, 3);
		c.fillOval(567, 121, 3, 3);
		c.fillOval(615, 121, 3, 3);

		// Row 8 Balls
		for (int i = 39; i <= 71; i += 16)
		{
			c.fillOval(i, 137, 3, 3);
		} // End of Row 8 #1 for loop

		c.fillOval(84, 134, 9, 9);
		for (int i = 135; i <= 183; i += 16)
		{
			c.fillOval(i, 137, 3, 3);
		} // End of Row 8 #2 for loop

		for (int i = 231; i <= 279; i += 16)
		{
			c.fillOval(i, 137, 3, 3);
		} // End of Row 8 #3 for loop

		for (int i = 327; i <= 375; i += 16)
		{
			c.fillOval(i, 137, 3, 3);
		} // End of Row 8 #4 for loop

		for (int i = 423; i <= 471; i += 16)
		{
			c.fillOval(i, 137, 3, 3);
		} // End of Row 8 #5 for loop

		for (int i = 519; i <= 567; i += 16)
		{
			c.fillOval(i, 137, 3, 3);
		} // End of Row 8 #6 for loop

		c.fillOval(615, 137, 3, 3);
		c.fillOval(631, 137, 3, 3);

		// Row 9 Balls
		c.fillOval(39, 153, 3, 3);
		c.fillOval(87, 153, 3, 3);
		c.fillOval(135, 153, 3, 3);
		c.fillOval(471, 153, 3, 3);
		c.fillOval(519, 153, 3, 3);
		c.fillOval(567, 153, 3, 3);
		c.fillOval(615, 153, 3, 3);

		// Row 10 Balls
		c.fillOval(39, 169, 3, 3);
		c.fillOval(87, 169, 3, 3);
		c.fillOval(135, 169, 3, 3);
		c.fillOval(471, 169, 3, 3);
		c.fillOval(519, 169, 3, 3);
		c.fillOval(567, 169, 3, 3);
		c.fillOval(615, 169, 3, 3);

		// Row 11 Balls
		c.fillOval(39, 185, 3, 3);
		for (int i = 87; i <= 183; i += 16)
		{
			c.fillOval(i, 185, 3, 3);
		} // End of Row 11 #1 for loop

		c.fillOval(420, 182, 9, 9);
		for (int i = 439; i <= 519; i += 16)
		{
			c.fillOval(i, 185, 3, 3);
		} // End of Row 11 #1 for loop

		c.fillOval(567, 185, 3, 3);
		c.fillOval(615, 185, 3, 3);

		// Row 12 Balls
		c.fillOval(39, 201, 3, 3);
		c.fillOval(87, 201, 3, 3);
		c.fillOval(183, 201, 3, 3);
		c.fillOval(423, 201, 3, 3);
		c.fillOval(519, 201, 3, 3);
		c.fillOval(567, 201, 3, 3);
		c.fillOval(615, 201, 3, 3);

		// Row 13 Balls
		c.fillOval(39, 217, 3, 3);
		c.fillOval(87, 217, 3, 3);
		c.fillOval(183, 217, 3, 3);
		c.fillOval(423, 217, 3, 3);
		c.fillOval(519, 217, 3, 3);
		c.fillOval(567, 217, 3, 3);
		c.fillOval(615, 217, 3, 3);

		// Row 14 Balls
		for (int i = 7; i <= 87; i += 16)
		{
			c.fillOval(i, 233, 3, 3);
		} // End of Row 14 #1 for loop

		for (int i = 135; i <= 183; i += 16)
		{
			c.fillOval(i, 233, 3, 3);
		} // End of Row 14 #2 for loop

		for (int i = 423; i <= 471; i += 16)
		{
			c.fillOval(i, 233, 3, 3);
		} // End of Row 14 #3 for loop

		for (int i = 519; i <= 615; i += 16)
		{
			c.fillOval(i, 233, 3, 3);
		} // End of Row 14 #4 for loop

		// Row 15 Balls
		c.fillOval(39, 249, 3, 3);
		c.fillOval(135, 249, 3, 3);
		c.fillOval(471, 249, 3, 3);
		c.fillOval(567, 249, 3, 3);
		c.fillOval(615, 249, 3, 3);

		// Row 16 Balls
		c.fillOval(39, 265, 3, 3);
		c.fillOval(135, 265, 3, 3);
		c.fillOval(471, 265, 3, 3);
		c.fillOval(567, 265, 3, 3);
		c.fillOval(615, 265, 3, 3);

		// Row 17 Balls
		for (int i = 39; i <= 87; i += 16)
		{
			c.fillOval(i, 281, 3, 3);
		} // End of Row 17 #1 for loop

		for (int i = 423; i <= 567; i += 16)
		{
			c.fillOval(i, 281, 3, 3);
		} // End of Row 17 #2 for loop

		c.fillOval(615, 281, 3, 3);

		// Row 18 Balls
		c.fillOval(39, 297, 3, 3);
		c.fillOval(423, 297, 3, 3);
		c.fillOval(567, 297, 3, 3);
		c.fillOval(615, 297, 3, 3);

		// Row 19 Balls
		c.fillOval(39, 313, 3, 3);
		c.fillOval(423, 313, 3, 3);
		c.fillOval(567, 313, 3, 3);
		c.fillOval(615, 313, 3, 3);

		// Row 20 Balls
		for (int i = 7; i <= 87; i += 16)
		{
			c.fillOval(i, 329, 3, 3);
		} // End of Row 20 #1 for loop

		c.fillOval(132, 326, 9, 9);
		for (int i = 151; i <= 167; i += 16)
		{
			c.fillOval(i, 329, 3, 3);
		} // End of Row 20 #2 for loop

		for (int i = 327; i <= 455; i += 16)
		{
			c.fillOval(i, 329, 3, 3);
		} // End of Row 20 #3 for loop

		c.fillOval(468, 326, 9, 9);
		for (int i = 519; i <= c.maxx(); i += 16)
		{
			c.fillOval(i, 329, 3, 3);
		} // End of Row 20 #4 for loop

		// Row 21 Balls
		c.fillOval(87, 345, 3, 3);
		c.fillOval(135, 345, 3, 3);
		c.fillOval(327, 345, 3, 3);
		c.fillOval(471, 345, 3, 3);
		c.fillOval(519, 345, 3, 3);
		c.fillOval(615, 345, 3, 3);

		// Row 22 Balls
		c.fillOval(87, 361, 3, 3);
		c.fillOval(135, 361, 3, 3);
		c.fillOval(327, 361, 3, 3);
		c.fillOval(471, 361, 3, 3);
		c.fillOval(519, 361, 3, 3);
		c.fillOval(615, 361, 3, 3);

		// Row 23 Balls
		for (int i = 7; i < c.maxx() - 16; i += 16)
		{
			c.fillOval(i, 377, 3, 3);
		} // End of Row 23 for loop

		// Row 24 Balls
		c.fillOval(39, 393, 3, 3);
		c.fillOval(183, 393, 3, 3);
		c.fillOval(231, 393, 3, 3);
		c.fillOval(375, 393, 3, 3);
		c.fillOval(423, 393, 3, 3);
		c.fillOval(567, 393, 3, 3);
		c.fillOval(615, 393, 3, 3);

		// Row 25 Balls
		c.fillOval(39, 409, 3, 3);
		c.fillOval(183, 409, 3, 3);
		c.fillOval(231, 409, 3, 3);
		c.fillOval(375, 409, 3, 3);
		c.fillOval(423, 409, 3, 3);
		c.fillOval(567, 409, 3, 3);
		c.fillOval(615, 409, 3, 3);

		// Row 26 Balls
		for (int i = 39; i <= 87; i += 16)
		{
			c.fillOval(i, 425, 3, 3);
		} // End of Row 26 #1 for loop

		for (int i = 135; i <= 183; i += 16)
		{
			c.fillOval(i, 425, 3, 3);
		} // End of Row 26 #2 for loop

		for (int i = 231; i <= 263; i += 16)
		{
			c.fillOval(i, 425, 3, 3);
		} // End of Row 26 #3 for loop

		c.fillOval(276, 422, 9, 9);
		for (int i = 327; i <= 375; i += 16)
		{
			c.fillOval(i, 425, 3, 3);
		} // End of Row 26 #4 for loop

		for (int i = 423; i <= 471; i += 16)
		{
			c.fillOval(i, 425, 3, 3);
		} // End of Row 26 #5 for loop

		for (int i = 519; i <= 567; i += 16)
		{
			c.fillOval(i, 425, 3, 3);
		} // End of Row 26 #6 for loop

		c.fillOval(612, 422, 9, 9);
		c.fillOval(631, 425, 3, 3);

		// Row 27 Balls
		c.fillOval(87, 441, 3, 3);
		c.fillOval(135, 441, 3, 3);
		c.fillOval(279, 441, 3, 3);
		c.fillOval(327, 441, 3, 3);
		c.fillOval(471, 441, 3, 3);
		c.fillOval(519, 441, 3, 3);

		// Row 28 Balls
		c.fillOval(87, 457, 3, 3);
		c.fillOval(135, 457, 3, 3);
		c.fillOval(279, 457, 3, 3);
		c.fillOval(327, 457, 3, 3);
		c.fillOval(471, 457, 3, 3);
		c.fillOval(519, 457, 3, 3);

		// Row 29 Balls
		for (int i = 7; i < c.maxx(); i += 16)
		{
			c.fillOval(i, 473, 3, 3);
		} // End of Row 5 for loop
	} // End of ballGraphics method

	public static void pacmanGraphics()
	{
		c.setColor(Color.yellow);
		c.fillArc(86, 273, 19, 19, 230, 260);
	} // End of pacmanGraphics method

	public static void wordGraphics()
	{
		// Title Box
		delay(500);
		c.setColor(Color.LIGHT_GRAY);
		delay(100);
		c.drawRect(31, 26, 573, 128);
		delay(100);
		c.drawRect(34, 29, 567, 122);
		delay(100);
		c.drawRect(37, 32, 561, 116);
		delay(100);
		c.drawRect(40, 35, 555, 110);
		c.setColor(Color.yellow);
		c.setFont(arcadeFont);
		c.drawString("ARCADE", 50, 130);

		// Yahtzee Box
		delay(1000);
		c.setColor(Color.LIGHT_GRAY);
		c.drawRect(180, 165, 401, 90);
		c.drawRect(182, 167, 397, 86);
		c.setColor(menuYahtzee);
		c.fillRect(183, 168, 396, 85);
		c.setFont(yahtzeeMenuFont);
		c.setColor(Color.white);
		c.fillRect(493, 175, 70, 70);
		c.setColor(Color.black);
		c.drawString("[1] YAHTZEE!", 200, 225);
		c.fillOval(520, 205, 15, 15);

		// Mastermind Box
		delay(500);
		c.setColor(Color.LIGHT_GRAY);
		c.drawRect(180, 272, 401, 90);
		c.drawRect(182, 274, 397, 86);
		c.setColor(menuMastermind);
		c.fillRect(183, 275, 396, 85);
		c.setFont(mastermindMenuFont);
		c.setColor(Color.black);
		c.drawString("[2] MASTERMIND", 200, 333);
		for (int i = 0; i < 6; i++)
		{
			c.setColor(availableColors[i]);
			c.fillRect(i * 30 + 310, 345, 30, 10);
		}

		// Pong Box
		delay(500);
		c.setColor(Color.LIGHT_GRAY);
		c.drawRect(180, 379, 401, 90);
		c.drawRect(182, 381, 397, 86);
		c.setColor(Color.black);
		c.fillRect(183, 382, 396, 85);
		c.setFont(pongMenuFont);
		c.setColor(Color.white);
		c.drawString("[3] PONG", 200, 441);
		c.fillOval(510, 405, 15, 15);
		c.fillRect(470, 410, 15, 45);
		c.fillRect(550, 390, 15, 45);
	} // End of wordGraphics method
} // end of ArcadeCPT class
