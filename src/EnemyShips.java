//this is the visual representation of the enemy ship
public class EnemyShips {
    //double char array to keep track of the enemy ship
    public char[][] enemyShip = new char[12][64];
    //the width and height of the above array
    private int arrayHeight = 12;
    private int arrayLength = 64;

    //default constructor which makes the double char array to the ship down below
    public EnemyShips() {
        //creates an array of strings
        String[] enemyShipStrings = new String[this.arrayHeight];

        //goes through the array of strings and sets it the ship below, string for convenience of making the ship by hand
        enemyShipStrings[0]   = "                                                                ";
        enemyShipStrings[1]   = "                 _________                _____                 ";
        enemyShipStrings[2]   = "              --/* GunL  *\\              /*   *\\                ";
        enemyShipStrings[3]   = "             --/   2]      \\____________/_      \\    ____       ";
        enemyShipStrings[4]   = "              /____________|      |Shield |    __\\  /*  ||\\>    ";
        enemyShipStrings[5]   = "             /Pilot|              |       |   |   \\/ 5] ||/>>   ";
        enemyShipStrings[6]   = "            <  1]  | I.P.P.Valor  |   4]  |   | Engine  ||>>>>  ";
        enemyShipStrings[7]   = "             \\_____|_______       |       |   |__ /\\    ||\\>>   ";
        enemyShipStrings[8]   = "            \\-\\            |______|_______|      /  \\*__||/>    ";
        enemyShipStrings[9]   = "             \\-\\   3]      /           \\        /               ";
        enemyShipStrings[10]  = "                \\*_GunM__*/             \\*____*/                ";
        enemyShipStrings[11]  = "                                                                ";

        //goes through and converts the string array to char arrays, then sets that to the final double char array
        for(int y = 0; y < this.arrayHeight; ++y) {
            char[] stringToChar = enemyShipStrings[y].toCharArray();
            for(int x = 0; x < this.arrayLength; ++x) {
                this.enemyShip[y][x] = stringToChar[x];
            }
        }
    }

    public void damageShip(int x, int y) {

    }

    private void breakPilot() {
        for(int y = 0; y<5; ++y) {

        }
    }

    //not particularly useful function which prints out the ship
    public void printShip() {
        for(int y = 0; y < this.arrayHeight; ++y) {
            for(int x = 0; x < this.arrayLength; ++x) {
                System.out.print(this.enemyShip[y][x]);
            }
            System.out.println();
        }
    }

    //just returns the array height
    public int getArrayHeight() {
        return this.arrayHeight;
    }

    //returns the array length
    public int getArrayLength() {
        return this.arrayLength;
    }
}