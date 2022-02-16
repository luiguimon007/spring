package com.luigui.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name ="user")
@EqualsAndHashCode
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    /*@OneToOne
    @JoinColumn(name="profile_id",referencedColumnName = "id")
    private Profile profile;*/

    private static final long serialVersionUID = 1L;

}
