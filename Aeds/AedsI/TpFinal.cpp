#include <iostream>
#include <vector>
#include <map>
#include <iomanip>
#include <limits>

using namespace std;

class Data {

  private:
      int dia;
      int mes;
      int ano;
  
  public:
      Data() : dia(0), mes(0), ano(0) {}
  
      Data(int dia, int mes, int ano) : dia(dia), mes(mes), ano(ano) {}
  
      void leiaData() {
          cout << "Data [dd/mm/aaaa]: ";
          bool dataValida = false;
          do {
              try {
                  int result = scanf("%d/%d/%d", &dia, &mes, &ano);
                  if (result != 3 || dia < 1 || dia > 31 || mes < 1 || mes > 12 || ano < 0) {
                      throw invalid_argument("\033[31mData inválida. Insira uma data válida no formato [dd/mm/aaaa].\033[0m");
                  
                  }
                  dataValida = true;
              } catch (const exception& e) {
                  cout << e.what();
                  cout << "Tente novamente." << endl;
                  cout << "Data [dd/mm/aaaa]: ";
                  cin.clear();
                  cin.ignore(numeric_limits<streamsize>::max(), '\n');
              }
          } while (!dataValida);
          cin.ignore(numeric_limits<streamsize>::max(), '\n');
      }


  
      void imprimir() const {
          cout << setw(2) << setfill('0') << dia << "/";
          cout << setw(2) << setfill('0') << mes << "/";
          cout << setw(4) << setfill('0') << ano;
      }
  
      int getDia() const {
          return dia;
      }
  
      int getMes() const {
          return mes;
      }
  
      int getAno() const {
          return ano;
      }
}; // Fim clas Data;


class Pessoa {

  protected:
      string nome;
      Data data;
  
  public:
      Pessoa(const string& nome, const Data& data) : nome(nome), data(data) {}
  
      virtual ~Pessoa() {}
  
      const string& getNome() const {
          return nome;
      }
  
      const Data& getData() const {
          return data;
      }
  
      virtual void imprimir() const = 0;

}; // Fim class Pessoa;



class Aluno : public Pessoa {

  private:
      string matricula;
  
  public:
      Aluno(const string& nome, const Data& data, const string& matricula)
          : Pessoa(nome, data), matricula(matricula) {}
  
      const string& getMatricula() const {
          return matricula;
      }
  
      void imprimir() const override {
          cout << "Aluno - Nome: " << nome << ", Matrícula: " << matricula << ", Data: ";
          data.imprimir();
          cout << endl;
      }

}; // Fim classe Aluno;



class Professor : public Pessoa {

  private:
      string titulacao;
  
  public:
      Professor(const string& nome, const Data& data, const string& titulacao)
          : Pessoa(nome, data), titulacao(titulacao) {}
  
      const string& getTitulacao() const {
          return titulacao;
      }
  
      void imprimir() const override {
          cout << "Professor - Nome: " << nome << ", Titulação: " << titulacao << ", Data: ";
          data.imprimir();
          cout << endl;
      }

}; // Fim classe Professor;


class GerenciadorPessoas {

  private:
      map<int, Pessoa*> pessoas;
      int proximoId;
  
  public:
      GerenciadorPessoas() : proximoId(1) {}

      int getPessoaId(const Pessoa* pessoa) const {
        for (const auto& par : pessoas) {
            if (par.second == pessoa) {
                return par.first;
            }
        }
        return -1;
    }


      void cadastrarAluno(const string& nome, const Data& data, const string& matricula) {
          pessoas[proximoId] = new Aluno(nome, data, matricula);
          cout << "Aluno cadastrado com sucesso! ID: " << proximoId << endl;
          proximoId++;
      }


      void cadastrarProfessor(const string& nome, const Data& data, const string& titulacao) {
          pessoas[proximoId] = new Professor(nome, data, titulacao);
          cout << "Professor cadastrado com sucesso! ID: " << proximoId << endl;
          proximoId++;
      }


      void listarAlunos() const {
          cout << "\nAlunos:" << endl;
          for (const auto& pessoa : pessoas) {
              Aluno* aluno = dynamic_cast<Aluno*>(pessoa.second);
              if (aluno) {
                  int idPessoa = getPessoaId(aluno);
                  cout << idPessoa << ": ";
                  aluno->imprimir();
              }
          }
      }


      void listarProfessores() const {
          cout << "\nProfessores:" << endl;
          for (const auto& pessoa : pessoas) {
              Professor* professor = dynamic_cast<Professor*>(pessoa.second);
              if (professor) {
                  int idPessoa = getPessoaId(professor);
                  cout << idPessoa << ": ";
                  professor->imprimir();
              }
          }
      }


      void listarTodos() const {
          cout << "\nAlunos e Professores:" << endl;
          for (const auto& pessoa : pessoas) {
              int idPessoa = getPessoaId(pessoa.second);
              cout << idPessoa << ": ";
              Pessoa* ptrPessoa = pessoa.second;
              ptrPessoa->imprimir();
          }
      }

  
      void deletarPessoa(int idPessoa) {
          auto it = pessoas.find(idPessoa);
          if (it != pessoas.end()) {
              Pessoa* pessoa = it->second;
              delete pessoa;
              pessoas.erase(it);
              cout << "Pessoa deletada com sucesso!" << endl;
          } else {
              cout << "\033[31mID da pessoa inválido. Nenhuma pessoa foi deletada.\033[0m\n" << endl;
          }
      }


