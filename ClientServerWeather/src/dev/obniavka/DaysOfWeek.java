package dev.obniavka;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class DaysOfWeek {
    public static String ukrainianDay(String day) {
        HashMap<String, String> week = new HashMap<>();
        week.put("MONDAY", "Понеділок");
        week.put("TUESDAY", "Вівторок");
        week.put("WEDNESDAY", "Середа");
        week.put("THURSDAY", "Четвер");
        week.put("FRIDAY", "П'ятниця");
        week.put("SATURDAY", "Субота");
        week.put("SUNDAY", "Неділя");

        return week.get(day);   }

        public static String setDays(int day){
            ArrayList<String> days = new ArrayList<String>();
            LocalDate localDate = LocalDate.now();
            days.add(DaysOfWeek.ukrainianDay((localDate.getDayOfWeek()).toString()));
            for (int i = 1; i < 7; i++) {
                days.add(DaysOfWeek.ukrainianDay((localDate.getDayOfWeek()).plus(i).toString()));
            }
            return days.get(day);
        }

}
