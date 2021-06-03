package kafka;

public interface KafkaProducer {
    void send(String key, String msg);
}
