package com.realestate.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "property")
public class Property {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String city;
    private String type;
    private Double price;
    private String imageUrl;
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    // Getters & Setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
    public String getCity(){return city;} public void setCity(String city){this.city=city;}
    public String getType(){return type;} public void setType(String type){this.type=type;}
    public Double getPrice(){return price;} public void setPrice(double price){this.price=price;}
    public void setPrice(Double price){this.price=price;}
    public String getImageUrl(){return imageUrl;} public void setImageUrl(String imageUrl){this.imageUrl=imageUrl;}
    public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
    public User getOwner(){return owner;} public void setOwner(User owner){this.owner=owner;}

    // For compatibility if you used ownerId Long before
    public Long getOwnerId(){ return owner!=null ? owner.getId() : null; }
}