# CounterDatabase

Aplicativo Android desenvolvido em Kotlin que serve como hub de informações sobre itens do Counter-Strike: Global Offensive (CS:GO) e Counter-Strike 2 (CS2).

![CounterDatabase Demo](docs/assets/1119.gif)

## O que o app faz

O CounterDatabase permite visualizar, pesquisar e explorar detalhes de:

- **Skins**: Catálogo completo de skins de armas com informações de raridade, float, StatTrak e imagens
- **Crates**: Visualização de caixas e seus itens contidos
- **Stickers**: Coleção de stickers com informações de torneios, times e efeitos
- **Agents**: Lista de agentes disponíveis no jogo
- **Highlights**: Melhores clipes e momentos dos torneios

Todas as seções possuem busca em tempo real e telas de detalhes com informações completas.

## Tecnologias Utilizadas

- **Kotlin** - Linguagem de programação
- **Android SDK** - Target SDK 36, Min SDK 30
- **Retrofit** - Cliente HTTP para APIs REST
- **Gson** - Serialização JSON
- **Glide** - Carregamento e cache de imagens
- **Material Design 3** - Design system
- **ViewBinding** - Binding seguro de views
- **ViewModel & LiveData** - Arquitetura MVVM
- **RecyclerView** - Listas performáticas
- **OkHttp** - Cliente HTTP com cache e timeouts

## Instalação

1. Clone o repositório:
```bash
git clone https://github.com/henrypretzels/CounterDatabase.git
```

2. Abra o projeto no Android Studio

3. Sincronize as dependências do Gradle

4. Execute o projeto em um dispositivo ou emulador Android

## Fonte de Dados

O aplicativo consome dados de uma API pública:
- **Base URL**: `https://raw.githubusercontent.com/ByMykel/CSGO-API/main/public/api/en/`

## Licença

Este projeto está sob a licença MIT.

---

**CounterDatabase** - Seu hub de curiosidades do Counter-Strike
