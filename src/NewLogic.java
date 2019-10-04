public class NewLogic {

    private Screen display;

    private int playerHP = 10;
    private int enemyHP  = 10;



    NewLogic() {
        display = createScreen();

    }

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
}