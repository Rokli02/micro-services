# Webshop Micro-service Backend

## Leírás

A feladatunk egy kitalált webshopnak a backendjének implementálása mikroszervíz architektúrában. Termékeket tudunk lekérni, módosítani. Regisztrálhatunk és beléphetünk ebbe a fiókunkba, így mégtöbb feature válik elérhetővé. Termékeket tudunk rendelni (akár anoním módon is) és a korábbi rendeléseinket lekérni, a státuszukat ellenőrizni. 

## Adatbázis kezelés

Az elindításhoz szükséges lesz a "Docker" alkalmazáshoz. A root könyvtárban kell hogy legyünk, aminek neve "micro-services", ugyanis innen tudjuk kiadni a parancsokat.

### Initializáció

Az adatbázis, a konténer létrejöttekor végrehajtja a következő fájlban található parancsokat `data/init/init.sql`. Az itt lehet megadni milyen táblák jöjjenek létre és esetlegesen milyen kezdeti értékek kerüljenek bele.

### Indítás

```
docker compose up -d
```

### Leállítás

```
docker compose down -v
```

### Adatbázis Log lekérdezés

```
docker logs -f micro-services-database-1
```

## Szervízek indítása

Jelenleg ezeket egyesével, manuálisan kell elindítani a fejlesztői környezettől függően.

Az alábbi portokon találhatók a szervizek:
- 3001 - user
- 3002 - product
- 3003 - order