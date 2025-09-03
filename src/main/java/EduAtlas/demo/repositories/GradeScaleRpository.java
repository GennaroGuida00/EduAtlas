package EduAtlas.demo.repositories;

import EduAtlas.demo.entities.GradeScale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeScaleRpository extends JpaRepository<GradeScale,Long> {
}
