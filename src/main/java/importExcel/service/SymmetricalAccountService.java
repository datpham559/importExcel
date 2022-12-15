package importExcel.service;

import importExcel.entity.Supplier;
import importExcel.entity.SymmetricalAccount;
import importExcel.helper.SupplierExcelHelper;
import importExcel.helper.SymmetricalAccountExcelHelper;
import importExcel.repository.SymmetricalAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class SymmetricalAccountService {
    @Autowired
    private SymmetricalAccountRepository symmetricalAccountRepository;

    public void save(MultipartFile file){
        try {
            List<SymmetricalAccount> symmetricalAccounts = SymmetricalAccountExcelHelper.excelToSymmetricalAccount(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (SymmetricalAccount symmetricalAccount : symmetricalAccounts){
                symmetricalAccount.setCreatedDate(date);
                symmetricalAccount.setKeyUUID(key);
            }
            symmetricalAccountRepository.saveAll(symmetricalAccounts);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public void deleteByKeyUUID(String keyUUID){
        symmetricalAccountRepository.deleteByKeyUUID(keyUUID);
    }

    public List<SymmetricalAccount> getSymmetrical() {
        return symmetricalAccountRepository.findAll();
    }

    public List<SymmetricalAccount> getSupplierByKeyUUID(String keyUUID) {
        return symmetricalAccountRepository.getSymmetricalByKeyUUID(keyUUID);
    }

    public ByteArrayInputStream exportExcel() {
        List<SymmetricalAccount> symmetricalAccounts = symmetricalAccountRepository.findAll();
        ByteArrayInputStream inputStream = SymmetricalAccountExcelHelper.symmetricalToExcel(symmetricalAccounts);
        return inputStream;
    }

}
