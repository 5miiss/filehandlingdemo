package com.example.filehandlingdemo.utils;

import com.example.filehandlingdemo.config.WebConfigurer;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Slf4j
public class FileServiceUtil {
    public static String saveProfileImage(String sourceData ,String UUID , String imgName) throws IOException {

        String imageString = sourceData;// parts[1];

        // create a buffered image
        BufferedImage image = null;
        byte[] imageByte;

        Base64.Decoder decoder = Base64.getDecoder();
        imageByte = decoder.decode(imageString);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);

        image = ImageIO.read(bis);
        bis.close();
        log.info("img converted");
        // write the image to a file
        File folder = new File(WebConfigurer.uploadDirectory +"/"+UUID);
        if(!folder.exists())
             folder.mkdir();
        File outputfile = new File(WebConfigurer.uploadDirectory +"/"+UUID +"/"+imgName+ ".png");
        log.info("file created");

        ImageIO.write(image, "png", outputfile);
        log.info("img saved");


        return WebConfigurer.uploadDirectory  +"/"+UUID +"/"+imgName+ ".png";
    }
}
