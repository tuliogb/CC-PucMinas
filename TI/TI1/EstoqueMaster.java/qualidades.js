//Seleciona todos os elementos com a classe qualidade:
const qualidades = document.querySelectorAll('.qualidade');


// O loop percorre o array de qualidades:
qualidades.forEach(qualidade => {

    //Quando o elemento que estiver sendo iterado tiver um mouseover, os outros elementos diminuirão a sua escala
    qualidade.addEventListener('mouseover', () => {

    //Pega os elementos anteriores do elemento hovered para alterar sua escala
    let sibling = qualidade.previousElementSibling;
    while (sibling) {
      if (sibling.classList.contains('qualidade')) {
        sibling.style.transform = 'scale(0.95)';
      }
      sibling = sibling.previousElementSibling;
    }

    //Peda os elementos posteriores para fazer o mesmo que antes
    sibling = qualidade.nextElementSibling;
    while (sibling) {
      if (sibling.classList.contains('qualidade')) {
        sibling.style.transform = 'scale(0.95)';
      }
      sibling = sibling.nextElementSibling;
    }
  });

  //No mouseout, o scale é retirado do elemento, em dúvida se isso é necessário:
  qualidade.addEventListener('mouseout', () => {
    let sibling = qualidade.previousElementSibling;
    while (sibling) {
      if (sibling.classList.contains('qualidade')) {
        sibling.style.transform = '';
      }
      sibling = sibling.previousElementSibling;
    }

    sibling = qualidade.nextElementSibling;
    while (sibling) {
      if (sibling.classList.contains('qualidade')) {
        sibling.style.transform = '';
      }
      sibling = sibling.nextElementSibling;
    }
  });
});
