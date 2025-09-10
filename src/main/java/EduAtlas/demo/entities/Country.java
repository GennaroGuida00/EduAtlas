package EduAtlas.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
