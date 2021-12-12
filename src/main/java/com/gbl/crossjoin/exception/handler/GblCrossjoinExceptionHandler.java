package com.gbl.crossjoin.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gbl.crossjoin.exception.BusinessException;
import com.gbl.crossjoin.exception.NotFoundCustomException;

@ControllerAdvice
public class GblCrossjoinExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Server Error", details);
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Business error", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(NotFoundCustomException.class)
    public final ResponseEntity<Object> handleNotFoundException(NotFoundCustomException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Data not found", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
	
	public static class ErrorResponse {
		
		private String userMessage;
		
		private List<String> details;
		
		public ErrorResponse(String userMessage, List<String> details) {
			this.userMessage = userMessage;
			this.details = details;
		}
		public String getUserMessage() {
			return userMessage;
		}
		public void setUserMessage(String userMessage) {
			this.userMessage = userMessage;
		}
		public List<String> getDetails() {
			return details;
		}
		public void setDetails(List<String> details) {
			this.details = details;
		}
	}
}
