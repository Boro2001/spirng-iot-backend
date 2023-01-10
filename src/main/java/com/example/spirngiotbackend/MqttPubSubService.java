package com.example.spirngiotbackend;

import org.springframework.stereotype.Service;


@Service
public class MqttPubSubService {

    MqttPubSubService() {
        String clientEndpoint = "<prefix>-ats.iot.<region>.amazonaws.com";   // use value returned by describe-endpoint --endpoint-type "iot:Data-ATS"
        String clientId = "<unique client id>";                              // replace with your own client ID. Use unique client IDs for concurrent connections.
        String certificateFile = "<certificate file>";                       // X.509 based certificate file
        String privateKeyFile = "<private key file>";
        KeyStorePasswordPair pair = SampleUtil.getKeyStorePasswordPair(certificateFile, privateKeyFile);
        AWSIotMqttClient client = new AWSIotMqttClient(clientEndpoint, clientId, pair.keyStore, pair.keyPassword);
    }
    public void publishMessage(){

    }
}
