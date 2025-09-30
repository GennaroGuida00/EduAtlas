package EduAtlas.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "countries")
@Getter
@Setter

public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long country_id;
    private String name;
    private int years_compulsary_schooling;
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Degree> degrees = new ArrayList<>();
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<GradeScale> gradeScales = new ArrayList<>();

    public Country(String name, int years_compulsary_schooling) {
        this.name = name;
        this.years_compulsary_schooling = years_compulsary_schooling;
    }

    public Country() {

    }


    public long getCountry_id() {
        return country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYears_compulsary_schooling() {
        return years_compulsary_schooling;
    }

    public void setYears_compulsary_schooling(int years_compulsary_schooling) {
        this.years_compulsary_schooling = years_compulsary_schooling;
    }
}
