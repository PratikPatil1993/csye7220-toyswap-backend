package com.backend.toyswap.model;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

/**
 *  Class to create Toy objects, with hibernate notations so it will be converted into a table.
 *  includes getters and setters
 **/

@Entity
@Table(name = "toy")
public class Toy {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "toyName")
    private String toyName;

    @Column(name = "toyPrice")
    private int toyPrice;

    @Column(name = "toyPhoto")
    private String toyPhoto;

    @ManyToOne()
    private User user;
    
    @Transient
    private MultipartFile toyImage;

    public MultipartFile getToyImage() {
		return toyImage;
	}

	public void setToyImage(MultipartFile toyImage) {
		this.toyImage = toyImage;
	}

	public Toy() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Toy(String toyName, int toyPrice, String toyPhoto, User user, MultipartFile toyImage) {
        this.toyName = toyName;
        this.toyPrice = toyPrice;
        this.user = user;
        this.toyPhoto = toyPhoto;
        this.toyImage = toyImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToy_Name() {
        return toyName;
    }

    public void setToy_Name(String toyName) {
        this.toyName = toyName;
    }

    public int getToy_Price() {
        return toyPrice;
    }

    public void setToy_Price(int toyPrice) {
        this.toyPrice = toyPrice;
    }

    public String getToy_Photo() {
        return toyPhoto;
    }

    public void setToy_Photo(String toyPhoto) {
        this.toyPhoto = toyPhoto;
    }
}


