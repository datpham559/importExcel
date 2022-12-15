package importExcel.controller;

import importExcel.entity.Customer;
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
@RequestMapping(value = "/api/excel")
public class CustomerController {

    @Autowired
    ExcelService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.save(file);
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

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        fileService.deleteByKeyUUID(keyUUID);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> customers = fileService.getCustomers();

        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping(value = "/getCustomersByKeyUUID")
    public ResponseEntity<List<Customer>> getCustomersByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        List<Customer> customers = fileService.getCustomersByKeyUUID(keyUUID);

        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping(value = "/export")
    public ResponseEntity<Resource> exportExcel() {
        String filename = "DM_KhachHang.xlsx";
        InputStreamResource file = new InputStreamResource(fileService.exportExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }


}
