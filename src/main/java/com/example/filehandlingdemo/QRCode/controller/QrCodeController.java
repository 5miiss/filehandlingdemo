package com.example.filehandlingdemo.QRCode.controller;

import com.example.filehandlingdemo.QRCode.Dto.QrData;
import com.example.filehandlingdemo.QRCode.utils.QrCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/QR")
public class QrCodeController {

    @PostMapping(path = "/encodedText")
    public Map<String,String> base64(@Valid @RequestBody QrData qrData)  {
        return Map.of("EncodedText", QrCode.generateBase64Code(qrData));
    }
}