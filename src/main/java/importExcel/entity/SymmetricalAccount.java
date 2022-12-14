package importExcel.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "symmetrical_account")
public class SymmetricalAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "first_debt")
    private double firstDebt;

    @Column(name = "first_yes")
    private double fisrtYes;

    @Column(name = "debt_arises")
    private double debtArises;

    @Column(name = "arises_yes")
    private double arisesYes;

    @Column(name = "accumulated_debt")
    private double accumulatedDebt;

    @Column(name = "accumulated_yes")
    private double accumulatedYes;

    @Column(name = "last_debt")
    private double lastDebt;

    @Column(name = "last_yes")
    private double lastYes;

    @Column(name = "createdDate")
    private LocalDate createdDate;
    @Column(name = "keyUUID")
    private String keyUUID;

    public SymmetricalAccount() {
    }
}
