#!/bin/bash

# Script para coletar código de gradle.properties, build.gradle, arquivos .java e .json do src/,
# listar caminhos dos .png do src/, e criar um arquivo projeto.txt com tudo.

# Criar ou limpar o arquivo projeto.txt
> projeto.txt

# Adicionar conteúdo de gradle.properties
echo "=== gradle.properties ===" >> projeto.txt
cat gradle.properties >> projeto.txt
echo "" >> projeto.txt

# Adicionar conteúdo de build.gradle
echo "=== build.gradle ===" >> projeto.txt
cat build.gradle >> projeto.txt
echo "" >> projeto.txt

# Adicionar conteúdo de todos os arquivos .java do src/
echo "=== Arquivos .java ===" >> projeto.txt
find src/ -name "*.java" -exec sh -c 'echo "=== $1 ===" >> projeto.txt; cat "$1" >> projeto.txt; echo "" >> projeto.txt' _ {} \;

# Adicionar conteúdo de todos os arquivos .json do src/
echo "=== Arquivos .json ===" >> projeto.txt
find src/ -name "*.json" -exec sh -c 'echo "=== $1 ===" >> projeto.txt; cat "$1" >> projeto.txt; echo "" >> projeto.txt' _ {} \;

# Listar caminhos dos arquivos .png do src/
echo "=== Caminhos dos arquivos .png ===" >> projeto.txt
find src/ -name "*.png" >> projeto.txt

echo "Arquivo projeto.txt criado com o código coletado."