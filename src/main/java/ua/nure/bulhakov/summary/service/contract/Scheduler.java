package ua.nure.bulhakov.summary.service.contract;

import org.apache.log4j.Logger;
import ua.nure.bulhakov.summary.database.DBException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class that is used to update database daily
 * @author A.Bulhakov
 */
public class Scheduler {

    private static final Logger logger = Logger.getLogger(Scheduler.class);

    private static Scheduler instance;

    private Scheduler(){

    }

    public static Scheduler getInstance(){
        if(instance == null){
            instance = new Scheduler();
        }
        return instance;
    }

    /**
     * Creates daily task to update Contracts and client's accounts
     */
    public void start() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    new ContractUpdater().updateAll();
                } catch (DBException e) {
                    logger.error("Can't update contracts", e);
                }
                timer.schedule(this, getTomorrowDate());
            }
        };
        timer.schedule(task, getTomorrowDate());
    }

    private Date getTomorrowDate(){
        LocalDate date = LocalDate.now().plusDays(1);
        return Date.from(date.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
