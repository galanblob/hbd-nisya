package apap.tugas.singidol.controller;

import apap.tugas.singidol.model.IdolModel;
import apap.tugas.singidol.model.KonserModel;
import apap.tugas.singidol.model.TipeModel;
import apap.tugas.singidol.model.Penampilan_konserModel;
import apap.tugas.singidol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class BonusController {
    @Qualifier("bonusServiceImpl")

    @Autowired
    private BonusService bonusService;

    @Autowired
    private TipeService tipeService;

    @Autowired
    private KonserService konserService;

    @GetMapping(value = "/bonus/konser/top/{namaTipe}")
    public String bonusPage(@PathVariable(value = "namaTipe") String namaTipe, Model model) {
        try {
            KonserModel konser = konserService.getKonserByIdKonser(bonusService.getBonus(namaTipe));
            List<Penampilan_konserModel> listPenampilan = konser.getListPenampilan();

            model.addAttribute("listPenampilan", listPenampilan);
            model.addAttribute("konser", konser);
            return "bonus-page";
        }
        catch(NullPointerException e) {
            return "tidak-ada-tiket";
        }
    }

    @GetMapping("/bonus/konser/top")
    public String bonusFormPage(Model model) {
        List<TipeModel> tipe = tipeService.getListTipe();
        model.addAttribute("listTipe", tipe);
        return "form-bonus-page";
    }

}