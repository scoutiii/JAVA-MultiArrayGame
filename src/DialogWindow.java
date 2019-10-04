//subclass of window which is dedicated to the dialog boxes
public class DialogWindow extends Window {
    //creates an object for the prompt area
    public DialogWindowPrompt prompt;
    //creates an object for the first option
    public DialogWindowOption1 option1;
    //creates an object for the second option
    public DialogWindowOption2 option2;
    //creates an object for the third option
    public DialogWindowOption3 option3;
    //creates an object for the user input
    public DialogWindowUserInput userInput;
    //the double char array which is the screen
    private char[][] mapArray;

    //default constructor which sets the widths and heights of the the dialog window
    //uses the same information to set the values for the sub dialog windows
    public DialogWindow(int width, int height, char[][] mapArray) {
        super(width, height);
        this.mapArray = mapArray;
        prompt = new DialogWindowPrompt(width, 4, this.mapArray);
        option1 = new DialogWindowOption1(width, 2, this.mapArray);
        option2 = new DialogWindowOption2(width, 2, this.mapArray);
        option3 = new DialogWindowOption3(width, 2, this.mapArray);
        userInput = new DialogWindowUserInput(width, 2, this.mapArray);
    }

    //function which calls all the sub classes draw functions with variations on the given starting y position
    public void drawDialogWindow(int startX, int startY) {
        this.prompt.drawDialogWindowPromptFinal(startX, startY);
        this.option1.drawDialogWindowOption1Final(startX, startY + 4);
        this.option2.drawDialogWindowOption2Final(startX, startY + 6);
        this.option3.drawDialogWindowOption3Final(startX, startY + 8);
        this.userInput.drawDialogWindowUserInputFinal(startX, startY + 10);
    }

    //function which essentially sets all the sub windows to blank spaces, with same variations on the given y
    public void drawDialogWindowsBlank(int startX, int startY) {
        this.prompt.drawDialogWindowPromptBlank(startX, startY);
        this.option1.drawDialogWindowOption1Blank(startX, startY + 4);
        this.option2.drawDialogWindowOption2Blank(startX, startY + 6);
        this.option3.drawDialogWindowOption3Blank(startX, startY + 8);
        this.userInput.drawDialogWindowUserInputBlank(startX, startY + 10);
    }
}

//a subclass of window dedicated to the prompt in the dialog window
class DialogWindowPrompt extends Window {
    //the double char array which is the screen
    public char[][]mapArray;

    //default constructor which makes a window with given width, height, and sets the screen array
    public DialogWindowPrompt(int width, int height, char[][] mapArray) {
        super(width, height);
        this.mapArray = mapArray;
    }

    //function which sets everything to ' '(blank space) and adds "Prompt: " to the start line
    public void drawDialogWindowPromptBlank(int startX, int startY) {
        for(int y = 0; y < this.getHeight(); ++y) {
            for(int x = 0; x < this.getWidth(); ++x) {
                if(y == this.getHeight() - 1) {
                    this.mapArray[startY + y][startX + x] = '*';
                }
                else if(x == 1) {
                    this.mapArray[startY + y][startX + x] = '>';
                }
                else {
                    this.mapArray[startY + y][startX + x] = ' ';
                }
            }
        }

        //adds the string prompt: to the front of the window
        String prompt = "Prompt: ";
        char[] promptChar = prompt.toCharArray();
        for(int x = 2; x < promptChar.length + 1; ++x) {
            this.mapArray[startY][startX + x] = promptChar[x - 2];
        }
    }

    //function which basically just calls draw blank
    public void drawDialogWindowPromptFinal(int startX, int startY) {
        drawDialogWindowPromptBlank(startX, startY);
    }

    //this will insert 3 given strings
    public void insertPrompt(String promptString1, String promptString2, String promptString3) {
        //converts the given strings to character arrays
        char[] promptString1toChar = promptString1.toCharArray();
        char[] promptString2toChar = promptString2.toCharArray();
        char[] promptString3toChar = promptString3.toCharArray();

        //goes through and changes the double char array with the new strings
        for(int x = 11; x - 11 < promptString1toChar.length; ++x) {
            this.mapArray[14][x] = promptString1toChar[x - 11];
        }
        for(int x = 4; x - 4 < promptString2toChar.length; ++x) {
            this.mapArray[15][x] = promptString2toChar[x - 4];
        }
        for(int x = 4; x - 4 < promptString3toChar.length; ++x) {
            this.mapArray[16][x] = promptString3toChar[x - 4];
        }
    }
}

//a subclass of window which is dedicated to the first option in the dialog window
class DialogWindowOption1 extends Window {
    //double char array which is the screen
    private char[][] mapArray;

    //default constructor which sets the width, height, and double char array
    public DialogWindowOption1(int width, int height, char[][]mapArray) {
        super(width, height);
        this.mapArray = mapArray;
    }

    //goes through and adds the boarders and option1 string
    public void drawDialogWindowOption1Blank(int startX, int startY) {
        for(int y = 0; y < this.getHeight(); ++y) {
            for(int x = 0; x < this.getWidth(); ++x) {
                if(y == this.getHeight() - 1) {
                    this.mapArray[startY + y][startX + x] = '*';
                }
                else if(x == 1) {
                    this.mapArray[startY + y][startX + x] = '>';
                }
                else {
                    this.mapArray[startY + y][startX + x] = ' ';
                }
            }
        }

        //inserts the option1 string
        String option = "Option1: ";
        char[] optionChar = option.toCharArray();
        for(int x = 2; x < optionChar.length + 1; ++x) {
            this.mapArray[startY][startX + x] = optionChar[x - 2];
        }
    }

