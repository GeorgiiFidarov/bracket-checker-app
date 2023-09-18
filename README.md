# SBERBRACKETS 
# Приложение для проверки правильности скобок



Это приложение разработано для помощи пользователям определить, правильно ли они расставили скобки в своем тексте. Особенностью приложения является необходимость наличия текста между открывающими и закрывающими скобками. Пустые скобки считаются некорректными.

## Запуск приложения

Чтобы развернуть приложение и запустить его, выполните следующие шаги:

1. Убедитесь, что на вашем компьютере установлен Java Development Kit (JDK).

2. Клонируйте репозиторий с приложением с помощью команды Git:

   ```bash
   git clone https://github.com/yourusername/bracket-checker-app.git
   ```

   Замените `yourusername` на ваше имя пользователя на GitHub.

3. Перейдите в каталог приложения:

   ```bash
   cd bracket-checker-app
   ```

4. Соберите проект с помощью Maven:

   ```bash
   mvn clean install
   ```

5. Запустите приложение:

   ```bash
   java -jar target/bracket-checker-app.jar
   ```

Приложение будет доступно по адресу `http://localhost:8080`.

## Использование API

Вы можете использовать следующий HTTP-запрос для проверки текста с помощью API приложения:

### Запрос

```http
POST /api/checkBrackets
Content-Type: application/json

{
  "text": "Вчера я отправился в поход в лес 
  (это мое любимое место для отдыха) вместе с друзьями. 
  Мы выбрали маршрут, который проходил через горные потоки 
  и поля (для разнообразия). В начале пути погода была отличной,
  солнце светило ярко, и птицы радостно пели. Однако, 
  когда мы подошли ближе к вершине горы, небо стало покрываться 
  облаками, (как будто природа готовила нам небольшой сюрприз). 
  Несмотря на это, виды были захватывающими, особенно когда мы 
  достигли высшей точки и увидели прекрасный вид на долину 
  (я почувствовал, что все усилия стоили того)."
}
```

Замените значение `"text"` на ваш текст для проверки.

### Ответ

Если текст содержит правильно расставленные скобки с текстом между ними, вы получите следующий ответ:

```json
{
  "isCorrect": true
}
```

Если текст содержит некорректные скобки или пустые скобки, ответ будет следующим:

```json
{
  "isCorrect": false
}
```

## Тестирование

Для тестирования приложения включены следующие тесты:

- `testValidTextWithNonEmptyBrackets`: Проверяет правильность текста с непустыми скобками.
- `testTextWithEmptyBrackets`: Проверяет текст с пустыми скобками.
- `testTextWithMismatchedBrackets`: Проверяет текст с несоответствующими скобками.
- `testEmptyText`: Проверяет пустой текст.
- `testNullText`: Проверяет отсутствие текста (null).
- `testEmptyTextWithSpaces`: Проверяет текст, состоящий только из пробелов.
- `testValidTextWithSpaces`: Проверяет текст с непустыми скобками и пробелами.
- `testValidTextWithCommas`: Проверяет текст с непустыми скобками и запятыми.
- `testInvalidTextWithCommas`: Проверяет текст с некорректными скобками и запятыми.
- `testValidTextWithNestedBrackets`: Проверяет текст с вложенными скобками.
- `testInvalidTextWithNestedBrackets`: Проверяет текст с некорректными вложенными скобками.

Чтобы выполнить тесты, запустите их с помощью вашей системы сборки или IDE.

## Конфигурации

Приложение использует следующие конфигурации:

- `server.port`: Порт, на котором работает приложение (по умолчанию 8080).

## Лицензия

Это приложение распространяется под лицензией MIT. Вы можете свободно использовать и модифицировать его.
