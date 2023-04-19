const img = document.getElementById('zoomImg');
let scale = 1;

setInterval(() => {
  if (scale === 1.2) {
    scale = 1;
  } else {
    scale = 1.2;
  }
  img.style.transform = `scale(${scale})`;
}, 5000);   