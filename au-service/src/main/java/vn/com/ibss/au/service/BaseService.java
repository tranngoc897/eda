package vn.com.ibss.au.service;


import org.springframework.stereotype.Service;
import vn.ibss.common.exception.BadRequestException;
import vn.ibss.common.utils.AppConstants;

@Service
public abstract class BaseService {

    public void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }
        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

}
