package apap.tugas.singidol.service;

import apap.tugas.singidol.model.KonserModel;
import apap.tugas.singidol.model.Penampilan_konserModel;
import apap.tugas.singidol.model.TiketModel;
import apap.tugas.singidol.model.TipeModel;
import apap.tugas.singidol.repository.TiketDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class BonusServiceImpl implements BonusService{
    @Autowired
    TiketDb tiketDb;

    @Override
    public Long getBonus(String nama) {

        List<TiketModel> listTiket = tiketDb.findAll();
        HashMap<KonserModel,Integer> isiBonus = new HashMap<KonserModel,Integer>();
        Integer nilai = 0;
        for (TiketModel tiket: listTiket){
            nilai += 1;
            if (tiket.getTipe().getNamaTipe().equals(nama)){
                if (isiBonus.containsKey(tiket.getKonser()) == false){
                    isiBonus.put(tiket.getKonser(), 1);
                }
                else {
                    isiBonus.put(tiket.getKonser(), isiBonus.get(tiket.getKonser())+1);
                }
            }
        }
        KonserModel konser = null;
        Integer besar = 0;
        for (Map.Entry<KonserModel,Integer> isi : isiBonus.entrySet()){
            if (konser != null){
                if (isi.getValue() >= besar &&
                        (isi.getKey().getNamaKonser().split(" ")[0].toLowerCase().charAt(0) <
                                konser.getNamaKonser().split(" ")[0].toLowerCase().charAt(0))){
                    konser = isi.getKey();
                    besar = isi.getValue();
                }
            }
            else{
                konser = isi.getKey();
                besar = isi.getValue();
            }
        }
        return konser.getIdKonser();
    }

}
