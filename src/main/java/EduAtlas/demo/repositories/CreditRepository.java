package EduAtlas.demo.repositories;

import EduAtlas.demo.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CreditRepository extends JpaRepository<Credit,Long> {

    @Query("SELECT c FROM Credit c LEFT JOIN c.degree d LEFT JOIN d.country co WHERE co.country_id = :countryId")
    List<Credit> findCreditsByCountryId(@Param("countryId") long countryId);
}
