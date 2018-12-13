package joda;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;

public class TestJoda {

    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        printDateTime(dateTime);

        System.out.println(dateTime.toString("yyyy/MM/dd HH:mm:ss"));

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy/MM/dd");
        System.out.println(dateTimeFormatter.print(new DateTime()));

        String s = "2018)12)12 14$15";
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormat.forPattern("yyyy)MM)dd HH$mm");
        DateTime dateTime1 = dateTimeFormatter1.parseDateTime(s);
        printDateTime(dateTime1);

        dateTime1 = new DateTime();
        dateTime1 = dateTime1.withHourOfDay(15).withMinuteOfHour(20);
        printDateTime(dateTime1);

        println();

        dateTime1 = new DateTime();
        dateTime1 = dateTime1.plusHours(3).plusMinutes(50);
        printDateTime(dateTime1);

        println();

        dateTime1 = new DateTime();
        dateTime1 = dateTime1.withMillisOfDay(0);
        printDateTime(dateTime1);

        println();

        dateTime1 = new DateTime();
        dateTime1 = dateTime1.plusWeeks(1).withDayOfWeek(2).withMillisOfDay(0).withHourOfDay(10);
        printDateTime(dateTime1);

        println();

        dateTime1 = new DateTime();
        dateTime1 = dateTime1.plusDays(1).millisOfDay().withMaximumValue();
        printDateTime(dateTime1);

        println();

        dateTime1 = new DateTime();
        dateTime1 = dateTime1.dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue();
        printDateTime(dateTime1);

        println();

        dateTime1 = new DateTime();
        dateTime1 = dateTime1.withMonthOfYear(11).withDayOfMonth(15);
        printDateTime(dateTime1);
        println();

        dateTime1 = dateTime1.plusMonths(1).dayOfMonth().withMinimumValue().
                dayOfWeek().setCopy(DateTimeConstants.MONDAY).plusDays(7)
                .millisOfDay().withMinimumValue().withHourOfDay(17);
        printDateTime(dateTime1);

        println();

        DateTime dateTime2 = new DateTime();
        DateTime dateTime3 = new DateTime(2018,11,13,0,0,0);
        Period period = new Period(dateTime3,dateTime2);
        System.out.println(period.getMonths()+"月"+period.getDays()+"天"
                +period.getHours()+"小时"+period.getMinutes()+"分");



        dateTime1 = new DateTime(1992,8,16,0,0);
        int years = Years.yearsBetween(dateTime1, new DateTime()).getYears();
        System.out.println(years);

        println();

        Minutes minutes = Minutes.minutesBetween(new DateTime().millisOfDay().withMinimumValue().withHourOfDay(9), new DateTime());
        System.out.println(minutes.getMinutes());

        println();

        LocalDate localDate = new LocalDate(1992,8,16);
        System.out.println(Years.yearsBetween(localDate,LocalDate.now()).getYears());

        println();

        LocalTime localTime = new LocalTime(9,0,0);
        System.out.println(Minutes.minutesBetween(localTime,LocalTime.now()).getMinutes());

        println();

        LocalDate localDate1 = dateTime1.toLocalDate();
        LocalTime localTime1 = dateTime1.toLocalTime();

        printDateTime(DateTime.now().withDate(localDate1));

        println();

        DateTime dateTime4 = new DateTime(new Date());
        printDateTime(dateTime4);

        println();

        DateTime dateTime5 = new DateTime(Calendar.getInstance());
        printDateTime(dateTime5);

        LocalDate localDate2 = LocalDate.fromDateFields(new Date());
        LocalDate localDate3 = LocalDate.fromCalendarFields(Calendar.getInstance());

        printDateTime(DateTime.now().withDate(localDate2));
        println();

    }

    private static void println(){
        System.out.println("==========================");
    }

    private static void printDateTime(DateTime dateTime){
        System.out.println("year: "+dateTime.getYear());
        System.out.println("month: "+dateTime.getMonthOfYear());
        System.out.println("day: "+dateTime.getDayOfMonth());
        System.out.println("hour: "+dateTime.getHourOfDay());
        System.out.println("minute: "+dateTime.getMinuteOfHour());
        System.out.println("second: "+dateTime.getSecondOfMinute());
        System.out.println("millisecond: " +dateTime.getMillisOfSecond());
        System.out.println("day_of_week: " +dateTime.getDayOfWeek());
    }
}
