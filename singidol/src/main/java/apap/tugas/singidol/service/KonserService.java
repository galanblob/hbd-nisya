package apap.tugas.singidol.service;
import apap.tugas.singidol.model.IdolModel;
import apap.tugas.singidol.model.KonserModel;

import java.time.LocalDateTime;
import java.util.List;

public interface KonserService{
    List<KonserModel> getListKonser();
    void addKonser(KonserModel konser);
    KonserModel getKonserByIdKonser(Long id);
    KonserModel updateKonser(KonserModel konser);
    List<KonserModel> getListKonserFilter(Long pendapatan, IdolModel idol);
}
