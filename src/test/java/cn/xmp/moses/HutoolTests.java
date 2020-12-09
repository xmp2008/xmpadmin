package cn.xmp.moses;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HutoolTests {

    @Test
    public void contextLoads() {
        RSA rsa = new RSA();

//获得私钥
//        PrivateKey privateKey = rsa.getPrivateKey();
        System.out.println("rsa.getPrivateKeyBase64() = " + rsa.getPrivateKeyBase64());
//获得公钥
//        PublicKey publicKey = rsa.getPublicKey();
        System.out.println("rsa.getPublicKeyBase64() = " + rsa.getPublicKeyBase64());

//公钥加密，私钥解密
//        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
//        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);//公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);

//Junit单元测试
Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

//私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);

//Junit单元测试
//Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt2, CharsetUtil.CHARSET_UTF_8));


    }
    @Test
    public void contextLoads1() {
        String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKHJY3JgvdhXhjjSrxal67keOxitkaUUYV1kAh+8HO7Why0hxEr0+s2fw31MoTwM30rstiGD0B9q9/cdO9RNzBDyBMc3UwRctFt4DPzLlXHIFZrGAW+RK+XWv7AohVbWNGCAx3HBCZGK8vXGGL1SKN4cWgqGQTUqIxZDHN+koRMFAgMBAAECgYAFzSDhwxJLpafMddGNcFmpg6n+0op8buSm4qUo2if3cH3cYm+VeH9dzDLIVW0aqCOBnkdRoFZgKgfGNqgkjr2Yd1iwRX58Mkz6dPXuq/zielQ3wR3HbsQZcUSEtJJgyKIfBRE8vqH/qbkYEFd+xavEXK9T8qAaDmGUT8aAS0TggQJBANVRFKLHlRFUFpIkM5vfjqx+qVrfbLXsJXE2cEZrcBXh47nIT4tfPH7AbooPFwAT2tYqsiC68dSGaiFWVMdms5UCQQDCKLyVHakpshtKhiE3Kp2xMTAFOCyg6QQBiXhpSCkXlyetekCCDr04j5U9ZJlW0Qh0MJVn6mj5RhoCiAFpaQWxAkEAtEXeU6apgsh2frDbcJpgf8RXKTXyMFhOWDvybIC1a7jH9CSlnJoyjMfhYwlXnbgp056nhOOYJcwmet/zq8ROdQJADJXHY4MArAs3aaU4EfNZNVsS2cPCYMu5zr+yZmCfCUBq+fuqosOB0GC+M7SJm+8AtUdQ5+Nx2naIyRE3xjVggQJAXs+zSKeMmv7owqttaj48Z9J8D07WpOlBLGqGUw4+lB/lCqHxHSCjgPZE7ylX9jgebGRuPvRUcJuLnIUpzyIhnQ==";
        String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChyWNyYL3YV4Y40q8Wpeu5HjsYrZGlFGFdZAIfvBzu1octIcRK9PrNn8N9TKE8DN9K7LYhg9Afavf3HTvUTcwQ8gTHN1MEXLRbeAz8y5VxyBWaxgFvkSvl1r+wKIVW1jRggMdxwQmRivL1xhi9UijeHFoKhkE1KiMWQxzfpKETBQIDAQAB";

        RSA rsa = new RSA(PRIVATE_KEY, PUBLIC_KEY);
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        //公钥加密
        System.out.println("Base64.encode(encrypt) = " + Base64.encode(encrypt));
        String a = "fTcglF2KZvAND5V1LQ+y+1evOD1Y0Fcr6M4nZbWv3tKG/GSrKuT/Ofqu54TZvtOaB1DhuqYClulVd6mLKI85wRh25AcaxwFu1MeWUD2X2ygTqwUzxyhayGQmXy9tg3AjC6HQHGZpjx62TW2iU1smpX/yPfHJQk20sDbfqisHDik=";
        //私钥解密
        byte[] aByte = HexUtil.decodeHex(a);
        byte[] decrypt = rsa.decrypt(aByte, KeyType.PrivateKey);
        System.out.println("StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8) = " + StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));
//
////Junit单元测试
//Assert.assertEquals("虎头闯杭州,多抬头看天,切勿只管种地", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

    }
}
