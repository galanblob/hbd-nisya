package apap.tugas.singidol.service;

import apap.tugas.singidol.model.TiketModel;

import java.util.List;

public interface TiketService {
    void addTiket(TiketModel tiket);
    String getNomorTiketz(TiketModel tiket , String namaKonser, String namaTipe);
    List<TiketModel> getListTiket();
    TiketModel getTiketByIdTiket(Long id);
    void deleteTiket(TiketModel tiket);
}
