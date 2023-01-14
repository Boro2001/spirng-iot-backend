package com.example.spirngiotbackend.mqtt;

import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.amazonaws.services.iot.client.sample.sampleUtil.SampleUtil;
import com.amazonaws.services.iot.client.sample.sampleUtil.SampleUtil.KeyStorePasswordPair;
import com.example.spirngiotbackend.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MqttPubSubService {
    public static final String topic = "intopic";

    MqttPubSubService() throws AWSIotException {
        String clientEndpoint = "a3c8kw37dysoug-ats.iot.eu-west-1.amazonaws.com";   // use value returned by describe-endpoint --endpoint-type "iot:Data-ATS"
        String clientId = "esp_32";                              // replace with your own client ID. Use unique client IDs for concurrent connections.
        String certificateFile = "/Users/mikolajborowicz/Desktop/all-policy/68b43f4e830436b8cd5d246374376688b91f3c07fef6e7afd58baf2a2e26fc54-certificate.pem.crt";                       // X.509 based certificate file
        String privateKeyFile = "/Users/mikolajborowicz/Desktop/all-policy/68b43f4e830436b8cd5d246374376688b91f3c07fef6e7afd58baf2a2e26fc54-private.pem.key";
        KeyStorePasswordPair pair = SampleUtil.getKeyStorePasswordPair(certificateFile, privateKeyFile);
        AWSIotMqttClient client = new AWSIotMqttClient(clientEndpoint, clientId, pair.keyStore, pair.keyPassword);
        client.connect();
        System.out.println("connected");

        client.publish(topic, AWSIotQos.QOS0, "helo");
        String topicName = "intopic";
        AWSIotQos qos = AWSIotQos.QOS0;

        Topic topic = new Topic(topicName, qos);
        client.subscribe(topic);

    }
    public void publishMessage(String message){
    }
}
