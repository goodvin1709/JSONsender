# JSON parser.
Робота с форматом JSON.
Використовується:
Java 1.7;
Log4j 1.2.16;
Система збірки Gradle 2.3;
Apache Http Client 4.5.2;

Як працювати.
В классі Application змінна URL відповідає адресі на яку треба відправити запит, змінна method відповідає за тип запиту(POST, GET).
В статичному блоці класу можна добавити параметри запиту, або залишити статичне поле пустим:
params.add(new BasicNameValuePair("paramName","ParamValue"));
ParamName - Назва параметру.
ParamValue - Значення параметру.

Запит виконується в потоці.
Результат буде виведений в форматі:

Response:
Об'єект1 : Значення1
Об'єект2 : Значення2. 
Об'єект3 : Значення3. 
....
