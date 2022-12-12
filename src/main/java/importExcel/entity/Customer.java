package importExcel.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(name="customerCode")
    private String customerCode;
    @NotNull
    @Column(name="customerName")
    private String customerName;

    @Column(name="address")
    private String address;

    @Column(name="customerGroup")
    private String customerGroup;

    @Column(name="tax")
    private String tax;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="unfollow")
    private boolean unfollow;

    public Customer() {
    }

    public Customer(Integer id, String customerCode, String customerName, String address, String customerGroup, String tax, String phoneNumber, boolean unfollow) {
        this.id = id;
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.address = address;
        this.customerGroup = customerGroup;
        this.tax = tax;
        this.phoneNumber = phoneNumber;
        this.unfollow = unfollow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
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
}
