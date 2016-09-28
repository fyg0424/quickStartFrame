package com.QuickStartFrame.management.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/*
 *采用spring提供的上传文件的方法
 */
@Controller
@RequestMapping(value = "/Upload")
public class UploadFileController {
	
	@RequestMapping(value = {"upload.html","uploadFilePage"}, method = RequestMethod.GET)
	public String UploadFilePage(){
		return "upload";
	}
	
	/*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping(value = "UploadFile",method = RequestMethod.POST)
    public String  springUpload(HttpServletRequest request) throws IllegalStateException, IOException
    {
         long  startTime=System.currentTimeMillis();
         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
           //获取multiRequest 中所有的文件名
            Iterator<String> iter=multiRequest.getFileNames();
             
            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {   long time = System.currentTimeMillis();
                    String path="E:/springUpload/"+time+file.getOriginalFilename();
                    File saveFile = new File(path);
                    if(!saveFile.getParentFile().exists()){
                    	saveFile.getParentFile().mkdirs();
                    }
                    //上传
                    file.transferTo(saveFile);
                }
                 
            }
           
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
    return "index"; 
    }

}
