package importExcel.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class FixedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fixedProductCode;

    private String fixedProductName;

    private String fixedProductType;

    private String company;

    private LocalDate increaseDate;

    private String accountVoucherNumber;

    private LocalDate startDate;

    private double usingTimeNumber;

    private double remainTimeNumber;

    private double price;

    private double depriation;

    private double depriationInSeason;

    private double depriationAccumulate;

    private double remainNumber;

    private double depriationInMonth;


    private String fixedAssestsAccount;

    private String depriationAccount;

    @Column(name = "keyUUID")
    private String keyUUID;

    @Column(name = "CreatedDate")
    private LocalDate createdDate;

    public FixedProduct() {
    }

    public FixedProduct(Long id, String fixedProductCode, String fixedProductName, String fixedProductType, String company, LocalDate increaseDate, String accountVoucherNumber, LocalDate startDate, double usingTimeNumber, double remainTimeNumber, double price, double depriation, double depriationInSeason, double depriationAccumulate, double remainNumber, double depriationInMonth, String fixedAssestsAccount, String depriationAccount, String keyUUID, LocalDate createdDate) {
        this.id = id;
        this.fixedProductCode = fixedProductCode;
        this.fixedProductName = fixedProductName;
        this.fixedProductType = fixedProductType;
        this.company = company;
        this.increaseDate = increaseDate;
        this.accountVoucherNumber = accountVoucherNumber;
        this.startDate = startDate;
        this.usingTimeNumber = usingTimeNumber;
        this.remainTimeNumber = remainTimeNumber;
        this.price = price;
        this.depriation = depriation;
        this.depriationInSeason = depriationInSeason;
        this.depriationAccumulate = depriationAccumulate;
        this.remainNumber = remainNumber;
        this.depriationInMonth = depriationInMonth;
        this.fixedAssestsAccount = fixedAssestsAccount;
        this.depriationAccount = depriationAccount;
        this.keyUUID = keyUUID;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFixedProductCode() {
        return fixedProductCode;
    }

    public void setFixedProductCode(String fixedProductCode) {
        this.fixedProductCode = fixedProductCode;
    }

    public String getFixedProductName() {
        return fixedProductName;
    }

    public void setFixedProductName(String fixedProductName) {
        this.fixedProductName = fixedProductName;
    }

    public String getFixedProductType() {
        return fixedProductType;
    }

    public void setFixedProductType(String fixedProductType) {
        this.fixedProductType = fixedProductType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public double getUsingTimeNumber() {
        return usingTimeNumber;
    }

    public void setUsingTimeNumber(double usingTimeNumber) {
        this.usingTimeNumber = usingTimeNumber;
    }

    public double getRemainTimeNumber() {
        return remainTimeNumber;
    }

    public void setRemainTimeNumber(double remainTimeNumber) {
        this.remainTimeNumber = remainTimeNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDepriation() {
        return depriation;
    }

    public void setDepriation(double depriation) {
        this.depriation = depriation;
    }

    public double getDepriationInSeason() {
        return depriationInSeason;
    }

    public void setDepriationInSeason(double depriationInSeason) {
        this.depriationInSeason = depriationInSeason;
    }

    public double getDepriationAccumulate() {
        return depriationAccumulate;
    }

    public void setDepriationAccumulate(double depriationAccumulate) {
        this.depriationAccumulate = depriationAccumulate;
    }

    public double getRemainNumber() {
        return remainNumber;
    }

    public void setRemainNumber(double remainNumber) {
        this.remainNumber = remainNumber;
    }

    public double getDepriationInMonth() {
        return depriationInMonth;
    }

    public void setDepriationInMonth(double depriationInMonth) {
        this.depriationInMonth = depriationInMonth;
    }

    public String getFixedAssestsAccount() {
        return fixedAssestsAccount;
    }

    public void setFixedAssestsAccount(String fixedAssestsAccount) {
        this.fixedAssestsAccount = fixedAssestsAccount;
    }

    public String getDepriationAccount() {
        return depriationAccount;
    }

    public void setDepriationAccount(String depriationAccount) {
        this.depriationAccount = depriationAccount;
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
