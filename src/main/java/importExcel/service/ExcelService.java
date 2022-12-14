package importExcel.service;

import importExcel.dto.DataToTable;
import importExcel.entity.Accreditative;
import importExcel.entity.Customer;
import importExcel.entity.Receipt;
import importExcel.helper.ExcelHelper;
import importExcel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ExcelService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccreditativesRepository accreditativesRepository;
    @Autowired
    private PayslipRepository payslipRepository;
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private DepositCollectionRepository depositCollectionRepository;

    public void save(MultipartFile file) {
        try {
            List<Customer> customers = ExcelHelper.excelToCustomers(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Customer customer : customers) {
                customer.setCreatedDate(date);
                customer.setKey(key);
            }
            customerRepository.saveAll(customers);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void deleteByKeyUUID(String keyUUID) {
        customerRepository.deleteByKeyUUID(keyUUID);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getCustomersByKeyUUID(String keyUUID) {
        return customerRepository.getCustomersByKeyUUID(keyUUID);
    }

    public ByteArrayInputStream exportExcel() {
        List<Customer> customers = customerRepository.findAll();
        ByteArrayInputStream inputStream = ExcelHelper.customersToExcel(customers);
        return inputStream;
    }
    public void saveToTable(MultipartFile file) {
        try {
            DataToTable dataToTable = ExcelHelper.excelToTable(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            if (dataToTable !=null) {
                if (dataToTable.getAccreditatives().size() > 0) {
                    for (Accreditative accreditative : dataToTable.getAccreditatives()) {
                        accreditative.setCreatedDate(date);
                        accreditative.setKeyUUID(key);
                    }
                    accreditativesRepository.saveAll(dataToTable.getAccreditatives());
                }
                if (dataToTable.getReceipts().size() > 0) {
                    for (Receipt receipt : dataToTable.getReceipts()) {
                        receipt.setCreatedDate(date);
                        receipt.setKeyUUID(key);
                    }
                    receiptRepository.saveAll(dataToTable.getReceipts());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
