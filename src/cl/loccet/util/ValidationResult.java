package cl.loccet.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ValidationResult {

    private List<String> messages;

    private Boolean valid;

    public ValidationResult() {
        valid = false;
    }

    public boolean addMessage(String message) {
        if (messages == null)
            messages = new ArrayList<>();
        return messages.add(message);
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getMessages() {
        if (messages == null)
            return null;

        return Collections.unmodifiableList(messages);
    }
}
