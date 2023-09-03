## Проект по автоматизации тестирования для сервиса [Snowball-Income](https://snowball-income.com/)
<p  align="center">
<a href="https://snowball-income.com/"><img src="./images/icons/favicon.png" width="100"></a> <a href="https://snowball-income.com/"><img src="./images/icons/snowball.svg" width="400"></a>
</p>

### :pushpin: Содержание:

+ [Описание](#Описание)
+ [Используемый стек](#computer-используемый-стек)
+ [Варианты запуска](#Варианты-запуска)
    + [Запуск тестов из терминала](#Команды-для-gradle)
    + [Запуск в Jenkins](#-запуск-в-jenkins)
+ [Уведомления в Telegram](#-telegram-уведомления)
+ [Результаты тестов в Allure Report](#-результаты-тестов-в-allure-report)
+ [Интеграция с Allure TestOps](#-интеграция-с-allure-testops)
+ [Интеграция с Jira](#-интеграция-с-allure-testops)
+ [Видео примера запуска тестов в Selenoid](#-видео-запуска-тестов-в-selenoid)

# <a name="Описание">Описание</a>
Тестовый проект состоит из веб-тестов (UI), тестов API и мобильных тестов (Android).

**Краткий список интересных фактов о проекте:**
- [x] `Page Object` проектирование
- [x] Параметризованные тесты
- [x] Различные конфигурации для запуска теста в зависимости от параметров сборки
- [x] Конфигурация с библиотекой `Owner`
- [x] Написана кастомная аннотация `@WithLogin` для прокидывания токена авторизации
- [x] Использование `Lombok` для моделей в API тестах
- [x] Использование request/response спецификаций для API тестов
- [x] Custom Allure listener для API requests/responses логов
- [x] Автотесты как тестовая документация
- [x] Интеграция с `Allure TestOps`
- [x] Интеграция с `Jira`

### :computer: Используемый стек

<p  align="center">
  <a href="https://www.jetbrains.com/idea/"><code><img width="5%" title="IntelliJ IDEA" src="./images/icons/IDEA-logo.svg"></code>
  <a href="https://www.java.com/"><code><img width="5%" title="Java" src="./images/icons/java-logo.svg"></code>
  <a href="https://selenide.org/"><code><img width="5%" title="Selenide" src="./images/icons/selenide-logo.svg"></code>
  <a href="https://aerokube.com/selenoid/"><code><img width="5%" title="Selenoid" src="./images/icons/selenoid-logo.svg"></code>
  <a href="https://gradle.org/"><code><img width="5%" title="Gradle" src="./images/icons/gradle-logo.svg"></code>
  <a href="https://junit.org/junit5/"><code><img width="5%" title="JUnit5" src="./images/icons/junit5-logo.svg"></code>
  <a href="https://rest-assured.io/"><code><img width="5%" title="RestAssured" src="./images/icons/rest-assured-logo.svg"></code>
  <a href="http://appium.io/docs/en/2.1/"><code><img width="5%" title="Appium" src="./images/icons/appium.svg"></code>
  <a href="https://www.browserstack.com/"><code><img width="5%" title="Browserstack" src="./images/icons/browserstack.svg"></code>
  <a href="https://github.com/allure-framework/allure2"><code><img width="5%" title="Allure Report" src="./images/icons/allure-Report-logo.svg"></code>
  <a href="https://qameta.io/"><code><img width="5%" title="Allure TestOps" src="./images/icons/allure-ee-logo.svg"></code>
  <a href="https://github.com/"><code><img width="5%" title="Github" src="./images/icons/git-logo.svg"></code>
  <a href="https://www.jenkins.io/"><code><img width="5%" title="Jenkins" src="./images/icons/jenkins-logo.svg"></code>
  <a href="https://www.atlassian.com/ru/software/jira"><code><img width="5%" title="Jira" src="./images/icons/jira-logo.svg"></code>
  <a href="https://web.telegram.org/"><code><img width="5%" title="Telegram" src="./images/icons/Telegram.svg"></code>
</p>

Автотесты в этом проекте написаны на `Java` использую `Selenide` фреймворк.\
`Gradle` - используется как инструмент автоматизации сборки.  \
`JUnit5` - для выполнения тестов.\
`REST Assured` - для тестирования REST-API сервисов.\
`Jenkins` - CI/CD для запуска тестов удаленно.\
`Selenoid` - для удаленного запуска браузера в `Docker` контейнерах.\
`Browserstack` - для запуска мобильных тестов удаленно.\
`Appium` - для взаимодействия с мобильным устройством.\
`Allure Report` - для визуализации результатов тестирования.\
`Telegram Bot` - для уведомлений о результатах тестирования.\
`Allure TestOps` - как система управления тестированием.

[Вернуться к оглавлению ⬆](#pushpin-содержание)

# <a name="Варианты запуска">Варианты запуска</a>

## <a name="GradleCommand">Команды для Gradle</a>
Для запуска локально и в Jenkins используется следующая команда::
```bash
gradle clean test -Dtag=<tag> -DrunIn=<runIn>
```

Дополнительные параметры:
> `-Dselenoid_user_sys_prop=enter_user` `-Dselenoid_key_sys_prop=enter_key` - данные для входа в Selenoid\
> `-Dbrowserstack_user_sys_prop=enter_user` `-Dbrowserstack_key_sys_prop=enter_key` - данные для входа в Browserstack\
> `-Dsnowball_login_sys_prop=enter_login` `-Dsnowball_pass_sys_prop=enter_pass`- данные для авторизации в Snowball-Income.

`tag` - теги для запуска выполнения тестов:
>- *API*
>- *Web*
>- *Mobile*
 
`runIn` - определяет среду для запуска этих тестов:
>- *api* - for api tests
>- *browser_selenoid*
>- *browser_local*
>- *ios_browserstack*

Дополнительные свойства извлекаются из соответствующего файла конфигурации (в зависимости от значения `runIn`):
```bash
./resources/config/${runIn}.properties
```

Допустимые комбинации:
```mermaid
graph LR
A[tag] --> B[API]
A --> C[Web]
A --> D[Mobile]
B --> K[api]
C --> E[browser_selenoid]
C --> F[browser_local]
D --> G[ios_browserstack]
```

[Вернуться к оглавлению ⬆](#pushpin-содержание)

## <img src="./images/icons/jenkins-logo.svg" title="Jenkins" width="4%"/> <a name="Запуск в Jenkins">Запуск в [Jenkins](https://jenkins.autotests.cloud/job/Snowball-Income/)</a>
Главная страница сборки:
<p  align="center">
<img src="images/screens/JenkinsBuild.png" width="950">
</p>

Параметризованное задание Jenkins может быть запущено с необходимыми ***Browser***, ***Browser Version***, ***Browser Size*** и ***Feature***:
<p  align="center">
<img src="images/screens/JenkinsParams.png" alt="JenkinsBuildParameters" width="950">
</p>

Конфиденциальная информация (имена для входа и пароли) хранится в зашифрованном виде в хранилище учетных данных Jenkins.\
И относительно безопасно передается в сборку аргументами gradle, а его значения маскируются в логах.

После завершения сборки результаты тестирования доступны в:
>- <code><strong>*Allure Report*</strong></code>
>- <code><strong>*Allure TestOps*</strong></code> - результаты загружаются туда и тест-кейсы могут автоматически обновляться в соответствии с последними изменениями в коде.

[Вернуться к оглавлению ⬆](#pushpin-содержание)

# <img width="4%" style="vertical-align:middle" title="Telegram" src="images/icons/Telegram.svg"> <a>Telegram уведомления</a>
После завершения сборки, бот созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с результатом.
<p  align="center">
<img src="images/screens/Telegram.png" width="550">
</p>

[Вернуться к оглавлению ⬆](#pushpin-содержание)

# <img width="4%" style="vertical-align:middle" title="AllureReport" src="images/icons/allure-Report-logo.svg"> <a name="AllureReport">Результаты тестов в [Allure Report](https://jenkins.autotests.cloud/job/Snowball-Income/39/allure/)</a>

## Главная страница
Главная страница отчета Allure содержит следующие блоки:

>- <code><strong>*ALLURE REPORT*</strong></code> - отображает дату и время теста, общее количество запущенных тестов, а также диаграмму с процентом и количеством успешных, упавших и сломавшихся в процессе выполнения тестов
>- <code><strong>*TREND*</strong></code> - отображает тенденцию выполнения тестов для всех запусков
>- <code><strong>*SUITES*</strong></code> - отображает распределение тестов по сьютам
>- <code><strong>*CATEGORIES*</strong></code> - отображает распределение неудачных тестов по типам дефектов
<p align="center">
  <img src="images/screens//AllureReport.png" width="950">
</p>

## Список тестов с шагами и тестовыми артефактами
На странице список тестов, сгруппированных по наборам, с указанием статуса для каждого теста.\
Может быть показана полная информация о каждом тесте: теги, продолжительность, подробные шаги.

<p align="center">
  <img src="images/screens/AllureTestSuites.png" alt="AllureReportSuites" width="750">
</p>

Также доступны дополнительные тестовые артефакты:
>- Screenshot
>- Page Source
>- Browser console log
>- Video

<p align="left">
  <img src="images/screens/AllureResult.png" alt="AllureResult" width="950">
</p>

[Вернуться к оглавлению ⬆](#pushpin-содержание)

# <img width="4%" style="vertical-align:middle" title="Allure TestOps" src="images/icons/allure-ee-logo.svg"> <a>Интеграция с [Allure TestOps](https://allure.autotests.cloud/project/3642/dashboards)</a>
> Ссылка доступна только авторизованным пользователям.

Выполнена интеграция сборки <code>Jenkins</code> с <code>Allure TestOps</code>.
Результат выполнения автотестов отображается в <code>Allure TestOps</code>
На Dashboard в <code>Allure TestOps</code> отображена статистика пройденных тестов.

Тест-кейсы в проекте импортируются и постоянно обновляются из кода,
поэтому нет необходимости в синхронизации ручных тест-кейсов и автотестов.\
Достаточно создать и обновить автотест в коде и тест-кейс всегда будет в актуальном состоянии.

## Allure TestOps Dashboard

<p align="center">
  <img src="images/screens/AllureTestOpsRun.png" alt="AllureTestOps" width="950">
</p>

```mermaid
stateDiagram-v2
state "Тест создан/обновлен в коде" as A
state "Запускается сборка в Jenkins" as B
state "Сборка в Jenkins завершена" as C
state "Запуск Allure TestOps, связанный со сборкой, отмеченной как закрытая" as D
state "Все выполненные тест-кейсы автоматически создаются/обновляются в соответствии с кодом" as E
[*] --> A
A --> B
B --> C
C --> D
D --> E
E --> A
```

## Allure TestOps Test Cases

<p align="center">
  <img src="images/screens/AllureTestOpsCases.png" alt="AllureTestOpsTests" width="950">
</p>

[Вернуться к оглавлению ⬆](#pushpin-содержание)

# <img width="4%" style="vertical-align:middle" title="Jira" src="images/icons/jira-logo.svg"> <a>Интеграция с [Jira](https://jira.autotests.cloud/browse/HOMEWORK-828)</a>
Реализована интеграция <code>Allure TestOps</code> с <code>Jira</code>, в тикете отображается информация, какие тест-кейсы были написаны в рамках задачи и результат их прогона.
<p align="center">
  <img src="images/screens/JiraIntegration.png" alt="JiraIntegration" width="950">
</p>

[Вернуться к оглавлению ⬆](#pushpin-содержание)

# <img width="4%" style="vertical-align:middle" title="Selenoid" src="images/icons/selenoid-logo.svg"> <a>Видео запуска тестов в Selenoid</a>
К каждому тесту в отчете прилагается видео прохождения теста:
<p align="center">
  <img src="images/screens/selenoid.gif" alt="VideoSelenoid">
</p>
Видео выполнения на мобильном устройстве:
<p align="center">
  <img src="images/screens/iphone.gif" alt="VideoIphone">
</p>

[Вернуться к оглавлению ⬆](#pushpin-содержание)
