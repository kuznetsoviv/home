package ru.kuznetsoviv.httpconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.stream.Collectors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static java.net.Proxy.Type.HTTP;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Base64.getEncoder;

public class HttpConnectionWrapper {

    public static void main(String[] args) throws IOException {
        IntegrationUtils.disableSSLCertificateChecking();
        HttpURLConnection connection = getConnection();
        connection.connect();
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                System.out.println(reader.lines().collect(Collectors.joining()));
            }
        } else {
            throw new IllegalStateException(String.format("Произошла ошибка запроса к сервису: %s, код ответа: %s",
                    connection.getResponseMessage(), connection.getResponseCode()));
        }
    }

    private static HttpURLConnection getConnection() throws IOException {
        URL url = new URL("https://rid.bplan.sev.gov.ru/backdoor/rest/integration/statetask/calc");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(
                new Proxy(HTTP, new InetSocketAddress("proxy.krista.ru", 8080))
        );

        Authenticator.setDefault(new SimpleAuthenticator("kuznetsoviv", "fDl%51uWaaob"));
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setConnectTimeout(Integer.MAX_VALUE);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Basic " + getAuthorization());
        connection.setRequestProperty("Content-Type", "application/json");
        return connection;
    }

    private static String getAuthorization() {
        String user = "depfin";
        String password = "1234";
        return getEncoder().encodeToString((user + ":" + password).getBytes(UTF_8));
    }

    static public class SimpleAuthenticator extends Authenticator {
        private String username;
        private String password;

        public SimpleAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password.toCharArray());
        }

    }

    static public class IntegrationUtils {

        /**
         * Отключить проверку сертификатов.
         */
        public static void disableSSLCertificateChecking() {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
                    // Not implemented
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
                    // Not implemented
                }
            }};
            try {
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                HostnameVerifier allHostsValid = (hostname, session) -> true;
                HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

}
