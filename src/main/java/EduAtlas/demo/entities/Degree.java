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
}
