package EduAtlas.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "degrees")
@Getter
@Setter

public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long degree_id;
    private String name;
    private int min_years;
    private int additional_years;
    private int eqf_level;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Degree(String name, int min_years, int additional_years, int eqf_level, Country country) {
        this.name = name;
        this.min_years = min_years;
        this.additional_years = additional_years;
        this.eqf_level = eqf_level;
        this.country = country;
    }

    public Degree() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMin_years() {
        return min_years;
    }

    public void setMin_years(int min_years) {
        this.min_years = min_years;
    }

    public int getAdditional_years() {
        return additional_years;
    }

    public void setAdditional_years(int additional_years) {
        this.additional_years = additional_years;
    }

    public int getEqf_level() {
        return eqf_level;
    }

    public void setEqf_level(int eqf_level) {
        this.eqf_level = eqf_level;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public long getDegree_id() {
        return degree_id;
    }

}
