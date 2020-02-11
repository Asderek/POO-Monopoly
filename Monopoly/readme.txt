Guia para testes

Teste Entrada e Saida da Cadeia:

	Inicie o Jogo;
	Clique nas pe�as no meio do tabuleiro
		para que elas v�o para a partida;
	Clique no botao DebugMenu;
	Clique em SetResult;
	Escreva 24;	
	Ande com a pe�a (da Cor do Turno) ateh va para a muralha;
	Clique no dado, em baixo do tabuleiro, e veja que a pe�a	
		n�o pode ser deslocada;		
						Teste Entrada (x)

	Clique no botao Pass, ateh passar pelo turno da pe�a
		presa 3x, e veja a mensagem para saida da pris�o;
						
						Teste Saida   (x)

Teste Compra de Terreno:

	Inicie o Jogo;
	Clique nas pe�as no meio do tabuleiro
		para que elas v�o para a partida;
	Clique no botao DebugMenu;
	Clique em SetResult;
	Escreva 1 (ou qualquer valor de casa colorida);
	Ande at� a casa The Shire (ou casa correspondente);
	Veja a mensagem de compra de Local;
						Teste Compra   (x)

Teste Constru��o de Imovel:

	Inicie o Jogo;
	Clique nas pe�as no meio do tabuleiro
		para que elas v�o para a partida;
	Clique no botao DebugMenu;
	Clique em CompraCasa;
	Escreva 9;
	Clique em Sim;
	Clique em CompraCasa;
	Escreva 11;
	Clique em Sim;
	Clique em SetResult;
	Escreva 1;
	Ande at� a casa The Shire;
	Veja a mensagem de compra de Imovel;
						Teste Constr	(x)



Teste Hipoteca:

	Inicie o Jogo;
	Clique nas pe�as no meio do tabuleiro
		para que elas v�o para a partida;
	Clique no botao DebugMenu;
	Clique em CompraCasa;
	Escreva 9;
	Clique em Sim;
	No Tabuleiro, clique 2x sobre a casa a hipotecar;
	Clique no bot�o Vender;
	Escolha a Op��o Hipoteca;
	Clique em sim, e veja que seu dinheiro foi alterado
		no valor da hipoteca;
						Teste Hipoteca	(x)

Teste Falencia:

	Inicie o Jogo;
	Clique nas pe�as no meio do tabuleiro
		para que elas v�o para a partida;
	Clique no botao DebugMenu;
	Clique em SetFundos;
	Escreva 0;
	Clique no bot�o Pass, e veja que sua pe�a foi removida
		tanto do tabuleiro, quanto da lista de turnos;
						Teste Falencia	(x)

Teste do Vencedor:

	Repita o Teste Falencia para todos os jogadores
		Exceto um;
	Veja o dialogo de Parabeniza��o do Vencedor;
						Teste Vencedor	(x)