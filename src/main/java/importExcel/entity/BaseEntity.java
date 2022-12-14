package importExcel.entity;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "voucherType")
    private String voucherType;

    @Column(name = "voucherNo")
    private String voucherNo;

    @Column(name = "voucherDate")
    private LocalDate voucherDate;

    @Column(name = "accountingDate")
    private LocalDate accountingDate;

    @Column(name = "invoiceNo")
    private String invoiceNo;

    @Column(name = "invoiceDate")
    private String invoiceDate;

    @Column(name = "debitAccount")
    private String debitAccount;

    @Column(name = "creditAccount")
    private String creditAccount;

    @Column(name = "currencyType")
    private String currencyType;

    @Column(name = "currency")
    private Long currency;

    @Column(name = "materialGoodCode")
    private String materialGoodCode;

    @Column(name = "materialGoodName")
    private String materialGoodName;

    @Column(name = "storageIn")
    private String storageIn ;

    @Column(name = "storageOut")
    private String storageOut;

    @Column(name = "caculationUnit")
    private String caculationUnit;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "price")
    private Long price;

    @Column(name = "tranferRate")
    private Float tranferRate;

    @Column(name = "moneyTranfer")
    private Long moneyTranfer;

    @Column(name = "fixedAssetsType")
    private String fixedAssetsType;

    @Column(name = "fixedAssetsCode")
    private String fixedAssetsCode;

    @Column(name = "toolsCode")
    private String toolsCode;

    @Column(name = "debitObject")
    private String debitObject;

    @Column(name = "creditObject")
    private String creditObject;

    @Column(name = "unit")
    private String unit;

    @Column(name = "employee")
    private String employee;

    @Column(name = "bankAccount")
    private String bankAccount;

    @Column(name = "itemCost")
    private String itemCost;

    @Column(name = "construction")
    private String construction;

    @Column(name = "costSet")
    private String costSet;

    @Column(name = "purchaseOrder")
    private String purchaseOrder;

    @Column(name = "buyOrder")
    private String buyOrder;

    @Column(name = "purchaseContract")
    private String purchaseContract;

    @Column(name = "saleContract")
    private String saleContract;

    @Column(name = "statsCode")
    private String statsCode;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "explanationDetail")
    private String explanationDetail;

    @Column(name = "recordStatus")
    private String recordStatus;

    @Column(name = "createdDate")
    private LocalDate createdDate;
    @Column(name = "keyUUID")
    private String keyUUID;

    public BaseEntity(Integer id, String voucherType, String voucherNo, LocalDate voucherDate, LocalDate accountingDate, String invoiceNo, String invoiceDate, String debitAccount, String creditAccount, String currencyType, Long currency, String materialGoodCode, String materialGoodName, String storageIn, String storageOut, String caculationUnit, Long amount, Long price, Float tranferRate, Long moneyTranfer, String fixedAssetsType, String fixedAssetsCode, String toolsCode, String debitObject, String creditObject, String unit, String employee, String bankAccount, String itemCost, String construction, String costSet, String purchaseOrder, String buyOrder, String purchaseContract, String saleContract, String statsCode, String explanation, String explanationDetail, String recordStatus,LocalDate createdDate, String keyUUID) {
        this.id = id;
        this.voucherType = voucherType;
        this.voucherNo = voucherNo;
        this.voucherDate = voucherDate;
        this.accountingDate = accountingDate;
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.currencyType = currencyType;
        this.currency = currency;
        this.materialGoodCode = materialGoodCode;
        this.materialGoodName = materialGoodName;
        this.storageIn = storageIn;
        this.storageOut = storageOut;
        this.caculationUnit = caculationUnit;
        this.amount = amount;
        this.price = price;
        this.tranferRate = tranferRate;
        this.moneyTranfer = moneyTranfer;
        this.fixedAssetsType = fixedAssetsType;
        this.fixedAssetsCode = fixedAssetsCode;
        this.toolsCode = toolsCode;
        this.debitObject = debitObject;
        this.creditObject = creditObject;
        this.unit = unit;
        this.employee = employee;
        this.bankAccount = bankAccount;
        this.itemCost = itemCost;
        this.construction = construction;
        this.costSet = costSet;
        this.purchaseOrder = purchaseOrder;
        this.buyOrder = buyOrder;
        this.purchaseContract = purchaseContract;
        this.saleContract = saleContract;
        this.statsCode = statsCode;
        this.explanation = explanation;
        this.explanationDetail = explanationDetail;
        this.recordStatus = recordStatus;
        this.createdDate = createdDate;
        this.keyUUID = keyUUID;
    }

    public BaseEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public LocalDate getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(LocalDate voucherDate) {
        this.voucherDate = voucherDate;
    }

    public LocalDate getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(LocalDate accountingDate) {
        this.accountingDate = accountingDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public Long getCurrency() {
        return currency;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }

    public String getMaterialGoodCode() {
        return materialGoodCode;
    }

    public void setMaterialGoodCode(String materialGoodCode) {
        this.materialGoodCode = materialGoodCode;
    }

    public String getMaterialGoodName() {
        return materialGoodName;
    }

    public void setMaterialGoodName(String materialGoodName) {
        this.materialGoodName = materialGoodName;
    }

    public String getStorageIn() {
        return storageIn;
    }

    public void setStorageIn(String storageIn) {
        this.storageIn = storageIn;
    }

    public String getStorageOut() {
        return storageOut;
    }

    public void setStorageOut(String storageOut) {
        this.storageOut = storageOut;
    }

    public String getCaculationUnit() {
        return caculationUnit;
    }

    public void setCaculationUnit(String caculationUnit) {
        this.caculationUnit = caculationUnit;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Float getTranferRate() {
        return tranferRate;
    }

    public void setTranferRate(Float tranferRate) {
        this.tranferRate = tranferRate;
    }

    public Long getMoneyTranfer() {
        return moneyTranfer;
    }

    public void setMoneyTranfer(Long moneyTranfer) {
        this.moneyTranfer = moneyTranfer;
    }

    public String getFixedAssetsType() {
        return fixedAssetsType;
    }

    public void setFixedAssetsType(String fixedAssetsType) {
        this.fixedAssetsType = fixedAssetsType;
    }

    public String getFixedAssetsCode() {
        return fixedAssetsCode;
    }

    public void setFixedAssetsCode(String fixedAssetsCode) {
        this.fixedAssetsCode = fixedAssetsCode;
    }

    public String getToolsCode() {
        return toolsCode;
    }

    public void setToolsCode(String toolsCode) {
        this.toolsCode = toolsCode;
    }

    public String getDebitObject() {
        return debitObject;
    }

    public void setDebitObject(String debitObject) {
        this.debitObject = debitObject;
    }

    public String getCreditObject() {
        return creditObject;
    }

    public void setCreditObject(String creditObject) {
        this.creditObject = creditObject;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getItemCost() {
        return itemCost;
    }

    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public String getCostSet() {
        return costSet;
    }

    public void setCostSet(String costSet) {
        this.costSet = costSet;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(String buyOrder) {
        this.buyOrder = buyOrder;
    }

    public String getPurchaseContract() {
        return purchaseContract;
    }

    public void setPurchaseContract(String purchaseContract) {
        this.purchaseContract = purchaseContract;
    }

    public String getSaleContract() {
        return saleContract;
    }

    public void setSaleContract(String saleContract) {
        this.saleContract = saleContract;
    }

    public String getStatsCode() {
        return statsCode;
    }

    public void setStatsCode(String statsCode) {
        this.statsCode = statsCode;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanationDetail() {
        return explanationDetail;
    }

    public void setExplanationDetail(String explanationDetail) {
        this.explanationDetail = explanationDetail;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
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
