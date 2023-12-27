package com.winterhold.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "Loan") //nama asli table di database
@Entity //untuk memetakan entity/ table java dengan database, harus memasukan id
//lombok pengganti geter seter dan konstraktor
@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor
public class Loan {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CustomerNumber")
    private String customerNumber;

    @Column(name = "BookCode")
    private String bookCode;

    @Column(name = "LoanDate")
    private LocalDate loanDate;

    @Column(name = "DueDate")
    private LocalDate dueDate;

    @Column(name = "ReturnDate")
    private LocalDate returnDate;

    @Column(name = "Note")
    private String note;

}
