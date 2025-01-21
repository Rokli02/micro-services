# Webshop Micro-service Backend

## Leírás

A feladatunk egy kitalált webshop backendjének az implementálása mikroszervíz architektúrában. Termékeket tudunk lekérni, módosítani és törölni. Regisztrálhatunk, beléphetünk a fiókunkba, ezáltal mégtöbb feature válik elérhetővé. Termékeket tudunk rendelni (akár anoním módon is) és a korábbi rendeléseinket lekérni, státuszukat ellenőrizni. 

## Adatbázis kezelés

Az elindításhoz szükséges lesz a "Docker" alkalmazáshoz. A root könyvtárban kell hogy legyünk, aminek neve "micro-services", ugyanis innen tudjuk kiadni a parancsokat.

### Initializáció

Az adatbázis, a konténer létrejöttekor végrehajtja a `data/init/init.sql` fájlban található parancsokat. Itt lehet megadni milyen táblák jöjjenek létre valamint milyen értékekkel töltse elő.

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