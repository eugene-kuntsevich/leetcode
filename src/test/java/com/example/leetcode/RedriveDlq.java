package com.example.leetcode;

import java.util.List;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.DeleteMessageResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

public class RedriveDlq {

  private final SqsClient sqsClient = SqsClient.builder()
      .region(Region.EU_CENTRAL_1)
      .credentialsProvider(DefaultCredentialsProvider.create())
      .build();
  private final String queueUrl = "";
  private final String targetQueueUrl = "";

  private static int counter = 0;

  @Test
  void redrive() {
    for (int i = 0; i < 1500; i++) {
      processBatch();
    }
  }

  private void processBatch() {
    List<Message> messages = getMessages();

    for (Message message : messages) {
//      System.out.println(
//          "Received message:  " + message.body() + ", messageId: " + message.messageId());

      String newBody = message.body()
          .replace("\"eventType\":null", "\"eventType\":\"upsertDocAddInfoTagEvent\"");

      //System.out.println("New body:  " + newBody);
      sendMessageToTargetQueue(newBody);

      deleteMessage(message);
      counter++;
      System.out.println("Counter: " + counter);
    }
  }

  private List<Message> getMessages() {
    ReceiveMessageRequest request = ReceiveMessageRequest.builder()
        .queueUrl(queueUrl)
        .maxNumberOfMessages(10) // max 10 at once
        .visibilityTimeout(30)   // seconds hidden after read
        .build();

    return sqsClient.receiveMessage(request).messages();
  }

  private void sendMessageToTargetQueue(String messageBody) {
    SendMessageRequest sendRequest = SendMessageRequest.builder()
        .queueUrl(targetQueueUrl)
        .messageBody(messageBody)
        .build();

    SendMessageResponse sendMessageResponse = sqsClient.sendMessage(sendRequest);
    //System.out.println("Sent message to target queue with id: " + sendMessageResponse.messageId());
  }

  private void deleteMessage(Message message) {
    DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
        .queueUrl(queueUrl)
        .receiptHandle(message.receiptHandle())
        .build();

    DeleteMessageResponse deleteMessageResponse = sqsClient.deleteMessage(deleteRequest);
    //System.out.println("Deleted message ID: " + message.messageId());
  }
}