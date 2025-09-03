package EduAtlas.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gradeScales")
@Getter
@Setter
@NoArgsConstructor
public class GradeScale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long grade_scale_id;
    private int min_value;
    private int max_value;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public GradeScale(int min_value, int max_value, Country country) {
        this.min_value = min_value;
        this.max_value = max_value;
        this.country = country;
    }
}
