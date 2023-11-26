package domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dob;
}