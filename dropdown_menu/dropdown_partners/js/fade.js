const header = document.getElementById('main-header');

window.addEventListener('scroll', function() {
  if (window.pageYOffset > 600) {
    header.classList.remove('translucent');
  } else {
    header.classList.add('translucent');
  }
});