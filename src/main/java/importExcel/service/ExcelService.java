package importExcel.service;

import importExcel.entity.Customer;
import importExcel.helper.ExcelHelper;
import importExcel.repository.CustomerRepository;
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

    public void save(MultipartFile file){
        try {
            List<Customer> customers = ExcelHelper.excelToCustomers(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (Customer customer : customers){
                customer.setCreatedDate(date);
                customer.setKey(key);
            }
            customerRepository.saveAll(customers);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public void deleteByKeyUUID(String keyUUID){
        customerRepository.deleteByKeyUUID(keyUUID);
    }

}
