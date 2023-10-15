document.addEventListener("DOMContentLoaded", function() {
    const botao = document.getElementById("btntemporario");
    botao.addEventListener("click", () => {
      var li = document.createElement('li');
      var menu = document.getElementById("menu");
      li.className = 'dropdown-item';
      li.textContent = "Produto X acabando";
      menu.appendChild(li);
    });
  });