package importExcel.controller;

import importExcel.entity.Payable;
import importExcel.entity.Stock;
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
@RequestMapping("/api/excel/stock")
public class StockController {
    @Autowired
    private ExcelService excelService;
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadStock(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.saveStock(file);
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
        excelService.deleteByKeyUUIDStock(keyUUID);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Stock>> getStocks() {
        List<Stock> stocks = excelService.getStocks();
        return ResponseEntity.status(HttpStatus.OK).body(stocks);
    }

    @GetMapping(value = "/getStocksByKeyUUID")
    public ResponseEntity<List<Stock>> getPayableByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        List<Stock> stocks = excelService.getStocksByKeyUUID(keyUUID);
        return ResponseEntity.status(HttpStatus.OK).body(stocks);
    }
}