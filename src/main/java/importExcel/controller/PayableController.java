package importExcel.controller;

import importExcel.entity.FixedProduct;
import importExcel.entity.Payable;
import importExcel.helper.ExcelHelper;
import importExcel.message.ResponseMessage;
import importExcel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/excel/pay")
public class PayableController {
    @Autowired
    ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadfilePayable(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.savePayable(file);
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
        excelService.deleteByKeyUUIDPay(keyUUID);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Payable>> getPayables() {
        List<Payable> payables = excelService.getPayables();
        return ResponseEntity.status(HttpStatus.OK).body(payables);
    }

    @GetMapping(value = "/getPayableByKeyUUID")
    public ResponseEntity<List<Payable>> getPayableByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        List<Payable> payables = excelService.getPayablesByKeyUUID(keyUUID);
        return ResponseEntity.status(HttpStatus.OK).body(payables);
    }
}
