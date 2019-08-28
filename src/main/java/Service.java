import java.io.IOException;
import java.util.Map;

import database.DBUtils;
import database.PostModel;
import nioserver.IMessageProcessor;
import nioserver.Server;
import nioserver.http.HttpMessageReaderFactory;

/**
 *
 */
public class Service {
    static final String httpResponse = "HTTP/1.1 200 OK\r\nContent-Length: 38\r\nContent-Type: text/html\r\n\r\n<html><body>Hello World!</body></html>";

    public static void main(String[] args) throws IOException {
        byte[] httpResponseBytes = httpResponse.getBytes("UTF-8");

        PostModel postModel = new PostModel();
        IMessageProcessor messageProcessor = (request, writeProxy) -> {
            System.out.println("Message Received from socket: " + request.socketId);

            Map<String, String> post = postModel.fetchPostByID(7);


            /*
            Message response = writeProxy.getMessage();
            response.socketId = request.socketId;
            response.writeToMessage(httpResponseBytes);

            writeProxy.enqueue(response);
            */
        };

        DBUtils.inst().init();

        Server server = new Server(9999, new HttpMessageReaderFactory(), messageProcessor);


        server.start();
    }
}
