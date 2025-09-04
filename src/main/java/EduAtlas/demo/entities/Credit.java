package EduAtlas.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "credits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int credit_id;
    private int credit_value;
    private int year;
    @ManyToOne
    @JoinColumn(name = "degree_id")
    private Degree degree;

    public Credit(int credit_value, int year, Degree degree) {
        this.credit_value = credit_value;
        this.year = year;
        this.degree = degree;
    }


    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public int getCredit_id() {
        return credit_id;
    }

    public int getCredit_value() {
        return credit_value;
    }

    public void setCredit_value(int credit_value) {
        this.credit_value = credit_value;
    }
}
