#Задание

Реализовать backend приложение, которое реализует работу 
с заказами командировок:
REST:
Post метод: обновления-создания travel
Get метод: получение данных по travel

У travel есть 3 состояния – open -> inprogress -> closed

В состоянии open обновление разрешено, во всех остальных запрещено
Из состояния inprogress переход в closed автоматический: 
1 день спустя статус inprogress

Stack технологий: Java, Spring Boot 2.0, H2 Database

#Описание

Приложение реализует работу с заказами командировок. У командировки есть:
- Имя командируемого
- Место командировки
- Дата начала
- Дата окончания
- Статус

Статус может принимать значения: 
Open -> InProgress -> Closed

Переход между статусами возможен только слева направо. 
Попытка других переходов вызовет ошибку.
Переход InProgress -> Closed автоматический по шедулеру.