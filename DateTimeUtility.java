package com.test.test.utility;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtility {

    public static final Integer DAYS_IN_WEEK = 7;

    public static String getFormatedDateTimeAsString(LocalDateTime dateTimeInput, String format) throws DateTimeException {
        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern(format);
        return dateTimeInput.format(formatterOutput);
    }

    public static LocalDateTime getFormattedStringAsLocalDateTime(String dateTimeInput, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateTimeInput, formatter);
    }

    public static Long getDateDiffInMonths(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            return 0L;
        }
        return ChronoUnit.MONTHS.between(startDate,endDate);
    }

    public static Integer getDateDiffInMonths(LocalDate startDate, LocalDate endDate) {
        int months = 1;
        if (startDate == null || endDate == null) {
            return 0;
        }
        Period diff = Period.between(startDate, endDate);
        if (diff.getMonths() > 0 && diff.getDays() > 0) {
            months = diff.getMonths() + 1;
        }
        return months;
    }

    public static Long getDateDiffInWeeks(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            return 0L;
        }
        return ChronoUnit.WEEKS.between(startDate,endDate);
    }

    public static BigDecimal getExactDateDiffInWeeks(LocalDateTime startDate, LocalDateTime endDate) {
        Long numberOfDays = getDateDiffInDays(startDate, endDate);
        if (numberOfDays.equals(0L)) {
            numberOfDays = 1L;
        }
        return (new BigDecimal(numberOfDays).divide(new BigDecimal(DAYS_IN_WEEK), BigDecimal.ROUND_UP));
    }


    public static Integer getDateDiffInDays(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        Period diff = Period.between(startDate, endDate);
        return diff.getDays();
    }

    public static Long getDateDiffInHours(LocalDateTime startDate, LocalDateTime endDate) {
        long hours = ChronoUnit.HOURS.between(startDate,endDate);
        return hours;
    }

    public static Long getDateDiffInDays(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            return 0L;
        }
        return ChronoUnit.DAYS.between(startDate,endDate);
    }

    public static Long getNumberOfDayBetweenTwoDatesBasedOnHours(LocalDateTime startDate, LocalDateTime endDate) {

        long days = getDateDiffInDays(startDate,endDate);
        long hours = ChronoUnit.HOURS.between(startDate,endDate);
        long extraHours = hours % 24;
        if(extraHours>0 && extraHours<24){
            days += 1;
        }
       return days;
    }

    public static boolean isEndDateGreaterThanStartDate(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate == null || endDate == null || startDate.isBefore(endDate);
    }

    public static boolean isEndDateGreaterThanStartDate(LocalDate startDate, LocalDate endDate ) {
        return startDate == null || endDate == null || startDate.isBefore(endDate);
    }

    public static Integer getTotalMinutesInTime(String time){
        String[] arrTime = time.split(":");
        Integer totalMinutes = Integer.parseInt(arrTime[0]) * 60 + Integer.parseInt(arrTime[1]) ;
        return totalMinutes;
    }

    /**
     * Returns current time for given timezone
     * @param timezone
     * @return ZonedDateTime
     */
    public static ZonedDateTime getZonedDateTime(String timezone){
        ZoneId zoneId = ZoneId.of(timezone);
        return ZonedDateTime.now(zoneId);
    }

    public static Integer getDayNumberByDateTime(LocalDateTime dateTime){
        return dateTime.getDayOfWeek().getValue();
    }

    public static LocalDateTime convertStringToDate(String date,DateTimeFormatter dateTimeFormatter){
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}
