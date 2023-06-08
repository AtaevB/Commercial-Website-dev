async function getAllNews() { //получение всех новостей при загрузке страницы 

    const res = await fetch('http://localhost/data/post.json');
    const news = await res.json(); //преобразование данных из response в json

    console.log(news);

    news.forEach(item => newsToHTML(item)); //перебор элементов массива news, чтобы для каждого из них вызывалась функция, превращающая их в html 

}

window.addEventListener('DOMContentLoaded', getAllNews); //событие домконтентлоудед (загрузка страницы) вызывает получение всех новостей через функцию getallnews

function newsToHTML ({date, image, text, title}) { //преобразует данные в html 

    const news = document.getElementById('news');

    const MAX_WORD_COUNT = 50; // Максимальное количество показываемых слов
    let words = text.split(' '); // Разделитель слов - пробел
    let truncatedText = words.length > MAX_WORD_COUNT ? words.slice(0, MAX_WORD_COUNT).join(' ') + '...' : text; // Переменная содержит сокращенный текст, если в изначальном больше 50 слов. Иначе текст сокращаться не будет
    let fullTextLink = '';

    if (words.length > MAX_WORD_COUNT) {
        fullTextLink = `<a href="index.html" onclick="redirectToArticle(event, '${title}')">Читать далее</a>`;
    }

    news.insertAdjacentHTML('afterbegin', `
    
        <div class="news-container">

            <div class="text">

                <h2>${title}</h2>
                <h4>${date}</h4>
                <h3>${truncatedText} ${fullTextLink}</h3>

            </div>

            <a href="index.html" onclick="redirectToArticle(event, '${title}')" class="image-link">
            <img src="${image}">
            </a>
        

        </div>
    
    `);

    

}
function redirectToArticle(event, title) {
        event.preventDefault(); // Отменяем переход по ссылке по умолчанию, чтобы сначала передались параметры
        window.location.href = `index.html?title=${encodeURIComponent(title)}`; // Переходим на новую страницу, передав заголовок новости в параметрах URL
      }