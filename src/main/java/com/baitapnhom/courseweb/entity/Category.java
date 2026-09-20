package com.baitapnhom.courseweb.entity;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, unique = true )
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Course> coursers;

    public Category() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCoursers() {
        return coursers;
    }

    public void setCoursers(List<Course> coursers) {
        this.coursers = coursers;
    }
}
