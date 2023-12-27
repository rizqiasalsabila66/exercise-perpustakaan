package com.winterhold.dto.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class UpsertCustomerDTO {

    @NotBlank(message = "Wajib diisi")
    @Size(max = 20, message = "Maksimal 20 char")
    private String MembershipNumber;

    @NotBlank(message = "Wajib diisi")
    @Size(max = 50, message = "Maksimal 50 char")
    private  String firstName;

    @Size(max = 50, message = "Maksimal 50 char")
    private  String lastName;

    @NotNull(message = "Wajib diisi")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank(message = "Wajib diisi")
    private  String gender;

    @Size(max = 20, message = "Maksimal 20 char")
    private  String phone;

    @Size(max = 500, message = "Maksimal 500 char")
    private  String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate membershipExpireDate;

}
