# Automation_API

API test automation for the IT School course: **TestNG**, **Rest Assured**, **JavaFaker**, and **Jackson**. Companion to the UI project [Proiect_Automation_ITSchool](https://github.com/dia-dianaaa/Proiect_Automation_ITSchool).

## Prerequisites

- JDK 26
- Maven 3.9+
- `JAVA_HOME` pointing at JDK 26

## IDE setup

Use Cursor or VS Code with the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack). See [`.vscode/extensions.json`](.vscode/extensions.json) and [`.vscode/settings.json`](.vscode/settings.json).

## Configuration

| File | Purpose |
|------|---------|
| [`src/test/resources/config.properties`](src/test/resources/config.properties) | `apiBaseUrl`, `apiPassword` |
| [`src/test/resources/config.properties.example`](src/test/resources/config.properties.example) | Template |

Set a real password in `config.properties` (or use gitignored `config.local.properties` and load it in your own setup). **Never commit real credentials.**

## Project structure

```
src/test/java/
  Acount/       # DemoQA API response models
  Config/       # ApiConfig (reads config.properties)
  Tests/        # ApiSmokeTest, BookStore flow scaffold
src/test/resources/
  config.properties
  body.json     # BookStore request body template
```

## Running tests

Smoke test (GET home page):

```bash
mvn test
```

Full DemoQA BookStore flow is in [`BookStore.java`](src/test/java/Tests/BookStore.java) as `bookStoreFlow` — disabled by default until `apiPassword` is set. Enable the test when ready:

```java
@Test(enabled = true)
public void bookStoreFlow() throws IOException { ... }
```

## Git ignore notes

`target/`, `config.local.properties`, `.env`, `.cursor/`, and user-specific IDE files are ignored. Shared `.vscode` recommendations and settings are committed.

## License

Course / personal educational project.
