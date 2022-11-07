package apap.tugas.singidol.controller;

import apap.tugas.singidol.model.*;
import apap.tugas.singidol.service.KonserService;
import apap.tugas.singidol.service.TiketService;
import apap.tugas.singidol.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class TiketController {
    @Qualifier("tiketServiceImpl")

    @Autowired
    private TiketService tiketService;

    @Autowired
    private KonserService konserService;

    @Autowired
    private TipeService tipeService;

    @GetMapping("/tiket")
    public String listTiket(Model model) {
        List<TiketModel> listTiket = tiketService.getListTiket();
        model.addAttribute("listTiket", listTiket);
        return "viewall-tiket";
    }

    @GetMapping("/tiket/{idTiket}")
    public String viewDetailTiketPage(@PathVariable(value = "idTiket") String idTiket, Model model) {
        TiketModel tiket = tiketService.getTiketByIdTiket(Long.parseLong(idTiket));
        model.addAttribute("tiket", tiket);
        return "detail-tiket";
    }

    @GetMapping("/tiket/pesan")
    public String addTiketFormPage(Model model) {
        TiketModel tiket = new TiketModel();
        List<TipeModel> listTipe = tipeService.getListTipe();
        List<KonserModel> listKonser = konserService.getListKonser();

        model.addAttribute("tiket", tiket);
        model.addAttribute("listKonser", listKonser);
        model.addAttribute("listTipe", listTipe);
        return "form-add-tiket";
    }

    @PostMapping(value = "/tiket/pesan", params = { "save" })
    public String addTiketSubmit(@ModelAttribute TiketModel tiket, Model model) {
        tiket.setTanggalPembelian(LocalDateTime.now());
        KonserModel namaKonser = konserService.getKonserByIdKonser(tiket.getKonser().getIdKonser());
        TipeModel namaTipe = tipeService.getTipeByIdTipe(tiket.getTipe().getIdTipe());
        tiket.setNomorTiket(tiketService.getNomorTiketz(tiket, namaKonser.getNamaKonser(), namaTipe.getNamaTipe()));
        namaKonser.setJumlahPendapatan(namaKonser.getJumlahPendapatan() + namaTipe.getHargaTipe());
        tiketService.addTiket(tiket);
        model.addAttribute("tiket", tiket.getNomorTiket());
        model.addAttribute("konser", namaKonser.getNamaKonser());
        return "add-tiket";
    }

    @GetMapping("/tiket/hapus/{idTiket}")
    public String deleteTiket(@PathVariable(value = "idTiket") String idTiket, Model model) {
        TiketModel tiket = tiketService.getTiketByIdTiket(Long.parseLong(idTiket));
        tiket.getKonser().setJumlahPendapatan(tiket.getKonser().getJumlahPendapatan() - tiket.getTipe().getHargaTipe());
        tiketService.deleteTiket(tiket);
        model.addAttribute("tiket", tiket.getNomorTiket());
        model.addAttribute("konser", tiket.getKonser().getNamaKonser());
        return "delete-tiket";
    }
}