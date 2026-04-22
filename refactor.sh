#!/bin/bash

echo "== Backup =="
cp -r src src_backup

echo "== Criando estrutura =="
mkdir -p src/main/java/com/bernardo/dbi/core
mkdir -p src/main/java/com/bernardo/dbi/stats/attributes
mkdir -p src/main/java/com/bernardo/dbi/entity/ki
mkdir -p src/main/java/com/bernardo/dbi/render/ki

echo "== Movendo CORE =="
mv src/main/java/com/bernardo/dbi/main/* src/main/java/com/bernardo/dbi/core/ 2>/dev/null
rm -rf src/main/java/com/bernardo/dbi/main

echo "== Movendo ATTRIBUTES =="
mv src/main/java/com/bernardo/dbi/status/Str.java src/main/java/com/bernardo/dbi/stats/attributes/ 2>/dev/null
mv src/main/java/com/bernardo/dbi/status/Con.java src/main/java/com/bernardo/dbi/stats/attributes/ 2>/dev/null
mv src/main/java/com/bernardo/dbi/status/Dex.java src/main/java/com/bernardo/dbi/stats/attributes/ 2>/dev/null
mv src/main/java/com/bernardo/dbi/status/Spi.java src/main/java/com/bernardo/dbi/stats/attributes/ 2>/dev/null
mv src/main/java/com/bernardo/dbi/status/Will.java src/main/java/com/bernardo/dbi/stats/attributes/ 2>/dev/null
mv src/main/java/com/bernardo/dbi/status/Mnd.java src/main/java/com/bernardo/dbi/stats/attributes/ 2>/dev/null

echo "== Movendo StatsManager =="
mv src/main/java/com/bernardo/dbi/status/StatsManager.java src/main/java/com/bernardo/dbi/stats/ 2>/dev/null

echo "== Limpando pasta antiga =="
rm -rf src/main/java/com/bernardo/dbi/status

echo "== Movendo KiBlast =="
mv src/main/java/com/bernardo/dbi/entity/EntityKiBlast.java src/main/java/com/bernardo/dbi/entity/ki/ 2>/dev/null
mv src/main/java/com/bernardo/dbi/render/RenderKiBlast.java src/main/java/com/bernardo/dbi/render/ki/ 2>/dev/null

echo "== Corrigindo packages =="
sed -i 's/package com.bernardo.dbi.main;/package com.bernardo.dbi.core;/' src/main/java/com/bernardo/dbi/core/*.java

sed -i 's/package com.bernardo.dbi.status;/package com.bernardo.dbi.stats.attributes;/' src/main/java/com/bernardo/dbi/stats/attributes/*.java

sed -i 's/package com.bernardo.dbi.status;/package com.bernardo.dbi.stats;/' src/main/java/com/bernardo/dbi/stats/StatsManager.java

sed -i 's/package com.bernardo.dbi.entity;/package com.bernardo.dbi.entity.ki;/' src/main/java/com/bernardo/dbi/entity/ki/EntityKiBlast.java

sed -i 's/package com.bernardo.dbi.render;/package com.bernardo.dbi.render.ki;/' src/main/java/com/bernardo/dbi/render/ki/RenderKiBlast.java

echo "== Corrigindo imports =="
sed -i 's/com.bernardo.dbi.status./com.bernardo.dbi.stats.attributes./g' src/main/java/com/bernardo/dbi/stats/StatsManager.java

echo "== Finalizado =="
echo "Agora roda: ./gradlew build"
