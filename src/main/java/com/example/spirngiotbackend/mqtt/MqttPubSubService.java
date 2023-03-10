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
        String clientEndpoint = "ag8bpew4rvau3-ats.iot.eu-west-1.amazonaws.com";   // use value returned by describe-endpoint --endpoint-type "iot:Data-ATS"
        String clientId = "spring-app";                              // replace with your own client ID. Use unique client IDs for concurrent connections.
        String certificateFile = "/home/ec2-user/credentials2/2d801a703b265c3c2b40e6eaec6261b8b9acd1bf949eaad76ec741fd38d886c2-certificate.pem.crt";                       // X.509 based certificate file
        String privateKeyFile = "/home/ec2-user/credentials2/2d801a703b265c3c2b40e6eaec6261b8b9acd1bf949eaad76ec741fd38d886c2-private.pem.key";
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
