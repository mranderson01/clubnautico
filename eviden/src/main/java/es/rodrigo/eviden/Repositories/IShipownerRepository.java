package es.rodrigo.eviden.Repositories;

import es.rodrigo.eviden.Models.Boat;
import es.rodrigo.eviden.Models.Shipowner;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IShipownerRepository extends JpaRepository<Shipowner,Integer> {

    @Query("SELECT s FROM Shipowner s JOIN s.user u WHERE u.dni = :dni")
    Shipowner findByDni(@Param("dni") String dni);
}
