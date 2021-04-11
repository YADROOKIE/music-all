package com.yad.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yad.web.entity.Meta;
import com.yad.web.entity.UserFile;
import com.yad.web.entity.UserFileVo;
import com.yad.web.exception.MyException;
import com.yad.web.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private  MetaService metaService ;

    @Transactional
    public Meta saveFile(MultipartFile file) {


        String filename = file.getOriginalFilename();
        String fileType = filename.substring(filename.lastIndexOf('.'));
        InputStream is = null;
        try {
            String hash = UUID.randomUUID().toString();
            is = file.getInputStream() ;

            filename = "file/"+ hash+fileType;

            Meta meta = metaService.getById(hash);
            if (meta!=null){
                return  meta;
            }

            this.createAndSaveFile(filename,is);

            meta = new Meta();
            meta.setId(hash);
            meta.setPath(filename);
            boolean save = metaService.save(meta);


            return  save?  meta : null;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public  boolean createAndSaveFile(String hash , InputStream is ) throws IOException {
        byte[] bytes = new  byte[1024];
        int len = -1;
        File file = new File(hash);
        boolean ok = file.createNewFile();
        if (!ok){
            return  false;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        while (((len=is.read(bytes))!=-1)){
                fileOutputStream.write(bytes,0,len);
        }
        fileOutputStream.close();
        return  true;
    }



    @Autowired
    private  UserFileService userFileService;
    private  static  String root  = "ROOT";
    private  static  String folderType = "folder";
    private  static  String fileType = "file";

    @Transactional
    public UserFileVo loadUserFiles(String id) {
        QueryWrapper<UserFile> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        wrapper.eq("name",root);
        wrapper.isNull("parent");
        UserFile rootFolder = userFileService.getOne(wrapper);
        if (rootFolder==null){
            //如果为空说明第一次使用 给初始化
            UserFile userFile = new UserFile();
            userFile.setUserId(id);
            userFile.setName(root);
            userFile.setType(folderType);
            userFileService.save(userFile);
        }
        rootFolder = userFileService.getOne(wrapper);
        if (rootFolder==null){
            throw  new MyException(20001,"出错了");
        }

        QueryWrapper<UserFile> w = new QueryWrapper<>();
        w.eq("user_id",id);
        w.eq("parent",rootFolder.getId());
        List<UserFile> list = userFileService.list(w);

        UserFileVo fileVo = new UserFileVo();
        BeanUtils.copyProperties(rootFolder,fileVo);
        fileVo.setChildren(list);
        return fileVo;
    }
    @Transactional
    public UserFileVo loadUserFiles(String id,String pid) {
        QueryWrapper<UserFile> wrapper = new QueryWrapper<>();
        wrapper.eq("id",pid);
        UserFile rootFolder = userFileService.getOne(wrapper);



        if (rootFolder==null){
            throw  new MyException(20001,"出错了");
        }

        QueryWrapper<UserFile> w = new QueryWrapper<>();
        w.eq("user_id",id);
        w.eq("parent",rootFolder.getId());
        List<UserFile> list = userFileService.list(w);

        UserFileVo fileVo = new UserFileVo();
        BeanUtils.copyProperties(rootFolder,fileVo);
        fileVo.setChildren(list);
        return fileVo;
    }
    public void userUpload(MultipartFile file, String uid,String pid) {
        Meta meta = this.saveFile(file);
        UserFile userFile = new UserFile() ;
        userFile.setType(fileType);
        userFile.setName(file.getOriginalFilename());
        userFile.setfId(meta.getId());
        userFile.setParent(pid);
        userFile.setUserId(uid);
        userFileService.save(userFile);
    }

    public void createFodler(String uid, String pid, String name) {
        UserFile userFile = new UserFile() ;
        userFile.setType(folderType);
        userFile.setUserId(uid);
        userFile.setParent(pid);
        userFile.setName(name);
        userFileService.save(userFile);
    }


}
