package importExcel.service;

import importExcel.entity.DmMerchandise;
import importExcel.entity.Supplier;
import importExcel.helper.DmMerchandiseExcelHelper;
import importExcel.helper.SupplierExcelHelper;
import importExcel.repository.DmMerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DmMerchandiseService {
    @Autowired
    private DmMerchandiseRepository dmMerchandiseRepository;

    public void save(MultipartFile file){
        try {
            List<DmMerchandise> dmMerchandises = DmMerchandiseExcelHelper.excelToDmMerchandise(file.getInputStream());
            String key = UUID.randomUUID().toString();
            LocalDate date = LocalDate.now();
            for (DmMerchandise dmMerchandise : dmMerchandises){
                dmMerchandise.setCreatedDate(date);
                dmMerchandise.setKeyUUID(key);
            }
            dmMerchandiseRepository.saveAll(dmMerchandises);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    public void deleteByKeyUUID(String keyUUID){
        dmMerchandiseRepository.deleteByKeyUUID(keyUUID);
    }

    public List<DmMerchandise> getDmMerchandise() {
        return dmMerchandiseRepository.findAll();
    }

    public List<DmMerchandise> getDmMerchandiseByKeyUUID(String keyUUID) {
        return dmMerchandiseRepository.getDmMerchandiseByKeyUUID(keyUUID);
    }

    public ByteArrayInputStream exportExcel() {
        List<DmMerchandise> dmMerchandises = dmMerchandiseRepository.findAll();
        ByteArrayInputStream inputStream = DmMerchandiseExcelHelper.dmMerchandiseToExcel(dmMerchandises);
        return inputStream;
    }

}
