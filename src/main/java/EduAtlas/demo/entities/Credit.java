package EduAtlas.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "credities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    @Id
    private int credit_id;
    private int year;
    @ManyToOne
    @JoinColumn(name = "degree_id")
    private Degree degree;

}
