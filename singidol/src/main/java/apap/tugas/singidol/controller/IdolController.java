package apap.tugas.singidol.controller;

import apap.tugas.singidol.model.IdolModel;
import apap.tugas.singidol.model.KonserModel;
import apap.tugas.singidol.model.Penampilan_konserModel;
import apap.tugas.singidol.service.IdolService;
import apap.tugas.singidol.service.KonserService;
import apap.tugas.singidol.service.PenampilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class IdolController {
    @Qualifier("idolServiceImpl")

    @Autowired
    private IdolService idolService;

    @GetMapping("/idol/{idIdol}")
    public String viewDetailIdolPage(@PathVariable(value = "idIdol") String idIdol, Model model) {
        IdolModel idol = idolService.getIdolById(Long.parseLong(idIdol));
        model.addAttribute("idol", idol);
        return "detail-idol";
    }

    @GetMapping("/idol/tambah")
    public String addIdolFormPage(Model model) {
        IdolModel idol = new IdolModel();
        model.addAttribute("idol", idol);
        return "form-add-idol";
    }

    @PostMapping(value = "/idol/tambah", params = { "save" })
    public String addIdolSubmit(@ModelAttribute IdolModel idol, Model model) {
        idolService.addIdol(idol);
        model.addAttribute("idol", idol.getNamaIdol());
        return "add-idol";
    }

    @GetMapping("/idol")
    public String listIdol(Model model) {
        List<IdolModel> listIdol = idolService.getListIdol();
        model.addAttribute("listIdol", listIdol);
        return "viewall-idol";
    }
}