package util;

import java.util.Timer;
import java.util.TimerTask;

public class TimerUtil {

  private static final Timer timer = new Timer();

  public static void addTask(final Task task, long period) {
    timer.schedule(new TimerTask() {

      @Override
      public void run() {
        task.run();
      }

    }, period, period);
  }

}
