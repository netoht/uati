package io.github.netoht.uati.collector.resource;

import io.github.netoht.uati.collector.domain.ResponseMessage;
import io.github.netoht.uati.collector.service.CollectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.BiFunction;

import static org.apache.commons.lang3.StringUtils.prependIfMissingIgnoreCase;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.status;

/**
 * Created by neto on 22/01/17.
 */
@RestController
public class CollectorResource {

    private final Logger LOGGER = LoggerFactory.getLogger(CollectorResource.class);

    private static final BiFunction<HttpStatus, String, ResponseEntity<ResponseMessage>> HttpMessage = (status, msg) ->
            status(status)
                    .body(new ResponseMessage()
                    .setCode(status.value())
                    .setMessage(msg));

    @Autowired
    private CollectService collectService;

    @RequestMapping("/collect")
    public ResponseEntity<ResponseMessage> collect(@RequestParam(value="hashtag") String hashtag) {
        try {
            if (StringUtils.isEmpty(hashtag)) {
                return HttpMessage.apply(BAD_REQUEST, "Invalid hashtag");
            }
            hashtag = prependIfMissingIgnoreCase(hashtag, "#");
            collectService.collectTweetsFrom(hashtag);
            return noContent().build();

        } catch (Exception e) {
            final String message = String.format("Unexpected error while processing the hashtag '%s'", hashtag);
            LOGGER.error(message, e);
            return HttpMessage.apply(INTERNAL_SERVER_ERROR, message);
        }
    }
}
