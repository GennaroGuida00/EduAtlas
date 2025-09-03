package EduAtlas.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
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
}
