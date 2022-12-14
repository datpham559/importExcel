package importExcel.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "supplies")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "supplier_code")
    private String supplierCode;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "address")
    private String address;

    @Column(name = "group_kh_ncc")
    private String group;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "unfollow")
    private boolean unfollow;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    @Column(name = "keyUUID")
    private String keyUUID;

    public Supplier() {
    }

    public Supplier(Integer id, String supplierCode, String supplierName, String address, String group, String taxCode, String phoneNumber, boolean unfollow, LocalDate createdDate, String keyUUID) {
        this.id = id;
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.address = address;
        this.group = group;
        this.taxCode = taxCode;
        this.phoneNumber = phoneNumber;
        this.unfollow = unfollow;
        this.createdDate = createdDate;
        this.keyUUID = keyUUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isUnfollow() {
        return unfollow;
    }

    public void setUnfollow(boolean unfollow) {
        this.unfollow = unfollow;
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
