package importExcel.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String toolSupplyCode;

    private String toolSupplyName;

    private String toolSupplyType;

    private String increaseReason;

    private LocalDate increaseDate;

    private String accountVoucherNumber;

    private int instrustmentNumber;

    private int remainInstrustmentNumber;

    private String unit;

    private double increaseNumber;

    private double decreaseAccumulateNumber;

    private double remainNumber;

    private double toolSupplyvalue;

    private double pbRatio;

    private double pbInSeason;

    private double pbAccumulate;

    private double remainValue;

    private String waittingAccount;

    @Column(name = "keyUUID")
    private String keyUUID;

    @Column(name = "CreatedDate")
    private LocalDate createdDate;

    public Inventory() {
    }

    public Inventory(Long id, String toolSupplyCode, String toolSupplyName, String toolSupplyType, String increaseReason, LocalDate increaseDate, String accountVoucherNumber, int instrustmentNumber, int remainInstrustmentNumber, String unit, double increaseNumber, double decreaseAccumulateNumber, double remainNumber, double toolSupplyvalue, double pbRatio, double pbInSeason, double pbAccumulate, double remainValue, String waittingAccount, String keyUUID, LocalDate createdDate) {
        this.id = id;
        this.toolSupplyCode = toolSupplyCode;
        this.toolSupplyName = toolSupplyName;
        this.toolSupplyType = toolSupplyType;
        this.increaseReason = increaseReason;
        this.increaseDate = increaseDate;
        this.accountVoucherNumber = accountVoucherNumber;
        this.instrustmentNumber = instrustmentNumber;
        this.remainInstrustmentNumber = remainInstrustmentNumber;
        this.unit = unit;
        this.increaseNumber = increaseNumber;
        this.decreaseAccumulateNumber = decreaseAccumulateNumber;
        this.remainNumber = remainNumber;
        this.toolSupplyvalue = toolSupplyvalue;
        this.pbRatio = pbRatio;
        this.pbInSeason = pbInSeason;
        this.pbAccumulate = pbAccumulate;
        this.remainValue = remainValue;
        this.waittingAccount = waittingAccount;
        this.keyUUID = keyUUID;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToolSupplyCode() {
        return toolSupplyCode;
    }

    public void setToolSupplyCode(String toolSupplyCode) {
        this.toolSupplyCode = toolSupplyCode;
    }

    public String getToolSupplyName() {
        return toolSupplyName;
    }

    public void setToolSupplyName(String toolSupplyName) {
        this.toolSupplyName = toolSupplyName;
    }

    public String getToolSupplyType() {
        return toolSupplyType;
    }

    public void setToolSupplyType(String toolSupplyType) {
        this.toolSupplyType = toolSupplyType;
    }

    public String getIncreaseReason() {
        return increaseReason;
    }

    public void setIncreaseReason(String increaseReason) {
        this.increaseReason = increaseReason;
    }

    public LocalDate getIncreaseDate() {
        return increaseDate;
    }

    public void setIncreaseDate(LocalDate increaseDate) {
        this.increaseDate = increaseDate;
    }

    public String getAccountVoucherNumber() {
        return accountVoucherNumber;
    }

    public void setAccountVoucherNumber(String accountVoucherNumber) {
        this.accountVoucherNumber = accountVoucherNumber;
    }

    public int getInstrustmentNumber() {
        return instrustmentNumber;
    }

    public void setInstrustmentNumber(int instrustmentNumber) {
        this.instrustmentNumber = instrustmentNumber;
    }

    public int getRemainInstrustmentNumber() {
        return remainInstrustmentNumber;
    }

    public void setRemainInstrustmentNumber(int remainInstrustmentNumber) {
        this.remainInstrustmentNumber = remainInstrustmentNumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getIncreaseNumber() {
        return increaseNumber;
    }

    public void setIncreaseNumber(double increaseNumber) {
        this.increaseNumber = increaseNumber;
    }

    public double getDecreaseAccumulateNumber() {
        return decreaseAccumulateNumber;
    }

    public void setDecreaseAccumulateNumber(double decreaseAccumulateNumber) {
        this.decreaseAccumulateNumber = decreaseAccumulateNumber;
    }

    public double getRemainNumber() {
        return remainNumber;
    }

    public void setRemainNumber(double remainNumber) {
        this.remainNumber = remainNumber;
    }

    public double getToolSupplyvalue() {
        return toolSupplyvalue;
    }

    public void setToolSupplyvalue(double toolSupplyvalue) {
        this.toolSupplyvalue = toolSupplyvalue;
    }

    public double getPbRatio() {
        return pbRatio;
    }

    public void setPbRatio(double pbRatio) {
        this.pbRatio = pbRatio;
    }

    public double getPbInSeason() {
        return pbInSeason;
    }

    public void setPbInSeason(double pbInSeason) {
        this.pbInSeason = pbInSeason;
    }

    public double getPbAccumulate() {
        return pbAccumulate;
    }

    public void setPbAccumulate(double pbAccumulate) {
        this.pbAccumulate = pbAccumulate;
    }

    public double getRemainValue() {
        return remainValue;
    }

    public void setRemainValue(double remainValue) {
        this.remainValue = remainValue;
    }

    public String getWaittingAccount() {
        return waittingAccount;
    }

    public void setWaittingAccount(String waittingAccount) {
        this.waittingAccount = waittingAccount;
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
