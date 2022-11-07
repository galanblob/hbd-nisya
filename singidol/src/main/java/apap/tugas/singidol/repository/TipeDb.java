package apap.tugas.singidol.repository;

import apap.tugas.singidol.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipeDb extends JpaRepository<TipeModel, Long>  {
    Optional<TipeModel> findByIdTipe(Long idTipe);
}