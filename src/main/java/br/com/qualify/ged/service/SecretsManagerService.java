//package br.com.qualify.ged.service;
//
//import com.amazonaws.services.secretsmanager.AWSSecretsManager;
//import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
//import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SecretsManagerService {
//
//    @Autowired
//    private AWSSecretsManager awsSecretsManager;
//
//    @Value("${app.aws.sm.name")
//    private String secretName;
//
//    public String getSecret(String key)  {
//        try {
//            GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
//            GetSecretValueResult getSecretValueResult = awsSecretsManager.getSecretValue(getSecretValueRequest);
//            JSONObject jsonObject=  new JSONObject(getSecretValueResult.getSecretString());
//            return jsonObject.get(key).toString();
//
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
