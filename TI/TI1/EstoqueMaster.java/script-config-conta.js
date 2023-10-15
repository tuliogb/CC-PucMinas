if (localStorage.getItem("logado") === null || localStorage.getItem("logado") === "false") {
    window.location.href = "login.html";
} else {
    var usuarioLogado = JSON.parse(localStorage.getItem("usuarioLogado"));
}

const campoSenha = document.getElementById('campoSenha');
const campoEmail = document.getElementById('campoEmail');

//Se os inputs acima conterem disabled, serao tornadas textareas:
if (campoSenha.disabled == true) {
    campoSenha.type = "text";
}
if (campoEmail.disabled == true) {
    campoEmail.type = "text";
}
///Apresenta o email e senha nas text areas
function apresentaDados(campoSenha, campoEmail) {
    campoEmail.value = usuarioLogado.email;
    campoSenha.value = usuarioLogado.senha;

    ///Configura toggle de exibir 
    var btnToggleSenha = document.getElementById("btnToggleSenha");

    btnToggleSenha.addEventListener("click", function() {
    if (this.classList.contains("active")) {
        campoSenha.type = "text";
    } else {
        campoSenha.type = "password";
    }
    });
};

apresentaDados(campoSenha, campoEmail);

///Configura botões de editar e salvar
const btnEditar = document.getElementById("BtnEditar");
const btnSalvar = document.getElementById("BtnSalvar");

btnEditar.addEventListener("click", () => {

    btnSalvar.hidden = false;
    btnEditar.hidden = true;

    ///tratamento dos inputs
    campoEmail.disabled = false;
    campoSenha.disabled = false;
    campoSenha.type = "text";


})

btnSalvar.addEventListener("click", () => {

    //tratamento botão editar/salvar
    btnSalvar.hidden = true;
    btnEditar.hidden = false;

    ///tratamento dos inputs
    campoEmail.disabled = true;
    campoSenha.disabled = true;
    campoSenha.type = "password";

    usuarioLogado.email = campoEmail.value;
    usuarioLogado.senha = campoSenha.value;

    localStorage.setItem("usuarioLogado", JSON.stringify(usuarioLogado));
    let usuarios = JSON.parse(localStorage.getItem("usuarios"));
    //ecluir o usuario antigo do localstorage e colocar o usuario logado
    usuarios.splice(usuarios.indexOf(usuarioLogado), 1, usuarioLogado);
    localStorage.setItem("usuarios", JSON.stringify(usuarios));
    alert("Dados alterados com sucesso!");
    
})


//Configura o switch de Notificações
const switchNotif = document.getElementById("switchNotif");
const NotifOnOff = document.getElementById("labelNotif");

switchNotif.addEventListener("change", function() {
    if (this.checked) {
        console.log("Switch ativado");
        NotifOnOff.querySelector("h3").textContent = "Notificações";
    } else {
        console.log("Switch desativado");
        NotifOnOff.querySelector("h3").textContent = "Notificações";
    }
});

// Configura o botão de dentro do modal
const btnLimpaDadosModal = document.getElementById("btnLimparDadosModal");

btnLimpaDadosModal.addEventListener("click", () => {

    let confirmaSenha = prompt("Informe sua senha para prosseguir: ");

    if(confirmaSenha == usuarioLogado.senha)
        localStorage.clear();
    else alert("Senha incorreta!");
    window.location.href = "index.html";
})

var btnLogOut = document.getElementById("btnLogOut");
btnLogOut.addEventListener("click", () => {
    localStorage.setItem("logado", JSON.stringify(false));
    window.location.href = "index.html";
})  