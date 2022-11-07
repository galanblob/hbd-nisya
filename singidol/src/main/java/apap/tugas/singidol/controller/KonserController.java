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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class KonserController {
    @Qualifier("konserServiceImpl")

    @Autowired
    private KonserService konserService;

    @Autowired
    private PenampilService penampilanService;

    @Autowired
    private IdolService idolService;

    @GetMapping("/konser/tambah")
    public String addKonserFormPage(Model model) {
        KonserModel konser = new KonserModel();
        List<IdolModel> listIdol = idolService.getListIdol();
        List<Penampilan_konserModel> listPenampilanNew = new ArrayList<>();

        konser.setListPenampilan(listPenampilanNew);
        konser.getListPenampilan().add(new Penampilan_konserModel());

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);

        return "form-add-konser";
    }

    @PostMapping(value = "/konser/tambah", params = { "save" })
    public String addKonserSubmit(@ModelAttribute KonserModel konser, Model model) {
        if (konser.getListPenampilan() == null) {
            konser.setListPenampilan(new ArrayList<>());
        }

        for (Penampilan_konserModel temp: konser.getListPenampilan()){
            temp.setKonser(konser);
        }

        konser.setJumlahPendapatan(0L);
        konserService.addKonser(konser);

        model.addAttribute("konser", konser.getNamaKonser());
        return "add-konser";
    }

    @GetMapping("/konser")
    public String listKonser(Model model) {
        List<KonserModel> listKonser = konserService.getListKonser();
        model.addAttribute("listKonser", listKonser);
        return "viewall-konser";
    }

    @PostMapping(value = "/konser/tambah", params = {"deleteRow"})
    private String deleteRowIdolMultiple(
            @ModelAttribute KonserModel konser,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);
        konser.getListPenampilan().remove(rowId.intValue());

        List<IdolModel> listIdol = idolService.getListIdol();

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);

        return "form-add-konser";
    }
    @PostMapping(value = "/konser/tambah", params = {"addRow"})
    private String addRowIdolMultiple(
            @ModelAttribute KonserModel konser,
            Model model
    ){
        if (konser.getListPenampilan() == null || konser.getListPenampilan().size() == 0){
            konser.setListPenampilan(new ArrayList<>());
        }
        konser.getListPenampilan().add(new Penampilan_konserModel());
        List<IdolModel> listIdol = idolService.getListIdol();

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);

        return "form-add-konser";
    }
   @GetMapping("/konser/{idKonser}")
   public String viewDetailKonserPage(@PathVariable(value = "idKonser") String idKonser, Model model) {
       KonserModel konser = konserService.getKonserByIdKonser(Long.parseLong(idKonser));
       List<Penampilan_konserModel> listPenampilan = konser.getListPenampilan();

       model.addAttribute("listPenampilan", listPenampilan);
       model.addAttribute("konser", konser);
       return "detail-konser";
   }

    @GetMapping("/konser/ubah/{idKonser}")
    public String updateKonserFormPage(@PathVariable(value = "idKonser") String idKonser, Model model) {
        KonserModel konser = konserService.getKonserByIdKonser(Long.parseLong(idKonser));
        List<IdolModel> listIdol = idolService.getListIdol();

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);
        return "form-update-konser";
    }

    @PostMapping(value= "/konser/ubah", params = {"update"})
    public String updateKonserSubmitPage(@ModelAttribute KonserModel konser, Model model) {
        for (Penampilan_konserModel temp: konser.getListPenampilan()){
            temp.setKonser(konser);
        }

        konser.setIdKonser(konser.getIdKonser());
        KonserModel updatedKonser = konserService.updateKonser(konser);
        model.addAttribute("konser", updatedKonser.getNamaKonser());
        return "update-konser";
    }

    @PostMapping(value = "/konser/ubah", params = {"deleteRow"})
    private String deleteUpdateRowIdolMultiple(
            @ModelAttribute KonserModel konser,
            @RequestParam(value = "idKonser") String idKonser,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);
        List<IdolModel> listIdol = idolService.getListIdol();

        Penampilan_konserModel penampilanHapus = konser.getListPenampilan().remove(rowId.intValue());
        penampilanService.penampilanHapus(penampilanHapus);

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);

        return "form-update-konser";
    }

    @PostMapping(value = "/konser/ubah", params = {"addRow"})
    private String addUpdateRowIdolMultiple(
            @ModelAttribute KonserModel konser,
            @RequestParam(value = "idKonser") String idKonser,
            Model model
    ){
        if (konser.getListPenampilan() == null || konser.getListPenampilan().size() == 0){
            konser.setListPenampilan(new ArrayList<>());
        }
        konser.getListPenampilan().add(new Penampilan_konserModel());
        List<IdolModel> listIdol = idolService.getListIdol();

        konser.setIdKonser(konser.getIdKonser());
        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);

        return "form-update-konser";
    }

    @GetMapping("/konser/cari")
    public String cariKonserFormPage(Model model) {
        List<IdolModel> listIdol = idolService.getListIdol();
        List<KonserModel> listKonser = konserService.getListKonser();
        List<KonserModel> listKonsernew = new ArrayList<>();
        model.addAttribute("listIdolExisting", listIdol);
        model.addAttribute("listKonsernew", listKonsernew);
        return "form-cari-konser";
    }

    @GetMapping(value = "/carikonser", params = "search")
    public String cariKonserPage(@RequestParam(value = "idIdol") String idIdol, @RequestParam(value = "jumlahPendapatan") String jumlahPendapatan, Model model) {
        List<IdolModel> listIdol = idolService.getListIdol();
        IdolModel idol = idolService.getIdolById(Long.parseLong(idIdol));
        List<KonserModel> listKonsernew = konserService.getListKonserFilter(Long.parseLong(jumlahPendapatan), idol);
        model.addAttribute("listIdolExisting", listIdol);
        model.addAttribute("listKonsernew", listKonsernew);
        return "form-cari-konser";
    }

}