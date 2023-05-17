[Отчет по тестированию](https://github.com/danya794/ByATourApp/blob/main/Report.md)

[Отчет по автоматизации](https://github.com/danya794/ByATourApp/blob/main/Summury.md)

### Запуск приложения
Для запуска приложения необходимо установить Docker

- склонировать репозиторий `https://github.com/BednovaK/DiplomQA.git`

- открыть склонированный проект в IntelliJ IDEA

- в терминале IntelliJ IDEA запустить Docker container командой `docker-compose up`

- в терминале IntelliJ IDEA запустить SUT:

с использованием БД MySQL командой `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar` 

с использованием БД PostgreSQL командой `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar` 

- запустить автотесты командой:

для БД MySQL: 
`./gradlew clean test -D db.url=jdbc:mysql://localhost:3306/app`

для БД PostgreSQL: 
`./gradlew clean test -D db.url=jdbc:postgresql://localhost:5432/app`

- запустить отчеты командой:

`./gradlew allureReport` (первоначальная команда)

`./gradlew allureServe` (запуск и открытие отчетов)

- остановить SUT комбинацией клавиш `CTRL+C`

- завершить работу контейнеров командой `docker-compose down`
