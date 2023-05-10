package bank_ops_analyzer;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@AllArgsConstructor
public class Validator {
    private String description;
    private String dt;
    private String amount;

    public Notification validate(){
        final Notification notification = new Notification();

        if(this.description.length() > 100)
            notification.addError("The description is too long");

        final LocalDate parsedDt;
        try{
            parsedDt = LocalDate.parse(this.dt);
            if(parsedDt.isAfter(LocalDate.now()))
                notification.addError("Date cannot be in the future");
        }catch (DateTimeParseException e){
            notification.addError("Invalid format for date");
        }

        final double amount;
        try{
            amount = Double.parseDouble(this.amount);
        }catch (NumberFormatException e){
            notification.addError("Invalid format for amount");
        }

        return notification;
    }
}
