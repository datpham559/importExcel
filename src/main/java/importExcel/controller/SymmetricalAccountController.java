package importExcel.controller;

import importExcel.entity.Supplier;
import importExcel.entity.SymmetricalAccount;
import importExcel.helper.SymmetricalAccountExcelHelper;
import importExcel.message.ResponseMessage;
import importExcel.service.SymmetricalAccountService;
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
public class SymmetricalAccountController {

    @Autowired
    SymmetricalAccountService symmetricalAccountService;

    @PostMapping("/upload-symmetrical-account")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (SymmetricalAccountExcelHelper.hasExcelFormat(file)) {
            try {
                symmetricalAccountService.save(file);

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
    @DeleteMapping(value = "/delete-symmetrical-account")
    public ResponseEntity<Void> deleteByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        symmetricalAccountService.deleteByKeyUUID(keyUUID);

        return new ResponseEntity<Void>( HttpStatus.OK);

    }

    @GetMapping(value = "/get-symmetrical")
    public ResponseEntity<List<SymmetricalAccount>> getSymmetrical() {
        List<SymmetricalAccount> symmetricalAccounts = symmetricalAccountService.getSymmetrical();

        return ResponseEntity.status(HttpStatus.OK).body(symmetricalAccounts);
    }

    @GetMapping(value = "/getSymmetricalByKeyUUID")
    public ResponseEntity<List<SymmetricalAccount>> getSymmetricalByKeyUUID(@RequestParam("keyUUID") String keyUUID) {
        List<SymmetricalAccount> symmetricalAccounts = symmetricalAccountService.getSupplierByKeyUUID(keyUUID);

        return ResponseEntity.status(HttpStatus.OK).body(symmetricalAccounts);
    }

    @GetMapping(value = "/export-symmetrical")
    public ResponseEntity<Resource> exportExcel() {
        String filename = "SoDuDauKy.xlsx";
        InputStreamResource file = new InputStreamResource(symmetricalAccountService.exportExcel());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

    }
}
