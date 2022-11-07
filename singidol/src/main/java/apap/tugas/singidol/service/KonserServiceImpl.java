package apap.tugas.singidol.service;

import apap.tugas.singidol.model.IdolModel;
import apap.tugas.singidol.model.Penampilan_konserModel;
import apap.tugas.singidol.service.KonserService;
import apap.tugas.singidol.model.KonserModel;
import apap.tugas.singidol.repository.KonserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashMap;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KonserServiceImpl implements KonserService {

    @Autowired
    KonserDb konserDb;

    @Override
    public void addKonser(KonserModel konser) {
        konserDb.save(konser);
    }

    @Override
    public List<KonserModel> getListKonser() {
        return konserDb.findAll();
    }

    @Override
    public KonserModel getKonserByIdKonser(Long id) {
        Optional<KonserModel> konser = konserDb.findByIdKonser(id);
        if (konser.isPresent()) {
            return konser.get();
        } else
            return null;
    }

    @Override
    public List<KonserModel> getListKonserFilter(Long pendapatan, IdolModel idol) {
        List<KonserModel> konser = konserDb.findAll();
        List<KonserModel> konser2 = new ArrayList<>();
        for (KonserModel ksr: konser) {
            if (ksr.getJumlahPendapatan() >= pendapatan) {
                for (Penampilan_konserModel pk : ksr.getListPenampilan()) {
                    if (pk.getIdol().equals(idol)) {
                        konser2.add(ksr);
                    }
                }
            }
        }
        return  konser2;
    }

    @Override
    public KonserModel updateKonser(KonserModel konser) {
        konserDb.save(konser);
        return konser;
    }
//    @Override
//    public List<CourseModel> getListCourseSort() {
//        return courseDb.findAll(Sort.by(Sort.Direction.ASC, "nameCourse"));
//    }
//
//    @Override
//    public CourseModel getCourseByCodeCourse(String code) {
//        Optional<CourseModel> course = courseDb.findByCode(code);
//        if (course.isPresent()) {
//            return course.get();
//        } else
//            return null;
//    }
//
//    @Override
//    public CourseModel getCourseByCodeCourseQuery(String code) {
//        Optional<CourseModel> course = courseDb.findByCodeUsingQuery(code);
//        if (course.isPresent()) {
//            return course.get();
//        } else
//            return null;
//    }
//
//    @Override
//    public CourseModel updateCourse(CourseModel course) {
//        courseDb.save(course);
//        return course;
//    }
//
//    @Override
//    public void deleteCourse(CourseModel course) {
//        courseDb.delete(course);
//    }
//
//    @Override
//    public boolean checkTime(CourseModel course) {
//        LocalDateTime timeNow = LocalDateTime.now();
//        return course.getTanggalBerakhir().isAfter(timeNow);
//    }
//
//    @Override
//    public boolean isClosed(LocalDateTime tanggalDimulai, LocalDateTime tanggalBerakhir) {
//        LocalDateTime now = LocalDateTime.now();
//        if (now.isBefore(tanggalDimulai) || now.isAfter(tanggalBerakhir)){
//            return true;
//        }
//        return false;
//    }
}