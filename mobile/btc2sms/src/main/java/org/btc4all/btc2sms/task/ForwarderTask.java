package org.btc4all.btc2sms.task;

import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.btc4all.btc2sms.IncomingMessage;

public class ForwarderTask extends HttpTask {

    private IncomingMessage message;

    public ForwarderTask(IncomingMessage message, BasicNameValuePair... paramsArr) {
        super(message.app, paramsArr);
        this.message = message;                
    }
    
    @Override
    protected String getDefaultToAddress() {
        return message.getFrom();
    }

    @Override
    protected void handleResponse(HttpResponse response) throws Exception {
        app.inbox.messageForwarded(message);       
        super.handleResponse(response);
    }

    @Override
    protected void handleFailure() {        
        app.inbox.messageFailed(message);
    } 
}
