package apap.tugas.singidol.service;

import apap.tugas.singidol.model.TipeModel;

import java.util.List;

public interface TipeService {
    List<TipeModel> getListTipe();
    TipeModel getTipeByIdTipe(Long id);
}
