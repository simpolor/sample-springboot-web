package io.simpolor.http.controller;

import io.simpolor.http.util.FileUtil;
import io.simpolor.http.util.RandomUtil;
import io.simpolor.http.util.RequestUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/receiver")
public class ReceiverController {

    @RequestMapping("/receiver/html/text")
    public ModelAndView receiverHtmlText(HttpServletRequest request) {

        System.out.println("-- ReceiverController > receiverHtmlText");

        ModelAndView mav = new ModelAndView();

        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String job = request.getParameter("job");
        System.out.println("> name : "+name+" / age : "+age+" / job : "+job);

        mav.addObject("name", name);
        mav.addObject("age", age);
        mav.addObject("job", job);


        mav.setViewName("receiverHtmlText");

        return mav;
    }

    @RequestMapping("/receiver/html/file")
    public ModelAndView receiverHtmlFile(HttpServletRequest request, @RequestParam("upload_file") MultipartFile file) throws IOException {

        System.out.println("-- ReceiverController > receiverHtmlFile");

        ModelAndView mav = new ModelAndView();

        String name = request.getParameter("name");
        System.out.println("> name : "+name);

        mav.addObject("name", name);

        if(file != null && !file.isEmpty()) {
            String filePath = "upload";
            String uploadFilePath = RequestUtil.getRealPath(request, filePath);
            File uploadFile = new File(uploadFilePath);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }

            String randomStr = RandomUtil.getUpperCase(12);
            System.out.println("> randomStr : "+randomStr);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String currentDateTime = sdf.format(new Date());
            System.out.println("> currentDateTime : "+currentDateTime);

            String orgFileName = file.getOriginalFilename();
            String orgFileExtension = FileUtil.getFileExtension(orgFileName);
            String saveFileName = randomStr.concat("_").concat(currentDateTime);
            String saveFilePath = uploadFilePath.concat(File.separator).concat(saveFileName).concat(orgFileExtension);
            String saveViewPath = File.separator.concat(filePath).concat(File.separator).concat(saveFileName).concat(orgFileExtension);

            System.out.println("> orgFileName : "+orgFileName);
            System.out.println("> orgFileExtension : "+orgFileExtension);
            System.out.println("> saveFileName : "+saveFileName);
            System.out.println("> saveFilePath : "+saveFilePath);
            System.out.println("> saveViewPath : "+saveViewPath);

            try {
                file.transferTo(new File(saveFilePath));
            }catch (Exception e) {
                e.printStackTrace();
            }

            mav.addObject("orgFileName", orgFileName);
            mav.addObject("saveViewPath", saveViewPath);
        }


        mav.setViewName("receiverHtmlFile");

