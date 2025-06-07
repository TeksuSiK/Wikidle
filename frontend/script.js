const startScreen = document.getElementById('start-screen');
const gameWindow = document.getElementById('game');
const startButton = document.getElementById('start-button');
const wikiFrame = document.getElementById('wiki-frame');
const targetWord = document.getElementById('target-word');
const stepCounter = document.getElementById('step-counter');
const timerDisplay = document.getElementById('timer');

let gameData = null;
let currentSteps = 0;
let expectedSteps = 0;

function fetchGame(language, difficulty) {
    return fetch(`/game?difficulty=${difficulty}&language=${language}`)
        .then(res => {
            if (!res.ok) throw new Error(`Server error: ${res.status}`);
            return res.json();
        });
}

function updateStepCounter() {
    stepCounter.textContent = `${currentSteps}/${expectedSteps}`;
    if (currentSteps > expectedSteps) {
        stepCounter.classList.add("over-limit");
    } else {
        stepCounter.classList.remove("over-limit");
    }
}

let startTime = null;
let timerInterval = null;

function formatTime(ms) {
    const totalSeconds = Math.floor(ms / 1000);
    const minutes = Math.floor(totalSeconds / 60).toString().padStart(2, '0');
    const seconds = (totalSeconds % 60).toString().padStart(2, '0');
    const milliseconds = Math.floor((ms % 1000) / 10).toString().padStart(2, '0');
    return `${minutes}:${seconds}:${milliseconds}`;
}

function startTimer() {
    startTime = Date.now();
    timerInterval = setInterval(() => {
        const elapsedMs = Date.now() - startTime;
        timerDisplay.textContent = `Czas: ${formatTime(elapsedMs)}`;
    }, 10);
}

function stopTimer() {
    clearInterval(timerInterval);
    timerInterval = null;
}

function setupGame(data) {
    gameData = data;
    currentSteps = 0;
    expectedSteps = data.expectedSteps;

    targetWord.textContent = data.targetTitle;
    updateStepCounter();

    timerDisplay.textContent = "Czas: ";
    startTimer();

    wikiFrame.src = `/proxy?url=${encodeURIComponent(data.startUrl)}`;
    startScreen.style.display = 'none';
    gameWindow.style.display = 'flex';
}

function handleMessage(event) {
    if (!event.data.currentUrl || !gameData) return;

    try {
        const parsed = new URL(event.data.currentUrl);
        const proxiedUrl = parsed.searchParams.get('url');
        if (!proxiedUrl) return;

        currentSteps++;
        updateStepCounter();

        if (proxiedUrl === gameData.targetUrl) {
            stopTimer();

            alert(`Gratulacje, wygrałeś, używając ${currentSteps}/${expectedSteps}. ${timerDisplay.textContent}`);

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
