import java.util.*;

public class GameLogic {
    //the screen object which everything happens
    private Screen display;
    //keeps track of the players health
    private int playerShipHealth;
    //keeps track of the enemy's health
    private int enemyShipHealth;
    //keeps track of the players shield
    private int playerShield;
    //keeps track of the enemy's shield
    private int enemyShield;
    //keeps track of the players missile count
    private int playerMissile;
    //keeps track of the enemy's missile count
    private int enemyMissile;
    //keeps track of player system damage
    private boolean isPlayerPilotDamaged;
    private boolean isPlayerGunLDamaged;
    private boolean isPlayerGunMDamaged;
    private boolean isPlayerShieldDamaged;
    private boolean isPlayerEngineDamaged;
    //keeps track of enemy system damage
    private boolean isEnemyPilotDamaged;
    private boolean isEnemyGunLDamaged;
    private boolean isEnemyGunMDamaged;
    private boolean isEnemyShieldDamaged;
    private boolean isEnemyEngineDamaged;

    private final int laserDamage = 1;
    private final int missileDamage = 2;

    private double playerEvasionPercent;
    private double enemyEvasionPercent;

    //constructor that initializes all the above values
    public GameLogic() {
        this.display = createScreen();
        playerShipHealth = 30;
        enemyShipHealth = 20;
        playerShield = 1;
        enemyShield = 1;
        playerMissile = 10;
        enemyMissile = 5;

        isPlayerPilotDamaged = false;
        isPlayerGunLDamaged = false;
        isPlayerGunMDamaged = false;
        isPlayerShieldDamaged = false;
        isPlayerEngineDamaged = false;

        isEnemyPilotDamaged = false;
        isEnemyGunLDamaged = false;
        isEnemyGunMDamaged = false;
        isEnemyShieldDamaged = false;
        isEnemyEngineDamaged = false;

        playerEvasionPercent = .3;
        enemyEvasionPercent = .3;

    }

    //helper function that does all the functions to creates the windows and such (Don't Change These values!!!)
    static private Screen createScreen() {
        Screen test = new Screen();
        //These values below, don't change
        test.addWindow(1,1,64, 12);
        test.addWindow(66,1,64,12);
        test.addWindow(1,14,129,12);
        test.setWidthAndHeight();
        test.makeBoarders();
        test.setWindowsType();
        test.draw();
        return test;
    }




    //this function is the game loop which is basically the game
//    public boolean gameLoop() {
//        //calls a function that displays all the info for the first turn
//        startingScreen();
//        //the actual loop that runs till someone dies basically
//        do {
//            //gets the user input
//            int input1 = getUserInput();
//            //displays screen according to user input
//            screenWithUserInput(input1);
//            //logic for laser gun shooting
//            if(input1 == 1) {
//                //where you want to shoot, either front half or back half of ship, or random
//                int input2 = getUserInput();
//                //if you choose to shot the front half
//                if(input2 == 1) {
//                    screenWithGivenValues(input1, input1 + 3, 7, 8, 9, input1, 0);
//                    int input3 = getUserInput();
//                    //if you choose to shot the pilot
//                    if(input3 == 1) {
//                        //if the enemy shield is down
//                        if(enemyShield == 0) {
//                            screenWithGivenValues(97,99, 100, 100, 100,  -1, 0);
//                            subtractEnemyHealth(laserDamage);
//                            isEnemyPilotDamaged = true;
//                        }
//                        //if the enemy shield is up
//                        else if(enemyShield > 0) {
//                            screenWithGivenValues(98, 100, 100, 100, 100, -1, 0);
//                        }
//                    }
//                    //if you choose to shoot the gun L
//                    if(input3 == 2) {
//                        //if the enemy shield is down
//                        if(enemyShield == 0) {
//                            screenWithGivenValues(97,99, 100, 100, 100,  -1, 0);
//                            subtractEnemyHealth(laserDamage);
//                            isEnemyGunLDamaged = true;
//                        }
//                        //if the enemy shield is up
//                        else if(enemyShield > 0) {
//                            screenWithGivenValues(98, 100, 100, 100, 100, -1, 0);
//                        }
//                    }
//                    //if you choose to shoot the gun M
//                    if(input3 == 3) {
//                        //if the enemy shield is down
//                        if(enemyShield == 0) {
//                            screenWithGivenValues(97,99, 100, 100, 100,  -1, 0);
//                            subtractEnemyHealth(laserDamage);
//                            isEnemyGunMDamaged = true;
//                        }
//                        //if the enemy shield is up
//                        else if(enemyShield > 0) {
//                            screenWithGivenValues(98, 100, 100, 100, 100, -1, 0);
//                        }
//                    }
//                    //if you want to shoot
//                    if(input2 == 2) {
//                        //screenWithGivenValues(input1, input1 + 3, );
//
//
//
//
//                    }
//
//
//
//                }
//            }
//
//
//
//
//
//
//        } while(playerShipHealth > 0);
//
//        return false;
//    }

