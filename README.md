# Api-Gateway
Сервис для маршрутизации http-запросов, а также для генерации и проверки токена авторизации.

### Взаимодействие с другими компонентами
- [backend-app](https://github.com/DevOL078/otus-microservices-backend-app)

### Use cases
- Регистрация пользователя в системе ([sequence-диаграмма](doc%2Fregister.puml))
- Вход пользователя в систему ([sequence-диаграмма](doc%2Flogin.puml))
- Получение данных профиля пользователя ([sequence-диаграмма](doc%2Fget-profile.puml))
- Изменение данных профиля пользователя ([sequence-диаграмма](doc%2Fedit-profile.puml))

### API
<span style="color:yellow">*TODO*</span>

### Deployment
Для запуска сервиса в k8s необходимо выполнить следующие команды из корня проекта:

1. Сборка артефакта
```
mvn clean package
```

2. Сборка Docker-образа
```
docker build . -t [image_name]:[image_tag]
docker push [image_name]:[image_tag]
```

3. Установка helm-чарта
```
helm upgrade --install api-gateway ./k8s/helm/api-gateway
```

4. (При развертывании в minikube) Открытие туннеля
```
minikube tunnel
```

### Docker-образ
https://hub.docker.com/repository/docker/dmoleynik0307/api-gateway/general

### Helm-chart
1. Deployment - [deployment.yaml](k8s%2Fhelm%2Fapi-gateway%2Ftemplates%2Fdeployment.yaml)
2. Service - [service.yaml](k8s%2Fhelm%2Fapi-gateway%2Ftemplates%2Fservice.yaml)
3. ServiceMonitor - [service-monitor.yaml](k8s%2Fhelm%2Fapi-gateway%2Ftemplates%2Fservice-monitor.yaml)
4. Ingress - [ingress.yaml](k8s%2Fhelm%2Fapi-gateway%2Ftemplates%2Fingress.yaml)

Values - [values.yaml](k8s%2Fhelm%2Fapi-gateway%2Fvalues.yaml)

### Liveness probe
```
GET http://arch.homework/health
```
Response (200):
```
{
    "status": "OK"
}
```

### Тестирование при помощи newman
Команда для запуска тестовых сценариев при помощи утилиты [newman](https://www.npmjs.com/package/newman):
```
newman run ./postman/postman_collection.json -e ./postman/postman_environment.json
```
Также файлы [postman_collection.json](postman%2Fpostman_collection.json) и [postman_environment.json](postman%2Fpostman_environment.json)
можно использовать для импорта коллекции запросов и окружения в [Postman](https://www.postman.com/)

### Access token
Для генерации токена используется пара RSA ключей (приватный и публичный).

Приватный ключ используется для генерации и валидации токена на стороне api-gateway,
а публичный - для проверки подписи на стороне других компонентов.




