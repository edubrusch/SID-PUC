Eduardo Bastos Brusch - Speculate

Projeto criado utilizando maven.

Requerimentos:

Java: 1.8
Maven: 3.0+

O compliance do c�digo em si usa apis do java at� 1.7, mas s� foi compilado em java 1.8 somente.
vers�o do maven utilizada: 3.3.9 com jdk 1.8.0_91


Compila��o:

Utilizar o comando: clean package assembly:single

Ser�o criados os seguintes arquivos:

[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ ebruschSpeculate ---
[INFO] Building jar: D:\me\PUCRS\sistemas distribuidos\git\ebruschSpeculate\target\ebruschSpeculate-1.0.jar
[INFO] 
[INFO] --- maven-assembly-plugin:2.6:single (create-executable-jar) @ ebruschSpeculate ---
[INFO] Building jar: D:\me\PUCRS\sistemas distribuidos\git\ebruschSpeculate\target\ebruschSpeculate-1.0-src.jar
[INFO] Building jar: D:\me\PUCRS\sistemas distribuidos\git\ebruschSpeculate\target\ebruschSpeculate-1.0-jar-with-dependencies.jar
[INFO] 
[INFO] --- maven-assembly-plugin:2.6:single (default-cli) @ ebruschSpeculate ---
[INFO] Building jar: D:\me\PUCRS\sistemas distribuidos\git\ebruschSpeculate\target\ebruschSpeculate-1.0-src.jar
[INFO] Building jar: D:\me\PUCRS\sistemas distribuidos\git\ebruschSpeculate\target\ebruschSpeculate-1.0-jar-with-dependencies.jar


O arquivo que deve ser utilizado � o "ebruschSpeculate-1.0-jar-with-dependencies.jar"

Para que funcione, o arquivo "game.properties" deve ser incluso na mesma pasta que o jogo.



Configura��es:

O arquivo game.properties cont�m as seguintes configura��es:

- Op��es do servidor

	MAX_MATCH_COUNT: n�mero m�ximo de jogos dentro do servidor;
	DEFAULT_PLAYER_BALL_COUNT: n�mero inicial de bolas de cada jogador.

- Op��es do cliente

	SERVER_ADDRESS: endere�o do servidor.



Executando:

Para executar como servidor, execute o comando java -jar ebruschSpeculate-1.0-jar-with-dependencies.jar -m server
Para executar como cliente, execute o comando java -jar ebruschSpeculate-1.0-jar-with-dependencies.jar -m client



Terminando a execu��o:

O cliente termina a execu��o no fim de cada jogo, ou ao pressionar CTRL + C.
O servidor termina ao pressionar CTRL + C.