const centeredElement = document.getElementById('baner');
const stuckElement = document.getElementById('text-begin');

window.addEventListener('load', () => {
  const centeredElementHeight = centeredElement.offsetHeight;
  stuckElement.style.top = centeredElementHeight + 'px';
});