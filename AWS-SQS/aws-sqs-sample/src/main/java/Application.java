import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Apple_Coco
 */
public class Application {

    public static void main(String[] args) {

        // 打印一个已存在的队列的URL
        System.out.println(SqsUtil.getQueueUrl("queue_test.fifo"));

        // 创建一个标准类型的队列
        String queueUrl = SqsUtil.createQueue("test");

        // 发送Message
        for (int i = 0; i < 6; i++) {
            SqsUtil.sendMessage(queueUrl, "Hello world " + i);
        }

        // 接收Message
        for (int i = 0; i < 6; i++) {
            SqsUtil.receiveMessages(queueUrl);
        }

        // 删除刚才创建的队列
        SqsUtil.deleteQueue(queueUrl);

    }

}
