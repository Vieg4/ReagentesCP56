# 🧪 Sistema de Gerenciamento de Inventário de Reagentes (GIR)

API REST desenvolvida para gerenciar o inventário de reagentes químicos em laboratórios de exames, garantindo controle rigoroso de validade, lotes e rastreabilidade de movimentações.

---

## 👥 Equipe de Desenvolvimento

| Nome | RM |
|------|-----|
| Felipe Marques de Oliveira | RM556319 |
| Gabriel Barros Cisoto | RM556309 |
| Gustavo Viega Martins Lopes | RM555885 |
| Kaio Drago Lima Souza  | RM556095 |

---

## 📋 Sobre o Projeto

Este projeto faz parte dos **Checkpoints 5 e 6** da disciplina **Domain Driven Design - Java** (2ESPH) da FIAP.

### 🎯 Objetivo

Permitir o controle completo de reagentes químicos, incluindo:
- ✅ Cadastro de reagentes com validação de validade
- ✅ Controle de fabricantes e fornecedores
- ✅ Gerenciamento de localizações físicas (refrigeradores, freezers, etc.)
- ✅ Rastreabilidade de todas as movimentações de estoque
- ✅ Prevenção de uso de reagentes vencidos ou reprovados

---

## 🏗️ Arquitetura do Projeto

### Estrutura de Pacotes

```
br.com.fiap.esph.ddd.reagentes.reagentes_api/
├── controller/          # Camada de Apresentação (REST Controllers)
├── service/             # Camada de Negócio (Regras de negócio)
├── repository/          # Camada de Acesso a Dados (JPA Repositories)
├── domain/
│   ├── model/          # Entidades JPA
│   ├── dto/            # Data Transfer Objects (Records)
│   └── mapper/         # Conversores Entity ↔ DTO
└── exception/          # Tratamento de exceções
```

### 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3.5.7**
- **Spring Web** (REST API)
- **Spring Data JPA** (Persistência)
- **Spring Validation** (Bean Validation)
- **H2 Database** (Banco em memória para desenvolvimento)
- **Maven** (Gerenciamento de dependências)
- **Lombok** (Redução de boilerplate)

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Git

### Passos para execução

1. **Clone o repositório**
```bash
git clone https://github.com/Vieg4/ReagentesCP56.git
cd ReagentesCP56
```

2. **Compile o projeto**
```bash
mvn clean install
```

3. **Execute a aplicação**
```bash
mvn spring-boot:run
```

4. **Acesse a aplicação**
- API: http://localhost:8080
- Console H2: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (deixar em branco)

---

## 📡 Endpoints da API

### 🏭 Fabricantes

#### Listar todos os fabricantes
```http
GET http://localhost:8080/api/fabricantes
```

#### Buscar fabricante por ID
```http
GET http://localhost:8080/api/fabricantes/{id}
```

#### Criar novo fabricante
```http
POST http://localhost:8080/api/fabricantes
Content-Type: application/json

{
  "nomeOficial": "Roche Diagnostics International AG",
  "nomeFantasia": "Roche",
  "cnpj": "12345678000199",
  "paisOrigem": "Suíça"
}
```

#### Atualizar fabricante
```http
PUT http://localhost:8080/api/fabricantes/{id}
Content-Type: application/json

{
  "nomeOficial": "Roche Diagnostics International AG",
  "nomeFantasia": "Roche",
  "cnpj": "12345678000199",
  "paisOrigem": "Suíça"
}
```

#### Deletar fabricante
```http
DELETE http://localhost:8080/api/fabricantes/{id}
```

**Respostas:**
- `201 Created` - Fabricante criado com sucesso
- `200 OK` - Operação bem-sucedida
- `204 No Content` - Exclusão bem-sucedida
- `404 Not Found` - Fabricante não encontrado
- `400 Bad Request` - Dados inválidos

---

### 📍 Localizações de Estoque

#### Listar todas as localizações
```http
GET http://localhost:8080/api/localizacoes
```

