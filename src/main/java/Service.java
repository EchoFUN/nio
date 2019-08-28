import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class Service {

    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer writeButter = ByteBuffer.allocate(1024);

    private Selector selector;

    public Service() throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        ServerSocket serverSocket = socketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8080));
        this.selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }


    public static void main(String[] args) {
        System.out.println("hi~");

        try {
            new Service().boot();
        } catch (IOException e) {
            ;
        }
    }

    public void boot() throws IOException {
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {

                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {

                }

                if (selectionKey.isReadable()) {

                }

                if (selectionKey.isWritable()) {

                }
            }
        }
    }
}
