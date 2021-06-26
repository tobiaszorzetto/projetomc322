
	/*public void jogarTurno(){

		this.mesa.printCartasNaMesa(this.mana);

		this.sortearDoDeck();//pega uma carta aleatoria de seu deck
		Scanner keyboard = new Scanner(System.in);
		boolean running = true;
		while (running) {
			if (this.mao.getSize()>0){
				System.out.println(this.nome + ", quais dessas cartas deseja jogar?");
				int i = 0;
				for (Carta carta : mao.getDeck()){
					i++;
					System.out.printf("| %d - %s (%d) |", i, carta.getNome(), carta.getMana());
				}
				System.out.println();
				String command = keyboard.nextLine();

				if (command.compareTo("0") == 0) {
					running = false;
				}
				else if (Integer.parseInt(command)<= mao.getSize()){
					int numero_carta = Integer.parseInt(command) - 1;
					this.jogarCarta(numero_carta);
				}

			}
			else{
				System.out.println(this.nome + " voce ja n tem mais cartas disponiveis");
				running = false;
			}
			this.mesa.verificarCondicoes();
			this.mesa.printCartasNaMesa(this.mana);
		}
	}*/

	/*public void atacar(){
		ArrayList<Seguidor> cartas_na_mesa = this.mesa.getCartasMesa(this);
		ArrayList<Seguidor> falta_atacar = new ArrayList<Seguidor>();
		for (Seguidor item : cartas_na_mesa) falta_atacar.add(item);

		this.mesa.printCartasNaMesa(falta_atacar);

		Scanner keyboard = new Scanner(System.in);
		boolean running = true;
		while (running) {

			System.out.println("Defina carta para atacar: ");
			String command = keyboard.nextLine();

			if (command.compareTo("0") == 0) {
				running = false;
			}
			else if(Integer.parseInt(command) <= cartas_na_mesa.size()){
				int numero_carta = Integer.parseInt(command) - 1;
				Seguidor carta = cartas_na_mesa.get(numero_carta);
				carta.atacar();
				if (carta.getTraco() == Traco.ATAQUEDUPLO){
					carta.atacar();
				}
				falta_atacar.remove(carta);

			}
			else{
				System.out.println("Nao existe carta com esse indice");
			}
			this.mesa.verificarCondicoes();
			this.mesa.printCartasNaMesa(falta_atacar);
		}

	}*/