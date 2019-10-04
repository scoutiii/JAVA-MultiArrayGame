//subclass of a window which is dedicated to the players ship
public class MyShipWindow extends Window {
    //creates a player ship object which is the visual element of the screen
    private PlayersShip playersShip = new PlayersShip();
    //the double char array, the screen
    private char[][] mapArray;

    //sets the width and height of the window and the double char array
    public MyShipWindow(int width, int height, char[][] mapArray) {
        super(width, height);
        this.mapArray = mapArray;
    }

    //takes the start x and y of the window, goes through and draws the ship found in PlayerShip object
    public void drawMyShipWindow(int startX, int startY) {
        for(int y = 0; y < this.getHeight(); ++y) {
            for(int x = 0; x < this.getWidth(); ++x) {
                this.mapArray[startY + y][startX + x] = playersShip.playerShip[y][x];
            }
        }
    }
}

