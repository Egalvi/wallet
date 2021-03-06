package ru.egalvi.wallet.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity(name = "category")
public class Category implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Category> subCategories;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Purchase> purchases;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Collection<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public Collection<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Collection<Purchase> purchases) {
        this.purchases = purchases;
    }
}
