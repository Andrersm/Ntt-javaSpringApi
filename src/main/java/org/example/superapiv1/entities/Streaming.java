package org.example.superapiv1.entities;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name= "tb_streaming")
public class Streaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "streaming")
    private List<Movie> catalog;

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

    public List<Movie> getCatalog() {
        return catalog;
    }

    public void setCatalog(List<Movie> catalog) {
        this.catalog = catalog;
    }
}
