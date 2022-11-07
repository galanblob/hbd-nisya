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
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "penampilan_konser")
public class Penampilan_konserModel  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_penampilan_konser",nullable = false)
    private Long idPenampilanKonser;

    @NotNull
    @Column(name = "jam_mulai_tampil", nullable = false)
    private String jamMulaiTampil;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_idol", referencedColumnName = "id_idol", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private IdolModel idol;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_konser", referencedColumnName = "id_konser", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private KonserModel konser;
}
