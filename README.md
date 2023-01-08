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

В перспективе планируется:

- Рефакторинг оформления build.gradle
- Внедрение firebase
- Добавить различное тестирование функционала
- Переписать UI под Jetpack Compose
- Переписать асинхронные задачи с RxJava на Coroutines и провести рефакторинг создания моделек data classes
- Расширить проект с добавлением новых экранов
