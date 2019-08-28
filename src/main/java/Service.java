import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import database.DBUtils;
import database.PostModel;
import nioserver.IMessageProcessor;
import nioserver.Message;
import nioserver.Server;
import nioserver.http.HttpMessageReaderFactory;

/**
 *
 */
public class Service {

    public static void main(String[] args) throws IOException {
        PostModel postModel = new PostModel();
        IMessageProcessor messageProcessor = (request, writeProxy) -> {
            System.out.println("Message Received from socket: " + request.socketId);
            Map<String, String> post = postModel.fetchPostByID(7);

            Message response = writeProxy.getMessage();
            response.socketId = request.socketId;
            String feedback = "HTTP/1.1 200 OK\r\nContent-Length: 38\r\nContent-Type: text/html\r\n\r\n<html><body>" + post.get("date") + "</body></html>";
            try {
                response.writeToMessage(feedback.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                ;
            }
            writeProxy.enqueue(response);
        };

        DBUtils.inst().init();
        Server server = new Server(9999, new HttpMessageReaderFactory(), messageProcessor);
        server.start();
    }
}
