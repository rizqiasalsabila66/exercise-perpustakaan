package com.winterhold.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "Customer") //nama asli table di database
@Entity //untuk memetakan entity/ table java dengan database, harus memasukan id
//lombok pengganti geter seter dan konstraktor
@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name = "MembershipNumber")
    private String membershipNumber;

    @Column(name = "FirstName")
    private  String firstName;

    @Column(name = "LastName")
    private  String lastName;

    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @Column(name = "Gender")
    private  String gender;

    @Column(name = "Phone")
    private  String phone;

    @Column(name = "Address")
    private String address;

    @Column(name = "MembershipExpireDate")
    private LocalDate membershipExpireDate;
}
