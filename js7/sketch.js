// Получаем элементы
const myButton = document.getElementById("myButton");
const myRange = document.getElementById("myRange");
const output = document.getElementById("output");

// Обработчик события для клика по кнопке
myButton.addEventListener("click", function() {
    alert("Вы нажали на кнопку!");
    myButton.style.backgroundColor = "#F100A2";  // Изменим цвет кнопки при клике
});

// Обработчик события для наведения на кнопку
myButton.addEventListener("mouseover", function() {
    myButton.style.backgroundColor = "#05A203";  // Изменим цвет при наведении
});

// Обработчик события для изменения значения ползунка
myRange.addEventListener("input", function() {
    output.textContent = "Текущее значение: " + myRange.value;  // Обновляем текст с текущим значением ползунка
});

// Дополнительно, добавим обработчик для события "mouseout" (когда убираем мышку с кнопки)
myButton.addEventListener("mouseout", function() {
    myButton.style.backgroundColor = "";  // Возвращаем исходный цвет кнопки, когда убрали курсор
});