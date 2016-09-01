package com.QuickStartFrame.management.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class FileUtil {
	private static final Logger logger = Logger.getLogger(FileUtil.class);
	
	/**
	 * @param request		{@link HttpServletRequest}
	 * @param directory		服务端存储上传文件的目录
	 * @return {"源文件名1": "服务器存储文件名称", "源文件名2": "服务器存储文件名称", ...}
	 */
	public static String saveRequestFiles(HttpServletRequest request, String directory) {
		DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");
        List<FileItem> fileList = null;
        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            return null;
        }
        
        Iterator<FileItem> it = fileList.iterator();
        String clientName = "";
        String serverName = "";
        String extName = "";
        
        JSONObject jsonObject = new JSONObject();
        while (it.hasNext()) {
            FileItem item = it.next();
            if (!item.isFormField()) {
                clientName = item.getName();
                long size = item.getSize();
                String type = item.getContentType();
                
                if (clientName == null || clientName.trim().equals("")) {
                    continue;
                }
                
                logger.info(clientName + " size: " + size + ", type: " + type);
                
                //扩展名格式：  
                if (clientName.lastIndexOf(".") >= 0) {
                    extName = clientName.substring(clientName.lastIndexOf("."));
                }
                
                serverName = UUID.randomUUID().toString();
                
                File saveFile = new File(directory + serverName + extName);
                try {
                    item.write(saveFile);
                    
                    jsonObject.put("OLD", clientName);
                    jsonObject.put("NEW", saveFile.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        return jsonObject.toString();
	}
}
