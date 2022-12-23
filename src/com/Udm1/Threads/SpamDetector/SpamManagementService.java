package com.Udm1.Threads.SpamDetector;

public interface SpamManagementService {

    public DefaultSpamManagementService.Spam makeSpamObject();

    public void collectInputs();

}