    void listarAniversariantesDoMes(int mes) const {
          try {
            
              if (mes < 1 || mes > 12) {
                throw invalid_argument("\033[31mMês inválido. Insira um número de mês válido (1 a 12).\033[0m");
              }
              cout << "\nAniversariantes do mês " << mes << ":" << endl;
              for (const auto& pessoa : pessoas) {
                  if (pessoa.second->getData().getMes() == mes) {
                    pessoa.second->imprimir();
                  }
              }  
          } catch (const exception& e) {
            cout << e.what() << endl;
          }
      }


    void exibirMenu() {
      
        limparConsole();
        cout << "         ______________________________________________________" << endl;
        cout << "        /                          (:)                         \\" << endl;
        cout << "       |    ________________________________________________    |" << endl;
        cout << "       |   |                                                |   |" << endl;
        cout << "       |   |                 MENU DE OPÇÕES                 |   |" << endl;
        cout << "       |   |________________________________________________|   |" << endl;
        cout << "       |   |                                                |   |" << endl;
        cout << "       |   | 1. Cadastrar Aluno.                            |   |" << endl;
        cout << "       |   | 2. Cadastrar Professor.                        |   |" << endl;
        cout << "       |   | 3. Listar Aluno.                               |   |" << endl;
        cout << "       |   | 4. Listar Professores.                         |   |" << endl;
        cout << "       |   | 5. Listar Pessoas.                             |   |" << endl;
        cout << "       |   | 6. Deletar Pessoa.                             |   |" << endl;
        cout << "       |   | 7. Aniversariantes do mês.                     |   |" << endl;
        cout << "       |   | 8. Limpar.                                     |   |" << endl;
        cout << "       |   | 0. Sair.                                       |   |" << endl;
        cout << "       |   |________________________________________________|   |" << endl;
        cout << "       |   |                                                |   |" << endl;
        cout << "       |   |             < Sistema Tulio Braga >            |   |" << endl;
        cout << "       |   |________________________________________________|   |" << endl;
        cout << "       |                                                        |" << endl;
        cout << "       |                                         <  >  - +  "; cout << "\x1B[34mº\x1B[0m";cout << "   |" << endl;
        cout << "        \\______________________________________________________/" << endl;
        cout << "                \\______________________________________/    " << endl;
        cout << "\n\033[32m\n\033[1m|--> Escolha uma opção: \033[0m";
        
    }

  
    void limparConsole() {
        system("clear");
    }
  
    ~GerenciadorPessoas() {
      for (const auto& pessoa : pessoas) {
        delete pessoa.second;
      }
    }

}; // Fim classe Gerenciador;


int main() {
  
    try {
        GerenciadorPessoas gerenciador;
        int opcao = 8;

        do {

            if (opcao == 8) {
                gerenciador.exibirMenu();
            } else {
                cout << "\n\033[32m\n\033[1m|--> Escolha uma opção: \033[0m";
            }
          
            cin >> opcao;

            switch (opcao) {
                case 1: {
                    string nomeAluno, matricula;
                    cout << "\nNome do Aluno: ";
                    cin.ignore();
                    getline(cin, nomeAluno);
                    cout << "Matrícula do Aluno: ";
                    getline(cin, matricula);

                    Data data;
                    data.leiaData();

                    gerenciador.cadastrarAluno(nomeAluno, data, matricula);
                    break;
                }
                case 2: {
                    string nomeProfessor, titulacao;
                    cout << "\nNome do Professor: ";
                    cin.ignore();
                    getline(cin, nomeProfessor);
                    cout << "Titulação do Professor: ";
                    getline(cin, titulacao);

                    Data data;
                    data.leiaData();

                    gerenciador.cadastrarProfessor(nomeProfessor, data, titulacao);
                    break;
                }
                case 3: {
                    gerenciador.listarAlunos();
                    break;
                }
                case 4: {
                    gerenciador.listarProfessores();
                    break;
                }
                case 5: {
                    gerenciador.listarTodos();
                    break;
                }
                case 6: {
                    int idPessoa;
                    gerenciador.listarTodos();
                    cout << "\nID da Pessoa a ser deletada: ";
                    cin >> idPessoa;
                    gerenciador.deletarPessoa(idPessoa);
                    break;
                }
                case 7: {
                    int mes;
                    cout << "\nDigite o número do mês: ";
                    cin >> mes;
                    gerenciador.listarAniversariantesDoMes(mes);
                    break;
                }
                case 8: {
                    break;
                }
                case 0: {
                    cout << "Encerrando o programa..." << endl;
                    break;
                }
                default: {
                    throw runtime_error("Opção inválida. Tente novamente.");
                }
            }
          
        } while (opcao != 0);
      
    } catch (const exception& e) {
        cout << "Ocorreu uma exceção ->  " << e.what() << endl;
    }

    return 0;
  
} // Fim main;

