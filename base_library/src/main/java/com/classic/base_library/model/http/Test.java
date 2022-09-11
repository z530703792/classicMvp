package com.classic.base_library.model.http;

import android.util.Log;


import com.classic.base_library.App.App;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Administrator on 2019/3/28.
 */

public class Test {

    public static X509TrustManager[] getTrustManager() {
        return new X509TrustManager[]{
                new X509TrustManager() {

                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        if (chain == null) {
                            throw new CertificateException("证书错误");
                        }
                        if (chain.length < 0) {
                            throw new CertificateException("证书错误");
                        }
                        for (X509Certificate cer : chain) {
                            cer.checkValidity();
                            try {
                                cer.verify(getSslPublicKey());
                            } catch (NoSuchAlgorithmException e) {
                                e.printStackTrace();
                            } catch (InvalidKeyException e) {
                                e.printStackTrace();
                            } catch (NoSuchProviderException e) {
                                e.printStackTrace();
                            } catch (SignatureException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        };
    }

    public static PublicKey getSslPublicKey() {
        PublicKey key = null;
        try {
            BufferedInputStream cerInput = new BufferedInputStream(App.getInstance().getAssets().open("hm.crt"));
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(cerInput);
            key = x509Certificate.getPublicKey();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static HostnameVerifier getSafeHostVerfier() {
        return new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession session) {
                Log.e("HostName", "verify: " + hostname);
                return true;
            }
        };
    }
}
