# Handgum

Данный проект создан в целях собственного обучения.
Приложение отображает экран в виде списка и экран с более детальным представлением рецензий фильмов от New York Times применяя [Movie Reviews API](https://developer.nytimes.com/docs/movie-reviews-api/1/routes/reviews/%7Btype%7D.json/get) 

<p align="left">
<img src="https://user-images.githubusercontent.com/47061951/226287879-0e7f4efe-a44d-4a35-b18b-0086fe981572.jpeg" alt="drawing" width="220"/>
&nbsp;&nbsp;
<img src="https://user-images.githubusercontent.com/47061951/226287895-124f52d7-a8cd-4931-a34c-9a282f681a0c.jpeg" alt="drawing" width="220"/>
</p>


В проекте реализована 

- многомодульность с DI от Dagger 2 c межмодульной передачей зависимостей по аналогии с докладом [Как мы делаем Яндекс.Карты для Android: DI](https://www.youtube.com/watch?v=COzmONYAY3U) 
- межмодульная навигация через [NavController от Navigation component](https://developer.android.com/guide/navigation?hl=en)
- [Миграция на Kotlin DSL](https://docs.gradle.org/current/userguide/migrating_from_groovy_to_kotlin_dsl.html), обьявление зависимостей через [version catalog](https://docs.gradle.org/current/userguide/platforms.html#sub::toml-dependencies-format), инкапсуляция общей логики сборки модулей в [Convention Plugins](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html#sec:convention_plugins) 
 
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
где через findDependencies предоставляется  RevListDependencies с интерфейс-зависимостями с реализацией из AppComponent. 
Далее создаем ViewModel с указанием в @Inject constructor() инерактора, а в нем так же указываем наш интерфейс взаимодействия с БД модельки

# В перспективе планируется:

- Переписать UI под Jetpack Compose
- Переписать асинхронные задачи с RxJava на Coroutines и провести рефакторинг создания моделек data classes
- Внедрение detekt (static code analysis) 
- Внедрение firebase
- Добавить различное тестирование функционала
- Расширить проект с добавлением новых экранов
- Добавление Feature toggles для нового функционала
