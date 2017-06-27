package edu.unq.desapp.groupA.backend.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class JSONDateSerialize extends JsonSerializer<Date> {
	
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    
    @Override
    public void serialize(Date t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        String formattedDate = dateFormat.format(t);
        jg.writeString(formattedDate);        
    }
}