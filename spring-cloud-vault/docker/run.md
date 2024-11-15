## pull vault
```shell
docker pull vault:1.13.3
```
## run vault
```shell
docker run --cap-add=IPC_LOCK -e 'VAULT_DEV_ROOT_TOKEN_ID=root' -e 'VAULT_DEV_LISTEN_ADDRESS=0.0.0.0:8200' -p 8200:8200 --name=vault -d vault:1.13.3
```
## env
```shell
export VAULT_TOKEN="hvs.9rUli4rwArRrAUYkVp0NVUlu"
export VAULT_ADDR="http://0.0.0.0:8200"
```
## init vault
```shell
vault kv put secret/application example.username=myuser example.password=mypassword
vault kv put secret/gs-vault-config example.username=demouser example.password=demopassword
vault kv put secret/gs-vault-config/cloud example.username=clouduser example.password=cloudpassword
```