let music; // Переменная для музыкального файла
let output = []; // Массив волн, каждая волна - массив точек {x, y}
let startX; // Начальная позиция для рисования
let startY; // Начальная позиция для рисования
let endY; // Конечная позиция для рисования
let spectrumWidth; // Ширина спектра, где рисуются линии
let speed = 1; // Скорость для движения линии по оси Y
let fft; // Объект для анализа частот помогает разложить сложные частоты на более мелкие

function preload(){
    music = loadSound('assets/parsRadio_loop.mp3'); // Загрузка музыки
}

function setup(){
   createCanvas(800, 800); // Создание холста
    startX = width / 5; // Позиция начала рисования на оси X
    endY = height / 5; // Конечная позиция по оси Y для удаления волн
    startY = height - endY; // Начальная позиция по оси Y для волн
    spectrumWidth = (width / 5) * 3; // Ширина спектра
    fft = new p5.FFT(); // Инициализация FFT 
}

function draw(){
    background(0); // Задний фон
    stroke(255); // Цвет линий
    strokeWeight(2); // Толщина линий

    // Каждые 45 кадров добавляем новую волну
    if(frameCount % 45 == 0) 
        addWave();
    
    // Итерируем по всем волнам и рисуем их
    for (let i = 0; i < output.length; i++) {
        let tmp = output[i]; // Получаем текущую волну
        noFill();
        beginShape(); // Начинаем рисовать форму
        for (let j = 0; j < tmp.length; j++) {
            tmp[j].y -= speed; // Сдвигаем по оси Y
            vertex(tmp[j].x, tmp[j].y); // Добавляем точку к линии
        }
        endShape(); // Завершаем рисование формы

        // Если волна выходит за пределы экрана, удаляем её
        if (tmp[0].y < endY)
            output.splice(i, 1);
    }
}

// Функция добавления волны
function addWave(){
    let tmp = fft.waveform(); // Получаем данные о форме волны
    let small_scale = 3, bigScale = 40; // Масштабы изменения амплитуды
    let wape_output = []; // Массив точек для текущей волны
    let x, y;

    // Итерируем по всем 1024 частотам
    for (let i = 0; i < tmp.length; i++) {
        if (i % 20 == 0) { // Берем каждую 20-ю частоту для упрощения
            x = map(i, 0, 1024, startX, startX + spectrumWidth); // Маппим частоту на ось X
            // Уменьшаем изменение амплитуды на краях спектра
            if (i < 1024 * 1 / 4 || i > 1024 * 3 / 4)
                y = map(tmp[i], -1, 1, -small_scale, small_scale); // Меньшее изменение
            else
                y = map(tmp[i], -1, 1, -bigScale, bigScale); // Большое изменение
            wape_output.push({x: x, y: startY + y}); // Добавляем точку в волну
        }
    }
    output.push(wape_output); // Добавляем текущую волну в общий массив волн
}

function mousePressed(){
    music.loop(); // Запускаем музыку по клику мыши
}