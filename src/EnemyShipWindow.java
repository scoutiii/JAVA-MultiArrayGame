//subclass of window dedicated to the enemy ship screen
public class EnemyShipWindow extends Window {
    //creates an enemy ship object for the visual element
    private EnemyShips enemyShips = new EnemyShips();
    //the double char array which is the screen
    private char[][] mapArray;

    //default constructor which sets the width, height, and double char array
    public EnemyShipWindow(int width, int height, char[][] mapArray) {
        super(width, height);
        this.mapArray = mapArray;
    }

    //goes through the enemy ships array and puts it on the screen
    public void drawEnemyShipWindow(int startX, int startY) {
        for(int y = 0; y < this.getHeight(); ++y) {
            for(int x = 0; x < this.getWidth(); ++x) {
                this.mapArray[startY + y][startX + x] = enemyShips.enemyShip[y][x];
            }
        }
    }


}