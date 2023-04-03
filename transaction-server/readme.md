### run zookeeper
- bin/zookeeper-server-start.sh config/zookeeper.properties

### run one broker
- bin/kafka-server-start.sh config/server.properties

### list group-id of consumer 
- bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list

### start consumer 
- bin/kafka-console-consumer.sh config/consumer.properties --bootstrap-server localhost:9092 --topic trx
- bin/kafka-console-consumer.sh config/consumer.properties --from-beginning --bootstrap-server localhost:9092 --topic trx

### start producer
- bin/kafka-console-producer.sh --broker-list localhost:9092 --topic trx

### create topic 
- bin/kafka-topics.sh --create --topic trx --bootstrap-server localhost:9092

### list topic
- bin/kafka-topics.sh --list --bootstrap-server localhost:9092 