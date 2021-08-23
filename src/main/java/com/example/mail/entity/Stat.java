package com.example.mail.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Stat {
    private Long id;
    private Long idMail;
    private Integer indexOffice;
    private Integer status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "id_mail")
    public Long getIdMail() {
        return idMail;
    }


    @Basic
    @Column(name = "index_office")
    public Integer getIndexOffice() {
        return indexOffice;
    }


    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return this.status;
    }

}
