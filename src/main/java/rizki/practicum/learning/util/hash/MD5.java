package rizki.practicum.learning.util.hash;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MD5 {
    /**
     * generate md5 value
     * @param md5 input data
     * @return hashed value
     */
    public static String generate(String md5) {
        md5 += new Date().getTime();
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
