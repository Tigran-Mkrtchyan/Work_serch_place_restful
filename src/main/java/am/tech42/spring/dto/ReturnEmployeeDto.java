package am.tech42.spring.dto;

import lombok.Data;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;


@Data
public class ReturnEmployeeDto {

    private String firstName;
    private String lastName;
    private String birthday;
    private String address;
    private String phoneNumber;
    private int age;

    public void evaluateAgeFromBirthday(Date birthday) {
        java.util.Date date = new java.util.Date(birthday.getTime());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        this.age = currentYear - calendar.get(Calendar.YEAR);
    }
}
