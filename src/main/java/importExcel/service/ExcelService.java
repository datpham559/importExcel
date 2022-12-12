package importExcel.service;

import importExcel.entity.Customer;
import importExcel.helper.ExcelHelper;
import importExcel.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    private CustomerRepository customerRepository;

    public void save(MultipartFile file){
        try {
            List<Customer> customers = ExcelHelper.excelToCustomers(file.getInputStream());
            customerRepository.saveAll(customers);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

}
