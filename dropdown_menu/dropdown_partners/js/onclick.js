document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.reviews > .review > .letter').forEach(function(el) {
      el.addEventListener('click', function(event) {
        var i_path = el.getAttribute('src');
        var overlay = document.createElement('div');
        overlay.id = 'overlay';
        var magnify = document.createElement('div');
        magnify.id = 'magnify';
        magnify.innerHTML = '<img src="' + i_path + '"><div id="close-popup"><i></i></div>';
        document.body.appendChild(overlay);
        document.body.appendChild(magnify);
        // magnify.style.left = (window.pageXOffset + (document.documentElement.clientWidth - magnify.offsetWidth) / 2) + 'px';
        // magnify.style.top = (window.innerHeight - magnify.offsetHeight) / 2 + 'px';
        overlay.style.display = 'block';
        magnify.style.display = 'block';
      });
    });
  
    document.body.addEventListener('click', function(event) {
      var target = event.target;
      if (target.id == 'close-popup' || target.id == 'overlay') {
        event.preventDefault();
        var overlay = document.querySelector('#overlay');
        var magnify = document.querySelector('#magnify');
        overlay.parentNode.removeChild(overlay);
        magnify.parentNode.removeChild(magnify);
      }
    });
  });