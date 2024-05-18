package es.rodrigo.eviden.Repositories;

import es.rodrigo.eviden.Models.Departure;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IDepartureRepository extends JpaRepository<Departure,Integer> {
    @Override
    Optional<Departure> findById(Integer integer);

    @Query("SELECT d FROM Departure d JOIN d.shipowner s JOIN s.user u WHERE u.username= :username")
    List<Departure> findDeparturesByUsername(@Param("username") String username);
}
