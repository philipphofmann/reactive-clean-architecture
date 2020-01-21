# Features

A feature contains usually four components: 
* __business__: contains the enterprise business logic as well as the use cases. 
* __ui__: contains Android UI including the ViewModels. 
* __repository__: The repository module implements the [repository pattern][1] and is the single source 
of truth for the data for the useCases. The repository here is based on always subscribing to 
the latest state.
* __main__: the dirty main model that knows all modules and plugs them together.

[1]: https://martinfowler.com/eaaCatalog/repository.html
 
