package com.recommender.app.exceptions;

public class QuestionaryInitializationFailedException extends Exception {
	QuestionaryInitializationFailedException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}
		public QuestionaryInitializationFailedException(String message) {
			super(message);
		}
		public QuestionaryInitializationFailedException(String message, Throwable cause) {
			super(message, cause);
		}
}
