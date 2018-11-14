package me.justicepro.spigotgui.Utils;

public enum ChatColor {
	
	black('0'),
	dark_blue('1'),
	dark_green('2'),
	dark_aqua('3'),
	dark_red('4'),
	dark_purple('5'),
	gold('6'),
	gray('7'),
	dark_gray('8'),
	blue('9'),
	green('a'),
	aqua('b'),
	red('c'),
	light_purple('d'),
	yellow('e'),
	white('f'),
	
	magic('k'),
	bold('b'),
	strike('m'),
	underline('n'),
	italic('o'),
	reset('r'),
	
	;
	
	private char c;
	
	private ChatColor(char c) {
		this.c = c;
	}
	
	@Override
	public String toString() {
		return "§" + c;
	}
	
}