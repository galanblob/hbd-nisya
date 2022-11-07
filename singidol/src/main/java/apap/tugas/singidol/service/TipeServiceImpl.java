package apap.tugas.singidol.service;

import apap.tugas.singidol.model.KonserModel;
import apap.tugas.singidol.model.Penampilan_konserModel;
import apap.tugas.singidol.model.TiketModel;
import apap.tugas.singidol.model.TipeModel;
import apap.tugas.singidol.repository.TiketDb;
import apap.tugas.singidol.repository.TipeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipeServiceImpl implements TipeService {
    @Autowired
    TipeDb tipeDb;

    @Override
    public List<TipeModel> getListTipe() {
        return tipeDb.findAll();
    }

    @Override
    public TipeModel getTipeByIdTipe(Long id) {
        Optional<TipeModel> tipe = tipeDb.findByIdTipe(id);
        if (tipe.isPresent()) {
            return tipe.get();
        } else
            return null;
    }

}
