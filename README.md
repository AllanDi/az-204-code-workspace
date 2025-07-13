# AZ-204 Spring Learning Project

🚀 **Projeto de estudo para certificação AZ-204 com Spring Boot + Azure**

Este projeto foi criado para praticar e demonstrar os conceitos do AZ-204 (Developing Solutions for Microsoft Azure) usando Spring Boot, com suporte tanto para serviços Azure reais (gratuitos) quanto para simulações locais.

## 🎯 Objetivo

Aprender os principais serviços Azure necessários para o AZ-204:
- ✅ **Azure Storage** (Blob Storage)
- ✅ **Azure Key Vault** (Gerenciamento de Secrets)
- 🔄 **Azure Service Bus** (Mensageria)
- 🔄 **Azure Application Insights** (Monitoramento)
- 🔄 **Azure Functions** (Serverless)
- 🔄 **Azure Cosmos DB** (NoSQL Database)

## 🏗️ Arquitetura

```
src/main/java/com/az204study/
├── Az204StudyApplication.java     # Aplicação principal
├── controller/                    # REST Controllers
│   ├── StorageController.java     # Endpoints para Storage
│   ├── KeyVaultController.java    # Endpoints para Key Vault
│   └── StatusController.java      # Status da aplicação
├── service/                       # Interfaces de serviço
│   ├── StorageService.java
│   ├── KeyVaultService.java
│   ├── real/                      # Implementações Azure reais
│   │   └── AzureStorageService.java
│   └── mock/                      # Implementações mock
│       ├── MockStorageService.java
│       └── MockKeyVaultService.java
├── config/                        # Configurações
│   ├── AzureConfig.java          # Configurações Azure
│   └── MockConfig.java           # Configurações mock
├── model/                         # Modelos de dados
│   └── FileMetadata.java
└── simulator/                     # Simuladores para demonstração
    └── AzureSimulator.java
```

## 🎮 Perfis de Execução

### 🧪 `local` (Padrão)
- **Tudo mockado** - Ideal para desenvolvimento
- Não requer credenciais Azure
- Simula todos os serviços em memória

### ☁️ `azure`
- **Serviços Azure reais** - Somente recursos gratuitos
- Requer credenciais Azure válidas
- Ideal para testes reais

### 🔀 `hybrid`
- **Mistura** - Azure gratuito + Mocks para recursos pagos
- Melhor custo-benefício para aprendizado

## 🚀 Como Executar

### 1. Execução Local (Recomendado para início)

```bash
# Clone o projeto
git clone <seu-repositorio>
cd az204-spring-learning

# Execute com profile local (padrão)
./mvnw spring-boot:run

# Ou explicitamente
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

### 2. Execução com Azure Real

```bash
# Configure as variáveis de ambiente
export AZURE_KEYVAULT_ENDPOINT=https://seu-keyvault.vault.azure.net/
export AZURE_STORAGE_CONNECTION_STRING=DefaultEndpointsProtocol=https;AccountName=...
export AZURE_SERVICEBUS_CONNECTION_STRING=Endpoint=sb://...
export APPLICATIONINSIGHTS_CONNECTION_STRING=InstrumentationKey=...

# Execute com profile Azure
./mvnw spring-boot:run -Dspring-boot.run.profiles=azure
```

### 3. Execução Híbrida

```bash
# Configure apenas os serviços gratuitos
export AZURE_KEYVAULT_ENDPOINT=https://seu-keyvault.vault.azure.net/
export AZURE_STORAGE_CONNECTION_STRING=DefaultEndpointsProtocol=https;AccountName=...

# Execute com profile híbrido
./mvnw spring-boot:run -Dspring-boot.run.profiles=hybrid
```

## 📡 Endpoints Principais

### Status da Aplicação
- `GET /api/status` - Status geral da aplicação
- `GET /api/status/health` - Health check

### Azure Storage
- `POST /api/storage/upload` - Upload de arquivo
- `GET /api/storage/list/{containerName}` - Lista arquivos
- `DELETE /api/storage/delete/{containerName}/{fileName}` - Deleta arquivo
- `GET /api/storage/exists/{containerName}/{fileName}` - Verifica se arquivo existe
- `POST /api/storage/container/{containerName}` - Cria container

### Azure Key Vault
- `GET /api/keyvault/secret/{secretName}` - Recupera secret
- `POST /api/keyvault/secret` - Armazena secret
- `DELETE /api/keyvault/secret/{secretName}` - Deleta secret
- `GET /api/keyvault/secret/{secretName}/exists` - Verifica se secret existe

### Monitoramento
- `GET /actuator/health` - Health check do Spring Actuator
- `GET /actuator/info` - Informações da aplicação

## 🧪 Testando a Aplicação

### 1. Verificar Status
```bash
curl http://localhost:8080/api/status
```

### 2. Testar Storage
```bash
# Upload de arquivo
curl -X POST -F "containerName=test" -F "file=@exemplo.txt" \
  http://localhost:8080/api/storage/upload

# Listar arquivos
curl http://localhost:8080/api/storage/list/test
```

### 3. Testar Key Vault
```bash
# Criar secret
curl -X POST -H "Content-Type: application/json" \
  -d '{"secretName":"teste","secretValue":"valor123"}' \
  http://localhost:8080/api/keyvault/secret

# Recuperar secret
curl http://localhost:8080/api/keyvault/secret/teste
```

## 💰 Custos Azure (Recursos Gratuitos)

| Serviço | Limite Gratuito | Custo Após Limite |
|---------|----------------|-------------------|
| **Key Vault** | 10.000 operações/mês | $0.03/10k operações |
| **Storage (Blob)** | 5GB + operações limitadas | $0.0184/GB/mês |
| **App Insights** | 5GB dados/mês | $2.30/GB |
| **Service Bus** | 1M mensagens/mês | $0.05/1M mensagens |

## 🔧 Configuração Azure

### Pré-requisitos
1. Conta Azure (gratuita)
2. Azure CLI instalado
3. Subscription ativa

### Setup Rápido
```bash
# Login no Azure
az login

# Criar grupo de recursos
az group create --name rg-az204-study --location eastus

# Criar Key Vault
az keyvault create --name kv-az204-study --resource-group rg-az204-study --location eastus

# Criar Storage Account
az storage account create --name staaz204study --resource-group rg-az204-study --location eastus --sku Standard_LRS

# Criar Service Bus Namespace
az servicebus namespace create --name sb-az204-study --resource-group rg-az204-study --location eastus
```

## 📚 Recursos de Estudo

### Links Oficiais
- [AZ-204 Exam Guide](https://docs.microsoft.com/en-us/learn/certifications/exams/az-204)
- [Azure SDK for Java](https://docs.microsoft.com/en-us/azure/developer/java/)
- [Spring Cloud Azure](https://docs.microsoft.com/en-us/azure/developer/java/spring-framework/)

### Laboratórios
- [Microsoft Learn - AZ-204](https://docs.microsoft.com/en-us/learn/paths/create-azure-app-service-web-apps/)
- [Azure Samples](https://github.com/Azure-Samples)

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request

## 📄 Licença

Este projeto é licenciado sob a MIT License - veja o arquivo [LICENSE](LICENSE) para detalhes.

## 🆘 Suporte

Se você encontrar problemas ou tiver dúvidas:
1. Verifique os logs da aplicação
2. Confirme as configurações do profile ativo
3. Teste primeiro com o profile `local`
4. Abra uma issue no GitHub

---

**Boa sorte nos estudos para o AZ-204! 🚀**
