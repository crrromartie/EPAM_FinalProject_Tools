## Сервис по прокату электроинструмента "ToolRental"<a name="русский"></a>

### Учебный проект по курсу Java Web Development

### Автор: Гапоненко Игорь

#### [Go to EN](#english)
---

### Оглавление<a name="оглавление"></a>

* [Общее описание](#общее_описание)
* [Пользователи](#пользователи)
* [Инструменты](#автомобили)
* [Заказы](#заказы)

### Общее описание<a name="общее_описание"></a>

Веб приложение предоставляет возможность осуществлять онлайн бронирование электроинструментов для аренды. Клиент может
подобрать нужный ему инструмент по типу, модели и цене, и забронировать инструмент на определенную дату. После
подтверждения заказа администратором, клиенту необходимо оплатить заказ, так же есть возможность отменить заказ. После
оплаты заказа, клиент может забрать инструмент для использования.

[⬆️Оглавление](#оглавление)
____

### Пользователи<a name="пользователи"></a>

В приложении определены следующие роли пользователей:

* **Гость**  
  Неавторизированный пользователь

  Функциональные возможности:
    * Просмотр домашней страницы
    * Просмотр каталога инструментов
    * Смена локали
    * Авторизация
    * Регистрация
* **Клиент**  
  У клиента определены три статуса: **Неподтвержденный, Активный, Заблокированный**.  
  Гостю, прошедшему процедуру регистрации присваивается роль **Клиент** и статус **Неподтвержденный**. На электронную
  почту клиента, указанную при регистрации, отправляется письмо со ссылкой перейдя по которой статус клиента меняется
  на **Активный**. В случае нарушения клиентом правил пользования сервисом администратор может его заблокировать,
  статус **Активный** сменится на **Заблокированный**.

  Функциональные возможности:
    * **Неподтвержденный**
        * Просмотр домашней страницы
        * Просмотр каталога инструментов
        * Смена локали
    * **Активный**
        * Просмотр домашней страницы
        * Просмотр каталога инструментов
        * Смена локали
        * Заказ инструмента
        * Оплата заказа
        * Просмотр своих заказов
        * Редактирование личных данных
    * **Заблокированный**
        * Просмотр домашней страницы
        * Просмотр каталога инструментов
        * Смена локали
* **Администратор**  
  Управляет работой сервиса. В его обязанности входит добавление новых инструментов в каталог, изменение их параметров,
  управление заказами клиентов, управление статусом клиентов.

  Функциональные возможности:
    * Просмотр домашней страницы
    * Смена локали
    * Просмотр заказов клиентов
    * Одобрение/отклонение заказа
    * Просмотр каталога инструментов
    * Изменение параметров инструмента
    * Добавление нового инструмента в каталог
    * Просмотр зарегистрированных клиентов
    * Изменение статуса клиента Активный/Заблокированный
    * Редактирование личных данных

[⬆️Оглавление](#оглавление)
____

### Инструменты<a name="инструменты"></a>

Являются предметной областью онлайн сервиса. Обладают различными параметрами, такими как название модели, тип, описание.
Так же для каждого инструмента определена стоимость его аренды за сутки и статус - доступен он для заказа клиентами или
нет.

[⬆️Оглавление](#оглавление)
___

### Заказы<a name="заказы"></a>

Результатом бронирования инструмента клиентом на определенный интервал дат является заказ. Заказ содержит информацию об
инструменте и клиенте, заказавшем его, интервале дат в течении которых клиент будет пользоваться инструментом, общей
сумме аренды.

Заказу присваивается один из шести статусов:

* **Новый**
  
  Присваивается новому заказу после его оформления клиентом.
  
* **Одобрен**
  
  Присваивается заказу после его одобрения администратором.
  
* **Отменен**
  
  Присваивается заказу в случае, если клиент отменил заказ.
  
* **Отклонен**
  
  Присваивается заказу в случае, если администратор отклонил заказ.
  
* **Активный**
  
  Присвивается после оплаты заказа.
  
* **Выполненный**
  
  По окончанию срока аренды статус заказа с **Активный** меняется на **Выполненный**.

В случаях, когда:

* Заказ не был подтвержден администратором до срока наступления аренды.
* Заказ был подтвержден администратором, но не был оплачен.

такой заказ считается недействительным и удаляется.

[⬆️Оглавление](#оглавление)
____

## Tool rental service "ToolRental"<a name="english"></a>
---

### Study project for the course Java Web Development

### Author: Haponenka Ihar

#### [Перейти на русский](#русский)
---

### Table of contents<a name="contents"></a>

* [General description](#description)
* [Users](#users)
* [Tools](#cars)
* [Orders](#orders)

### General description<a name="description"></a>

The web application provides the ability to book online power tools for rent. The client can choose the instrument he
needs by type, model and price, and book the instrument for a specific date. After approving the order by the
administrator, the client needs to pay for the order, it is also possible to cancel the order. After paying for the
order, the client can take the tool for use.

[⬆️Table of contents](#contents)
____

### Users<a name="users"></a>

The application defines the following user roles:

* **Guest**  
  Unauthorized user

  Functionality:
    * Home page view
    * Browse the tool catalog
    * Changing locale
    * Login
    * Register
* **Client**  
  The client, in turn, has three statuses: **Unconfirmed, Active, Blocked**. The guest who has passed the registration
  procedure is assigned the role **Client** and the status **Unconfirmed**. The client's email address specified during
  registration is sent a letter with a link by clicking on which the client's status changes to **Active**. If the
  client violates the rules for using the service, the administrator can block it, status from **Active** will change
  to **Blocked**.

  Functionality:
    * **Unconfirmed**
        * Home page view
        * Browse the tool catalog
        * Changing locale
    * **Active**
        * Home page view
        * Browse the tool catalog
        * Changing locale
        * Tool order
        * Order payment
        * View your orders
        * Editing personal data
    * **Blocked**
        * Home page view
        * Browse the tool catalog
        * Changing locale
* **Admin**  
  Manages the online service. His responsibilities include adding new tool to the catalog, changing their parameters,
  client order management, client status management.

  Functionality:
    * Home page view
    * Changing locale
    * View clients orders
    * Order approving/rejecting
    * Browse the tool catalog
    * Change the rental price, tool status (whether it is available for ordering)
    * Adding a new tool to the catalog
    * Viewing registered clients
    * Client status change Active / Blocked
    * Editing personal data

[⬆️Table of contents](#contents)
____

### Tools<a name="tols"></a>

Are the subject area of an online service. They have various parameters such as model name, type, description. Also,
each tool is assigned a rental price per day, and a status - whether it is available for ordering by clients or not.

[⬆️Table of contents](#contents)
___

### Orders<a name="orders"></a>

The result of the client booking a tool for a certain date interval is an order. The order contains information about
the tool, and the client who ordered it, the range of dates during which the client will use the tool, the total rental
amount.

An order is assigned one of six statuses:

* **New**
  
  Assigned to a new order after the client has placed it.
  
* **Approved**
  
  Assigned to an order after the administrator approves it.
  
* **Canceled**
  
  Assigned to an order if the client canceled the order.
  
* **Rejected**
  
  Assigned to an order if the administrator rejects the order.
  
* **Active**
  
  Assigned after payment of the order.
  
* **Complete**
  
  At the end of the lease, the order status from **Active** changes to **Complete**.

In cases when:

* The order was not confirmed by the administrator before the rental period
* The order was confirmed by the administrator but was not paid

such an order is considered invalid and deleted.

[⬆️Table of contents](#contents)
____