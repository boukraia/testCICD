package projetarchitecture.projetarchitecture.util;

import projetarchitecture.projetarchitecture.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientPhoneValidator implements ClientValidator {
    @Override
    public void validate(Client client) {
        String phoneNumber = client.getPhoneNumber();

        // Check if the phone number is exactly 10 digits long
        if (phoneNumber == null || phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits");
        }

        // Check if the phone number contains only digits
        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Phone number must contain only numeric characters");
        }
    }
}
