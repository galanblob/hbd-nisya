package apap.tugas.singidol.service;

import apap.tugas.singidol.model.IdolModel;

import java.util.List;

public interface IdolService {
    List<IdolModel> getListIdol();

    IdolModel getIdolById(Long id);

    void addIdol(IdolModel idol);
}