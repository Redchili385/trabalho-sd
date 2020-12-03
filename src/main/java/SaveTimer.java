import java.util.Timer;
import java.util.TimerTask;

public class SaveTimer extends Timer {

    public SaveTimer(String name, TimerTask task, long firstTime, long period){
        super(name);
        this.scheduleAtFixedRate(task, firstTime, period);
    }
}
