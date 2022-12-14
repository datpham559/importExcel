package importExcel.service;

import importExcel.entity.*;
import importExcel.helper.ExcelHelper;
import importExcel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ExcelService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ReceivableRepository receivableRepository;

    @Autowired
    private PayableRepository payableRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private FixedProductRepository fixedProductRepository;

    @Autowired
    private StockRepository stockRepository;
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

    public void saveReceiveable(MultipartFile file) {
        try {
            List<Receivable> receivables = ExcelHelper.excelToReceivable(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Receivable receivable : receivables) {
                receivable.setCreatedDate(date);
                receivable.setKeyUUID(key);
            }
            receivableRepository.saveAll(receivables);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void deleteByKeyUUIDReceive(String keyUUID) {
        receivableRepository.deleteByKeyUUID(keyUUID);
    }

    public void savePayable(MultipartFile file) {
        try {
            List<Payable> payables = ExcelHelper.excelToPayable(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Payable payable : payables) {
                payable.setCreatedDate(date);
                payable.setKeyUUID(key);
            }
            payableRepository.saveAll(payables);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void deleteByKeyUUIDPay(String keyUUID){
        payableRepository.deleteByKeyUUID(keyUUID);
    }

    public void saveInventory(MultipartFile file){
        try {
            List<Inventory> inventories = ExcelHelper.excelToInventory(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Inventory inventory : inventories) {
                inventory.setCreatedDate(date);
                inventory.setKeyUUID(key);
            }
            inventoryRepository.saveAll(inventories);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void deleteByKeyUUIDInventory(String keyUUID){
        inventoryRepository.deleteByKeyUUID(keyUUID);
    }

    public void saveFixedProduct(MultipartFile file){
        try {
            List<FixedProduct> fixedProducts = ExcelHelper.excelToFixedProduct(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (FixedProduct fixedProduct : fixedProducts) {
                fixedProduct.setCreatedDate(date);
                fixedProduct.setKeyUUID(key);
            }
            fixedProductRepository.saveAll(fixedProducts);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void deleteByKeyUUIDFixedProduct(String keyUUID){
        fixedProductRepository.deleteByKeyUUID(keyUUID);
    }

    public void saveStock(MultipartFile file){
        try {
            List<Stock> stocks = ExcelHelper.excelToStock(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Stock stock : stocks) {
                stock.setCreatedDate(date);
                stock.setKeyUUID(key);
            }
            stockRepository.saveAll(stocks);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void deleteByKeyUUIDStock(String keyUUID){
        stockRepository.deleteByKeyUUID(keyUUID);
    }
}
