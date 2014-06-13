package matuszek.typer.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

@Provider
@Produces("application/json")
public class JsonProvider implements MessageBodyWriter<Object> {
    @Override
    public long getSize(Object obj, Class type, Type genericType,
            Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class type, Type genericType,
            Annotation annotations[], MediaType mediaType) {
        return true;
    }

    @Override
    public void writeTo(Object target, Class type, Type genericType,
            Annotation[] annotations, MediaType mediaType,
            MultivaluedMap httpHeaders, OutputStream outputStream)
            throws IOException {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    	ObjectMapper om = new ObjectMapper();
    	
    	DeserializationConfig deserConfig = om.getDeserializationConfig().withDateFormat(sdf);
    	SerializationConfig serConfig = om.getSerializationConfig().withDateFormat(sdf);
    	
    	om.setSerializationConfig(serConfig);
    	om.setDeserializationConfig(deserConfig);
    	
    	System.out.println("Target: " + target);
    	
        om.writeValue(outputStream, target);
        
    }
}