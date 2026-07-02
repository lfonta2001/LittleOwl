<a name="readme-top"></a>

<div align="center">

# LITTLE OWL

Little Owl is a Discord bot designed to be scalable.

</div>

## Sections

- [Features](#features)
- [Project Status](#project-status)
- [Motive](#why-little-owl)
    - [Clarifications](#general-clarifications)
- [Running your own bot](#running-your-own-bot)
    - [Requirements](#requirements)
    - [Running locally](#running-locally)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Features

`` # marked points are yet to be implemented``

- Music bot functionality #
- Modular architecture #
- Gradle-based setup
- Environment based configuration
- Message and command moderation #
- Customizable language support #

<p align="right" >(<a href="#readme-top">go up</a>)</p>

## Project status

Little Owl is currently in the early stages of development. The general structure and implementation may change drastically before production release.
To check the current progress, see `ROADMAP.md` file.

<p align="right" >(<a href="#readme-top">go up</a>)</p>

## Why Little Owl?

This project was originally started because of the need of a music bot that my friends and I had full control over, without the limitations of paid subscriptions or other requirements.
It later evolved into a more general-purpose bot and a learning project focused on Java, object-oriented programming, and scalable application design.

### General clarifications

* In early commits you can see a lot of empty files and useless classes, that is because I migrated from the same project in TypeScript and made the files to have a general structure following what I was doing.
* Following the previous point, take into account that pretty much every empty file can be deleted or changed drastically up until the first production release.

* You can ask why you would migrate all the project to Java.
Well, I like OOP, am starting to learn Java, and making the project in TypeScript was making me feel a little dizzy regarding the lack of restrictions towards what I am allowed to do in terms of language structure;
apart from that, the project was barely in a middle point, so I considered it to be a good point to change, without having to rewrite too much.

<p align="right" >(<a href="#readme-top">go up</a>)</p>

## Running your own bot

### Requirements

* Java 21 (I'm using the JDK version temurin-21 from Eclipse Temurin)
* Gradle, currently 9.6.0

### Running locally

1. Clone the repository
    ```sh
   git clone https://github.com/lfonta2001/LittleOwl.git
    ```
    
   or
   
    ```sh
   git clone git@github.com:lfonta2001/LittleOwl.git
    ```
3. Update or create variables in `.env` file or set them as environment variables. The file `.evn.example` is provided as a template.

4. Run the bot:
   ```sh
   ./gradlew run
   ```
   
   On Windows:
   
   ```sh
   gradlew.bat run
   ```
   
Feel free to create your own bot using this as a starting point or take all the ideas you want.

<p align="right" >(<a href="#readme-top">go up</a>)</p>

## Contributing

**Any help is welcome!**

Creating this in community would be a huge honor to me, that is the main objective when making this an open-source project.

If you have any questions or suggestions, feel free to open an issue with the tag "enhancement" or contact me [here](#contact).

You can help by:

- Reporting bugs
- Suggesting new features
- Improving documentation
- Implementing new commands
- Translating the bot to your language
- Refactoring code
- Writing tests

Before making a large change, please open an issue so we can discuss the idea first.

To make a contribution, follow the steps below:

1. [_Fork_](https://github.com/lfonta2001/LittleOwl/fork) the repository
2. Clone your fork to your local machine (`git clone <URL of your fork>`)
3. Add the original repository as a remote (`git remote add upstream <URL of original repository>`)
4. Create a new branch (`git checkout -b <name of your branch>`)
5. Make your changes and commit them (`git commit -m "<your commit message>"`)
6. Push your changes to your fork (`git push origin <name of your branch>`)
7. Create a new [_Pull Request_](https://github.com/lfonta2001/LittleOwl/pulls) from your fork to the original repository

<p align="right" >(<a href="#readme-top">go up</a>)</p>

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

<p align="right" >(<a href="#readme-top">go up</a>)</p>

## Contact

- [Mail](mailto:lafd.2810@gmail.com)
- [Discord](https://discord.com/users/725554259634159636)

<p align="right" >(<a href="#readme-top">go up</a>)</p>
