package EduAtlas.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gradeScales")
@Getter
@Setter
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

    public GradeScale() {

    }

    public long getGrade_scale_id() {
        return grade_scale_id;
    }

    public int getMin_value() {
        return min_value;
    }

    public void setMin_value(int min_value) {
        this.min_value = min_value;
    }

    public int getMax_value() {
        return max_value;
    }

    public void setMax_value(int max_value) {
        this.max_value = max_value;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
