package nioserver.http;

import nioserver.IMessageReader;
import nioserver.IMessageReaderFactory;
import nioserver.MessageBuffer;

/**
 * Created by jjenkov on 18-10-2015.
 */
public class HttpMessageReaderFactory implements IMessageReaderFactory {

    public HttpMessageReaderFactory() {
    }

    @Override
    public IMessageReader createMessageReader() {
        return new HttpMessageReader();
    }
}
