package com.winterhold.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "Book") //nama asli table di database
@Entity //untuk memetakan entity/ table java dengan database, harus memasukan id
//lombok pengganti geter seter dan konstraktor
@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "Code")
    private String code;

    @Column(name = "Title")
    private  String title;

    @Column(name = "CategoryName")
    private  String categoryName;

    @ManyToOne
    @JoinColumn(name = "CategoryName",insertable = false, updatable = false)
    private Category category;

    @Column(name = "AuthorId")
    private  Long authorId;

    @ManyToOne
    @JoinColumn(name = "AuthorId",insertable = false, updatable = false)
    private Author author;

    @Column(name = "IsBorrowed")
    private Boolean isBorrowed;

    @Column(name = "Summary")
    private String summary;

    @Column(name = "ReleaseDate")
    private LocalDate releaseDate;

    @Column(name = "TotalPage")
    private Long totalPage;

}
