hbase:
    create 'u2itable','info'
    create 'userItem','item'

kafka：
    topic -> rec-system
    ./kafka-console-consumer.sh --topic rec-system --zookeeper one.3cgg.rec:2181 --from-beginning
