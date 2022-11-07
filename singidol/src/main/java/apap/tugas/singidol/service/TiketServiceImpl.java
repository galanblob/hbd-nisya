package apap.tugas.singidol.service;

import apap.tugas.singidol.model.KonserModel;
import apap.tugas.singidol.model.Penampilan_konserModel;
import apap.tugas.singidol.model.TiketModel;
import apap.tugas.singidol.repository.TiketDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class TiketServiceImpl implements TiketService{
    @Autowired
    TiketDb tiketDb;

    @Override
    public void addTiket(TiketModel tiket) {
        tiketDb.save(tiket);
    }

    @Override
    public List<TiketModel> getListTiket() {
        return tiketDb.findAll();
    }

    @Override
    public TiketModel getTiketByIdTiket(Long id) {
        Optional<TiketModel> tiket = tiketDb.findByIdTiket(id);
        if (tiket.isPresent()) {
            return tiket.get();
        } else
            return null;
    }

    @Override
    public void deleteTiket(TiketModel tiket) {
        tiketDb.delete(tiket);
    }


    @Override
    public String getNomorTiketz(TiketModel tiket, String namaKonser, String namaTipe) {
        String nama = tiket.getNamaLengkap().split(" ")[0].substring(0,3).toUpperCase();
        LocalDateTime now = LocalDateTime.now();
        String tanggalBeli = now.getDayOfMonth() + String.valueOf(now.getMonthValue());
        String tanggalLahir = tiket.getTanggalLahir().getDayOfMonth() + String.valueOf(tiket.getTanggalLahir().getMonthValue());
        int gabungan = Integer.parseInt(tanggalBeli) + Integer.parseInt(tanggalLahir);
        String gabung2 = "";
        if (gabungan <1000){
            gabung2 = "0" + String.valueOf(gabungan);
        }
        else {
            gabung2 = String.valueOf(gabungan);
        }
        String namakonser1 = namaKonser.split(" ")[0].substring(0,1).toLowerCase();
        int namakonser2 = namakonser1.charAt(0) - 'a' + 1;
        String namakonser3 = "";
        if (namakonser2 < 10){
            namakonser3 = "0" + String.valueOf(namakonser2);
        }
        else {
            namakonser3 = String.valueOf(namakonser2);
        }
        String jenis ;
        if (namaTipe.equals("vip")){
            jenis = "VIP";
        }
        else if (namaTipe.equals("platinum")){
            jenis = "PLT";
        }
        else if (namaTipe.equals("gold")){
            jenis = "GLD";
        }
        else {
            jenis = "SLV";
        }
        Random rnd = new Random();
        String charTerakhir = String.valueOf((char) (rnd.nextInt(26) + 'a')).toUpperCase();
        return nama + gabung2 + namakonser3 + jenis + charTerakhir;
    }
}
