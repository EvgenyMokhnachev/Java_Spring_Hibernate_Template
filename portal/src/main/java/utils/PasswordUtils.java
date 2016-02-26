package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class PasswordUtils {
    private static final String globalSlat = "@#$d!w13";
    private static final int iteration = 30;

    public static String GetPasswordHash(String password, String slat) {
        String buff = md5Hash(sha1Hash(password + slat) + globalSlat);
        for (int i = 0; i <= iteration; i++)
            if (i % 2 == 0) buff = md5Hash(buff);
            else buff = sha1Hash(buff);
        buff = sha1Hash(buff);
        return buff;
    }

    public static boolean CheckPassword(String password, String slat, String hash) {
        return GetPasswordHash(password, slat).equals(hash);
    }

    public static String newSlat() {
        return UUID.randomUUID().toString();
    }

    public static String md5Hash(String str) {
        MessageDigest md5;
        StringBuffer hexString;
        hexString = new StringBuffer();
        try {
            md5 = MessageDigest.getInstance("md5");
            md5.reset();
            md5.update(str.getBytes());
            byte messageDigest[] = md5.digest();
            for (byte aMessageDigest : messageDigest) hexString.append(Integer.toHexString(0xFF & aMessageDigest));
        } catch (NoSuchAlgorithmException e) {
            return e.toString();
        }
        return hexString.toString();
    }

    public static String sha1Hash(String password) {
        MessageDigest mDigest;
        try {
            mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(password.getBytes());
            StringBuffer sb;
            sb = new StringBuffer();
            for (byte aResult : result) sb.append(Integer.toString((aResult & 0xff) + 0x100, 16).substring(1));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String text, String keyWord) {
        byte[] arr = text.getBytes();
        byte[] keyArr = keyWord.getBytes();
        byte[] result = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) result[i] = (byte) (arr[i] ^ keyArr[i % keyArr.length]);
        return new String(Base64.getEncoder().encode(result));
    }

    public static String decrypt(String in, String keyWord) {
        byte[] text = Base64.getDecoder().decode(in.getBytes());
        byte[] result = new byte[text.length];
        byte[] keyArr = keyWord.getBytes();
        for (int i = 0; i < text.length; i++) result[i] = (byte) (text[i] ^ keyArr[i % keyArr.length]);
        return new String(result);
    }
}
