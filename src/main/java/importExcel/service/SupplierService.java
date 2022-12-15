package importExcel.service;

import importExcel.entity.Customer;
import importExcel.entity.Supplier;
import importExcel.helper.ExcelHelper;
import importExcel.helper.SupplierExcelHelper;
import importExcel.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public void save(MultipartFile file){
        try {
            List<Supplier> suppliers = SupplierExcelHelper.excelToSupplier(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Supplier supplier : suppliers){
                supplier.setCreatedDate(date);
                supplier.setKeyUUID(key);
            }
            supplierRepository.saveAll(suppliers);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public void deleteByKeyUUID(String keyUUID){
        supplierRepository.deleteByKeyUUID(keyUUID);
    }

    public List<Supplier> getSupplier() {
        return supplierRepository.findAll();
    }

    public List<Supplier> getSupplierByKeyUUID(String keyUUID) {
        return supplierRepository.getSupplierByKeyUUID(keyUUID);
    }

    public ByteArrayInputStream exportExcel() {
        List<Supplier> suppliers = supplierRepository.findAll();
        ByteArrayInputStream inputStream = SupplierExcelHelper.supplierToExcel(suppliers);
        return inputStream;
    }

}
