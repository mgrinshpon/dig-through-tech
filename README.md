# dig-through-tech
[digthrough.tech](digthrough.tech) source code. Project structure is very mutable right now and in a general state of flux.

## Projects

### dtt-common
Common files safe to be used by all services. Contains absolutely no dependencies from other services.

### dtt-dcds
Stands for "deck creation/destruction service." Microservice for creating and removing decks from the deck database.

### dtt-monte-carlo
Deck monte-carlo simulations. One day... :(

## Contributing
Interested in contributing? Besides the normal GitHub pull requests and discussions, there's a [discord channel](https://discord.gg/7r2SJ) available.

### Code Style
Please use [Google's Java Codestyle](https://google.github.io/styleguide/javaguide.html) with one difference: make sure all method parameters are auto-saved as "final." Google's Java Codestye is also available as a [convenient download from GitHub](https://github.com/google/google-java-format/blob/master/README.md).