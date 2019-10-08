package slef.defined.protocol;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import slef.defined.protocol.model.Vacation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @author: 程泰恒
 */
@Slf4j
@Configuration
@ServletComponentScan
public class ProtocolApplicationMvcCnf implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        new MediaType("application", "x-chth", StandardCharsets.UTF_8);
        registry.addStatusController("healthz", HttpStatus.OK);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new HttpMessageConverter<Chth>() {

                           @Override
                           public boolean canRead(Class<?> aClass, MediaType mediaType) {
                               return ClassUtils.isAssignable(Chth.class, aClass) && mediaType.equalsTypeAndSubtype(new MediaType("application", "x-chth"));
                           }

                           @Override
                           public boolean canWrite(Class<?> aClass, MediaType mediaType) {
                               return ClassUtils.isAssignable(Chth.class, aClass) && mediaType.equalsTypeAndSubtype(new MediaType("application", "x-chth"));
                           }

                           @Override
                           public List<MediaType> getSupportedMediaTypes() {
                               return Collections.singletonList(new MediaType("application", "x-chth"));
                           }

                           @Override
                           public Chth read(Class<? extends Chth> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
                               String string = IOUtils.toString(httpInputMessage.getBody(), StandardCharsets.UTF_8);
                               String[] parts = string.split("\\.");

                               return new Vacation(Long.parseLong(parts[0]), parts[1]);
                           }

                           @Override
                           public void write(Chth chth, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
                               String string = httpOutputMessage.getBody().toString();
                               log.debug("outputString = {}", string);
                           }
                       }
        );
    }

}
