package project;

import java.time.LocalDate;

public class DateWrapper {

  private LocalDate date;

  public DateWrapper(String date) {
    super();
    this.date = LocalDate.parse(date);
  }

  public DateWrapper(LocalDate date) {
    super();
    this.date = date;
  }

  public LocalDate getDate() {
    return date;
  }

  public void addMonth() {
    date = date.plusMonths(1);
  }
  
  public LocalDate addMonths(int x) {
	  return date = date.plusMonths(x);
  }

}
