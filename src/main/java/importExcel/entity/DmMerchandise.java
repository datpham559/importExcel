package importExcel.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dm_merchandise")
@Data
public class DmMerchandise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "nature")
    private String nature;

    @Column(name = "group_vthh")
    private String group;

    @Column(name = "describe")
    private String describe;

    @Column(name = "explain_buy")
    private String explainBuy;

    @Column(name = "explain_sell")
    private String explainSell;

    @Column(name = "main_dvt")
    private String main;

    @Column(name = "warranty_period")
    private String warrantyPeriod;

    @Column(name = "quantity_inventory")
    private double quantityInventory;

    @Column(name = "source")
    private String source;

    @Column(name = "implicitly_repository")
    private String implicitly;

    @Column(name = "warehouse_account")
    private String warehouseAccount;

    @Column(name = "expense_account")
    private String expenseAccount;

    @Column(name = "income_account")
    private String incomeAccount;

    @Column(name = "discount_account")
    private String discountAccount;

    @Column(name = "sale_account")
    private String saleAccount;

    @Column(name = "return_account")
    private String returntAccount;

    @Column(name = "rate_ckmh")
    private double rate;

    @Column(name = "fixed_purchase_price")
    private double fixedPurchasePrice;

    @Column(name = "latest_purchase_price")
    private double latestPurchasePrice;

    @Column(name = "unit_price_sell_1")
    private double unitPriceSell1;

    @Column(name = "unit_price_sell_2")
    private double unitPriceSell2;

    @Column(name = "unit_price_sell_3")
    private double unitPriceSell3;

    @Column(name = "fixed_unit_price")
    private double fixedUnitPrice;

    @Column(name = "unit_price_after_tax")
    private double unitPriceAfterTax;

    @Column(name = "tax_rate_gtgt")
    private String taxRateGtgt;

    @Column(name = "tax_rate_nk")
    private double taxRateNk;

    @Column(name = "tax_rate_xk")
    private double taxRateXk;

    @Column(name = "group_hhdv_taxable_ttdb")
    private String groupTaxable;

    @Column(name = "unfollow")
    private double unfollow;

    @Column(name = "inventory_number")
    private double inventoryNumber;

    @Column(name = "characteristic")
    private String characteristic;

    @Column(name = "inventory_value")
    private double inventoryValue;

    @Column(name = "follow")
    private double follow;

    @Column(name = "discount")
    private double discount;

    @Column(name = "from_amount")
    private String amountFrom;

    @Column(name = "to_amount")
    private String toAmount;

    @Column(name = "percent_discount")
    private double percentDiscount;

    @Column(name = "discount_amount")
    private double discountAmount;

    @Column(name = "conversion_unit")
    private String conversionUnit;

    @Column(name = "primary_unit_conversion_rate")
    private double primaryUnitConversionRate;

    @Column(name = "calculation")
    private String calculation;

    @Column(name = "describe1")
    private String describe1;

    @Column(name = "unit_price_sell1")
    private double unitPriceSell_1;

    @Column(name = "unit_price_sell2")
    private double unitPriceSell_2;

    @Column(name = "unit_price_sell3")
    private double unitPriceSell_3;

    @Column(name = "fixed_unit_price1")
    private double fixedUnitPrice1;

    @Column(name = "material_code")
    private String materialCode;

    @Column(name = "material_name")
    private String materialName;

    @Column(name = "dvt")
    private String dvt;

    @Column(name = "amount")
    private String amount;

    @Column(name = "specification_code")
    private String specificationCode;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "allow_same")
    private String allowSame;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    @Column(name = "keyUUID")
    private String keyUUID;

    public DmMerchandise() {
    }

}
