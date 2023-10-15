const cards = document.querySelectorAll('.card');

fetch('https://fakestoreapi.com/products')
  .then(response => response.json())
  .then(data => {

    for (let i = 0; i <= 11; i++) {
      const produto = data[i];
      const card = cards[i];

      const cardFoto = card.querySelector('.card-img-top');
      const cardNome = card.querySelector('.card-title');
      const cardDescricao = card.querySelector('.card-text');
      const cardPreco = card.querySelector('.btn');

      cardFoto.src = produto.image;
      cardFoto.style.width = '250px';
      cardFoto.style.height = '250px';
      cardNome.textContent = produto.title;
      cardDescricao.textContent = produto.description;
      cardPreco.textContent = `Detalhes ($${produto.price}...)`;

      cardPreco.addEventListener('click', () => {
        redirectToProductDetails(i + 1);
      });
    }
  })
  .catch(error => {
    console.error('Erro ao fazer a solicitação HTTP:', error);
  });

function redirectToProductDetails(productId) {
  const url = `detalhes.html?id=${productId}`;
  window.location.href = url;
}



  //-----------------------------------------------------------------------------------

  const searchInput = document.getElementById('search');
  const searchBtn = document.getElementById('searchBtn');
  const productList = document.getElementById('productList');
  
  searchBtn.addEventListener('click', searchProducts);

  //----------------------------------------------------------------------------------- 
  
  function searchProducts() {
    const searchTerm = searchInput.value.toLowerCase();
  
    axios.get(`https://fakestoreapi.com/products`)
      .then(response => {
        const data = response.data;
  
        const filteredProducts = data.filter(product => product.title.toLowerCase().startsWith(searchTerm.charAt(0)));
 
        productList.innerHTML = '';
  
        if (filteredProducts.length === 0) {
          productList.innerHTML = 'Nenhum produto encontrado.';
          return;
        }
  
        filteredProducts.forEach(product => {
          const productCard = document.createElement('div');
          productCard.classList.add('product-card');
          productCard.innerHTML = `
            <img src="${product.image}" alt="${product.title}" style="width: 250px; height: 250px;">
            <h5>${product.title}</h5>
            <p>${product.description}</p>
            <a href="#" class="btn btn-primary">Buy for $${product.price}</a>
          `;
          productList.appendChild(productCard);
        });
      })
      .catch(error => {
        console.error('Erro ao fazer a solicitação HTTP:', error);
      });
  }

//-----------------------------------------------------------------------------------







  
  
  
  
