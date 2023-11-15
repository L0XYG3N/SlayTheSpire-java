
import UI.GUI;
import UI.GUI.ScreenState;

public class Main {
    public static void main(String[] args) {
        //TempUI t = new TempUI();
        new GUI();
        GUI.changeScreen(ScreenState.MAIN);

    }
}