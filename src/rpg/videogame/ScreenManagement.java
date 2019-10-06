package rpg.videogame;

import java.io.IOException;

public class ScreenManagement {

    public void clearScreen() {
        System.out.println(new String(new char[100]).replace("\0", "\r\n"));
    }

    public void waitForInput() {
        try {
            System.in.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
