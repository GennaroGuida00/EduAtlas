package EduAtlas.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "degrees")
@Getter
@Setter
@NoArgsConstructor
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long degree_id;
    private String name;
    private int duration_years;
    private int eqf_level;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Degree(int duration_years, String name, int eqf_level, Country country) {
        this.duration_years = duration_years;
        this.name = name;
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

    public int getDuration_years() {
        return duration_years;
    }

    public void setDuration_years(int duration_years) {
        this.duration_years = duration_years;
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
