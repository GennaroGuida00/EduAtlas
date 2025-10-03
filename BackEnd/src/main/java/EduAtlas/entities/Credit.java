package EduAtlas.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "credits")
@Getter
@Setter

public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int credit_id;
    private int year_1_credits;
    private int year_2_credits;
    private int year_3_credits;
    private boolean year_4_is_additional;
    private Integer year_4_credits;
    private boolean year_5_is_additional;
    private Integer year_5_credits;
    @ManyToOne
    @JoinColumn(name = "degree_id")
    private Degree degree;

    public Credit(int year_1_credits, int year_2_credits, int year_3_credits, boolean year_4_is_additional, Integer year_4_credits, boolean year_5_is_additional, Integer year_5_credits, Degree degree) {
        this.year_1_credits = year_1_credits;
        this.year_2_credits = year_2_credits;
        this.year_3_credits = year_3_credits;
        this.year_4_is_additional = year_4_is_additional;
        this.year_4_credits = year_4_credits;
        this.year_5_is_additional = year_5_is_additional;
        this.year_5_credits = year_5_credits;
        this.degree = degree;
    }

    public Credit() {

    }

    public int getCredit_id() {
        return credit_id;
    }

    public int getYear_1_credits() {
        return year_1_credits;
    }

    public void setYear_1_credits(int year_1_credits) {
        this.year_1_credits = year_1_credits;
    }

    public int getYear_2_credits() {
        return year_2_credits;
    }

    public void setYear_2_credits(int year_2_credits) {
        this.year_2_credits = year_2_credits;
    }

    public int getYear_3_credits() {
        return year_3_credits;
    }

    public void setYear_3_credits(int year_3_credits) {
        this.year_3_credits = year_3_credits;
    }

    public boolean isYear_4_is_additional() {
        return year_4_is_additional;
    }

    public void setYear_4_is_additional(boolean year_4_is_additional) {
        this.year_4_is_additional = year_4_is_additional;
    }

    public Integer getYear_4_credits() {
        return year_4_credits;
    }

    public void setYear_4_credits(Integer year_4_credits) {
        this.year_4_credits = year_4_credits;
    }

    public boolean isYear_5_is_additional() {
        return year_5_is_additional;
    }

    public void setYear_5_is_additional(boolean year_5_is_additional) {
        this.year_5_is_additional = year_5_is_additional;
    }

    public Integer getYear_5_credits() {
        return year_5_credits;
    }

    public void setYear_5_credits(Integer year_5_credits) {
        this.year_5_credits = year_5_credits;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }
}
