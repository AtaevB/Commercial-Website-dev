async function getSelectedNews(title) {
    const res = await fetch('http://localhost/data/post.json');
    const news = await res.json();
  
    const selectedNews = news.find(item => item.title === title);
    if (!selectedNews) return;
  
    newsToHTML(selectedNews); // Допустим, у вас есть функция newsToHTML, которая отображает новость на странице

    console.log(selectedNews);
  }
  
  function newsToHTML ({image, text, title}, selectedNews) { //преобразует данные в html 

    const news = document.getElementById('news');
    news.innerHTML = '';

    news.insertAdjacentHTML('afterbegin', `
    
        

        <div class="photo-wrapper"><img src="${image}"></div>

        <div class="text-begin">
            <h2>${title}</h2>
        </div>

        <div class="text-wrapper">
                <h3>${text}</h3>
        </div>

    
    
    `);

}

function redirectToArticle(event, title) {
    event.preventDefault();
    window.location.href = `index.html?title=${encodeURIComponent(title)}`;
  }

  const urlParams = new URLSearchParams(window.location.search);
  const title = urlParams.get('title');
  getSelectedNews(title);