package apap.tugas.singidol.service;

import apap.tugas.singidol.model.Penampilan_konserModel;

import java.util.List;

public interface PenampilService {
    List<Penampilan_konserModel> getListPenampil();
    void penampilanHapus(Penampilan_konserModel penampilan);
}
