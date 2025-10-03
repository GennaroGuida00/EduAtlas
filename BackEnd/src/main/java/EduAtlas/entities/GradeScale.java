package EduAtlas.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gradeScales")
@Getter
@Setter
public class GradeScale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long grade_scale_id;
    private String grade_value;
    private double min_value;
    private double max_value;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public GradeScale(String grade_value, double min_value, double max_value, Country country) {
        this.grade_value = grade_value;
        this.min_value = min_value;
        this.max_value = max_value;
        this.country = country;
    }

    public GradeScale() {

    }

    public long getGrade_scale_id() {
        return grade_scale_id;
    }

    public double getMin_value() {
        return min_value;
    }

    public void setMin_value(double min_value) {
        this.min_value = min_value;
    }

    public double getMax_value() {
        return max_value;
    }

    public void setMax_value(double max_value) {
        this.max_value = max_value;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getGrade_value() {
        return grade_value;
    }

    public void setGrade_value(String grade_value) {
        this.grade_value = grade_value;
    }
}
