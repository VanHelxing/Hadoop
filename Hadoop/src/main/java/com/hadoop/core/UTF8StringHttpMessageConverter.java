package com.hadoop.core;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;

/**
 * 
 * SpringMVC的@ResponseBody注解当返回值是String时会乱码,原因是因为字符转化器固定了转换编码为"ISO-8859-1".
 * 解决方式: 重写一个字符转化器,固定编码为"UTF-8"
 * 
 * @author yang
 *
 */

public class UTF8StringHttpMessageConverter extends AbstractHttpMessageConverter<String> {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	private final List<Charset> availableCharsets;
	private boolean writeAcceptCharset  = true;
	
	public UTF8StringHttpMessageConverter() {
		super(new MediaType("text", "plain", DEFAULT_CHARSET), MediaType.ALL);  
        this.availableCharsets = new ArrayList<Charset>(Charset.availableCharsets().values());  
	}
	
	public void setWriteAcceptCharset(boolean writeAcceptCharset) {  
        this.writeAcceptCharset = writeAcceptCharset;  
    }  
	
	@Override
	protected boolean supports(Class<?> clazz) {
		return String.class.equals(clazz);  
	}

	@Override
	protected String readInternal(Class<? extends String> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		Charset charset = getContentTypeCharset(inputMessage.getHeaders().getContentType());  
        return FileCopyUtils.copyToString(new InputStreamReader(inputMessage.getBody(), charset)); 
	}
	
	
	@Override
	protected Long getContentLength(String t, MediaType contentType) throws IOException {
		Charset charset = getContentTypeCharset(contentType);  
        try {  
            return (long) t.getBytes(charset.name()).length;  
        }  
        catch (UnsupportedEncodingException ex) {  
            // should not occur  
            throw new InternalError(ex.getMessage());  
        }  
	}

	@Override
	protected void writeInternal(String t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		
	}
	
	protected List<Charset> getAcceptedCharsets() {  
        return this.availableCharsets;  
    }  
  
    private Charset getContentTypeCharset(MediaType contentType) {  
        if (contentType != null && contentType.getCharSet() != null) {  
            return contentType.getCharSet();  
        }  
        else {  
            return DEFAULT_CHARSET;  
        }  
    }  

}
