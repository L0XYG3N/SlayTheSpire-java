package m;
import UI.GUI;
import UI.GUI.ScreenState;

public class Main {
    public static void main(String[] args) {
        new GUI();
        GUI.changeScreen(ScreenState.MAIN);
    }
}