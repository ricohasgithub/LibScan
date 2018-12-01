
public class Date {

    private int year;
    private int month;
    private int day;

    public Date (int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date (String dateString) {
        // Date string comes in the format: MM/DD/YYYY
        month = Integer.parseInt(dateString(0,2));
        day = Integer.parseInt(dateString(3,5));
        year = Integer.parseInt(dateString(6,10));
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

}