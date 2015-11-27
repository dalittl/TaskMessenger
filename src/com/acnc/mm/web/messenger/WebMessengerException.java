/**
 *
 */
package com.acnc.mm.web.messenger;

import com.acnc.mm.web.WebException;


/**
 * @author Kunta L.
 *
 */
public class WebMessengerException extends WebException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -612181299174448050L;

	public WebMessengerException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * @param msg
	 * @param cause
	 */
	public WebMessengerException (String msg, Throwable cause){
		super (msg, cause);
	}

}
