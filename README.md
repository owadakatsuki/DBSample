# Form Application
## favicon
######置き場所
```
static/
```
## database(mysql)
######バックアップ
```
mysqldump --single-transaction -u [ユーザ名] -p [データベース名] > [ファイル作る場所]
```
######復元
```
mysql -u [ユーザ名] -p [データベース名] < dumpファイル名
```
