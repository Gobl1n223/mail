package com.example.mail.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 *Сущность почтового отделения
 *Используються аннотации Lombok для лучшей читаемости кода
 */
@Entity
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "postal_office", schema = "mail", catalog = "")
public class PostalOffice {
    private Long id;
    private Integer index;
    private String name;
    private String adress;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "index", nullable = false)
    public Integer getIndex() {
        return index;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "adress", nullable = false, length = 255)
    public String getAdress() {
        return adress;
    }



}
