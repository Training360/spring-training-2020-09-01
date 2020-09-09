MariaDB Docker:

```
docker run -p 3306:3306 -e MYSQL_DATABASE=employees -e MYSQL_USER=employees -e MYSQL_PASSWORD=employees -e MYSQL_ALLOW_EMPTY_PASSWORD=true -d --name employees-mariadb mariadb
``` 