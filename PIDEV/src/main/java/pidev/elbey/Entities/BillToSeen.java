package pidev.elbey.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor

@RequiredArgsConstructor
public class BillToSeen implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBillToSeen;
    private Date billDate;
    private float tva;
    private float sale;
    private float totalHT;
    private float totalTTC;

    @OneToOne(mappedBy="billtoseen")
    private Delivery delivery;
}
