docker service create --constraint node.role==manager --replicas 1 \
--name zipkin --network npm-public \
--update-delay 10s --with-registry-auth  \
--update-parallelism 1 openzipkin/zipkin