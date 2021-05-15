package thread;

import javafx.application.Platform;
import ui.Main;
import ui.ResidentUnitGUI;

import javax.swing.*;

public class ProgressBarThread extends Thread {
    private Main main;
    
    public ProgressBarThread(Main main){
        setDaemon(true);
        this.main = main;

    }

    @Override
    public void run() {
        while (main.isStart()){

            try {
                Thread.sleep(10);
                if (main.getProgressBars().getProgress() < 1){
                    Platform.runLater(()->main.aumentarBarra());

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
