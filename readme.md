#Projeto Hamburgueria Brazier
Crie um sistema da Hamburgueria Brazier, em Java usando o paradigma programação orientada a objetos, usando herança e encapsulamento (se necessário) com os seguintes requisitos:
- Deve possuir Interface Grafica simples e intuitiva.
- Deve criar arquivo data.json para que os dados utilizados pelas classes sejam utilizados em instancias futuras;

Classe Endereço:
	- atributos: rua, numero, bairro, complemento. Deve ser feito validação de todos os campos preenchidos de forma correta.

Classe Cliente: 
	- atributos: id, nome, endereço (herança de Endereço), telefone, cpf. Deve ser feito a validação do formato do CPF para o padrão brasileiro.
	- Método CadastrarClientes: usuário deve ter que informar os atributos da classe, onde o id será incrementado de forma sequencial e automática. Deve ser possível fazer edição futura dos dados do cliente.
	- Método HistoricoPedidos: deve ser armazenado um histórico dos pedidos realizados pelo cliente.

Classe Estoque: 
	- atributos: id, ingrediente, valor, quantidade. 
	- Método CadastrarIngrediente para inserir novos itens com os atributos da classe. Deve ser possível edição futura dos dados do ingrediente. Id deve ser incrementado de forma automática.

Classe Catalogo: 
	- atributos: id, nomeLanche, ingrediente (herança de Estoque), valorCusto, valorSugerido, valorFinal. 
	- Método para CadastrarLanche, onde deve selecionar os itens que compõem o lanche e sua respectiva quantidade. Deve usar o método ValorSugerido para exibir o valorSugerido e assim o usuário informar o valorFinal. Deve ser possível a edição futura dos dados do lanche. Id deve ser incrementado de forma automática.
	- Método para calcular ValorDeCusto (soma do valor dos itens que compõem o lanche)
	- Método para calcular ValorSugerido.
	- Método ExibirLanche: Exibe as informações do lanche, nomeLanche, ingrediente, valorCusto, valorSugerido (34% sobre o ValorDeCusto), valorFinal

Classe Pedido: 
	- atributos: id, cliente (herança de Cliente), lanche (herança de Catalago nomeLanche), valorTotal, pagamento (pix, cartão crédito, cartão débito, espécie), status, frete. 
	- Método CriarPedido: usuário selecionará o cliente, selecionar lanche cadastrado (herança Catalago), quantidade. Nesse método deverá descontar no Estoque a quantidade dos itens que compõem o lanche do pedido (Herança de Estoque). Deve ter a opção de cadastrar um ou mais lanches. Pagamento atribuido automaticamente para Pendente. StatusPedido atribuído automaticamento para Fila. Usuário seleciona se o frete é delivery ou retirada. Se delivery deve ser inserido o valor do frete.
	- Método Desconto: Método para aplicar desconto no valorTotal do pedido. Usuário informa quantos porcento sobre o valoTotal do pedido.
	- Método Pagamento: Ao finalizar CriarPedido deve ser iniciar o método de pagamento e selecionar o opcao de pagamento status pagamento: Pendente ou Pago.
	- Método StatusPedido: Ao finalizar o método Pagamento, o pedido é atribuido automaticamente para Fila. Usuário terá opção de mudar status do pedido para Produção, Concluido ou Cancelado. Ao mudar para status de pedido cancelado deve atualizar o estoque (devolução dos itens).
	- Método ListarPedidos: método para listar os pedidos com filtro por StatusPedido.

Classe Caixa: 
	- atributos: saldo, entrada, saida
	- Método Entrada: método para inserir valores de entrada (Herança classe Pedido). Ao atualizar o status de pagamento de algum pedido para Pago o valorTotal do pedido é somado ao saldo com a descrição padrao "Recebimento pedido ID: getIdPedido". Se o usuário voltar o status de pagamento de pedido para Pendente, o mesmo deve descontar do saldo. Usuário poderá ter a opção de adicionar qualquer entrada com descrição e o valor de entrada. Deve salvar automaticamente a data e horário da entrada.
	- Método Saída: usuário insere uma descrição e o valor de saída. Deve salvar automaticamente a data e horário da saída.
	- Método RelatorioCaixa: gerará um relatório diário, mensal, anual, com SaldoInicial, SaldoFinal, SomaEntradas, SomaSaidas, CustosIngredientes, Lucro.
