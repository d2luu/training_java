 # Training JAVA - Luu 
ダオ・ザン・リューの新入社員教育のプロジェクト

## サポートブラウザ

- サポートブラウザはmacOS/Chrome各最新版を想定しています

## アプリケーション（サーバ）の構成について

- JAVA
- SpringBoot FrameWork
- MySQL
- Gradle
- Eclipse
- MySQL Workbench

テーブルスキーマ
## テーブルスキーマ
|      users             |     tasks            |    
| ---------------------- |------------------     |        
|  id INT `PK`      	     | id INT				|		
|  name  VARCHAR         | name VARCHAR          	|  
|  email VARCHAR     	 | description VARCHAR	|		  
|  password  VARCHAR     | deadline DATETIME		|
|						| status INT            	| 
|						| label VARCHAR			|
|						| priority INT			|
|						| user_id `FK`			|