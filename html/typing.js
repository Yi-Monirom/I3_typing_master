const WORDS = [
    "shall", "toward", "have", "modern", "try", "eight", "move", "off", "usual", "imagine",
    "quick", "brown", "fox", "jumps", "over", "lazy", "dog", "keyboard", "speed", "accuracy"
];
const TEST_LENGTH = 10;
let testWords = [];
let current = 0;
let startTime = null;
let correct = 0;
let finished = false;

function pickWords() {
    let arr = [];
    while (arr.length < TEST_LENGTH) {
        const w = WORDS[Math.floor(Math.random() * WORDS.length)];
        if (!arr.includes(w)) arr.push(w);
    }
    return arr;
}

function renderWords() {
    const wordsDiv = document.getElementById('words');
    wordsDiv.innerHTML = '';
    testWords.forEach((word, idx) => {
        const span = document.createElement('span');
        span.className = 'word' + (idx === current ? ' current' : '');
        span.textContent = word + ' ';
        wordsDiv.appendChild(span);
    });
}

function updateWords(status) {
    const wordsDiv = document.getElementById('words');
    const spans = wordsDiv.querySelectorAll('.word');
    spans.forEach((span, idx) => {
        span.className = 'word' +
            (idx === current ? ' current' : '') +
            (status && status[idx] === true ? ' correct' : '') +
            (status && status[idx] === false ? ' incorrect' : '');
    });
}

function startTest() {
    testWords = pickWords();
    current = 0;
    correct = 0;
    finished = false;
    startTime = null;
    document.getElementById('input').value = '';
    document.getElementById('stats').textContent = '';
    renderWords();
    document.getElementById('input').focus();
}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('input').addEventListener('input', function(e) {
        if (finished) return;
        if (!startTime) startTime = new Date();
        const val = e.target.value.trim();
        if (val.endsWith(' ')) {
            const wordTyped = val.trim();
            if (wordTyped.length > 0) {
                if (wordTyped === testWords[current]) correct++;
            }
            current++;
            e.target.value = '';
            if (current >= testWords.length) {
                finished = true;
                showStats();
            }
            updateWords(getStatus());
        }
    });
    startTest();
});

function getStatus() {
    let status = [];
    for (let i = 0; i < current; i++) {
        status[i] = document.getElementById('input').value.trim() === testWords[i] ? true : false;
    }
    return status;
}

function showStats() {
    const endTime = new Date();
    const seconds = ((endTime - startTime) / 1000);
    const wpm = Math.round((testWords.length / seconds) * 60);
    const accuracy = Math.round((correct / testWords.length) * 100);
    document.getElementById('stats').textContent =
        `WPM: ${wpm} | Accuracy: ${accuracy}%`;
}

function restartTest() {
    startTest();
}