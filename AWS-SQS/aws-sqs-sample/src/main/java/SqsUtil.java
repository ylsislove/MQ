
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

import java.util.*;


public class SqsUtil {

    private static AmazonSQS sqs;

    static {
        // 创建一个sqs对象，注意要指定Regions和你消息队列的地区一致
        sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    }

    /**
     * 禁用调用无参构造函数
     */
    private SqsUtil() {
    }

    /**
     * 根据Queue Name查询Url
     */
    public static String getQueueUrl(String queueName) {
        return sqs.getQueueUrl(queueName).getQueueUrl();
    }

    /**
     * 创建Queue
     */
    public static String createQueue(String queueName) {
        System.out.println("Creating a new SQS queue called " + queueName);
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        return sqs.createQueue(createQueueRequest).getQueueUrl();
    }

    /**
     * 发送消息
     */
    public static void sendMessage(String queueUrl, String message) {
        System.out.println("Sending a message to " + queueUrl);
        // 声明一个发送消息的请求
        SendMessageRequest request = new SendMessageRequest();
        // 指定要将消息发送到哪个队列
        request.withQueueUrl(queueUrl);
        // 设置消息内容
        request.withMessageBody(message);
        // 发送消息
        sqs.sendMessage(request);
    }

    /**
     * 接收消息
     */
    public static void receiveMessages(String queueUrl) {
        System.out.println("Receiving messages from " + queueUrl);
        // 声明一个接收消息的请求
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
        // 设置一些参数
        receiveMessageRequest.setMaxNumberOfMessages(5);
        receiveMessageRequest.withWaitTimeSeconds(10);
        // 声明一个存放消息的List
        List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        // 遍历List，打印消息内容
        for (Message message : messages) {
            System.out.println("Message: " + message.getBody());
            // 删除已被接收的消息
            System.out.println("Deleting a message.");
            sqs.deleteMessage(queueUrl, message.getReceiptHandle());
        }
    }

    /**
     * 删除Queue
     */
    public static void deleteQueue(String queueUrl) {
        System.out.println("Deleting the queue " + queueUrl);
        sqs.deleteQueue(queueUrl);
    }

}