#### Criar nova localização
```http
POST http://localhost:8080/api/localizacoes
Content-Type: application/json

{
  "codigoLocal": "REF-BQ-01",
  "descricao": "Refrigerador 1 da Bioquímica",
  "tipo": "REFRIGERADOR",
  "faixaTemperaturaNominal": "2°C a 8°C",
  "setor": "Bioquímica"
}
```

**Tipos de Localização disponíveis:**
- `REFRIGERADOR`
- `FREEZER_MINUS20`
- `FREEZER_MINUS80`
- `PRATELEIRA_AMBIENTE`
- `OUTRO`

---

### 🧪 Reagentes

#### Listar todos os reagentes
```http
GET http://localhost:8080/api/reagentes
```

#### Criar novo reagente
```http
POST http://localhost:8080/api/reagentes
Content-Type: application/json

{
  "nome": "Soro Controle Bioquímica Nível 1",
  "codigoSku": "SKU-90887-A1",
  "lote": "BX-2025-09A",
  "dataValidade": "2026-10-31",
  "dataRecebimento": "2025-11-02",
  "quantidadeEmEstoque": 50,
  "status": "QUARENTENA",
  "fabricanteId": "uuid-do-fabricante",
  "localizacaoEstoqueId": "uuid-da-localizacao"
}
```

**Status de Reagente disponíveis:**
- `QUARENTENA` - Aguardando liberação do QC
- `LIBERADO` - Aprovado e pronto para uso
- `EM_USO` - Lote em uso no analisador
- `VENCIDO` - Data de validade expirada
- `REPROVADO_CONTROLE_QUALIDADE` - Falhou no QC
- `DESCARTADO` - Removido do estoque

---

### 📦 Movimentações de Estoque

#### Listar todas as movimentações
```http
GET http://localhost:8080/api/movimentacoes
```

#### Criar nova movimentação
```http
POST http://localhost:8080/api/movimentacoes
Content-Type: application/json

{
  "tipo": "ENTRADA_NOTA",
  "quantidadeMovimentada": 100,
  "dataHoraMovimentacao": "2025-11-02T15:00:10",
  "observacao": "Recebimento NF 12345"
}
```

**Tipos de Movimentação disponíveis:**
- `ENTRADA_NOTA`
- `SAIDA_USO_ANALISADOR`
- `SAIDA_DESCARTE_VENCIMENTO`
- `SAIDA_DESCARTE_CONTROLE_QUALIDADE`
- `AJUSTE_INVENTARIO_POSITIVO`
- `AJUSTE_INVENTARIO_NEGATIVO`

---

## 🗂️ Modelo de Dados

### Entidades Principais

1. **Reagente** (Entidade Central)
   - Relacionamento ManyToOne com Fabricante
   - Relacionamento ManyToOne com LocalizacaoEstoque
   - Relacionamento OneToMany com MovimentacaoEstoque

2. **Fabricante**
   - Relacionamento OneToMany com Reagente

3. **LocalizacaoEstoque**
   - Relacionamento OneToMany com Reagente

4. **MovimentacaoEstoque**
   - Relacionamento ManyToOne com Reagente

---

## ✅ Requisitos Atendidos

- ✅ **(a)** Estrutura de pacotes organizada
- ✅ **(b)** REST Controllers com CRUD completo (GET, POST, PUT, DELETE)
- ✅ **(c)** Operações para listagem e consulta por ID
- ✅ **(d)** Códigos de status HTTP corretos (200, 201, 204, 404, 400)
- ✅ **(e)** DTOs usando Records
- ✅ **(f)** Classes de Service com regras de negócio
- ✅ **(g)** Entidades com relacionamentos bidirecionais
- ✅ **(h)** Mappers para conversão Entity ↔ DTO
- ✅ **(i)** Commits de todos os membros da equipe
- ✅ **(j)** README.md documentado

---

## 📝 Observações

- O projeto utiliza banco de dados H2 em memória, os dados são perdidos ao reiniciar a aplicação
- Todas as validações são feitas com Bean Validation
- Exception Handler global trata erros de forma padronizada
- Relacionamentos bidirecionais garantem integridade referencial
