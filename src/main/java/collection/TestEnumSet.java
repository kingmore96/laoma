package collection;

import java.util.*;

public class TestEnumSet {

    private static Worker[] workers = new Worker[]{
            new Worker("张三", EnumSet.of(
                    Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.FRIDAY)),
            new Worker("李四", EnumSet.of(
                    Day.TUESDAY, Day.THURSDAY, Day.SATURDAY)),
            new Worker("王五", EnumSet.of(
                    Day.TUESDAY, Day.THURSDAY)),
    };

    //哪些天一个人都不会来？
    public static void question1(Worker[] workers){
        Set<Day> days = EnumSet.allOf(Day.class);

        for (Worker worker : workers) {
            days.removeAll(worker.getAvailableDays());
        }
        System.out.println(days);
    }

    //有哪些天至少会有一个人来？
    public static void question2(Worker[] workers){
        Set<Day> days = EnumSet.noneOf(Day.class);
        for (Worker worker : workers) {
            days.addAll(worker.getAvailableDays());
        }
        System.out.println(days);
    }

    //有哪些天所有人都会来？
    public static void question3(Worker[] workers){
        Set<Day> days = EnumSet.allOf(Day.class);
        for (Worker worker : workers) {
            days.retainAll(worker.getAvailableDays());
        }

        System.out.println(days);
    }

    //哪些人周一和周二都会来？
    public static void question4(Worker[] workers){
        Set<Worker> workerSet = new HashSet<>();
        for (Worker worker : workers) {
            if(worker.getAvailableDays().containsAll(EnumSet.of(Day.MONDAY,Day.TUESDAY))){
                workerSet.add(worker);
            }
        }

        for (Worker worker : workerSet) {
            System.out.println(worker.getName());
        }
    }

    //哪些天至少会有两个人来？
    public static void question5(Worker[] workers){
        EnumMap<Day,Integer> enumMap = new EnumMap<>(Day.class);
        for (Worker worker : workers) {
            for (Day day : worker.getAvailableDays()) {
                Integer count = enumMap.get(day);
                if(count == null){
                    enumMap.put(day,1);
                }else{
                    enumMap.put(day,count+1);
                }
            }
        }

        Set<Day> days = EnumSet.noneOf(Day.class);
        for (Map.Entry<Day, Integer> dayIntegerEntry : enumMap.entrySet()) {
            if(dayIntegerEntry.getValue() >= 2){
                days.add(dayIntegerEntry.getKey());
            }
        }
        System.out.println(days);

    }

    public static void main(String[] args) {
        question1(workers);
        question2(workers);
        question3(workers);
        question4(workers);
        question5(workers);
    }
}
