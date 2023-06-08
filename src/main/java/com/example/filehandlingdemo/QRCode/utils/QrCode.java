package com.example.filehandlingdemo.QRCode.utils;

import com.example.filehandlingdemo.QRCode.Dto.QrData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Component
public class QrCode {
    public static String toHex(Integer val){
        return  StringUtils.leftPad(Integer.toHexString(val), 2, "0");
//        String hexString = Integer.toHexString(val);
//        if (hexString.length() < 2) {
//            hexString = "0" + hexString;
//        }
//        return hexString;
    }
    private static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
//        StringBuilder sb = new StringBuilder();
//
//        // Process the hex string in pairs
//        for (int i = 0; i < hexStr.length(); i += 2) {
//            // Get the hex value of the current pair
//            String hexPair = hexStr.substring(i, i + 2);
//
//            // Convert the hex value to decimal
//            int decimalValue = Integer.parseInt(hexPair, 16);
//
//            // Convert the decimal value to ASCII character
//            char asciiChar = (char) decimalValue;
//
//            // Append the ASCII character to the result
//            sb.append(asciiChar);
//        }
//
//        return sb.toString();
    }

    private static String base64Encoder(String text) {
//       return Base64.getEncoder().encodeToString(text.getBytes());
        byte[] inputBytes = text.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(inputBytes);
    }
    public static int getByteLength(String inputString) {
        byte[] bytes = inputString.getBytes(StandardCharsets.UTF_8);
        return bytes.length;
    }
    public static void main(String [] args){
        String sellerName="أستاذ الحسين . # 2 5 a";
        Long vatNum = 311090713500003L;
        LocalDateTime time = LocalDateTime.now();
        Double totalPrice = 173.00;
        Double vat = 22.57;

//        String text =generateBase64Code(sellerName,vatNum,time,totalPrice,vat);


    }
    public static String generateBase64Code(QrData qrData){
        String sellerName = qrData.getSellerName();
        String vatNum = qrData.getVatNum().toString();
        String time = qrData.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss'Z'"));
        String totalPrice = qrData.getTotalPrice().toString();
        String vat = qrData.getVat().toString();
        return base64Encoder(hexToAscii("01")+hexToAscii(toHex(getByteLength(sellerName)))+sellerName
                +(hexToAscii("02"))+hexToAscii(toHex(getByteLength(vatNum)))+vatNum
                +(hexToAscii("03"))+hexToAscii(toHex(getByteLength(time)))+ time
                +(hexToAscii("04"))+hexToAscii(toHex(getByteLength(totalPrice)))+totalPrice
                +(hexToAscii("05"))+hexToAscii(toHex(getByteLength(vat)))+vat);
    }



}