    /**
     * enemy turn
     */

    public void enemyTurn() {
        //randomly picks a thing to do, ie shoot laser, missile, or wait
        int enemyChoice = (int)(Math.random() * 3) + 1;
        //randomly picks a room
        int room = (int)(Math.random() * 5) + 1;

        //if they "Choose" to shoot lasers
        if(enemyChoice == 1) {
            //if the enemies laser gun system is broken
            if(isEnemyGunLDamaged == true) {
                //tells you they can't shoot
                screenWithGivenValues(-11, 0, 100, 100, 100, -1, 0);
                getUserInput();
            }
            //checks to see if the player shields are up
            else if(playerShield > 0 && isPlayerShieldDamaged == false) {
                //displays what room they shot at, and that it misses, so press 123 then enter
                screenWithGivenValues(-50 + (-1 * room),-98, 100, 100, 100, -1, 0 );
                getUserInput();
            }

            //if the players shields are down
            else {
                //randomly picks a number to see if it will hit
                if(Math.random() > playerEvasionPercent) {
                    //adjusts the values based on where the room is
                    switch (room) {
                        //for the players pilot system
                        case 1: isPlayerPilotDamaged = true;
                        if(playerEvasionPercent > .10) {
                            playerEvasionPercent -= .1;
                            break;
                        }
                        break;
                        //for the players laser gun system
                        case 2: isPlayerGunLDamaged = true;
                            break;
                        //for the players missile system
                        case 3: isPlayerGunMDamaged = true;
                            break;
                        //for the players shield system
                        case 4: if(isPlayerShieldDamaged == false) {
                            isPlayerShieldDamaged = true;
                            subtractPlayerSheild(laserDamage);
                            break;
                        }
                        else {
                            isPlayerShieldDamaged = true;
                            break;
                        }
                        //for the players engine system
                        case 5: isPlayerEngineDamaged = true;
                        if(playerEvasionPercent > .10) {
                            playerEvasionPercent -= .1;
                            break;
                        }
                        break;
                    }
                    //decreases the players health
                    subtractPlayerHealth(laserDamage);
                    //displays the info of what just happened
                    screenWithGivenValues(-50 + (-1 * room),-101, 100, 100, 100, -1, 0);
                    getUserInput();
                }
                else {
                    //if they miss, tells you, and then gets your input
                    screenWithGivenValues(-50 + (-1 * room), -103, 100, 100, 100, -1, 0);
                    getUserInput();
                }
            }
        }
        else if(enemyChoice == 2) {
            //if the system is broken
            if(isEnemyGunMDamaged == true) {
                //tells you they can't shoot
                screenWithGivenValues(-12, 0, 100, 100, 100, -1, 0);
                getUserInput();
            }
            //if the enemy is out of missiles
            else if(enemyMissile == 0) {
                //tells you they are out of missiles
                screenWithGivenValues(-8, 0, 100, 100, 100, -1, 0);
                getUserInput();
            }

            //if they can shoot a missile
            else {
                //takes away an enemy missile
                subtractEnemyMissile();
                //sees if it will hit
                if(Math.random() > playerEvasionPercent) {
                    //adjusts the values based on where the room is
                    switch (room) {
                        //for the players pilot system
                        case 1: isPlayerPilotDamaged = true;
                            if(playerEvasionPercent > .10) {
                                playerEvasionPercent -= .1;
                                break;
                            }
                            break;
                        //for the players laser gun system
                        case 2: isPlayerGunLDamaged = true;
                            break;
                        //for the players missile system
                        case 3: isPlayerGunMDamaged = true;
                            break;
                        //for the players shield system
                        case 4: if(isPlayerShieldDamaged == false) {
                            isPlayerShieldDamaged = true;
                            subtractPlayerSheild(laserDamage);
                            break;
                        }
                        else {
                            isPlayerShieldDamaged = true;
                            break;
                        }
                            //for the players engine system
                        case 5: isPlayerEngineDamaged = true;
                            if(playerEvasionPercent > .10) {
                                playerEvasionPercent -= .1;
                                break;
                            }
                            break;
                    }
                    //decreases the players health
                    subtractPlayerHealth(missileDamage);
                    //displays the info of what just happened
                    screenWithGivenValues(-50 + (-1 * room),-102, 100, 100, 100, -1, 0);
                    getUserInput();
                }
                else {
                    //if they miss, tells you, and then gets your input
                    screenWithGivenValues(-50 + (-1 * room), -104, 100, 100, 100, -1, 0);
                    getUserInput();
                }
            }
        }
        else if(enemyChoice == 3) {
            //resets shields and fixes enemy damaged system
            screenWithGivenValues(-80, -81, 100, 100, 100, -1, 0);
            //recharges shields
            if(enemyShield < 2) {
                addEnemySheild();
            }
            //fixes all systems
            isEnemyPilotDamaged = false;
            isEnemyGunLDamaged = false;
            isEnemyGunMDamaged = false;
            isEnemyShieldDamaged = false;
            isEnemyEngineDamaged = false;
            //gets user input
            getUserInput();
        }
    }


