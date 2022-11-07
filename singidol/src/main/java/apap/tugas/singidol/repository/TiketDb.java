package apap.tugas.singidol.repository;

import apap.tugas.singidol.model.TiketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TiketDb extends JpaRepository<TiketModel, Long>  {
    Optional<TiketModel> findByIdTiket(Long idTiket);
}