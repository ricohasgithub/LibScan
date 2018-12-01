
public class Book {

    public String ISBN;
    public String bar;

    // Due date
    public Date due;
    // Checkout date
    public Date check;

    public Book (String ISBN, String bar) {
        this.ISBN = ISBN;
        this.bar = bar;
    }

    public Book (String ISBN) {
        this.ISBN = ISBN;
        bar = "NO BAR CODE";
    }

    public Book (String bar) {
        ISBN = "NO ISBN";
        this.bar = bar;
    }

    public void setDueDate (Date due) {
        this.due = due;
    }

    public void setCheckDate (Date check) {
        this.check = check;
    }

    public String getDueString () {

        if (due == null || check == null) {
            throw new IllegalArgumentException("Date not initialized");
        }

        return "Years: " + due.getYearDiff(check) + "\t Months: " + due.getMonthDiff(check) + "\t Days: " + due.getDayDiff(check);

    }

    public boolean isDue () {
        return !(due.isLarger(check));
    }

}