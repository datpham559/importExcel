package importExcel.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Payable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String supplierCode;
    private String supplierName;
    @Column(name = "Account_Debit")
    private String accountDebit;
    @Column(name = "AOB_Debit")
    private double aobDebit;

    @Column(name = "AOB_Credit")
    private double aobCredit;

    @Column(name = "Arise_Debit")
    private double ariseDebit;

    @Column(name = "Arise_Credit")
    private double ariseCredit;

    @Column(name = "Close_Debit")
    private double closeDebit;

    @Column(name = "Close_Credit")
    private double closeCredit;

    @Column(name = "keyUUID")
    private String keyUUID;

    @Column(name = "CreatedDate")
    private LocalDate createdDate;

    public Payable(Long id, String supplierCode, String supplierName, String accountDebit, double aobDebit, double aobCredit, double ariseDebit, double ariseCredit, double closeDebit, double closeCredit, String keyUUID, LocalDate createdDate) {
        this.id = id;
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.accountDebit = accountDebit;
        this.aobDebit = aobDebit;
        this.aobCredit = aobCredit;
        this.ariseDebit = ariseDebit;
        this.ariseCredit = ariseCredit;
        this.closeDebit = closeDebit;
        this.closeCredit = closeCredit;
        this.keyUUID = keyUUID;
        this.createdDate = createdDate;
    }

    public Payable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAccountDebit() {
        return accountDebit;
    }

    public void setAccountDebit(String accountDebit) {
        this.accountDebit = accountDebit;
    }

    public double getAobDebit() {
        return aobDebit;
    }

    public void setAobDebit(double aobDebit) {
        this.aobDebit = aobDebit;
    }

    public double getAobCredit() {
        return aobCredit;
    }

    public void setAobCredit(double aobCredit) {
        this.aobCredit = aobCredit;
    }

    public double getAriseDebit() {
        return ariseDebit;
    }

    public void setAriseDebit(double ariseDebit) {
        this.ariseDebit = ariseDebit;
    }

    public double getAriseCredit() {
        return ariseCredit;
    }

    public void setAriseCredit(double ariseCredit) {
        this.ariseCredit = ariseCredit;
    }

    public double getCloseDebit() {
        return closeDebit;
    }

    public void setCloseDebit(double closeDebit) {
        this.closeDebit = closeDebit;
    }

    public double getCloseCredit() {
        return closeCredit;
    }

    public void setCloseCredit(double closeCredit) {
        this.closeCredit = closeCredit;
    }

    public String getKeyUUID() {
        return keyUUID;
    }

    public void setKeyUUID(String keyUUID) {
        this.keyUUID = keyUUID;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
