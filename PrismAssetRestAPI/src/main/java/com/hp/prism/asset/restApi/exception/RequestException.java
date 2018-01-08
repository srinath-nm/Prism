/**
 * 
 */
package com.hp.prism.asset.restApi.exception;

/**
 * @author jayanthi.krishnan
 *
 */
public class RequestException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestException(String message) {
		super(message, null, false, false);
	}
} 
