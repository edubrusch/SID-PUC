Eduardo Bastos Brusch - Speculate

Projeto criado utilizando maven.

Requerimentos:

Java: 1.8
Maven: 3.0+

O compliance do código em si usa apis do java até 1.7, mas só foi compilado em java 1.8 somente.
versão do maven utilizada: 3.3.9 com jdk 1.8.0_91


Compilação:

Utilizar o comando: clean package assembly:single

Serão criados os seguintes arquivos:

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


O arquivo que deve ser utilizado é o "ebruschSpeculate-1.0-jar-with-dependencies.jar"

Para que funcione, o arquivo "game.properties" deve ser incluso na mesma pasta que o jogo.



Configurações:

O arquivo game.properties contém as seguintes configurações:

- Opções do servidor

	MAX_MATCH_COUNT: número máximo de jogos dentro do servidor;
	DEFAULT_PLAYER_BALL_COUNT: número inicial de bolas de cada jogador.

- Opções do cliente

	SERVER_ADDRESS: endereço do servidor.



Executando:

Para executar como servidor, execute o comando java -jar ebruschSpeculate-1.0-jar-with-dependencies.jar -m server
Para executar como cliente, execute o comando java -jar ebruschSpeculate-1.0-jar-with-dependencies.jar -m client



Terminando a execução:

O cliente termina a execução no fim de cada jogo, ou ao pressionar CTRL + C.
O servidor termina ao pressionar CTRL + C.