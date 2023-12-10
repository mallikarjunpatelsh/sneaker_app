Sneaker APP

This project is with Clean architecture

Overview:</br>
The First Home screen will be list of sneaker </br>
On selecting sneaker it will be navigating to details screen</br>
In details screen we can add the item to cart </br>
Checkout page page displays item in the cart</br>
We can remove product from checkout page</br>
Complete Exceptions are handled in BaseDatasource class where all remote and local datsource operation is evaluavated
Exception is handled while reading json.</br></br>

Details:</br>
Three main packages - data, domain, ui</br>
- data contains Dao, Datasource, Repository</br>
- domain contains data classes and entities and it caontains UseCase classes</br>
- ui contains Activity, Fragment, adapters, viewholders, ui model classes, callbacks</br>
</br>
Data transfer is from data layer(DAO/service -> datasource -> repository) to domain(UseCase) and ui(viewmodel -> fragments/activity)</br>
Flow is used in the data transfer

Note:</br>
Size selction is functional, it is just hardcodeed and having the feature of selction and unselection</br>
color selection is a hard coded ui</br>
Reading json data instead of Retroift api call</br>
Cart item will be saved in the DB - ROOM DB</br>



