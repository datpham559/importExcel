package importExcel.dto;

import importExcel.entity.Accreditative;
import importExcel.entity.DepositCollection;
import importExcel.entity.PaySlip;
import importExcel.entity.Receipt;

import java.util.List;

public class DataToTable {
    private List<Accreditative> accreditatives;
    private List<DepositCollection> depositCollections;
    private List<PaySlip> paySlips ;
    private List<Receipt> receipts;

    public DataToTable() {
    }

    public DataToTable(List<Accreditative> accreditatives, List<DepositCollection> depositCollections, List<PaySlip> paySlips, List<Receipt> receipts) {
        this.accreditatives = accreditatives;
        this.depositCollections = depositCollections;
        this.paySlips = paySlips;
        this.receipts = receipts;
    }

    public List<Accreditative> getAccreditatives() {
        return accreditatives;
    }

    public void setAccreditatives(List<Accreditative> accreditatives) {
        this.accreditatives = accreditatives;
    }

    public List<DepositCollection> getDepositCollections() {
        return depositCollections;
    }

    public void setDepositCollections(List<DepositCollection> depositCollections) {
        this.depositCollections = depositCollections;
    }

    public List<PaySlip> getPaySlips() {
        return paySlips;
    }

    public void setPaySlips(List<PaySlip> paySlips) {
        this.paySlips = paySlips;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
}
