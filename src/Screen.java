public class Screen {
    //makes 3 window objects, mostly just used as a counter
    private Window[] windows = new Window[3];
    //the three specific window objects
    public MyShipWindow myShipWindow;
    public EnemyShipWindow enemyShipWindow;
    public DialogWindow dialogWindow;
    //keeps track of the windows X and Y coordinate
    private int[] posX = new int[this.windows.length];
    private int[] posY = new int[this.windows.length];
    //keeps track of the total screen width and height
    private int totalHeight;
    private int totalWidth;
    //keeps track of all the characters, the actual screen
    private char[][] screenArray;

    //default constructor that just sets the X and Y coordinates to -1 (like a sentinel)
    public Screen() {
        for(int i = 0; i < this.windows.length; ++i) {
            posX[i] = -1;
            posY[i] = -1;
        }
    }

    //creates a window with the given x, y, width, and height
    public Window addWindow(int x, int y, int width, int height) {
        //creates the window with the given width and height
        Window newWindow = new Window(width, height);
        //variable to keep track of weather the x and y has been add
        boolean addedXY = false;
        //goes through and adds x y if available
        for(int i = 0; i < this.windows.length; ++i) {
            if (this.posY[i] == -1 && !addedXY) {
                this.posY[i] = y;
                this.posX[i] = x;
                this.windows[i] = newWindow;
                addedXY = true;
            }
        }
        return newWindow;
    }

    //this function sets the width and height of the array based on width and height of the three windows
    public void setWidthAndHeight() {
        this.totalHeight = this.windows[1].getHeight() + this.windows[2].getHeight() + 3;
        this.totalWidth  = this.windows[2].getWidth() + 2;
        this.screenArray = new char[this.totalHeight][this.totalWidth];
    }

    //this function goes through and sets the boarders around the windows
    public void makeBoarders() {
        for(int y = 0; y < this.totalHeight; ++y) {
            for(int x = 0; x < this.totalWidth; ++x) {
                if(y == 0) {
                    this.screenArray[y][x] = '-';
                }
                else if(y == this.totalHeight - 1) {
                    this.screenArray[y][x] = '-';
                }
                else if(y == this.totalHeight / 2) {
                    this.screenArray[y][x] = '-';
                }
                else if(x == 0) {
                    this.screenArray[y][x] = '-';
                }
                else if(x == this.totalWidth - 1) {
                    this.screenArray[y][x] = '-';
                }
                else if(x == this.totalWidth / 2 && y < this.totalHeight/2) {
                    this.screenArray[y][x] = '-';
                }
                else {
                    this.screenArray[y][x] = 'x';
                }
            }
        }
    }

    //sets the windows to the specialized classes for the screen
    public void setWindowsType() {
        //sets the windows array to the specific window types, but doesn't really do much
        this.windows[0] = new MyShipWindow(this.windows[0].getWidth(),this.windows[0].getHeight(), screenArray);
        this.windows[1] = new EnemyShipWindow(this.windows[1].getWidth(), this.windows[1].getHeight(), screenArray);
        this.windows[2] = new DialogWindow(this.windows[2].getWidth(), this.windows[2].getHeight(), screenArray);

        //this makes the specific windows bases on the given data from earlier functions
        myShipWindow = new MyShipWindow(this.windows[0].getWidth(),this.windows[0].getHeight(), screenArray);
        enemyShipWindow = new EnemyShipWindow(this.windows[1].getWidth(), this.windows[1].getHeight(), screenArray);
        dialogWindow = new DialogWindow(this.windows[2].getWidth(), this.windows[2].getHeight(), screenArray);
    }

    //this calls the draw functions of the specific windows, passes the x and y values
    public void draw() {
        myShipWindow.drawMyShipWindow(posX[0], posY[0]);
        enemyShipWindow.drawEnemyShipWindow(posX[1],posY[1]);
        dialogWindow.drawDialogWindow(posX[2], posY[2]);
    }

    //this essentially gets rid of the text from gamelogic, and replaces it with ' ', only works with the dialog box
    public void drawBlankDialog() {
        dialogWindow.drawDialogWindowsBlank(posX[2], posY[2]);
    }

    //goes through, adds a couple of blank lines before displaying the screen array
    public void displayScreen() {
        //adds 15 new lines in front of screen
        for(int i = 0; i < 15; ++i) {
            System.out.println();
        }

        //goes through and prints the screen array
        for(int y = 0; y < this.totalHeight; ++y) {
            for(int x = 0; x < this.totalWidth; ++x) {
                System.out.print(screenArray[y][x]);
            }
            System.out.println();
        }
    }

    //inserts 3 strings for the prompt area
    public void setDialogWindowPrompt(String s1, String s2, String s3) {
        dialogWindow.prompt.insertPrompt(s1, s2, s3);
    }

    //inserts a string for the option 1 area
    public void setDialogWindowOption1(String s1) {
        dialogWindow.option1.insertOption1(s1);
    }

    //inserts a string for the option 2 area
    public void setDialogWindowOption2(String s1) {
        dialogWindow.option2.insertOption2(s1);
    }

    //inserts a string for the option 3 area
    public void setDialogWindowOption3(String s1) {
        dialogWindow.option3.insertOption3(s1);
    }

    //inserts 2 strings for the user input area
    public void setDialogWindowUserInput(String s1, String s2) {
        dialogWindow.userInput.insertUserInput(s1, s2);
    }
}
