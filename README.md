Signature-demo
==============

Проект signature-demo демонстрирует процесс подписания и проверки электронной подписи формата XAdES-BES.
 
Проект представляет собой утилиту командной строки, при помощи который можно подписать XML-документ, проверить подпись в XML-докумнете. 


Настройка
=========
Signature-demo требует для своей работы СКЗИ КриптоПро CSP и СКЗИ Trusted Java 2.0.

Установка и настройка СКЗИ КриптоПро CSP производится в соответствии с эксплуатационной документацией.
 
Установка и настройка Trusted Java
----------------------------------
СКЗИ Trusted Java можно установить в двух вариантах:

- в режиме интеграции с JRE.
- без интеграции с JRE.

Не рекомендуется устанавливать Trusted Java в режиме интеграции с JRE т.к. для корректной работы потребуется часть используемых 
библиотек также скопировать в JRE

**Порядок установки Trusted Java в ОС Windows**:
 
- Установить КриптоПро CSP.
- Установить Trusted Java при помощи инсталлятора.
- Ввести лицензионный ключ через "Управление лицензиями".
 
Инсталлятор интегрирует Trusted Java с JRE. Для того чтобы убрать интеграцию Trusted Java с JRE:

- В файле \<JRE\>\lib\security\java.security закомментировать строки вида


    ssl.SocketFactory.provider=com.digt.trusted.jsse.provider.DigtSocketFactory
    security.provider.11=com.digt.trusted.jce.provider.DIGTProvider

- Удалить файл \<JRE\>\lib\ext\trusted_java20.jar

В случае если требуется работать в режиме интеграции с JRE, вместо этого нужно:

- Скопировать в \<JRE\>\lib\ext библиотеки xmlsec-{version}.jar и commons-logging-{version}.jar из состава Apache Santuario (http://santuario.apache.org/download.html).

Внимание: рекомендуется использовать Apache Santuario версии 1.5.x.
 
Внимание: Apache Santuario версии 2.x использует библиотеку логировния slf4j, а не commons-logging.

**Порядок установки Trusted Java в ОС Linux**:

Ниже описан порядок установки Trusted Java без интеграции с JRE:

- Установить КриптоПро CSP.
- Скачать с сайта http://www.trusted.ru/support/downloads/?product=142 версию, совместимую установленной версией КриптоПро CSP
- Распаковать в корень диска содержимое дистрибутива Trusted Java:


    tar -xf ./trustedjava-{version}.gz -C /
    
- В каталоге $JRE/lib/amd64/ создать символическую ссылку на библиотеку Trusted Java:


    ln -s "/opt/DIGT/Trusted Java 2.0/lib/amd64/libdjcp20.so" $JRE/lib/amd64/
     
- Указать лицензию в файле /opt/DIGT/etc/Trusted/Java 2.0/license.lic 
 
Сборка
======
Перед сборкой необходимо скопировать файл trusted_java20.jar(его можно найти в дистрибутиве Trusted Java) в директорию lib.
Для сборки signature-demo необходимо использовать команду:

    gradlew clean build distZip

Для сборки и работы нужна Java 7 или выше. 

Использование
=============
Полученный в результате сборки дистрибутив build/distributions/signature-demo-{version}.zip необходимо распаковать.

Запуск программы производится из директории bin:

Для Windows:

    signature-demo.bat

Для Linux:

    ./signature-demo

При запуске без параметров отображается справка по использованию программы.

Подписание
----------

Для подписания XML-документа:

- Запросить список имеющихся ключевых контейнеров:


    signature-demo -list -storename CurrentUser/My
    

К примеру:

    FAT12\00000000\keyname1.000\1234, PrivateKeyEntry
    SCARD\ETOKEN_JAVA_00000000\0000\1234, PrivateKeyEntry
    ...

- Подписать документ:


    signature-demo -sign -in in.xml -out out.xml -element foo -alias FAT12\00000000\keyname1.000\1234
    
    
Где:

- in.xml - имя входного файла
- out.xml - имя выходного файла (если параметр -out опустить, вывод будет производиться на экран)
- foo - Id подписываемого элемента
- FAT12\00000000\keyname1.000\1234 - имя ключевого контейнера


Проверка подписи
----------------

Для проверки всех подписей в документе:


    signature-demo -verify -in document.xml

Результат проверки отображается на экране. Пример:

    The document contains 1 signature(s).

    Signature # 1 is valid:
    Signature id: xmldsig-ea96660f-bdbb-42a3-8ace-8c5c4d9aaa31
    Signed URIs: #foo
    Signature form: XAdES-BES
    Validation certificate: CN=Тестовый ключ
    Issued by: CN=Тестовый УЦ, C=RU

Для проверки конкретной подписи по ее Id:

    signature-demo -verify -in document.xml -signature xmldsig-ea96660f-bdbb-42a3-8ace-8c5c4d9aaa31

В случае если проверка прошла успешно, код возврата программы равен 0.
В случае ошибки код возврата больше нуля.
