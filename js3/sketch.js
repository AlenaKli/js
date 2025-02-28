let sound; // Переменная для аудиофайла
let isLoaded = false; // Проверка на загрузку
let amplitude;
let amplitudes = [];
let fft;

function preload() {
    soundFormats('mp3', 'wav');
    sound = loadSound('assets/yee-king_track.mp3', () => {
        console.log("sound is loaded!");
        isLoaded = true;
    });
    sound.setVolume(0.2); // Установим громкость на 20%
}

function setup() {
    createCanvas(1024, 1024);
    textAlign(CENTER); // Центрируем текст
    textSize(32);
    
    amplitude = new p5.Amplitude();
    for (let i = 0; i < 512; i++) {
        amplitudes.push(0);
    }
    
    fft = new p5.FFT();
}

function draw() {
    background(0);
    fill(255);
    
    if (!isLoaded) {
        text("Loading sound...", width / 2, height / 2);
    } else {
        if (!sound.isPlaying()) {
            text("Press any key to play sound", width / 2, height / 2);
        } else {
            let level = amplitude.getLevel();
            amplitudes.push(level);
            amplitudes.shift();
            text(level, width / 2, 40);
            let size = map(level, 0, 0.20, 100, 200);
            ellipse(width / 2, height / 2, size, size);

            let freqs = fft.analyze();
            stroke(0, 150, 0);
            for (let i = 0; i < freqs.length; i++) {
                line(i, height, i, height - freqs[i] * 4);
            }

            noStroke();
            let energy = fft.getEnergy("bass");
            fill("#FF0000");
            ellipse(width / 4, height / 2, 100 + energy);

            let high_energy = fft.getEnergy("highMid");
            fill("#0000FF");
            ellipse(width * 3 / 4, height / 2, 100 + high_energy);
        }
    }
}

function keyPressed() {
    if (isLoaded && !sound.isPlaying()) {
        let r = map(mouseX, 0, width, 0.5, 4.0); // Регулировка скорости воспроизведения
        sound.loop(0, r); // Зацикливаем музыку
    }
    if (key == ' ') { // Останавливаем/воспроизводим аудио при нажатии пробела
        if (sound.isPaused()) {
            sound.play();
        } else {
            sound.pause();
        }
    }
}