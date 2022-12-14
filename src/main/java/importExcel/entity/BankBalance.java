package importExcel.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bank_balance")
public class BankBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bank_account")
    private String bankAccount;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "branch")
    private String branch;

    @Column(name = "opening_balance")
    private double openingBalance;

    @Column(name = "debt_Incurred")
    private double debtIncurred;

    @Column(name = "incurred")
    private double Incurred;

    @Column(name = "ending_balance")
    private double endingBalance;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    @Column(name = "keyUUID")
    private String keyUUID;

    public BankBalance() {
    }

    public BankBalance(Integer id, String bankAccount, String bankName, String branch, double openingBalance, double debtIncurred, double incurred, double endingBalance, LocalDate createdDate, String keyUUID) {
        this.id = id;
        this.bankAccount = bankAccount;
        this.bankName = bankName;
        this.branch = branch;
        this.openingBalance = openingBalance;
        this.debtIncurred = debtIncurred;
        Incurred = incurred;
        this.endingBalance = endingBalance;
        this.createdDate = createdDate;
        this.keyUUID = keyUUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public double getDebtIncurred() {
        return debtIncurred;
    }

    public void setDebtIncurred(double debtIncurred) {
        this.debtIncurred = debtIncurred;
    }

    public double getIncurred() {
        return Incurred;
    }

    public void setIncurred(double incurred) {
        Incurred = incurred;
    }

    public double getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(double endingBalance) {
        this.endingBalance = endingBalance;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getKeyUUID() {
        return keyUUID;
    }

    public void setKeyUUID(String keyUUID) {
        this.keyUUID = keyUUID;
    }
}
