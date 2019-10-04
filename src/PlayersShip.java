//is the visual representation of the players ship
public class PlayersShip {
    //double char array which keeps track of the ships characters
    public char[][] playerShip = new char[12][64];
    //just is the height and width of the above array
    private int arrayHeight = 12;
    private int arrayLength = 64;

    //default constructor which creates the player ship
    public PlayersShip() {
        //array of strings to make it easier to create the ship by hand
        String[] youShipStrings = new String[this.arrayHeight];

        //sets the array of strings to the ship below
        youShipStrings[0]   = "                                  _____                         ";
        youShipStrings[1]   = "      __                         /*   *\\---                     ";
        youShipStrings[2]   = "   </|* \\      _________________/  2]  |\\---                    ";
        youShipStrings[3]   = "   <\\||  \\    /  |              |      | \\---                   ";
        youShipStrings[4]   = "   </||  |\\__/ 5]|              |_GunL_|  \\_______              ";
        youShipStrings[5]   = "   <\\||  |Engine |   I.S.C.Pelican          /Pilot\\             ";
        youShipStrings[6]   = "   </||--|-------|-------------------------|  1]   >            ";
        youShipStrings[7]   = "   <\\||  |Shield |               ______    _\\_____/             ";
        youShipStrings[8]   = "   </||  /    \\4]|              | GunM |  /                     ";
        youShipStrings[9]   = "   <\\|| /      \\_|_____________ |      | /---                   ";
        youShipStrings[10]  = "     |*/                       \\   3]  |/---                    ";
        youShipStrings[11]  = "                                \\_*___*/---                     ";

        //goes through and converts the the string to a char array, then sets the final char array
        for(int y  =0; y < this.arrayHeight; ++y) {
            char[] stringToChar = youShipStrings[y].toCharArray();
            for(int x = 0; x < this.arrayLength; ++x) {
                this.playerShip[y][x] = stringToChar[x];
            }
        }
    }

    //not real useful function which just prints out the ship
    public void printShip() {
        for(int y = 0; y < this.arrayHeight; ++y) {
            for(int x = 0; x < this.arrayLength; ++x) {
                System.out.print(this.playerShip[y][x]);
            }
            System.out.println();
        }
    }

    //not real useful function which gives the height of the ship
    public int getArrayHeight() {
        return this.arrayHeight;
    }

    //not real useful function which gives the length/width of the array
    public int getArrayLength() {
        return this.arrayLength;
    }
}