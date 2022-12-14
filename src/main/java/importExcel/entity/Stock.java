package importExcel.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stockName;

    private String stockCode;

    private String itemName;

    private String unit;

    private double firstSeasonNumber;

    private double firstSeasonValue;

    private double goodReceiptNumber;

    private double goodReceiptValue;

    private double goodDeliveryNumber;

    private double goodDeliveryValue;

    private double lastSeasonNumber;

    private double lastSeasonValue;

    private double average;
    @Column(name = "keyUUID")
    private String keyUUID;

    @Column(name = "CreatedDate")
    private LocalDate createdDate;

    public Stock() {
    }

    public Stock(Long id, String stockName, String stockCode, String itemName, String unit, double firstSeasonNumber, double firstSeasonValue, double goodReceiptNumber, double goodReceiptValue, double goodDeliveryNumber, double goodDeliveryValue, double lastSeasonNumber, double lastSeasonValue, double average, String keyUUID, LocalDate createdDate) {
        this.id = id;
        this.stockName = stockName;
        this.stockCode = stockCode;
        this.itemName = itemName;
        this.unit = unit;
        this.firstSeasonNumber = firstSeasonNumber;
        this.firstSeasonValue = firstSeasonValue;
        this.goodReceiptNumber = goodReceiptNumber;
        this.goodReceiptValue = goodReceiptValue;
        this.goodDeliveryNumber = goodDeliveryNumber;
        this.goodDeliveryValue = goodDeliveryValue;
        this.lastSeasonNumber = lastSeasonNumber;
        this.lastSeasonValue = lastSeasonValue;
        this.average = average;
        this.keyUUID = keyUUID;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getFirstSeasonNumber() {
        return firstSeasonNumber;
    }

    public void setFirstSeasonNumber(double firstSeasonNumber) {
        this.firstSeasonNumber = firstSeasonNumber;
    }

    public double getFirstSeasonValue() {
        return firstSeasonValue;
    }

    public void setFirstSeasonValue(double firstSeasonValue) {
        this.firstSeasonValue = firstSeasonValue;
    }

    public double getGoodReceiptNumber() {
        return goodReceiptNumber;
    }

    public void setGoodReceiptNumber(double goodReceiptNumber) {
        this.goodReceiptNumber = goodReceiptNumber;
    }

    public double getGoodReceiptValue() {
        return goodReceiptValue;
    }

    public void setGoodReceiptValue(double goodReceiptValue) {
        this.goodReceiptValue = goodReceiptValue;
    }

    public double getGoodDeliveryNumber() {
        return goodDeliveryNumber;
    }

    public void setGoodDeliveryNumber(double goodDeliveryNumber) {
        this.goodDeliveryNumber = goodDeliveryNumber;
    }

    public double getGoodDeliveryValue() {
        return goodDeliveryValue;
    }

    public void setGoodDeliveryValue(double goodDeliveryValue) {
        this.goodDeliveryValue = goodDeliveryValue;
    }

    public double getLastSeasonNumber() {
        return lastSeasonNumber;
    }

    public void setLastSeasonNumber(double lastSeasonNumber) {
        this.lastSeasonNumber = lastSeasonNumber;
    }

    public double getLastSeasonValue() {
        return lastSeasonValue;
    }

    public void setLastSeasonValue(double lastSeasonValue) {
        this.lastSeasonValue = lastSeasonValue;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
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
