TaskList
Учебное Android-приложение: список задач на Jetpack Compose с хранением данных в Room.

Возможности
Добавление задачи
Удаление задачи
Список обновляется автоматически
Задачи сохраняются между запусками

Стек
Kotlin 2.2.10 · Jetpack Compose (BOM 2026.02.01) · Room 2.8.5 · KSP · Gradle 9.5 · minSdk 24

Запуск
git clone https://github.com/diyora-eng/android-tasklist.git
cd android-tasklist
./gradlew installDebug


Или открыть папку в Android Studio и нажать Run.

Если сборка не находит SDK, создай в корне local.properties:

sdk.dir=/Users/<имя>/Library/Android/sdk


Структура
app/src/main/java/com/xemoado/tasklist/
├── MainActivity.kt     # экран на Compose
├── Task.kt             # @Entity — таблица tasks
├── TaskDao.kt          # @Dao — запросы к базе
└── TaskDataBase.kt     # @Database — база и доступ к ней


Работа с базой
Room собирается из трёх частей: @Entity описывает таблицу, @Dao — запросы, @Database — саму базу.
Чтение идёт через Flow, поэтому экран перерисовывается сам после записи. Запись — через suspend-функции.

Изменил @Entity — подними version в @Database и добавь Migration, иначе приложение упадёт при запуске.
