# Documentação de Testes para o Branch `main`

Este documento fornece um resumo dos testes atualmente implementados no branch `main`. Ele serve como referência para entender a cobertura de testes existente.

---

### 1. SkinsViewModelTest

- **Local do Arquivo:** `app/src/test/java/com/example/counterdatabase/ExampleUnitTest.kt`
- **Tipo:** Teste Unitário Local (Roda na JVM).
- **Objetivo:** Verificar a lógica de negócio de busca e filtro da `SkinsViewModel` de forma isolada do framework Android.

**O que ele Testa:**
- `searchSkins should filter the list correctly`: Checa se buscar por um termo como "Dragon" retorna corretamente apenas as skins que contêm esse termo no nome.
- `searchSkins should be case-insensitive`: Garante que a lógica de busca ignore maiúsculas e minúsculas (ex: "dragon" encontra "Dragon Lore").
- `searchSkins with empty query should return the full list`: Confirma que uma busca com string vazia (`""`) retorna a lista original, sem filtros.
- `searchSkins with null query should return the full list`: Confirma que uma busca com valor `null` também retorna a lista sem filtros.
- `searchSkins with no matching results should return an empty list`: Verifica se buscar por um termo que não existe retorna uma lista vazia.

---

### 2. StickersViewModelTest

- **Local do Arquivo:** `app/src/test/java/com/example/counterdatabase/ui/stickers/StickersViewModelTest.kt`
- **Tipo:** Teste Unitário Local (Roda na JVM).
- **Objetivo:** Verificar a lógica de busca da `StickersViewModel`, que lida com filtros por múltiplos campos.

**O que ele Testa:**
- Filtra corretamente a lista de stickers por `name`, `tournament_team` e `tournament_event`.
- Garante que a busca seja insensível a maiúsculas e minúsculas em todos os campos.
- Confirma que buscas com `null` ou strings vazias retornam a lista completa e sem filtros.
- Verifica que uma busca sem resultados correspondentes retorna uma lista vazia.

**Status:** Um teste está falhando atualmente. Este é um resultado positivo, pois significa que a suíte de testes está identificando com sucesso um bug na lógica de filtro da ViewModel que precisa ser corrigido.

---

### 3. HighlightDetailsActivityTest

- **Local do Arquivo:** `app/src/androidTest/java/com/example/counterdatabase/ui/highlights/HighlightDetailsActivityTest.kt`
- **Tipo:** Teste Instrumentado (Roda em um dispositivo Android ou emulador).
- **Objetivo:** Verificar o comportamento da UI, a exibição de dados e o gerenciamento do ciclo de vida da `HighlightDetailsActivity`.

**Instruções para Rodar:**
1.  No Android Studio, clique com o botão direito no arquivo `HighlightDetailsActivityTest.kt`.
2.  Selecione **Run 'HighlightDetailsActivityTest'**.
3.  Escolha um emulador Android disponível ou um dispositivo físico conectado.

**O que ele Testa:**
- `displaysAllDetails_whenHighlightIsComplete`: Checa se todos os elementos da UI (nome, descrição, torneio, player de vídeo, etc.) são exibidos corretamente quando um objeto `Highlight` completo é passado para a activity.
- `hidesEmptyViews_whenDataIsMissing`: Verifica se os elementos da UI correspondentes a campos `null` ou vazios no objeto `Highlight` são corretamente ocultados (`View.GONE`) para evitar espaços em branco.
- `playerIsReleased_onStop`: Um teste de ciclo de vida crítico que garante que a instância do `ExoPlayer` seja corretamente liberada (`null`) quando a activity é interrompida. Isso previne a reprodução de vídeo em segundo plano e evita vazamentos de memória.

---

### 4. CategoryTest

- **Local do Arquivo:** `app/src/test/java/com/example/counterdatabase/test/CategoryTest.kt`
- **Tipo:** Teste Unitário Local (Roda na JVM).
- **Objetivo:** Validar a integridade estrutural e o comportamento fundamental da data class `Category`.

**O que ele Testa:**

O teste garante que a classe `Category` funcione como esperado em dois cenários principais:

- **`category_creation_withAllProperties_success()`**: Este teste verifica se, ao criar um objeto `Category`, os valores passados no construtor são atribuídos corretamente às suas propriedades (`id` e `name`). Uma falha aqui indicaria um problema na inicialização da classe.

- **`category_equality_comparison_success()`**: Este teste valida o comportamento do método `equals()` (que é gerado automaticamente por ser uma `data class`). Ele confirma que dois objetos com os mesmos valores de propriedade são considerados iguais, e objetos com valores diferentes são considerados diferentes. Uma falha poderia significar que `Category` não foi declarada como uma `data class`.
