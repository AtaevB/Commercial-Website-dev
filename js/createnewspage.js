async function getNewsByTitle(title) {
    const res = await fetch('http://localhost/data/post.json');
    const news = await res.json();
  
    const selectedNews = news.find(item => item.title === title);
    if (!selectedNews) return;
  
    newsToHTML(selectedNews);
  }
  
// async function getAllNews() { //получение всех новостей при загрузке страницы 

//     const res = await fetch('http://localhost/data/post.json');
//     const news = await res.json(); //преобразование данных из response в json

//     console.log(news);

//     news.forEach(item => newsToHTML(item)); //перебор элементов массива news, чтобы для каждого из них вызывалась функция, превращающая их в html 

// }

window.addEventListener('DOMContentLoaded', getNewsByTitle); //событие домконтентлоудед (загрузка страницы) вызывает получение всех новостей через функцию getallnews

function newsToHTML ({date, image, text, title}) { //преобразует данные в html 

    const news = document.getElementById('news');

    const MAX_WORD_COUNT = 100; // Максимальное количество показываемых слов
    let words = text.split(' ');
    let truncatedText = words.length > MAX_WORD_COUNT ? words.slice(0, MAX_WORD_COUNT).join(' ') + '...' : text;

    let fullTextLink = '';

    if (words.length > MAX_WORD_COUNT) {
        fullTextLink = `<a href="index.html" onclick="showFullText(event)">Читать далее</a>`;
    }

    news.insertAdjacentHTML('afterbegin', `
    
        <div class="news-container">

            <div class="text">

                <h2>${title}</h2>
                <h4>${date}</h4>
                <h3>${truncatedText} ${fullTextLink}</h3>

            </div>

            <img src="${image}">
        

        </div>
    
    `);

}