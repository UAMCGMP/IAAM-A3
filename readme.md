- [Projeto](#projeto)
- [Algoritimo Random Forest](#algoritimo-random-forest)
  - [Fluxo do Aplicativo](#fluxo-do-aplicativo)
- [Implementação](#implementação)
- [Componentes Principais](#componentes-principais)
  - [Back-end](#back-end)
- [Requerimentos](#requerimentos)
- [Executando](#executando)

# Projeto
Este projeto foi desenvolvido como parte dos estudos da disciplina Inteligência Artificial e Aprendizado de Maquina do curso de Engenharia da Computação na Universidade Anhembi Morumbi, lecionada pelo professor [Paulo Nietto](https://github.com/paulonietto). O projeto foi realizado por um grupo de quatro estudantes entusiastas de programação, incluindo:
- [Camila](https://github.com/ccaetano478)
- [Gabriel](https://github.com/gabrielsteffen)
- [Mateus](https://github.com/MtTimm)
- [Paulo](https://github.com/paulodaniellac)

O objetivo deste projeto é aplicar e aprimorar os conhecimentos adquiridos durante a disciplina de Inteligência Artificial e Aprendizado de Maquina, bem como a prática colaborativa em um ambiente de desenvolvimento de software.

# Algoritimo Random Forest
O algoritmo Random Forest é um método de aprendizado de máquina que usa várias árvores de decisão para previsão ou classificação. Ele funciona a partir de um conjunto de árvores de decisão independentes para operar. Os dados de treinamento são amostrados aleatoriamente para treinar cada árvore de substituição. Durante a previsão, as árvores votam de forma individual para tomar decisões. O que definirá a previsão final é a classe com mais votos. Ao combinar várias árvores teremos uma maior precisão da previsão e uma diminuição do overfitting. Este algoritmo também pode determinar a importância das variáveis de entrada. Isso indicará os recursos mais pertinentes à previsão.

## Fluxo do Aplicativo


# Implementação
- [x] Carregamento e leitura de arquivos *.csv*, *.xlsx*;
- [x] Normalização dos dados;
- [X] Entrada de treinamento
- [X] Coleta das amostras

# Componentes Principais
O projeto é composto por vários arquivos e classes que desempenham funções específicas. A seguir estão os principais componentes do programa:

## Back-end
- `App.java`: Ponto de entrada para executar o Random Forest. Carrega dados, treina o modelo, faz previsões e exibe resultados.
- `Dado.java`:
- `DecisionTree.java`: Representa uma árvore de decisão no Random Forest. Constrói a árvore, faz previsões e calcula importância das características.
- `Leitor.java`: Realiza o carregamento e leitura do arquivo *.csv* ou *txt
- `Node.java`: Representa um nó em uma árvore de decisão. Armazena dados de divisão e permite fazer previsões.
- `Normalizador.java`: Realiza a normalização dos dados.
- `RandomForest.java`: Treina e faz previsões usando o algoritmo Random Forest. Calcula importância das características e combina várias árvores de decisão. 
- `SplitCriteria.java`: Armazena critérios de divisão em um nó da árvore. Contém nome e valor da característica para determinar o caminho.


# Requerimentos
–
# Executando
1. Passo 1.
2. Passo 2.
3. Passo 3.
