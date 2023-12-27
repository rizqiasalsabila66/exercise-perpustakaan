package com.winterhold.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "Author") //nama asli table di database
@Entity //untuk memetakan entity/ table java dengan database, harus memasukan id
//lombok pengganti geter seter dan konstraktor
@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor
public class Author {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //untuk mengasih tau bahwa id di generate secara auto
    private Long id;

    @Column(name = "Title")
    private  String title;

    @Column(name = "FirstName")
    private  String firstName;

    @Column(name = "LastName")
    private  String lastName;

    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @Column(name = "DeceasedDate")
    private LocalDate deceasedDate;

    @Column(name = "Education")
    private String education;

    @Column(name = "Summary")
    private String summary;

}