    /**
     * game simple
     */

    public void gameLoopSimple() {
        //prints out the starting screen
        startingScreen();

        //starts the loop
        do {
            //gets the first choice, ie shoot laser, missile, wait
            int input1 = getUserInput();
            //prints what you choose
            //screenWithUserInput(input1);
            //randomly picks a room to shot at
            int room = (int)(Math.random() * 5) + 1;



            //if you choose to shot laser guns
            if(input1 == 1) {

                //if your laser gun system is broken
                if(isPlayerGunLDamaged == true) {
                    //tells you that your laser gun is damaged
                    screenWithGivenValues(-9, 0, 100, 100, 100, -1, 0);
                    getUserInput();
                    enemyTurn();
                    screenPostTurn(-5);
                    continue;
                }
                //if the enemy shield is up
                else if(enemyShield > 0 && isEnemyShieldDamaged == false) {
                    //says where you target and says it misses
                    screenWithGivenValues(room + 50, 98, 100, 100, 100, -1, 0);
                    //just to get back to main screen
                    getUserInput();
                    enemyTurn();
                    screenPostTurn(-5);
                    continue;
                }

                //if the shields are down, sees if it will hit
                else {
                    //randomly generates a number, if that number is greater than the evasion percent, then it counts as a hit
                    if(Math.random() > enemyEvasionPercent) {
                        //updates the values of the hit rooms
                        switch (room) {
                            //values for the piloting room, decreases evasion by 10%
                            case 1: isPlayerPilotDamaged = true;
                            if(enemyEvasionPercent > .10) {
                                enemyEvasionPercent -= .1;
                                break;
                            }
                            break;
                            //values for the laser gun room
                            case 2: isEnemyGunLDamaged = true;
                                break;
                            //values for the missile room
                            case 3: isEnemyGunMDamaged = true;
                                break;
                            //values for the shield room
                            case 4: if(isEnemyShieldDamaged == false) {
                                isEnemyShieldDamaged = true;
                                subtractEnemySheild(laserDamage);
                                break;
                            }
                            else {
                                isEnemyShieldDamaged = true;
                            }
                                //values for the engine room, decreases evasion by 10%
                            case 5: isPlayerEngineDamaged = true;
                                if(enemyEvasionPercent > .10) {
                                    enemyEvasionPercent -= .1;
                                    break;
                                }
                                break;
                        }
                        //decreases the enemy's health
                        subtractEnemyHealth(laserDamage);
                        //displays the result of shooting
                        screenWithGivenValues(room + 50, 99, 100, 100, 100, -1, 0);
                        //enemyTurn();
                    }
                    //if the weapon misses
                    else {
                        //tells you that you miss
                        screenWithGivenValues(room + 50, 100, 100, 100, 100, -1, 0);
                        getUserInput();
                        enemyTurn();
                        screenPostTurn(-5);
                        continue;
                    }
                }
            }



            //if you want to shoot a missile
            else if(input1 == 2) {
                //if your missile system is broken
                if(isPlayerGunMDamaged == true) {
                    //tells you your system is damaged
                    screenWithGivenValues(-10, 0, 100, 100, 100, -1, 0);
                    getUserInput();
                    enemyTurn();
                    screenPostTurn(-5);
                    continue;
                }
                //checks to see if the player has missiles
                else if(playerMissile == 0) {
                    //just says you are out of missiles, try something else
                    screenWithGivenValues(-6, 0, 100, 100, 100, -1, 0);
                    //goes back to main screen
                    getUserInput();
                    enemyTurn();
                    screenPostTurn(-5);
                    continue;
                }

                //basically if you have missiles, do this
                else {
                    //decreases the missile by one
                    subtractPlayerMissile();
                    //if a random number is greater that the enemy evasion, then it hits
                    if(Math.random() > enemyEvasionPercent) {
                        //updates the values of the hit rooms
                        switch (room) {
                            //values for the piloting room, decreases evasion by 10%
                            case 1: isPlayerPilotDamaged = true;
                                if(enemyEvasionPercent > .10) {
                                    enemyEvasionPercent -= .1;
                                    break;
                                }
                                break;
                            //values for the laser gun room
                            case 2: isEnemyGunLDamaged = true;
                                break;
                            //values for the missile room
                            case 3: isEnemyGunMDamaged = true;
                                break;
                            //values for the shield room
                            case 4: if(isEnemyShieldDamaged == false) {
                                isEnemyShieldDamaged = true;
                                subtractEnemySheild(laserDamage);
                                break;
                            }
                            else {
                                isEnemyShieldDamaged = true;
                            }
                                //values for the engine room, decreases evasion by 10%
                            case 5: isPlayerEngineDamaged = true;
                                if(enemyEvasionPercent > .10) {
                                    enemyEvasionPercent -= .1;
                                    break;
                                }
                                break;
                        }
                        subtractEnemyHealth(missileDamage);
                        //displays the result of shooting
                        screenWithGivenValues(room + 50, 99, 100, 100, 100, -1, 0);
                        //enemyTurn();
                    }
                    //if you miss
                    else {
                        //tells you that you miss
                        screenWithGivenValues(room + 50, 100, 100, 100, 100, -1, 0);
                        getUserInput();
                        enemyTurn();
                        screenPostTurn(-5);
                        continue;
                    }
                }
            }




            //if you choose to wait a turn
            else if(input1 == 3) {
                //tells you that you wait and things get fixed and shields recharge
                screenWithGivenValues(80, 81, 100, 100, 100, -1, 0);
                //regenerates the shields up to a certain value
                if(playerShield < 2) {
                    addPlayerSheild();
                }
                //sets all systems to not damaged
                isPlayerPilotDamaged  = false;
                isPlayerGunMDamaged   = false;
                isPlayerGunLDamaged   = false;
                isPlayerShieldDamaged = false;
                isPlayerEngineDamaged = false;
                //gets you back to the main screen
                getUserInput();
                enemyTurn();
                screenPostTurn(-7);
                continue;
            }



            //just to get back to main screen
            getUserInput();
            enemyTurn();
            screenPostTurn(50 - room);


        } while(playerShipHealth > 0 && enemyShipHealth > 0);


    }


