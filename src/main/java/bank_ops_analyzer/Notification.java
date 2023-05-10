package bank_ops_analyzer;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// pattern Notification - for error collecting
public class Notification {
    @Getter
    private final List<String> errors = new ArrayList<>();

    public void addError(final String message){
        errors.add(message);
    }

    public boolean hasErrors(){
        return errors.isEmpty();
    }

    public String errorMessage(){
        return errors.toString();
    }
}
