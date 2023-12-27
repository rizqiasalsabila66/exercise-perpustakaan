package com.winterhold.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "Category") //nama asli table di database
@Entity //untuk memetakan entity/ table java dengan database, harus memasukan id
//lombok pengganti geter seter dan konstraktor
@Getter
@Setter //getter setter semua properti di class ini
@AllArgsConstructor //konstraktor semua atribut
@NoArgsConstructor
public class Category {

    @Id
    @Column(name = "Name")
    private String name;

    @Column(name = "Floor")
    private  Integer floor;

    @Column(name = "Isle")
    private  String isle;

    @Column(name = "Bay")
    private  String bay;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
//    EAGER=SEMANGAT, category ke nama field di book yang ada many to one nya
    private List<Book> books;
}