    //the function that displays opening info and how to input choices
    private void startingScreen() {
        screenWithGivenValues(-1,-2,1,2,3,-1,0);
    }

    //the function which takes the user input and displays info accordingly
    private void screenWithUserInput(int input) {
        if(input == 1 || input == 2) {
            screenWithGivenValues(input, input + 3, 1 + 3, 2 + 3, 3 + 3, input, 0);
        }
        else if(input == 3) {
            screenWithGivenValues(input, input + 3, 100, 100, 100, input, 0);
        }
    }

    private void screenPostTurn(int input) {
        screenWithGivenValues(input, -4, 1, 2, 3, -1, 0);
    }

    //creates prompts when you pass is certain values
    private void screenWithGivenValues(int promptString1, int promptString2, int optionString1, int optionString2, int optionString3, int inputString1, int inputString2) {
        //clears the screen of previous, unnecessary text
        display.drawBlankDialog();

        display.setDialogWindowPrompt(getPromptStrings(promptString1), getPromptStrings(promptString2), getShipsStatus());
        display.setDialogWindowOption1(getOptionStrings(optionString1));
        display.setDialogWindowOption2(getOptionStrings(optionString2));
        display.setDialogWindowOption3(getOptionStrings(optionString3));
        display.setDialogWindowUserInput(getInputStrings(inputString1), getInputStrings(inputString2));

        showDisplay();
    }


