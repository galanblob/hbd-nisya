package apap.tugas.singidol.service;

import apap.tugas.singidol.model.Penampilan_konserModel;
import apap.tugas.singidol.repository.Penampilan_konserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PenampilServiceImpl implements PenampilService {
    @Autowired
    Penampilan_konserDb penampilDb;

    @Override
    public List<Penampilan_konserModel> getListPenampil() {
        return penampilDb.findAll();
    }

    @Override
    public void penampilanHapus(Penampilan_konserModel penampilan){
        penampilDb.delete(penampilan);
    }

}
