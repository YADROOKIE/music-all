package com.yad.web.controller.file;

import com.yad.web.entity.BaseUser;
import com.yad.web.entity.Meta;
import com.yad.web.entity.UserFile;
import com.yad.web.service.FileService;
import com.yad.web.service.MetaService;
import com.yad.web.service.UserFileService;
import com.yad.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;


@CrossOrigin
@RestController
@RequestMapping(value ="/file")
public class RestFileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private UserFileService userFileService;
    @Autowired
    private MetaService metaService;

    @PostMapping("/upload")
    public  Object upload(MultipartFile file) throws IOException {
        Meta meta = fileService.saveFile(file);
        return  meta;
    }

    @PostMapping("/user/upload/{pid}")
    public R  userUpload(HttpServletRequest request,
                         @PathVariable("pid") String pid,MultipartFile file){
        BaseUser user = (BaseUser) request.getSession().getAttribute("user");
        if (user==null){
            return  R.error();
        }
        fileService.userUpload(file,user.getId(),pid);
        return  R.ok();
    }

    @PostMapping("/user/folder/{pid}/{name}")
    public R  createFolder( HttpServletRequest request,
                         @PathVariable("pid") String pid, @PathVariable("name") String name){
        BaseUser user = (BaseUser) request.getSession().getAttribute("user");
        if (user==null){
            return  R.error();
        }
        fileService.createFodler(user.getId(),pid,name);
        return  R.ok();
    }

    @GetMapping("/user/get/{id}")
    public ResponseEntity<FileSystemResource> userGetFile(@PathVariable String id,HttpServletResponse response){
        UserFile userFile = userFileService.getById(id);
        Meta meta = metaService.getById(userFile.getfId());
        File file = new File(meta.getPath());
        if (!file.exists()){
            return  null;
        }
        return export(file,userFile.getName());
    }


    public ResponseEntity<FileSystemResource> export(File file,String fileName) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }

}
