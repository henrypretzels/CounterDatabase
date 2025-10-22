# CounterDatabase

CounterDatabase é um aplicativo Android desenvolvido em Kotlin que serve como um banco de dados abrangente para itens do Counter-Strike: Global Offensive (CS:GO) e Counter-Strike 2 (CS2). O aplicativo permite aos usuários visualizar, pesquisar e explorar detalhes de skins de armas e stickers do jogo.

## Funcionalidades

### Skins de Armas
- Visualização completa de skins de armas do CS:GO/CS2
- Sistema de busca em tempo real por nome da skin
- Detalhes completos incluindo:
  - Informações da arma
  - Categoria e padrão
  - Raridade com cores específicas
  - Valores de float (min/max)
  - Suporte a StatTrak
  - Imagens de alta qualidade

### Stickers
- Catálogo completo de stickers do jogo
- Busca avançada por nome, time ou evento do torneio
- Informações detalhadas incluindo:
  - Tipo e efeito do sticker
  - Evento e time do torneio
  - Raridade com cores específicas
  - Crates associados
  - Nome do mercado

## Arquitetura

O projeto segue os princípios de arquitetura limpa e padrões modernos de desenvolvimento Android:

### Padrão MVVM
- **Model**: Classes de dados com Parcelable para navegação
- **View**: Activities e layouts XML com ViewBinding
- **ViewModel**: Gerenciamento de estado e lógica de negócio

### Camadas da Aplicação
- **Data Layer**: Modelos de dados, API service e repositories
- **UI Layer**: Activities, Adapters, ViewModels e layouts
- **Network Layer**: Retrofit para comunicação com APIs externas

### Componentes Principais
- **Retrofit**: Comunicação com API externa
- **Glide**: Carregamento e cache de imagens
- **ViewBinding**: Binding seguro de views
- **LiveData**: Reatividade e observação de dados
- **DiffUtil**: Otimização de performance no RecyclerView

## Estrutura do Projeto

```
app/src/main/java/com/example/counterdatabase/
├── data/
│   ├── ApiService.kt              # Interface Retrofit
│   ├── RetrofitInstance.kt        # Configuração Retrofit
│   ├── SkinsRepository.kt         # Repository para skins
│   ├── StickersRepository.kt      # Repository para stickers
│   ├── Category.kt               # Modelo de categoria
│   ├── Pattern.kt                 # Modelo de padrão
│   ├── Rarity.kt                 # Modelo de raridade
│   ├── Skin.kt                   # Modelo de skin
│   ├── Weapon.kt                 # Modelo de arma
│   ├── Sticker.kt                # Modelo de sticker
│   ├── Crate.kt                  # Modelo de crate
│   └── Tournament.kt             # Modelo de torneio
├── ui/
│   ├── skins/
│   │   ├── SkinsActivity.kt      # Lista de skins
│   │   ├── SkinDetailsActivity.kt # Detalhes da skin
│   │   ├── SkinsAdapter.kt       # Adapter do RecyclerView
│   │   └── SkinsViewModel.kt     # ViewModel para skins
│   └── stickers/
│       ├── StickersActivity.kt   # Lista de stickers
│       ├── StickerDetailsActivity.kt # Detalhes do sticker
│       ├── StickersAdapter.kt    # Adapter do RecyclerView
│       └── StickersViewModel.kt  # ViewModel para stickers
└── MainActivity.kt               # Tela principal
```

## Tecnologias Utilizadas

- **Kotlin**: Linguagem principal de desenvolvimento
- **Android SDK**: Target SDK 36, Min SDK 30
- **Retrofit 2.9.0**: Cliente HTTP para APIs REST
- **Gson**: Serialização/deserialização JSON
- **Glide 4.16.0**: Carregamento e cache de imagens
- **Material Design 3**: Design system do Android
- **ViewBinding**: Binding seguro de views
- **ViewModel & LiveData**: Arquitetura reativa
- **RecyclerView**: Listas performáticas
- **SearchView**: Busca em tempo real

## Fonte de Dados

O aplicativo consome dados de uma API pública hospedada no GitHub:
- **Base URL**: `https://raw.githubusercontent.com/ByMykel/CSGO-API/main/public/api/pt-BR/`
- **Endpoints**:
  - `/skins.json` - Catálogo de skins
  - `/stickers.json` - Catálogo de stickers

## Requisitos do Sistema

- **Android**: 11.0 (API 30) ou superior
- **Arquitetura**: ARM64, ARMv7, x86, x86_64
- **Memória**: Mínimo 2GB RAM recomendado
- **Armazenamento**: 50MB para instalação
- **Conexão**: Internet para carregamento de dados e imagens

## Instalação

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/CounterDatabase.git
```

2. Abra o projeto no Android Studio

3. Sincronize as dependências do Gradle

4. Execute o projeto em um dispositivo ou emulador Android

## Desenvolvimento

### Configuração do Ambiente
- Android Studio Arctic Fox ou superior
- JDK 11 ou superior
- Android SDK com API 30+
- Gradle 8.13.0

### Build do Projeto
```bash
./gradlew assembleDebug
```

### Execução de Testes
```bash
./gradlew test
```

## Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Roadmap

### Funcionalidades Futuras
- [ ] Sistema de crates
- [ ] Catálogo de agents
- [ ] Filtros avançados por raridade, categoria, etc.
- [ ] Favoritos e listas personalizadas
- [ ] Modo offline com cache local
- [ ] Notificações de preços de mercado
- [ ] Integração com APIs de preços

### Melhorias Técnicas
- [ ] Implementação de testes unitários
- [ ] Injeção de dependência com Hilt
- [ ] Navigation Component
- [ ] Room Database para cache local
- [ ] Tratamento robusto de erros
- [ ] Loading states e feedback visual

## Contato

Para dúvidas, sugestões ou reportar bugs, abra uma issue no repositório ou entre em contato através do email: [seu-email@exemplo.com]

---

**CounterDatabase** - Seu banco de dados completo para itens do Counter-Strike
