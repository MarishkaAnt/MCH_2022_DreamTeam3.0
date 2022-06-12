# MCH_2022_DreamTeam3.0
Repo for Hackathon Moskow City Hack 2022 (team name: DreamTeam3.0)

## Установка

Клонируем репо

```sh
git clone https://github.com/MarishkaAnt/MCH_2022_DreamTeam3.0.git
```

## Начало работы с подпроектом **frontend**

### Менеджер пакетов npm

В подпроекте frontend используется менеджер пакетов npm.

```sh
# устанавливаем node
nvm install 14.18.2
```

### Начальная настройка frontend

```sh
# переходим в директорию frontend
cd mosprom/frontend/

# устанавливаем пакеты (из директории frontend)
npm i

# запускаем приложение
npm start
```

## Начало работы с подпроектом **backend**

### Начальная настройка backend

### База данных Postgres

Устанавливаем PostreSQL

Добавляем базу данных

### Maven

Устанавливаем maven

```sh
# переходим в директорию backend
cd mosprom/backend/

# билдим проект
mvn clean package
```
### IntelliJ IDEA

Устанавливаем IntelliJ IDEA Ultimate Edition
(с запуском из командной строки разобраться не удалось быстро)

Открываем подпроект backend в IntelliJ IDEA

### REST API

```sh
# переходим в модуль rest

# устанавливаем свойства в файле
src/main/resources/application.properties

# устанавливаем url базы данных
spring.datasource.url=

# устанавливаем пользователя и пароль для подключения к базе данных
spring.datasource.username=
spring.datasource.password=

# запускаем приложение
src/main/java/com/dreamteam3/rest/RestApplication.java
```

### Утилита Company uploader

```sh
# переходим в модуль company-uploader

# устанавливаем свойства в файле
src/main/resources/application.properties

# устанавливаем url базы данных
spring.datasource.url=

# устанавливаем пользователя и пароль для подключения к базе данных
spring.datasource.username=
spring.datasource.password=

# устанавливаем режим загрузки компаний
com.dreamteam3.mosprom.company-uploader.resourceType=FILE ИЛИ DATABASE

# устанавливаем путь к файлу со списком компаний (только латинские буквы)
com.dreamteam3.mosprom.company-uploader.filePath=

# запускаем приложение
src/main/java/com/dreamteam3/company/uploader/CompanyUploaderUtility.java
```

### Утилита Crawler

```sh
# переходим в модуль crawler

# устанавливаем свойства в файле
src/main/resources/application.properties

# устанавливаем url базы данных
spring.datasource.url=

# устанавливаем пользователя и пароль для подключения к базе данных
spring.datasource.username=
spring.datasource.password=

# запускаем приложение
src/main/java/com/dreamteam3/crawler/CrawlerUtility.java
```
