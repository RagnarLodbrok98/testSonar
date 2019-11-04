package tp.lumiere.capteurPresence;

import java.util.Random;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

/**
 * Hello world!
 *
 */
public class App 
{
	private static MqttClient clientPub;
    public static void main( String[] args ) throws MqttSecurityException, MqttException, InterruptedException
    {
    	clientPub = new MqttClient("tcp://localhost:1883", "capteurPresence");
        clientPub.connect();
        
        Random rand = new Random();

        int i=0;
        while(i<5000)
        {
            Thread.sleep(1000);
            int presence = rand.nextInt(2);        // Entre 0 et 30
            String content = String.valueOf(presence);
            MqttMessage message = new MqttMessage();
            message.setPayload(content.getBytes());
            clientPub.publish("capteurPresence", message);
            i--;
        }
        clientPub.disconnect();
    }
}
