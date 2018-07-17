package com.ssm.web.controller;

import com.ssm.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author 贾令强
 * @since 2018/6/18 16:38
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private static final String FILE_PATH = "/Users/zcjlq/workspace/IdeaProjects/ssm-security/ssm-security-demo/src/main/java/com/ssm/web/controller/";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        String fileName = file.getName();
        String originalFilename = file.getOriginalFilename();

        System.out.println("fileName:" + fileName);
        System.out.println("originalFilename:" + originalFilename);

        String newFileName = System.currentTimeMillis() + ".txt";
        File newFile = new File(FILE_PATH, newFileName);
        file.transferTo(newFile);
        return new FileInfo(newFileName, FILE_PATH);
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletResponse response) {
        try (InputStream inputStream = new FileInputStream(new File(FILE_PATH, id + ".txt"));
             OutputStream outputStream = response.getOutputStream()) {
            IOUtils.copy(inputStream, outputStream);
            response.setHeader("Content-Disposition", "attachment;filename=test.txt");
            response.setContentType("application/x-download");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
