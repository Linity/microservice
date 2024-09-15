package cn.com.ljw.base;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Steph_Lin
 * @date 2020/12/18
 */
public class TokenUtil {

    public static String generateToken(String userId) {
        String tokenMd5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest(userId.getBytes());
            BASE64Encoder base = new BASE64Encoder();
            tokenMd5 = base.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return tokenMd5;
    }

}
