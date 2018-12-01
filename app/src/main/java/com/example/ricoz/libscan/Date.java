package com.example.ricoz.libscan;

public class Date {

    public int year;
    public int month;
    public int day;

    public Date (int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date (String dateString) {
        // Date string comes in the format: MM/DD/YYYY
        month = Integer.parseInt(dateString.substring(0,2));
        day = Integer.parseInt(dateString.substring(3,5));
        year = Integer.parseInt(dateString.substring(6,10));
    }

    public Date () {
        year = 0;
        month = 0;
        day = 0;
    }

    public boolean isLarger (Date d) {
        // This method returns false if the current date is larger than the date d
        if (year < d.year) {
            return false;
        } else if (year == d.year) {
            if (month < d.month) {
                return false;
            } else if (month == d.month) {
                if (day < d.day) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getYearDiff (Date d) {
        int yearDiff = year - d.year;
        if (yearDiff < 0) {
            return 0;
        }
        return yearDiff;
    }

    public int getMonthDiff (Date d) {
        int monthDiff = month - d.month;
        if (monthDiff < 0) {
            return 0;
        }
        return monthDiff;
    }

    public int getDayDiff (Date d) {
        int dayDiff = day - d.day;
        if (dayDiff < 0) {
            return 0;
        }
        return dayDiff;
    }

}