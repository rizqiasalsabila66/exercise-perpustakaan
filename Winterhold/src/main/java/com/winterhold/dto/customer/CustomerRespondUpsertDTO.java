package com.winterhold.dto.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor //konstraktor kosong
public class CustomerRespondUpsertDTO {

    private  String MembershipNumber;
    private  String firstName;
    private  String lastName;
    private  LocalDate birthDate;
    private  String gender;
    private  String phone;
    private  String address;
    private  LocalDate membershipExpireDate;
}
