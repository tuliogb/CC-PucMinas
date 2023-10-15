if (localStorage.getItem("usuarios") === null ||  localStorage.getItem("usuarios") === "[]") {
  localStorage.setItem("usuarios", JSON.stringify([]));
}

if (localStorage.getItem("logado") === "true") {
  window.location.href = "produtos.html";
}

function registrar(event) {
  event.preventDefault(); // Evita o comportamento padrão de envio do formulário

  var email = document.getElementById("email").value;
  var senha = document.getElementById("senha").value;

  var usuario = {
      email: email,
      senha: senha,
  };

  var usuarios = JSON.parse(localStorage.getItem("usuarios"));
  usuarios.push(usuario);
  localStorage.setItem("usuarios", JSON.stringify(usuarios));
  alert("Registro concluído com sucesso");
}
  
var btnRegistrar = document.querySelector(".btn-registrar");

if (btnRegistrar){
  btnRegistrar.addEventListener("click", registrar);
}

function login(event) {
  event.preventDefault(); // Evita o comportamento padrão de envio do formulário

  // Obter os valores dos campos de entrada de login
  var email = document.getElementById("loginEmail").value;
  var senha = document.getElementById("loginSenha").value;

  // Verificar se as credenciais são válidas
  var usuarios = JSON.parse(localStorage.getItem("usuarios"));
  var usuario = usuarios.find(usuario => usuario.email === email);
  if (usuario === undefined) {
    alert("Email inválido");
    return;
  }

  
  var storedSenha = usuario.senha;

  if (storedSenha === senha) {
    alert("Login bem-sucedido");
    // Redirecionar para a página de estoqur
    window.location.href = "produtos.html";
    //Criar variavel global para o sistema reconhecer que está logado:
    localStorage.setItem("logado", JSON.stringify(true));
    localStorage.setItem("usuarioLogado", JSON.stringify(usuario));
  } else {
    localStorage.setItem("logado", JSON.stringify(false));
    alert("Credenciais inválidas");
    // Lidar com credenciais inválidas, como exibir uma mensagem de err
  }
}

var btnEntrar = document.querySelector(".btn-entrar");
if(btnEntrar){
  btnEntrar.addEventListener("click", login);
}

