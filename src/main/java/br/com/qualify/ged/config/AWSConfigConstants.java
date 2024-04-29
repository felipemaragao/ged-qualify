package br.com.qualify.ged.config;

import org.springframework.beans.factory.annotation.Value;

public class AWSConfigConstants {
    public static final String ACCESS_KEY = "localstack";
    public static final String SECRET_KEY = "localstack";
    public static final String ENDPOINT = "http://localhost:4566";
    public static final String US_EAST_1 = "us-east-1";

    @Value("${cloud.aws.sns.order-created.arn}")
    public static final String ORDER_CREATED_TOPIC = "order-created-topic";

    @Value("${cloud.aws.sqs.order-queue.url}")
    public static final String ORDER_QUEUE = "order-queue";

    @Value("${cloud.aws.sqs.order-queue-2.url}")
    public static final String ORDER_QUEUE_2 = "order-queue-2";
}
