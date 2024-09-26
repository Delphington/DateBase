=============== Хранилища данных ===============

### БД - база данных клиентов 
id - индификатор клиента
name - имя клиента
sex - пол клиента
age - возвраст клиента
AVO - средний чек
===============================
| id | name | sex | age | AOV | 
==============================

### Type Dates
id - Integer NOT NULL
Name - String (буквы Англ и Русские) NOT NULL
sex - String NOT NULL
age - Integer NOT NULL
AOV - Integer NOT NULL

### INFO COMMAND  
GET - получение элемент по ID 
PUT - добавление элемента, если его нет 
UPDATE - обновление элемента, если он есть 
DELETE - удаление элемента, если он есть 
CREATE - создание нового файла, если его нету и добавление предыдущих данных
[E] - выход 
[P] - вывод всей БД 

### SYNTAX 
GET id
PUT (id, name, sex, age, AVO) 
UPDATE (id, name, sex, age, AVO) 
DELETE id 
CREATE (file name) 
[E] - exit

