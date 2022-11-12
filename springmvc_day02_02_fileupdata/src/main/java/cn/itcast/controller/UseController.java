package cn.itcast.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @author fail
 * @create 2021-03-21 14:43
 */
@Controller
@RequestMapping("/user")
public class UseController {

    /**
     * SpringMVC跨服务器方式的文件上传
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fileupload3")
    public String fileupload3(MultipartFile upload) throws Exception {
        System.out.println("SpringMVC跨服务器方式的文件上传...");
        // 定义图片服务器的请求路径
        String path = "http://localhost:9090/day02_springmvc5_02image/uploads/";
        // 获取到上传文件的名称
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        // 把文件的名称唯一化
        filename = uuid + "_" + filename;
        // 向图片服务器上传文件
        // 创建客户端对象
        Client client = Client.create();
        // 连接图片服务器
        WebResource webResource = client.resource(path + filename);
        // 上传文件
        webResource.put(upload.getBytes());
        return "success";
    }


    /**
     * MultipartFile upload要和表单中的name一样
     * SpringMVC传统方式文件上传
     */
    @RequestMapping("/MultipartFile")
    public String multipartFile(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("文件上传...");

        String path = "E:\\Java练习保存目录\\ssm_study_springMVC\\springmvc_day02_02_fileupdata\\src\\main\\webapp\\images\\";
        File file1 = new File(path);

        if (!file1.exists()) {
            file1.mkdirs();
        }

        String filename = upload.getOriginalFilename();
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        upload.transferTo(new File(path, s + filename));

        return "success";

    }

    /**
     * 以前的方式
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileupdata")
    public String test(HttpServletRequest request) throws Exception {
        System.out.println("文件上传...");

        String path = "E:\\Java练习保存目录\\ssm_study_springMVC\\springmvc_day02_02_fileupdata\\src\\main\\webapp\\images\\";
        File file1 = new File(path);

        if (!file1.exists()) {
            file1.mkdirs();
        }

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems = servletFileUpload.parseRequest(request);
        for (FileItem i : fileItems) {

            if (i.isFormField()) {

            } else {
                String filename = i.getName();
                String replace = UUID.randomUUID().toString().replace("-", "");
                i.write(new File(path, replace + filename));
                i.delete();

            }
        }

        return "success";
    }
}
