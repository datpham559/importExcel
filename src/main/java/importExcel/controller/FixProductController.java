package importExcel.controller;

import importExcel.entity.Customer;
import importExcel.entity.FixedProduct;
import importExcel.helper.ExcelHelper;
import importExcel.message.ResponseMessage;
import importExcel.service.ExcelService;
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
@RequestMapping(value = "/api/excel/fixedProduct")
public class FixProductController {
    @Autowired
    ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFixedProduct(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.saveFixedProduct(file);
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
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        excelService.deleteByKeyUUID(keyUUID);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @GetMapping(value = "/export")
    public ResponseEntity<Resource> exportExcel() {
        String filename = "TSCD.xlsx";
        InputStreamResource file = new InputStreamResource(excelService.exportTSCDExcel());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<FixedProduct>> getFixProducts() {
        List<FixedProduct> fixedProducts = excelService.getFixProducts();
        return ResponseEntity.status(HttpStatus.OK).body(fixedProducts);
    }

    @GetMapping(value = "/getFixProductByKeyUUID")
    public ResponseEntity<List<FixedProduct>> getFixProductByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        List<FixedProduct> fixedProducts = excelService.getFixProductsByKeyUUID(keyUUID);
        return ResponseEntity.status(HttpStatus.OK).body(fixedProducts);
    }
}
