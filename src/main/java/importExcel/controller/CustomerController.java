package importExcel.controller;

import importExcel.helper.ExcelHelper;
import importExcel.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import importExcel.service.ExcelService;

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

        return new ResponseEntity<Void>( HttpStatus.OK);

    }
}
