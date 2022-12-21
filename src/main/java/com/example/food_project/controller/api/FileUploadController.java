package com.example.food_project.controller.api;


import com.example.food_project.payload.response.DataResponse;
import com.example.food_project.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file){
        fileUploadService.storedFile(file);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(200);
        dataResponse.setSuccess(true);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request)
    throws IOException {
        Resource resource = fileUploadService.loadFileByName(fileName);
        String contentType = "";
        if(resource != null){
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }

        if(contentType == null || contentType.equals("")) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
