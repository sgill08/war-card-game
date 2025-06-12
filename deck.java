package War;
import java.util.*;
public class deck {
	public static void makeAndShuffle(ArrayList<String> p, ArrayList<String> c)
	{
		int[] deck = new int[52];
		String[] ranks = {"2","3","4","5","6","7","8","9","10","11","12","13"};
		for(int i = 0; i < deck.length; i++)
		{
			deck[i] = i;
		}
		for(int i = 0; i < 150; i++)
		{
			int indexVal = (int) (Math.random() * deck.length);
			int temp = deck[i % 52];
			deck[i % 52] = deck[indexVal];
			deck[indexVal] = temp;
			
		}
		String[] rank = new String[52];

		for(int i = 0; i < 52; i++)
		{
				rank[i] = ranks[(deck[i] % 12)];
		}
		for(int i = 0; i < deck.length; i++)
		{
			if(i%2 == 0)
			{
				p.add(rank[i]);
			}
			else
			{
				c.add(rank[i]);
			}
		}
	}
	public static String check(int a)
	{
		String r = "";
		if(a == 10)
		{
			r = "Jack";
		}
		if(a == 11)
		{
			r = "Queen";
		}
		if(a == 12)
		{
			r = "King";
		}
		if(a == 13)
		{
			r = "Ace";
		}
		return r;
		
	}
	public static void playGame(ArrayList<String> p, ArrayList<String> c)
	{
		if(p.size() >= 0 || c.size() >= 0)
		{
			int pCard = Integer.parseInt(p.get(0));
			int cCard = Integer.parseInt(c.get(0));
			if(pCard > cCard)
			{
				if(pCard >= 10)
				{
					System.out.println("Player wins with a " + check(pCard));
				}
				else
				{
					System.out.println("Player wins with a " + pCard);
				}
				p.remove(0);
				c.remove(0);
				p.add("" + pCard);
				p.add("" + cCard);
				if(p.size() == 52)
				{
					endGame(p,c);
				}
			}
			else if(cCard > pCard)
			{
				if(cCard >= 10)
				{
					System.out.println("Computer wins with a " + check(cCard));
				}
				else
				{
					System.out.println("Computer wins with a " + cCard);
				}
				p.remove(0);
				c.remove(0);
				c.add("" + pCard);
				c.add("" + cCard);
				if(c.size() == 52)
				{
					endGame(p,c);
				}
			}
			else if(pCard == cCard)
			{
				war(p,c);
			}
		}

	}
	/*
	 * Lordy lord use comments pls 
	 * I think this starts the game
	 */
	public static void war(ArrayList<String> p, ArrayList<String> c)
	{
		ArrayList<String> middlep = new ArrayList<String>();
		ArrayList<String> middlec = new ArrayList<String>();
		ArrayList<String> middle = new ArrayList<String>();
		int pCard = 0;
		int cCard = 0;
		// do regular war if both players have enough cards in their hands //
		if(p.size() >= 5 && c.size() >= 5)
		{
			for(int i = 0; i < 4; i++)
			{
				middlep.add(p.get(i));
				middlec.add(c.get(i));
			}
			for(int i = 3; i >= 0; i--)
			{
				p.remove(i);
				c.remove(i);
			}
			pCard = Integer.parseInt(p.get(0));
			cCard = Integer.parseInt(c.get(0));
			p.remove(0);
			c.remove(0);
			if(pCard == cCard)
			{
				p.add("" + pCard);
				c.add("" + cCard);
				for(int i = 0; i < middlep.size(); i++)
				{
					p.add(middlep.get(i));
				}
				for(int i = 0; i < middlec.size(); i++)
				{
					c.add(middlec.get(i));
				}
				for(int i = middlep.size()-1; i >= 0; i--)
				{
					middlep.remove(i);
					middlec.remove(i);
				}
				war(p,c);
			}
			if(pCard > cCard)
			{
				p.add("" + cCard);
				p.remove(0);
				p.add("" + pCard);
				for(int i = 0; i < middlep.size(); i++)
				{
					p.add(middlep.get(i));
				}
				for(int i = 0; i < middlec.size(); i++)
				{
					p.add(middlec.get(i));
				}
				if(p.size() == 52)
				{
					endGame(p, c);
				}
			}
			if(cCard > pCard)
			{
				c.add("" + pCard);
				c.remove(0);
				c.add("" + cCard);
				for(int i = 0; i < middlep.size(); i++)
				{
					p.add(middlep.get(i));
				}
				for(int i = 0; i < middlec.size(); i++)
				{
					p.add(middlec.get(i));
				}
				if(c.size() == 52)
				{
					endGame(p, c);
				}
			}
			for(int i = middlep.size()-1; i >= 0; i--)
			{
				middlep.remove(i);
			}
			for(int i = middlec.size()-1; i >= 0; i--)
			{
				middlec.remove(i);
			}
		}
		else {
				if (p.size() < 5  && c.size() < 5) {
					for(int i = 0; i < p.size()-1; i++)
					{
						middle.add(p.get(0));
						middlep.add(p.get(0));
						p.remove(0);
					}
					for(int i = 0; i < c.size()-1; i++)
					{
						middle.add(c.get(0));
						middlec.add(c.get(0));
						c.remove(0);
					}
					pCard = Integer.parseInt(p.get(0));
					cCard = Integer.parseInt(c.get(0));
					middle.add("" + cCard);
					middle.add("" + pCard);
					middlec.add("" + cCard);
					middlep.add("" + pCard);
					c.remove(0);
					p.remove(0);
					if(pCard > cCard)
					{
						for(int i = middle.size()-1; i >= 0; i--)
						{
							p.add(middle.get(i));
							middle.remove(i);
						}
						if(p.size() == 52)
						{
							endGame(p, c);
						}
					}
					else if(cCard > pCard)
					{
						for(int i = middle.size()-1; i>=0; i--)
						{
							c.add(middle.get(i));
							middle.remove(i);
						}
						if(c.size() == 52)
						{
							endGame(p,c);
						}
					}
					else if(pCard == cCard)
					{
						for(int i = 0; i < middlep.size(); i++)
						{
							p.add(middlep.get(i));
						}
						for(int i = 0; i < middlec.size(); i++)
						{
							c.add(middlec.get(i));
						}
						war(p, c);
					}
				}
				else if(p.size() < 5 && c.size() >= 5)
				{
					for(int i = 0; i < p.size()-1; i++)
					{
						middle.add(p.get(0));
						middlep.add(p.get(0));
						p.remove(0);
					}
					for(int i = 0; i < 4; i++)
					{
						middle.add(c.get(0));
						middlec.add(c.get(0));
						c.remove(0);
					}
					pCard = Integer.parseInt(p.get(0));
					cCard = Integer.parseInt(c.get(0));
					middlep.add("" + pCard);
					middlec.add("" + cCard);
					p.remove(0);
					c.remove(0);
					if(pCard > cCard)
					{
						for(int i = middle.size()-1; i >= 0; i--)
						{
							p.add(middle.get(i));
							middle.remove(i);
						}
						if(p.size() == 52)
						{
							endGame(p, c);
						}
					}
					else if(cCard > pCard)
					{
						for(int i = middle.size()-1; i>=0; i--)
						{
							c.add(middle.get(i));
							middle.remove(i);
						}
						if(c.size() == 52)
						{
							endGame(p,c);
						}
					}
				}
				else if(p.size() >= 5 && c.size() < 5)
				{
					for(int i = 0; i < c.size()-1; i++)
					{
						middle.add(c.get(0));
						middlec.add(c.get(0));
						c.remove(0);
					}
					for(int i = 0; i < 4; i++)
					{
						middle.add(p.get(0));
						middlep.add(p.get(0));
						p.remove(0);
					}
					pCard = Integer.parseInt(p.get(0));
					cCard = Integer.parseInt(c.get(0));
					middlep.add("" + pCard);
					middlec.add("" + cCard);
					p.remove(0);
					c.remove(0);
					if(pCard > cCard)
					{
						for(int i = middle.size()-1; i >= 0; i--)
						{
							p.add(middle.get(i));
							middle.remove(i);
						}
						if(p.size() == 52)
						{
							endGame(p, c);
						}
					}
					else if(cCard > pCard)
					{
						for(int i = middle.size()-1; i>=0; i--)
						{
							c.add(middle.get(i));
							middle.remove(i);
						}
						if(c.size() == 52)
						{
							endGame(p,c);
						}
					}
				}
			}
		for(int i = middle.size()-1; i >= 0; i--)
		{
			middle.remove(i);
		}
		for(int i = middlec.size()-1; i >= 0; i--)
		{
			middlec.remove(i);
		}
		for(int i = middlep.size()-1; i >= 0; i--)
		{
			middlep.remove(i);
		}
	}
	public static void endGame(ArrayList<String> p, ArrayList<String> c)
	{
		if(p.size() == 0)
		{
			System.out.println("The computer wins the game!");
			System.exit(0);
		}
		else if(c.size() == 0)
		{
			System.out.println("The player wins the game!");
			System.exit(0);
		}
	}
	public static void main(String []args )
	{
		
		ArrayList<String> player = new ArrayList<String>();
		ArrayList<String> computer = new ArrayList<String>();
		makeAndShuffle(player, computer);
		while(player.size() != 0 || computer.size() != 0)
		{
			if(player.size() > 0 || computer.size() > 0)
			{
				if(player.size() == 0 || computer.size() == 0)
				{
					endGame(player, computer);
					System.exit(0);
				}
				int pCard = Integer.parseInt(player.get(0));
				int cCard = Integer.parseInt((computer.get(0)));
				if(pCard > cCard)
				{
					if(pCard >= 10)
					{
						System.out.println("Player wins with a " + check(pCard));
					}
					else
					{
						System.out.println("Player wins with a " + pCard);
					}
					player.remove(0);
					computer.remove(0);
					player.add("" + pCard);
					player.add("" + cCard);
				}
				else if(cCard > pCard)
				{
					if(cCard >= 10)
					{
						System.out.println("Computer wins with a " + check(cCard));
					}
					else
					{
						System.out.println("Computer wins with a " + cCard);
					}
					player.remove(0);
					computer.remove(0);
					computer.add("" + pCard);
					computer.add("" + cCard);
				}
				else if(pCard == cCard)
				{
					war(player,computer);
				}
			}
		}
		
	}
}

