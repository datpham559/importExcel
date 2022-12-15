package importExcel.controller;

import importExcel.entity.FixedProduct;
import importExcel.entity.Inventory;
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
@RequestMapping("/api/excel/inventory")
public class InventoryController {
    @Autowired
    ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadfileInventory(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.saveInventory(file);
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
        excelService.deleteByKeyUUIDInventory(keyUUID);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @GetMapping(value = "/export")
    public ResponseEntity<Resource> exportExcel() {
        String filename = "CCDC.xlsx";
        InputStreamResource file = new InputStreamResource(excelService.exportCCDCExcel());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Inventory>> getInventories() {
        List<Inventory> inventories = excelService.getInventories();
        return ResponseEntity.status(HttpStatus.OK).body(inventories);
    }

    @GetMapping(value = "/getInventoriesByKeyUUID")
    public ResponseEntity<List<Inventory>> getInventoriesByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        List<Inventory> inventories = excelService.getInventoriesByKeyUUID(keyUUID);
        return ResponseEntity.status(HttpStatus.OK).body(inventories);
    }

}
