package vn.ibss.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;


public abstract class BaseController {

    public <T> ResponseEntity<List<T>> buildEntitiesListResponse(List<T> response) {
        return new ResponseEntity<>(response, getJsonHttpHeaders(), HttpStatus.OK);
    }

    public <T> ResponseEntity<T> buildEntityResponse(T response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public HttpHeaders getJsonHttpHeaders() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
