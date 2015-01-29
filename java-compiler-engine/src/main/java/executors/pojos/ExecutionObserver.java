package executors.pojos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Kacper on 2014-11-11.
 */
public class ExecutionObserver  {

    private static boolean isAlive(Process p) {
        try {
            p.exitValue();
            return false;
        } catch (IllegalThreadStateException e) {
            return true;
        }
    }

    public static final boolean observe(Process p) {
        try {
            int count = 0;
            int sleeptime = 500;
            while (isAlive(p)) {
                count++;
                Thread.sleep(sleeptime);
                if (sleeptime * count > 10000) {
                    p.destroy();
                    return false;
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ExecutionObserver.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }

}
