const rotatelogo = document.getElementById("rotatelogo");

let rotation = 0;

function rotateLogo() {
  rotation += 5;
  rotatelogo.style.transform = `rotate(${rotation}deg)`;
}

setInterval(rotateLogo, 50);