package me.dlands.endworldreset.settings;

import me.dlands.endworldreset.Endworldreset;
import org.bukkit.configuration.file.FileConfiguration;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;

public class Settings {

    private FileConfiguration config;
    private int many;
    private EveryType type;
    private Calendar nextReset;
    private Calendar futureReset;

    public Settings(FileConfiguration config){
        this.config = config;
        setup();
    }

    void setup(){
        StringBuilder everyData = new StringBuilder((String) config.get("Config.every"));
        String nextReset = (String) config.get("Save.nextReset");
        switch (everyData.charAt(everyData.length()-1)){
            case 'd' : type = EveryType.DAYS; break;
            case 'w' : type = EveryType.WEEK; break;
            case 'm' : type = EveryType.MONTH; break;
        }
        many = Integer.parseInt(everyData.deleteCharAt(everyData.length()-1).toString());

        if(nextReset != null){
            this.nextReset = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            try {
                this.nextReset.setTime(sdf.parse((String) config.get("Save.nextReset") + " " + (String) config.get("Config.time")));
            } catch (ParseException e) {
                e.printStackTrace();
            };
            futureReset = (Calendar) this.nextReset.clone();
            futureReset.add(type.getCalendarType(), many);
        }
    }

    void set(){
        this.nextReset = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse((String) config.get("Config.time")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.nextReset.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        this.nextReset.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
        this.nextReset.add(type.getCalendarType(), many);
    }

    public EveryType getType() {
        return type;
    }
    public Calendar getNextReset() {
        return nextReset;
    }
    public int getMany() {
        return many;
    }

    enum EveryType {
        DAYS("Day(s)", Calendar.DATE),
        WEEK("Week(s)", Calendar.WEEK_OF_YEAR),
        MONTH("Month(s)", Calendar.MONTH);

        private String type;
        private int CalendarType;
        EveryType(String type, int CalendarType){
            this.type = type;
            this.CalendarType = CalendarType;
        }

        public String toString(){
            return this.type;
        }

        public int getCalendarType() {
            return CalendarType;
        }
    }

    public void print(){
        if(nextReset != null){
            SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Endworldreset.log.log(Level.INFO, "=============Setting===========");
            Endworldreset.log.log(Level.INFO, " every : " + many + " " + type.toString());
            Endworldreset.log.log(Level.INFO, " time  : " + config.get("Config.time"));
            Endworldreset.log.log(Level.INFO, "===========Reset World=========");
            Endworldreset.log.log(Level.INFO, " next    : " + formater.format(nextReset.getTime()));
            Endworldreset.log.log(Level.INFO, " future  : " + formater.format(futureReset.getTime()));
            Endworldreset.log.log(Level.INFO, "===============================");
        } else {
            SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            Endworldreset.log.log(Level.INFO, "=============Setting===========");
            Endworldreset.log.log(Level.INFO, " every : " + many + " " + type.toString());
            Endworldreset.log.log(Level.INFO, " time  : " + config.get("Config.time"));
            Endworldreset.log.log(Level.INFO, "===========Reset World=========");
            Endworldreset.log.log(Level.INFO, " not set yet!");
            Endworldreset.log.log(Level.INFO, "===============================");
        }
    }
}