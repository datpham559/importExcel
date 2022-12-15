package importExcel.controller;

import importExcel.entity.Customer;
import importExcel.entity.Supplier;
import importExcel.helper.SupplierExcelHelper;
import importExcel.message.ResponseMessage;
import importExcel.service.SupplierService;
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
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping("/upload-supplier")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (SupplierExcelHelper.hasExcelFormat(file)) {
            try {
                supplierService.save(file);

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
    @DeleteMapping(value = "/delete-suplier")
    public ResponseEntity<Void> deleteByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        supplierService.deleteByKeyUUID(keyUUID);

        return new ResponseEntity<Void>( HttpStatus.OK);

    }

    @GetMapping(value = "/get-suplplier")
    public ResponseEntity<List<Supplier>> getSupllier() {
        List<Supplier> suppliers = supplierService.getSupplier();

        return ResponseEntity.status(HttpStatus.OK).body(suppliers);
    }

    @GetMapping(value = "/getSupplierByKeyUUID")
    public ResponseEntity<List<Supplier>> getSupplierByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        List<Supplier> suppliers = supplierService.getSupplierByKeyUUID(keyUUID);

        return ResponseEntity.status(HttpStatus.OK).body(suppliers);
    }

    @GetMapping(value = "/export-suplier")
    public ResponseEntity<Resource> exportExcel() {
        String filename = "NHACUNGCAP.xlsx";
        InputStreamResource file = new InputStreamResource(supplierService.exportExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

    }
}
