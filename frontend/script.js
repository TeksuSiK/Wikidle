const startScreen = document.getElementById('start-screen');
const gameWindow = document.getElementById('game');
const startButton = document.getElementById('start-button');
const targetTitle = document.getElementById('target-title');
const wikiFrame = document.getElementById('wiki-frame');

let gameData = null;

function fetchGame(language, difficulty) {
    return fetch(`http://localhost:8080/game?difficulty=${difficulty}&language=${language}`)
        .then(res => {
            if (!res.ok) throw new Error(`Server error: ${res.status}`);
            return res.json();
        });
}

function setupGame(data) {
    gameData = data;
    targetTitle.innerText = "Hasło: " + data.targetTitle;
    wikiFrame.src = `http://localhost:8080/proxy?url=${encodeURIComponent(data.startUrl)}`;
    startScreen.style.display = 'none';
    gameWindow.style.display = 'flex';
}

function handleMessage(event) {
    if (!event.data.currentUrl) return;
    if (!gameData) return;

    try {
        const parsed = new URL(event.data.currentUrl);
        const proxiedUrl = parsed.searchParams.get('url');
        if (!proxiedUrl) return;

        const decodedUrl = decodeURIComponent(proxiedUrl);

        if (decodedUrl === gameData.targetUrl) {
            alert("Gratulacje, wygrałeś!");

            gameWindow.style.display = 'none';
            startScreen.style.display = 'flex';

            wikiFrame.src = 'about:blank';
        }
    } catch (e) {
        console.error("Error parsing URL", e);
    }
}

function init() {
    startButton.addEventListener('click', () => {
        const language = document.getElementById('language-select').value;
        const difficulty = document.getElementById('difficulty-select').value;

        fetchGame(language, difficulty)
            .then(setupGame)
            .catch(err => alert("Error starting game: " + err));
    });

    window.addEventListener('message', handleMessage);
}

window.addEventListener('DOMContentLoaded', init);
