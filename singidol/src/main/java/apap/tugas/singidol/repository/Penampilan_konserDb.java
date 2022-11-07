package apap.tugas.singidol.repository;

import apap.tugas.singidol.model.Penampilan_konserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Penampilan_konserDb extends JpaRepository<Penampilan_konserModel, Long>  {
    Optional<Penampilan_konserModel> findByIdPenampilanKonser(Long idPenampilanKonser);
}