    //function which calls the displayscreen function from the Screen class
    private void showDisplay() {
        this.display.displayScreen();
    }

    //a function which holds all the strings for the prompt,
    private String getPromptStrings(int choice) {
        //negative numbers correspond with the very first screen (not really anymore)
        //then the positive numbers go based on the choice the user makes
        switch (choice) {
            case 0: return "";
            //this is for the introductory screen
            case -1: return "There is an enemy Ship! Looks like an Inter Planetary Pirate ship.";
            case -2: return "What would you like to do? Looks like is has a similar layout to our ship.";
            case -3: return "this is a dummy line for the status bar... health for you: 20, shields 1, enemy health: 20, shields 1";
            case -4: return "Now What?";
            case -5: return "Well that didn't work...";
            case -6: return "You are out of missiles.";
            case -7: return "All systems operational, and shields are up.";
            case -8: return "They are out of missiles...";
            case -9: return "Your Laser gun system is damaged, you can't shoot!";
            case -10: return "Your Missile system is damaged, you can't shoot!";
            case -11: return "Their Laser gun system is damaged, they can't shoot!";
            case -12: return "Their Missile system is damaged, they can't shoot!";
            //this is for what you choose to do
            case 1: return "You want to shot your laser gun at the enemy ship.";
            case 2: return "You want to shot a missile at the enemy ship.";
            case 3: return "You decided to wait and let your shields recharge.";

            case 4: return "Where do you want to shoot?";
            case 5: return "Where do you want to shoot?";
            case 6: return "";

            case 45: return "You damaged their Engine system!";
            case 46: return "You damaged their Shield system!";
            case 47: return "You damaged their Missile system!";
            case 48: return "You damaged their Laser Gun system!";
            case 49: return "You damaged their Piloting system!";
            case 51: return "You shoot at their Piloting system!";
            case 52: return "You shoot at their Laser gun system!";
            case 53: return "You shoot at their Missile system!";
            case 54: return "You shoot at their Shield system!";
            case 55: return "You shoot at their Engine system!";

            case -45: return "They damaged your Engine system!";
            case -46: return "They damaged your Shield system!";
            case -47: return "They damaged your Missile system!";
            case -48: return "They damaged your Laser Gun system!";
            case -49: return "They damaged your Piloting system!";
            case -51: return "They shoot at your Piloting system!";
            case -52: return "They shoot at your Laser gun system!";
            case -53: return "They shoot at your Missile system!";
            case -54: return "They shoot at your Shield system!";
            case -55: return "They shoot at your Engine system!";

            case 80: return "You decide to wait...";
            case 81: return "Your shields recharge and your systems are fixed.";
            case -80: return "They decide to wait..";
            case -81: return "Their shields recharge, and systems get repaired.";

            case 97: return "Their shield is down!";
            case 98: return "Their shield is up, your laser can't penetrate!";
            case -98: return "Your shields are up, their lasers can't penetrate!";
            case 99: return "It hit!";
            case -99: return "They landed the shot!";
            case 100: return "It missed!";
            case -100: return "They missed!";

            case -101: return "The laser hit!";
            case -102: return "The missile hit!";
            case -103: return "The laser missed.";
            case -104: return "The missile missed.";
        }
        //if the input given is not one of the above
        return "Error";
    }

