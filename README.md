# Автотесты на **промо Торгового Эквайринга**, [**https://www.tinkoff.ru/business/acquiring/**](https://www.tinkoff.ru/business/acquiring/)

## Стек и инструменты
 Тесты были написаны на Java + JUnit5 + Selenide + Gradle

| Java | Gradle | Junit5 | Selenide |
|:----:|:------:|:------:|:--------:|
| <img src="images/JAVA.svg" width="40" height="40"> | <img src="images/Gradle.svg" width="40" height="40"> | <img src="images/Junit5.svg" width="40" height="40"> | <img src="images/Selenide.svg" width="40" height="40"> |

Запускаются и прогоняются через Jenkins + Selenoid

| Jenkins | Selenoid | 
|:--------:|:-------------:|
| <img src="images/Jenkins.svg" width="40" height="40"> | <img src="images/Selenoid.svg" width="40" height="40"> | 

С отчетами в Allure + Telegram
| Allure Report | Telegram |
|:---------:|:--------:|
| <img src="images/Allure Report.svg" width="40" height="40"> | <img src="images/Telegram.svg" width="40" height="40"> |

Присыпаны щепоткой любви)
| Любовь |
|:---------:|
| <img src="images/heart.png" width="40" height="40"> |


## Запуск через Jenkins https://jenkins.autotests.cloud/job/tacq/

### С какими параметрами можно запустить тесты:

* browser (default chrome)
* browserVersion (default 89.0)
* browserSize (default 1920x1080)
* browserMobileView (mobile device name, for example iPhone X)
* remoteDriverUrl (url address from selenoid or grid)
* videoStorage (url address where you should get video)
* threads (number of threads)
Пример настроек перед запуском сборки в Jenkins:

### Пример странички с выбором параметров сборки
![alt "Выбор параметров"](./images/jenkinsParamsExample.png)

### Как запустить тесты из командной строки
Запустить тесты с дефолтными параметрами:
```bash
gradle clean test
```

Запустить тесты с нужными нам параметрами:
```bash
gradle clean -DremoteDriverUrl=https://user1:1234@selenoid.autotests.cloud/wd/hub/ -DvideoStorage=https://selenoid.autotests.cloud/video/ -Dthreads=1 test
```

Сформировать отчет в Allure:
```bash
allure serve build/allure-results
```
## Прогоняются тесты в Selenoid https://selenoid.autotests.cloud/#/
### Пример прогона теста в Selenoid
![alt "Video from Selenoid"](./images/TestExample.gif "Video from Selenoid")





:world_map: <a target="_blank" href="https://www.linkedin.com/in/edubrovin/">linkedin</a><br/>
:airplane: Telegram - @dubrowin


