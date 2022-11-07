package apap.tugas.singidol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tiket")
public class TiketModel  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tiket",nullable = false)
    private Long idTiket;

    @NotNull
    @Column(name = "nama_Lengkap", nullable = false)
    private String namaLengkap;

    @NotNull
    @Column(name = "nomor_tiket" ,nullable = false)
    private String nomorTiket;

    @NotNull
    @Column(name = "email_tiket",nullable = false)
    private String emailTiket;

    @NotNull
    @Column(name = "tanggal_pembelian",nullable = false)
    @DateTimeFormat(pattern= "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalPembelian;

    @NotNull
    @Column(name = "tanggal_lahir",nullable = false)
    @DateTimeFormat(pattern= "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime tanggalLahir;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_konser", referencedColumnName = "id_konser", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KonserModel konser;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TipeModel tipe;
}
