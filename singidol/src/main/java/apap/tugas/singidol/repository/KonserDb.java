package apap.tugas.singidol.repository;

import apap.tugas.singidol.model.KonserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KonserDb extends JpaRepository<KonserModel, Long>  {
    Optional<KonserModel> findByIdKonser(Long idKonser);
//    List<KonserModel> findByPendapatanAndIdol(Long jumlahPendapatan, Long idIdol);

}