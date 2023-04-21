package com.hauschildt.utilities;
import com.azure.communication.email.models.*;
import com.azure.communication.email.*;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import io.github.cdimascio.dotenv.Dotenv;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MyAzure {

    private static String getConnection() {
        Dotenv dotenv = Dotenv.load();
        String connectionString = "endpoint=https://" + dotenv.get("AZURE_ENDPOINT") + ".communication.azure.com/;accesskey=" + dotenv.get("AZURE_COMMUNICATION_KEY");
        return connectionString;
    }

    public static void sendEmail(String toAddress, String subject, String body) {
        EmailClient emailClient = new EmailClientBuilder()
                .connectionString(getConnection())
                .buildClient();
        
        EmailMessage message = new EmailMessage()
                .setSenderAddress("<DoNotReply@8e5626b0-d6d9-4077-b51f-ababb5784fc4.azurecomm.net>")
                .setToRecipients("<" + toAddress + ">")
                .setSubject(subject)
                .setBodyPlainText(body);

        try
        {
            SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(message, null);

            PollResponse<EmailSendResult> pollResponse = null;

            Duration timeElapsed = Duration.ofSeconds(0);
            Duration POLLER_WAIT_TIME = Duration.ofSeconds(10);
            while (pollResponse == null
                    || pollResponse.getStatus() == LongRunningOperationStatus.NOT_STARTED
                    || pollResponse.getStatus() == LongRunningOperationStatus.IN_PROGRESS)
            {
                pollResponse = poller.poll();
                System.out.println("Email send poller status: " + pollResponse.getStatus());

                Thread.sleep(POLLER_WAIT_TIME.toMillis());
                timeElapsed = timeElapsed.plus(POLLER_WAIT_TIME);

                if (timeElapsed.compareTo(POLLER_WAIT_TIME.multipliedBy(18)) >= 0)
                {
                    throw new RuntimeException("Polling timed out.");
                }
            }

            if (poller.getFinalResult().getStatus() == EmailSendStatus.SUCCEEDED)
            {
                System.out.printf("Successfully sent the email (operation id: %s)", poller.getFinalResult().getId());
            }
            else
            {
                throw new RuntimeException(poller.getFinalResult().getError().getMessage());
            }
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }

}
