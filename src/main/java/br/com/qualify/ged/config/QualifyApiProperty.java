package br.com.qualify.ged.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("qualify")
public class QualifyApiProperty {

    private String originPermitida = "http://localhost:4200";

    private final Seguranca seguranca = new Seguranca();

    private final S3 s3 = new S3();

    private final Aws aws = new Aws();

    public Aws getAws(){return aws;}

    public S3 getS3() {
        return s3;
    }

    private final Mail mail = new Mail();

    public Mail getMail() {
        return mail;
    }

    public Seguranca getSeguranca() {
        return seguranca;
    }

    public String getOriginPermitida() {
        return originPermitida;
    }

    public void setOriginPermitida(String originPermitida) {
        this.originPermitida = originPermitida;
    }

    public static class S3 {

        private String bucketName;

        private String region;

        private String endpoint;

        private String accessKeyId;

        private String secretAccessKey;

        public String getBucketName() {
            return bucketName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getSecretAccessKey() {
            return secretAccessKey;
        }

        public void setSecretAccessKey(String secretAccessKey) {
            this.secretAccessKey = secretAccessKey;
        }

    }

    public static class Aws {

        private String env;

        public String getEnv() {
            return env;
        }

        public void setEnv(String env) {
            this.env = env;
        }
    }

    public static class Seguranca {
        private List<String> redirectsPermitidos;
        private String authServerUrl;

        public List<String> getRedirectsPermitidos() {
            return redirectsPermitidos;
        }

        public void setRedirectsPermitidos(List<String> redirectsPermitidos) {
            this.redirectsPermitidos = redirectsPermitidos;
        }

        public String getAuthServerUrl() {
            return authServerUrl;
        }

        public void setAuthServerUrl(String authServerUrl) {
            this.authServerUrl = authServerUrl;
        }
    }

    public static class Mail {

        private String host;

        private Integer port;

        private String username;

        private String password;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
