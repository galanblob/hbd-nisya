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
@Table(name = "tipe")
public class TipeModel  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long idTipe;

    @NotNull
    @Column(name = "harga",nullable = false)
    private Long hargaTipe;

    @NotNull
    @Column(name = "namaTipe", nullable = false)
    private String namaTipe;

    @NotNull
    @Column(name = "deskripsiTipe",nullable = false)
    private String deskripsiTipe;

    @OneToMany(mappedBy = "tipe" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TiketModel> listTiket;
}
