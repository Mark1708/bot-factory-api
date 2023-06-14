```ssh
docker stack deploy -c keycloak.yml keycloak --with-registry-auth
docker stack deploy -c db.yml db
sh zipkin.sh
docker stack deploy -c kafka.yml kafka
docker stack deploy -c eureka-config.yml eureka-config --with-registry-auth
docker stack deploy -c bot-factory.yml bot-factory --with-registry-auth
docker stack deploy -c gateway.yml gateway --with-registry-auth
docker stack deploy -c web-app.yml web-app --with-registry-auth
```