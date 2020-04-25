package com.recommender.app.exceptions;

public class RecommendationNotFoundException extends Exception {
	RecommendationNotFoundException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}
		public RecommendationNotFoundException(String message) {
			super(message);
		}
		public RecommendationNotFoundException(String message, Throwable cause) {
			super(message, cause);
		}
}
