package apap.tugas.singidol.repository;

import apap.tugas.singidol.model.IdolModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdolDb extends JpaRepository<IdolModel, Long>  {
    Optional<IdolModel> findByIdIdol(Long idIdol);
}