# Wikidle — Wikipedia Path Game

Wikidle is a web-based game where the player navigates through Wikipedia articles starting from a random page, aiming to reach a specific target article by clicking links inside the content. The game challenges your knowledge and navigation skills while keeping you inside Wikipedia.

## Motivation

There wasn’t any website allowing players to choose difficulty levels or language preferences for this type of Wikipedia path game. Wikidle fills this gap by providing customizable gameplay with multi-language support and adjustable challenges.

## Features

- Random start article based on selected difficulty and language.
- Proxy backend fetching and cleaning Wikipedia pages, keeping the user within Wikipedia.
- Frontend with iframe embedding the Wikipedia content, styled and secured.
- Real-time URL tracking inside iframe to detect when the player reaches the target article.

## Technologies Used

- **Backend:** Java with Javalin and Jsoup for HTML parsing
- **Frontend:** JavaScript, HTML, CSS
- **Deployment:** Docker

## Future Features

- Support for more languages beyond initial set.
- More precise filtering of Wikipedia content (e.g., better handling of references, notes, and extraneous elements).
- Option for the player to choose a custom starting article or select from specific categories
- Enhanced UI/UX improvements.
- User progress tracking and stats.
- Multiplayer or leaderboard support.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

## License

This project is licensed under the Apache License 2.0.
