public class Window {
    //variables to hold the width and height values
    private int width;
    private int height;
    //the double character array which is the screen to be edited
    public char[][] mapArray;

    //default constructor which sets the width and height of the window
    public Window(int width, int height) {
        this.width = width;
        this.height = height;
    }

    //returns the double char array
    public char[][] getMapArray() {
        return this.mapArray;
    }

    //returns the width of the window
    public int getWidth() {
        return this.width;
    }

    //returns the height of the window
    public int getHeight() {
        return this.height;
    }
}