    //function which makes the status bar based on each ships status lol
    private String getShipsStatus() {
        //the basic strings which will hold the info
        String playerStatusBarBase = "PLAYER STATUS: H:   S:  M:   E%:   ";
        String enemyStatusBarBase  = " ENEMY STATUS: H:   S:  M:   E%:   ";

        //converts the strings above to character arrays
        char[] playerStatusBarBaseToChar = playerStatusBarBase.toCharArray();
        char[] enemyStatusBarBaseToChar  = enemyStatusBarBase.toCharArray();

        //makes a string from the players health, then converts to character array
        String playerHealth;
        if(this.playerShipHealth < 10) {
            playerHealth = "0" + this.playerShipHealth;
        }
        else {
            playerHealth = "" + this.playerShipHealth;
        }
        char[] playerHealthToChar = playerHealth.toCharArray();

        //makes a string from the enemy's health, then converts to character array
        String enemyHealth;
        if(this.enemyShipHealth < 10) {
            enemyHealth = "0" + this.enemyShipHealth;
        }
        else {
            enemyHealth = "" + this.enemyShipHealth;
        }
        char[] enemyHealthToChar = enemyHealth.toCharArray();

        //makes a string from the players shield, then converts to character array
        String playerShield = "" + this.playerShield;
        char[] playerShieldToChar = playerShield.toCharArray();

        //makes a string from the enemy's shield, then converts to character array
        String enemyShield = "" + this.enemyShield;
        char[] enemyShieldToChar = enemyShield.toCharArray();

        //makes a string from the players missile count, then converts to character array
        String playerMissile;
        if(this.playerMissile < 10) {
            playerMissile = "0" + this.playerMissile;
        }
        else {
            playerMissile = "" + this.playerMissile;
        }
        char[] playerMissileToChar = playerMissile.toCharArray();

        //makes a string from the enemy's missile count, then converts to character array
        String enemyMissile;
        if(this.enemyMissile < 10) {
            enemyMissile = "0" + this.enemyMissile;
        }
        else {
            enemyMissile = "" + this.enemyMissile;
        }
        char[] enemyMissileToChar = enemyMissile.toCharArray();

        //makes a string from the players evasion percent
        String playerEvasion;
        if(this.playerEvasionPercent * 100 < 10) {
            playerEvasion = "00" + (this.playerEvasionPercent * 100);
        }
        else if(this.playerEvasionPercent * 100 < 100) {
            playerEvasion = "0" + (this.playerEvasionPercent * 100);
        }
        else {
            playerEvasion = "" + this.playerEvasionPercent;
        }
        char[] playerEvasionToChar = playerEvasion.toCharArray();

        //makes a string from the enemy's evasion percent
        String enemyEvasion;
        if(this.enemyEvasionPercent * 100 < 10) {
            enemyEvasion = "00" + (this.enemyEvasionPercent * 100);
        }
        else if(this.enemyEvasionPercent * 100 < 100) {
            enemyEvasion = "0" + (this.enemyEvasionPercent *100);
        }
        else {
            enemyEvasion = "" + this.enemyEvasionPercent;
        }
        char[] enemyEvasionToChar = enemyEvasion.toCharArray();

        //sets parts of base array to the players values
        playerStatusBarBaseToChar[17] = playerHealthToChar[0];
        playerStatusBarBaseToChar[18] = playerHealthToChar[1];
        playerStatusBarBaseToChar[22] = playerShieldToChar[0];
        playerStatusBarBaseToChar[26] = playerMissileToChar[0];
        playerStatusBarBaseToChar[27] = playerMissileToChar[1];
        playerStatusBarBaseToChar[32] = playerEvasionToChar[0];
        playerStatusBarBaseToChar[33] = playerEvasionToChar[1];
        playerStatusBarBaseToChar[34] = playerEvasionToChar[2];

        //sets parts of base array to the enemy's values
        enemyStatusBarBaseToChar[17] = enemyHealthToChar[0];
        enemyStatusBarBaseToChar[18] = enemyHealthToChar[1];
        enemyStatusBarBaseToChar[22] = enemyShieldToChar[0];
        enemyStatusBarBaseToChar[26] = enemyMissileToChar[0];
        enemyStatusBarBaseToChar[27] = enemyMissileToChar[1];
        enemyStatusBarBaseToChar[32] = enemyEvasionToChar[0];
        enemyStatusBarBaseToChar[33] = enemyEvasionToChar[1];
        enemyStatusBarBaseToChar[34] = enemyEvasionToChar[2];

        //converts the character arrays above into strings
        String playerStatusBarFinal = new String(playerStatusBarBaseToChar);
        String enemyStatusBarFinal = new String(enemyStatusBarBaseToChar);

        //returns the new string with both ships values
        return playerStatusBarFinal + enemyStatusBarFinal;
    }

