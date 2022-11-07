package apap.tugas.singidol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "konser")
public class KonserModel  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_konser",nullable = false)
    private Long idKonser;

    @NotNull
    @Column(name = "nama_Konser", nullable = false)
    private String namaKonser;

    @NotNull
    @Column(name = "jumlah_pendapatan" ,nullable = false)
    private Long jumlahPendapatan;

    @NotNull
    @Column(name = "tempat_konser",nullable = false)
    private String tempatKonser;

    @NotNull
    @Column(name = "waktu_konser",nullable = false)
    @DateTimeFormat(pattern= "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktuKonser;

    @OneToMany(mappedBy = "konser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Penampilan_konserModel> listPenampilan;
 
    @OneToMany(mappedBy = "konser" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TiketModel> listTiket;
}