        return mav;
    }

    @RequestMapping("/http/file")
    public String receiverHttpFile(MultipartHttpServletRequest request) throws IOException {

        System.out.println("-- ReceiverController > receiverHttpFile");

        String result = "fail";

        String name = request.getParameter("name");
        System.out.println("> name : "+name);

        MultipartFile file = request.getFile("upload_file");
        if(file != null && !file.isEmpty()) {
            String filePath = "upload";
            String uploadFilePath = RequestUtil.getRealPath(request, filePath);
            File uploadFile = new File(uploadFilePath);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }

            String randomStr = RandomUtil.getUpperCase(12);
            System.out.println("> randomStr : "+randomStr);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String currentDateTime = sdf.format(new Date());
            System.out.println("> currentDateTime : "+currentDateTime);

            String orgFileName = file.getOriginalFilename();
            String orgFileExtension = FileUtil.getFileExtension(orgFileName);
            String saveFileName = randomStr.concat("_").concat(currentDateTime);
            String saveFilePath = uploadFilePath.concat(File.separator).concat(saveFileName).concat(orgFileExtension);
            String saveViewPath = File.separator.concat(filePath).concat(File.separator).concat(saveFileName).concat(orgFileExtension);

            System.out.println("> orgFileName : "+orgFileName);
            System.out.println("> orgFileExtension : "+orgFileExtension);
            System.out.println("> saveFileName : "+saveFileName);
            System.out.println("> saveFilePath : "+saveFilePath);
            System.out.println("> saveViewPath : "+saveViewPath);

            try {
                file.transferTo(new File(saveFilePath));
                result = "success";
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @RequestMapping("/http/array")
    public String receiverHttpArray(HttpServletRequest request){

        System.out.println("-- ReceiverController > receiverHttpArray");

        String result = "fail";

        String authorization = request.getHeader("Authorization");
        System.out.println("-- authorization : " + authorization);

        String name = request.getParameter("name");
        String[] imageUrls = request.getParameterValues("image_urls");
        // String[] imagesUrl = request.getParameterValues("images_url");
        System.out.println("-- name : " + name);
        for(String imageUrl : imageUrls) {
            System.out.println("-- imageUrls : " + imageUrl);
        }
        // for(String imageUrl : imagesUrl) {
        // 	System.out.println("-- imagesUrl : " + imageUrl);
        // }




        return result;
    }

    @RequestMapping("/receiver/read/file")
    public String receiverReadFile(HttpServletRequest request){

        System.out.println("-- ReceiverController > receiverReadFile");

        String result = "fail";

        try {
            String imgUrl = "http://www.mkyong.com/image/mypic.jpg";
            URL url = new URL(imgUrl);
            String fileName = imgUrl.substring( imgUrl.lastIndexOf('/')+1, imgUrl.length() );
            String fileExt = imgUrl.substring( imgUrl.lastIndexOf('.')+1, imgUrl.length() );

            String filePath = "upload";
            String uploadFilePath = request.getServletContext().getRealPath(filePath);
            System.out.println("-- uploadFilePath=" + uploadFilePath);

            // 디렉토리 유무확인 및 생성
            File uploadFile = new File(uploadFilePath);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }

            BufferedImage image = ImageIO.read(url);
            ImageIO.write(image, fileExt, new File(uploadFilePath + File.separator + fileName));

            result = "success";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/receiver/async/get/{name}")
    public String receiverAsyncGet(HttpServletRequest request, @PathVariable String name){

        System.out.println("-- ReceiverController > receiverAsyncGet");

        System.out.println("-- name : "+name);

        String result = "fail";

        return result;
    }

    @RequestMapping("/receiver/sync/post")
    public String receiverSyncPost(HttpServletRequest request){

        System.out.println("-- ReceiverController > receiverSyncPost");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        System.out.println("-- Current time : "+fmt.format(new Date()));

        String authorization = request.getHeader("Authorization");
        String contentType = request.getHeader("Content-type");
        System.out.println("-- authorization : "+authorization);
        System.out.println("-- contentType : "+contentType);

        String result = "fail";

        return result;
    }

    @RequestMapping("/receiver/async/post")
    public String receiverAsyncPost(HttpServletRequest request){

        System.out.println("-- ReceiverController > receiverAsyncPost");

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        System.out.println("-- Current time : "+fmt.format(new Date()));

        String authorization = request.getHeader("Authorization");
        String contentType = request.getHeader("Content-type");
        System.out.println("-- authorization : "+authorization);
        System.out.println("-- contentType : "+contentType);

        String result = "fail";

        return result;
    }

    @RequestMapping("/receiver/string/file")
    public String receiverStringFile(HttpServletRequest request) throws IOException {

        // 작업 중..
        System.out.println("-- ReceiverController > receiverStringFile");

        String result = "fail";

        String filePath = "upload";
        String uploadFilePath = RequestUtil.getRealPath(request, filePath);
        File uploadFile = new File(uploadFilePath);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }

        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
            String name = multipartRequest.getParameter("name");
            String stringFile = multipartRequest.getParameter("string_file");

            System.out.println("> name : "+name);
            System.out.println("> stringFile : "+stringFile);

            byte[] byteFile = Base64.decodeBase64(stringFile);
            String copyFilePath = request.getServletContext().getRealPath(filePath.concat(File.separator).concat("abcde.jpg"));
            FileOutputStream fos = new FileOutputStream(new File(copyFilePath));
            fos.write(byteFile);

            result = "success";

        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
