package com.example.demoapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Controller
@RequestMapping("/api")
public class FileController {

    @RequestMapping(value = "/getImage.do")
    public void getImage(@RequestParam("fileName") String fileName, HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        File file = null;
        FileInputStream fis = null;
        BufferedInputStream in = null;
        ByteArrayOutputStream bStream = null;

        String filePath = "D:\\images"+File.separator+fileName;

        try {
            file = new File(filePath);
            //file = courseDtaService.selectThumbnail(courseDtaVO);
            if(!file.exists()) {
                String noneFilePath = request.getServletContext().getRealPath("/") + "/resources/image/no-image.jpg";
                noneFilePath = noneFilePath.replaceAll("\\\\","/");
                file = new File(noneFilePath);
            }

            fis = new FileInputStream(file);
            in = new BufferedInputStream(fis);
            bStream = new ByteArrayOutputStream();
            int imgByte;
			/*while ((imgByte = in.read()) != -1) {
				bStream.write(imgByte);
			}*/
            while (true) {
                imgByte = in.read();
                if(imgByte == -1) {
                    break;
                }
                bStream.write(imgByte);
            }

            response.setHeader("Content-Type",  "image/jpeg");
            response.setContentLength(bStream.size());

            bStream.writeTo(response.getOutputStream());

            response.getOutputStream().flush();
            response.getOutputStream().close();
        }catch(Exception e) {
        }finally{
            if (bStream != null) {
                try {
                    bStream.close();
                } catch (Exception est) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ei) {
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception efis) {
                }
            }
        }

    }


    @PostMapping("/upload.json")
    @ResponseBody
    public void uploadFile(@RequestPart("file") MultipartFile file) throws IOException {

        byte[] buffer = file.getBytes();
        FileOutputStream fos = new FileOutputStream("D:\\images"+File.separator+file.getOriginalFilename());
        try {
            fos.write(buffer);
        }catch(Exception e) {
            System.out.println("실패");
            e.printStackTrace();
        }finally {
            fos.close();
        }
    }

}
