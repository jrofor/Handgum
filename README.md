# Handgum

Данный проект создан в целях собственного обучения.
Приложение отображает экран в виде списка и экран с более детальным представлением рецензий фильмов от New York Times применяя [Movie Reviews API](https://developer.nytimes.com/docs/movie-reviews-api/1/routes/reviews/%7Btype%7D.json/get) 

В проекте реализована 

- многомодульность с DI от Dagger 2 и межмодульной передачей зависимостей по аналогии с докладом [Как мы делаем Яндекс.Карты для Android: DI](https://www.youtube.com/watch?v=COzmONYAY3U) 
- межмодульная навигации через [NavController от Navigation component](https://developer.android.com/guide/navigation?hl=en)
 
Приложение состоит из следующих модулей:

- **app** - содержащий Application, MainActivity и основной компонент DI AppComponent
- **core** - содержит утилиты, open классы, инструменты для предоставления Dependencies
- **api-network** - для rest api запросов, использующий Retrofit
- **database** - база данных приложения на основе Room
- **common-entity** - общие модельки и интерфейсы применяемые в разных модулях
- **navigation** - функционал для построения межмодульной навигации через NavController
- **navigation-api** - содержит интерфейсы для межмодульной навигации
- **revlist** (feature-модуль) - фича экрана списка рецензий
- **revdetails** (feature-модуль) - фича экрана рецензии

# Многомодульность с DI
Схема предоставления Context для модуля базы данных:

<img width="1241" alt="Схема предоставления Context для модуля базы данных" src="https://user-images.githubusercontent.com/47061951/216811117-9e6e3bb1-33e7-4b12-b405-e4f7bdd2711c.png">


Схема дальнейшего предоставления репозитория базы данных для feature модуля:

<img width="1304" alt="Схема дальнейшего предоставления репозитория  базы данных для feature модуля" src="https://user-images.githubusercontent.com/47061951/216811269-19b77f2d-c5cd-451a-bb34-c91c6d56d327.png">

По итогу в RevListFragment внедряется компонент
```kotlin
DaggerRevListComponent.factory().create(findDependencies()).inject(this)
```
где через findDependencies предоставляется  RevListDependencies с интерфейс-зависимостями с реализаций из AppComponent. 
Далее создаем ViewModel с указанием в @Inject constructor() инерактора, а в нем так же указываем наш интерфейс взаимодействия с БД модельки

# В перспективе планируется:

- Рефакторинг оформления build.gradle
- Внедрение firebase
- Добавить различное тестирование функционала
- Переписать UI под Jetpack Compose
- Переписать асинхронные задачи с RxJava на Coroutines и провести рефакторинг создания моделек data classes
- Расширить проект с добавлением новых экранов
- Добавление Feature toggles для нового функционала
