package thread;

import javafx.application.Platform;
import ui.ResidentUnitGUI;

public class LocalDateThread extends Thread  {
    ResidentUnitGUI ur;

    public LocalDateThread(ResidentUnitGUI ur){
        setDaemon(true);
        this.ur = ur;

    }

    @Override
    public void run() {
       while (true){
           Platform.runLater(new Thread() {
               public void run(){
                   ur.updateDay();
               }
           });
           try {
               Thread.sleep(100);

           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    }
}
