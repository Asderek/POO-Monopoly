Guia para testes

Teste Entrada e Saida da Cadeia:

	Inicie o Jogo;
	Clique nas peças no meio do tabuleiro
		para que elas vão para a partida;
	Clique no botao DebugMenu;
	Clique em SetResult;
	Escreva 24;	
	Ande com a peça (da Cor do Turno) ateh va para a muralha;
	Clique no dado, em baixo do tabuleiro, e veja que a peça	
		não pode ser deslocada;		
						Teste Entrada (x)

	Clique no botao Pass, ateh passar pelo turno da peça
		presa 3x, e veja a mensagem para saida da prisão;
						
						Teste Saida   (x)

Teste Compra de Terreno:

	Inicie o Jogo;
	Clique nas peças no meio do tabuleiro
		para que elas vão para a partida;
	Clique no botao DebugMenu;
	Clique em SetResult;
	Escreva 1 (ou qualquer valor de casa colorida);
	Ande até a casa The Shire (ou casa correspondente);
	Veja a mensagem de compra de Local;
						Teste Compra   (x)

Teste Construção de Imovel:

	Inicie o Jogo;
	Clique nas peças no meio do tabuleiro
		para que elas vão para a partida;
	Clique no botao DebugMenu;
	Clique em CompraCasa;
	Escreva 9;
	Clique em Sim;
	Clique em CompraCasa;
	Escreva 11;
	Clique em Sim;
	Clique em SetResult;
	Escreva 1;
	Ande até a casa The Shire;
	Veja a mensagem de compra de Imovel;
						Teste Constr	(x)



Teste Hipoteca:

	Inicie o Jogo;
	Clique nas peças no meio do tabuleiro
		para que elas vão para a partida;
	Clique no botao DebugMenu;
	Clique em CompraCasa;
	Escreva 9;
	Clique em Sim;
	No Tabuleiro, clique 2x sobre a casa a hipotecar;
	Clique no botão Vender;
	Escolha a Opção Hipoteca;
	Clique em sim, e veja que seu dinheiro foi alterado
		no valor da hipoteca;
						Teste Hipoteca	(x)

Teste Falencia:

	Inicie o Jogo;
	Clique nas peças no meio do tabuleiro
		para que elas vão para a partida;
	Clique no botao DebugMenu;
	Clique em SetFundos;
	Escreva 0;
	Clique no botão Pass, e veja que sua peça foi removida
		tanto do tabuleiro, quanto da lista de turnos;
						Teste Falencia	(x)

Teste do Vencedor:

	Repita o Teste Falencia para todos os jogadores
		Exceto um;
	Veja o dialogo de Parabenização do Vencedor;
						Teste Vencedor	(x)