    //basically just calls draw blank
    public void drawDialogWindowOption1Final(int startX, int startY) {
        drawDialogWindowOption1Blank(startX, startY);
    }

    //takes a string and inserts it into the option 1 spot
    public void insertOption1(String option1String1) {
        char[] option1String1ToChar = option1String1.toCharArray();

        for(int x = 12; x - 12 < option1String1ToChar.length; ++x) {
            this.mapArray[18][x] = option1String1ToChar[x - 12];
        }
    }
}

//a subclass of window dedicated the the second option in the dialog window
class DialogWindowOption2 extends Window {
    //the double char array which is the screen
    private char[][] mapArray;

    //default constructor which sets the width, height, and double char array
    public DialogWindowOption2(int width, int height, char[][] mapArray) {
        super(width, height);
        this.mapArray = mapArray;
    }

    //goes through and sets the boarder and the blank space, and the option2 string
    public void drawDialogWindowOption2Blank(int startX, int startY) {
        for(int y = 0; y < this.getHeight(); ++y) {
            for(int x = 0; x < this.getWidth(); ++x) {
                if(y == this.getHeight() - 1) {
                    this.mapArray[startY + y][startX + x] = '*';
                }
                else if(x == 1) {
                    this.mapArray[startY + y][startX + x] = '>';
                }
                else {
                    this.mapArray[startY + y][startX + x] = ' ';
                }
            }
        }

        //inserts the option2 string
        String option = "Option2: ";
        char[] optionChar = option.toCharArray();
        for(int x = 2; x < optionChar.length + 1; ++x) {
            this.mapArray[startY][startX + x] = optionChar[x - 2];
        }
    }

    //basically just calls draw blank
    public void drawDialogWindowOption2Final(int startX, int startY) {
        drawDialogWindowOption2Blank(startX, startY);
    }

    //takes a string and inserts is where option2 is
    public void insertOption2(String option2String1) {
        char[] option2String1ToChar = option2String1.toCharArray();

        for(int x = 12; x - 12 < option2String1ToChar.length; ++x) {
            this.mapArray[20][x] = option2String1ToChar[x - 12];
        }
    }
}

//a subclass of window which is dedicated to the third option in the dialog window
class DialogWindowOption3 extends Window {
    //the double char array which is the screen
    private char[][] mapArray;

    //default constructor which sets the width, height, and double char array
    public DialogWindowOption3(int width, int height, char[][] mapArray) {
        super(width, height);
        this.mapArray = mapArray;
    }

    //inserts the blank spaces and option3 string
    public void drawDialogWindowOption3Blank(int startX, int startY) {
        for(int y = 0; y < this.getHeight(); ++y) {
            for(int x = 0; x < this.getWidth(); ++x) {
                if(y == this.getHeight() - 1) {
                    this.mapArray[startY + y][startX + x] = '*';
                }
                else if(x == 1) {
                    this.mapArray[startY + y][startX + x] = '>';
                }
                else {
                    this.mapArray[startY + y][startX + x] = ' ';
                }
            }
        }

        //inserts the option3 string
        String option = "Option3: ";
        char[] optionChar = option.toCharArray();
        for(int x = 2; x < optionChar.length + 1; ++x) {
            this.mapArray[startY][startX + x] = optionChar[x - 2];
        }
    }

    //basically just calls draw blank
    public void drawDialogWindowOption3Final(int startX, int startY) {
        drawDialogWindowOption3Blank(startX, startY);
    }

    //takes a string and inserts it where option3 is
    public void insertOption3(String option3String1) {
        char[] option3String1ToChar = option3String1.toCharArray();

        for(int x = 12; x - 12 < option3String1ToChar.length; ++x) {
            this.mapArray[22][x] = option3String1ToChar[x - 12];
        }
    }
}

//a subclass of window dedicated to the user input of the dialog window
class DialogWindowUserInput extends Window {
    //the double char array which is the screen
    private char[][] mapArray;

    //default constructor which sets the width, height, and double char array
    public DialogWindowUserInput(int width, int height, char[][] mapArray) {
        super(width, height);
        this.mapArray = mapArray;
    }

    //inserts the blank spaces and the user input string
    public void drawDialogWindowUserInputBlank(int startX, int startY) {
        for(int y = 0; y < this.getHeight(); ++y) {
            for(int x = 0; x < this.getWidth(); ++x) {
                if(x == 1) {
                    this.mapArray[startY + y][startX + x] = '>';
                }
                else {
                    this.mapArray[startY + y][startX + x] = ' ';
                }
            }
        }

        //inserts the input: string
        String input = "Input: ";
        char[] inputChar = input.toCharArray();
        for(int x = 2; x < inputChar.length + 1; ++x) {
            this.mapArray[startY][startX + x] = inputChar[x - 2];
        }
    }

    //basically just calls the draw blank
    public void drawDialogWindowUserInputFinal(int startX, int startY) {
        drawDialogWindowUserInputBlank(startX, startY);
    }

    //takes two strings and inserts them where the user input goes
    public void insertUserInput(String userInputString1, String userInputString2) {
        char[] userInputString1ToChar = userInputString1.toCharArray();
        char[] userInputString2ToChar = userInputString2.toCharArray();

        for(int x = 10; x - 10 < userInputString1ToChar.length; ++x) {
            this.mapArray[24][x] = userInputString1ToChar[x - 10];
        }
        for(int x = 4; x - 4 < userInputString2ToChar.length; ++x) {
            this.mapArray[25][x] = userInputString2ToChar[x - 4];
        }
    }
}