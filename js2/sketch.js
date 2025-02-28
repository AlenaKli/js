// Функция для изменения текста в заголовке h1
const functionH1 = () => {
    document.querySelector("h1").innerHTML = "Hello, sunshine!";
};

// Находим кнопку по селектору и добавляем обработчик клика
const button = document.querySelector("button");
button.addEventListener("click", () => {
    // Изменяем стиль кнопки при нажатии
    button.style.background = "#F100A2";
    button.style.color = "#FFFFFF";
    button.style.border = "4px double #05A203";

    // Вызываем функцию для изменения текста в заголовке
    functionH1();
});