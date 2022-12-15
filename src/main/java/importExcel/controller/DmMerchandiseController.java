package importExcel.controller;

import importExcel.entity.DmMerchandise;
import importExcel.entity.Supplier;
import importExcel.helper.DmMerchandiseExcelHelper;
import importExcel.message.ResponseMessage;
import importExcel.service.DmMerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/excel")
public class DmMerchandiseController {

    @Autowired
    DmMerchandiseService dmMerchandiseService;

    @PostMapping("/upload-dm-merchandise")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (DmMerchandiseExcelHelper.hasExcelFormat(file)) {
            try {
                dmMerchandiseService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
    @DeleteMapping(value = "/delete-dm-merchandise")
    public ResponseEntity<Void> deleteByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        dmMerchandiseService.deleteByKeyUUID(keyUUID);

        return new ResponseEntity<Void>( HttpStatus.OK);

    }

    @GetMapping(value = "/get-dm-merchandise")
    public ResponseEntity<List<DmMerchandise>> getDmMerchandise() {
        List<DmMerchandise> dmMerchandises = dmMerchandiseService.getDmMerchandise();

        return ResponseEntity.status(HttpStatus.OK).body(dmMerchandises);
    }

    @GetMapping(value = "/getDmMerchandiseByKeyUUID")
    public ResponseEntity<List<DmMerchandise>> getSupplierByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        List<DmMerchandise> dmMerchandises = dmMerchandiseService.getDmMerchandiseByKeyUUID(keyUUID);

        return ResponseEntity.status(HttpStatus.OK).body(dmMerchandises);
    }

    @GetMapping(value = "/export-dm-merchandise")
    public ResponseEntity<Resource> exportExcel() {
        String filename = "VatTuHangHoa.xlsx";
        InputStreamResource file = new InputStreamResource(dmMerchandiseService.exportExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

    }
}
