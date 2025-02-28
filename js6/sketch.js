let previousMouseX;
let previousMouseY;

let canvas;
let editMode;
let currentShape = [];
let currentColor = [0, 0, 0]; // Цвет для рисования (по умолчанию черный)
let fillColor = [255, 0, 0, 100]; // Цвет заливки фигуры (по умолчанию красный)
let strokeWeightVal = 1; // Толщина линии

function setup() {
    canvas = createCanvas(800, 800);
    background(200);
    stroke(0);

    editMode = 0; // Начальный режим - рисование

    // Кнопки для управления
    createButton('Clear').mousePressed(clearCanvas);
    createButton('Create Shape').mousePressed(toggleEditMode);

    // Кнопки для рисования
    createButton('Pencil').mousePressed(() => { 
        strokeWeightVal = 1; 
        stroke(currentColor); 
        strokeWeight(strokeWeightVal); // Устанавливаем толщину для обычного карандаша
    });
    createButton('Thick Pencil').mousePressed(() => { 
        strokeWeightVal = 5; 
        stroke(currentColor); 
        strokeWeight(strokeWeightVal); // Устанавливаем толщину для толстого карандаша
    });

    // Кнопки для выбора цвета линии
    createButton('Black').mousePressed(() => { 
        currentColor = [0, 0, 0]; 
        stroke(currentColor); 
    });
    createButton('Red').mousePressed(() => { 
        currentColor = [255, 0, 0]; 
        stroke(currentColor); 
    });
    createButton('Green').mousePressed(() => { 
        currentColor = [0, 255, 0]; 
        stroke(currentColor); 
    });
    createButton('Blue').mousePressed(() => { 
        currentColor = [0, 0, 255]; 
        stroke(currentColor); 
    });

    // Кнопки для выбора цвета заливки
    createButton('Fill Red').mousePressed(() => { fillColor = [255, 0, 0, 100]; });
    createButton('Fill Green').mousePressed(() => { fillColor = [0, 255, 0, 100]; });
    createButton('Fill Blue').mousePressed(() => { fillColor = [0, 0, 255, 100]; });
}

function draw() {
    // Логика рисования линий
    if (mouseIsPressed && editMode === 0 && mousePressOnCanvas()) {
        if (previousMouseX === undefined || previousMouseY === undefined) {
            previousMouseX = mouseX;
            previousMouseY = mouseY;
        } else {
            line(previousMouseX, previousMouseY, mouseX, mouseY);
            previousMouseX = mouseX;
            previousMouseY = mouseY;
        }
    }

    // Логика рисования и редактирования фигуры
    if (editMode === 1 || editMode === 2) {
        beginShape();
        for (let i = 0; i < currentShape.length; i++) {
            vertex(currentShape[i].x, currentShape[i].y);
        }

        // Включаем заливку, если фигура завершена
        if (editMode === 2) {
            fill(fillColor);  // Цвет заливки
            noStroke();  // Без обводки
        }

        endShape(CLOSE);

        // Рисование точек для редактирования вершин
        if (editMode === 2) {
            fill(255, 0, 0); // Редкие вершины
            for (let i = 0; i < currentShape.length; i++) {
                ellipse(currentShape[i].x, currentShape[i].y, 10, 10);
            }
        }
    }
}

function mousePressed() {
    if (mousePressOnCanvas()) {  // Убедимся, что нажали на холст
        // Убедимся, что рисуем только в режиме рисования фигуры
        if (editMode === 1) {
            currentShape.push({ x: mouseX, y: mouseY });
        }
    }
}

function mouseReleased() {
    if (editMode === 2) {
        // Завершаем редактирование фигуры
        editMode = 0;
    }
}

function toggleEditMode() {
    if (editMode === 0) {
        editMode = 1; // Начало рисования формы
    } else if (editMode === 1) {
        editMode = 2; // Завершение формы
    } else {
        editMode = 0; // Завершаем рисование формы
        currentShape = []; // Очистка текущей фигуры
    }
}

function clearCanvas() {
    background(200);
    previousMouseX = undefined;
    previousMouseY = undefined;
    currentShape = [];
    editMode = 0;
}

function mousePressOnCanvas() {
    return mouseX > 0 && mouseX < width && mouseY > 0 && mouseY < height;
}