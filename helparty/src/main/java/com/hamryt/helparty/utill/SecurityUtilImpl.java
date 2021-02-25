package com.hamryt.helparty.utill;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SecurityUtilImpl implements SecurityUtil {

    private SecurityUtilImpl() {
    }

    public String encryptSha256(String data) {
        String retVal = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes());

            byte[] byteData = md.digest();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            retVal = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("SHA256 Error:" + e.toString());
            throw new RuntimeException("SHA256 암호화 ERROR!", e);
        }
        return retVal;
    }

}