    //function which has the strings for the option windows
    private String getOptionStrings(int choice) {
        //these are the common ones, maybe the only ones
        switch (choice) {
            case 0: return "";
            case 1: return "Shoot Laser gun.";
            case 2: return "Shoot Missile gun.";
            case 3: return "Wait a turn";

            case 4: return "Shoot at Pilot, Gun L, or Gun M?";
            case 5: return "Shoot at Shield, or Engine?";
            case 6: return "Shoot at random?";

            case 7: return "Shoot at Pilot?";
            case 8: return "Shoot at Gun L?";
            case 9: return "Shoot at Gun M?";

            case 10: return "Shoot at Shield?";
            case 11: return "Shoot at Engine?";
            case 12: return "Shoot at Random?";

            case 100: return "Enter any number to continue";
        }
        //if value given is not found above
        return "Error";
    }

    //function which has the strings for the input windows
    private String getInputStrings(int choice) {
        //a base string to add your choice to
        String baseInput = "You chose option ";
        //the negative is for the introductory turn
        switch (choice) {
            case -1: return "type 1, 2, or 3, then enter. ";
            case 0: return "";
            case 1: return baseInput + "1.";
            case 2: return baseInput + "2.";
            case 3: return baseInput + "3.";
        }
        //if value given is not found above
        return "Error";
    }

    //function which gets the user input, checks for values 1 2 and 3
    private int getUserInput() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int input = scanner.nextInt();
        while(input < 1 || input > 3) {
            System.out.println("only enter 1 2 or 3.");
            input = scanner.nextInt();
        }
        return input;
    }

    //subs players health by given amount
    private void subtractPlayerHealth(int damage) {
        this.playerShipHealth -= damage;
    }

    //subs enemy's health by given amount
    private void subtractEnemyHealth(int damage) {
        this.enemyShipHealth -= damage;
    }

    //subs players shield by given amount
    private void subtractPlayerSheild(int damage) {
        this.playerShield -= damage;
    }

    //subs enemy's shield by given amount
    private void subtractEnemySheild(int damage) {
        this.enemyShield -= damage;
    }

    //adds players shield by given amount
    private void addPlayerSheild() {
        this.playerShield += 1;
    }

    //adds enemy's shield by given amount
    private void addEnemySheild() {
        this.enemyShield += 1;
    }

    //subs players missile count by one
    private void subtractPlayerMissile() {
        this.playerMissile -= 1;
    }

    //subs enemy's missile count by one
    private void subtractEnemyMissile() {
        this.enemyMissile -= 1;
    }


}