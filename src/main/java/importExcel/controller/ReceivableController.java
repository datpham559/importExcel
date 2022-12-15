package importExcel.controller;

import importExcel.entity.Payable;
import importExcel.entity.Receivable;
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
@RequestMapping(value = "/api/excel/receive")
public class ReceivableController {
    @Autowired
    ExcelService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadfileReceive(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.saveReceiveable(file);
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
    public ResponseEntity<Void> deleteByUUIDReceive(@RequestParam("keyUUID") String keyUUID) {
        System.out.println(keyUUID);
        fileService.deleteByKeyUUIDReceive(keyUUID);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Receivable>> getReceivables() {
        List<Receivable> receivables = fileService.getReceivables();
        return ResponseEntity.status(HttpStatus.OK).body(receivables);
    }

    @GetMapping(value = "/getReceiveableByKeyUUID")
    public ResponseEntity<List<Receivable>> getReceivableByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        List<Receivable> receivables = fileService.getReceivableByKeyUUID(keyUUID);
        return ResponseEntity.status(HttpStatus.OK).body(receivables);
    }
}
