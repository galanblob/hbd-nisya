package apap.tugas.singidol.service;

import apap.tugas.singidol.model.IdolModel;
import apap.tugas.singidol.model.KonserModel;
import apap.tugas.singidol.repository.IdolDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IdolServiceImpl implements IdolService {
    @Autowired
    IdolDb idolDb;

    @Override
    public List<IdolModel> getListIdol() {
        return idolDb.findAll();
    }

    @Override
    public void addIdol(IdolModel idol) {
        idolDb.save(idol);
    }

    @Override
    public IdolModel getIdolById(Long id) {
        Optional<IdolModel> idol = idolDb.findByIdIdol(id);
        if (idol.isPresent()) {
            return idol.get();
        } else
            return null;
    }
}
