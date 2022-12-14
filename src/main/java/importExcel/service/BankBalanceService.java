package importExcel.service;

import importExcel.entity.BankBalance;
import importExcel.helper.BankBalanceExcelHelper;
import importExcel.repository.BankBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BankBalanceService {
    @Autowired
    private BankBalanceRepository bankBalanceRepository;

    public void save(MultipartFile file){
        try {
            List<BankBalance> bankBalances = BankBalanceExcelHelper.excelToBankBalance(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (BankBalance bankBalance : bankBalances){
                bankBalance.setCreatedDate(date);
                bankBalance.setKeyUUID(key);
            }
            bankBalanceRepository.saveAll(bankBalances);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public void deleteByKeyUUID(String keyUUID){
        bankBalanceRepository.deleteByKeyUUID(keyUUID);
    }

}
